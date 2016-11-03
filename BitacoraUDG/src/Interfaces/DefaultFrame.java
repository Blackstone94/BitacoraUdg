package Interfaces;

 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JMenu;
 import javax.swing.JMenuItem;
 import javax.swing.JMenuBar;
 import javax.swing.UIManager;

public class DefaultFrame extends JFrame{
	//Atributos
	private JMenuBar menuToolbar;
	public JMenu menuHorario,menuAgenda,menuNotas,menuAjustes,menuInicio;
	
	/** 
	 *Constructor DefaultFrame
	 */
	public DefaultFrame() {
		initComponents();
	}
	
	
    private void initComponents()
    {
    	menuToolbar = new JMenuBar();
    	menuInicio = new JMenu("Inicio");
    	menuHorario = new JMenu("Horario");
    	menuAgenda = new JMenu("Agenda");
    	menuNotas = new JMenu("Notas");
    	menuAjustes = new JMenu("Ajustes");
    	configComponents();
    }
    
    private void configComponents()
    {
    	try 
    	{
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex) 
		{
		}
		menuToolbar.add(menuInicio);
		menuToolbar.add(menuHorario);
		menuToolbar.add(menuAgenda);
		menuToolbar.add(menuNotas);
		menuToolbar.add(menuAjustes);
    	this.setBounds(300,100,800,700);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    	this.setLayout(null);
    	this.setJMenuBar(menuToolbar);
    	this.setVisible(true);
    }
    
    /* Familia de metodos setDisable
     * - Estos metodos deshabilitan el menuItem
     *   de navegacion de acuerdo a su nombre
     **/
    public void setDisableInicio()
    {
    	menuInicio.setEnabled(false);
    }
    
    public void setDisableHorario()
    {
    	this.menuHorario.setEnabled(false);
    }
    
    public void setDisableNotas()
    {
    	this.menuNotas.setEnabled(false);
    }
    
    public void setDisableAgenda()
    {
    	this.menuAgenda.setEnabled(false);
    }
    
    public void setDisableAjustes()
    {
    	this.menuAjustes.setEnabled(false);
    }
    
}
