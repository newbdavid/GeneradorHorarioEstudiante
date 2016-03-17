package com.generador.controlador;

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
import com.generador.utilidad.MultiMapa;

/**
 * Clase encargada de la optimización del horario
 * 
 * @author Luis Reinoso
 *
 */

public class Organizador {
	
	private List<Materia> listaMaterias;
	private int numeroMaterias;
	
	public Organizador(MultiMapa mapMaterias) {
		this.listaMaterias = mapMaterias.getAllMaterias();
		this.numeroMaterias = this.listaMaterias.size();
	}
	
	public void calcularHorarioOptimo () {
		
		//Configuracion predeterminada
		Configuration config = new DefaultConfiguration();
		
		//Almacenar mejor cada generacion
		config.setPreservFittestIndividual(true);

		FitnessFunction fitness = new FitnessHorario(listaMaterias);
		try {
			//Configurar fitness
			config.setFitnessFunction(fitness);

			//Gen de ejemplo
			
			Gene[] genHorario = new Gene[numeroMaterias];
			for (int i = 0; i < numeroMaterias; i++) {
				genHorario[i] = new BooleanGene(config,false);	
			}
			
			IChromosome cromosoma = new Chromosome(config, genHorario);
			config.setSampleChromosome(cromosoma);
			config.setPopulationSize(200);
			
			Genotype poblacion;
			poblacion = Genotype.randomInitialGenotype(config);
			
			//Evolucion
			for (int i = 0; i < 2000; i++) {
				poblacion.evolve();	
			}
			
			IChromosome cromosomaOptimo = poblacion.getFittestChromosome();
			resultado(cromosomaOptimo);
			
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String infoMateria (int indice) {
		return listaMaterias.get(indice).toString();
	}
	
	public void resultado (IChromosome cromosomaOptimo) {
		
		Integer creditos = 0;
		System.out.println("valor:"+cromosomaOptimo.getFitnessValue());
		System.out.println("edad: "+cromosomaOptimo.getAge());
		for (int i = 0; i < numeroMaterias; i++) {
			System.out.println("gen"+i+":"+cromosomaOptimo.getGene(i).getAllele());
			if ((boolean) cromosomaOptimo.getGene(i).getAllele()) {
				System.out.println(infoMateria(i));
				creditos += listaMaterias.get(i).getIntCreditos();
			}
		}
		System.out.println("creditos total: "+creditos);
		
	}
}
