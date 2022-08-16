/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplc.Pages;

import aplc.Prolog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caesa
 */
public class KnowledgeBase extends javax.swing.JFrame {

    DefaultTableModel model;

    /**
     * Creates new form Prolog
     */
    public KnowledgeBase() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        try {
            Prolog.generatePrologText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTableAsc() {
        try {
            List<String> Names = new ArrayList<>();
            List<String> Cases = new ArrayList<>();
            tb4.setAutoCreateRowSorter(true);
            model = (DefaultTableModel) tb4.getModel();
            Optional<List> temp = Optional.ofNullable(Prolog.getCasesByAscendingOrder());
            if (temp.isPresent()) {
                Prolog.getCasesByAscendingOrder().stream().forEach(p -> {
                    Names.addAll(Arrays.asList(p.replace("[", "").split(",")[0]));
                });
                Prolog.getCasesByAscendingOrder().stream().forEach(p -> {
                    Cases.addAll(Arrays.asList(p.replace("]", "").substring(p.indexOf(",") + 1)));
                });
                for (int i = 0; i < Prolog.getCasesByAscendingOrder().size(); i++) {
                    String names = Names.get(i);
                    String cases = Cases.get(i);
                    model.insertRow(model.getRowCount(), new Object[]{names, cases});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initTableDsc() {
        try {
            List<String> Names = new ArrayList<>();
            List<String> Cases = new ArrayList<>();
            tb4.setAutoCreateRowSorter(true);
            model = (DefaultTableModel) tb4.getModel();
            Optional<List> temp = Optional.ofNullable(Prolog.getCasesByAscendingOrder());
            if (temp.isPresent()) {
                Prolog.getCasesByDecendingOrder().stream().forEach(p -> {
                    Names.addAll(Arrays.asList(p.replace("[", "").split(",")[0]));
                });
                Prolog.getCasesByDecendingOrder().stream().forEach(p -> {
                    Cases.addAll(Arrays.asList(p.replace("]", "").substring(p.indexOf(",") + 1)));
                });
                for (int i = 0; i < Prolog.getCasesByDecendingOrder().size(); i++) {
                    String names = Names.get(i);
                    String cases = Cases.get(i);
                    model.insertRow(model.getRowCount(), new Object[]{names, cases});
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbSort = new javax.swing.JComboBox<>();
        btnBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb4 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        cmbSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Select Sorting Option-", "Accending", "Deccending" }));
        cmbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSortActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tb4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name Region", "Cases"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmbSort, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(420, 420, 420)
                        .addComponent(btnBack)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbSort, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
        MainMenu menuObj = new MainMenu();
        menuObj.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void cmbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSortActionPerformed
        switch (cmbSort.getSelectedIndex()) {
            default -> {
                cmbSort.setSelectedIndex(0);
            }
            case 0 -> {
                model = (DefaultTableModel) tb4.getModel();
                model.setRowCount(0);
            }
            case 1 -> {
                model = (DefaultTableModel) tb4.getModel();
                model.setRowCount(0);
                initTableAsc();
            }

            case 2 -> {
                model = (DefaultTableModel) tb4.getModel();
                model.setRowCount(0);
                initTableDsc();
            }
        }
    }//GEN-LAST:event_cmbSortActionPerformed

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
            java.util.logging.Logger.getLogger(KnowledgeBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KnowledgeBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KnowledgeBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KnowledgeBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KnowledgeBase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox<String> cmbSort;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb4;
    // End of variables declaration//GEN-END:variables
}
