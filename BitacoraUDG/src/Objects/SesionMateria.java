package Objects;
/**
 * Clase que representa una sesion de una Materia
 * la cual contiene los datos mas importantes de
 * la sesion.
 *
 * @author Marquez Casillas Edgar Saul
 * @version 1.0
 */

public class SesionMateria {
	//Atributos
	private String d;
	private String hi;
	private String hf;
	private String salon;
	/**
	 * Constructor de la clase sesionMateria
	 * que inicializa los atributos con las 
	 * variables obtenidas como parametro.
	 *
	 * @param dia Dia que es impartida la materia
	 * @param horaIni hora a la que inicia la sesion
	 * @param horaFin hora a la que termina la sesion
	 * @param aula Salon en la que se imparte la materia
	 */
	public SesionMateria(String dia, String horaIni,String horaFin,String aula) {
		this.d = dia;
		this.hi = horaIni;
		this.hf = horaFin;
		this.salon = aula;
	}
	
	/**
	 * Metodo que retorna el Dia de esta sesion
	 * @return String Atributo d de la clase
	 */
	public String getDia()
	{
		return this.d;
	}
	
	/**
	 * Metodo que retorna la hora de inicio de esta sesion
	 * @return String Atributo hi de la clase
	 */
	public String getHoraInicial()
	{
		return this.hi;
	}
	
	/**
	 * Metodo que retorna la hora de fin de esta sesion
	 * @return String Atributo hf de la clase
	 */
	public String getHoraFinal()
	{
		return this.hf;
	}
	
	/**
	 * Metodo que retorna el Salon de esta sesion
	 * @return String Atributo salon de la clase
	 */
	public String getSalon()
	{
		return this.salon;
	}
	
	/**
	 * Metodo que Asigna el valor obtenido como parametro
	 * al atributo "d" de la clase.
	 * @param day Nuevo dia que se imparte la materia
	 */
	public void setDia(String day)
	{
		this.d = day;
	}
	
	/**
	 * Metodo que Asigna el valor obtenido como parametro
	 * al atributo "hi" de la clase.
	 * @param hIni Nueva hora Inicial a la que se imparte la materia
	 */
	public void setHoraInicial(String hIni)
	{
		this.hi = hIni;
	}
	
	/**
	 * Metodo que Asigna el valor obtenido como parametro
	 * al atributo "hf" de la clase.
	 * @param hFin Nueva hora de finalizacion de la sesion de la materia
	 */
	public void setHoraFinal(String hFin)
	{
		this.hf = hFin;
	}
	
	/**
	 * Metodo que Asigna el valor obtenido como parametro
	 * al atributo "salon" de la clase.
	 * @param aula Nuevo aula en la que se imparte la materia
	 */
	public void setSalon(String aula)
	{
		this.salon = aula;
	}
	
}
