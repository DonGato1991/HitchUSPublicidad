/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.hitch;

import UpperEssential.UpperEssentialLookAndFeel;
import ec.edu.espe.model.view.Login;
import java.net.URL;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author JuanAndresCaspi
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
//            URL sqlScriptUrl = Run.class
//                       .getClassLoader().getResource("Chocolate.theme");
//            System.out.println(sqlScriptUrl.getPath());
            
            UIManager.setLookAndFeel(new UpperEssentialLookAndFeel());
            
            Login l= new Login();
            l.setLocationRelativeTo(null);
            l.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Impossible load theme.");
        }
    }
    
}
