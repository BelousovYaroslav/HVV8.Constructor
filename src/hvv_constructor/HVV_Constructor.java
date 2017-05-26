/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_constructor;

import java.util.TreeMap;
import javax.swing.JOptionPane;
import org.apache.log4j.*;
import sun.audio.*;
import java.io.*;
import javax.swing.UIManager;

/**
 *
 * @author yaroslav
 */
public class HVV_Constructor {
    private final String m_strAMSrootEnvVar;
    public String GetAMSRoot() { return m_strAMSrootEnvVar; }
    
    public FrmMainWindow m_pMainWnd;
    public TreeMap m_program;
    static Logger logger = Logger.getLogger(HVV_Constructor.class);
    public static final org.apache.log4j.Level LOG_LEVEL = org.apache.log4j.Level.DEBUG;
    
    public HVV_Constructor() {
        m_strAMSrootEnvVar = System.getenv( "AMS_ROOT");
    }
    
    public void start() {
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
            java.util.logging.Logger.getLogger( FrmMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger( FrmMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger( FrmMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger( FrmMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        m_pMainWnd = new FrmMainWindow( this);
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                m_pMainWnd.setVisible( true);
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //BasicConfigurator.configure();
        //logger.setLevel( LOG_LEVEL);
        
        String strAMSrootEnvVar = System.getenv( "AMS_ROOT");
        if( strAMSrootEnvVar == null) {
            MessageBoxError( "Не задана переменная окружения AMS_ROOT!", "HVV_Constructor");
            return;
        }
        
        String strlog4jPropertiesFile = strAMSrootEnvVar + "/etc/log4j.constructor.properties";
        File file = new File( strlog4jPropertiesFile);
        if(!file.exists()) {
            System.out.println("It is not possible to load the given log4j properties file :" + file.getAbsolutePath());
            BasicConfigurator.configure();
        }
        else
            PropertyConfigurator.configure( file.getAbsolutePath());
        
        logger.info( "HVV_Contructor::main(): in. Start point!");
        
        
        new HVV_Constructor().start();
        
        
    }
    
    /**
     * Функция для сообщения пользователю информационного сообщения
     * @param strMessage сообщение
     * @param strTitleBar заголовок
     */
    public static void MessageBoxInfo( String strMessage, String strTitleBar)
    {
        JOptionPane.showMessageDialog( null, strMessage, strTitleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Функция для сообщения пользователю сообщения об ошибке
     * @param strMessage сообщение
     * @param strTitleBar заголовок
     */
    public static void MessageBoxError( String strMessage, String strTitleBar)
    {
        JOptionPane.showMessageDialog( null, strMessage, strTitleBar, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Функция для запроса у пользователя простого ответа Да/Нет
     * @param strMessage сообщение
     * @param strTitleBar заголовок
     */
    public static int MessageBoxAskYesNo( String strMessage, String strTitleBar)
    {
        //UIManager.put( "YES", "Да");
        //UIManager.put( "No", "Нет");
        
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.okButtonText", "Согласен");
        UIManager.put("OptionPane.yesButtonText", "Да");
    
        int nDialogResult = JOptionPane.showConfirmDialog( null, strMessage, strTitleBar, JOptionPane.YES_NO_OPTION);
        return nDialogResult;
    }
}
