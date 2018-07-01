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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author lendle
 */
public class MapEntryTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    private Object editorValue=null;
    private JButton deleteButton=null;
    private JTable parentTable=null;
    private JFormattedTextField intValueEditor = null;
    private JTextField stringValueEditor=null;

    public MapEntryTableCellEditor() {
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
                MapEntryTableCellEditor.this.stopCellEditing();
            }
        });
        
        stringValueEditor=new JTextField();
        stringValueEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorValue=stringValueEditor.getText();
                MapEntryTableCellEditor.this.stopCellEditing();
            }
        });
        stringValueEditor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                editorValue=stringValueEditor.getText();
                MapEntryTableCellEditor.this.stopCellEditing();
            }
            
        });
        deleteButton=new JButton("-");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEntryTableModel model=(MapEntryTableModel) parentTable.getModel();
                int index=-1;
                for(int i=0; i<model.getEntries().size(); i++){
                    if(model.getEntries().get(i)==editorValue){
                        index=i;
                        break;
                    }
                }
                if(index!=-1){
                    model.getEntries().remove(index);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            parentTable.updateUI();
                        }
                    });
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
        switch (column) {
            case 0:
            case 1:
                this.editorValue=value;
                intValueEditor.setValue(value);
                return intValueEditor;
            case 2:
            case 3:
                this.editorValue=value;
                stringValueEditor.setText((value==null)?"":(""+value));
                return stringValueEditor;
            case 4:
                this.editorValue = (MapEntry) value;
                return deleteButton;
        }
        return null;
    }

}
