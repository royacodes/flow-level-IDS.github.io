

package processdata;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapData {

    String file1;
    String file2;
    int testrow , IProw;
    String [][] test ;
    String [][] IP ;
     public static String time[][];
       public static int [][] train;
     public static int [][] temp;
     public static String [][] val;
    int tir =0;
    int n=0;
    int N = 0; //Number of Node
    int R=10; //Number of Repea
    int population[][] = null,newpopulation[][] = null , temppopulation[][] = null ;
    int tot=0;
    String outfile;
    float tempMQ[];
    public void Map(String file,String Ipfile , String fn){
        file1 = file;
        file2 = Ipfile;
        outfile = fn;
        ReaData rIP = new ReaData(); //read distinct IP
        rIP.Read(file2, 2);
        IProw = rIP.RetRow();
        IP = rIP.RetArray();

        ReaData rTest = new ReaData(); //read org data
        rTest.Read(file1, 7);
        testrow = rTest.RetRow();
        test = rTest.RetArray();
        train = new int[testrow][6];
         for(int j=0; j<testrow; j++){

                   test[j][0]=test[j][1];//src IP
                   test[j][1]=test[j][2];//dest IP
                   test[j][2]=test[j][3];//protocol
                   test[j][3]=test[j][4].substring(11, 19);//time
                   test[j][4]=test[j][5];//src port
                   test[j][5]=test[j][6];//dest port

                     }
          SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); //for increase time
               Calendar cal = Calendar.getInstance();
               Calendar cal1 = Calendar.getInstance();//for increase time
               Calendar cal2 = Calendar.getInstance();//for increase time

               String str1=test[0][3]/*.substring(0, 5)*/;
               String str2;
               String str3=str1;
               String tp;

        

               int countt=0;

               int index1=0 ,index2=0 , b=0;
               while(index2<testrow-1){
                try {
                    int tag = 0;
                   // System.out.println(index2);
                     str1=test[index2][3]/*.substring(0, 5)*/;
                    str3=str1;
                    Date d = df.parse(str1);
                    cal.setTime(d);
                    cal.add(Calendar.SECOND, 1);
                    str2=df.format(cal.getTime());
                    Date dd=df.parse(str3);
                    cal1.setTime(dd);
     //System.out.println(str2+" min"+str1);
                     while(b==0 && index1<testrow-1 ){

                         if(str3.compareTo(str2)!=0 && index1<testrow-1 && cal1.before(cal) && tag!=1){

                          if((index1)+1 <testrow){
                         tp = test[(index1)+1][3];
                         Date ddd = df.parse(tp);
                         cal2.setTime(ddd);
                             }
                         if(cal2.before(cal1) ){
                             tag = 1;
                         }

                         index1++;
                         str3=test[index1][3]/*.substring(0,5)*/;
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
                    if(index1<testrow-1){
                    for(int z=index2 ; z<index1 ;z++){
                      //  time[z][1]= test[z][3]/*.substring(0, 5)*/;
                      //  time[z][0]= Integer.toString(countt); //matrix for mapping from attack table to time sery
                        tir++;
                       test[z][6]=Integer.toString(countt);
                      //  test[z][6]=time[z][1];
                    }
                    }
 else if(index1==testrow-1){
      for(int z=index2 ; z<=index1 ;z++){
                       // time[z][1]= test[z][3]/*.substring(0, 5)*/;
                        //time[z][0]= Integer.toString(countt); //matrix for mapping from attack table to time sery
                        tir++;
                       test[z][6]=Integer.toString(countt);
                     //   test[z][6]=time[z][1];
                    }
 }
               //     System.out.println("index1:"+index1+"index2"+index2+"tt"+(trow-1));
                    index2=index1;



                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }


               }
               DataMp();
               CrData();
    }
     public void DataMp(){
              for (int j=0 ; j<testrow ; j++){   //mapping dest ip
                     train[j][0] =   SearchIp(test[j][1] , IP , IProw);

                    }
                  for (int j=0 ; j<testrow ; j++){   //mapping src ip
                     train[j][1] =   SearchIp(test[j][0] , IP , IProw);
                    }
                   for(int k=0 ; k<testrow ; k++){
             train[k][2] = Integer.parseInt(test[k][6]); // time
                            }
            for(int k=0 ; k<testrow ; k++){
               if(Integer.parseInt(test[k][5])<0)
                   train[k][3]=0;
               else
             train[k][3] = Integer.parseInt(test[k][5]); // destPort
            }
                 for(int k=0 ; k<testrow ; k++){
                     if(Integer.parseInt(test[k][4])<0)
                   train[k][4]=0;
               else
             train[k][4] = Integer.parseInt(test[k][4]); // srcPort
                             }
                     for(int k=0 ; k<testrow ; k++){

             test[k][0] = Integer.toString(train[k][0]);
             test[k][1] = Integer.toString(train[k][1]);
             //test[k][3] = Integer.toString(train[k][2]);
             test[k][4] = Integer.toString(train[k][4]);
             test[k][5] = Integer.toString(train[k][5]);

                             }

 /*                  try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/datamap.txt"); //write to csv file
                for(int m = 0; m < testrow; m++)  //write matrix train to csv file
         {
            for (int j=0; j<7; j++)
             {
                 wf.append(test[m][j]);
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
    }*/
    }
    public void CrData(){
               int tt=0;
               int start = Integer.parseInt(test[0][6]);
               int end = Integer.parseInt(test[testrow-1][6]);
               String d= test[0][0] , s=test[0][1]; //initialize dest and src IP
               String dp=test[0][4], sp=test[0][5];
               String Prot = test[0][2];
               int c=0 ,t=0 , count=0  ;
               val = new String[testrow][8];
               temp = new int [testrow][1];
               while(start<=end){
                   for(int p=0; p<testrow ; p++){
                       if(train[p][2]==start){//inja irad daree
                          c++;

                       }
                   }
                      for(int m =t ; m<c ; m++ ){


                           if(temp[m][0]!=1){
                               d=test[m][0];
                               s=test[m][1];
                               Prot = test[m][2];
                               dp = test[m][4];
                               sp = test[m][5];
                               n++;


                      for(int h=t ; h<c ; h++){
                           if(d.compareTo(test[h][0])==0 && s.compareTo(test[h][1])==0 && Prot.compareTo(test[h][2])==0 && dp.compareTo(test[h][4])==0 && sp.compareTo(test[h][5])==0 && temp[h][0]!=1){
                               temp[h][0]=1; //tag
                               count++;

                           }

                       }

                           val[n-1][2]=Integer.toString(count);

                           }
                           count=0;

                           val[n-1][0]=d;
                            val[n-1][1]=s;

                           val[n-1][3]=test[c-1][3];//time
                           val[n-1][4] = dp;
                           val[n-1][5] = sp;
                           val[n-1][6] =Prot;
                            tt++;


                           }



start++;
t=c;
                   }
            //   System.out.println(tt);
              // System.out.println(val[0][0]);

                    String   outputfile =outfile;
            try{
               FileWriter wf = new FileWriter(outputfile);
                for(int m = 0; m < n  ; m++)
         {
            for (int j=0; j<7; j++)
             {
                 wf.append(val[m][j]);
                 if(j<6)
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
    public void TsData(int num){
               SimpleDateFormat df = new SimpleDateFormat("HH:mm"); //for increase time
               Calendar cal = Calendar.getInstance();
               Calendar cal1 = Calendar.getInstance();//for increase time
               Calendar cal2 = Calendar.getInstance();//for increase time

               String str1=val[0][3]/*.substring(0, 5)*/;
               String str2;
               String str3=str1;
               String tp;



               int countt=0;

               int index1=0 ,index2=0 , b=0;
               while(index2<n-1){
                try {
                    int tag = 0;
                   // System.out.println(index2);
                    str1=val[index2][3]/*.substring(0, 5)*/;
                    str3=str1;
                    Date d = df.parse(str1);
                    cal.setTime(d);
                    cal.add(Calendar.MINUTE, num);
                    str2=df.format(cal.getTime());
                    Date dd=df.parse(str3);
                    cal1.setTime(dd);
    // System.out.println(str2+" min"+str1);
                     while(b==0 && index1<n-1 ){

                         if(str3.compareTo(str2)!=0 && index1<n-1 && cal1.before(cal) && tag!=1){

                         if((index1)+1 <n){
                         tp = val[(index1)+1][3];
                         Date ddd = df.parse(tp);
                         cal2.setTime(ddd);
                             }
                         if(cal2.before(cal1) ){
                             tag = 1;
                         }

                         index1++;
                         str3=val[index1][3]/*.substring(0,5)*/;
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
                    if(index1<n-1){
                    for(int z=index2 ; z<index1 ;z++){
                      //  time[z][1]= test[z][3]/*.substring(0, 5)*/;
                      //  time[z][0]= Integer.toString(countt); //matrix for mapping from attack table to time sery
                        tir++;
                       val[z][7]=Integer.toString(countt);
                      //  test[z][6]=time[z][1];
                    }
                    }
 else if(index1==n-1){
      for(int z=index2 ; z<=index1 ;z++){
                       // time[z][1]= test[z][3]/*.substring(0, 5)*/;
                        //time[z][0]= Integer.toString(countt); //matrix for mapping from attack table to time sery
                        tir++;
                       val[z][7]=Integer.toString(countt);
                     //   test[z][6]=time[z][1];
                    }
 }
               //     System.out.println("index1:"+index1+"index2"+index2+"tt"+(trow-1));
                    index2=index1;



                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }


               }
        /*                    String   outputfile ="D:/elmi/payan name/dataset/mydatasett.txt";
            try{
               FileWriter wf = new FileWriter(outputfile);
                for(int m = 0; m < n  ; m++)
         {
            for (int j=0; j<8; j++)
             {
                 wf.append(val[m][j]);
                 if(j<7)
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
    }*/
    }
    public void convert(int num) throws ClassNotFoundException,
    SQLException {

        TsData(num);
        
        int index2 = 0;
        int index1 = 0;
        int temprow;
        int s;
        int STime =Integer.parseInt(val[0][7]);
        int ETime =Integer.parseInt(val[n-1][7]);


         String username = "root"; // mySQL username
    	String password = "123456"; // mySQL password
    	String url = "jdbc:mysql://localhost"; // Connect to database IDS
    	Class.forName("com.mysql.jdbc.Driver");

    	 final Connection con = (Connection) DriverManager.getConnection(url, username, password); // Connect
    	final Statement stmt;
        stmt = (Statement) con.createStatement(); // for Query

long sumdif =0;
Date dNow2 = new Date();
final SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS");


        System.out.println("Starting Time Date: " + ft.format(dNow2));
       for(int j=STime ; j<=ETime ; j++){
            try{
                System.out.println("index2=" + index2);
                if(index2<n-1){
           while(Integer.parseInt(val[index2][7]) == j && index2!=n-1)
               if(index2<n-1)
               index2++;
                }
           } catch(ArrayIndexOutOfBoundsException e){

           }

           temprow = (index2-index1);
            try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/dataset"+ Integer.toString(j)+".txt"); //write to csv file
                for(int m = index1; m < index2; m++)  //write matrix train to csv file
         {
            for (int z=0; z<7; z++)
             {
                 wf.append(val[m][z]);
                 if(z<6)
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
            String query="CREATE DATABASE dataa";
        try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
         query="use dataa";
        try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}

query = "create table dataset(srcIp varchar(50) DEFAULT NULL,destIp varchar(50) DEFAULT NULL, pn varchar(50) DEFAULT NULL, Protocol varchar(50) DEFAULT NULL,pckTime varchar(50) DEFAULT NULL,stcPort varchar(50) DEFAULT NULL,destPort varchar(50) DEFAULT NULL);";

try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "LOAD DATA LOCAL INFILE 'D://elmi//payan name//dataset//dataset"+j+".txt' INTO TABLE dataset FIELDS TERMINATED BY ',' lines terminated by '\r\n';";

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
query = "select * from IP into outfile 'D://elmi//payan name//dataset//distinct"+j+".txt'fields terminated by ',' lines terminated by '\r\n';";
     try {
							stmt.executeQuery(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
query = "drop database dataa";
try {
							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}
int disrow;
String File = "D://elmi//payan name//dataset//distinct"+j+".txt";

ReaData rIP = new ReaData(); //read distinct IP
        rIP.Read(File, 2);
        disrow = rIP.RetRow();
        String [][] distinctdest = new String [disrow][2];
        distinctdest = rIP.RetArray();


 int [] hArray = new int [testrow];
 int y=0;
 int a = disrow;
 int [][] Mat = new int[a][a];
 int [][] Mat1 = new int[a][a];
N=a;
 for(int k=0; k<a ; k++){//dest
     for(int q=0; q<a; q++){ //src
       if(k==q){
            Mat[k][q]=0;
            Mat1[k][q]=0;
         }
      else if(k!=q && Mat[k][q]==0 && Mat1[k][q]==0){
            for(int m=index1 ; m<index2 ; m++){
               if(hArray[m]!=1 ){
//System.out.println("dis1"+ distinctdest[k][1] + "dis2" + distinctdest[q][1] + "val1:" + val[m][0] + "val2:" + val[m][1]  );
                if(distinctdest[k][1].compareTo(val[m][0])==0 && distinctdest[q][1].compareTo(val[m][1])==0    ){

                   Mat[k][q]=Integer.parseInt(val[m][2])+Mat[k][q];

                   Mat1[k][q]=1+Mat1[k][q];
                    hArray[m]=1;
                   
                }
                    }
            }
          
        }
     }
 }

  try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/MatrixW"+j+".txt");
                for(int m = 0; m < a; m++)  //write matrix train to csv file
         {
            for (int z=0; z<a; z++)
             {
                 wf.append(String.valueOf(Mat[m][z]));
                 if(z<a-1)
                 wf.append(',');
             }

            wf.append('\r');
               wf.append('\n');
               wf.flush();
               System.out.println(m);
         } //end write
         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }

tot=0;
try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/MatrixE"+j+".txt");
                for(int m = 0; m < a; m++)  //write matrix train to csv file
         {
            for (int z=0; z<a; z++)
             {
                 wf.append(String.valueOf(Mat1[m][z]));
                 tot=tot+Mat1[m][z];
                 if(z<a-1)
                 wf.append(',');
             }

            wf.append('\r');
               wf.append('\n');
               wf.flush();
             //  System.out.println(m);
         } //end write
         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }
	Date dNow = new Date( );
       // final SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
        //ft.format(dNow);
       // System.out.println("Starting Time Date: " + ft.format(dNow));

 population=new int[R*N][N];
newpopulation=new int[R*N][N];
        Random random=new Random();
        Individual individal=new Individual();

       int []elitism=new int[N];
       float elit1,elit2 ;
       tempMQ=new float[R*N];
for(int i=0;i<R*N;i++){
     for(int q=0;q<N;q++){
        population[i][q]=random.nextInt(N)+1;}

}

       int tag[]= new int[R*N];
       individal.Fitness(Mat1, N,population,tot,tag , tempMQ);
       tempMQ= individal.getMQ();
       elit1=individal.p();
       elitism=individal.o();
        for(int i=0;i<R*N;i++){
           newpopulation[i]= individal.Tournment();
        }
        newpopulation=individal.Crossover(newpopulation);
        boolean areEquals=false;
        temppopulation = newpopulation;

        for(int r=0; r<R*N;r++){
            // Arrays.sort(population[r]);
            //Arrays.sort(temppopulation[r]);
            areEquals = Arrays.equals(population[r], temppopulation[r]);
            if(areEquals==true)
                tag[r]=1;
        }
        temppopulation=null;
       population=newpopulation;
int p=100;
if(N>=200)
    p=50;
if(N>=500)
    p=20;

        for(int NG=1;NG<p;NG++){

        individal.Fitness(Mat1, N,population,tot,tag , tempMQ); ///be jaye G, T
        tempMQ= individal.getMQ();
        elit2=individal.p();
        if(elit1<elit2){elit1=elit2;elitism=individal.o();}
        for(int i=0;i<R*N;i++){
           newpopulation[i]= individal.Tournment();
        //   System.out.println(i);
        }
        newpopulation=individal.Crossover(newpopulation);
          areEquals=false;
        temppopulation = newpopulation;
for(int t=0 ; t<R*N;t++){
    tag[t]=0;
}
        for(int r=0; r<R*N;r++){
            //Arrays.sort(population[r]);
            //Arrays.sort(temppopulation[r]);
            areEquals = Arrays.equals(population[r], temppopulation[r]);
            if(areEquals==true)
                tag[r]=1;
        }
        temppopulation=null;
       population=newpopulation;
      System.out.println("repeat       "+NG);
    }

System.out.print("\n");

       for(int h=0 ; h<N;h++)
System.out.print(elitism[h]+",");
System.out.println();
System.out.println(elit1);

         Graphic represent=new Graphic(a, elitism, Mat1,Mat ,j);
         represent.Mat();
Date dNow1 = new Date();
long diff = dNow1.getTime() - dNow.getTime();
diff = diff/(1000%60);

           index1=index2;
sumdif = diff + sumdif;
       }

        System.out.println("Starting Time Date: " + ft.format(dNow2));
Date dNow3 = new Date();
System.out.println("Ending Time Date: " + ft.format(dNow3));
long diff1 = dNow3.getTime() - dNow2.getTime();
System.out.println(dNow2.getTime());
System.out.println(dNow3.getTime());
System.out.println("Time diffrence whole: " + diff1/(1000%60));
System.out.println("Time diffrence genetic: " + sumdif/(1000%60));
    
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
