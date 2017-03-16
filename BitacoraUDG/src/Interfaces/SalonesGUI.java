package Interfaces;
/**
 * Clase que extiende de Jframe siendo esta
 * un frame especifico para que el usuario
 * especifique el salon que tendra por cada
 * una de las sesiones elegidas en la anterior
 * interfaz. Esta clase implementa las 
 * interfaces windowListener y ActionListener
 * por lo cual sobre escribe los metodos de
 * dichas interfaces.
 * @see AddMateriaGUI
 * @see WindowListener
 * @see ActionListener
 *
 * @author Marquez Casillas Edgar Saul
 * @version 1.0
 **/
 import javax.swing.JFrame;
 import javax.swing.JTable;
 import javax.swing.JLabel;
 import javax.swing.JButton;
 import javax.swing.JCheckBox;
 import javax.swing.JScrollPane;
 import javax.swing.JOptionPane;
 import javax.swing.table.DefaultTableModel;
 import java.awt.event.WindowEvent;
 import java.awt.event.WindowListener;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.util.ArrayList;
 import java.util.StringTokenizer;

public class SalonesGUI extends JFrame implements WindowListener,ActionListener{
	// atributos
	private DefaultTableModel dtm;
	private JTable table;
	private JScrollPane jsp;
	private JCheckBox cbMS;
	private JLabel lbInstruciones;
	private JButton aceptar;
	private AddMateriaGUI padre;
	private int numSesiones;
	private String sesiones;
	/**
	 * Constructor de la clase SalonesGUI
	 * que inicializa los atributos con las 
	 * variables obtenidas como parametro y
	 * manda a ejecutar el metodo initComponents
	 * @see initComponents
	 * @param amGUI JFrame padre de este
	 * @param numOfSesions numero de sesiones de la materia
	 * @param cadSesiones cadena que contiene las horas 
	 * 							 y dias de las sesiones
	 */
	public SalonesGUI(AddMateriaGUI amGui,int numOfSesions,String cadSesiones) {
		padre = amGui;
		this.numSesiones = numOfSesions;
		this.sesiones = cadSesiones;
		initComponents();
	}
	
	/**
	 * Metodo que crea las instancias de los atributos
	 * de la clase, manda a ejecutar el metodo configTable,
	 * configura los componentes creados y configura la
	 * ventana, Asi como tambien le agrega los listeners
	 * a los componentes.
	 * @see configTable
	 */
	private void initComponents()
	{
		//Creacion de instancias
		lbInstruciones = new JLabel("Introdusca el aula asignada a la materia para las siguientes horas:");
		dtm = new DefaultTableModel();
		table = new JTable(dtm);
		jsp = new JScrollPane(table);
		cbMS = new JCheckBox("Mismo salon para todas las horas?");
		aceptar = new JButton("Aceptar");
		
		//configuracion de instancias
		this.configTable();
		lbInstruciones.setBounds(5,5,400,30);
		jsp.setBounds(5,40,460,400);
		cbMS.setBounds(5,455,200,30);
		cbMS.addActionListener(this);
		aceptar.setBounds(280,455,150,40);
		aceptar.addActionListener(this);
		//configuracion de ventana
		this.addWindowListener(this);
		this.setLayout(null);
		this.setTitle("Asignar aulas");
		this.setBounds(300,100,485,550);
		this.add(lbInstruciones);
		this.add(jsp);
		this.add(cbMS);
		this.add(aceptar);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Metodo que configura la tabla, agregandole
	 * las columnas y colocando los renglones con
	 * los datos contenidos en la cadena sesiones
	 */
	private void configTable()
	{
		dtm.addColumn("Dia");
		dtm.addColumn("Horas");
		dtm.addColumn("Salon");
		ArrayList<String> row = new ArrayList<String>();
		StringTokenizer data = new StringTokenizer(sesiones,"|");
		String aux="";
		while(data.hasMoreTokens())
		{
			
			aux = data.nextToken();
			String sd[] = aux.split("/");
			row.add(sd[0]);
			row.add(sd[1]);
			row.add("");
			dtm.addRow(row.toArray());
			row.clear();
		}
	}
	
	/**
	 * Metodo que verifica si todas las celdas de
	 * la tabla tienen informacion.
	 * @return boolean true si ya tienen informacion, caso contrario false
	 */
	private boolean checkAllDataComplete()
	{
		for (int i = 0; i<table.getRowCount(); i++)
			if( ((String)table.getValueAt(i,2)).compareTo("") == 0 )
				return false;
		return true;
	}
	/**
	 * Metodos sobre escritos de la clase WindowListener
	 * Estos metodos son obligatorios ya que esta clase 
	 * no es una clase abstracta para ente caso no son
	 * utilizados. EL metodo windowClosing se utilizo 
	 * para dar un aviso al usuario y para regresar la
	 * ejecucion a la ventana padre.
	 * @see WindowListener
	 */
	public void windowClosing(WindowEvent e)
	{
		JOptionPane.showMessageDialog(this,"No se introdujeron aulas para las sesiones de la materia","Error: null data",JOptionPane.WARNING_MESSAGE);
		this.padre.clearSesionsString();
		this.padre.show();
		this.dispose();
	}
	public void windowActivated(WindowEvent e)
	{
		
	}
	public void	windowClosed(WindowEvent e)
	{
		
	}
	public void windowDeactivated(WindowEvent e)
	{
		
	}
	public void windowDeiconified(WindowEvent e)
	{
		
	}
	public void windowIconified(WindowEvent e)
	{
		
	}
	public void windowOpened(WindowEvent e)
	{
		
	}
	
	/**
	 * Metodo sobreescrito de la clase ActionListener
	 * en el cual se distinguen 2 casos.
	 * 
	 * El primer caso es en el que el usuario seleccione
	 * el checkBox, si esto ocurre se toma el valor de la
	 * tercera columna-primer renglon y se llenan las celdas
	 * de la tercera columna de los renglones restantes.
	 *
	 * El segundo caso es por el boton aceptar con lo 
	 * cual se crea una cadena temporal con los datos
	 * de todas las celdas, se manda a ejecutar el 
	 * metodo setSeleccionesString del padre de esta 
	 * clase y se dispone de esta
	 * @see AddMateriaGUI
	 */
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == cbMS)
		{
			if(cbMS.isSelected())
			{
				String value = "";
				if(numSesiones >1)
				{
					value = (String)table.getValueAt(0,2);
					for (int i = 1; i<numSesiones; i++)
					{
						table.setValueAt(value,i,2);
					}
					this.update(this.getGraphics());
				}
			}
		}
		
		if(ae.getSource() == aceptar)
		{
			if(this.checkAllDataComplete())
			{
				String dataSesion = "";
				for (int i = 0; i<table.getRowCount(); i++) 
				{
					dataSesion += (String)table.getValueAt(i,0) + "/" + (String)table.getValueAt(i,1) + "/" + (String)table.getValueAt(i,2) + "|";
				}
				this.padre.setSesionesString(dataSesion);
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Por favor llene todas las celdas antes de continuar.","Not all cell has data",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	
}

