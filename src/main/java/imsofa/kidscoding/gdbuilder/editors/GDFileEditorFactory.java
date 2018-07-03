/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author lendle
 */
public class GDFileEditorFactory {
    public static GDFileEditor getEditor(File file) throws IOException{
        String content=FileUtils.readFileToString(file, "utf-8");
        if(content.trim().startsWith("extends 'res://GridBase.gd'")){
            return new GridEditor();
        }else{
            return new GDTextArea();
        }
    }
}
