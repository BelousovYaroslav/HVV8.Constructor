/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_constructor.newHv;

/**
 *
 * @author yaroslav
 */
public class PnlHVVibration extends javax.swing.JPanel {

    /**
     * Creates new form PnlHVVibration
     */
    public PnlHVVibration() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGVibration = new javax.swing.ButtonGroup();
        radVibrationOn = new javax.swing.JRadioButton();
        radVibrationOff = new javax.swing.JRadioButton();

        setMaximumSize(new java.awt.Dimension(900, 400));
        setMinimumSize(new java.awt.Dimension(900, 400));
        setLayout(null);

        btnGVibration.add(radVibrationOn);
        radVibrationOn.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        radVibrationOn.setSelected(true);
        radVibrationOn.setText("Включить");
        add(radVibrationOn);
        radVibrationOn.setBounds(30, 30, 160, 26);

        btnGVibration.add(radVibrationOff);
        radVibrationOff.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        radVibrationOff.setText("Выключить");
        add(radVibrationOff);
        radVibrationOff.setBounds(30, 80, 160, 26);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGVibration;
    public javax.swing.JRadioButton radVibrationOff;
    public javax.swing.JRadioButton radVibrationOn;
    // End of variables declaration//GEN-END:variables
}
