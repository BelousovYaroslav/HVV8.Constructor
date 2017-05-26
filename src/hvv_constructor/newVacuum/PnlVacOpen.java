/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_constructor.newVacuum;

import hvv_constructor.HVV_Constructor;
import hvv_devices.HVV_VacuumDevices;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author yaroslav
 */
public class PnlVacOpen extends javax.swing.JPanel {

    private HVV_Constructor theApp;
    private DefaultComboBoxModel cmbObjectModel;
    
    /**
     * Creates new form PnlVacOpen
     */
    public PnlVacOpen( HVV_Constructor app) {
        initComponents();
        
        theApp = app;
        cmbObjectModel = new DefaultComboBoxModel();
        HVV_VacuumDevices.getInstance().fillComboOpenClose( cmbObjectModel);
        cmbObject.setModel( cmbObjectModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbObject = new javax.swing.JComboBox();

        setMaximumSize(new java.awt.Dimension(900, 400));
        setMinimumSize(new java.awt.Dimension(900, 400));
        setLayout(null);

        jLabel1.setText("Открываемый объект:");
        add(jLabel1);
        jLabel1.setBounds(30, 30, 410, 30);

        cmbObject.setMaximumRowCount(30);
        cmbObject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08А. Клапан XLCV", "08B. Клапан XLCV", "08C. Клапан XLCV", "09A. Клапан XSA1", "09B. Клапан XSA1", "09C. Клапан XSA1", "10A. Клапан XSA3", "10B. Клапан XSA3", "10C. Клапан XSA3", "11A. Клапан Ap-Tech", "11B. Клапан Ap-Tech", "012. Затвор Pfeifer Vacuum", "013. Клапан SS-BN8VCR8-C Swagelok", "14A. Клапан Parker (Гребёнка O2)", "14B. Клапан Parker (Гребёнка Ne20)", "14C. Клапан Parker (Гребёнка He4)", "14D. Клапан Parker (Гребёнка Ne20+22)", "14E. Клапан Parker (Гребёнка He3)", "15A. Клапан Ap-Tech (подвод O2)", "15B. Клапан Ap-Tech (подвод Ne20)", "15C. Клапан Ap-Tech (подвод He4)", "16A. Регулятор расхода газа MKS 1179 (O2)", "16B. Регулятор расхода газа MKS 1179 (Ne20)", "16C. Регулятор расхода газа MKS 1179 (He4)", "17A. Регулятор расхода газа MKS M330B (Ne20+22)", "17B. Регулятор расхода газа MKS M330B (He3)", "018. Регулятор расхода газа MKS 1480A (N2)", "23A. Клапан 6LV-BNBW4-P1-O Swagelok (подвод Ne20+22)", "23B. Клапан 6LV-BNBW4-P1-O Swagelok (подвод He3)" }));
        add(cmbObject);
        cmbObject.setBounds(30, 70, 410, 40);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox cmbObject;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
