package Interfaces;

 import Misc.roundButton;
 import java.awt.Color;
 import java.awt.BorderLayout;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import javax.swing.JMenu;
 import javax.swing.JPanel;
 import javax.swing.JTable;
 import javax.swing.JLabel;
 import javax.swing.JMenuBar;
 import javax.swing.JMenuItem;
 import javax.swing.ImageIcon;
 import javax.swing.JTextField;
 import javax.swing.JScrollPane;
 import javax.swing.ButtonGroup;
 import javax.swing.JOptionPane;
 import javax.swing.border.Border;
 import javax.swing.border.LineBorder;
 import javax.swing.JRadioButtonMenuItem;
 import javax.swing.table.DefaultTableModel;

public class NotasGUI extends DefaultFrame implements MouseListener{
	private InicioGUI inicio;
	private HorarioGUI horario;
	private EventosGUI eventos;
	private JMenuBar bottomBar;
	private JMenu materias, orden, filtros;
	private JRadioButtonMenuItem ordenFecha,ordenTipo,ordenNombre;
	private JRadioButtonMenuItem imgFilter,audioFilter,fileFilter,allFilter;
	private JPanel panelBottom;
	private ButtonGroup ordenGroup,filterGroup;
	private JScrollPane panelTabla;
	private JTable notasTable;
	private DefaultTableModel modeloNotas;
	private roundButton addNote;
	private ImageIcon imgNota1,imgLupa;
	private JTextField nomBuscar;
	private JLabel dummyNota1,btnSearch;
	private Border borde = LineBorder.createBlackLineBorder();
	/**
	 * Contructor NotasGUI
	 */
	public NotasGUI() {
		initComponents();
		
	}
	
	/**
     * Method initComponents
	 * - Se crean todos los componentes declados en la clase.
	 **/
	private void initComponents()
	{
		imgNota1 = new ImageIcon(this.getClass().getResource("/Interfaces/Iconos/lbNota.png"));
		imgLupa = new ImageIcon(this.getClass().getResource("/Interfaces/Iconos/lupa.jpg"));
		dummyNota1 = new JLabel(imgNota1);
		nomBuscar = new JTextField();
		btnSearch = new JLabel(imgLupa);
		addNote = new roundButton("<html><h1>+</h1></html>");
		modeloNotas = new DefaultTableModel();
		notasTable = new JTable(modeloNotas);
		panelTabla = new JScrollPane(notasTable);
		panelBottom = new JPanel();
		bottomBar = new JMenuBar();
		materias = new JMenu("Materias");
		orden = new JMenu("Ordenar por:");
		ordenGroup = new ButtonGroup();
		ordenNombre = new JRadioButtonMenuItem("Nombre");
		ordenFecha = new JRadioButtonMenuItem("Fecha");
		ordenTipo = new JRadioButtonMenuItem("Tipo");
		filtros = new JMenu("Filtrar por:");
		filterGroup = new ButtonGroup();
		fileFilter = new JRadioButtonMenuItem("Archivos");
		imgFilter = new JRadioButtonMenuItem("Imagenes");
		audioFilter = new JRadioButtonMenuItem("Audio");
		allFilter = new JRadioButtonMenuItem("Todo");
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
		this.menuHorario.addMouseListener(this);
		this.menuAjustes.addMouseListener(this);
		this.menueventos.addMouseListener(this);
		this.menuInicio.addMouseListener(this);
		//Se configuran los componentes
		dummyNota1.setBounds(120,this.getHeight()-110,imgNota1.getIconWidth(),imgNota1.getIconHeight());
		dummyNota1.setBorder(borde);
		nomBuscar.setBounds(5,10,150,30);
		btnSearch.setBounds(160,5,imgLupa.getIconWidth(),imgLupa.getIconHeight());
		btnSearch.addMouseListener(this);
		addNote.setBounds(this.getWidth()-100,this.getHeight()-140,50,50);
		addNote.setFocusPainted(false);
		modeloNotas.addColumn("Titulo");
		modeloNotas.addColumn("Descripcion");
		modeloNotas.addColumn("Multimedia");
		panelTabla.setBounds(5,50,773,500);
		ordenNombre.setSelected(true);
		ordenGroup.add(ordenNombre);
		ordenGroup.add(ordenTipo);
		ordenGroup.add(ordenFecha);
		orden.add(ordenNombre);
		orden.add(ordenTipo);
		orden.add(ordenFecha);
		allFilter.setSelected(true);
		filterGroup.add(fileFilter);
		filterGroup.add(imgFilter);
		filterGroup.add(audioFilter);
		filterGroup.add(allFilter);
		filtros.add(fileFilter);
		filtros.add(imgFilter);
		filtros.add(audioFilter);
		filtros.add(allFilter);
		bottomBar.add(materias);
		bottomBar.add(orden);
		bottomBar.add(filtros);
		panelBottom.setBounds(0,this.getHeight()-80,this.getWidth(),20);
		panelBottom.setLayout(new BorderLayout());
		panelBottom.add(bottomBar,BorderLayout.SOUTH);
		//Se configura la ventana y se agregan los componentes
		this.setTitle("Notas");
		this.add(nomBuscar);
		this.add(dummyNota1);
		this.add(panelBottom);
		this.add(panelTabla);
		this.add(btnSearch);
		this.add(addNote);
		this.setDisableNotas();
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
    	if(me.getSource() == this.menueventos)
    	{
    		eventos = new EventosGUI();
    		this.dispose();
    	}
    	if(me.getSource() == this.menuAjustes)
    		System.out.println ("Presiono ajustes");
    	if(me.getSource() == this.menuInicio)
    	{
    		inicio = new InicioGUI();
    		this.dispose();
    	}
    	if(me.getSource() == btnSearch)
    		System.out.println ("Presiono buscar");
    }
    
}
