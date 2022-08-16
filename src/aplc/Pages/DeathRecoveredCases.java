/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplc.Pages;

import aplc.Country;
import aplc.Functions;
import aplc.ReadFile;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caesa
 */
public class DeathRecoveredCases extends javax.swing.JFrame {

    DefaultTableModel model;
    List<Country> confirmedCasesDataSet = ReadFile.getConfirmedCases();
    List<Country> distinctCountriesList = Functions.getDistinctCountryList(confirmedCasesDataSet);
    List<Country> countriesDeathCasesDataSet = ReadFile.getDeathCases();
    List<Country> countriesRecoveredCasesDataSet = ReadFile.getRecoveredCases();

    /**
     * Creates new form DeathRecoveredCases
     */
    public DeathRecoveredCases() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        initTable();
    }

    private void initTable() {
        try {
            tb3.setAutoCreateRowSorter(true);
            model = (DefaultTableModel) tb3.getModel();
            String[][] tableData = new String[distinctCountriesList.size()][5];
            if (distinctCountriesList != null) {
                for (int i = 0; i < distinctCountriesList.size(); i++) {

                    tableData[i][0] = (distinctCountriesList.get(i).getName_Region());
                    tableData[i][1] = String.valueOf(Math.max(Functions.getLowestCountryData(Functions.getSameCountries(countriesDeathCasesDataSet, distinctCountriesList.get(i).getName_Region())
                    ), 0));
                    tableData[i][2] = String.valueOf(Math.max(Functions.getHighestCountryData(Functions.getSameCountries(countriesDeathCasesDataSet, distinctCountriesList.get(i).getName_Region()),
                            Functions.getHighestValue), 0));
                    tableData[i][3] = String.valueOf(Math.max(Functions.getLowestCountryData(Functions.getSameCountries(countriesRecoveredCasesDataSet, distinctCountriesList.get(i).getName_Region())
                    ), 0));
                    tableData[i][4] = String.valueOf(Math.max(Functions.getHighestCountryData(Functions.getSameCountries(countriesRecoveredCasesDataSet, distinctCountriesList.get(i).getName_Region()),
                            Functions.getHighestValue), 0));

                }
            }
            for (String[] row : tableData) {
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // TODO add your handling code here:

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tb3 = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tb3.setAutoCreateRowSorter(true);
        tb3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tb3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Country", "Lowest Death", "Highest Death", "Lowest Recovered", "Highest Recovered"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb3);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
        StatisticSelection statsObj = new StatisticSelection();
        statsObj.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(DeathRecoveredCases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeathRecoveredCases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeathRecoveredCases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeathRecoveredCases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeathRecoveredCases().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb3;
    // End of variables declaration//GEN-END:variables
}
