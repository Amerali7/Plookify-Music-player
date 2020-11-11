/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.logic;
import javax.swing.*;
/**
 *
 * @author Fionnuala
 */
public class Login extends player.GUI.logInGUI {
    public static boolean check(String email, String password){
        
        JTable data = Data.getD("SELECT email,password FROM Account");
        for(int i=0;i<data.getRowCount();i++){       
            if((data.getValueAt(i, 0).equals(email))&&(data.getValueAt(i,1).equals(password))){
                return true;    
            }
        }
        return false;    
    }
}
