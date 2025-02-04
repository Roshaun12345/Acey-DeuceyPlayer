/**
 * Acey-Deucey Game Program
 * Implements a client-side logic for interacting with the dealer in an Acey-Deucey card game.
 * Features include determining the next bet based on cards dealt and communicating with the dealer server.
 *
 *
 * @author Roshaun Gregory
 * @github Roshaun12345
 * @version October 30, 2023
 */



import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Random;
import java.lang.Math;


/**
* rw for read and write 
* Handles communication with the dealer server. Connects via a socket and provides methods
* for sending (write) and receiving (read) messages.
*/
class rw{
     String IpAddress;
     String IpPort;
     Socket socket;
     DataInputStream dis;
     DataOutputStream dos;
     
     public rw(String ipp, String ipa){
          IpAddress = ipa;
          IpPort=ipp;
          
          try{
              socket = new Socket(IpAddress, Integer.parseInt(IpPort));
              dis = new DataInputStream(socket.getInputStream());
              dos = new DataOutputStream(socket.getOutputStream());
            }catch(IOException b){
                b.printStackTrace();
            }
        } 
    
     void write(String s) throws IOException{
            dos.writeUTF(s);
            dos.flush();
        }

     String read() throws IOException{
         return dis.readUTF();
       }
     
}



/**
* Mid
* Calculates the bet for a "middle play" in the Acey-Deucey game based on the difference
* between two cards and a base value (lowval).
*/
class Mid{
  int card1;
  int card2;
  int diff;
  int lowval;
  

  public Mid(int cc1, int cc2, int lower){
    card1 = cc1;
    card2 = cc2;
    lowval = lower;
    diff = Math.abs(cc2 -cc1);
  }

  public String mbet(){
    int mybet_m = 0;
    double b_m = 0.0;
    String bet_m = "";
    
    if (diff<3){
      mybet_m=0;
      bet_m = bet_m+String.valueOf(mybet_m);
     
    }
    else if(diff > 2 && diff < 5){
      mybet_m = 1;
      bet_m = bet_m+String.valueOf(mybet_m);
      
    }
    else if(diff >4 && diff<8){
      b_m = lowval * (10.0/100.0);
      mybet_m = (int) Math.floor(b_m);
      bet_m = bet_m+String.valueOf(mybet_m);
      
    }
    else if(diff>7 && diff<11){
      b_m = lowval * (20.0/100.0);
      mybet_m = (int) Math.floor(b_m);
      bet_m = bet_m+String.valueOf(mybet_m);
      
    }
    else if(diff>10 && diff<14 ){
      b_m = lowval * (30.0/100.0);
      mybet_m = (int) Math.floor(b_m);
      bet_m = bet_m+String.valueOf(mybet_m);
    }
    return bet_m;
  
    
  }

}

/**
* High or Low
* Algorithm to determine whether the prgrom should bet a high or low play next
*/
class H_or_L{
  int cd1;

  public H_or_L(int c){
    cd1 = c;
    
  }
  public String choice(){
    String b = "";
    //Chose to set 8 (the most middle value) to low
    if(cd1==8){
      b=b+"low";
    }
    else if(cd1>8){
      b=b+"low";
    }
    else{
      b=b+"high";
    }
    return b;
  }
}


/**
* Algorithm to determine the bet played when on a high play
*/
class High{
  int cd01;
  int lowvalue;
  int cardamount;

  public High(int c, int lv, int ca){
    cd01 = c;
    lowvalue = lv;
    cardamount = ca;
  }

  public String hbet(){
    int mybet_h = 0;
    double b_h = 0.0;
    String bet_h = "";
    if (cd01 == 2){
      //If the value of the card is 2, and the amount of times said card has been played is equal to seven, then it is safe for the program to make a big bet here
      if(cardamount == 7){
        b_h = lowvalue * (60.0/100.0);
        mybet_h = (int) Math.floor(b_h);
        bet_h = bet_h+String.valueOf(mybet_h);
      }
      else{
        b_h = lowvalue * (30.0/100.0);
        mybet_h = (int) Math.floor(b_h);
        bet_h = bet_h+String.valueOf(mybet_h);
      }
    }
    else if(cd01 == 3){
      b_h = lowvalue * (25.0/100.0);
      mybet_h = (int) Math.floor(b_h);
      bet_h = bet_h+String.valueOf(mybet_h);
    }
    else if(cd01 == 4){
      b_h = lowvalue * (20.0/100.0);
      mybet_h = (int) Math.floor(b_h);
      bet_h = bet_h+String.valueOf(mybet_h);
    }
    else if(cd01 == 5){
      b_h = lowvalue * (10.0/100.0);
      mybet_h = (int) Math.floor(b_h);
      bet_h = bet_h+String.valueOf(mybet_h);
    }
    else if(cd01 == 6){
      b_h = lowvalue * (5.0/100.0);
      mybet_h = (int) Math.floor(b_h);
      bet_h = bet_h+String.valueOf(mybet_h);
    }
    else if(cd01 == 7){
      mybet_h = 1 ;
      bet_h = bet_h+String.valueOf(mybet_h);
    }
    return bet_h;

  }
}

/**
* Algorithm to determine the bet played when on a low play
*/
class Low{
  int cd_1;
  int low_value;
  int card_amount;

  public Low(int c, int lv, int ca){
    cd_1 = c;
    low_value = lv;
    card_amount = ca;
  }

