/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_constructor.newVacuum;

import JProg.JProgAStatement;
import static JProg.JProgAStatement.STATEMENT_TYPE_VACUUM_CLOSE;
import static JProg.JProgAStatement.STATEMENT_TYPE_VACUUM_OPEN;
import static JProg.JProgAStatement.STATEMENT_TYPE_VACUUM_SET;
import static JProg.JProgAStatement.STATEMENT_TYPE_VACUUM_WAIT;
import JProg.vacuum.JProgVacuumClose;
import JProg.vacuum.JProgVacuumOpen;
import JProg.vacuum.JProgVacuumSet;
import JProg.vacuum.JProgVacuumTurnOff;
import JProg.vacuum.JProgVacuumTurnOn;
import JProg.vacuum.JProgVacuumWait;
import hvv_constructor.HVV_Constructor;
import hvv_devices.HVV_VacuumDevice;
import hvv_devices.HVV_VacuumDevices;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class DlgNewVacCommand extends javax.swing.JDialog {

    PnlVacOpen pnlVacOpen;
    PnlVacClose pnlVacClose;
    PnlVacTurnOn pnlVacTurnOn;
    PnlVacTurnOff pnlVacTurnOff;
    PnlVacSet pnlVacSet;
    PnlVacWait pnlVacWait;
    
    private HVV_Constructor theApp;
    
    static Logger logger = Logger.getLogger( DlgNewVacCommand.class);
    
    private int m_nEditLineNumber;
    private JProgAStatement m_pEditStatement;
    
    /**
     * Creates new form DlgNewVacCommand2
     */
    public DlgNewVacCommand(java.awt.Frame parent, boolean modal, HVV_Constructor app, JProgAStatement statement, int nLineNumber) {
        super( parent, modal);
        initComponents();
        
        theApp = app;
        
        pnlVacOpen = new PnlVacOpen( theApp);
        pnlVacClose = new PnlVacClose( theApp);
        pnlVacTurnOn = new PnlVacTurnOn( theApp);
        pnlVacTurnOff = new PnlVacTurnOff( theApp);
        pnlVacSet = new PnlVacSet( theApp);
        pnlVacWait = new PnlVacWait( theApp);
        
        pnlTabPanel.add( pnlVacOpen);
        pnlVacOpen.setBounds( 2, 2, 900, 300);
        pnlTabPanel.add( pnlVacClose);
        pnlVacClose.setBounds( 2, 2, 900, 300);
        pnlTabPanel.add( pnlVacTurnOn);
        pnlVacTurnOn.setBounds( 2, 2, 950, 300);
        pnlTabPanel.add( pnlVacTurnOff);
        pnlVacTurnOff.setBounds( 2, 2, 950, 300);
        pnlTabPanel.add( pnlVacSet);
        pnlVacSet.setBounds( 2, 2, 950, 300);
        pnlTabPanel.add( pnlVacWait);
        pnlVacWait.setBounds( 2, 2, 950, 350);
        
        edtLineNumber.setText( "" + nLineNumber);
        
        if( statement != null && statement.getStatementRoughType() == JProg.JProgAStatement.STATEMENT_TYPE_VACUUM) {
            setTitle( "Редактирование команды для вакуумной части поста");
            btnOk.setText( "Сохранить");    
            
            m_nEditLineNumber = nLineNumber;
            m_pEditStatement = statement;
            
            switch( statement.getStatementType()) {
                case JProg.JProgAStatement.STATEMENT_TYPE_VACUUM_TURN_ON: {
                    logger.debug( "TURN_ON type of vacuum.object to edit");
                    
                    pnlVacOpen.setVisible( false);
                    pnlVacClose.setVisible( false);
                    pnlVacTurnOn.setVisible( true);
                    pnlVacTurnOff.setVisible( false);
                    pnlVacSet.setVisible( false);
                    pnlVacWait.setVisible( false);
            
                    JProgVacuumTurnOn stat = ( JProgVacuumTurnOn) statement;
                    pnlVacTurnOn.cmbObject.getModel().setSelectedItem( stat.GetOperableDevice());
                    
                    tglTurnOn.setSelected( true);
                }
                break;
                    
                case JProg.JProgAStatement.STATEMENT_TYPE_VACUUM_TURN_OFF: {
                    logger.debug( "TURN_OFF type of vacuum.object to edit");
                    
                    pnlVacOpen.setVisible( false);
                    pnlVacClose.setVisible( false);
                    pnlVacTurnOn.setVisible( false);
                    pnlVacTurnOff.setVisible( true);
                    pnlVacSet.setVisible( false);
                    pnlVacWait.setVisible( false);
            
                    JProgVacuumTurnOff stat = ( JProgVacuumTurnOff) statement;
                    pnlVacTurnOff.cmbObject.getModel().setSelectedItem( stat.GetOperableDevice());
                    
                    tglTurnOff.setSelected( true);
                }
                break;
                    
                case JProg.JProgAStatement.STATEMENT_TYPE_VACUUM_OPEN: {
                    logger.debug( "OPEN type of vacuum.object to edit");
                    
                    pnlVacOpen.setVisible( true);
                    pnlVacClose.setVisible( false);
                    pnlVacTurnOn.setVisible( false);
                    pnlVacTurnOff.setVisible( false);
                    pnlVacSet.setVisible( false);
                    pnlVacWait.setVisible( false);
            
                    JProgVacuumOpen stat = ( JProgVacuumOpen) statement;
                    pnlVacOpen.cmbObject.getModel().setSelectedItem( stat.GetOperableDevice());
                    
                    tglOpen.setSelected( true);
                }
                break;
                    
                case JProg.JProgAStatement.STATEMENT_TYPE_VACUUM_CLOSE: {
                    logger.debug( "CLOSE type of vacuum.object to edit");
                    
                    pnlVacOpen.setVisible( false);
                    pnlVacClose.setVisible( true);
                    pnlVacTurnOn.setVisible( false);
                    pnlVacTurnOff.setVisible( false);
                    pnlVacSet.setVisible( false);
                    pnlVacWait.setVisible( false);
            
                    JProgVacuumClose stat = ( JProgVacuumClose) statement;
                    pnlVacClose.cmbObject.getModel().setSelectedItem( stat.GetOperableDevice());
                    
                    tglClose.setSelected( true);
                }
                break;
                    
                case JProg.JProgAStatement.STATEMENT_TYPE_VACUUM_WAIT: {
                    logger.debug( "WAIT type of vacuum.object to edit");
                    
                    pnlVacOpen.setVisible( false);
                    pnlVacClose.setVisible( false);
                    pnlVacTurnOn.setVisible( false);
                    pnlVacTurnOff.setVisible( false);
                    pnlVacSet.setVisible( false);
                    pnlVacWait.setVisible( true);
            
                    JProgVacuumWait stat = ( JProgVacuumWait) statement;
                    
                    HVV_VacuumDevice dev = stat.GetOperableDevice();
                    String strParameterKey = stat.getExpectedParam();
                    String strParameterName = ( String) dev.m_mapParameters.get( stat.getExpectedParam());
                    String strToSelect = dev.m_strID + "." + dev.m_strName + "." + strParameterKey + "." + strParameterName;
                    pnlVacWait.cmbObject.getModel().setSelectedItem( strToSelect);
                    
                    
                    
                    pnlVacWait.edtSetValue.setText( "" + stat.getExpected());
                    pnlVacWait.edtSetValueError.setText( "" + stat.getError());
                    switch( stat.getApproximation()) {
                        case JProgVacuumWait.VAC_WAIT_APPROACH_FROM_NEVERMIND: pnlVacWait.cmbApproximation.setSelectedIndex( 0); break;
                        case JProgVacuumWait.VAC_WAIT_APPROACH_FROM_UPSIDE: pnlVacWait.cmbApproximation.setSelectedIndex( 1); break;
                        case JProgVacuumWait.VAC_WAIT_APPROACH_FROM_DOWNSIDE: pnlVacWait.cmbApproximation.setSelectedIndex( 2); break;
                    }
                    
                            
                    tglWait.setSelected( true);
                }
                break;
                    
                case JProg.JProgAStatement.STATEMENT_TYPE_VACUUM_SET: {
                    logger.debug( "SET type of vacuum.object to edit");
                    
                    pnlVacOpen.setVisible( false);
                    pnlVacClose.setVisible( false);
                    pnlVacTurnOn.setVisible( false);
                    pnlVacTurnOff.setVisible( false);
                    pnlVacSet.setVisible( true);
                    pnlVacWait.setVisible( false);
            
                    JProgVacuumSet stat = ( JProgVacuumSet) statement;
                    
                    String strDevID = stat.GetOperableDevice().getID();
                    String strDevName = stat.GetOperableDevice().m_strName;
                    String strParamKey = stat.getSetParam();
                    String strParamName = ( String) stat.GetOperableDevice().m_mapParameters.get( strParamKey);
                    
                    String strToSelect = strDevID + "." + strDevName + "." + strParamKey + "." + strParamName;                        
                    pnlVacSet.cmbObject.getModel().setSelectedItem( strToSelect);
                    
                    pnlVacSet.edtSetValue.setText( "" + stat.getSetValue());
                    tglSet.setSelected( true);
                }
                break;
                    
                default:
                    logger.warn( "Strange type of service.object to edit");
                    
                    pnlVacOpen.setVisible( true);
                    pnlVacClose.setVisible( false);
                    pnlVacTurnOn.setVisible( false);
                    pnlVacTurnOff.setVisible( false);
                    pnlVacSet.setVisible( false);
                    pnlVacWait.setVisible( false);
                break;
            }
        }
        else {
            m_nEditLineNumber = -1;
            m_pEditStatement = null;
    
            pnlVacOpen.setVisible( true);
            pnlVacClose.setVisible( false);
            pnlVacTurnOn.setVisible( false);
            pnlVacTurnOff.setVisible( false);
            pnlVacSet.setVisible( false);
            pnlVacWait.setVisible( false);
            
            setTitle( "Добавление команды для вакуумной части поста");
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
        btnCancel = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        tglSet = new javax.swing.JToggleButton();
        tglOpen = new javax.swing.JToggleButton();
        tglClose = new javax.swing.JToggleButton();
        tglWait = new javax.swing.JToggleButton();
        tglTurnOn = new javax.swing.JToggleButton();
        tglTurnOff = new javax.swing.JToggleButton();
        pnlTabPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Добавление команды для вакуумной части поста");
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setModal(true);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        setResizable(false);
        getContentPane().setLayout(null);

        lblLineNumber.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblLineNumber.setText("Строка:");
        getContentPane().add(lblLineNumber);
        lblLineNumber.setBounds(12, 12, 74, 35);

        edtLineNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        getContentPane().add(edtLineNumber);
        edtLineNumber.setBounds(98, 12, 100, 35);

        btnCancel.setText("Отмена");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel);
        btnCancel.setBounds(840, 510, 150, 50);

        btnOk.setText("Добавить");
        btnOk.setToolTipText("");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        getContentPane().add(btnOk);
        btnOk.setBounds(680, 510, 150, 50);

        btnGTab.add(tglSet);
        tglSet.setText("Задать");
        tglSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglSetActionPerformed(evt);
            }
        });
        getContentPane().add(tglSet);
        tglSet.setBounds(670, 60, 150, 60);

        btnGTab.add(tglOpen);
        tglOpen.setSelected(true);
        tglOpen.setText("Открыть");
        tglOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglOpenActionPerformed(evt);
            }
        });
        getContentPane().add(tglOpen);
        tglOpen.setBounds(20, 60, 150, 60);

        btnGTab.add(tglClose);
        tglClose.setText("Закрыть");
        tglClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglCloseActionPerformed(evt);
            }
        });
        getContentPane().add(tglClose);
        tglClose.setBounds(190, 60, 150, 60);

        btnGTab.add(tglWait);
        tglWait.setText("Ожидание");
        tglWait.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglWaitActionPerformed(evt);
            }
        });
        getContentPane().add(tglWait);
        tglWait.setBounds(830, 60, 150, 60);

        btnGTab.add(tglTurnOn);
        tglTurnOn.setText("Включить");
        tglTurnOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglTurnOnActionPerformed(evt);
            }
        });
        getContentPane().add(tglTurnOn);
        tglTurnOn.setBounds(350, 60, 150, 60);

        btnGTab.add(tglTurnOff);
        tglTurnOff.setText("Выключить");
        tglTurnOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglTurnOffActionPerformed(evt);
            }
        });
        getContentPane().add(tglTurnOff);
        tglTurnOff.setBounds(510, 60, 150, 60);
        tglTurnOff.getAccessibleContext().setAccessibleDescription("");

        pnlTabPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlTabPanelLayout = new javax.swing.GroupLayout(pnlTabPanel);
        pnlTabPanel.setLayout(pnlTabPanelLayout);
        pnlTabPanelLayout.setHorizontalGroup(
            pnlTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 968, Short.MAX_VALUE)
        );
        pnlTabPanelLayout.setVerticalGroup(
            pnlTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );

        getContentPane().add(pnlTabPanel);
        pnlTabPanel.setBounds(20, 130, 970, 370);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tglOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglOpenActionPerformed
        pnlVacOpen.setVisible( true);
        pnlVacClose.setVisible( false);
        pnlVacTurnOn.setVisible( false);
        pnlVacTurnOff.setVisible( false);
        pnlVacSet.setVisible( false);
        pnlVacWait.setVisible( false);
    }//GEN-LAST:event_tglOpenActionPerformed

    private void tglCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglCloseActionPerformed
        pnlVacOpen.setVisible( false);
        pnlVacClose.setVisible( true);
        pnlVacTurnOn.setVisible( false);
        pnlVacTurnOff.setVisible( false);
        pnlVacSet.setVisible( false);
        pnlVacWait.setVisible( false);
    }//GEN-LAST:event_tglCloseActionPerformed

    private void tglTurnOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglTurnOnActionPerformed
        pnlVacOpen.setVisible( false);
        pnlVacClose.setVisible( false);
        pnlVacTurnOn.setVisible( true);
        pnlVacTurnOff.setVisible( false);
        pnlVacSet.setVisible( false);
        pnlVacWait.setVisible( false);
    }//GEN-LAST:event_tglTurnOnActionPerformed

    private void tglTurnOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglTurnOffActionPerformed
        pnlVacOpen.setVisible( false);
        pnlVacClose.setVisible( false);
        pnlVacTurnOn.setVisible( false);
        pnlVacTurnOff.setVisible( true);
        pnlVacSet.setVisible( false);
        pnlVacWait.setVisible( false);
    }//GEN-LAST:event_tglTurnOffActionPerformed

    private void tglSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglSetActionPerformed
        pnlVacOpen.setVisible( false);
        pnlVacClose.setVisible( false);
        pnlVacTurnOn.setVisible( false);
        pnlVacTurnOff.setVisible( false);
        pnlVacSet.setVisible( true);
        pnlVacWait.setVisible( false);
    }//GEN-LAST:event_tglSetActionPerformed

    private void tglWaitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglWaitActionPerformed
        pnlVacOpen.setVisible( false);
        pnlVacClose.setVisible( false);
        pnlVacTurnOn.setVisible( false);
        pnlVacTurnOff.setVisible( false);
        pnlVacSet.setVisible( false);
        pnlVacWait.setVisible( true);
    }//GEN-LAST:event_tglWaitActionPerformed

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
            HVV_Constructor.MessageBoxInfo( "Введённый номер строки \"" + nLineNumber + "\" не является числом!", "Неправильный параметр");
            return;
        }
        
        if( m_pEditStatement != null) {
            //мы редактировали старую statement
            if( !strLineNumber.equals( m_nEditLineNumber)) {
                //мы поменяли номер строки! надо удалить старую!
                theApp.m_program.remove( m_nEditLineNumber);
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
        }
    
        if( tglOpen.isSelected()) {
            int nIndex = pnlVacOpen.cmbObject.getSelectedIndex();
            Object obj = pnlVacOpen.cmbObject.getModel().getElementAt( nIndex);
            JProgVacuumOpen statement = new JProgVacuumOpen(
                    ( HVV_VacuumDevice) obj);
            
            theApp.m_program.put( nLineNumber, statement);
        }
        else if( tglClose.isSelected()) {
            Object obj = pnlVacClose.cmbObject.getSelectedItem();
            JProgVacuumClose statement = new JProgVacuumClose(
                    ( HVV_VacuumDevice) obj);

            theApp.m_program.put( nLineNumber, statement);
        }
        else if( tglTurnOn.isSelected()) {
            JProgVacuumTurnOn statement = new JProgVacuumTurnOn(
                    ( HVV_VacuumDevice) pnlVacTurnOn.cmbObject.getSelectedItem());
            
            theApp.m_program.put( nLineNumber, statement);
        }
        else if( tglTurnOff.isSelected()) {
            JProgVacuumTurnOff statement = new JProgVacuumTurnOff(
                    ( HVV_VacuumDevice) pnlVacTurnOff.cmbObject.getSelectedItem());
            
                
            theApp.m_program.put( nLineNumber, statement);
        }
        else if( tglSet.isSelected()) {
            //PnlVacSet pnlVacSet;
            double dblSetValue = 0.;
            try {
                dblSetValue = Double.parseDouble( pnlVacSet.edtSetValue.getText());
            } catch( NumberFormatException ex) {
                HVV_Constructor.MessageBoxInfo( "Введённое значение для уставки \"" + pnlVacSet.edtSetValue.getText() + "\" не является double-числом!", "Неправильный параметр");
                return;
            }        
            
            String strSelectedParam = ( String) pnlVacSet.cmbObject.getSelectedItem();
            int nPoint1 = strSelectedParam.indexOf( ".", 0);
            int nPoint2 = strSelectedParam.indexOf( ".", nPoint1+1);
            int nPoint3 = strSelectedParam.indexOf( ".", nPoint2+1);
            
            String strDevId = strSelectedParam.substring( 0, nPoint1);
            String strParamId = strSelectedParam.substring( nPoint2 + 1, nPoint3);
            
            JProgVacuumSet statement = new JProgVacuumSet(
                    ( HVV_VacuumDevice) HVV_VacuumDevices.getInstance().m_devices.get( strDevId),
                    strParamId,
                    dblSetValue);
            
                
            theApp.m_program.put( nLineNumber, statement);
        }
        else if( tglWait.isSelected()) {
            //PnlVacWait pnlVacWait;
            
            //ожидаемый параметр
            String strSelectedParam = ( String) pnlVacWait.cmbObject.getSelectedItem();
            int nPoint1 = strSelectedParam.indexOf( ".", 0);
            int nPoint2 = strSelectedParam.indexOf( ".", nPoint1+1);
            int nPoint3 = strSelectedParam.indexOf( ".", nPoint2+1);
            
            String strDevId = strSelectedParam.substring( 0, nPoint1);
            String strParamId = strSelectedParam.substring( nPoint2 + 1, nPoint3);
            
            String strDevWaitParam = strDevId + "." + strParamId;
            
            //ожидаемое значение
            double dblWaitValue = 0.;
            try {
                dblWaitValue = Double.parseDouble( pnlVacWait.edtSetValue.getText());
            } catch( NumberFormatException ex) {
                HVV_Constructor.MessageBoxInfo( "Введённое значение для ожидаемого значения \"" + pnlVacSet.edtSetValue.getText() + "\" не является double-числом!", "Неправильный параметр");
                return;
            }
            
            //ошибка
            double dblWaitValueError = 0.;
            try {
                dblWaitValueError = Double.parseDouble( pnlVacWait.edtSetValueError.getText());
            } catch( NumberFormatException ex) {
                HVV_Constructor.MessageBoxInfo( "Введённое значение для погрешности ожидаемого значения \"" + pnlVacSet.edtSetValue.getText() + "\" не является double-числом!", "Неправильный параметр");
                return;
            }
            
            int nApproximation = JProgVacuumWait.VAC_WAIT_APPROACH_FROM_NEVERMIND;
            switch( pnlVacWait.cmbApproximation.getSelectedIndex()) {
                case 0: nApproximation = JProgVacuumWait.VAC_WAIT_APPROACH_FROM_NEVERMIND; break;
                case 1: nApproximation = JProgVacuumWait.VAC_WAIT_APPROACH_FROM_UPSIDE; break;
                case 2: nApproximation = JProgVacuumWait.VAC_WAIT_APPROACH_FROM_DOWNSIDE; break;
            }
            
            //String strDevParam, double dblExpected, double dblError, int nApproximation
            
            JProgVacuumWait statement = new JProgVacuumWait( strDevWaitParam, dblWaitValue, dblWaitValueError, nApproximation);
            theApp.m_program.put( nLineNumber, statement);
        }
        
        dispose();
        theApp.m_pMainWnd.ShowProgram();
    }//GEN-LAST:event_btnOkActionPerformed

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
            java.util.logging.Logger.getLogger(DlgNewVacCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgNewVacCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgNewVacCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgNewVacCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgNewVacCommand dialog = new DlgNewVacCommand(new javax.swing.JFrame(), true, null, null, 10);
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
    private javax.swing.JLabel lblLineNumber;
    private javax.swing.JPanel pnlTabPanel;
    private javax.swing.JToggleButton tglClose;
    private javax.swing.JToggleButton tglOpen;
    private javax.swing.JToggleButton tglSet;
    private javax.swing.JToggleButton tglTurnOff;
    private javax.swing.JToggleButton tglTurnOn;
    private javax.swing.JToggleButton tglWait;
    // End of variables declaration//GEN-END:variables
}
