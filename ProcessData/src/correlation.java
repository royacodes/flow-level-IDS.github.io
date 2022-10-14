
import java.io.*;
import java.util.*;
import java.lang.*;
import processdata.ReaData;
public class correlation {

   
    public static void main(String[] args) {
         String InFile1 = "D:/elmi/payan name/dataset/traindata 7thweek friday 1998/10min/org data/matrixvazn/matrix82.txt";
         String InFile2 = "D:/elmi/payan name/dataset/traindata 7thweek friday 1998/10min/org data/matrixedge/matrix2.txt";
         

          BufferedReader br = null;

        LinkedList<String[]> Te = new LinkedList<String[]>();//dataset
        LinkedList<String[]> Tee = new LinkedList<String[]>();

         String datarow;
        String [][] test = null; //test
        String [][] test1 = null; //test
        String [][] test2 = null; //test
        String [][] ttest = null; //test

        int [] array1 = null ;
        int [] array2 = null;
        int [] array3 = null;


         int testrow = 0 ,testrow1=0 , testrow2=0;

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

                        test =Te.toArray(new String[i][i]);

testrow1 = i;
 Te.clear();
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

               int count=0;
for(int a=0;a<testrow1;a++){//tedade yal
    for(int p=0;p<testrow1; p++){
        count=Integer.parseInt(test[a][p])+count;//RR


    }
}
               System.out.println(count);

                         try { //read test.txt

		br = new BufferedReader(new FileReader(InFile2));
                datarow = br.readLine();
                int i=1;
                         Te.addLast(datarow.split(","));//add to queue
		while ((datarow = br.readLine())!= null) {   //create IP matrix


                      i++;//column

                        Te.addLast(datarow.split(","));



		        // use comma as separator
		}

                        test1 =Te.toArray(new String[i][i]);

testrow2 = i;
 Te.clear();
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
      
         try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/Matrix.txt");



        for(int k=0 ; k< testrow1 ; k++){
            for(int j=0 ; j<testrow1 ; j++){

                if(Integer.parseInt(test[k][j])!=0 && Integer.parseInt(test1[k][j])!=0){
                   
                           wf.append(test[k][j]);

                 wf.append(',');
             wf.append(test1[k][j]);

            wf.append('\r');
               wf.append('\n');
               wf.flush();



                }
            }
                     }
                wf.close();
         } catch (Exception e)
    {
      e.printStackTrace();
    }
        
      
        


    }



}
