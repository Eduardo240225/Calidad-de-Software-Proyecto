package CLASES;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TABLAIMAGENES extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            btn.setName("i");
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            return btn;
        }
        return super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);
    }
}
