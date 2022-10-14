

package processdata;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaData {

    public static String file1;
    public static int row, roww, counter=0;
    public static BufferedReader br = null;
    public static LinkedList<String[]> rows = new LinkedList<String[]>();
    public static LinkedList<String[]> rows1 = new LinkedList<String[]>();
    public static String datarow;
    public static String [][] test = null;
    public static String [][] ttest = null;
    public static String [][] test1 = null;

    public ReaData(){

    }
    public void Read(String file , int col){
        file1=file;
        try {

		br = new BufferedReader(new FileReader(file1));
                datarow = br.readLine();

                int i=1;
                         rows.addLast(datarow.split(","));//add to queue
		while ((datarow = br.readLine())!= null ) {

                      i++;//column

                      if (i<=1200083)

                      rows.addLast(datarow.split(",")); // use comma as separator
                     


		}
                          if(i<1200083){
     test =rows.toArray(new String[i][col]);
                        row = i;
 }
           else if(i >= 1200083)
            {
             //         i= 1200083;
               //         test =rows.toArray(new String[i][col]);
                 //      row = i;
                 rows.clear();
                        try {

		br = new BufferedReader(new FileReader(file1));
                datarow = br.readLine();

                i=1;
                         rows.addLast(datarow.split(","));//add to queue
		while ((datarow = br.readLine())!= null ) {

                      i++;//column

                      if(i>=1200083)

                      rows.addLast(datarow.split(",")); // use comma as separator



		}
                         row= i - 1200083;
                         test =rows.toArray(new String[row][col]);


            }
                         catch (FileNotFoundException e) {
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
        rows.clear();
    }
    public String [][] RetArray(){
        return test;
    }
    public int RetRow(){
        return (row);
    }
}
