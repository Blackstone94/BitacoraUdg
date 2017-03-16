package Interfaces;
/**
 * Clase que extiende de DefaultFRame siendo esta
 * un frame especifico para que el usuario pueda
 * ver las materias que tiene a lo largo de la 
 * semana, asi como el salon donde seran impartidas,
 * ademas el usuario puede agregar una nueva materia
 * (siempre y cuando aun tenga espacio), modificar o
 * eliminar una ya existente. 
 * Siendo este un frame "principal" el cual contiene 
 * los elementos de navegacion entre los demas frames
 * "prinicpales", este tiene consigo un menu para que
 * el usuario pueda moverse entre dichos frames.
 * navegacion
 *
 * @see DefaultFrame
 *
 * @author Marquez Casillas Edgar Saul
 * @version 1.0
 **/
 import Misc.roundButton;
 import Objects.SesionMateria;
 import Objects.Materia;
 import java.awt.Color;
 import javax.swing.JTable;
 import javax.swing.JButton;
 import java.util.ArrayList;
 import javax.swing.JScrollPane;
 import java.awt.event.MouseEvent;
 import javax.swing.SwingConstants;
 import java.awt.event.MouseListener;
 import javax.swing.ListSelectionModel;
 import javax.swing.table.DefaultTableModel;
 import javax.swing.table.DefaultTableModel;
 import javax.swing.table.DefaultTableCellRenderer;
 import java.io.File;
 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.util.StringTokenizer;
 import java.io.IOException;
 import javax.swing.JOptionPane;
 
public class HorarioGUI extends DefaultFrame implements MouseListener{
	//Atributos
	private InicioGUI inicio;
	private NotasGUI notas;
	private EventosGUI eventos;
	private JTable horarioTable;
	private DefaultTableModel modeloTable;
	private JScrollPane panelTabla;
	private roundButton nuevaMateria;
	private ArrayList<String> hora = new ArrayList<String>();
	private ArrayList<Materia> materias = new ArrayList<Materia>();
	private DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer titleColorAlign = new DefaultTableCellRenderer();
	/**
	 * Constructor de la clase HorarioGUI
	 * el cual solo llama a la funcion initComponents
	 * @see initComponents
	 */
	public HorarioGUI() 
	{
		initComponents();
	}
	
