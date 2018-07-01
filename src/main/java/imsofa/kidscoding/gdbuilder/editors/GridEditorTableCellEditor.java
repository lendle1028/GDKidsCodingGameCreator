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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author lendle
 */
public class GridEditorTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private Object editorValue = null;
    private JFormattedTextField intValueEditor = null;
    private JButton codeButton=null;
    private JButton mapEntriesButton=null;

    public GridEditorTableCellEditor() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        intValueEditor=new JFormattedTextField(formatter);
        intValueEditor.addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                editorValue=evt.getNewValue();
                GridEditorTableCellEditor.this.stopCellEditing();
            }
        });
        
        codeButton=new JButton("...");
        
        codeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GDEditorDialog dlg=new GDEditorDialog(null, (String)editorValue);
                dlg.setSize(800, 600);
                dlg.setLocationRelativeTo(null);
                dlg.setVisible(true);
                editorValue=dlg.getCode();
                GridEditorTableCellEditor.this.stopCellEditing();
            }
        });
        
        mapEntriesButton=new JButton("...");
        mapEntriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEntryEditorDialog dlg=new MapEntryEditorDialog(null, (List<GridModel.MapEntry>) editorValue);
                dlg.setSize(800, 600);
                dlg.setLocationRelativeTo(null);
                dlg.setVisible(true);
                editorValue=dlg.getEntries();
                GridEditorTableCellEditor.this.stopCellEditing();
            }
        });
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
            case INIT_FUNCTION:
                String code=(String) value;
                if(code==null || code.trim().length()==0){
                    if(row==GOAL_FUNCTION){
                        value="func is_goal(pos):\r\n\treturn true";
                    }else if(row==INIT_FUNCTION){
                        value="func initMap():\r\n\tfor entry in map:\r\n\t\tspawnObject(entry[0], entry[1], map[entry][0], map[entry][1])";
                    }
                }
                this.editorValue = value;
                return codeButton;
            case MAP_ENTRIES:
                this.editorValue = value;
                return mapEntriesButton;
            case CUSTOM_CODES:
                this.editorValue = value;
                return codeButton;
        }
        return null;
    }

}
