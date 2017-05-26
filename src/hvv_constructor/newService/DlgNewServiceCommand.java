/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_constructor.newService;

import JProg.JProgAStatement;
import JProg.service.JProgServAdminStep;
import JProg.service.JProgServMessageStatement;
import JProg.service.JProgServPauseStatement;
import JProg.service.JProgServSoundStatement;
import hvv_constructor.HVV_Constructor;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class DlgNewServiceCommand extends javax.swing.JDialog {

    PnlServPause pnlServPause;
    PnlServSound pnlServSound;
    PnlServMessage pnlServMessage;
    PnlServAdminStep pnlServAdminStep;
    
    private HVV_Constructor theApp;
    
    static Logger logger = Logger.getLogger(HVV_Constructor.class);
    
    private int m_nEditLineNumber;
    private JProgAStatement m_pEditStatement;
            
    /**
     * Creates new form DlgNewServiceCommand
     */
    public DlgNewServiceCommand( java.awt.Frame parent, boolean modal, HVV_Constructor app, JProgAStatement statement, int nLineNumber) {
        super(parent, modal);
        initComponents();
        
        theApp = app;
        
        pnlServPause = new PnlServPause();
        pnlServSound = new PnlServSound( app);
        pnlServMessage = new PnlServMessage();
        pnlServAdminStep = new PnlServAdminStep();
        
        jPanel1.add( pnlServPause);
        pnlServPause.setBounds( 2, 2, 900, 300);
        jPanel1.add( pnlServSound);
        pnlServSound.setBounds( 2, 2, 900, 300);
        jPanel1.add( pnlServMessage);
        pnlServMessage.setBounds( 2, 2, 900, 350);
        jPanel1.add( pnlServAdminStep);
        pnlServAdminStep.setBounds( 2, 2, 900, 350);
        
        edtLineNumber.setText( "" + nLineNumber);
        
        if( statement != null && statement.getStatementRoughType() == JProg.JProgAStatement.STATEMENT_TYPE_SERVICE) {
            setTitle( "Редактирование сервисной команды");
            btnOk.setText( "Сохранить");    
            
            m_nEditLineNumber = nLineNumber;
            m_pEditStatement = statement;
            
            switch( statement.getStatementType()) {
                case JProg.JProgAStatement.STATEMENT_TYPE_SERVICE_MESSAGE: {
                    logger.debug( "MESSAGE type of service.object to edit");
                    tglMessage.setSelected( true);
                    pnlServPause.setVisible( false);
                    pnlServSound.setVisible( false);
                    pnlServMessage.setVisible( true);
                    pnlServAdminStep.setVisible( false);
                    JProgServMessageStatement stat = ( JProgServMessageStatement) statement; 
                    pnlServMessage.edtMessage.setText( stat.GetMessage());
                    pnlServMessage.edtWavSound.setText( stat.GetSoundWavFile());
                    pnlServMessage.edtWavPeriodic.setText( stat.GetReminderWav());
                    pnlServMessage.edtPeriod.setText( "" + stat.GetReminderPeriod());
                }
                break;
                case JProg.JProgAStatement.STATEMENT_TYPE_SERVICE_SOUND: {
                    logger.debug( "SOUND type of service.object to edit");
                    tglSound.setSelected( true);
                    pnlServPause.setVisible( false);
                    pnlServSound.setVisible( true);
                    pnlServMessage.setVisible( false);
                    pnlServAdminStep.setVisible( false);
                    JProgServSoundStatement stat = ( JProgServSoundStatement) statement; 
                    pnlServSound.edtWavFile.setText( stat.GetWavFile());
                }
                break;
                case JProg.JProgAStatement.STATEMENT_TYPE_SERVICE_PAUSE: {
                    logger.debug( "PAUSE type of service.object to edit");
                    tglPause.setSelected( true);
                    pnlServPause.setVisible( true);
                    pnlServSound.setVisible( false);
                    pnlServMessage.setVisible( false);
                    pnlServAdminStep.setVisible( false);
                    JProgServPauseStatement stat = ( JProgServPauseStatement) statement;
                    pnlServPause.m_lPauseMillis = stat.GetDuration();
                    pnlServPause.ActualizeFields();
                }
                break;
                case JProg.JProgAStatement.STATEMENT_TYPE_SERVICE_ADMIN_STEP: {
                    logger.debug( "ADMIN_STEP type of service.object to edit");
                    tglAdminStep.setSelected( true);
                    pnlServPause.setVisible( false);
                    pnlServSound.setVisible( false);
                    pnlServMessage.setVisible( false);
                    pnlServAdminStep.setVisible( true);
                    JProgServAdminStep stat = ( JProgServAdminStep) statement; 
                    
                    for( int i = 0; i < pnlServAdminStep.cmbSteps.getItemCount(); i++) {
                        String strItem = ( String) pnlServAdminStep.cmbSteps.getItemAt(i);
                        if( strItem.contains( stat.GetFinishedStep())) {
                            pnlServAdminStep.cmbSteps.setSelectedIndex(i);
                            break;
                        }
                    }
                }
                break;
                default:
                    logger.warn( "Strange type of service.object to edit");
                    pnlServPause.setVisible( true);
                    pnlServSound.setVisible( false);
                    pnlServMessage.setVisible( false);
                break;
            }
        }
        else {
            m_nEditLineNumber = -1;
            m_pEditStatement = null;
    
            pnlServPause.setVisible( true);
            pnlServPause.ActualizeFields();
            
            pnlServSound.setVisible( false);
            
            pnlServMessage.setVisible( false);
            
            pnlServAdminStep.setVisible( false);
            
            setTitle( "Добавление сервисной команды");
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

        btnGTab = new javax.swing.ButtonGroup();
        lblLineNumber = new javax.swing.JLabel();
        edtLineNumber = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        tglPause = new javax.swing.JToggleButton();
        tglSound = new javax.swing.JToggleButton();
        tglMessage = new javax.swing.JToggleButton();
        tglAdminStep = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(null);

        lblLineNumber.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblLineNumber.setText("Строка:");
        getContentPane().add(lblLineNumber);
        lblLineNumber.setBounds(12, 12, 74, 35);

        edtLineNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        getContentPane().add(edtLineNumber);
        edtLineNumber.setBounds(98, 12, 100, 35);

        btnOk.setText("Добавить");
        btnOk.setToolTipText("");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        getContentPane().add(btnOk);
        btnOk.setBounds(660, 500, 150, 53);

        btnCancel.setText("Отмена");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel);
        btnCancel.setBounds(820, 500, 150, 53);

        btnGTab.add(tglPause);
        tglPause.setSelected(true);
        tglPause.setText("Пауза");
        tglPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglPauseActionPerformed(evt);
            }
        });
        getContentPane().add(tglPause);
        tglPause.setBounds(25, 60, 200, 40);

        btnGTab.add(tglSound);
        tglSound.setText("Звуковой сигнал");
        tglSound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglSoundActionPerformed(evt);
            }
        });
        getContentPane().add(tglSound);
        tglSound.setBounds(270, 60, 200, 40);

        btnGTab.add(tglMessage);
        tglMessage.setText("Сообщение");
        tglMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglMessageActionPerformed(evt);
            }
        });
        getContentPane().add(tglMessage);
        tglMessage.setBounds(520, 60, 200, 40);

        btnGTab.add(tglAdminStep);
        tglAdminStep.setText("Завершение этапа");
        tglAdminStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglAdminStepActionPerformed(evt);
            }
        });
        getContentPane().add(tglAdminStep);
        tglAdminStep.setBounds(770, 60, 200, 40);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(30, 110, 940, 370);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tglSoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglSoundActionPerformed
        pnlServPause.setVisible( false);
        pnlServSound.setVisible( true);
        pnlServMessage.setVisible( false);
        pnlServAdminStep.setVisible( false);
    }//GEN-LAST:event_tglSoundActionPerformed

    private void tglPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglPauseActionPerformed
        pnlServPause.setVisible( true);
        pnlServSound.setVisible( false);
        pnlServMessage.setVisible( false);
        pnlServAdminStep.setVisible( false);
    }//GEN-LAST:event_tglPauseActionPerformed

    private void tglMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglMessageActionPerformed
        pnlServPause.setVisible( false);
        pnlServSound.setVisible( false);
        pnlServMessage.setVisible( true);
        pnlServAdminStep.setVisible( false);
    }//GEN-LAST:event_tglMessageActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        String strLineNumber = edtLineNumber.getText();
        if( strLineNumber.isEmpty()) {
            HVV_Constructor.MessageBoxInfo( "Введите номер строки команды!", "Неправильный параметр");
            return;
        }
        
        //проверка что в качестве строки программы ввели число
        int nLineNumber = 0;
        try {
            nLineNumber = Integer.parseInt( strLineNumber);
        } catch( NumberFormatException ex) {
            HVV_Constructor.MessageBoxInfo( "Введённый номер строки \"" + strLineNumber + "\" не является числом!", "Неправильный параметр");
            return;
        }
        
        if( m_pEditStatement != null) {
            //мы редактировали старую statement
            if( !strLineNumber.equals( m_nEditLineNumber)) {
                //мы поменяли номер строки! надо удалить старую!
                theApp.m_program.remove( m_nEditLineNumber);
            }
            
            if( tglMessage.isSelected()) {
                int nPeriod = 0;
                try {
                    nPeriod = Integer.parseInt( pnlServMessage.edtPeriod.getText());
                } catch( NumberFormatException ex) {
                    logger.warn( "Period integer value parse failed!", ex);
                }        
                JProgServMessageStatement item = new JProgServMessageStatement(
                        pnlServMessage.edtMessage.getText(),
                        pnlServMessage.edtWavSound.getText(),
                        pnlServMessage.edtWavPeriodic.getText(),
                        nPeriod);
                theApp.m_program.put( nLineNumber, item);
            }
            else if( tglPause.isSelected()) {
                JProgServPauseStatement item = new JProgServPauseStatement( Integer.parseInt( pnlServPause.edtDuration.getText()));
                theApp.m_program.put( nLineNumber, item);
            }
            else if( tglSound.isSelected()) {
                JProgServSoundStatement item = new JProgServSoundStatement( pnlServSound.edtWavFile.getText());
                theApp.m_program.put( nLineNumber, item);
            }
            else if( tglAdminStep.isSelected()) {
                String strSelectedStep = ( String) pnlServAdminStep.cmbSteps.getSelectedItem();
                int nIndx = strSelectedStep.indexOf( ' ');
                String strAdminStep = strSelectedStep.substring( 0, nIndx);
                JProgServAdminStep item = new JProgServAdminStep( strAdminStep);
                theApp.m_program.put( nLineNumber, item);
            }
        }
        else {
            //мы создавали новый statement
            
            
        
            /*
            if( nLineNumber == 0) {
                ConstrApp.MessageBoxInfo( "Неправильный параметр", "Введённый номер строки <b>" + strLineNumber + "</b> уже существует в программе!");
                return;
            }
        
            /*
            if( nLineNumber == 0) {
                ConstrApp.MessageBoxInfo( "Неправильный параметр", "Введённый номер строки <b>" + strLineNumber + "</b> уже существует в программе!");
                return;
            }
            */
        
        
            //проверка что такой строки не существует
            if( theApp.m_program.containsKey( nLineNumber) == true) {
                HVV_Constructor.MessageBoxInfo( "Введённый номер строки \"" + strLineNumber + "\" уже существует в программе!", "Неправильный параметр");
                return;
            }
            
            if( tglMessage.isSelected()) {
                int nPeriod = 0;
                try {
                    String strEdtPeriod = pnlServMessage.edtPeriod.getText();
                    if( strEdtPeriod != null && strEdtPeriod.isEmpty() == false)
                        nPeriod = Integer.parseInt( strEdtPeriod);
                    else
                        nPeriod = 0;
                } catch( NumberFormatException ex) {
                    logger.warn( "Period integer value parse failed!", ex);
                }  
                JProgServMessageStatement item = new JProgServMessageStatement(
                        pnlServMessage.edtMessage.getText(),
                        pnlServMessage.edtWavSound.getText(),
                        pnlServMessage.edtWavPeriodic.getText(),
                        nPeriod);
                theApp.m_program.put( nLineNumber, item);
            }
            else if( tglPause.isSelected()) {
                int nDuration = 0;
                try {
                    nDuration = Integer.parseInt( pnlServPause.edtDuration.getText());
                } catch( NumberFormatException ex) {
                    HVV_Constructor.MessageBoxInfo( "Введённая длительность паузы \"" + pnlServPause.edtDuration.getText() + "\" не является числом!", "Неправильный параметр");
                    return;
                }
                
                JProgServPauseStatement item = new JProgServPauseStatement( Integer.parseInt( pnlServPause.edtDuration.getText()));
                theApp.m_program.put( nLineNumber, item);
            }
            else if( tglSound.isSelected()) {
                String strWavFile = pnlServSound.edtWavFile.getText();
                if( strWavFile.isEmpty()) {
                    HVV_Constructor.MessageBoxInfo( "Не указан wav-файл для проигрывания!", "Неправильный параметр");
                    return;
                }
                JProgServSoundStatement item = new JProgServSoundStatement( pnlServSound.edtWavFile.getText());
                theApp.m_program.put( nLineNumber, item);
            }
            else if( tglAdminStep.isSelected()) {
                String strSelectedStep = ( String) pnlServAdminStep.cmbSteps.getSelectedItem();
                int nIndx = strSelectedStep.indexOf( ' ');
                String strAdminStep = strSelectedStep.substring( 0, nIndx);
                JProgServAdminStep item = new JProgServAdminStep( strAdminStep);
                theApp.m_program.put( nLineNumber, item);
            }
        }
        
        dispose();
        theApp.m_pMainWnd.ShowProgram();
    }//GEN-LAST:event_btnOkActionPerformed

    private void tglAdminStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglAdminStepActionPerformed
        pnlServPause.setVisible( false);
        pnlServSound.setVisible( false);
        pnlServMessage.setVisible( false);
        pnlServAdminStep.setVisible( true);
    }//GEN-LAST:event_tglAdminStepActionPerformed

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
            java.util.logging.Logger.getLogger(DlgNewServiceCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgNewServiceCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgNewServiceCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgNewServiceCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgNewServiceCommand dialog = new DlgNewServiceCommand(new javax.swing.JFrame(), true, null, null, 10);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.ButtonGroup btnGTab;
    private javax.swing.JButton btnOk;
    private javax.swing.JTextField edtLineNumber;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLineNumber;
    private javax.swing.JToggleButton tglAdminStep;
    private javax.swing.JToggleButton tglMessage;
    private javax.swing.JToggleButton tglPause;
    private javax.swing.JToggleButton tglSound;
    // End of variables declaration//GEN-END:variables
}
