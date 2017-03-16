 package Interfaces;
 /**
 * Clase que extiende de DefaultFRame siendo esta
 * el frame inicial que el usuario ve al ejecutar
 * el programa. Este frame solo sirve para ver los
 * eventos proximos a caducar y de acceso a los 
 * demas frames del programa.
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
 import java.awt.Font;
 import java.awt.Color;
 import java.util.ArrayList;
 import javax.swing.JLabel;
 import javax.swing.JTable;
 import javax.swing.JScrollPane;
 import javax.swing.border.Border;
 import javax.swing.SwingConstants;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import javax.swing.border.LineBorder;
 import javax.swing.border.TitledBorder;
 import javax.swing.BorderFactory;
 import javax.swing.table.DefaultTableModel;
 import javax.swing.table.DefaultTableCellRenderer;
 
public class InicioGUI extends DefaultFrame implements MouseListener{
	//Atributos
	private HorarioGUI horario;
	private NotasGUI notas;
	private EventosGUI eventos;
	private JScrollPane jspTable;
	private JTable events;
	private JLabel lbLogo,tituloColum1,tituloColum2,tituloColum3,tituloColum4;
	private Border borde = LineBorder.createBlackLineBorder();
	private TitledBorder tituloTabla = BorderFactory.createTitledBorder("Eventos");
	private DefaultTableModel modelo;
	private DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer titleColorAlign = new DefaultTableCellRenderer();
	ArrayList<String> aux = new ArrayList<String>();//Eliminar despues por inutilidad
	
	/**
	 * Constructor de la clase InicioGUI el cual solo 
	 * manda a ejecutar el metodo initComponents.
	 */
    public InicioGUI() {
    	initComponents();
    }
    
    /**
     * Metodo que crea todos los componentes declarados 
     * en la clase y manda a ejecutar configComponents.
	 **/
    private void initComponents()
    {
		lbLogo = new JLabel("LOGO",JLabel.CENTER);
		tituloColum1 = new JLabel("Titulo",JLabel.CENTER);
		modelo = new DefaultTableModel();
		events = new JTable(modelo);
		jspTable = new JScrollPane(events);
		configComponents();
    }
    
    /**
     * Metodo que configura las propiedades de los componentes y
	 * de la ventana. Y se agregan los componentes a la ventana.
	 **/
    private void configComponents()
    {
    	//Se agregan los escuchadoes al menu de navegacion
		this.menuNotas.addMouseListener(this);
		this.menuHorario.addMouseListener(this);
		this.menuAjustes.addMouseListener(this);
		this.menueventos.addMouseListener(this);
		//Configuracion de los componentes
		lbLogo.setBounds(200,50,400,100);
		lbLogo.setFont(new Font("Century725 Cn BT",Font.BOLD,36));
		lbLogo.setBorder(borde);
		configTable();
		//Configuracion de la ventana y se agregan los componentes
		this.setDisableInicio();
		this.setTitle("Inicio");
    	this.add(lbLogo);
    	this.add(jspTable);
    	this.update(this.getGraphics());
    }
    
    /**
	 * Metodo especifico para darle un formato al Jtable
	 * de esta clase. Agregando columnas.
	 * @see Jtable
	 */
    private void configTable()
    {    	
		centrar.setHorizontalAlignment(SwingConstants.CENTER);
		titleColorAlign.setHorizontalAlignment(SwingConstants.CENTER);
		titleColorAlign.setBackground(Color.decode("0x80bde5"));
    	aux.add("a13513");
    	aux.add("a21321");
    	aux.add("a12321");
    	aux.add("a23131");
		modelo.addColumn("<html><b>Titulo</b></html>");
		modelo.addColumn("<html><b>Descripcion</b></html>");
		modelo.addColumn("<html><b>Materia</b></html>");
		modelo.addColumn("<html><b>Expiracion</b></html>");
		modelo.addRow(aux.toArray());
		events.getTableHeader().setDefaultRenderer(titleColorAlign);
		for (int i = 0; i<events.getColumnCount(); i++)
			events.getColumnModel().getColumn(i).setCellRenderer(centrar);
		jspTable.setBounds(10,170,760,450);
		jspTable.setBorder(tituloTabla);
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
     * principales.
     */
    public void mouseClicked(MouseEvent me)
    {
    	if(me.getSource() == this.menuHorario)
    	{
    		horario = new HorarioGUI();
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
    }
    
}