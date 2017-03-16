package Interfaces;

 import java.util.Date;
 import java.util.Calendar;
 import java.awt.Color;
 import java.awt.GridLayout;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JButton;
 import javax.swing.JSpinner;
 import javax.swing.JTextField;
 import javax.swing.JCheckBox;
 import javax.swing.border.Border;
 import javax.swing.SpinnerDateModel;
 import javax.swing.border.LineBorder;
 import java.awt.event.WindowListener;
 import java.awt.event.ActionListener;
 import java.awt.event.ActionEvent;
 import java.awt.event.WindowEvent;
 //import com.toedter.calendar.*;


public class AddEventoGUI extends JFrame implements WindowListener,ActionListener{
	private EventosGUI eventos;
	private JLabel lbNomMateria,lbDescripcion,lbCaducidad,lbHoraAlarma;
	private JTextField txtNomMateria,txtDescripcion,txtCaducidad;
	private JCheckBox cbAlarma;
	private JButton aceptar,cancelar;
	private Border borde = LineBorder.createBlackLineBorder();
	private JSpinner spHoraAlarma;
	private Date date;
	private SpinnerDateModel sm;
	private JSpinner.DateEditor de;
	
	/**
	 * Method AddEventoGUI
	 */
	public AddEventoGUI(EventosGUI events) {
		eventos = events;
		eventos.setEnabled(false);
		lbNomMateria = new JLabel("Nombre Materia:");
		txtNomMateria = new JTextField();
		lbDescripcion = new JLabel("Descripcion del evento:");
		txtDescripcion = new JTextField();
		lbCaducidad = new JLabel("Caducidad:");
		txtCaducidad = new JTextField();
		cbAlarma = new JCheckBox("Alarama de evento");
		lbHoraAlarma=new JLabel("Hora final de la alarma:");
		date = new Date();
		sm = new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);
		spHoraAlarma = new JSpinner(sm);
		de = new JSpinner.DateEditor(spHoraAlarma,"HH:mm:ss");
		aceptar = new JButton("Aceptar");
		cancelar  =new JButton("Cancelar");
		initComponents();
	}
	
	private void initComponents()
	{
		lbNomMateria.setBounds(5,15,100,30);
		txtNomMateria.setBounds(120,15,258,30);
		lbDescripcion.setBounds(5,50,130,30);
		txtDescripcion.setBounds(120,50,258,30);
		lbCaducidad.setBounds(5,85,100,30);
		txtCaducidad.setBounds(120,85,258,30);
		cbAlarma.setBounds(90,120,298,30);
		cbAlarma.setSelected(false);
		cbAlarma.addActionListener(this);
		lbHoraAlarma.setBounds(5,155,150,30);
		spHoraAlarma.setBounds(155,155,150,30);
		addAlarm(false);
		aceptar.setBounds(40,200,100,40);
		aceptar.addActionListener(this);
		cancelar.setBounds(250,200,100,40);
		cancelar.addActionListener(this);
		this.setBounds(450,250,400,290);
		this.setTitle("Nueva Eventos");
		this.addWindowListener(this);
		this.setLayout(null);
		this.add(lbNomMateria);
		this.add(txtNomMateria);
		this.add(lbDescripcion);
		this.add(txtDescripcion);
		this.add(lbCaducidad);
		this.add(txtCaducidad);
		this.add(cbAlarma);
		this.add(lbHoraAlarma);
		this.add(spHoraAlarma);
		this.add(aceptar);
		this.add(cancelar);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void addAlarm(boolean estadoCheck)
	{
		if(estadoCheck)
		{
			this.spHoraAlarma.setEnabled(true);
		}
		else
		{
			this.spHoraAlarma.setEnabled(false);
		}
	}
	
	public void windowClosing(WindowEvent e)
	{
		eventos.setEnabled(true);
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
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == cbAlarma)
		{
			addAlarm(cbAlarma.isSelected());
		}
		if(ae.getSource() == cancelar)
		{
			eventos.setEnabled(true);
			this.dispose();
		}
	}
}
