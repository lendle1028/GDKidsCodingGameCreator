/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import imsofa.kidscoding.gdbuilder.GDParser;
import imsofa.kidscoding.gdbuilder.ParseException;
import imsofa.kidscoding.gdbuilder.editors.GridModel.MapEntry;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author lendle
 */
public class GridModelFactory {
    public static String model2Code(GridModel model){
        StringBuffer buffer=new StringBuffer();
        buffer.append("extends 'res://GridBase.gd'\r\n");
        if(model.getCustomCodes()!=null){
            buffer.append(model.getCustomCodes()+"\r\n");
        }
        buffer.append("var map={\r\n");
        List<MapEntry> entries=model.getMapEntries();
        for(int i=0; entries!=null && i<entries.size(); i++){
            MapEntry entry=entries.get(i);
            buffer.append(String.format("\t[%d,%d]:[\"%s\",\"%s\"]\r\n", entry.getGridX(), entry.getGridY(),
                    entry.getObjectGDPath(), entry.getName()));
        }
        buffer.append("}\r\n");
        buffer.append(model.getInitFunction()+"\r\n");
        buffer.append(model.getGoalFunction()+"\r\n");
        buffer.append(String.format("func getGridSize():\r\n\treturn Vector2(%d, %d)\r\n", model.getGridWidth(), model.getGridHeight()));
        buffer.append(String.format("func getTileSize():\r\n\treturn Vector2(%d, %d)\r\n", model.getTileWidth(), model.getTileHeight()));
        
        return buffer.toString();
    }
    
    public static GridModel code2Model(String code) throws ParseException{
        GridModel model=new GridModel();
        if(code.startsWith("extends ")){
            code=code.substring(code.indexOf('\n')+1);
        }
        GDParser parser=new GDParser(new StringReader(code));
        parser.Input(parser);
        return model;
    }
}
