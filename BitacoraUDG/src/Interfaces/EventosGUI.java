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
 import javax.swing.JCheckBox;
 import javax.swing.JComboBox;
 import javax.swing.JScrollPane;
 import javax.swing.ButtonGroup;
 import javax.swing.JOptionPane;
 import javax.swing.border.Border;
 import javax.swing.border.LineBorder;
 import javax.swing.JRadioButtonMenuItem;
 import javax.swing.table.DefaultTableModel;
 import javax.swing.ListSelectionModel;
 import java.util.Vector;

public class EventosGUI extends DefaultFrame implements MouseListener{
	private InicioGUI inicio;
	private HorarioGUI horario;
	private NotasGUI notas;
	private EventosGUI eventos;
	private JMenuBar bottomBar;
	private JMenu materias, orden, filtros;
	private JRadioButtonMenuItem incumplidosFilter,cumplidosFilter,noseHizoFilter,allFilter;
	private JRadioButtonMenuItem ordenCaducidad,ordenDescripcion,ordenMateria;
	private ButtonGroup ordenGroup,filterGroup;
	private JPanel panelBottom;
	private JScrollPane panelTabla;
	private JTable table;
	private DefaultTableModel modeloEventos;
	private roundButton addEvent;
	private boolean estadoCumplido,estadoIncumplido,estadoNoseHizo;
	Vector<Object> aux = new Vector<Object>();
	/**
	 * Construcor EventosGUI
	 */
	public EventosGUI() {
		initComponents();
	}
	
	/**
     * Method initComponents
	 * - Se crean todos los componentes declados en la clase.
	 **/
	private void initComponents()
	{
		addEvent = new roundButton("<html><h1>+</h1></html>");
		modeloEventos = new DefaultTableModel();
		table = new JTable(modeloEventos){
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
		};
		panelTabla = new JScrollPane(table);
		panelBottom = new JPanel();
		bottomBar = new JMenuBar();
		materias = new JMenu("Materias");
		orden = new JMenu("Ordenar por:");
		ordenGroup = new ButtonGroup();
		ordenMateria = new JRadioButtonMenuItem("Materia");
		ordenCaducidad = new JRadioButtonMenuItem("Caducidad");
		ordenDescripcion = new JRadioButtonMenuItem("Descripcion");
		filtros = new JMenu("Filtrar por:");
		filterGroup = new ButtonGroup();
		cumplidosFilter = new JRadioButtonMenuItem("Cumplidos");
		incumplidosFilter = new JRadioButtonMenuItem("Incumplidos");
		noseHizoFilter = new JRadioButtonMenuItem("No se hizo");
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
		aux.add("Hola Mundo perros\n\n\n");
		aux.add("Valer verga");
		aux.add("31-febrero-2017");
		aux.add(false);
		aux.add(false);
		aux.add(false);
		estadoCumplido=false;
		estadoIncumplido=false;
		estadoNoseHizo = false;
		//Se agregan los escuchadores de la ventana
		this.menuNotas.addMouseListener(this);
		this.menuHorario.addMouseListener(this);
		this.menuAjustes.addMouseListener(this);
		this.menuInicio.addMouseListener(this);
		//Se configuran los componentes
		addEvent.setBounds(this.getWidth()-100,this.getHeight()-140,50,50);
		addEvent.setFocusPainted(false);
		addEvent.addMouseListener(this);
		modeloEventos.addColumn("Descripcion");//Columna 0
		modeloEventos.addColumn("Materia");//Columna     1
		modeloEventos.addColumn("Caducidad");//Columna   2
		modeloEventos.addColumn("Cumplido");//Columna    3
		modeloEventos.addColumn("Incumplido");//Columna  4
		modeloEventos.addColumn("No se hizo");//Columna  5
		table.addMouseListener(this);
		//table.getColumn("Estado").setCellRenderer(new RadioButtonRenderer());//Para colocar radioButtons en una sola celda
		//table.getColumn("Estado").setCellEditor(new RadioButtonEditor(new JCheckBox()));
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setRowHeight(30);
		table.setCellSelectionEnabled(true);
		panelTabla.setBounds(5,50,773,500);
		modeloEventos.addRow(aux);
		ordenCaducidad.setSelected(true);
		ordenGroup.add(ordenCaducidad);
		ordenGroup.add(ordenDescripcion);
		ordenGroup.add(ordenMateria);
		orden.add(ordenCaducidad);
		orden.add(ordenDescripcion);
		orden.add(ordenMateria);
		allFilter.setSelected(true);
		filterGroup.add(cumplidosFilter);
		filterGroup.add(incumplidosFilter);
		filterGroup.add(noseHizoFilter);
		filterGroup.add(allFilter);
		filtros.add(materias);
		filtros.add(cumplidosFilter);
		filtros.add(incumplidosFilter);
		filtros.add(noseHizoFilter);
		filtros.add(allFilter);
		bottomBar.add(orden);
		bottomBar.add(filtros);
		panelBottom.setBounds(0,this.getHeight()-80,this.getWidth(),20);
		panelBottom.setLayout(new BorderLayout());
		panelBottom.add(bottomBar,BorderLayout.SOUTH);
		//Se configura la ventana y se agregan los componentes
		this.setTitle("Eventos");
		this.add(panelBottom);
		this.add(panelTabla);
		this.add(addEvent);
		this.setDisableeventos();
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
    	if(me.getSource() == this.menuNotas)
    	{
    		notas = new NotasGUI();
    		this.dispose();
    	}
    	
    	if(me.getSource() == table)
    	{
    		switch(table.getColumnModel().getSelectionModel().getLeadSelectionIndex())
    		{
    			case 3://Cumplido
    				if(estadoIncumplido == true || estadoNoseHizo == true)
    				{
    					table.setValueAt(false,table.getSelectionModel().getLeadSelectionIndex(),3);
    					JOptionPane.showMessageDialog(this,"No se puede seleccionar mas de un estado a la vez.","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
    				}
    				else
    					estadoCumplido = (boolean) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(),table.getColumnModel().getSelectionModel().getLeadSelectionIndex());
    				break;
    			case 4://Incumplido
    				if(estadoCumplido == true || estadoNoseHizo == true)
    				{
    					table.setValueAt(false,table.getSelectionModel().getLeadSelectionIndex(),4);
    					JOptionPane.showMessageDialog(this,"No se puede seleccionar mas de un estado a la vez.","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
    				}
    				else
    					estadoIncumplido = (boolean) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(),table.getColumnModel().getSelectionModel().getLeadSelectionIndex());
    				break;
    			case 5://No se hizo
    				if(estadoCumplido == true || estadoIncumplido == true)
    				{
    					table.setValueAt(false,table.getSelectionModel().getLeadSelectionIndex(),5);
    					JOptionPane.showMessageDialog(this,"No se puede seleccionar mas de un estado a la vez.","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
    				}
    				else 
    					estadoNoseHizo = (boolean) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(),table.getColumnModel().getSelectionModel().getLeadSelectionIndex());
    				break;
    		}
    	}
    	if(me.getSource() == addEvent)
    	{
    		new AddEventoGUI(this);
    	}
    	
    }
    
}
