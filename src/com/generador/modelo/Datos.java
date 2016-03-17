package com.generador.modelo;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.generador.utilidad.MultiMapaMaterias;

/**
 * Clase encargada de almacenar los datos de entrada en un multimapa
 * <p>
 * Los datos de entrada son los horarios posibles, y las materias posibles
 * </p>
 * 
 * @author Luis Reinoso
 */

public class Datos {

	private Document docMateriasPosibles;
	private Document docHorarioMaterias;
	private MultiMapaMaterias mapMaterias;
	private List<String> strMaterias = new ArrayList<String>();

	/**
	 * Constructor de la clase Datos
	 * 
	 * @param materiasPosibles
	 *            datos materias posibles
	 * @param horariosMaterias
	 *            datos horarios materias
	 */

	public Datos(Document materiasPosibles, Document horariosMaterias) {
		Element materia;
		Elements infoMateria;
		Materia clase;

		// Materias Posibles
		this.docMateriasPosibles = materiasPosibles;
		Elements tablaMateriasPosibles = docMateriasPosibles
				.getElementsByClass("controlgrilla").get(0).children().get(0)
				.children();

		// Horario Materias
		this.docHorarioMaterias = horariosMaterias;
		Elements tablaHorarioMaterias = docHorarioMaterias
				.getElementsByClass("controlgrilla").get(0).children().get(0)
				.children();

		// Almacenamiento Materias Posibles
		MultiMapaMaterias mapTablaMateriasPosibles = new MultiMapaMaterias();
		for (int i = 1; i < tablaMateriasPosibles.size() - 1; i++) {
			materia = tablaMateriasPosibles.get(i);
			infoMateria = materia.children();

			// Almacenamiento horarios disponibles de cada posible materia
			for (int j = 0; j < tablaHorarioMaterias.select(
					"tr:contains(" + infoMateria.get(1).text() + ")").size(); j++) {

				clase = new Materia(infoMateria.get(1).text(), infoMateria.get(
						2).text(), infoMateria.get(3).text(), infoMateria
						.get(4).text(), infoMateria.get(5).text(), infoMateria
						.get(6).text());

				// Información obtenida del archivo docHorariosMaterias
				clase.setStrParalelo(tablaHorarioMaterias
						.select("tr:contains(" + infoMateria.get(1).text()
								+ ")").get(j).children().get(3).text());
				clase.setStrAula(tablaHorarioMaterias
						.select("tr:contains(" + infoMateria.get(1).text()
								+ ")").get(j).children().get(4).text());
				// Agrega los horarios a cada materia que tenga dicha
				// informacion en docHorariosMaterias
				clase.setHorario(new Horario(tablaHorarioMaterias
						.select("tr:contains(" + infoMateria.get(1).text()
								+ ")").get(j).children().get(5).text(),
						tablaHorarioMaterias
								.select("tr:contains("
										+ infoMateria.get(1).text() + ")")
								.get(j).children().get(6).text(),
						tablaHorarioMaterias
								.select("tr:contains("
										+ infoMateria.get(1).text() + ")")
								.get(j).children().get(7).text(),
						tablaHorarioMaterias
								.select("tr:contains("
										+ infoMateria.get(1).text() + ")")
								.get(j).children().get(8).text(),
						tablaHorarioMaterias
								.select("tr:contains("
										+ infoMateria.get(1).text() + ")")
								.get(j).children().get(9).text(),
						tablaHorarioMaterias
								.select("tr:contains("
										+ infoMateria.get(1).text() + ")")
								.get(j).children().get(10).text()));

				mapTablaMateriasPosibles.put(infoMateria.get(1).text(), clase);
			}
		}
		this.mapMaterias = mapTablaMateriasPosibles;
	}

	/**
	 * @return retorna el multimapa con todas las materias.
	 */
	public MultiMapaMaterias getMapMaterias() {
		return mapMaterias;
	}

	/**
	 * @param mapMaterias
	 *            Multimapa de la clase Materia
	 */

	public void setMapMaterias(MultiMapaMaterias mapMaterias) {
		this.mapMaterias = mapMaterias;
	}

	/**
	 * @return Retorna una lista de arreglos(String) la cual contiene el nombre
	 *         de las materias
	 */
	public List<String> getStrMaterias() {
		this.strMaterias.clear();
		for (int i = 0; i < this.mapMaterias.keySet().size(); i++) {
			this.strMaterias
					.add((String) this.mapMaterias.keySet().toArray()[i]);
		}
		return strMaterias;
	}

	/**
	 * @param strMaterias
	 *            Lista de string con el nombre de las materias
	 */
	public void setStrMaterias(List<String> strMaterias) {
		this.strMaterias = strMaterias;
	}
}
