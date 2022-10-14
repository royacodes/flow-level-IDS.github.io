

package processdata;

import java.io.*;
import java.util.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;

public class timing {

    
    public static void main(String[] args) throws ClassNotFoundException,
    SQLException {

             /*   SimpleDateFormat df = new SimpleDateFormat("HH:mm"); //for increase time
                Calendar cal = Calendar.getInstance();

                String str1="07:59";
                String str2="12:57";
                String str3="07:59";
                int count=0;
                String time[][] = new String [20000][2];
                while(str3.compareTo(str2)!=0){
                    Date d = null;
            try {
                d = df.parse(str1);
            } catch (ParseException ex) {
                Logger.getLogger(timing.class.getName()).log(Level.SEVERE, null, ex);
            }
                    cal.setTime(d);
                    cal.add(Calendar.MINUTE, 1);
                    str3=df.format(cal.getTime());
                    count++;
                    time[count-1][0]=Integer.toString(count);
                    time[count-1][1]=str1;
                    System.out.println(time[count-1][1]);
                    str1=str3;


                }
                try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/ttime.txt");
                for(int m = 0; m < count; m++)  //write matrix train to csv file
         {

                 wf.append(time[m][0]);
                 wf.append(',');
                 wf.append(time[m][1]);
               //  wf.append(',');
                // wf.append(ttest[m][2]);
                // wf.append(',');
                // wf.append(ttest[m][3]);
                // wf.append(',');
                // wf.append(ttest[m][4]);




            wf.append('\r');
               wf.append('\n');
               wf.flush();

         } //end write
         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }*/
    /*    String file1 = "D:/elmi/payan name/dataset/time.txt";
          String file2 = "D:/elmi/payan name/dataset/ttime.txt";
       
       // String time1[][] = new String [20000][2];
SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); //for increase time
                Calendar cal = Calendar.getInstance();
                Calendar cal1 = Calendar.getInstance();

        int IProw = 0 , testrow=0 ,ATrow=0 , c=0;
         ReaData rIP = new ReaData(); //read distinct IP
         rIP.Read(file1, 2);
         IProw = rIP.RetRow();
         System.out.println("g:"+IProw);
          String time[][] = new String [IProw][2];
        time = rIP.RetArray();
       //   ReaData rIP = new ReaData(); //read distinct IP
        rIP.Read(file2, 2);
        ATrow = rIP.RetRow();
        System.out.println("g:"+ATrow);
         String time1[][] = new String [ATrow][2];
        time1 = rIP.RetArray();
        String cc[][] = new String[ATrow][2];
        String gg[][] = new String[IProw][2];
int aft=0;
        for(int i=0; i<ATrow ; i++){
                  Date d = null;
            try {
                d = df.parse(time1[i][1]);
            } catch (ParseException ex) {
                Logger.getLogger(timing.class.getName()).log(Level.SEVERE, null, ex);
            }
                    cal.setTime(d);
            for(int j=aft; j<IProw ; j++){
                       Date dd = null;
            try {
                dd = df.parse(time[j][1]);
            } catch (ParseException ex) {
                Logger.getLogger(timing.class.getName()).log(Level.SEVERE, null, ex);
            }
                    cal1.setTime(dd);

                if(cal1.after(cal))
                    j=IProw;
                else{
                if(time1[i][1].compareTo(time[j][1])==0 ){
                    System.out.println(time1[i][1]+" "+time[j][1]);
                    cc[i][0]=time1[i][0];
                    cc[i][1]=time[j][0];
                    aft++;
                    
                }
                }
            }
        }
for(int k=0; k< ATrow;k++){
    if(cc[k][0]!=null){
        gg[c][0]=cc[k][0];
        gg[c][1]=cc[k][1];
        c++;
    }
}

                  try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/cc.csv");
                for(int m = 0; m < ATrow; m++)  //write matrix train to csv file
         {

                 wf.append(gg[m][0]);
                 wf.append(',');
                 wf.append(gg[m][1]);
               //  wf.append(',');
                // wf.append(ttest[m][2]);
                // wf.append(',');
                // wf.append(ttest[m][3]);
                // wf.append(',');
                // wf.append(ttest[m][4]);




            wf.append('\r');
               wf.append('\n');
               wf.flush();

         } //end write
         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }
     /*     int STime=1;
        int ETime=17892;
        String file1 = "D:/elmi/payan name/dataset/cc.txt";
        int IProw = 0 , testrow=0 ,ATrow=0;
         ReaData rIP = new ReaData(); //read distinct IP
         rIP.Read(file1, 2);
         IProw = rIP.RetRow();
         System.out.println("g:"+IProw);
          String time[][] = new String [IProw][2];
        time = rIP.RetArray();
        String f = null;
        for(int q=STime;q<=ETime ;q++){
      //      System.out.println(time[q-1][0]);
String InFile1 = "D:/elmi/payan name/dataset//matrix"+time[q-1][0]+".txt";
String InFile2 = "D:/elmi/payan name/dataset/traindata 6thweek thursday 1998//org data/clustering/matrix"+q+".txt";
File file = new File(InFile1);
File file2 = new File(InFile2);
if(time[q-1][0] != null){
    file2.renameTo(file);
}
    else
 {
    System.out.println("yes");
                     try{
               FileWriter wf = new FileWriter(file);
                for(int m = 0; m < 2; m++)  //write matrix train to csv file
         {

                 wf.append('0');

               //  wf.append(',');
                // wf.append(ttest[m][2]);
                // wf.append(',');
                // wf.append(ttest[m][3]);
                // wf.append(',');
                // wf.append(ttest[m][4]);




            wf.append('\r');
               wf.append('\n');
               wf.flush();

         } //end write
         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }
}
// else if(time[q-1][0] != null){
  //   file.renameTo(file2);
//}

        }*/


       /* String file1 = "D:/elmi/payan name/dataset/cc.txt";
        int IProw = 0 , testrow=0 ,ATrow=0;
         ReaData rIP = new ReaData(); //read distinct IP
         rIP.Read(file1, 2);
         IProw = rIP.RetRow();
         System.out.println("g:"+IProw);
          String time[][] = new String [IProw][2];
        time = rIP.RetArray();
        String file2 = "D:/elmi/payan name/dataset/IP.txt";
         rIP.Read(file2, 3);
         ATrow = rIP.RetRow();
         System.out.println("g:"+ATrow);
          String time1[][] = new String [ATrow][4];
          String attack[][] = new String [ATrow+ATrow][4];
        time1 = rIP.RetArray();
       
for(int i =0; i<ATrow ; i++){
    time1[i][0]= time [Integer.parseInt(time1[i][0])-1][0];
    
}
        for(int j=0 ; j<17892 ; j++){
    attack[j][0]=Integer.toString(j+1);
    attack[j][1]=String.valueOf(0);
    attack[j][2]=String.valueOf(0);
    attack[j][3]=String.valueOf(j+1);
    

}
        for(int j=0; j<ATrow ; j++){
            attack[Integer.parseInt(time1[j][0])-1][1]= time1[j][1];
             attack[Integer.parseInt(time1[j][0])-1][2]= time1[j][2];
             System.out.println(attack[j][0]+","+attack[j][1]+","+attack[j][2]);

        }


                   try{
               FileWriter wf = new FileWriter("D://elmi//payan name//dataset//normal.txt");
               int ii=0;
                for(int m = 1679; m < 1707; m++)  //write matrix train to csv file
         {

                // wf.append('0');

               //  wf.append(',');
                 wf.append(attack[m][0]);
                 wf.append(',');
                 wf.append(attack[m][1]);
                 wf.append(',');
                 wf.append(attack[m][2]);
                 wf.append(',');
                 wf.append(Integer.toString(ii+1));
                 ii++;




            wf.append('\r');
               wf.append('\n');
               wf.flush();

         } //end write
                       for(int m = 9342; m < 9518; m++)  //write matrix train to csv file
         {

                // wf.append('0');

               //  wf.append(',');
                 wf.append(attack[m][0]);
                 wf.append(',');
                 wf.append(attack[m][1]);
                 wf.append(',');
                 wf.append(attack[m][2]);
                 wf.append(',');
                 wf.append(Integer.toString(ii+1));
                 ii++;




            wf.append('\r');
               wf.append('\n');
               wf.flush();

         } //end write
         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }*/
        

  /*      int STime=1;
        int ETime=6053;
        int c=0;
        int g=0;
        int IProw;


        for(int q=STime;q<=ETime ;q++){

        String InFile1 = "D:/elmi/payan name/dataset//distinctsrc"+q+".txt";

        File file = new File(InFile1);
        if(file.exists()){
         ReaData rIP = new ReaData(); //read distinct IP
         rIP.Read(InFile1, 2);
         IProw = rIP.RetRow();
        // System.out.println("g:"+IProw);
        // String IP[][] = new String [IProw][2];
        // IP = rIP.RetArray();
         c = IProw+c;
        }
        }
        System.out.println(c);
        String [][] ttest=new String[c][3];
        int count=0;
         for(int q=STime;q<=ETime ;q++){

        String InFile1 = "D:/elmi/payan name/dataset//distinctsrc"+q+".txt";

        File file = new File(InFile1);
        if(file.exists()){
            g++;
         ReaData rIP = new ReaData(); //read distinct IP
         rIP.Read(InFile1, 2);
         IProw = rIP.RetRow();
         //System.out.println("g:"+IProw);
         String IP[][] = new String [IProw][2];
         IP = rIP.RetArray();
         for(int i=0 ; i<IProw ; i++){
             System.out.println(count);
             ttest[count][0]= Integer.toString(q);
             ttest[count][1]= Integer.toString(g);
             ttest[count][2]= IP[i][0];
             count++;
         }
        }
            }
        String file1 = "D:/elmi/payan name/dataset/cc.txt";
      ReaData rIP = new ReaData(); //read distinct IP
         rIP.Read(file1, 2);
         IProw = rIP.RetRow();
         //System.out.println("g:"+IProw);
          String time[][] = new String [IProw][2];
        time = rIP.RetArray();
           String file2 = "D:/elmi/payan name/dataset/normal.txt";
      //ReaData rIP = new ReaData(); //read distinct IP
         rIP.Read(file2, 4);
         IProw = rIP.RetRow();
        // System.out.println("g:"+IProw);
          String time1[][] = new String [IProw][4];
          int h=0 , flag=0;
        time1 = rIP.RetArray();

        for(int i =0; i<count ; i++){
    ttest[i][0]= time [Integer.parseInt(ttest[i][0])-1][0];    
}
         for(int i =0; i<IProw && flag==0 ; i++){
             flag=0;
             System.out.println(h);
             if(ttest[h][0].compareTo(time1 [i][0])==0 ){
                 while(ttest[h][0].compareTo(time1 [i][0])==0 && flag==0 ){
                     ttest[h][1]=time1[i][3];
                     h++;
                     if(h>=count){
                         flag=1;
                         h--;
                     }
                 }
                
             }
   // ttest[i][1]= time1;
}
                    try{
               FileWriter wf = new FileWriter("D://elmi//payan name//dataset//IPsrc.txt");
                for(int m = 0; m < count; m++)  //write matrix train to csv file
         {

                 wf.append(ttest[m][0]);

                 wf.append(',');
                 wf.append(ttest[m][1]);
                 wf.append(',');
                 wf.append(ttest[m][2]);
                // wf.append(',');
                // wf.append(ttest[m][4]);




            wf.append('\r');
               wf.append('\n');
               wf.flush();

         } //end write
         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }*/

  /*         String file1 = "C://Users//ROYA//Desktop//data1//normal.txt";
      ReaData rIP = new ReaData(); //read distinct IP
         rIP.Read(file1, 3);
        int  IProw = rIP.RetRow();
         //System.out.println("g:"+IProw);
          String time[][] = new String [IProw][3];
        time = rIP.RetArray();
           String file2 = "D:/elmi/payan name/dataset/normal1.csv";
     
             try{
               FileWriter wf = new FileWriter(file2);
                for(int m = 0; m < IProw; m++)  //write matrix train to csv file
         {

                 wf.append(time[m][0]);

                 wf.append(',');
                 wf.append(time[m][1]);
                 wf.append(',');
                 wf.append(time[m][2]);
                // wf.append(',');
                // wf.append(ttest[m][4]);




            wf.append('\r');
               wf.append('\n');
               wf.flush();

         } //end write
         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }*/

    /* 	String username = "root"; // mySQL username
    	String password = "123456"; // mySQL password
    	String url = "jdbc:mysql://localhost"; // Connect to database IDS
    	Class.forName("com.mysql.jdbc.Driver");

    	final Connection con = DriverManager.getConnection(url, username, password); // Connect
    	final Statement stmt;
        stmt = con.createStatement(); // for Query

	final String tbl="dataset"; // Table name

        String query="CREATE DATABASE data";
        try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
         query="use data";
        try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}

query = "create table dataset(frameNum varchar(50) DEFAULT NULL,srcIp varchar(50) DEFAULT NULL,destIp varchar(50) DEFAULT NULL,Protocol varchar(50) DEFAULT NULL,pckTime varchar(50) DEFAULT NULL,stcPort varchar(50) DEFAULT NULL,destPort varchar(50) DEFAULT NULL);";

try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "LOAD DATA LOCAL INFILE 'D://elmi//payan name//dataset//out.txt' INTO TABLE dataset FIELDS TERMINATED BY ',' lines terminated by '\r\n';";

try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "create table IP(ID int NOT NULL AUTO_INCREMENT primary key,IP varchar(50) DEFAULT NULL);";
try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "insert into IP(IP)select distinct a.IP from (select distinct srcIp as IP from dataset union select distinct destIp as IP from dataset)a;";
    try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "select * from IP into outfile 'D://elmi//payan name//dataset//dataIP.txt'fields terminated by ',' lines terminated by '\r\n';";
     try {
							stmt.executeQuery(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "drop database data";
try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
     *
     */
        int STime=152;
        int ETime=842;
        int i;
  for(int q=STime;q<=ETime ;q++){

         String InFile1 = "D://elmi//payan name//dataset//testdata 1999 5th week//tuesday//90s//part1//data//dataset"+q+".txt";
         //   String InFile1 = "D://elmi//payan name//dataset//testdata 1999 5th week//tuesday//1min//part2//cluster//maa2.txt";
            File f = new File(InFile1);
            i= q-1;
          // System.out.println(i);
            String InFile2 = "D://elmi//payan name//dataset//dataset"+i+".txt";
           // String InFile2 = "D://elmi//payan name//dataset//testdata 1999 5th week//tuesday//1min//part2//cluster//maa3.txt";
            File ff = new File(InFile2);
            
          

            f.renameTo(ff);
        }
    }
     }



