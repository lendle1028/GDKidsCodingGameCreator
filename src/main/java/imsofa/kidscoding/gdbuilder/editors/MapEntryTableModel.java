/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import imsofa.kidscoding.gdbuilder.editors.GridModel.MapEntry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lendle
 */
public class MapEntryTableModel extends AbstractTableModel{
    private List<MapEntry> entries=new ArrayList<>();

    public MapEntryTableModel(List<MapEntry> entries) {
        this.entries=entries;
    }

    public MapEntryTableModel() {
    }
    
    public List<MapEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<MapEntry> entries) {
        this.entries = entries;
    }

    @Override
    public int getRowCount() {
        return entries.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MapEntry entry=entries.get(rowIndex);
        switch(columnIndex){
            case 0: return entry.getGridX();
            case 1: return entry.getGridY();
            case 2: return entry.getName();
            case 3: return entry.getObjectGDPath();
            case 4: return entry;
            case 5: return entry;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex==4 || columnIndex==5;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Grid X";
            case 1: return "Grid Y";
            case 2: return "Name";
            case 3: return "GD Path";
            case 4: return "";
            case 5: return "";
        }
        return null;
    }
    
    
}
