package com.generador.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.CPInstruction;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.BooleanGene;
import org.jgap.impl.DefaultConfiguration;

import com.generador.modelo.Horario;
import com.generador.modelo.Materia;

/**
 * Clase encargada de la optimización del horario
 * 
 * @author Luis Reinoso
 *
 */

public class Organizador {

	private List<Materia> listaMaterias;
	private List<List<Materia>> listaSoluciones;
	private List<IChromosome> listaCromosomas;
	private List<Integer> listaSeleccionado;
	private int numeroMateriasDisponibles;
	private int numeroMateriasObligatorias;
	private int minCreditos;
	private int maxCreditos;
	
	public Organizador(List<Materia> listaMaterias, int minCreditos ,int maxCreditos, List<Integer> listaSeleccionado) {
		this.listaMaterias = listaMaterias;
		this.listaSoluciones = new ArrayList<List<Materia>>(5);
		this.listaCromosomas = new ArrayList<IChromosome>();
		this.listaSeleccionado = listaSeleccionado;
		this.numeroMateriasDisponibles = this.listaMaterias.size();
		//TODO Considerar Plan de titulacion
		this.numeroMateriasObligatorias = intMateriasObligatorios(listaMaterias) - 1;
		this.minCreditos = minCreditos;
		this.maxCreditos = maxCreditos;
	}

	public void calcularHorarioOptimo () {

		//Configurar en varias ocaciones
		Configuration.reset();
		
		//Configuracion predeterminada
		Configuration config = new DefaultConfiguration();

		//Almacenar mejor cada generacion
		config.setPreservFittestIndividual(true);

		//Constructor fitness
		FitnessFunction fitness = new FitnessHorario(listaMaterias, minCreditos, maxCreditos, listaSeleccionado);
		
		int fitnessEsperado = numeroMateriasObligatorias * 10 + listaSeleccionado.size() * 100;
		
		try {
			//Configurar fitness
			config.setFitnessFunction(fitness);

			//Gen de ejemplo
			Gene[] genHorario = new Gene[numeroMateriasDisponibles];
			for (int i = 0; i < numeroMateriasDisponibles; i++) {
				genHorario[i] = new BooleanGene(config,false);	
			}

			IChromosome cromosoma = new Chromosome(config, genHorario);
			config.setSampleChromosome(cromosoma);
			config.setPopulationSize(300);

			Genotype poblacion;
			poblacion = Genotype.randomInitialGenotype(config);

			IChromosome cromosomaOptimo;

			//Evolucion
			for (int i = 0; i < 2500; i++) {
				poblacion.evolve();
				cromosomaOptimo = poblacion.getFittestChromosome();
				
				if (cromosomaOptimo.getFitnessValue() >= fitnessEsperado 
						&& !listaCromosomas.contains(cromosomaOptimo))
				{
					listaCromosomas.add(cromosomaOptimo);
					System.out.println("Fitness: "+cromosomaOptimo.getFitnessValue());

					listaSoluciones.add(resultado(cromosomaOptimo));
					System.out.println();
				}
			}
			System.out.println("Esperado:"+fitnessEsperado);
			System.out.println("Obligatorias:"+numeroMateriasObligatorias);
			System.out.println("Seleccionado:"+listaSeleccionado.size());
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public List<Materia> resultado (IChromosome cromosomaOptimo) {

		List<Materia> solucion = new ArrayList<Materia>(8);
		for (int i = 0; i < numeroMateriasDisponibles; i++) {
			if ((boolean) cromosomaOptimo.getGene(i).getAllele()){
				solucion.add(listaMaterias.get(i));
				System.out.println(listaMaterias.get(i).toString());
			}
		}
		
		List<Horario> listaHorarios = new ArrayList<Horario>();
		Horario utilidad = new Horario(" ", " ", " ", " ", " ", " ");
		Horario sumatorio = null, horasHuecas;
		//Restricción horas huecas
		for (Materia materia : solucion) {
			listaHorarios.add(materia.getHorario());
		}
		sumatorio = utilidad.sumatorioHorarios(listaHorarios);
		horasHuecas = utilidad.horasHuecas(sumatorio);
		System.out.println("sumatorio"+sumatorio);
		System.out.println("horasHuecas"+horasHuecas);
		return solucion;
	}

	public List<List<Materia>> getListaSoluciones() {
		return listaSoluciones;
	}

	public void setListaSoluciones(List<List<Materia>> listaSoluciones) {
		this.listaSoluciones = listaSoluciones;
	}
	
	public int intMateriasObligatorios(List<Materia> listaMaterias) {
		
		int contadorObligatorias = 0;
		for (Materia materia : listaMaterias) {
			
			if (materia.getCategoria().getStrCategoria().equals("FORMACION PROFESIONAL")
					&& materia.getCategoria().getStrSubCategoria().equals("OBLIGATORIAS")) {
				contadorObligatorias++;
				System.out.println(materia.getStrNombre());
			}
		}
		return contadorObligatorias;
	}
}
