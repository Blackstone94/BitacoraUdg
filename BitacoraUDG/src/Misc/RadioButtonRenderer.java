package Misc;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RadioButtonRenderer implements TableCellRenderer {
public JPanel pnl = new JPanel();
public ButtonGroup group1 = new ButtonGroup();
public JRadioButton btnOne = new JRadioButton("Cumplido");
public JRadioButton btnTwo = new JRadioButton("No cumplido");
public JRadioButton btnThree = new JRadioButton("No se hizo");

	public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
		if (value==null) 
			return null;
		group1.add(btnOne );
	 	group1.add(btnTwo );
	 	group1.add(btnThree );
	 	pnl.add(btnOne );
	 	pnl.add(btnTwo );
	 	pnl.add(btnThree );
	
	 	btnThree .setSelected(false);
	 	btnOne .setSelected(false);
		btnTwo .setSelected(false);
	 	switch(Integer.parseInt((String)value))
		{
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
}