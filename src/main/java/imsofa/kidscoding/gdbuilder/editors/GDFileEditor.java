/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import java.io.File;

/**
 *
 * @author lendle
 */
public interface GDFileEditor {
    public void init(File gdFile);
    public boolean isModified();
}
