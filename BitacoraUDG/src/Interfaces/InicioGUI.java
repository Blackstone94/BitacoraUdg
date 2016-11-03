 package Interfaces;
 
 import java.awt.Font;
 import java.util.Vector;
 import javax.swing.JLabel;
 import javax.swing.JTable;
 import javax.swing.JScrollPane;
 import javax.swing.border.Border;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import javax.swing.border.LineBorder;
 import javax.swing.table.DefaultTableModel;
 
public class InicioGUI extends DefaultFrame implements MouseListener{
	//Atributos
	private HorarioGUI horario;
	private NotasGUI notas;
	private AgendaGUI agenda;
	private JScrollPane jspTable;
	private JTable eventos;
	private JLabel lbLogo;
	private Border borde = LineBorder.createBlackLineBorder();
	private DefaultTableModel modelo;
	Vector<String> aux = new Vector<String>();//Eliminar despues por inutilidad
	
	//Constructor
    public InicioGUI() {
    	initComponents();
    }
    
    /**
     * Method initComponents
	 * - Se crean todos los componentes declados en la clase.
	 **/
    private void initComponents()
    {
		lbLogo = new JLabel("LOGO",JLabel.CENTER);
		modelo = new DefaultTableModel();
		eventos = new JTable(modelo);
		jspTable = new JScrollPane(eventos);
		configComponents();
    }
    
    /* configComponents
	 * - Se configuran las propiedades de los componentes y
	 *   de la ventana. Y se agregan los componentes a la 
	 *   ventana.
	 **/
    private void configComponents()
    {
    	//Se agregan los escuchadoes al menu de navegacion
		this.menuNotas.addMouseListener(this);
		this.menuHorario.addMouseListener(this);
		this.menuAjustes.addMouseListener(this);
		this.menuAgenda.addMouseListener(this);
		//Configuracion de los componentes
		lbLogo.setBounds(200,50,400,100);
		lbLogo.setFont(new Font("Century725 Cn BT",Font.BOLD,36));
		lbLogo.setBorder(borde);
    	aux.add("a13513");
    	aux.add("a21321");
    	aux.add("a12321");
    	aux.add("a23131");
		modelo.addColumn("Titulo");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Materia");
		modelo.addColumn("Expiracion");
		modelo.addRow(aux);
		jspTable.setBounds(10,170,760,450);
		//Configuracion de la ventana y se agregan los componentes
		this.setDisableInicio();
		this.setTitle("Inicio");
    	this.add(lbLogo);
    	this.add(jspTable);
    	this.update(this.getGraphics());
    }
    
    /* Familia de metodos mouseListener
	 * - Se sobre escribe el metodo mouseClicked de la
	 *   clase MouseListener. Para poder hacer poseible
	 *   la navegacion entre ventanas.
	 * @param MouseEvent me  evento captado por los escuchadores
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
    
    public void mouseClicked(MouseEvent me)
    {
    	if(me.getSource() == this.menuHorario)
    	{
    		horario = new HorarioGUI();
    		this.dispose();
    	}
    	if(me.getSource() == this.menuAgenda)
    	{
    		agenda = new AgendaGUI();
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