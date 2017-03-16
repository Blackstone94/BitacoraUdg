package Interfaces;
/**
 * Clase que extiende de Jframe siendo esta
 * un frame especifico para que el usuario
 * especifique el nombre de la materia a 
 * agregar asi como seleccione los dias que
 * tendra la materia a lo largo de la semana.
 *
 * @author Marquez Casillas Edgar Saul
 * @version 1.0
 **/
 import Misc.SelectionHorario;
 import Objects.Materia;
 import java.util.ArrayList;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Component;
 import java.awt.GridLayout;
 import java.awt.event.MouseEvent;
 import java.awt.event.WindowEvent;
 import java.awt.event.MouseListener;
 import java.awt.event.WindowListener;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JTable;
 import javax.swing.table.JTableHeader;
 import javax.swing.JButton;
 import javax.swing.JTextField;
 import javax.swing.JOptionPane;
 import javax.swing.JScrollPane;
 import javax.swing.border.Border;
 import javax.swing.SwingConstants;
 import javax.swing.border.LineBorder;
 import javax.swing.ListSelectionModel;
 import javax.swing.table.TableColumn;
 import javax.swing.table.TableCellRenderer;
 import javax.swing.table.DefaultTableModel;
 import javax.swing.table.DefaultTableCellRenderer;
 import java.io.File;
 import java.io.BufferedWriter;
 import java.io.FileWriter;
 import java.io.IOException;
import java.io.PrintStream;

public class AddMateriaGUI extends JFrame implements WindowListener,MouseListener{
	//Atributos de la clase
	private HorarioGUI horario;
	private JLabel lbNomMateria;
	private JTextField txtNomMateria;
	private JTable table;
	private DefaultTableModel modeloTable;
	private JScrollPane panelTabla;
	private ArrayList<String> hora = new ArrayList<String>();
	private JButton aceptar,cancelar;
	private Border borde = LineBorder.createBlackLineBorder();
	private JTableHeader titulos;
	private String sesiones;
	private int numDays;
	private Materia m;
	/**
	 * Contructor de la clase AddMateriaGUI el cual inicializa
	 * el atributo de clase horario, igualando a el valor 
	 * contenido por la variable parametro parent. Ademas de 
	 * crear las instancias de los demas atributos de clase y 
	 * llamar a ejecutar la funcion initComponents()
	 * @see initComponents()
	 * @param parent variable que contiene el objeto del frame
	 *               anterior a este.
	 */
	public AddMateriaGUI(HorarioGUI parent) {
		horario = parent;
		horario.setEnabled(false);
		lbNomMateria = new JLabel("Nombre Materia:");
		txtNomMateria = new JTextField();
		modeloTable = new DefaultTableModel();
		table = new JTable(modeloTable);
		panelTabla = new JScrollPane(table);
		aceptar = new JButton("Aceptar");
		cancelar  =new JButton("Cancelar");
		initComponents();
		sesiones = "";
		numDays=0;
	}
	
	/**
	 * Metodo utilizado para crear todos los renglones de la 
	 * tabla y añadiendo a la primera columna los valos 
	 * consecutivos de un rango de 1 hora comenzando por el 
	 * rango 7:00-8:00.
	 */
	private void llenarHoras()
	{
		String aux = "",finalNumero=":00",separacion="-";
		int horaAux = 8;
		hora.add("7:00-8:00");
		hora.add("");
		hora.add("");
		hora.add("");
		hora.add("");
		hora.add("");
		hora.add("");
		modeloTable.addRow(hora.toArray());
		for (int i = 0; i<12; i++)
		{
			aux = Integer.toString(horaAux);// string  = "8"
			aux += finalNumero + separacion; // string  = "8:00-"
			horaAux++;
			aux += Integer.toString(horaAux);// string  = "8:00-9"
			aux += finalNumero ;// string  = "8:00-9:00"
			hora.set(0,aux);
			modeloTable.addRow(hora.toArray());
		}
	}
	
