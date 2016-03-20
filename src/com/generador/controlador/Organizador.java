package com.generador.controlador;

import java.util.ArrayList;
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

		//Configuracion predeterminada
		Configuration config = new DefaultConfiguration();

		//Almacenar mejor cada generacion
		config.setPreservFittestIndividual(true);

		//Constructor fitness
		FitnessFunction fitness = new FitnessHorario(listaMaterias, minCreditos, maxCreditos, listaSeleccionado);
		
		//TODO verificar si se consideran las materias TODAS la materias obligatorias 
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
					listaSoluciones.add(resultado(cromosomaOptimo));
				}
			}	
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public List<Materia> resultado (IChromosome cromosomaOptimo) {

		System.out.println();
		List<Materia> solucion = new ArrayList<Materia>(8);
		for (int i = 0; i < numeroMateriasDisponibles; i++) {
			if ((boolean) cromosomaOptimo.getGene(i).getAllele()){
				solucion.add(listaMaterias.get(i));
				System.out.println(listaMaterias.get(i).toString());
			}
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
		for (Materia materia : listaMaterias) {
			if (materia.getCategoria().getStrSubCategoria().equals("OBLIGATORIAS"));
			contadorObligatorias++;
		}
		return contadorObligatorias;
	}
}