  public String lbet(){
    int mybet_l = 0;
    double b_l = 0.0;
    String bet_l = "";
    
    if (cd_1 == 8){
      mybet_l = 0;
      bet_l = bet_l+String.valueOf(mybet_l);
    }
    else if (cd_1 == 9){
      mybet_l = 1;
      bet_l = bet_l+String.valueOf(mybet_l);
    }
    
    else if(cd_1 == 10){
      b_l = low_value * (5.0/100.0);
      mybet_l = (int) Math.floor(b_l);
      bet_l = bet_l+String.valueOf(mybet_l);
    }
    else if(cd_1 == 11){
      b_l = low_value * (10.0/100.0);
      mybet_l = (int) Math.floor(b_l);
      bet_l = bet_l+String.valueOf(mybet_l);
    }
    else if(cd_1 == 12){
      b_l = low_value * (20.0/100.0);
      mybet_l = (int) Math.floor(b_l);
      bet_l = bet_l+String.valueOf(mybet_l);
    }
    else if(cd_1 == 13){
      b_l = low_value * (25.0/100.0);
      mybet_l = (int) Math.floor(b_l);
      bet_l = bet_l+String.valueOf(mybet_l);
    }
    else if(cd_1 == 14){
      //If the value of the card is 14, and the amount of times said card has been played is equal to seven, then it is safe for the program to make a big bet here
      if(card_amount == 7){
      b_l = low_value * (60.0/100.0);
      mybet_l = (int) Math.floor(b_l);
      bet_l = bet_l+String.valueOf(mybet_l);
      }
      else{
      b_l = low_value * (30.0/100.0);
      mybet_l = (int) Math.floor(b_l);
      bet_l = bet_l+String.valueOf(mybet_l);
      
      }
    }
    return bet_l;
  }
}





/**
* Main method for the Acey game program.
* Connects to the dealer using the provided IP address and port from command-line arguments.
* Reads commands from the dealer, processes them, and sends appropriate responses.
*/


class Acey{
  static public void main(String[] args){
    String DealerCmds ="";
    String IpA= args[0];
    String IpP= args[1];
    rw myobj = new rw(IpP, IpA);
    //Loop for every new round played
    do{
      

         
      try{
        String response = "";
        
          
        DealerCmds = myobj.read();

        //If statements searches for a specific word in the text the dealer will send to us
        if (DealerCmds.equals("login")){
          response = response+"Roshaun12345:Shaun";  
          myobj.write(response);
          System.out.println(response);
        }

        else if(DealerCmds.contains("play")){
          //Splits the elements of the text apart to get individual values
          String[] c = DealerCmds.split(":");
          String c1val = c[3].substring(0,c[3].length()-1);
          String c2val = c[4].substring(0,c[4].length()-1);

          //Gets the integer value of the first card
          int c1num = 0;
          String c1numm = c1val;
          if (c1numm.equals("J")){
            c1num = 11;
          }
          else if (c1numm.equals("Q")){
            c1num = 12;
          }
          else if (c1numm.equals("K")){
            c1num = 13;
          }
          else if (c1numm.equals("A")){
            c1num = 14;
          }
          else{
            c1num = Integer.parseInt(c1numm);
          }
          
          //Gets the integer value of the second card
          int c2num = 0;
          String c2numm = c2val;
          if (c2numm.equals("J")){
            c2num = 11;
          }
          else if (c2numm.equals("Q")){
            c2num = 12;
          }
          else if (c2numm.equals("K")){
            c2num = 13;
          }
          else if (c2numm.equals("A")){
            c2num = 14;
          }
          else{
            c2num = Integer.parseInt(c2numm);
          }
                
          
                
          //Finds the maximum value we can bet by comparing the pot value with our stack value
          int lowerVal = 0;
          int potVal = Integer.parseInt(c[1]);
          int stackVal = Integer.parseInt(c[2]);
          if (potVal < stackVal){
            lowerVal = potVal;
          }
          else{
            lowerVal = stackVal;
          }
          String lval = String.valueOf(lowerVal);


          //For loop to count the number of times our last two cards have been played since the last reshuffle.
          int amountofcard1 = 0;
          int amountofcard2 = 0;
          for(int i =6; i < c.length; i++ ){
            if (c[i].equals(c1val)){
              amountofcard1=amountofcard1+1;
            }
            else if (c[i].equals(c2val)){
              amountofcard2=amountofcard2+1;
            }

          }
                
          //Checks to see if it is mid, high or low
          if (!c1val.equals(c2val)){
            Mid M = new Mid(c1num, c2num, lowerVal);
            String bnum = M.mbet();
            response=response+"mid:"+ bnum;
            myobj.write(response);
            System.out.println(response);
          }
          else if(c1val.equals(c2val)){
            H_or_L HL = new H_or_L(c1num);
            String reply = HL.choice();
            if(reply.equals("high")){
              High H = new High(c1num, lowerVal, amountofcard1);
              String bnum = H.hbet();
              response = response+"high:"+bnum;
              myobj.write(response);
              System.out.println(response);
            }
            else if(reply.equals("low")){
              Low L = new Low(c1num, lowerVal, amountofcard1);
              String bnum = L.lbet();
              response = response+"low:"+bnum;
              myobj.write(response);
              System.out.println(response);
          }
        }
        } 

        else if(DealerCmds.contains("status")){
          System.out.println(DealerCmds);
        }
        
        else if(DealerCmds.contains("done")){
          System.out.println(DealerCmds);
          System.exit(0);
        }
       
      }catch(IOException a){
        a.printStackTrace();
      }
    //When dealers sends the text done, the code will stop and the game will be over for my program
    }while(!DealerCmds.equals("done"));
  }
}