	/**
	 * Metodo que configura la posicion de los componentes 
	 * dentro del frame, asi como indicar al frame que no
	 * se utilizara un layout determinado. Aqui tambien se
	 * agregan los listeners de los componentes JButton y 
	 * al frame usando el MouseListener y WindowListener.
	 * @see MouseListener
	 * @see WindowListener
	 */
	private void initComponents()
	{
		lbNomMateria.setBounds(5,15,100,30);
		txtNomMateria.setBounds(90,15,298,30);
		panelTabla.setBounds(10,50,765,460);
		configTable();
		aceptar.setBounds(200,520,100,40);
		aceptar.addMouseListener(this);
		cancelar.setBounds(500,520,100,40);
		cancelar.addMouseListener(this);
		this.setBounds(300,100,785,600);
		this.setTitle("Nueva Materia");
		this.addWindowListener(this);
		this.setLayout(null);
		this.add(lbNomMateria);
		this.add(txtNomMateria);
		this.add(panelTabla);
		this.add(aceptar);
		this.add(cancelar);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Metodo que coloca un valor de cadena vacia a la variable
	 * sesiones.
	 */
	public void clearSesionsString()
	{
		this.sesiones = "";
	}
	
	
	/**
	 * Metodo que toma el valor del parametro newSesionString
	 * y se lo coloca al atributo sesiones; se llama al metodo
	 * setSesionesSemana de la clase Materia pasandole como 
	 * parametro el atributo de esta clase sesiones. Y por 
	 * ultimo se envia la instancia de la clase materia al frame
	 * padre mandando a llamar al metodo setMateriaInTable del 
	 * mismo, se habilita la ventana padre y se dispone de la
	 * actual.
	 * @see Materia
	 * @see HorarioGUI
	 * @see dispose
	 * @param newSesionString Contiene la cadena completa de sesiones
	 * 						  con el formato dd/hh/aula|...
	 */
	public void setSesionesString(String newSesionString)
	{
		this.sesiones = newSesionString;
		m.setSesionesSemana(this.sesiones);
		try 
		{
			File f = new File("materias.mate");
			PrintStream bw = new PrintStream(f);
			bw.println(m.getNombre() + "%" + newSesionString);
			bw.close();
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(this,"Exception at writing file"+ex.getMessage(),"InOut Exception.",JOptionPane.ERROR_MESSAGE);
		}
		horario.setMateriaInTable(m);
		horario.setEnabled(true);
		this.dispose();
	}
	
	/**
	 * Metodo que agrega las columnas al modelo de la tabla,
	 * manda a ejecutar el metodo llenarHoras y le da  un
	 * formato especifico a la tabla ademas de asignar el
	 * MouseListener a la tabla.
	 * @see llenarHoras
	 * @see MouseListener
	 */
	private void configTable()
	{
		titulos = table.getTableHeader();
		Font fuente = new Font("Verdana", Font.ITALIC, 12); 
		titulos.setFont(fuente);
		modeloTable.addColumn("Horas");
		modeloTable.addColumn("Lunes");
		modeloTable.addColumn("Martes");
		modeloTable.addColumn("Miercoles");
		modeloTable.addColumn("Jueves");
		modeloTable.addColumn("Viernes");
		modeloTable.addColumn("Sabado");
		llenarHoras();
		table.setRowHeight(33);
		table.setCellSelectionEnabled(true);
		table.setDefaultRenderer(Object.class,new SelectionHorario(table));
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(this);
	}
	
	/**
	 * Crea una cadena con las celdas seleccionadas por el 
	 * usuario teniendo el siguiente formato por cada 
	 * seleccion: dd/HH| ; el dia se toma por la cabecera
	 * de la tabla y la hora por el renglon en el que se 
	 * encuentra la seleccion en la primera columna. Por
	 * ultimo se almacena la cadena creada en el atributo
	 * sesion
	 */
	private void createSesionString()
	{
		String datoCelda = "";
		String dia = "", horas = "";
		for (int i = 0; i<table.getRowCount()-1; i++)
		{
			for (int j = 1; j<table.getColumnCount()-1; j++)
			{
				datoCelda = (String)table.getModel().getValueAt(i,j);
				if(datoCelda.compareTo("S")!=0)
				{
					continue;
				}
				else if(datoCelda.compareTo("S")==0)
				{
					dia = (String)table.getTableHeader().getColumnModel().getColumn(j).getHeaderValue();
					horas = (String)table.getModel().getValueAt(i,0);
					sesiones += dia + "/" + horas + "|";
				}
			}
		}
	}
	
	/**
	 * Metodo sobreescrito de la clase WindowListener
	 * que se ejecuta al generarse el evento de cierre
	 * de una ventana, en este caso se utiliza para
	 * habilitar la ventana padre y disponer de la 
	 * ventana actual.
	 * @see WindowClosing
	 * @see WindowListener
	 * @see dispose
	 */
	@Override
	public void windowClosing(WindowEvent e)
	{
		horario.setEnabled(true);
		this.dispose();
	}
	
	/**
	 * Metodos sobre escritos de la clase WindowListener
	 * Estos metodos son obligatorios ya que esta clase 
	 * no es una clase abstracta para ente caso no son
	 * utilizados.
	 * @see WindowListener
	 */
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
	 * Metodos sobre escritos de la clase MouseListener
	 * Estos metodos no son utlizados en este caso pero
	 * se tienen que añadir debido a que la clase no es
	 * abstracta y su sobre escritura es de caracter 
	 * obligatorio.
	 **/
	public void mousePressed(MouseEvent e)
    {
    	
    }
    public void mouseReleased(MouseEvent e)
    {
    	
    }
    public void mouseEntered(MouseEvent e)
    {
    	
    }
    public void mouseExited(MouseEvent e)
    {
    	
    }
    
    /**
     * Metodo sobre escrito de la clase MouseListener
     * Que ocurre cuando uno de los objetos a los que 
     * se les agrego el listener presenta el evento 
     * especifico de mouseClicked. 
     * En este se distinguen 3 casos ya que se agrego
     * listener en 3 componentes de la clase.
     *
     * El primero es el table en el cual se le asigna 
     * el valor de tipo String "S" a la celda vacia
     * que el usuario selecciono, si esta ya tiene 
     * el valor "S" se borra dicho valor y se deja la
     * celda en blanco, en el caso de que el valor sea 
     * diferente de "S" o cadena vacia no se hara nada.
     *
     * El segundo caso es el de el JButton cancelar, en
     * el cual actua como un windowClosing.
     *
     * El tercero es el de el JButton aceptar, en el cual
     * se valida que los campos txtNomMateria tenga algun 
     * valor y que se hubiese seleccionado al menos una 
     * celda del JTable; si se cumplen estas validaciones
     * se crea la instancia de tipo Materia, se manda a 
     * ejecutar crearSesionString, se crea un frame de tipo
     * SalonesGUI y se esconde la ventana actual con la 
     * funcion hide heredada de la clase JFrame; caso 
     * contrario se despliegan respectivos mensajes por el 
     * error en instancias de la clase JOptionPane con el
     * metodo showMessageDialog
     *
     * @see MouseListener
     * @see mouseClicked
     * @see windowClosing
     * @see Materia
     * @see crearSesionString
     * @see SalonesGUI
     * @see hide
     * @see JFrame
     * @see JOptionPane
     * @see showMessageDialog
     */
    public void mouseClicked(MouseEvent me)
    {
    	if(me.getSource() == table)
    	{
    		String datoCelda = (String)table.getModel().getValueAt(table.getSelectionModel().getLeadSelectionIndex(),table.getColumnModel().getSelectionModel().getLeadSelectionIndex());
    		if(datoCelda == null || datoCelda == "")
    		{
    			table.setValueAt("S",table.getSelectionModel().getLeadSelectionIndex(),table.getColumnModel().getSelectionModel().getLeadSelectionIndex());
    			numDays++;
    		}
    		else if(datoCelda.compareTo("S")==0)
			{
				table.setValueAt("",table.getSelectionModel().getLeadSelectionIndex(),table.getColumnModel().getSelectionModel().getLeadSelectionIndex());
				numDays--;
			}
    	}
    	if(me.getSource() == cancelar)
    	{
    		horario.setEnabled(true);
			this.dispose();
    	}
    	if(me.getSource() == aceptar)
    	{
    		if(txtNomMateria.getText().compareTo("") != 0)
    		{
    			if(numDays>=1)
    			{
    				String nom = this.txtNomMateria.getText();
					m = new Materia(nom, this.numDays);
					this.createSesionString();
					new SalonesGUI(this,this.numDays,this.sesiones);
					this.hide();
    			}
    			else
    				JOptionPane.showMessageDialog(this,"Elija por lo menos un dia para la materia.","No se eligio nada",JOptionPane.WARNING_MESSAGE);
	    	}
	    	else
	    		JOptionPane.showMessageDialog(this,"Agregue un nombre a la materia por favor.","No todos los campos estan llenos",JOptionPane.WARNING_MESSAGE);
    	}
    }
    
}
