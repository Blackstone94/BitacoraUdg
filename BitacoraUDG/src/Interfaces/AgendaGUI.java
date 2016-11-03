package Interfaces;

 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;

public class AgendaGUI extends DefaultFrame implements MouseListener{
	private InicioGUI inicio;
	private HorarioGUI horario;
	private NotasGUI notas;
	private AgendaGUI agenda;
	/**
	 * Construcor AgendaGUI
	 */
	public AgendaGUI() {
		initComponents();
	}
	
	/**
     * Method initComponents
	 * - Se crean todos los componentes declados en la clase.
	 **/
	private void initComponents()
	{
		configComponents();
	}
	
	/**
	 * configComponents
	 * - Se configuran las propiedades de los componentes y
	 *   de la ventana. Y se agregan los componentes a la 
	 *   ventana.
	 **/
	private void configComponents()
	{
		//Se agregan los escuchadores de la ventana
		this.menuNotas.addMouseListener(this);
		this.menuHorario.addMouseListener(this);
		this.menuAjustes.addMouseListener(this);
		this.menuInicio.addMouseListener(this);
		this.setTitle("Agenda");
		this.setDisableAgenda();
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
