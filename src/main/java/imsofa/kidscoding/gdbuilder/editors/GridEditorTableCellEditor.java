/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import static imsofa.kidscoding.gdbuilder.editors.GridEditorTableModel.*;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author lendle
 */
public class GridEditorTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private Object editorValue = null;
    private JFormattedTextField intValueEditor = null;

    public GridEditorTableCellEditor() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        intValueEditor=new JFormattedTextField(formatter);
    }

    @Override
    public Object getCellEditorValue() {
        return this.editorValue;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        switch (row) {
            case GRID_WIDTH:

            case GRID_HEIGHT:

            case TILE_WIDTH:

            case TILE_HEIGHT:
                this.editorValue = value;
                intValueEditor.setValue(value);
                return intValueEditor;
            case GOAL_FUNCTION:
                return "Goal Function";
            case INIT_FUNCTION:
                return "Init Function";
            case MAP_ENTRIES:
                return "Map Entries";
        }
    }

}