	/**
	 * Metodo que crea todos los componentes declados 
	 * en la clase y se inicializan las variables, 
	 * ademas de mandar a ejecutar el metodo configComponents.
	 * @see configComponents
	 **/
	private void initComponents()
	{
		modeloTable = new DefaultTableModel();
		horarioTable = new JTable(modeloTable);
		panelTabla = new JScrollPane(horarioTable);
		nuevaMateria = new roundButton("<html><h1>+</h1></html>");
		configComponents();
		cargaMateriasFromFile();
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
	 * Metodo en el que se configuran las propiedades de los 
	 * componentes de la ventana, asi como configurar la 
	 * misma ventana.
	 **/
	private void configComponents()
	{
		//Se agregan los listeners de la navegacion
		this.menuNotas.addMouseListener(this);
		this.menuAjustes.addMouseListener(this);
		this.menueventos.addMouseListener(this);
		this.menuInicio.addMouseListener(this);
		configTable();
		//Se acomodan los componentes en el JFrame y se agregan al mismo
		panelTabla.setBounds(10,10,760,450);
		nuevaMateria.setBounds(this.getWidth()-100,this.getHeight()-180,50,50);
		nuevaMateria.setFocusPainted(false);
		nuevaMateria.addMouseListener(this);
		this.setTitle("Horario");
		this.setDisableHorario();
    	this.add(panelTabla);
    	this.add(nuevaMateria);
    	this.update(this.getGraphics());
	}
	
	/**
	 * Metodo especifico para darle un formato al Jtable
	 * de esta clase. Agregando columnas, mandando a 
	 * ejecutar llenarHoras y agregando el listener de
	 * tipo MouseListener a la tabla.
	 * @see Jtable
	 * @see llenarHoras
	 * @see MouseListener
	 */
	private void configTable()
	{
		centrar.setHorizontalAlignment(SwingConstants.CENTER);
		titleColorAlign.setHorizontalAlignment(SwingConstants.CENTER);
		titleColorAlign.setBackground(Color.decode("0xc6ecff"));
		modeloTable.addColumn("Horas");
		modeloTable.addColumn("Lunes");
		modeloTable.addColumn("Martes");
		modeloTable.addColumn("Miercoles");
		modeloTable.addColumn("Jueves");
		modeloTable.addColumn("Viernes");
		modeloTable.addColumn("Sabado");
		llenarHoras();
		horarioTable.setRowHeight(33);
		horarioTable.setCellSelectionEnabled(true);
		horarioTable.getTableHeader().setDefaultRenderer(titleColorAlign);
		horarioTable.getColumnModel().getColumn(0).setCellRenderer(titleColorAlign);
		horarioTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		for (int i = 1; i<horarioTable.getColumnCount(); i++)
		{
			horarioTable.getColumnModel().getColumn(i).setCellRenderer(centrar);
		}
		horarioTable.addMouseListener(this);
	}
	
	/**
	 * Metodo que coloca en las celdas de la tabla
	 * el nombre y el aula de la materia pasada 
	 * como parametro, asi como agregar esta misma
	 * a un arreglo de materias.
	 * @see Materia
	 * @param nMateria instancia de tipo Materia que
	 * 				   contiene los datos de una sola
	 * 				   materia.
	 */
	public void setMateriaInTable(Materia nMateria)
	{
		String cadCelda = "", aux ="";
		int row = 0,colum = 0;
		cadCelda += "<html>" + nMateria.getNombre()+"<br>";
		ArrayList<SesionMateria> sm = new ArrayList<SesionMateria>();
		sm = nMateria.getSesionesSemana();
		for (int i = 0; i<sm.size(); i++)
		{
			cadCelda += sm.get(i).getSalon() + "</html>";
			aux = sm.get(i).getHoraInicial() + "-" + sm.get(i).getHoraFinal();
			//obtener el indice del row
			for (int j = 0; j<horarioTable.getRowCount()-1; j++)
				if( ((String)horarioTable.getValueAt(j,0)).compareTo( aux ) == 0 )
				{
					row = j;
					break;
				}
			//obtener el indice de la columna
			for (int j = 0; j<horarioTable.getColumnCount()-1; j++)
			{
				aux = (String)horarioTable.getTableHeader().getColumnModel().getColumn(j).getHeaderValue();
				if(aux.compareTo(sm.get(i).getDia()) == 0)
				{
					colum = j;
					break;
				}
			}
			horarioTable.setValueAt(cadCelda,row,colum);
		}
		materias.add(nMateria);
		this.update(this.getGraphics());
	}
	
	/**
	 * Metodo que carga las materias almacenadas en
	 * el archivo materias.mate
	 */
	public void cargaMateriasFromFile()
	{
		try {
			File f = new File("materias.mate");
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String line="",dataCelda="<html>",div2="";
			while((line = br.readLine()) != null)
			{
				String[] div1 = line.split("%");//split name from session
				int row=0,colum=0;
				dataCelda += "<strong>" + div1[0] + "</strong><br>";//set name plus line jump
				StringTokenizer sesiones = new StringTokenizer(div1[1],"|");//get sesiones for materia
				while(sesiones.hasMoreTokens())
				{
					div2 = sesiones.nextToken();
					System.out.println(sesiones.countTokens());
					if(div2.equals("") || div2.equals(" "))
						continue;
					String[] sessionData = div2.split("/");
					dataCelda += sessionData[2]+"</html>";
					String nomDia = "";
					for (int j = 0; j<horarioTable.getRowCount()-1; j++)
						if( ((String)horarioTable.getValueAt(j,0)).compareTo( sessionData[1] ) == 0 )
						{
							row = j;
							break;
						}
					for (int j = 0; j<horarioTable.getColumnCount()-1; j++)
					{
						nomDia = (String)horarioTable.getTableHeader().getColumnModel().getColumn(j).getHeaderValue();
						if(nomDia.compareTo(sessionData[0]) == 0)
						{
							colum = j;
							break;
						}
					}
					horarioTable.setValueAt(dataCelda,row,colum);
					dataCelda = "<html><strong>" + div1[0] + "</strong><br>";
				}//End for sesiones
			}//End while file read
			this.update(this.getGraphics());
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(this,"Exception at open file\n"+ex.getMessage(),"IO Exception",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Metodos sobre escritos de la clase MouseListener
	 * Estos metodos no son utlizados en este caso pero
	 * se tienen que añadir debido a que la clase no es
	 * abstracta y su sobre escritura es de caracter 
	 * obligatorio.
	 * @see MouseListener
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
     * Metodo sobre escrito de la clase MouseListener.
     * En este se realiza la navegacion entre frames
     * principales. Tambien se tienen los casos en los
     * que el usuario desee agregar una nueva materia
     * ya sea presoinando el boton nuevaMateria o el 
     * Jtable, creando asi un objeto de tipo AddMateriaGUI.
     * @see AddMAteriaGUI
     */
    public void mouseClicked(MouseEvent me)
    {
    	if(me.getSource() == this.menuInicio)
    	{
    		inicio = new InicioGUI();
    		this.dispose();
    	}
    	if(me.getSource() == this.menueventos)
    	{
    		eventos = new EventosGUI();
    		this.dispose();
    	}
    	if(me.getSource() == this.menuAjustes)
    		System.out.println ("Presiono ajustes");
    	if(me.getSource() == this.menuNotas)
    	{
    		notas = new NotasGUI();
    		this.dispose();
    	}
    	if(me.getSource() == nuevaMateria)
    	{
    		new AddMateriaGUI(this);
    	}
    	if(me.getSource() == horarioTable)
    	{
    		String datoCelda = (String)horarioTable.getModel().getValueAt(horarioTable.getSelectionModel().getLeadSelectionIndex(),horarioTable.getColumnModel().getSelectionModel().getLeadSelectionIndex());
    		if(datoCelda == null || datoCelda == "")
    		{
    			new AddMateriaGUI(this);
    		}
    		else//Implementar metodo modificar materia
    		{
    		}
    	}
    }
    
}
