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
public class GridEditorTableCellRenderer implements TableCellRenderer{
    private JLabel textRenderer=new JLabel();
    private JButton complexValueRenderer=new JButton("...");
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        switch (row) {
            case GRID_WIDTH:
            case GRID_HEIGHT:
            case TILE_WIDTH:
            case TILE_HEIGHT:
                textRenderer.setText(""+value);
                return textRenderer;
            case GOAL_FUNCTION:
            case INIT_FUNCTION:
                return complexValueRenderer;
            case MAP_ENTRIES:
                return complexValueRenderer;
            case CUSTOM_CODES:
                return complexValueRenderer;
        }
        return null;
    }
    
}
