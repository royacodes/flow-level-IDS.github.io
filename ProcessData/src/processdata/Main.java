

package processdata;


import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
   
    public static void main(String[] args) {

        String file1 = "D:/elmi/payan name/dataset/traindata 6thweek thursday 1998/dataIP6th.txt";
        String file2 = "D://elmi//payan name//dataset//traindata 6thweek thursday 1998//6thweekth.txt" ;
       // String file3 = "D:/elmi/payan name/dataset/Attackinfor.txt";

        String [][] test = null; 
        String [][] IP = null ;
        String [][] attack = null;
        int [][] train; // mapping has 3 column dest,src,time
        int [][] temp;
        int [][] val;
        String time[][];
        int IProw = 0 , testrow=0 ,ATrow=0;
        String outputfile =null;

        ReaData rIP = new ReaData(); //read distinct IP
        rIP.Read(file1, 2);
        IProw = rIP.RetRow();
        IP = rIP.RetArray();

        ReaData rTest = new ReaData(); //read org data
        rTest.Read(file2, 7);
        testrow = rTest.RetRow();
        test = rTest.RetArray();

     /*   ReaData rAttack = new ReaData(); //read attack
        rAttack.Read(file3, 8);
        ATrow = rAttack.RetRow();
        attack = rAttack.RetArray();*/
             ///System.out.println(test[923][1]+","+test[923][2]+","+test[923][3]+","+test[923][4].substring(0, 8)+","+test[923][5]+","+test[923][6]);
             for(int j=0; j<testrow; j++){

                   test[j][0]=test[j][1];
                   test[j][1]=test[j][2];
                 //  if(j==923)
                   //System.out.println("   "+test[j][4].substring(0, 8));
                   test[j][2]=test[j][4].substring(0, 8);
                   test[j][3]=test[j][5];
                   test[j][4]=test[j][6];

                     }
//System.out.println(test[923][2]+"njhj");
 train = new int[testrow][6]; // [][0]--> destIp , [][1]--> srcIp , [][2]-->time, [][3]-->destPort, [][4]-->srcPort
 time = new String[testrow][2];

  Separate spt = new Separate();
  spt.sp(test, testrow, 10);
  test = spt.Retst();
  time = spt.Retime();

  //spt.AttackMp(attack, time, IP, IProw, ATrow, testrow);
  spt.DataMp(testrow, IProw, IP, test);
  spt.CrData(testrow);
	


 
	System.out.println("Done");
    }
    public static int SearchIp(String ip , String[][] Sip , int size){
        String testIp;
        int category=0 ;
        String[][] StIP;
        testIp = ip;


        for (int i=0 ; i<size ; i++){
             if(Sip[i][1].equals(ip)){
                 category =Integer.parseInt(Sip[i][0]);
                 i=size;
             }
 else
     category = 0;
        }
        return category;

    }

}
