/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder.editors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author lendle
 */
public class GridEditor extends javax.swing.JPanel implements GDFileEditor{
    private GridModel gridModel=null;
    private boolean modified=false;
    private List<ModifiedListener> modifiedListeners=new ArrayList<>();
    @Override
    public void addModifiedListener(ModifiedListener l) {
        modifiedListeners.add(l);
    }

    @Override
    public void removeModifiedListener(ModifiedListener l) {
        modifiedListeners.remove(l);
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }
    
    
    /**
     * Creates new form GridEditor
     */
    public GridEditor() {
        initComponents();
//        try {
//            String code=FileUtils.readFileToString(new File("test.gd"), "utf-8");
//
//            gridModel=GridModelFactory.code2Model(code);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        this.propsTable.setModel(new GridEditorTableModel(gridModel));
        this.propsTable.getColumnModel().getColumn(1).setCellEditor(new GridEditorTableCellEditor());
        this.propsTable.getColumnModel().getColumn(1).setCellRenderer(new GridEditorTableCellRenderer());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        propsTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        propsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        propsTable.setRowHeight(25);
        jScrollPane1.setViewportView(propsTable);

        jTabbedPane1.addTab("Properties", jScrollPane1);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable propsTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void init(File gdFile) {
        try {
            String code=FileUtils.readFileToString(gdFile, "utf-8");
            gridModel=GridModelFactory.code2Model(code);
            this.propsTable.setModel(new GridEditorTableModel(gridModel));
            propsTable.getModel().addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    modified=true;
                    for(ModifiedListener l : modifiedListeners){
                        l.modified();
                    }
                }
            });
            modified=false;
            this.propsTable.getColumnModel().getColumn(1).setCellEditor(new GridEditorTableCellEditor());
            this.propsTable.getColumnModel().getColumn(1).setCellRenderer(new GridEditorTableCellRenderer());
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run() {
                    propsTable.updateUI();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public String getCode() {
        return GridModelFactory.model2Code(gridModel);
    }
}
