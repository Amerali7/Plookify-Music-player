package playlist;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Account {
    public int accountID;public String fName;public String lName;public  String address;
    public String email;public char paymentType;public char accountType;
    public ArrayList<Playlist> playlists;
    public ArrayList<Account> friends;
    
    public int getAccountID(){return accountID;}
    public String getFName(){return fName;}
    public String getLName(){return lName;}
    public String getEmail(){return email;}
    public char getPaymentType(){return paymentType;}
    public char getAccountType(){return accountType;}
    public ArrayList<Playlist> getPlaylists(){return playlists;}
    public ArrayList<Account> getFriends(){return friends;}
    public void addPlaylist(Playlist p){
        if(playlists!=null){
            playlists.add(p);
        }else{
            playlists=new ArrayList<>();
            playlists.add(p);
        }
    }
    public Account(int AccountID,String FName,String LName,String Email,
            char PaymentType,char AccountType){
        accountID=AccountID;fName=FName;lName=LName;email=Email;paymentType=PaymentType;
        accountType=AccountType;
    }
    public Account(int AccountID,String FName,String LName){
        accountID=AccountID;fName=FName;lName=LName;        
    }
}
