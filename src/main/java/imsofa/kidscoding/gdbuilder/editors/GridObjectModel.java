/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

/**
 *
 * @author lendle
 */
public class GridObjectModel {
    private String gdPath=null;
    private boolean kinematicObject=false;
    private String customCodes=null;
    private String type=null;
    private String customInit=null;

    public String getCustomInit() {
        return customInit;
    }

    public void setCustomInit(String customInit) {
        this.customInit = customInit;
    }
    
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

    public String getCustomCodes() {
        return customCodes;
    }

    public void setCustomCodes(String customCodes) {
        this.customCodes = customCodes;
    }

    public String getGdPath() {
        return gdPath;
    }

    public void setGdPath(String gdPath) {
        this.gdPath = gdPath;
    }

    public boolean isKinematicObject() {
        return kinematicObject;
    }

    public void setKinematicObject(boolean kinematicObject) {
        this.kinematicObject = kinematicObject;
    }
    
    
}
