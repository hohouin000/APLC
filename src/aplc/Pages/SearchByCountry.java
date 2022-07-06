/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplc.Pages;

import aplc.Country;
import aplc.ReadFile;
import aplc.Functions;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caesa
 */
public class SearchByCountry extends javax.swing.JFrame {

    DefaultTableModel model;
    List<Country> confirmedCasesDataSet = ReadFile.getConfirmedCases();
    List<Country> countriesDeathCasesDataSet = ReadFile.getDeathCases();
    List<Country> countriesRecoveredCasesDataSet = ReadFile.getRecoveredCases();

    /**
     * Creates new form SearchByCountry
     */
    public SearchByCountry() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tb4 = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblCountry = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb4.setAutoCreateRowSorter(true);
        tb4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type of Cases", "Number of Cases"
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

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnReset.setText("Reset Table");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnReset)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnReset))
                .addGap(25, 25, 25)
                .addComponent(lblCountry)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        tb4.setAutoCreateRowSorter(true);
        model = (DefaultTableModel) tb4.getModel();
        model.setRowCount(0);
        String countrySearched;
        if (txtSearch.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Textfield is empty, please insert a country !",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                countrySearched = txtSearch.getText().toLowerCase();
                List<Country> countryResult = Functions.searchByCountryName(confirmedCasesDataSet, countrySearched);
                Country country = countryResult.get(0);
                lblCountry.setText("Country Name:  " + country.getName_Region());
                initTable(country);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Country Name !",
                        "Error Message",
                        JOptionPane.ERROR_MESSAGE);

            }

        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void initTable(Country country) {
        String[][] tableData = new String[9][2];
        tableData[0][0] = "Total Confirmed Covid 19 Cases";
        tableData[1][0] = "Total Recovered Covid 19 Cases";
        tableData[2][0] = "Total Death Covid 19 Cases";
        tableData[3][0] = "Lowest Confirmed Covid 19 Cases Per Day";
        tableData[4][0] = "Highest Confirmed Covid 19 Cases Per Day";
        tableData[5][0] = "Lowest Recovered Covid 19 Cases Per Day";
        tableData[6][0] = "Highest Recovered Covid 19 Cases Per Day";
        tableData[7][0] = "Lowest Death Covid 19 Cases Per Day";
        tableData[8][0] = "Highest Death Covid 19 Cases Per Day";

        tableData[0][1] = String.valueOf(Functions.getTotalConfirmedCasesByCountry(Functions.getSameCountries(confirmedCasesDataSet, country.getName_Region())));
        tableData[1][1] = String.valueOf(Functions.getTotalConfirmedCasesByCountry(Functions.getSameCountries(countriesRecoveredCasesDataSet, country.getName_Region())));
        tableData[2][1] = String.valueOf(Functions.getTotalConfirmedCasesByCountry(Functions.getSameCountries(countriesDeathCasesDataSet, country.getName_Region())));
        tableData[3][1] = String.valueOf(Math.max(Functions.getLowestCountryData(Functions.getSameCountries(confirmedCasesDataSet, country.getName_Region())
        ), 0));
        tableData[4][1] = String.valueOf(Math.max(Functions.getHighestCountryData(Functions.getSameCountries(confirmedCasesDataSet, country.getName_Region()),
                Functions.getHighestValue), 0));
        tableData[5][1] = String.valueOf(Math.max(Functions.getLowestCountryData(Functions.getSameCountries(countriesRecoveredCasesDataSet, country.getName_Region())
        ), 0));
        tableData[6][1] = String.valueOf(Math.max(Functions.getHighestCountryData(Functions.getSameCountries(countriesRecoveredCasesDataSet, country.getName_Region()),
                Functions.getHighestValue), 0));
        tableData[7][1] = String.valueOf(Math.max(Functions.getLowestCountryData(Functions.getSameCountries(countriesDeathCasesDataSet, country.getName_Region())
        ), 0));
        tableData[8][1] = String.valueOf(Math.max(Functions.getHighestCountryData(Functions.getSameCountries(countriesDeathCasesDataSet, country.getName_Region()),
                Functions.getHighestValue), 0));

        for (String[] row : tableData) {
            model.addRow(row);
        }
    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel) tb4.getModel();
        model.setRowCount(0);
        lblCountry.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
        StatisticSelection StatisticSelection = new StatisticSelection();
        StatisticSelection.setVisible(true);
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
            java.util.logging.Logger.getLogger(SearchByCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchByCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchByCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchByCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchByCountry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCountry;
    private javax.swing.JTable tb4;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}