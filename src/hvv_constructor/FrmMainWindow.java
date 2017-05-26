/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_constructor;

import hvv_constructor.newService.DlgNewServiceCommand;
import hvv_constructor.newHv.DlgNewHVCommand;
import hvv_constructor.newVacuum.DlgNewVacCommand;
import JProg.JProgAStatement;
import JProg.service.JProgServPauseStatement;
import hvv_constructor.newService.Utils;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author yaroslav
 */
public class FrmMainWindow extends javax.swing.JFrame {
    HVV_Constructor theApp;
    static Logger logger = Logger.getLogger( FrmMainWindow.class);
        
    DefaultListModel lstModel;
    
    
    /**
     * Inner class for FileOpen dialog filter
     */
    class MyXMLFilter extends FileFilter {

        @Override
        public boolean accept( File f) {
            if (f.isDirectory()) {
                return true;
            }
            
            String extension = Utils.getExtension( f);
            if( extension != null) {
                if( extension.equals( Utils.xml))
                    return true;
                else
                    return false;
            }

            return false;
        }

        @Override
        public String getDescription() {
            return "XML files";
        }
        
    }
    
    /**
     * Creates new form NewJFrame
     */
    public FrmMainWindow( HVV_Constructor app) {
        initComponents();
        
        theApp = app;
        
        setTitle( "Конструктор программ автоматизации, v.1.0.0.1 (2016.08.08 16:29), (C) ФЛАВТ 2016.");
        lstModel = new DefaultListModel();
        lstProgram.setModel( lstModel);
        
        theApp.m_program = new TreeMap();
        /*
        theApp.m_program.put( 10, new JProg.service.JProgServPauseStatement( 10));
        theApp.m_program.put( 20, new JProg.service.JProgServPauseStatement( 20));
        theApp.m_program.put( 100, new JProg.service.JProgServPauseStatement( 30));
        theApp.m_program.put( 200, new JProg.service.JProgServPauseStatement( 40));
        */
        ShowProgram();
    }

    public final void ShowProgram() {
        logger.trace( "in");
        Set set = theApp.m_program.entrySet();
        Iterator it = set.iterator();
        
        lstModel.clear();
        
        logger.debug( "it.hasNext()=" + it.hasNext());
        while( it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            int nLine = ( int) entry.getKey();
            logger.debug( "nLine=" + nLine);
            JProg.JProgAStatement abstractStatement = ( JProg.JProgAStatement) entry.getValue();
            logger.debug( "statement.type=" + abstractStatement.getStatementType());
            String strStatement = nLine + " " + abstractStatement.GetAsString();
            lstModel.addElement( strStatement);
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

        btnAddHVStatement = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstProgram = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        btnLoad = new javax.swing.JButton();
        btnAddVacStatement = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnAddServiceStatement = new javax.swing.JButton();
        btnNewProgram = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1400, 1020));
        setMinimumSize(new java.awt.Dimension(1400, 1020));
        setPreferredSize(new java.awt.Dimension(1400, 1020));
        setResizable(false);
        getContentPane().setLayout(null);

