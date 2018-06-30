/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lendle
 */
public class GridEditorTableModel extends AbstractTableModel {

    private GridModel gridModel = null;

    public static final int GRID_WIDTH = 0, GRID_HEIGHT = 1, TILE_WIDTH = 2, TILE_HEIGHT = 3, GOAL_FUNCTION = 4,
            INIT_FUNCTION = 5, MAP_ENTRIES = 6;

    public GridEditorTableModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }

    @Override
    public int getRowCount() {
        return 7;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            switch (rowIndex) {
                case GRID_WIDTH:
                    return "Grid Width";
                case GRID_HEIGHT:
                    return "Grid Height";
                case TILE_WIDTH:
                    return "Tile Width";
                case TILE_HEIGHT:
                    return "Tile Height";
                case GOAL_FUNCTION:
                    return "Goal Function";
                case INIT_FUNCTION:
                    return "Init Function";
                case MAP_ENTRIES:
                    return "Map Entries";
            }
        }else{
            switch (rowIndex) {
                case GRID_WIDTH:
                    return this.gridModel.getGridWidth();
                case GRID_HEIGHT:
                    return this.gridModel.getGridHeight();
                case TILE_WIDTH:
                    return this.gridModel.getTileWidth();
                case TILE_HEIGHT:
                    return this.gridModel.getTileHeight();
                case GOAL_FUNCTION:
                    return this.gridModel.getGoalFunction();
                case INIT_FUNCTION:
                    return this.gridModel.getInitFunction();
                case MAP_ENTRIES:
                    return this.gridModel.getMapEntries();
            }
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (rowIndex) {
                case GRID_WIDTH:
                    this.gridModel.setGridWidth(Integer.valueOf(""+aValue));
                    return;
                case GRID_HEIGHT:
                    this.gridModel.setGridHeight(Integer.valueOf(""+aValue));
                    return;
                case TILE_WIDTH:
                    this.gridModel.setTileWidth(Integer.valueOf(""+aValue));
                    return;
                case TILE_HEIGHT:
                    this.gridModel.setTileHeight(Integer.valueOf(""+aValue));
                    return;
                case GOAL_FUNCTION:
                    this.gridModel.setGoalFunction(""+aValue);
                    return;
                case INIT_FUNCTION:
                    this.gridModel.setInitFunction(""+aValue);
                    return;
                case MAP_ENTRIES:
                    this.gridModel.getMapEntries().clear();
                    this.gridModel.getMapEntries().addAll((List)aValue);
                    return;
            }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex==1;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return (columnIndex==0)?String.class:Object.class;
    }

    @Override
    public String getColumnName(int column) {
        return (column==0)?"Key":"Value";
    }

}
