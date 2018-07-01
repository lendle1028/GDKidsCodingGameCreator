/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import static imsofa.kidscoding.gdbuilder.editors.GridEditorTableModel.*;

/**
 *
 * @author lendle
 */
public class MapEntryTableCellRenderer implements TableCellRenderer{
    private JButton deleteButton=new JButton("-");
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        switch (column) {
            case 4:
                return deleteButton;
        }
        return null;
    }
    
}
