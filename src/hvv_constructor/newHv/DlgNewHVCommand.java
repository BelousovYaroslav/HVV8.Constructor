/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_constructor.newHv;

import JProg.JProgAStatement;
import JProg.hv.JProgHvChannels;
import JProg.hv.JProgHvFan;
import JProg.hv.JProgHvMainSwitcher;
import JProg.hv.JProgHvPreset;
import JProg.hv.JProgHvVibration;
import hvv_constructor.HVV_Constructor;
import javax.swing.JRadioButton;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class DlgNewHVCommand extends javax.swing.JDialog {

    PnlHVMainSwitcher pnlHvMainSwitcher;
    PnlHVRegime pnlHvRegime;
    PnlHVChannels pnlHvChannels;
    PnlHVVibration pnlHvVibration;
    PnlHVFan pnlHvFan;
    PnlHVPreset pnlHvPreset;
    
    private HVV_Constructor theApp;    
    static Logger logger = Logger.getLogger( DlgNewHVCommand.class);
    
    private int m_nEditLineNumber;
    private JProgAStatement m_pEditStatement;
    
    /**
     * Creates new form DlgNewHVCommand
     */
    public DlgNewHVCommand( java.awt.Frame parent, boolean modal, HVV_Constructor app, JProgAStatement statement, int nLineNumber) {
        super( parent, modal);
        initComponents();
        
        theApp = app;
        
        tglRegime.setVisible( false);
                
        pnlHvMainSwitcher = new PnlHVMainSwitcher();
        pnlHvRegime = new PnlHVRegime();
        pnlHvChannels = new PnlHVChannels();
        pnlHvVibration = new PnlHVVibration();
        pnlHvFan = new PnlHVFan();
        pnlHvPreset = new PnlHVPreset();
        
        pnlTabPanel.add( pnlHvMainSwitcher);
        pnlHvMainSwitcher.setBounds( 2, 2, 900, 300);
        pnlTabPanel.add( pnlHvRegime);
        pnlHvRegime.setBounds( 2, 2, 900, 300);
        pnlTabPanel.add( pnlHvChannels);
        pnlHvChannels.setBounds( 2, 2, 950, 300);
        pnlTabPanel.add( pnlHvVibration);
        pnlHvVibration.setBounds( 2, 2, 950, 300);
        pnlTabPanel.add( pnlHvFan);
        pnlHvFan.setBounds( 2, 2, 950, 300);
        pnlTabPanel.add( pnlHvPreset);
        pnlHvPreset.setBounds( 2, 2, 950, 300);
        
        edtLineNumber.setText( "" + nLineNumber);
        
        if( statement != null && statement.getStatementRoughType() == JProg.JProgAStatement.STATEMENT_TYPE_HV) {
            setTitle( "Редактирование команды для в/в части поста");
            btnOk.setText( "Сохранить");    
            
            m_nEditLineNumber = nLineNumber;
            m_pEditStatement = statement;
            
            switch( statement.getStatementType()) {
                case JProg.JProgAStatement.STATEMENT_TYPE_HV_MAIN_SWITCHER: {
                    logger.debug( "MAIN.SWITCHER type of hv to edit");
                    
                    tglMainSwitcher.setSelected( true);
                    
                    pnlHvMainSwitcher.setVisible( true);
                    pnlHvRegime.setVisible( false);
                    pnlHvChannels.setVisible( false);
                    pnlHvVibration.setVisible( false);
                    pnlHvPreset.setVisible( false);
                    pnlHvFan.setVisible( false);
                    
                    JProgHvMainSwitcher stat = ( JProgHvMainSwitcher) statement; 
                    if( stat.GetState() == JProgHvMainSwitcher.TURN_MAIN_SWITCHER_OFF) {
                        pnlHvMainSwitcher.radMainSwitcherOn.setSelected(  false);
                        pnlHvMainSwitcher.radMainSwitcherOff.setSelected( true);
                    }
                    if( stat.GetState() == JProgHvMainSwitcher.TURN_MAIN_SWITCHER_ON) {
                        pnlHvMainSwitcher.radMainSwitcherOn.setSelected(  false);
                        pnlHvMainSwitcher.radMainSwitcherOff.setSelected( true);
                    }
                    
                }
                break;
                
                case JProg.JProgAStatement.STATEMENT_TYPE_HV_PRESET: {
                    logger.debug( "PRESET type of hv to edit");
                    
                    tglPreset.setSelected( true);
                    
                    pnlHvMainSwitcher.setVisible( false);
                    pnlHvRegime.setVisible( false);
                    pnlHvChannels.setVisible( false);
                    pnlHvVibration.setVisible( false);
                    pnlHvPreset.setVisible( true);
                    pnlHvFan.setVisible( false);
                    
                    JProgHvPreset stat = ( JProgHvPreset) statement; 
                    pnlHvPreset.edtPreset.setText( "" + stat.GetPreset());
                }
                break;

                case JProg.JProgAStatement.STATEMENT_TYPE_HV_CHANNELS: {
                    logger.debug( "CHANNELS type of hv to edit");
                    
                    tglMultipleChannels.setSelected( true);
                    
                    pnlHvMainSwitcher.setVisible( false);
                    pnlHvRegime.setVisible( false);
                    pnlHvChannels.setVisible( true);
                    pnlHvVibration.setVisible( false);
                    pnlHvPreset.setVisible( false);
                    pnlHvFan.setVisible( false);
                    
                    JProgHvChannels stat = ( JProgHvChannels) statement; 
                    
                    //галка "проверить админа"
                    pnlHvChannels.chkCheckAdmin.setSelected( stat.GetCheckAdmin());
                    
                    JRadioButton [] radsA_noChange = {    pnlHvChannels.radTDev1aNoChange, pnlHvChannels.radTDev2aNoChange,
                                                        pnlHvChannels.radTDev3aNoChange, pnlHvChannels.radTDev4aNoChange,
                                                        pnlHvChannels.radTDev5aNoChange, pnlHvChannels.radTDev6aNoChange,
                                                        pnlHvChannels.radTDev7aNoChange, pnlHvChannels.radTDev8aNoChange};
                    
                    JRadioButton [] radsT_noChange = {    pnlHvChannels.radTDev1tNoChange, pnlHvChannels.radTDev2tNoChange,
                                                        pnlHvChannels.radTDev3tNoChange, pnlHvChannels.radTDev4tNoChange,
                                                        pnlHvChannels.radTDev5tNoChange, pnlHvChannels.radTDev6tNoChange,
                                                        pnlHvChannels.radTDev7tNoChange, pnlHvChannels.radTDev8tNoChange};
                    
                    JRadioButton [] radsA_on = {    pnlHvChannels.radTDev1aOn, pnlHvChannels.radTDev2aOn,
                                                        pnlHvChannels.radTDev3aOn, pnlHvChannels.radTDev4aOn,
                                                        pnlHvChannels.radTDev5aOn, pnlHvChannels.radTDev6aOn,
                                                        pnlHvChannels.radTDev7aOn, pnlHvChannels.radTDev8aOn};
                    
                    JRadioButton [] radsT_on = {    pnlHvChannels.radTDev1tOn, pnlHvChannels.radTDev2tOn,
                                                        pnlHvChannels.radTDev3tOn, pnlHvChannels.radTDev4tOn,
                                                        pnlHvChannels.radTDev5tOn, pnlHvChannels.radTDev6tOn,
                                                        pnlHvChannels.radTDev7tOn, pnlHvChannels.radTDev8tOn};
                    
                    JRadioButton [] radsA_off = {    pnlHvChannels.radTDev1aOff, pnlHvChannels.radTDev2aOff,
                                                        pnlHvChannels.radTDev3aOff, pnlHvChannels.radTDev4aOff,
                                                        pnlHvChannels.radTDev5aOff, pnlHvChannels.radTDev6aOff,
                                                        pnlHvChannels.radTDev7aOff, pnlHvChannels.radTDev8aOff};
                    
                    JRadioButton [] radsT_off = {    pnlHvChannels.radTDev1tOff, pnlHvChannels.radTDev2tOff,
                                                        pnlHvChannels.radTDev3tOff, pnlHvChannels.radTDev4tOff,
                                                        pnlHvChannels.radTDev5tOff, pnlHvChannels.radTDev6tOff,
                                                        pnlHvChannels.radTDev7tOff, pnlHvChannels.radTDev8tOff};
                    
                    for( int i=0; i<8; i++) {
                        radsA_noChange[i].setSelected( true);
                        switch( stat.GetDeviceAnodeAction(i)) {
                            //case JProgHvChannels.ACTION_NO_CHANGE:  radsA_noChange[i].setSelected( true);   break;
                            case JProgHvChannels.ACTION_TURN_ON:    radsA_on[i].setSelected( true); break;
                            case JProgHvChannels.ACTION_TURN_OFF:   radsA_off[i].setSelected( true); break;
                        }
                        
                        radsT_noChange[i].setSelected( true);
                        switch( stat.GetDeviceTubulationAction( i)) {
                            //case JProgHvChannels.ACTION_NO_CHANGE:  radsA_noChange[i].setSelected( true); break;
                            case JProgHvChannels.ACTION_TURN_ON:    radsT_on[i].setSelected( true); break;
                            case JProgHvChannels.ACTION_TURN_OFF:   radsT_off[i].setSelected( true); break;
                        }
                        
                    }
                }
                break;
                    
                case JProg.JProgAStatement.STATEMENT_TYPE_HV_VIBRATON: {
                    logger.debug( "VIBRATION type of hv to edit");
                    
                    tglVibration.setSelected( true);
                    
                    pnlHvMainSwitcher.setVisible( false);
                    pnlHvRegime.setVisible( false);
                    pnlHvChannels.setVisible( false);
                    pnlHvVibration.setVisible( true);
                    pnlHvPreset.setVisible( false);
                    pnlHvFan.setVisible( false);
                    
                    JProgHvVibration stat = ( JProgHvVibration) statement;
                    if( stat.GetState() == JProgHvVibration.TURN_VIBRATION_OFF)
                        pnlHvVibration.radVibrationOff.setSelected( true);
                    
                    if( stat.GetState() == JProgHvVibration.TURN_VIBRATION_ON)
                        pnlHvVibration.radVibrationOn.setSelected( true);
                }
                break;
                    
                case JProg.JProgAStatement.STATEMENT_TYPE_HV_FAN: {
                    logger.debug( "FAN type of hv to edit");
                    
                    tglFan.setSelected( true);
                    
                    pnlHvMainSwitcher.setVisible( false);
                    pnlHvRegime.setVisible( false);
                    pnlHvChannels.setVisible( false);
                    pnlHvVibration.setVisible( false);
                    pnlHvPreset.setVisible( false);
                    pnlHvFan.setVisible( true);
                    
                    JProgHvFan stat = ( JProgHvFan) statement;
                    if( stat.GetState() == JProgHvFan.TURN_FAN_OFF)
                        pnlHvFan.radFanOff.setSelected( true);
                    
                    if( stat.GetState() == JProgHvFan.TURN_FAN_ON)
                        pnlHvFan.radFanOn.setSelected( true);
                }
                break;
                    
                default:
                    
                    tglMainSwitcher.setSelected( true);
                    
                    pnlHvMainSwitcher.setVisible( true);
                    pnlHvRegime.setVisible( false);
                    pnlHvChannels.setVisible( false);
                    pnlHvVibration.setVisible( false);
                    pnlHvPreset.setVisible( false);
                    pnlHvFan.setVisible( false);
                    
                    logger.fatal( "TODO rest incoming objects for HV statement edit");
            }
        }
        else {
            m_nEditLineNumber = -1;
            m_pEditStatement = null;
    
            pnlHvMainSwitcher.setVisible( true);
            pnlHvRegime.setVisible( false);
            pnlHvChannels.setVisible( false);
            pnlHvVibration.setVisible( false);
            pnlHvFan.setVisible( false);
            pnlHvPreset.setVisible( false);
        
            setTitle( "Добавление команды для в/в части поста");
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

        radMainSwicther = new javax.swing.ButtonGroup();
        radRegime = new javax.swing.ButtonGroup();
        btnGTab = new javax.swing.ButtonGroup();
        lblLineNumber = new javax.swing.JLabel();
        edtLineNumber = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        tglMainSwitcher = new javax.swing.JToggleButton();
        tglRegime = new javax.swing.JToggleButton();
        tglPreset = new javax.swing.JToggleButton();
        pnlTabPanel = new javax.swing.JPanel();
        tglMultipleChannels = new javax.swing.JToggleButton();
        tglVibration = new javax.swing.JToggleButton();
        tglFan = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Добавление команды для высоковольтной части поста");
        setMinimumSize(new java.awt.Dimension(1000, 600));
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

        btnOk.setText("Добавить");
        btnOk.setToolTipText("");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        getContentPane().add(btnOk);
        btnOk.setBounds(670, 500, 150, 53);

        btnCancel.setText("Отмена");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel);
        btnCancel.setBounds(830, 500, 150, 53);

        btnGTab.add(tglMainSwitcher);
        tglMainSwitcher.setSelected(true);
        tglMainSwitcher.setText("Главный рубильник");
        tglMainSwitcher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglMainSwitcherActionPerformed(evt);
            }
        });
        getContentPane().add(tglMainSwitcher);
        tglMainSwitcher.setBounds(20, 60, 180, 60);

        btnGTab.add(tglRegime);
        tglRegime.setText("Переключить режим");
        tglRegime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglRegimeActionPerformed(evt);
            }
        });
        getContentPane().add(tglRegime);
        tglRegime.setBounds(900, 10, 90, 60);

        btnGTab.add(tglPreset);
        tglPreset.setText("Уставка");
        tglPreset.setToolTipText("");
        tglPreset.setActionCommand("<html><align=\"center\">Задать<br>подключения ЛГ</align></html>");
        tglPreset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tglPreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglPresetActionPerformed(evt);
            }
        });
        getContentPane().add(tglPreset);
        tglPreset.setBounds(200, 60, 180, 60);

        pnlTabPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlTabPanelLayout = new javax.swing.GroupLayout(pnlTabPanel);
        pnlTabPanel.setLayout(pnlTabPanelLayout);
        pnlTabPanelLayout.setHorizontalGroup(
            pnlTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 958, Short.MAX_VALUE)
        );
        pnlTabPanelLayout.setVerticalGroup(
            pnlTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        getContentPane().add(pnlTabPanel);
        pnlTabPanel.setBounds(20, 130, 960, 360);

        btnGTab.add(tglMultipleChannels);
        tglMultipleChannels.setText("<html><center>Задать<br>подключения ЛГ</center></html>");
        tglMultipleChannels.setToolTipText("");
        tglMultipleChannels.setActionCommand("<html><align=\"center\">Задать<br>подключения ЛГ</align></html>");
        tglMultipleChannels.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tglMultipleChannels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglMultipleChannelsActionPerformed(evt);
            }
        });
        getContentPane().add(tglMultipleChannels);
        tglMultipleChannels.setBounds(380, 60, 180, 60);

        btnGTab.add(tglVibration);
        tglVibration.setText("Вибрация");
        tglVibration.setToolTipText("");
        tglVibration.setActionCommand("<html><align=\"center\">Задать<br>подключения ЛГ</align></html>");
        tglVibration.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tglVibration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglVibrationActionPerformed(evt);
            }
        });
        getContentPane().add(tglVibration);
        tglVibration.setBounds(560, 60, 180, 60);

        btnGTab.add(tglFan);
        tglFan.setText("Вентилятор");
        tglFan.setToolTipText("");
        tglFan.setActionCommand("<html><align=\"center\">Задать<br>подключения ЛГ</align></html>");
        tglFan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tglFan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglFanActionPerformed(evt);
            }
        });
        getContentPane().add(tglFan);
        tglFan.setBounds(740, 60, 180, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tglMainSwitcherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglMainSwitcherActionPerformed
        pnlHvMainSwitcher.setVisible( true);
        pnlHvRegime.setVisible( false);
        pnlHvChannels.setVisible( false);
        pnlHvVibration.setVisible( false);
        pnlHvFan.setVisible( false);
        pnlHvPreset.setVisible( false);
    }//GEN-LAST:event_tglMainSwitcherActionPerformed

    private void tglRegimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglRegimeActionPerformed
        pnlHvMainSwitcher.setVisible( false);
        pnlHvRegime.setVisible( true);
        pnlHvChannels.setVisible( false);
        pnlHvVibration.setVisible( false);
        pnlHvFan.setVisible( false);
        pnlHvPreset.setVisible( false);
    }//GEN-LAST:event_tglRegimeActionPerformed

    private void tglPresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglPresetActionPerformed
        pnlHvMainSwitcher.setVisible( false);
        pnlHvRegime.setVisible( false);
        pnlHvChannels.setVisible( false);
        pnlHvVibration.setVisible( false);
        pnlHvFan.setVisible( false);
        pnlHvPreset.setVisible( true);
    }//GEN-LAST:event_tglPresetActionPerformed

    private void tglMultipleChannelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglMultipleChannelsActionPerformed
        pnlHvMainSwitcher.setVisible( false);
        pnlHvRegime.setVisible( false);
        pnlHvChannels.setVisible( true);
        pnlHvVibration.setVisible( false);
        pnlHvFan.setVisible( false);
        pnlHvPreset.setVisible( false);
    }//GEN-LAST:event_tglMultipleChannelsActionPerformed

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
            
        if( tglMainSwitcher.isSelected()) {
            JProgHvMainSwitcher item = new JProgHvMainSwitcher();
            
            if( pnlHvMainSwitcher.radMainSwitcherOn.isSelected())
                item.SetState( JProgHvMainSwitcher.TURN_MAIN_SWITCHER_ON);
            if( pnlHvMainSwitcher.radMainSwitcherOff.isSelected())
                item.SetState( JProgHvMainSwitcher.TURN_MAIN_SWITCHER_OFF);
            
            theApp.m_program.put( nLineNumber, item);
        }
        else if( tglMultipleChannels.isSelected()) {
            JProgHvChannels item = new JProgHvChannels();
            
            //Проверка возможности подачи у административного модуля
            item.SetCheckAdmin( pnlHvChannels.chkCheckAdmin.isSelected());
            
            //DEVICE1.ANODE
            if( pnlHvChannels.radTDev1aNoChange.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE1, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev1aOn.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE1, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev1aOff.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE1, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE1.TUBULATION
            if( pnlHvChannels.radTDev1tNoChange.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE1, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev1tOn.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE1, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev1tOff.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE1, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE2.ANODE
            if( pnlHvChannels.radTDev2aNoChange.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE2, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev2aOn.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE2, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev2aOff.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE2, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE2.TUBULATION
            if( pnlHvChannels.radTDev2tNoChange.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE2, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev2tOn.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE2, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev2tOff.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE2, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE3.ANODE
            if( pnlHvChannels.radTDev3aNoChange.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE3, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev3aOn.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE3, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev3aOff.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE3, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE3.TUBULATION
            if( pnlHvChannels.radTDev3tNoChange.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE3, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev3tOn.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE3, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev3tOff.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE3, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE4.ANODE
            if( pnlHvChannels.radTDev4aNoChange.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE4, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev4aOn.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE4, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev4aOff.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE4, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE4.TUBULATION
            if( pnlHvChannels.radTDev4tNoChange.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE4, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev4tOn.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE4, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev4tOff.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE4, JProgHvChannels.ACTION_TURN_OFF);
            
            
            
            
            //DEVICE5.ANODE
            if( pnlHvChannels.radTDev5aNoChange.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE5, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev5aOn.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE5, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev5aOff.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE5, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE5.TUBULATION
            if( pnlHvChannels.radTDev5tNoChange.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE5, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev5tOn.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE5, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev5tOff.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE5, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE6.ANODE
            if( pnlHvChannels.radTDev6aNoChange.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE6, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev6aOn.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE6, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev6aOff.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE6, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE6.TUBULATION
            if( pnlHvChannels.radTDev6tNoChange.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE6, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev6tOn.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE6, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev6tOff.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE6, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE7.ANODE
            if( pnlHvChannels.radTDev7aNoChange.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE7, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev7aOn.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE7, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev7aOff.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE7, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE7.TUBULATION
            if( pnlHvChannels.radTDev7tNoChange.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE7, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev7tOn.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE7, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev7tOff.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE7, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE8.ANODE
            if( pnlHvChannels.radTDev8aNoChange.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE8, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev8aOn.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE8, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev8aOff.isSelected())
                item.SetDeviceAnodeAction( JProgHvChannels.DEVICE8, JProgHvChannels.ACTION_TURN_OFF);
            
            //DEVICE8.TUBULATION
            if( pnlHvChannels.radTDev8tNoChange.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE8, JProgHvChannels.ACTION_NO_CHANGE);
            else if( pnlHvChannels.radTDev8tOn.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE8, JProgHvChannels.ACTION_TURN_ON);
            else if( pnlHvChannels.radTDev8tOff.isSelected())
                item.SetDeviceTubulationAction( JProgHvChannels.DEVICE8, JProgHvChannels.ACTION_TURN_OFF);
            
            theApp.m_program.put( nLineNumber, item);
        }
        else if( tglRegime.isSelected()) {
            logger.fatal( "TODO: if( tglRegime.isSelected()");
        }
        else if( tglVibration.isSelected()) {
            //************************************************************* VIBRATION
            JProgHvVibration item = new JProgHvVibration();
            if( pnlHvVibration.radVibrationOn.isSelected())
                item.SetAction( JProgHvVibration.TURN_VIBRATION_ON);
            if( pnlHvVibration.radVibrationOff.isSelected())
                item.SetAction( JProgHvVibration.TURN_VIBRATION_OFF);
            
            theApp.m_program.put( nLineNumber, item);
        }
        else if( tglFan.isSelected()) {
            //************************************************************* FAN
            JProgHvFan item = new JProgHvFan();
            if( pnlHvFan.radFanOn.isSelected())
                item.SetAction( JProgHvFan.TURN_FAN_ON);
            if( pnlHvFan.radFanOff.isSelected())
                item.SetAction( JProgHvFan.TURN_FAN_OFF);
            
            theApp.m_program.put( nLineNumber, item);
        }
        else if( tglPreset.isSelected()) {
            JProgHvPreset item = new JProgHvPreset();
            int nPreset = 1000;
            try {
                nPreset = Integer.parseInt( pnlHvPreset.edtPreset.getText());
            }
            catch( NumberFormatException ex) {
                logger.error( "NumberFormatException happens while parsing preset value '" +
                        pnlHvPreset.edtPreset.getText() + "'", ex);
            }
            item.SetPreset( nPreset);

            theApp.m_program.put( nLineNumber, item);
        }
        
        dispose();
        theApp.m_pMainWnd.ShowProgram();
    }//GEN-LAST:event_btnOkActionPerformed

    private void tglVibrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglVibrationActionPerformed
        pnlHvMainSwitcher.setVisible( false);
        pnlHvRegime.setVisible( false);
        pnlHvChannels.setVisible( false);
        pnlHvVibration.setVisible( true);
        pnlHvFan.setVisible( false);
        pnlHvPreset.setVisible( false);
    }//GEN-LAST:event_tglVibrationActionPerformed

    private void tglFanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglFanActionPerformed
        pnlHvMainSwitcher.setVisible( false);
        pnlHvRegime.setVisible( false);
        pnlHvChannels.setVisible( false);
        pnlHvVibration.setVisible( false);
        pnlHvFan.setVisible( true);
        pnlHvPreset.setVisible( false);
    }//GEN-LAST:event_tglFanActionPerformed

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
            java.util.logging.Logger.getLogger(DlgNewHVCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgNewHVCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgNewHVCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgNewHVCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgNewHVCommand dialog = new DlgNewHVCommand( new javax.swing.JFrame(), true, null, null, 10);
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
    private javax.swing.ButtonGroup radMainSwicther;
    private javax.swing.ButtonGroup radRegime;
    private javax.swing.JToggleButton tglFan;
    private javax.swing.JToggleButton tglMainSwitcher;
    private javax.swing.JToggleButton tglMultipleChannels;
    private javax.swing.JToggleButton tglPreset;
    private javax.swing.JToggleButton tglRegime;
    private javax.swing.JToggleButton tglVibration;
    // End of variables declaration//GEN-END:variables
}
