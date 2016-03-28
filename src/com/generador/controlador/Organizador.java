package com.generador.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
		this.numeroMateriasObligatorias = intMateriasObligatorios(listaMaterias);
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
		
		int fitnessEsperado = numeroMateriasObligatorias * 1000 + listaSeleccionado.size() * 1000;
		
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
			}
		}
		
		List<Horario> listaHorarios = new ArrayList<Horario>();
		
		//Restricción horas huecas
		for (Materia materia : solucion) {
			listaHorarios.add(materia.getHorario());
		}
		
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
		
		HashMap<Object, Materia> hashMap = new HashMap<>();
		
		for (Materia materia : listaMaterias) {
			try {
				hashMap.put(materia.getStrCodigo(), materia);
			} catch (Exception e) {
			}
		}
		
		Object[] keys = hashMap.keySet().toArray();
		
		for (int i = 0; i < keys.length; i++) {
			if (hashMap.get(keys[i]).getCategoria().getStrCategoria().equals("FORMACION PROFESIONAL")
					&& hashMap.get(keys[i]).getCategoria().getStrSubCategoria().equals("OBLIGATORIAS"))
				contadorObligatorias++;
		}
		
		return contadorObligatorias;
	}
}
