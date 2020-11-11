/* This class will look to see if the user is a paying subscriber and if the person they want to add is a paying subscriber
   If they are both paying subscribers the program will send a request to the friend
   However if either the user or the friend are not paying subscribers the program will show an error message
   explaining the situation */

/**Author Sofia Bibi**/
/**version 1**/

package Social_Manager.Social_Manager_Code;

import javax.swing.JOptionPane; 


public class AddFriend {
    public static void main (String user, Boolean subscription, String friend, Boolean friendSubscription) { 
    // Looks to see if the user and/or friend are paying subscribers 
    // Throws an error if either if not a paying subscriber
    
    if (subscription == true) {
        if (friendSubscription = true) {
            addFriend(user);
        }
        else {
            JOptionPane.showMessageDialog(null, friend + " is not a paying subscriber.");
        }     
    }
    else {
        JOptionPane.showMessageDialog(null,"You are not a paying subscriber. Please purchase a subscription to be able to add friends");
    }
}
    
    public static void addFriend(String user) {
        JOptionPane.showMessageDialog(null, user + " wants to add you as a friend."); 
    }
    
}
