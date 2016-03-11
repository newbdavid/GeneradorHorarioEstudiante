package com.generador.modelo;

/**
 * Clase que representa las materias
 * <p>
 * La información de cada materia es almancenada en esta clase
 * </p>
 * 
 * @author Luis Reinoso
 */

public class Materia {
	private String strCodigo;
	private String strNombre;
	private String strParalelo;
	private String strAula;
	private Horario horario;
	private int intCreditos;
	private int intNumMatricula;
	private Categoria categoria;
	private int intPrioridad = 1;
	private final int ATRIBUTOS = 9;

	/**
	 * Constructor de la clase Materia diseñado para el archivo HorariosMaterias
	 * <p>
	 * Se agrega la información de una Materia del archivo HorariosMaterias
	 * </p>
	 * 
	 * @param codigo
	 *            Representa el código de la materia
	 * @param nombre
	 *            Representa el nombre de la materia
	 * @param paralelo
	 *            Representa el paralelo de la materia
	 * @param aula
	 *            Representa el aula de la materia
	 * @param lunes
	 *            Representa el horario de la materia del día lunes
	 * @param martes
	 *            Representa el horario de la materia del día martes
	 * @param miercoles
	 *            Representa el horario de la materia del día miercoles
	 * @param jueves
	 *            Representa el horario de la materia del día jueves
	 * @param viernes
	 *            Representa el horario de la materia del día viernes
	 * @param sabado
	 *            Representa el horario de la materia del día sabado
	 */
	public Materia(String codigo, String nombre, String paralelo, String aula,
			String lunes, String martes, String miercoles, String jueves,
			String viernes, String sabado) {
		this.strCodigo = codigo;
		this.strNombre = nombre;
		this.strParalelo = paralelo;
		this.strAula = aula;
		this.horario = new Horario(lunes, martes, miercoles, jueves, viernes,
				sabado);
	}

	/**
	 * Constructor de la clase Materia diseñado para llenado de todos los
	 * parámetros de la clase
	 * <p>
	 * Se agrega la información de una Materia llenando todos la información de
	 * la misma
	 * </p>
	 * 
	 * @deprecated Este método no tiene una fuente directa de información
	 * @param codigo
	 *            Representa el código de la materia
	 * @param nombre
	 *            Representa el nombre de la materia
	 * @param paralelo
	 *            Representa el paralelo de la materia
	 * @param aula
	 *            Representa el aula de la materia
	 * @param lunes
	 *            Representa el horario de la materia del día lunes
	 * @param martes
	 *            Representa el horario de la materia del día martes
	 * @param miercoles
	 *            Representa el horario de la materia del día miercoles
	 * @param jueves
	 *            Representa el horario de la materia del día jueves
	 * @param viernes
	 *            Representa el horario de la materia del día viernes
	 * @param sabado
	 *            Representa el horario de la materia del día sabado
	 * @param creditos
	 *            Representa el número de créditos que ocupa una Materia
	 * @param numMatricula
	 *            Representa el número de veces matriculado en esta Materia
	 * @param categoria
	 *            Representa la categoría según el sistema estudiantil
	 * @param subCategoria
	 *            Representa la subcategoria según el sistema estudiantil
	 */
	public Materia(String codigo, String nombre, String paralelo, String aula,
			String lunes, String martes, String miercoles, String jueves,
			String viernes, String sabado, String creditos,
			String numMatricula, String categoria, String subCategoria) {
		this.strCodigo = codigo;
		this.strNombre = nombre;
		this.strParalelo = paralelo;
		this.strAula = aula;
		this.horario = new Horario(lunes, martes, miercoles, jueves, viernes,
				sabado);
		this.intCreditos = Integer.parseInt(creditos);
		this.intNumMatricula = Integer.parseInt(numMatricula);
		this.categoria = new Categoria(categoria, subCategoria);
	}

