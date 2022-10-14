

package processdata;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Separate {

     public static int [][] train;
     public static int [][] temp;
     public static int [][] val;
     public static String time[][];
     public static String tst [][];
     public void separate(){

    }
    public void sp(String [][] tstt , int trow , int min){
        tst=tstt;
              // [][0]--> destIp , [][1]--> srcIp , [][2]-->time, [][3]-->destPort, [][4]-->srcPort
           time = new String[trow][2];
           int tir=0;
// convert time to int in 10 min
               SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); //for increase time
               Calendar cal = Calendar.getInstance();
               Calendar cal1 = Calendar.getInstance();//for increase time

               String str1=tst[0][2]/*.substring(0, 5)*/;
               String str2;
               String str3=str1;
               int countt=0;

               int index1=0 ,index2=0 , b=0;
               while(index2<trow-1){
                try {
                     str1=tst[index2][2]/*.substring(0, 5)*/;
                    str3=str1;
                    Date d = df.parse(str1);
                    cal.setTime(d);
                    cal.add(Calendar.SECOND, 1);
                    str2=df.format(cal.getTime());
                    Date dd=df.parse(str3);
                    cal1.setTime(dd);
     System.out.println(str2+" min"+str1);
                     while(b==0 && index1<trow-1 ){

                         if(str3.compareTo(str2)!=0 && index1<trow-1 && cal1.before(cal)){


                        index1++;
                        System.out.println("str3: "+tst[index1][2]/*.substring(0,5)*/+"ind: "+index1);
                         str3=tst[index1][2]/*.substring(0,5)*/;
                         dd = df.parse(str3);
                         cal1.setTime(dd);
                         }
 else{
                             b=1;
 }

                     }
 b=0;
                  // index1=index1-1;//index of last 16:31
                    countt++;
                    if(index1<trow-1){
                    for(int z=index2 ; z<index1 ;z++){
                        time[z][1]= tst[z][2]/*.substring(0, 5)*/;
                        time[z][0]= Integer.toString(countt); //matrix for mapping from attack table to time sery
                        tir++;
                       // tst[z][5]=Integer.toString(countt);
                        tst[z][5]=time[z][1];
                    }
                    }
 else if(index1==trow-1){
      for(int z=index2 ; z<=index1 ;z++){
                        time[z][1]= tst[z][2]/*.substring(0, 5)*/;
                        time[z][0]= Integer.toString(countt); //matrix for mapping from attack table to time sery
                        tir++;
                       // tst[z][5]=Integer.toString(countt);
                        tst[z][5]=time[z][1];
                    }
 }
               //     System.out.println("index1:"+index1+"index2"+index2+"tt"+(trow-1));
                    index2=index1;



                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }


               }
               System.out.println(countt+"   sec");
               SimpleDateFormat dff = new SimpleDateFormat("HH:mm:ss"); //for increase time
               //Calendar call = Calendar.getInstance(); //for increase time
               int index3=1,index4=0,index5=0,counttt=0;
               String str5=tst[0][3],str6=tst[0][3];

               while(index5!=trow){
                 counttt=0;
                   while(Integer.parseInt(tst[index4][5])==index3 ){
                   try{
                  if(str5.compareTo(dff.format(dff.parse(tst[index4][3])))==0)
                      counttt++;
                       while(str5.compareTo(dff.format(dff.parse(tst[index4][2])))==0){
                           tst[index4][6]=Integer.toString(counttt);

                           index4++;
                            if(index4==trow){
                                index4--;
                                str5="1";
                                index5=trow;
                                index3=1;
                            }


//System.out.println(str5+" ,"+dff.format(dff.parse(test[index4][2]))+ " ,"+ test[index4][5]+"  ,"+index3+"   ,"+ index5);
                       }


                       if(str5.compareTo("1")!=0){
                        Date dd=dff.parse(str5);
                      //  System.out.println(str5);
                        cal.setTime(dd);
                    cal.add(Calendar.SECOND, 1);
                    str6=dff.format(cal.getTime());
                     str5=str6;
                       }
                //    System.out.println(str5);

System.out.println(index3+"   bor");

                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                //   System.out.println(index5+"  ,"+index3);

                   }

                   index3++;


               }
             for(int j=0; j<trow;j++){

            tst[j][2]=tst[j][5];
            tst[j][5]=tst[j][6];

               }
                          String   outputfile ="D:/elmi/payan name/dataset/time.txt";
            try{
               FileWriter wf = new FileWriter(outputfile);
                for(int m = 0; m < tir ; m++)
         {
            for (int j=0; j<2; j++)
             {
                 wf.append(String.valueOf(time[m][j]));
                 if(j<1)
                 wf.append(',');
             }
          //   ..  writer.append(Matrix[i][j]);
            wf.append('\r');
               wf.append('\n');
               wf.flush();
         }
         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    }

    public String [][] Retst(){
        return tst;
    }
    public String [][] Retime(){
        return time;
    }

    public void AttackMp(String attack [][] , String time [][] , String IP [][], int IProw, int ATrow , int testrow ){
        int hour, min, sec;
              for (int j=0 ; j<ATrow ; j++){   //mapping start time

                     attack[j][0] =  Integer.toString(SearchIp(attack[j][0].substring(0, 5) , time , testrow));
                     hour = Integer.parseInt(attack[j][1].substring(0, 2)) * 60;
                     min = Integer.parseInt(attack[j][1].substring(3, 5));
                     sec = Integer.parseInt(attack[j][1].substring(6, 8)) / 60;
                     min = hour + min + sec;
                     if(min>=1)
                         attack[j][1] = Integer.toString(Integer.parseInt(attack[j][0]) + min);
                     else
                         attack[j][1] = attack[j][0];

                    }

                for (int j=0 ; j<ATrow ; j++){//mapping IP

                     attack[j][4] =  Integer.toString(SearchIp(attack[j][4] , IP , IProw));

                    }
               for (int j=0 ; j<ATrow ; j++){//mapping IP

                     attack[j][5] =  Integer.toString(SearchIp(attack[j][5] , IP , IProw));

                    }
                       try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/attackmap1.txt"); //write to csv file
                for(int m = 0; m < ATrow; m++)  //write matrix train to csv file
         {
            for (int j=0; j<7; j++)
             {
                 wf.append(String.valueOf(attack[m][j]));
                 if(j<6)
                 wf.append(',');
             }
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

    public void DataMp(int testrow , int IProw , String IP [][] , String test [][]){
              for (int j=0 ; j<testrow ; j++){   //mapping dest ip
                     train[j][0] =   SearchIp(test[j][1] , IP , IProw);
                    }
                  for (int j=0 ; j<testrow ; j++){   //mapping src ip
                     train[j][1] =   SearchIp(test[j][0] , IP , IProw);
                    }
                   for(int k=0 ; k<testrow ; k++){
             train[k][2] = Integer.parseInt(test[k][2]); // time
                             }
            for(int k=0 ; k<testrow ; k++){
               if(Integer.parseInt(test[k][4])<0)
                   train[k][3]=0;
               else
             train[k][3] = Integer.parseInt(test[k][4]); // destPort
                             }
                 for(int k=0 ; k<testrow ; k++){
                     if(Integer.parseInt(test[k][3])<0)
                   train[k][4]=0;
               else
             train[k][4] = Integer.parseInt(test[k][3]); // srcPort
                             }
                     for(int k=0 ; k<testrow ; k++){
             train[k][5] = Integer.parseInt(test[k][5]); // srcPort
                             }



            try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/datamap1.txt"); //write to csv file
                for(int m = 0; m < testrow; m++)  //write matrix train to csv file
         {
            for (int j=0; j<6; j++)
             {
                 wf.append(String.valueOf(train[m][j]));
                 if(j<5)
                 wf.append(',');
             }
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
    public static void CrData(int testrow){

              temp = new int[testrow][6]; // [][0]-->dest, [][1]-->src, [][2]-->tag, [][3]-->dport, [][4]-->Sport
               int start = train[0][2];
               int end = train[testrow-1][2];
               int d= train[0][0] , s=train[0][1]; //initialize dest and src IP
               int dp=train[0][3], sp=train[0][4];
               int c=0 ,t=0 , count=0 , n=0 ;
               val = new int[testrow][7]; //[][0]-->dest, [][1]-->src, [][2]-->pckCount, [][3]-->time
               while(start<=end){
                   for(int p=0; p<testrow ; p++){
                       if(train[p][2]==start){
                          c++;

                       }
                   }

     int start1=train[t][5],start2=1;


     int end1=train[c-1][5];
     int cc=0 , tt=0;


     while(start1<=end1){
                   for(int l=t; l<c ; l++){

                       if(train[l][5]==start1){
                          temp[cc][0]=train[l][0];//dest
                          temp[cc][1]=train[l][1];//src
                          temp[cc][3]=train[l][3];//dport
                          temp[cc][4]=train[l][4];//sport
                          temp[cc][5]=train[l][5];
                          cc++;

                       }
                   }



                       for(int m =tt ; m<cc ; m++ ){


                           if(temp[m][2]!=1){
                               d=temp[m][0];
                               s=temp[m][1];
                               dp = temp[m][3];
                               sp = temp[m][4];
                               n++;


                      for(int h=tt ; h<cc ; h++){
                           if(d==temp[h][0] && s==temp[h][1] && dp==temp[h][3] && sp==temp[h][4] && temp[h][2]!=1){
                               temp[h][2]=1; //tag
                               count++;

                           }

                       }

                           val[n-1][2]=count;

                           }
                           count=0;

                           val[n-1][0]=d;
                            val[n-1][1]=s;

                           val[n-1][3]=start;
                           val[n-1][4] = dp;
                           val[n-1][5] = sp;
                           val[n-1][6] =start1;



                           }



start1++;
tt=cc;
                   }

                   for(int q=0; q<cc ; q++){
            temp[q][0]=0;//dest
                          temp[q][1]=0;//src
                          temp[q][2]=0;
                          temp[q][3]=0;//dport

                          temp[q][4]=0;//sport
                          temp[q][5]=0;
      }
//System.out.println("cc="+c);
                   t=c;
                   start++;
               }


                   String   outputfile ="D:/elmi/payan name/dataset/mydataset7f1.txt";
            try{
               FileWriter wf = new FileWriter(outputfile);
                for(int m = 0; m < testrow && val[m][0]!=0 ; m++)
         {
            for (int j=0; j<6; j++)
             {
                 wf.append(String.valueOf(val[m][j]));
                 if(j<5)
                 wf.append(',');
             }
          //   ..  writer.append(Matrix[i][j]);
            wf.append('\r');
               wf.append('\n');
               wf.flush();
         }
         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }
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
