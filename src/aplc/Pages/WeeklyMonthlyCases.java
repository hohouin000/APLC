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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caesa
 */
public class WeeklyMonthlyCases extends javax.swing.JFrame {

    DefaultTableModel model;
    SimpleDateFormat weekFormat = new SimpleDateFormat("ww,Y");
    SimpleDateFormat weekFormat2 = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMM-yyyy");
    List<Country> confirmedCasesDataSet = ReadFile.getConfirmedCases();
    List<Country> availableCountriesList = Functions.getCountryList(confirmedCasesDataSet);
    List<String> weeklyFormattedDates = Functions.getWeeklyOrMonthlyFormattedDate(confirmedCasesDataSet, weekFormat);
    List<String> monthlyFormattedDates = Functions.getWeeklyOrMonthlyFormattedDate(confirmedCasesDataSet, monthFormat);

    /**
     * Creates new form WeeklyMonthlyCases
     */
    public WeeklyMonthlyCases() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        initTableWeeklyCases();
    }

    private void initTableWeeklyCases() {
        tb2.setAutoCreateRowSorter(true);
        tb2.setAutoResizeMode(tb2.AUTO_RESIZE_OFF);
        model = (DefaultTableModel) tb2.getModel();
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Country");
        if (weeklyFormattedDates != null) {
            for (int i = 0; i < weeklyFormattedDates.size(); i++) {
                String weeklyDate = weeklyFormattedDates.get(i);
                columnNames.add((Functions.getStartDateForWeekly(confirmedCasesDataSet, weeklyDate, weekFormat, weekFormat2)
                        + " to " + Functions.getEndDateForWeekly(confirmedCasesDataSet, weeklyDate, weekFormat, weekFormat2)));
            }
            for (String columnName : columnNames) {
                model.addColumn(columnName);
            }
        }
        String[][] tableData = new String[availableCountriesList.size()][weeklyFormattedDates.size() + 1];
        if (availableCountriesList != null) {
            for (int i = 0; i < availableCountriesList.size(); i++) {
                tableData[i][0] = availableCountriesList.get(i).getName_Region();
                for (int k = 0; k < weeklyFormattedDates.size(); k++) {
                    tableData[i][k + 1] = (Functions.getWeeklyOrMonthlyConfirmedCasesByCountry(Functions.getSameCountries(
                            confirmedCasesDataSet, availableCountriesList.get(i).getName_Region()),
                            availableCountriesList.get(i).getName_Region(),
                            weeklyFormattedDates.get(k), weekFormat)).toString();
                }
            }
            for (String[] row : tableData) {
                model.addRow(row);
            }

        }
    }

    private void initTableMonthlyCases() {
        tb2.setAutoCreateRowSorter(true);
        tb2.setAutoResizeMode(tb2.AUTO_RESIZE_OFF);
        model = (DefaultTableModel) tb2.getModel();
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Country");
        if (monthlyFormattedDates != null) {
            for (int i = 0; i < monthlyFormattedDates.size(); i++) {

                String MonthlyDate = monthlyFormattedDates.get(i);
                System.out.println(MonthlyDate); //   01/2020
                try {
                    columnNames.add(monthFormat.format(monthFormat.parse(MonthlyDate)));
                } catch (ParseException ex) {
                    Logger.getLogger(WeeklyMonthlyCases.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for (String columnName : columnNames) {
                model.addColumn(columnName);
            }
        }
        String[][] tableData = new String[availableCountriesList.size()][monthlyFormattedDates.size() + 1];
        if (availableCountriesList != null) {
            for (int i = 0; i < availableCountriesList.size(); i++) {
                tableData[i][0] = availableCountriesList.get(i).getName_Region();
                for (int k = 0; k < monthlyFormattedDates.size(); k++) {
                    tableData[i][k + 1] = (Functions.getWeeklyOrMonthlyConfirmedCasesByCountry(Functions.getSameCountries(confirmedCasesDataSet,
                            availableCountriesList.get(i).getName_Region()),
                            availableCountriesList.get(i).getName_Region(),
                            monthlyFormattedDates.get(k), monthFormat)).toString();
                }
            }
            for (String[] row : tableData) {
                model.addRow(row);
            }

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

        jScrollPane2 = new javax.swing.JScrollPane();
        tb2 = new javax.swing.JTable();
        cmbReport = new javax.swing.JComboBox<>();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb2.setAutoCreateRowSorter(true);
        tb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tb2);

        cmbReport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Weekly Confirmed Cases", "Monthly Confirmed Cases" }));
        cmbReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbReportActionPerformed(evt);
            }
        });

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
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmbReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbReportActionPerformed
        // TODO add your handling code here:
        switch (cmbReport.getSelectedIndex()) {
            default -> {
                cmbReport.setSelectedIndex(0);
            }
            case 0 -> {
                model.setRowCount(0);
                model.setColumnCount(0);
                initTableWeeklyCases();
            }
            case 1 -> {
                model.setRowCount(0);
                model.setColumnCount(0);
                initTableMonthlyCases();
            }
        }
    }//GEN-LAST:event_cmbReportActionPerformed

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
            java.util.logging.Logger.getLogger(WeeklyMonthlyCases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WeeklyMonthlyCases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WeeklyMonthlyCases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WeeklyMonthlyCases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WeeklyMonthlyCases().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox<String> cmbReport;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb2;
    // End of variables declaration//GEN-END:variables
}