	/**
	 * Constructor de la clase Materia diseñado para el archivo MateriasPosibles
	 * <p>
	 * Se agrega la información de una Materia del archivo HorariosMaterias
	 * </p>
	 * 
	 * @param codigo
	 *            Representa el código de la materia
	 * @param nombre
	 *            Representa el nombre de la materia
	 * @param creditos
	 *            Representa el número de créditos que ocupa una Materia
	 * @param numMatricula
	 *            Representa el número de veces matriculado en esta Materia
	 * @param categoria
	 *            Representa la categoría según el sistema estudiantil
	 * @param subCategoria
	 *            Representa la subcategoria según el sistema estudiantil
	 */
	public Materia(String codigo, String nombre, String creditos,
			String numMatricula, String categoria, String subCategoria) {
		this.strCodigo = codigo;
		this.strNombre = nombre;
		this.intCreditos = Integer.parseInt(creditos.substring(0, 1));
		this.intNumMatricula = Integer.parseInt(numMatricula);
		this.categoria = new Categoria(categoria, subCategoria);

		/**
		 * Se establece la prioridad según los siguientes criterios Por
		 * categoria Por número de matrícula Por número de créditos
		 */
		this.intPrioridad += this.categoria.getIntPrioridad()
				+ this.intNumMatricula + this.intCreditos;
	}

	/**
	 * @return El código de la materia
	 */
	public String getStrCodigo() {
		return strCodigo;
	}

	/**
	 * @param strCodigo
	 *            El código de la materia
	 */
	public void setStrCodigo(String strCodigo) {
		this.strCodigo = strCodigo;
	}

	/**
	 * @return Nombre de la materia
	 */
	public String getStrNombre() {
		return strNombre;
	}

	/**
	 * @param strNombre
	 *            Nombre de la materia
	 */
	public void setStrNombre(String strNombre) {
		this.strNombre = strNombre;
	}

	/**
	 * @return Paralelo de la materia
	 */
	public String getStrParalelo() {
		return strParalelo;
	}

	/**
	 * @param strParalelo
	 *            Paralelo de la materia
	 */
	public void setStrParalelo(String strParalelo) {
		this.strParalelo = strParalelo;
	}

	/**
	 * @return El aula de la materia
	 */
	public String getStrAula() {
		return strAula;
	}

	/**
	 * @param strAula
	 *            El aula de la materia
	 */
	public void setStrAula(String strAula) {
		this.strAula = strAula;
	}

	/**
	 * @return El Horario de la materia
	 */
	public Horario getHorario() {
		return horario;
	}

	/**
	 * @param horario
	 *            El horario de la materia
	 */
	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	/**
	 * @return Número de créditos de la materia
	 */
	public int getIntCreditos() {
		return intCreditos;
	}

	/**
	 * @param intCreditos
	 *            Número de créditos de la materia
	 */
	public void setIntCreditos(int intCreditos) {
		this.intCreditos = intCreditos;
	}

	/**
	 * @return Número de matrícula de la materia
	 */
	public int getIntNumMatricula() {
		return intNumMatricula;
	}

	/**
	 * @param intNumMatricula
	 *            Número de matrícula de la materia
	 */
	public void setIntNumMatricula(int intNumMatricula) {
		this.intNumMatricula = intNumMatricula;
	}

	/**
	 * @return Categoria según sistema estudiantil
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            Categoria según sistema estudiantil
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Prioridad de la materia
	 */
	public int getIntPrioridad() {
		return intPrioridad;
	}

	/**
	 * @param intPrioridad
	 *            Prioridad de la materia
	 */
	public void setIntPrioridad(int intPrioridad) {
		this.intPrioridad = intPrioridad;
	}

	/**
	 * Método para comparar materias según prioridad
	 * 
	 * @param anotherMateria
	 * @return Retorna 1 si la materia es mayor, 0 si es igual y -1 si es menor
	 */
	public int compareTo(Materia anotherMateria) {

		if (this.intPrioridad > anotherMateria.getIntPrioridad()) {
			return 1;
		} else if (this.intPrioridad == anotherMateria.getIntPrioridad()) {
			return 0;
		} else {
			return -1;
		}
	}

	public int size() {
		return this.ATRIBUTOS;
	}

	public Object[] getInfoMateria() {
		Object[] infoMateria = { getStrCodigo(), getStrNombre(),
				getStrParalelo(), getStrAula(), getHorario(), getIntCreditos(),
				getIntNumMatricula(), getCategoria().getStrCategoria(),
				getIntPrioridad() };
		return infoMateria;
	}
}