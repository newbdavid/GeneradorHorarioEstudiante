package aplicacion;

/**
 * Clase encargada de establecer la prioridad según categoria
 * 
 * @author Luis Reinoso
 */

public class Categoria {
	private String strCategoria;
	private String strSubCategoria;
	private int intPrioridad;

	/**
	 * Agrega prioridad según sea la categoria
	 * 
	 * <p>
	 * Esta información se obtiene desde la fuente de datos MateriasPosibles
	 * </p>
	 * 
	 * @param strCategoria
	 *            Nombre de categoría según sistema estudiantil
	 * @param strSubCategoria
	 *            Nombre de subcategoria según sistema estudiantil
	 */
	public Categoria(String strCategoria, String strSubCategoria) {

		if (strCategoria.equalsIgnoreCase("FORMACION PROFESIONAL")
				&& strSubCategoria.equalsIgnoreCase("OBLIGATORIAS")) {
			this.intPrioridad = 4;
			this.strCategoria = strCategoria;
			this.strSubCategoria = strSubCategoria;
		} else if (strCategoria
				.equalsIgnoreCase("FORMACION PROFESIONAL COMPLEMENTARIA")
				&& strSubCategoria.equalsIgnoreCase("OBLIGATORIAS")) {
			this.intPrioridad = 3;
			this.strCategoria = strCategoria;
			this.strSubCategoria = strSubCategoria;
		} else if (strCategoria
				.equalsIgnoreCase("FORMACION EN CIENCIAS SOCIALES Y HUMANIS")
				&& strSubCategoria.equalsIgnoreCase("OPTATIVAS")) {
			this.intPrioridad = 2;
			this.strCategoria = strCategoria;
			this.strSubCategoria = strSubCategoria;
		} else {
			// TODO Clasificacion para PROYECTO DE TITULACION
			this.intPrioridad = 1;
			this.strCategoria = strCategoria;
			this.strSubCategoria = strSubCategoria;
		}
	}

	/**
	 * Compará dos Categorias
	 * 
	 * @param anotherCategoria
	 *            Categoria a comparar
	 * @return Retorna 1 si esta Categoria es mayor, 0 si es igual y -1 si es
	 *         menor
	 */
	public int compareTo(Categoria anotherCategoria) {
		if (this.intPrioridad > anotherCategoria.getIntPrioridad()) {
			return 1;
		} else if (this.intPrioridad == anotherCategoria.getIntPrioridad()) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * @return Nombre de la categoria
	 */
	public String getStrCategoria() {
		return strCategoria;
	}

	/**
	 * @param strCategoria
	 *            Nombre de la categoria
	 */
	public void setStrCategoria(String strCategoria) {
		this.strCategoria = strCategoria;
	}

	/**
	 * @return Nombre de la subcategoria
	 */
	public String getStrSubCategoria() {
		return strSubCategoria;
	}

	/**
	 * @param strSubCategoria
	 *            Nombre de la subcategoria
	 */
	public void setStrSubCategoria(String strSubCategoria) {
		this.strSubCategoria = strSubCategoria;
	}

	/**
	 * @return Prioridad de la categoría
	 */
	public int getIntPrioridad() {
		return intPrioridad;
	}

	/**
	 * @param intPrioridad
	 *            Prioridad de la categoría
	 */
	public void setIntPrioridad(int intPrioridad) {
		this.intPrioridad = intPrioridad;
	}
}
