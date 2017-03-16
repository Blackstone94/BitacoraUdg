package Misc;

 import java.awt.Color;
 import java.awt.Component;
 import javax.swing.JTable;
 import javax.swing.JLabel;
 import javax.swing.table.DefaultTableCellRenderer;
 import javax.swing.SwingConstants;

public class SelectionHorario  extends DefaultTableCellRenderer{
	DefaultTableCellRenderer renderer;
	
	public SelectionHorario(JTable table)
	{
        renderer = (DefaultTableCellRenderer)
            table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        //renderer.setBackground(Color.decode("0xc6ecff"));
	}
	
    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        
        if (String.valueOf(value).compareTo("S")==0) {
            this.setBackground(Color.decode("0x2b89bf"));
            this.setForeground(Color.white);
        } else {
            this.setBackground(Color.white);
            this.setForeground(Color.black);
        }
        return this;
    }
}
