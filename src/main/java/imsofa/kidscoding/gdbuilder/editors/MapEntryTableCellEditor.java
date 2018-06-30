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
import imsofa.kidscoding.gdbuilder.editors.GridModel.MapEntry;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author lendle
 */
public class MapEntryTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    private MapEntry editorValue=null;
    private JButton editButton=null;
    private JButton deleteButton=null;
    private JTable parentTable=null;

    public MapEntryTableCellEditor() {
        editButton=new JButton("...");
        
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEntryTableCellEditor.this.stopCellEditing();
            }
        });
        
        deleteButton=new JButton("-");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEntryTableModel model=(MapEntryTableModel) parentTable.getModel();
                for(MapEntry entry : model.getEntries()){
                    if(entry==editorValue){
                        model.getEntries().remove(entry);
                        break;
                    }
                }
                MapEntryTableCellEditor.this.stopCellEditing();
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        return this.editorValue;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        parentTable=table;
        switch (row) {
            case 4:
                this.editorValue = (MapEntry) value;
                return deleteButton;
            case 5:
                this.editorValue = (MapEntry) value;
                return editButton;
        }
        return null;
    }

}
