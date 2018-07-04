/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import imsofa.kidscoding.gdbuilder.GDParser;
import imsofa.kidscoding.gdbuilder.GDParserTokenManager;
import imsofa.kidscoding.gdbuilder.ParseException;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author lendle
 */
public class GridObjectModelFactory {

    public static String model2Code(GridObjectModel model) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("extends 'res://GridObject.gd'\r\n");
        if (model.getCustomCodes() != null) {
            buffer.append(model.getCustomCodes() + "\r\n");
        }
        buffer.append("func init(name):\r\n"
                + "\t.init(name, \"" + model.getType() + "\", \"" + model.getGdPath() + "\")\r\n"
                +"\t__init__()");
        buffer.append("func isKinematicObject():\r\n"
                + "\treturn " + model.isKinematicObject()+"\r\n");
        if(model.getCustomInit()!=null){
            buffer.append(model.getCustomInit());
        }

        return buffer.toString();
    }

    public static GridObjectModel code2Model(String code) throws ParseException {
        GridObjectModel model = new GridObjectModel();
        if (code.startsWith("extends ")) {
            code = code.substring(code.indexOf('\n') + 1).trim();
        }
        GDParser parser = new GDParser(new StringReader(code));
        parser.Input(parser);
        StringBuffer customCodes = new StringBuffer();
        for (int i = 0; i < parser.gd.vars.size(); i++) {
            GDParser.Variable var = (GDParser.Variable) parser.gd.vars.get(i);
            //System.out.println(var.id);

            if (customCodes.length() > 0) {
                customCodes.append("\r\n");
            }
            customCodes.append("var " + var.content);

        }
        for (int i = 0; i < parser.gd.functions.size(); i++) {
            GDParser.Function f = (GDParser.Function) parser.gd.functions.get(i);
            if (f.id.equals("__init__")) {
                model.setCustomInit("func " + f.id + "(" + String.join(",", f.args) + "):\r\n" + f.body);
            } else if (f.id.equals("isKinematicObject")) {
                if(f.body.contains("return true")){
                    model.setKinematicObject(true);
                }else{
                    model.setKinematicObject(false);
                }
            } else if (f.id.equals("init")) {
                int index=f.body.indexOf(".init(")+".init(".length();
                int index1=f.body.indexOf(")", index);
                String str=f.body.substring(index, index1);
                String [] array=str.split(",");
                array[1]=array[1].trim();
                array[2]=array[2].trim();
                model.setType(array[1].substring(1, array[1].length()-1));
                model.setGdPath(array[2].substring(1, array[2].length()-1));
            } else {
                if (customCodes.length() > 0) {
                    customCodes.append("\r\n");
                }
                customCodes.append("func " + f.id + "(" + String.join(",", f.args) + "):\r\n" + f.body);
            }
        }

        if (customCodes.length() > 0) {
            model.setCustomCodes(customCodes.toString());
        }
        return model;
    }
}
