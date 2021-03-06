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
public class PnlVacWait extends javax.swing.JPanel {

    private HVV_Constructor theApp;
    private DefaultComboBoxModel cmbObjectModel;
    
    /**
     * Creates new form PnlVacWait
     */
    public PnlVacWait( HVV_Constructor app) {
        initComponents();
        
        theApp = app;
        cmbObjectModel = new DefaultComboBoxModel();
        HVV_VacuumDevices.getInstance().fillComboWait( cmbObjectModel);
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

        edtSetValue = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbObject = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edtSetValueError = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbApproximation = new javax.swing.JComboBox();

        setMaximumSize(new java.awt.Dimension(900, 400));
        setMinimumSize(new java.awt.Dimension(900, 400));
        setPreferredSize(new java.awt.Dimension(900, 400));
        setLayout(null);
        add(edtSetValue);
        edtSetValue.setBounds(30, 170, 150, 40);

        jLabel1.setText("Ожидаемое значение:");
        add(jLabel1);
        jLabel1.setBounds(30, 130, 170, 30);

        cmbObject.setMaximumRowCount(15);
        cmbObject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "002. Насос турбомолекулярный (обороты)", "003. Откачная станция (обороты)", "04А. Датчик вакуума MicroPirani (на форвакууумном насосе)", "04B. Датчик вакуума MicroPirani (на форвакуумном баллоне)", "04C. Датчик вакуума MicroPirani (на откачной станции)", "005. Вакууметр DualMag (на турбомолекулярном насосе)", "006. Baratron 750B (камера приборов)", "007. Baratron 627A (гребёнка)", "16A. Регулятор расхода газа MKS 1179 (O2)", "16B. Регулятор расхода газа MKS 1179 (Ne20)", "16C. Регулятор расхода газа MKS 1179 (He4)", "17A. Регулятор расхода газа MKS M330B (Ne20+22)", "17B. Регулятор расхода газа MKS M330B (He3)", "018. Регулятор расхода газа MKS 1480A (N2)" }));
        add(cmbObject);
        cmbObject.setBounds(30, 70, 840, 40);

        jLabel2.setText("Ожидать показаний от объекта:");
        add(jLabel2);
        jLabel2.setBounds(30, 30, 410, 30);

        jLabel3.setText("Приближение:");
        add(jLabel3);
        jLabel3.setBounds(30, 240, 170, 30);
        add(edtSetValueError);
        edtSetValueError.setBounds(220, 170, 150, 40);

        jLabel4.setText("Погрешность:");
        add(jLabel4);
        jLabel4.setBounds(220, 130, 170, 30);

        cmbApproximation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Неважно", "Сверху", "Снизу" }));
        add(cmbApproximation);
        cmbApproximation.setBounds(30, 280, 150, 40);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox cmbApproximation;
    public javax.swing.JComboBox cmbObject;
    public javax.swing.JTextField edtSetValue;
    public javax.swing.JTextField edtSetValueError;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
