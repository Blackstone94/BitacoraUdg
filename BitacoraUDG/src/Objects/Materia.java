package Objects;

/**
 * Clase que representa una materia del usuario
 * la cual contiene los datos mas importantes de
 * la materia.
 *
 * @author Marquez Casillas Edgar Saul
 * @version 1.0
 */
 
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Materia {
	private String nombre;
	private int numDias;
	private ArrayList<SesionMateria> sesionesSemana;
	
	/**
	 * Metodo constructor Materia
	 *
	 * @param name Nombre de la materia
	 * @param numDays Numero de dias que tiene la materia
	 */
	public Materia(String name, int numDays) {
		this.nombre = name;
		this.numDias = numDays;
		sesionesSemana = new ArrayList<SesionMateria>();
	}
	
	/**
	 * Method setSesionesSemana
	 *
	 * Metodo que con la cadena pasada como parametro
	 * la desglosa y crea un objeto auxiliar de tipo
	 * SesionMateria y despues agrega dicho objeto al
	 * ArrayList de esta misma clase.
	 *
	 * @param data  Son los dias de la semana 
	 * escogidos para la clase estos tienen el siguiente
	 * formato: diaSem/hI-hF/salon|diaSem/hI-hF/salon|...
	 **/
	public void setSesionesSemana(String data)
	{
		data = data.substring(0,data.length()-2);
		SesionMateria sm;
		String dia[] = new String[3];
		String hora[] = new String[2];
		String aux = "";
		StringTokenizer dataDivided = new StringTokenizer(data,"|"); 
		while(dataDivided.hasMoreTokens())
		{
			aux = dataDivided.nextToken();
			dia = aux.split("/");
			hora = dia[1].split("-");
			sm = new SesionMateria(dia[0],hora[0],hora[1],dia[2]);
			sesionesSemana.add(sm);
		}
	}
	
	/**
	 * Metodo que devuelve el arreglo de instancias
	 * de objetos de tipo SesionMateria para poder
	 * obtener los dias y horas de la materia
	 * @return ArrayList retorna el arreglo de objetos
	 *                   de tipo SesionMateria.
	 */
	public ArrayList getSesionesSemana()
	{
		return this.sesionesSemana;
	}
	
	/**
	 * Metodo que retorna una cadena que
	 * contiene el nombre de la materia
	 * almacenado en el atributo nombre.
	 * @return String atributo "nombre" de la clase
	 */
	public String getNombre()
	{
		return this.nombre;
	}

}
