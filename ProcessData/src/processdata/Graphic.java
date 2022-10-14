/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package processdata;

import java.awt.*;
import java.io.FileWriter;
import javax.swing.*;
import java.util.*;


public class Graphic  {

   public int M[];
   public int k ,t ,b=0,b1=0;
   public int L[][];
   public int R[][];
   public int RR[][];
   public String TE[][];
   public int temp[];
   public int external[];
   public int tempi[];
   public Draw s;
   public int [] temp1;
   public int [] temp2;
   public int [] ext1;
   int count=0;
   public int w;
   public int tag =0;
  

    public Graphic(int n ,int A[] , int Q[][] , int QQ[][] ,int q){
        k=n;
        M=new int[k];
        R=new int [k][k];
        L= new int[k][k];
        temp = new int[k];
        external = new int[k];
        tempi = new int[k];
        M=A;
        R = Q;
        RR=QQ;
        w=q;
        temp1=new int[k];
        temp2=new int[k];
        ext1=new int[k];
        TE=new String[k][k+5];

    }
    public Graphic(){

    }
    public void Mat(){

        for(int i=0 ; i<k ;i++){
            for(int j =0; j<k ; j++){
                L[i][j]=0;
            }
        }
        for(int i=0 ; i<k;i++){
                L[M[i]-1][i]= 1;
        }
    //    for(int i=0 ; i<k; i++){
      //      for(int j=0 ; j<k; j++){
        //    System.out.print(L[i][j]+",");
        //}
          //  System.out.println("\n");
        //}
        int b=0,r=0 , b1 =0;
        int c=0;
        int total=0;
        for(int g=0; g<k ; g++){
    temp1[g]=0;
        temp2[g]=0;
        ext1[g] =0;
        }
        
        int count=0,count1=0;
for(int a=0;a<k;a++){//tedade yal
    for(int p=0;p<k; p++){
        count=RR[a][p]+count;//RR


    }
}
        for(int a=0;a<k;a++){//tedade yal
    for(int p=0;p<k; p++){
        count1=R[a][p]+count1;//RR


    }
}
        int s=0,cc=0 , tag1=0;
        for(int i=0 ; i<k ;i++){
            tag1=0;
            b1=0;
            for(int j =0 ; j<k ; j++){
                
                   
                if(L[i][j]==1){
                  //  temp[i]++; //tu har khushe chanta gereh
                    //tempi[b]=i;
                    temp[b]=j;
                    TE[cc][b]="["+j+"]";
                 //   System.out.print("["+temp[b]+"],");
                    System.out.println("b=  "+b+ "i=    "+i);
                    b++;
 tag1=1;
                }
 
               
            }//end for
            tag=0;
            if(tag1==1){
            for(int j=0 ; j<k ; j++){


                   if (temp[tag] != j) {

                    external[b1] = j;
                    System.out.println("b=  "+b1+ "node=    "+j);
                     (b1)++;

                }
 else if (temp[tag] == j)
                       tag++;
            }
            }
            if(b>s)
                s=b; //tedad sustunhaye TE


            for(int p=0; p<b ; p++){
                for(int q=0; q<b ; q++){
                    if(R[temp[p]][temp[q]]!=0  ){
                    temp1[i]=R[temp[p]][temp[q]]+temp1[i];
                    temp2[i]=RR[temp[p]][temp[q]]+temp2[i];
                  //  System.out.println("vazn"+temp[p]+" "+temp[q] +"   "+R[temp[p]][temp[q]]);
                    }
                }
            }

            for(int p=0; p<b ; p++){
                for(int q=0; q<b1 ; q++){
                    if(R[external[q]][temp[p]]!=0  ){
                    ext1[i]=R[external[q]][temp[p]]+ext1[i];

                    }
                }
            }
if(b!=0){
     r++;
     TE[cc][b]=Integer.toString(r);//shomare cluster
     TE[cc][b+1]=Integer.toString(temp1[i]);//vazne cluster
     TE[cc][b+2]=Integer.toString(temp2[i]);//tedad yale dakheli
     TE[cc][b+3]=Integer.toString((count-temp2[i]));//tedad yale khareji
     TE[cc][b+4]=Integer.toString((ext1[i]));//vazn khareji
          //  System.out.println(" "+r +" ,"+temp1[i]);//+temp2[i] ttedade yal
     cc++;

            total=total+temp2[i];
            }
for(int g=0; g<k ; g++){
    temp[g]=0;
    external[g]=0;
            }
            b=0;

           // System.out.println("\n");
        }//end for
        //TE ro mirizim tu file
        s=s+5;
         try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/Matrix"+w+".txt");
                for(int m = 0; m < cc; m++)  //write matrix train to csv file
         {
            for (int z=0; z<s; z++)
             {
                if(TE[m][z]!=null){
                 wf.append(TE[m][z]);
              //   if(z<s-1)
                 wf.append(',');
                 }
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
System.out.println(total+"total");

System.out.println("External Edge="+(count-total)); //yale kharejio mirizim tu file dige


      //  s = new Draw(k,temp ,L ,R);
        //s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end Mat




}