        btnAddHVStatement.setText("<html><center>Добавить команду<br>для в/в поста</center></html>");
        btnAddHVStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHVStatementActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddHVStatement);
        btnAddHVStatement.setBounds(1120, 40, 270, 78);

        lstProgram.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        lstProgram.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstProgram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstProgramMouseClicked(evt);
            }
        });
        lstProgram.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstProgramValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstProgram);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(12, 42, 1100, 940);

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel1.setText("<html><b><u>Составляемая программа автоматизации:</b></u></html>");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(12, 12, 822, 18);

        btnLoad.setText("<html><center>Загрузить программу</center></html>");
        btnLoad.setActionCommand("");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });
        getContentPane().add(btnLoad);
        btnLoad.setBounds(1120, 610, 270, 78);

        btnAddVacStatement.setText("<html><center>Добавить команду<br>для вакуумного поста</center></html>");
        btnAddVacStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddVacStatementActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddVacStatement);
        btnAddVacStatement.setBounds(1120, 130, 270, 78);

        btnSave.setText("<html><center>Сохранить программу</center></html>");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave);
        btnSave.setBounds(1120, 690, 270, 78);

        btnExit.setText("<html><center>Выход</center></html>");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(1120, 900, 270, 78);

        btnRemove.setText("<html><center>Удалить выбранную команду</center></html>");
        btnRemove.setEnabled(false);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemove);
        btnRemove.setBounds(1120, 310, 270, 78);

        btnAddServiceStatement.setText("<html><center>Добавить<br>сервисную команду</center></html>");
        btnAddServiceStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddServiceStatementActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddServiceStatement);
        btnAddServiceStatement.setBounds(1120, 220, 270, 78);

        btnNewProgram.setText("<html><center>Создать новую программу</center></html>");
        btnNewProgram.setActionCommand("");
        btnNewProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewProgramActionPerformed(evt);
            }
        });
        getContentPane().add(btnNewProgram);
        btnNewProgram.setBounds(1120, 530, 270, 78);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int calculateNewStringNumber() {
        int nNewStatementLineNumber = 0;
        
        int nCurrentItemIndex = lstProgram.getSelectedIndex();
        if( nCurrentItemIndex == -1) {
            int nLastItemIndex = lstProgram.getModel().getSize() - 1;
            if( nLastItemIndex > -1) {
                String strLastItem = ( String) lstProgram.getModel().getElementAt( nLastItemIndex);
                String strLastLineNumber = strLastItem.substring( 0, strLastItem.indexOf( " "));
                try {
                    int nLastLineNumber = Integer.parseInt( strLastLineNumber);
                    int nNewLineNumber = (( nLastLineNumber / 10)  + 1) * 10;
                    nNewStatementLineNumber = nNewLineNumber;
                }
                catch( Exception ex) {
                    logger.error( "Exception while getting int of last statement number", ex);    
                }
            }
            else {
                nNewStatementLineNumber = 10;
            }
        }
        else {
            int nLastItemIndex = lstProgram.getModel().getSize() - 1;
            if( nCurrentItemIndex == nLastItemIndex) {
                String strLastItem = ( String) lstProgram.getModel().getElementAt( nLastItemIndex);
                String strLastLineNumber = strLastItem.substring( 0, strLastItem.indexOf( " "));
                try {
                    int nLastLineNumber = Integer.parseInt( strLastLineNumber);
                    int nNewLineNumber = (( nLastLineNumber / 10)  + 1) * 10;
                    nNewStatementLineNumber = nNewLineNumber;
                }
                catch( Exception ex) {
                    logger.error( "Exception while getting int of last statement number", ex);    
                }
            }
            else {
                String strCurrentItem = ( String) lstProgram.getModel().getElementAt( nCurrentItemIndex);
                String strCurrentLineNumber = strCurrentItem.substring( 0, strCurrentItem.indexOf( " "));
                
                String strNextItem = ( String) lstProgram.getModel().getElementAt( nCurrentItemIndex + 1);
                String strNextLineNumber = strNextItem.substring( 0, strNextItem.indexOf( " "));
                
                try {
                    int nCurrentLineNumber = Integer.parseInt( strCurrentLineNumber);
                    int nNextLineNumber = Integer.parseInt( strNextLineNumber);
                    
                    if( nCurrentItemIndex == nNextLineNumber + 1) {
                       HVV_Constructor.MessageBoxInfo( "Вы пытаетесь вставить новую строку между строками с номерами " +
                               nCurrentLineNumber + " и " + nNextLineNumber + "!", "Внимание!");
                    }
                    else {
                        int nNewLineNumber = ( nCurrentLineNumber + nNextLineNumber) / 2;
                        nNewStatementLineNumber = nNewLineNumber;
                    }
                }
                catch( Exception ex) {
                    logger.error( "Exception while getting int of last statement number", ex);    
                }
            }
        }
        return nNewStatementLineNumber;
    }
    
    private void btnAddHVStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHVStatementActionPerformed
        //PnlNewHVCommand panel = new PnlNewHVCommand();
        //final JDialog frame = new JDialog( this, "Заголовок", true);
        //frame.getContentPane().add( panel);
        //frame.pack();
        //frame.setVisible( true);
        
        //vctProgram.add( "Закрыть\tклапан 13");
        //RefreshProgram();
        
        int nNewStatementLineNumber = calculateNewStringNumber();
        DlgNewHVCommand dlg = new DlgNewHVCommand( this, true, theApp, null, nNewStatementLineNumber);
        dlg.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e) {
                
            }
        });
        
        dlg.setVisible( true);
        ShowProgram();
    }//GEN-LAST:event_btnAddHVStatementActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter( new MyXMLFilter());
        
        fc.setCurrentDirectory( new File( theApp.GetAMSRoot() + "/ReadyPrograms"));
        
        int returnVal = fc.showOpenDialog( this);
        if( returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            logger.info( "LoadProgram opening: " + file.getName());
            
            TreeMap newProgram = new TreeMap();
            boolean bResOk = true;
            try {
                SAXReader reader = new SAXReader();
                URL url = file.toURI().toURL();
                Document document = reader.read( url);
                Element program = document.getRootElement();
                if( program.getName().equals( "Program")) {
                    // iterate through child elements of root
                    for ( Iterator i = program.elementIterator(); i.hasNext(); ) {
                        Element element = ( Element) i.next();
                        String name = element.getName();
                        String strLineNumber = element.getTextTrim();
                        int nLineNumber = Integer.parseInt( strLineNumber);
                        
                        JProgAStatement statement = JProgAStatement.parse( element);
                        if( statement != null)
                            newProgram.put( nLineNumber, statement);
                        
                        
                        logger.debug( "Pairs: [" + name + " : " + strLineNumber + "]");
                    }
                    
                    theApp.m_program = newProgram;
                    ShowProgram();
                }
                else
                    logger.error( "There is no 'program' root-tag in pointed XML");
                
            } catch( MalformedURLException ex) {
                logger.error( "MalformedURLException caught while loading settings!", ex);
                bResOk = false;
            } catch( DocumentException ex) {
                logger.error( "DocumentException caught while loading settings!", ex);
                bResOk = false;
            }
        
        } else {
            logger.info("LoadProgram cancelled.");
        }
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnAddVacStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddVacStatementActionPerformed
        int nNewStatementLineNumber = calculateNewStringNumber();
        DlgNewVacCommand dlg = new DlgNewVacCommand( this, true, theApp, null, nNewStatementLineNumber);
        dlg.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e) {
                
            }
        });
        
        dlg.setVisible( true);
    }//GEN-LAST:event_btnAddVacStatementActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Document saveFile = DocumentHelper.createDocument();
        Element program = saveFile.addElement( "Program");
        
        Set set = theApp.m_program.entrySet();
        Iterator it = set.iterator();
        while( it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            int nLineNumber = ( int) entry.getKey();            
            JProg.JProgAStatement abstractStatement = ( JProg.JProgAStatement) entry.getValue();            
            Element statement = program.addElement( "LineNumber").addText( "" + nLineNumber);
            abstractStatement.AddXMLStatement( statement);
        }
        
        OutputFormat format = OutputFormat.createPrettyPrint();
        
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter( new MyXMLFilter());
        fc.setCurrentDirectory( new File( theApp.GetAMSRoot() + "/ReadyPrograms"));
        
        int returnVal = fc.showSaveDialog( this);
        if( returnVal == JFileChooser.APPROVE_OPTION) {
            String strFilePathName = fc.getSelectedFile().getAbsolutePath();
            if( !strFilePathName.endsWith( ".xml"))
                strFilePathName += ".xml";
            File file = new File( strFilePathName);            
            XMLWriter writer;
            try {
                writer = new XMLWriter( new FileWriter( file.getAbsolutePath()), format);
                writer.write( saveFile);
                writer.close();
            } catch (IOException ex) {
                logger.error( "IOException: ", ex);
            }
        
        } else {
            logger.error( "Пользователь не указал имя файла куда сохранять программу.");
        }
        
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
        System.exit( 0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        if( lstProgram.getSelectedIndex() != -1) {
            String str = ( String) lstModel.get( lstProgram.getSelectedIndex());
            String strLineNumber = str.substring( 0, str.indexOf( " "));
            int nLineNumber = Integer.parseInt( strLineNumber);
            if( theApp.m_program.containsKey( nLineNumber))
                theApp.m_program.remove( nLineNumber);
            ShowProgram();
            btnRemove.setEnabled( false);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void lstProgramValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstProgramValueChanged
        btnRemove.setEnabled( lstProgram.getSelectedIndex() != -1);
    }//GEN-LAST:event_lstProgramValueChanged

    private void btnAddServiceStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddServiceStatementActionPerformed
        int nNewStatementLineNumber = calculateNewStringNumber();
        DlgNewServiceCommand dlg = new DlgNewServiceCommand( this, true, theApp, null, nNewStatementLineNumber);
        dlg.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e) {
                
            }
        });
        
        dlg.setVisible( true);
    }//GEN-LAST:event_btnAddServiceStatementActionPerformed

    private void lstProgramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstProgramMouseClicked
        if( evt.getButton() == MouseEvent. BUTTON1 && evt.getClickCount() == 2) {
            if( lstProgram.getSelectedIndex() != -1) {
                String str = ( String) lstModel.get( lstProgram.getSelectedIndex());
                String strLineNumber = str.substring( 0, str.indexOf( " "));
                int nLineNumber = Integer.parseInt(strLineNumber);
                if( theApp.m_program.containsKey( nLineNumber)) {
                    JProgAStatement statement = ( JProgAStatement) theApp.m_program.get( nLineNumber);
                    switch( statement.getStatementRoughType()) {
                        case JProgAStatement.STATEMENT_TYPE_VACUUM:
                            DlgNewVacCommand dlgVac = new DlgNewVacCommand( this, true, theApp, statement, nLineNumber);
                            dlgVac.addWindowListener( new WindowAdapter() {
                                public void windowClosing( WindowEvent e) {
                                }
                            });
                            dlgVac.setVisible( true);
                        break;
                        case JProgAStatement.STATEMENT_TYPE_SERVICE:
                            DlgNewServiceCommand dlg1 = new DlgNewServiceCommand( this, true, theApp, statement, nLineNumber);
                            dlg1.addWindowListener( new WindowAdapter() {
                                public void windowClosing( WindowEvent e) {
                                }
                            });
                            dlg1.setVisible( true);
                        break;
                        case JProgAStatement.STATEMENT_TYPE_HV:
                            DlgNewHVCommand dlg2 = new DlgNewHVCommand( this, true, theApp, statement, nLineNumber);
                            dlg2.addWindowListener( new WindowAdapter() {
                                public void windowClosing( WindowEvent e) {
                                }
                            });
                            dlg2.setVisible( true);
                        break;
                        default:
                            logger.fatal( "TODO rest types of already existing statements to edit");
                    }
                            
                }
                ShowProgram();
                btnRemove.setEnabled( false);
            }
        }
    }//GEN-LAST:event_lstProgramMouseClicked

    private void btnNewProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewProgramActionPerformed
        int nResult = HVV_Constructor.MessageBoxAskYesNo( "Создать новую программу?\nЭто сбросит текущую!", "Создание новой программы");
        if( nResult == JOptionPane.YES_OPTION) {
            theApp.m_program.clear();
            theApp.m_pMainWnd.ShowProgram();
        }
    }//GEN-LAST:event_btnNewProgramActionPerformed


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
            java.util.logging.Logger.getLogger(FrmMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //WRONG WAY - run from ConstrApp
                new FrmMainWindow( new HVV_Constructor()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddHVStatement;
    private javax.swing.JButton btnAddServiceStatement;
    private javax.swing.JButton btnAddVacStatement;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnNewProgram;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstProgram;
    // End of variables declaration//GEN-END:variables
}
