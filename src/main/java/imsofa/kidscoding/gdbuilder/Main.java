/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.kidscoding.gdbuilder;

import imsofa.kidscoding.gdbuilder.editors.GDFileEditor;
import imsofa.kidscoding.gdbuilder.editors.GDFileEditorFactory;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author lendle
 */
public class Main extends javax.swing.JFrame {

    private File editingFile = null;
    private GDFileEditor currentEditor = null;
    private JFileChooser fileChooser = null;

    /**
     * Creates new form NewJFrame
     */
    public Main() {
        initComponents();
        this.setSize(1024, 600);
        FileTreeModel model = new FileTreeModel(new File("godot"));
        this.contentTree.setModel(model);
        this.contentTree.setCellRenderer(new FileTreeCellRenderer());
        this.contentTree.updateUI();

        fileChooser = new JFileChooser() {
            public void approveSelection() {
                if (getSelectedFile().isFile()) {
                    return;
                } else {
                    super.approveSelection();
                }
            }
        };
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        contentTree = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        editorContainer = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        buttonSave = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        buttonOpen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(300);

        contentTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contentTreeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(contentTree);

        jSplitPane1.setLeftComponent(jScrollPane2);

        jPanel1.setLayout(new java.awt.BorderLayout());

        editorContainer.setLayout(new java.awt.BorderLayout());
        jPanel1.add(editorContainer, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        buttonSave.setText("SAVE");
        buttonSave.setEnabled(false);
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        jPanel2.add(buttonSave);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jSplitPane1.setRightComponent(jPanel1);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jToolBar1.setRollover(true);

        buttonOpen.setText("Open");
        buttonOpen.setFocusable(false);
        buttonOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpenActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonOpen);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contentTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contentTreeMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {

                File file = (File) contentTree.getSelectionPath().getLastPathComponent();
                if (editorContainer.getComponentCount() != 0) {
                    if (currentEditor != null) {
                        if (currentEditor.isModified()) {
                            int ret = JOptionPane.showConfirmDialog(Main.this, "File modified, save (y/n)?", "File Modified", JOptionPane.YES_NO_CANCEL_OPTION);
                            if (ret == JOptionPane.YES_OPTION) {
                                FileUtils.write(editingFile, currentEditor.getCode(), "utf-8");
                                currentEditor.setModified(false);
                            }
                        }
                    }
                    editorContainer.removeAll();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            buttonSave.setEnabled(false);
                        }
                    });
                }

                if (file.getName().endsWith(".gd")) {
                    editingFile = file;
                    GDFileEditor editor = GDFileEditorFactory.getEditor(file);
                    currentEditor = editor;
                    editor.init(file);
                    editor.addModifiedListener(new GDFileEditor.ModifiedListener() {
                        @Override
                        public void modified() {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    buttonSave.setEnabled(true);
                                }
                            });
                        }
                    });
                    editorContainer.add((JComponent) editor);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            editorContainer.revalidate();
                            editorContainer.repaint();
                        }
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_contentTreeMouseClicked

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        try {
            // TODO add your handling code here:
            FileUtils.write(editingFile, currentEditor.getCode(), "utf-8");
            currentEditor.setModified(false);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    buttonSave.setEnabled(false);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpenActionPerformed
        // TODO add your handling code here:
        int ret = fileChooser.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            FileTreeModel model = new FileTreeModel(fileChooser.getSelectedFile());
            this.contentTree.setModel(model);
            this.contentTree.setCellRenderer(new FileTreeCellRenderer());
            this.contentTree.updateUI();
        }
    }//GEN-LAST:event_buttonOpenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonOpen;
    private javax.swing.JButton buttonSave;
    private javax.swing.JTree contentTree;
    private javax.swing.JPanel editorContainer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
