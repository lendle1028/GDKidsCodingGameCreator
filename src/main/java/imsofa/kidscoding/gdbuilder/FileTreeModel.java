/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder;

import java.io.File;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author lendle
 */
public class FileTreeModel implements TreeModel{
    private File rootDir=null;

    public FileTreeModel(File rootDir) {
        this.rootDir=rootDir;
    }

    public File getRootDir() {
        return rootDir;
    }

    public void setRootDir(File rootDir) {
        this.rootDir = rootDir;
    }
    
    @Override
    public Object getRoot() {
        return this.getRootDir();
    }

    @Override
    public Object getChild(Object parent, int index) {
        File parentFile=(File) parent;
        return parentFile.listFiles()[index];
    }

    @Override
    public int getChildCount(Object parent) {
        File parentFile=(File) parent;
        return parentFile.listFiles().length;
    }

    @Override
    public boolean isLeaf(Object node) {
        File file=(File) node;
        return !file.isDirectory();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File parentFile=(File) parent;
        File [] children=parentFile.listFiles();
        for(int i=0; children!=null && i<children.length; i++){
            if(children[i].equals(child)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }
    
}
