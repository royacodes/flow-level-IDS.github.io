

package processdata;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class proc {

    
    public static void main(String[] args) {


        String file1 = "C:/Users/Roya/6thweekth.txt";
        String [][] test = null;
        String [][] ttest = null;
        String [][] IP = null;
        String outputfile ="D:/elmi/payan name/dataset/6thweekth.txt";
        int testrow=0 , co=0;

         ReaData rTest = new ReaData(); //read org data
        rTest.Read(file1, 7);
        testrow = rTest.RetRow();

        test = rTest.RetArray();
        ttest = new String [testrow][7];
        for(int i=0; i<testrow;i++){
            if(test[i].length == 5 ){
                System.out.println(test[i][0]+", bb");
                ttest[co][0] = test[i][0];
                ttest[co][1] = test[i][1];
                ttest[co][2] = test[i][2];
                ttest[co][3] = test[i][3];
                ttest[co][4] = test[i][4];
                ttest[co][5] = Integer.toString(0);
                ttest[co][6] = Integer.toString(0);
                co++;
            }
            if(test[i].length == 7  ){
            //    System.out.println(test[i][0]+", nn");
                if(!(test[i][4].contains(":")) && !(test[i][3].contains(":"))){

                ttest[co][0] = test[i][0];
                ttest[co][1] = test[i][1];
                ttest[co][2] = test[i][2];
                ttest[co][3] = test[i][5];
                ttest[co][4] = test[i][6];
                ttest[co][5] = Integer.toString(0);
                ttest[co][6] = Integer.toString(0);
                co++;
                }
 else{
                ttest[co][0] = test[i][0];
                ttest[co][1] = test[i][1];
                ttest[co][2] = test[i][2];
                ttest[co][3] = test[i][3];
                ttest[co][4] = test[i][4];
                ttest[co][5] = test[i][5];
                ttest[co][6] = test[i][6];
                co++;
                }
            }
        }

        try{
               FileWriter wf = new FileWriter(/*"D:/elmi/payan name/dataset/Fitness"+q+".txt"*/outputfile);
                for(int m = 0; m < co ; m++)  //write matrix train to csv file
         {
            for (int z=0; z<7; z++)
             {

                 wf.append(ttest[m][z]);
                 if(z<6)
                 wf.append(',');
                 }
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

}
