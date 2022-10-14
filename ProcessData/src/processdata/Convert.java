

package processdata;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Convert {

    static int [] sortArray;
    public static void main(String[] args) {

     //   String InFile1 = "D:/elmi/dataset1.txt";
        String InFile1 = "D:/elmi/payan name/dataset/mydataset7f1.txt";
      //  String InFile2 = "D:/elmi/payan name/dataset/traindata 7thweek friday 1998/10min/attackmap.txt";

        BufferedReader br = null;

        LinkedList<String[]> Te = new LinkedList<String[]>();//dataset
        LinkedList<String[]> At = new LinkedList<String[]>();//attack/train

        String datarow;
        String [][] test = null; //test
        String [][] attack = null ; //attackinfo1
        int [][] temp = null;
        int [][] temp1;
        int [] temp2;
        String [] distinct;

         int Atrow = 0 , testrow=0;

         try { //read test.txt

		br = new BufferedReader(new FileReader(InFile1));
                datarow = br.readLine();

                int i=1;
                         Te.addLast(datarow.split(","));//add to queue
		while ((datarow = br.readLine())!= null) {   //create IP matrix


                      i++;//column

                        Te.addLast(datarow.split(","));



		        // use comma as separator
		}
                        
                        test =Te.toArray(new String[i][6]);

testrow = i;


 Te.clear();


	} catch (FileNotFoundException e) {
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


     /*           try { //read ATTACKINFO1.txt

		br = new BufferedReader(new FileReader(InFile2));
                datarow = br.readLine();


                int i=1;
                         At.addLast(datarow.split(","));

                    //     System.out.println(row.size());
		while ((datarow = br.readLine()) != null) { //create test matrix

                 //
                   // row.clear();
                    i++;//column
                        At.addLast(datarow.split(","));

                       //  System.out.println(i);
		}



              attack = At.toArray(new String[i][7]); //[][0]-->frameNum, [][1]-->srcIP, [][2]-->destIP, [][3]-->protocol, [][4]-->pckTime, [][5]-->srcPort, [][6]-->destPort
Atrow=i;

      

             At.clear();



        } catch (FileNotFoundException e) {
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
	}*/

        
        int index1=0 ,index2=0 , s=0,co=0,atrow1=0;
        int temprow = 0;
/*String th [][] = new String [Atrow][6];
for(int k=0 ; k<Atrow ; k++){
    if(attack[k][0].compareTo(attack[k][1])!=0){
        for(int y=Integer.parseInt(attack[k][0]);y<=Integer.parseInt(attack[k][1]);y++) {
            th[co][0]=Integer.toString(y);
            th[co][1]=attack[k][2];
             th[co][2]=attack[k][3];
              th[co][3]=attack[k][4];
               th[co][4]=attack[k][5];
                th[co][5]=attack[k][6];

         co++;
         
        }
    }
}
atrow1=Atrow+co;
int ck=0;
String at [][]= new String[atrow1][6];
for(int k=0 ; k<Atrow ; k++){
    if(attack[k][0].compareTo(attack[k][1])==0){
        at[ck][0]=attack[k][0];
            at[ck][1]=attack[k][2];
             at[ck][2]=attack[k][3];
              at[ck][3]=attack[k][4];
               at[ck][4]=attack[k][5];
                at[ck][5]=attack[k][6];
                
ck++;
    }
        }
int co1=0;
for(int k=ck ; k<ck+co ; k++){
    if(co1<co){
     at[k][0]=th[co1][0];
            at[k][1]=th[co1][1];
             at[k][2]=th[co1][2];
              at[k][3]=th[co1][3];
               at[k][4]=th[co1][4];
                at[k][5]=th[co1][5];
                
                co1++;
                
    }
        }
atrow1=ck+co;
int arr[] = new int[atrow1];
for(int k=0; k<atrow1 ; k++){
    arr[k]=Integer.parseInt(at[k][0]);
   // System.out.println("k"+k);
}
bubble_srt(arr);
for(int k=0; k<atrow1 ; k++){
    
}
int indexx;
String at2 [][]= new String[atrow1][6];
for(int k=0 ; k<atrow1 ; k++){
    indexx=SearchIpp(arr[k] , at , atrow1);
    at2[k][0]=at[indexx][0];
    at2[k][1]=at[indexx][1];
    at2[k][2]=at[indexx][2];
    at2[k][3]=at[indexx][3];
    at2[k][4]=at[indexx][4];
    at2[k][5]=at[indexx][5];
    at[indexx][0]="0";
    
}*/

int ro=1;
if(ro==1){
       int STime =1;
        int ETime =38;

       for(int j=STime ; j<=ETime ; j++){
           try{
           while(Integer.parseInt(test[index2][3]) == j)
               index2++;
           } catch(ArrayIndexOutOfBoundsException e){

           }

index2 = index2-1;
           temprow = (index2-index1)+1;
           temp = new int[temprow][8];
           temp1 = new int[temprow][7];
           temp2 = new int[temprow];
s=index1;

           for (int m=0 ; m<temprow  ; m++){
               temp[m][0] = m+1;
               
                   temp[m][1] =Integer.parseInt(test[s][0]);
                   temp[m][2] =Integer.parseInt(test[s][1]);
                   temp[m][3] =Integer.parseInt(test[s][2]);
                  // temp[m][4] =0;
                   temp[m][4] =Integer.parseInt(test[s][4]);
                   temp[m][5] =Integer.parseInt(test[s][5]);
                   //System.out.println(at2[k][0]+","+at2[k][1]+","+at2[k][2]+","+at2[k][3]+","+at2[k][4]+","+at2[k][5]+","+k+","+indexx);
                   s++;
               
           }


//System.out.println(ck+" ,"+co+" ," + co1 + " ,"+Atrow+ " ,"+atrow1);

      /*     for(int k=0 ; k<atrow1 ; k++){
//System.out.println(at2[k][0] +" ,"+k);
               if(at2[k][0].compareTo(Integer.toString(j))==0 ){
                   for(int n = 0; n< temprow ; n++){
                       
                       if(at2[k][3].compareTo(Integer.toString(temp[n][2]))==0 && at2[k][4].compareTo(Integer.toString(temp[n][1]))==0  && at2[k][1].compareTo(Integer.toString(temp[n][6]))==0 && at2[k][2].compareTo(Integer.toString(temp[n][5]))==0 ){
                         // System.out.println(at2[k][0]);
                         //  System.out.println(at2[k][3]+","+temp[n][2]+","+at2[k][4]+","+temp[n][1]+","+at2[k][1]+","+temp[n][6]+","+at2[k][2]+","+temp[n][5]+","+k+","+n);
                         //  temp[n][4]= Integer.valueOf(attack[k][3].substring(0, 1));
                           if(at2[k][5].compareTo("neptune")==0){
                              temp[n][4]=1;
                              System.out.println("neptune");
                           }
                           if(at2[k][5].compareTo("smurf")==0)
                               temp[n][4]=2;
                           if(at2[k][5].compareTo("back")==0)
                               temp[n][4]=3;

                       }
                                                   
                   }
               }


           }*/
for(int k=0 ; k<temprow; k++){
    temp2[k]=temp[k][1];
    //System.out.println(temp2[k]);
           }
bubble_srt(temp2);
int index;
for(int k=0 ; k<temprow ; k++){
    index=SearchIp(temp2[k] , temp , temprow);
    if(temp[index][7]!=1){
        temp1[k][0]= temp[index][0];
        temp1[k][1]= temp[index][1];
        temp1[k][2]= temp[index][2];
        temp1[k][3]= temp[index][3];
        temp1[k][4]= temp[index][4];
        temp1[k][5]= temp[index][5];
       // temp1[k][6]= temp[index][6];
        temp[index][7]= 1;
        temp[index][1]= 0;
    }
}
for(int k=0 ; k<temprow; k++){

  //  System.out.println(sortArray[k]);
           }

            try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/dataset"+ Integer.toString(j)+".txt"); //write to csv file
                for(int m = 0; m < temprow; m++)  //write matrix train to csv file
         {
            for (int z=0; z<6; z++)
             {
                 wf.append(String.valueOf(temp1[m][z]));
                 if(z<5)
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
          // for(int a=0; a<temprow ; a++)
            //System.out.println(temp[a][0] + " ," + temp[a][1] + " ," + temp[a][2] + " ,"+ temp[a][3] + " ," + temp[a][4] + " ," +temp[a][5] + " ," + temp[a][6]);
 index2++;
index1=index2;
//System.out.println(index1);
        }
        }

             //   for(int t=0; t< temprow ; t++){
               //     for(int q=0 ; q<temprow ; q++){

                 //   }
             //   }

        
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
//System.out.println("n"+m);
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

  public static int SearchIpp(int ip , String [][] Sip , int size){

        int category=0 ;

        for(int i =0 ; i<size ; i++){
            if(Integer.parseInt(Sip[i][0]) == ip){
                category =i;
                i=size;
            }

        }
        return category;

}

}
