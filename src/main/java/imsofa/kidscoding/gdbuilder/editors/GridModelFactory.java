/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import imsofa.kidscoding.gdbuilder.GDParser;
import imsofa.kidscoding.gdbuilder.GDParser.Function;
import imsofa.kidscoding.gdbuilder.GDParser.Variable;
import imsofa.kidscoding.gdbuilder.ParseException;
import imsofa.kidscoding.gdbuilder.editors.GridModel.MapEntry;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author lendle
 */
public class GridModelFactory {

    public static String model2Code(GridModel model) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("extends 'res://GridBase.gd'\r\n");
        if (model.getCustomCodes() != null) {
            buffer.append(model.getCustomCodes() + "\r\n");
        }
        buffer.append("var map={\r\n");
        List<MapEntry> entries = model.getMapEntries();
        for (int i = 0; entries != null && i < entries.size(); i++) {
            MapEntry entry = entries.get(i);
            buffer.append(String.format("\t[%d,%d]:[\"%s\",\"%s\"]\r\n", entry.getGridX(), entry.getGridY(),
                    entry.getObjectGDPath(), entry.getName()));
        }
        buffer.append("}\r\n");
        buffer.append(model.getInitFunction() + "\r\n");
        buffer.append(model.getGoalFunction() + "\r\n");
        buffer.append(String.format("func getGridSize():\r\n\treturn Vector2(%d, %d)\r\n", model.getGridWidth(), model.getGridHeight()));
        buffer.append(String.format("func getTileSize():\r\n\treturn Vector2(%d, %d)\r\n", model.getTileWidth(), model.getTileHeight()));

        return buffer.toString();
    }

    public static GridModel code2Model(String code) throws ParseException {
        int gridWidth=13;
        int gridHeight=7;
        int tileWidth=65;
        int tileHeight=65;
        
        GridModel model = new GridModel();
        if (code.startsWith("extends ")) {
            code = code.substring(code.indexOf('\n') + 1).trim();
        }
        System.out.println(code);
        GDParser parser = new GDParser(new StringReader(code));
        parser.Input(parser);
        StringBuffer customCodes=new StringBuffer();
        for (int i = 0; i < parser.gd.vars.size(); i++) {
            Variable var = (Variable) parser.gd.vars.get(i);
            //System.out.println(var.id);
            if(var.id.equals("map")){
                int index=var.content.indexOf("{")+1;
                int index1=var.content.lastIndexOf("}");
                String sub=var.content.substring(index, index1).trim();
                String [] array=sub.split("](\\s)*,");
                for(String str : array){
                    //System.out.println("str="+str);
                    str=str+"]";
                    int colonIndex=str.indexOf(":");
                    String firstPart=str.substring(0, colonIndex).trim();
                    String secondPart=str.substring(colonIndex+1).trim();
                    String [] firstPartArgs=firstPart.substring(1, firstPart.length()-1).trim().split(",");
                    String [] secondPartArgs=secondPart.substring(1, secondPart.length()-1).trim().split(",");
                    int gridX=Integer.valueOf(firstPartArgs[0].trim());
                    int gridY=Integer.valueOf(firstPartArgs[1].trim());
                    String gdPath=secondPartArgs[0].trim();
                    String name=secondPartArgs[1].trim();
                    gdPath=gdPath.substring(1, gdPath.length()-1);
                    name=name.substring(1, name.length()-1);
                    MapEntry entry=new MapEntry();
                    entry.setGridX(gridX);
                    entry.setGridY(gridY);
                    entry.setName(name);
                    entry.setObjectGDPath(gdPath);
                    model.getMapEntries().add(entry);
                }
            }else{
                if(customCodes.length()>0){
                    customCodes.append("\r\n");
                }
                customCodes.append("var "+var.content);
            }
        }
        for (int i = 0; i < parser.gd.functions.size(); i++) {
            Function f = (Function) parser.gd.functions.get(i);
            System.out.println(f.id);
            if(f.id.equals("initMap")){
                model.setInitFunction("func "+f.id+"("+String.join(",", f.args)+"):\r\n"+f.body);
            }else if(f.id.equals("is_goal")){
                model.setGoalFunction("func "+f.id+"("+String.join(",", f.args)+"):\r\n"+f.body);
            }else if(f.id.equals("getGridSize")){
                int index=f.body.indexOf("Vector2(");
                index=index+"Vector2(".length();
                int index1=f.body.indexOf(")", index+1);
                String sizeString=f.body.substring(index+1, index1);
                String [] sizeArgs=sizeString.split(",");
                gridWidth=Integer.valueOf(sizeArgs[0].trim());
                gridHeight=Integer.valueOf(sizeArgs[1].trim());
            }else if(f.id.equals("getTileSize")){
                int index=f.body.indexOf("Vector2(");
                index=index+"Vector2(".length();
                int index1=f.body.indexOf(")", index+1);
                String sizeString=f.body.substring(index+1, index1);
                String [] sizeArgs=sizeString.split(",");
                tileWidth=Integer.valueOf(sizeArgs[0].trim());
                tileHeight=Integer.valueOf(sizeArgs[1].trim());
            }else{
                if(customCodes.length()>0){
                    customCodes.append("\r\n");
                }
                customCodes.append("func "+f.id+"("+String.join(",", f.args)+"):\r\n"+f.body);
            }
        }
        
        model.setGridWidth(gridWidth);
        model.setGridHeight(gridHeight);
        model.setTileWidth(tileWidth);
        model.setTileHeight(tileHeight);
        
        if(customCodes.length()>0){
            model.setCustomCodes(customCodes.toString());
        }
        return model;
    }
}
