

package processdata;
import java.io.*;
import java.util.*;
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

public class Mat {

    static int [] sortArray;
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
 String username = "root"; // mySQL username
    	String password = "123456"; // mySQL password
    	String url = "jdbc:mysql://localhost"; // Connect to database IDS
    	Class.forName("com.mysql.jdbc.Driver");

    	 final Connection con = (Connection) DriverManager.getConnection(url, username, password); // Connect
    	final Statement stmt;
        stmt = (Statement) con.createStatement(); // for Query

        int STime=1;
        int ETime=3000;
        for(int q=STime;q<=ETime ;q++){
      //  String InFile1 = "D:/elmi/payan name/dataset/traindata 6thweek thursday 1998/1min/dataset"+q+".txt";
            String InFile1 = "D://elmi//payan name//dataset//testdata 1999 4th week//thursday//1min//data//dataset"+q+".txt";
      //  String InFile1 = "D:/elmi/data4.txt";

File file = new File(InFile1);
if(file.exists()){

        BufferedReader br = null;

        LinkedList<String[]> Te = new LinkedList<String[]>();//dataset

        String datarow;
        String [][] test = null; //test
        String [][] ttest=null;
        String [] distinctsrc;
      //  String [] distinctdest;
        String [] distinct2;
        int [][] temp = null;
        int [][] temp1;
        int [] temp2;

        int Atrow = 0 , testrow=0;
int total=0;
        try { //read test.txt

		br = new BufferedReader(new FileReader(InFile1));
                datarow = br.readLine();
if(datarow!=null){
                int i=1;
                         Te.addLast(datarow.split(","));//add to queue
		while ((datarow = br.readLine())!= null) {   //create IP matrix


                      i++;//column

                        Te.addLast(datarow.split(","));



		        // use comma as separator
		}

                        test =Te.toArray(new String[i][7]);

testrow = i;
ttest = new String[testrow][7];
 Te.clear();

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
query = "LOAD DATA LOCAL INFILE 'D://elmi//payan name//dataset//testdata 1999 4th week//thursday//1min//data//dataset"+q+".txt' INTO TABLE dataset FIELDS TERMINATED BY ',' lines terminated by '\r\n';";

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
query = "select * from IP into outfile 'D://elmi//payan name//dataset//distinct"+q+".txt'fields terminated by ',' lines terminated by '\r\n';";
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
String File = "D://elmi//payan name//dataset//distinct"+q+".txt";

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
//N=a;
 for(int k=0; k<a ; k++){//dest
     for(int j=0; j<a; j++){ //src
       if(k==j){
            Mat[k][j]=0;
            Mat1[k][j]=0;
         }
      else if(k!=j && Mat[k][j]==0 && Mat1[k][j]==0){
            for(int m=0 ; m<testrow ; m++){
               if(hArray[m]!=1 ){
//System.out.println("dis1"+ distinctdest[k][1] + "dis2" + distinctdest[j][1] + "val1:" + test[m][0] + "val2:" + test[m][1]  );
                if(distinctdest[k][1].compareTo(test[m][0])==0 && distinctdest[j][1].compareTo(test[m][1])==0    ){

                   Mat[k][j]=Integer.parseInt(test[m][2])+Mat[k][j];

                   Mat1[k][j]=1+Mat1[k][j];
                    hArray[m]=1;

                }
                    }
            }

        }
     }
 }

  try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/MatrixW"+q+".txt");
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

//tot=0;
try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/MatrixE"+q+".txt");
                for(int m = 0; m < a; m++)  //write matrix train to csv file
         {
            for (int z=0; z<a; z++)
             {
                 wf.append(String.valueOf(Mat1[m][z]));
                // tot=tot+Mat1[m][z];
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
 /*for(int k=0; k<testrow ; k++){

   //  total=Integer.parseInt(test[k][3])+total;
    if(Integer.parseInt(test[k][4])==0){
         
         ttest[total][0]=test[k][0];
         ttest[total][1]=test[k][1];
         ttest[total][2]=test[k][2];
         ttest[total][3]=test[k][3];
         ttest[total][4]=test[k][4];
         ttest[total][5]=test[k][5];
         ttest[total][6]=test[k][6];
         total++;
     }


 }
 try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/traindata 7thweek friday 1998/10min/normal data/normal"+q+".txt");
                for(int m = 0; m < total; m++)  //write matrix train to csv file
         {
            for (int z=0; z<7; z++)
             {
                 wf.append(String.valueOf(ttest[m][z]));
                 if(z<6)
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
 */

// System.out.println("total="+total);
/*****************************
 int ro=1;
if(ro==1){
  distinctdest = new String[testrow+testrow];
  distinctsrc = new String[testrow+testrow];
  distinct2 = new String[testrow+testrow];
 String [] distinct1 = new String[testrow+testrow];

 int category =0 , n=0 , b=0,f=0,ind=0;;
 for(int j=0; j<testrow;j++){
     distinct1[j]=test[j][2];//////inja

  //    System.out.println(distinct1[j]);
 }

for(int j=0 ; j<testrow ; j++){
     for(int k=0; k<testrow ; k++){

        // if(j==k ){
          //   distinct[ind]=distinct1[j];
            // System.out.println(distinct[ind]+" ,"+k);
            // ind++;
         //}
         if(distinct1[k]!=null && distinct1[j]!=null){
         if(distinct1[k].compareTo(distinct1[j])==0){
             if(j!=k)
                 distinct1[k]=null;
             else if(j==k)
             b++;
         }
         }

    }
  //   System.out.println(b);
    //   j=b-1;


     }
 int a=0;
 for(int k=0 ; k<testrow ; k++ ){
     if(distinct1[k]!=null){
         distinctdest[a]=distinct1[k];
         a++;

 //System.out.println("dis    "+(a-1)+" ," + distinct[a-1]);
     }
             }
// for(int j=0; j<testrow;j++){
  //   distinct2[j]=test[j][2];

  //    System.out.println(distinct1[j]);
 //}

//for(int j=0 ; j<testrow ; j++){
  //   for(int k=0; k<testrow ; k++){

        // if(j==k ){
          //   distinct[ind]=distinct1[j];
            // System.out.println(distinct[ind]+" ,"+k);
            // ind++;
         //}
    //     if(distinct2[k]!=null && distinct2[j]!=null){
      //   if(distinct2[k].compareTo(distinct2[j])==0){
        //     if(j!=k)
          //       distinct2[k]=null;
            // else if(j==k)
          //   b++;
         //}
         //}

    //}
  //   System.out.println(b);
    //   j=b-1;


     //}
// int a1=0;
// for(int k=0 ; k<testrow ; k++ ){
  //   if(distinct2[k]!=null){
    //     distinctsrc[a1]=distinct2[k];
      //   a1++;

 //System.out.println("dis    "+(a-1)+" ," + distinct[a-1]);
     //}
       //      }
//inja
 /*************************
  * int disSize=a;
 System.out.println(disSize);
 System.out.println(n);

 for(int j=0 ; j<testrow ; j++){
     for(int k=0; k<disSize+n ; k++){
         if(distinctdest[k].compareTo(test[j][2])==0 ){
              
           //  System.out.println(distinct[k]+"     ,"+j+"    ,"+k);
           //  k=disSize;
             category =1;



         }

     }
     //System.out.println(category);
     if(category==0 ){
         distinctdest[a+n]=test[j][2];
     // System.out.println(test[j][3]);

          n++;
     }

        category=0;

 }
 disSize = a+n;
 ***************************************/
/*********************************
 try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/distinctsrc"+q+".txt");
 *
                for(int m = 0; m < /*disSize*/ /*a; m++)  //write matrix train to csv file
         {

                 wf.append(String.valueOf(distinctdest[m]));

                 wf.append(',');
                 wf.append(Integer.toString(m));


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

/* try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/distinctsrcIP"+q+".txt");
                for(int m = 0; m < a; m++)  //write matrix train to csv file
         {

                 wf.append(String.valueOf(distinctsrc[m]));

                 wf.append(',');
                 wf.append(Integer.toString(m));


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

*/
/***************************
 int disSize=a;
 int [] hArray = new int [testrow];
 int y=0;
// if(a>=a1)
  //   n=a;
 //else if(a1>a)
   //  n=a1;
 int [][] Mat = new int[disSize][disSize];
// for(int k=0; k<a1 ; k++){//src
  //   for(int j=0; j<a; j++){ //dest
    //     Mat[k][j]=0;
     //}
     //}

 for(int k=0; k<disSize ; k++){//dest
     for(int j=0; j<disSize; j++){ //src
       if(k==j)
            Mat[k][j]=0;
      else if(k!=j && Mat[k][j]==0){
            for(int m=0 ; m<testrow ; m++){
               if(hArray[m]!=1 ){

                if(distinctdest[k].compareTo(test[m][0])==0 && distinctdest[j].compareTo(test[m][1])==0    ){

                   Mat[k][j]=Integer.parseInt(test[m][2])+Mat[k][j];

              //   Mat[k][j]=1+Mat[k][j];
                 //           Integer.parseInt(test[m][4]);
                    hArray[m]=1;
                   

                 //    System.out.println(m);
                }

//System.out.println(k+" ,"+j+" ," + Mat[k][j] );
                }


            }
            Mat[j][k]=Mat[k][j];
       //     System.out.println(j);
        }
     }
     System.out.println(k);
 }
/// for(int k=0; k<n;k++){
   //  for(int j=0; j<n; j++){
    //     System.out.print(Mat[k][j]+" ,");
     //}
     //System.out.print("\n");
 //}

     try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/Matrix"+q+".txt");
                for(int m = 0; m < disSize; m++)  //write matrix train to csv file
         {
            for (int z=0; z<disSize; z++)
             {
                 wf.append(String.valueOf(Mat[m][z]));
                 if(z<disSize-1)
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
  for(int j=0 ; j<testrow ; j++){
     distinctdest[j]=null;
 }
 
            }//if datarow!=null
  /* try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/traindata 7thweek friday 1998/10min/normal data/normal"+q+".txt");
                for(int m = 0; m < total; m++)  //write matrix train to csv file
         {
            for (int z=0; z<7; z++)
             {
                 wf.append(String.valueOf(ttest[m][z]));
                 if(z<6)
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
    }*/
         //   }//if datarow!=null


            }
            } catch  (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            }
        }
        }
        }//if file.exist

        System.out.println("Done");
    }

     public static void bubble_srt(int array[]) {
        int n = array.length;
        int k;
        sortArray = new int[n];
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                    swapNumbers(i, k, array);
                }
            }

        }
         printNumbers(array);
    }

    private static void swapNumbers(int i, int j, int[] array) {

        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

private static void printNumbers(int[] input) {
          int n = input.length;
        for (int i = 0; i < n; i++) {
            sortArray[i]  = input[i];
        }

    }
  public static int SearchIp(int ip , int[][] Sip , int size){

        int category=0 ;

        for(int i =0 ; i<size ; i++){
            if(Sip[i][1] == ip){
                category =i;
                i=size;
            }

        }
        return category;

}

}
