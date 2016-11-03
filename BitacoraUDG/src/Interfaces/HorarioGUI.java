package Interfaces;

 import javax.swing.JTable;
 import javax.swing.JScrollPane;
 import javax.swing.JButton;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import javax.swing.table.DefaultTableModel;
 import Misc.roundButton;
 
public class HorarioGUI extends DefaultFrame implements MouseListener{
	//Atributos
	private InicioGUI inicio;
	private NotasGUI notas;
	private AgendaGUI agenda;
	private JTable horarioTable;
	private DefaultTableModel modeloTable;
	private JScrollPane panelTabla;
	private roundButton nuevaMateria;
	/**
	 * Constructor HorarioGUI
	 */
	public HorarioGUI() 
	{
		initComponents();
	}
	
	/* Method initComponents
	 * - Se crean todos los componentes declados en la clase
	 *   y se inicializan las variables.
	 **/
	private void initComponents()
	{
		modeloTable = new DefaultTableModel();
		horarioTable = new JTable(modeloTable);
		panelTabla = new JScrollPane(horarioTable);
		nuevaMateria = new roundButton("<html><h1>+</h1></html>");
		configComponents();
	}
	
	/* configComponents
	 * - Se configuran las propiedades de los componentes
	 *   de la ventana, asi como configurar la misma ventana
	 **/
	private void configComponents()
	{
		//Se agregan los listeners de la navegacion
		this.menuNotas.addMouseListener(this);
		this.menuAjustes.addMouseListener(this);
		this.menuAgenda.addMouseListener(this);
		this.menuInicio.addMouseListener(this);
		//Se agregan las columnas a la tabla
		modeloTable.addColumn("Horas");
		modeloTable.addColumn("Lunes");
		modeloTable.addColumn("Martes");
		modeloTable.addColumn("Miercoles");
		modeloTable.addColumn("Jueves");
		modeloTable.addColumn("Viernes");
		modeloTable.addColumn("Sabado");
		//Se acomodan los componentes en el JFrame y se agregan al mismo
		panelTabla.setBounds(10,10,760,450);
		nuevaMateria.setBounds(this.getWidth()-100,this.getHeight()-180,50,50);
		this.setTitle("Horario");
		this.setDisableHorario();
    	this.add(panelTabla);
    	this.add(nuevaMateria);
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
    	if(me.getSource() == this.menuInicio)
    	{
    		inicio = new InicioGUI();
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
