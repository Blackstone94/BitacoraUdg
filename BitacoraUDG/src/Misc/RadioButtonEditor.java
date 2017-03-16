package Misc;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class RadioButtonEditor extends DefaultCellEditor implements ItemListener 
{
	public JPanel pnl = new JPanel();
	public ButtonGroup group1 = new ButtonGroup();
	public JRadioButton btnOne = new JRadioButton("Cumplido");
	public JRadioButton btnTwo = new JRadioButton("Incumplido");
	public JRadioButton btnThree = new JRadioButton("No se hizo");

	public RadioButtonEditor(JCheckBox checkBox) 
	{
		super(checkBox);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int row, int column) 
	{
		if (value==null) 
       		return null;
		group1.add(btnOne );
		group1.add(btnTwo );
		group1.add(btnThree );
  		pnl.add(btnOne );
		pnl.add(btnTwo );
		pnl.add(btnThree );
		btnThree .setSelected(false);
		btnTwo .setSelected(false);
		btnOne .setSelected(false);

		switch(Integer.parseInt((String)value)) {
			case 3:
				btnThree.setSelected(true);
			break;
			case 2:
				btnTwo.setSelected(true);
			break;
			case 1:
				btnOne.setSelected(true);
			break;
		}
		return pnl;
	}

	public Object getCellEditorValue() 
	{
		if(btnTwo.isSelected() == true)
			return "2";
		if(btnOne.isSelected() == true)
			return "1";
		if(btnThree.isSelected() == true)
			return "3";
		return "";
	}

	public void itemStateChanged(ItemEvent e) 
	{
		super.fireEditingStopped();                
	}
 }