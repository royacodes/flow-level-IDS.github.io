
import java.io.*;
import java.util.*;

public class Attack {

   
    public static void main(String[] args) {

        String InFile1 = "D:/elmi/payan name/dataset/tcpdump.list";

         BufferedReader br = null;

        LinkedList<String[]> Te = new LinkedList<String[]>();//dataset
        LinkedList<String[]> Tee = new LinkedList<String[]>();

         String datarow;
        String [][] test = null; //test
        String [][] ttest = null; //test


         int testrow = 0 ,ttestrow=0;
         File f = new File(InFile1);
         if(f.exists()){
          try { //read test.txt

		br = new BufferedReader(new FileReader(InFile1));
                datarow = br.readLine();

                int i=1;
                         Te.addLast(datarow.split(" "));//add to queue
		while ((datarow = br.readLine())!= null) {   //create IP matrix


                      i++;//column

                        Te.addLast(datarow.split(" "));



		        // use comma as separator
		}
                        test =Te.toArray(new String[i][12]);

testrow = i;
Te.clear();
int j =0;
ttest = new String [testrow][7];
//System.out.println(Integer.toString(Integer.parseInt(test[0][8].substring(12, 15))));//0-3,4-7,8-11,12-15
for(int k=0 ; k<testrow ; k++){
    
    if(Integer.parseInt(test[k][10])!=0){
        ttest[j][0]=test[k][3];
        ttest[j][1]=test[k][4];
        if(test[k][6].equals("-"))
            ttest[j][2]="0";
       else
        ttest[j][2]=test[k][6];
       if(test[k][7].equals("-"))
           ttest[j][3]="0";
       else
        ttest[j][3]=test[k][7];
        ttest[j][4]=Integer.toString(Integer.parseInt(test[k][8].substring(0, 3)))+"."+Integer.toString(Integer.parseInt(test[k][8].substring(4, 7)))+"."+Integer.toString(Integer.parseInt(test[k][8].substring(8, 11)))+"."+Integer.toString(Integer.parseInt(test[k][8].substring(12, 15)));
        ttest[j][5]=Integer.toString(Integer.parseInt(test[k][9].substring(0, 3)))+"."+Integer.toString(Integer.parseInt(test[k][9].substring(4, 7)))+"."+Integer.toString(Integer.parseInt(test[k][9].substring(8, 11)))+"."+Integer.toString(Integer.parseInt(test[k][9].substring(12, 15)));
        ttest[j][6]=test[k][11];

        j++;
    }
}
for(int k=0 ; k<j ; k++){
   System.out.println(ttest[k][0] + " ," + ttest[k][1] + " ," + ttest[k][2] + " ," + ttest[k][3] + " ,"+  ttest[k][4] + " ," + ttest[k][5] + " ," + ttest[k][6]);
}
 try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/Attackinfor.txt");
                for(int m = 0; m < j; m++)  //write matrix train to csv file
         {

                 wf.append(ttest[m][0]);
                 wf.append(',');
                 wf.append(ttest[m][1]);
                 wf.append(',');
                 wf.append(ttest[m][2]);
                 wf.append(',');
                 wf.append(ttest[m][3]);
                 wf.append(',');
                 wf.append(ttest[m][4]);
                 wf.append(',');
                 wf.append(ttest[m][5]);
                 wf.append(',');
                 wf.append(ttest[m][6]);




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
        }
    }

}
