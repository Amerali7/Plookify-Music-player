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
public class Search {
    public static JTable results(String word){
        JTable data = Data.getD("SELECT trackID,title,artist,genre,time FROM Track WHERE title LIKE '%"+word+"%'OR artist LIKE '%"+word+"%'OR genre LIKE '%"+word+"%'");
        NowPlaying.nowP(data);
        return data;
    }
}
