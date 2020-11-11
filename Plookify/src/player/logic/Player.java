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
public class Player {
    public static JTable getSong(){
        JTable table = Data.getD("SELECT song,artist,duration,trackID FROM NowPlaying");
        
        return table;
    }
    public static int min(int seconds) {
        int minutes = (seconds % 3600) / 60;
        return minutes;
    }
    public static int sec(int seconds){
        int secs = seconds % 60;
        return secs;
    }

    public static String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }
        

        return String.valueOf(number);
    }
}
