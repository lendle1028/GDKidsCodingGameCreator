/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lendle
 */
public class GridModel {

    private int gridWidth = -1;
    private int gridHeight = -1;
    private int tileWidth = -1;
    private int tileHeight = -1;
    private String goalFunction = null;
    private String initFunction = null;
    private List<MapEntry> mapEntries=new ArrayList<>();
    private List<String> vars=new ArrayList<>();
    private List<String> functions=new ArrayList<>();

    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public String getGoalFunction() {
        return goalFunction;
    }

    public void setGoalFunction(String goalFunction) {
        this.goalFunction = goalFunction;
    }

    public String getInitFunction() {
        return initFunction;
    }

    public void setInitFunction(String initFunction) {
        this.initFunction = initFunction;
    }

    public List<MapEntry> getMapEntries() {
        return mapEntries;
    }

    public void setMapEntries(List<MapEntry> mapEntries) {
        this.mapEntries = mapEntries;
    }

    public List<String> getVars() {
        return vars;
    }

    public void setVars(List<String> vars) {
        this.vars = vars;
    }

    public List<String> getFunctions() {
        return functions;
    }

    public void setFunctions(List<String> functions) {
        this.functions = functions;
    }
    
    

    static class MapEntry {
        private int gridX=-1;
        private int gridY=-1;
        private String objectGDPath=null;
        private String name=null;

        public int getGridX() {
            return gridX;
        }

        public void setGridX(int gridX) {
            this.gridX = gridX;
        }

        public int getGridY() {
            return gridY;
        }

        public void setGridY(int gridY) {
            this.gridY = gridY;
        }

        public String getObjectGDPath() {
            return objectGDPath;
        }

        public void setObjectGDPath(String objectGDPath) {
            this.objectGDPath = objectGDPath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
        
    }
}
