package processdata;


import java.io.*;
import java.util.*;
import java.math.*;

public class great {

   
    public static void main(String[] args) {

        int STime=1;
        int ETime=3000;
        String [][] distance=new String[ETime][2];
        String [][] distancee=new String[ETime][2];
        String [][] ttest=new String[ETime][7];
        String [][] ttest1=new String[ETime][7];
        String [][] ttest2=new String[ETime][2];
        int c=0 ,cc=0;
        int tp=0, fn=0, tn=0, fp=0;
        int g=0;
float E=(float) 0;
        float a1=0,a2=0,a3=0,a4=0,a5=0;

        int qp=0 , ff=0,t=0;
        float sumext=0 , sum2=0;
        int node = 0;
        float s6 = 100000;
        
        for(int q=STime;q<=ETime ;q++){

        
      //  String InFile1 = "D:/elmi/payan name/dataset//matrix"+q+".txt";
            
            String InFile1 = "D://elmi//payan name//dataset//testdata 1999 5th week//thursday//1min//cluster//matrix"+q+".txt";

        BufferedReader br = null;

        LinkedList<String[]> Te = new LinkedList<String[]>();//dataset
        LinkedList<String[]> Tee = new LinkedList<String[]>();

         String datarow;
        String [][] test = null; //test
       

        // int testrow = 0 ,ttestrow=0;
         File f = new File(InFile1);
         if(f.exists()){

             g++;
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

int testrow = i;
int len=0 , ll=0;
Te.clear();

/*int great=0 , index;
for(int k=0 ; k<testrow ; k++){
    for(int j=0; j<testrow ; j++){
        if(Integer.parseInt(test[k][j])>great){
            great=Integer.parseInt(test[k][j]);

        }
    }
}
ttest[c][0]=Integer.toString(q);
ttest[c][1]=Integer.toString(great);
c++;*/
int nodet =0;
sumext=0;
  for(int k=0;k<testrow;k++){
        len=test[k].length;
        ll=0;
        node=0;
       
        for(int j=0; j<len;j++){

            if((test[k][j].contains("["))){
                node++;
                nodet++;
            }
            if(!(test[k][j].contains("["))){
               //  System.out.print(test[k][j]+ "  ,");
             test[k][ll]=test[k][j];
           //   System.out.print(test[k][ll]+ "  ,");
               ll++;
            }
        }
        test[k][ll]=Integer.toString(node);
    }
System.out.println("ll    "+ll);
float s=0,s2=0 , s5=0;
int s1=0, s3=0 , s4=0;
float sum=0,sum1=0,prob=0,prob1=0,ent=0,ent1=0;
int b=0 , b1=0;
sum2=0;
for(int k=0;k<testrow;k++){
    sum = Integer.parseInt(test[k][1])*Integer.parseInt(test[k][2])+sum;
    sum1 = Integer.parseInt(test[k][1])+sum1;//yal
    sum2 = Integer.parseInt(test[k][2])+sum2;
    sumext = (Float.parseFloat(test[k][4]) / Float.parseFloat(test[k][5]))+sumext;//vazn
    System.out.println("eeeeee:    "+ Integer.parseInt(test[k][4]) + "ent:    "+sumext + " hjh  "+q);
  //  for(int j=0;j<ll;j++){
  /*  sum = Integer.parseInt(test[k][1])+Integer.parseInt(test[k][4]);
    sum1 = Integer.parseInt(test[k][2])+Integer.parseInt(test[k][3]);
    
    prob=Integer.parseInt(test[k][1])/sum;
    prob1=Integer.parseInt(test[k][2])/sum1;
    System.out.println(Math.log(prob)+"   "+prob);
    if(prob== 0) ent=ent;
    else
    ent=                (float) (prob * Math.log(prob) * (-1))+ent;
    if(prob1==0) ent1=ent1;
    else
    ent1=                (float) (prob1 * Math.log(prob1) * (-1))+ent1;*/
  //  if(Float.parseFloat(test[k][1]) !=0){
        if(Float.parseFloat(test[k][1])>s){ //max yal
           // s=Integer.parseInt(test[k][1]);
            s = Float.parseFloat(test[k][1]);
            s1=k;
        }
   // }
    if(Integer.parseInt(test[k][2])>s2){ //max vazn
            s2=Integer.parseInt(test[k][2]);
            s3=k;
        }

    if(Float.parseFloat(test[k][4])/Float.parseFloat(test[k][5])> s5 ){ //max vazn
            s5=Float.parseFloat(test[k][4])/Float.parseFloat(test[k][5]);
            s4=k;
            System.out.println("s5+          "+s5);
        }
        
    //}
}
ent1=0;
for(int k=0; k<testrow;k++){
   // if(k>0){
   //   ent=                  (float) Math.sqrt(Math.pow(Integer.parseInt(test[s1][1]) - Integer.parseInt(test[k][1]), 2) + Math.pow(Integer.parseInt(test[s1][2]) - Integer.parseInt(test[k][2]), 2))+ent;
       //ent=             (float) Math.pow((Integer.parseInt(test[s1][1]) - Integer.parseInt(test[k][1])) , 2) + (float) Math.pow((Integer.parseInt(test[s1][2]) - Integer.parseInt(test[k][2])) , 2);
       //ent =            (float) Math.sqrt(ent);
    ent = (Integer.parseInt(test[s1][1]) - Integer.parseInt(test[k][1]));
       if(ent1<ent){
           ent1=ent;
       }
   // }
}


//testrow++;
float we= Integer.parseInt(test[s1][2])+Integer.parseInt(test[s1][3]);
float we1= Integer.parseInt(test[s1][1])+Integer.parseInt(test[s1][4]);
prob1=we1 - sum1;
prob= sum/(sum2 * sumext);
ent1 = testrow;
//ttest[c][4]=Float.toString( prob);
//ttest[c][4]=Float.toString(Float.parseFloat(test[s4][4]) * testrow);
ttest[c][4]=Float.toString(Float.parseFloat(test[s1][1])/Float.parseFloat(test[s1][5]));

if(prob> a4){
    a4 = prob;
}

 else{
   //  ttest1[cc][4]=Integer.toString(0);
    /* if(q!=qp && ff==0 &&t==0){
     if(q == 412 || q == 413 || q== 414 || q== 415 || q==416 || q==417 || q==419 || q == 423 ){
        fp++;
        ff=1;
        qp=q;
    }
    else{
        tn++;
        ff=1;
         }
    }*/
              }

ttest[c][0]=Integer.toString(q);
//ttest1[c][0]=Integer.toString(q);
//ttest[c][1]=Integer.toString(testrow);

//ttest[c][1]=Float.toString((testrow));
if(testrow> a1){
    a1 = testrow;
}
if(testrow > 21){
//    ttest1[c][1]=Integer.toString(1);
  /*  if(q!=qp && t==0 && ff==0)
    {
        if(q == 412 || q == 413 || q== 414 || q== 415 || q==416 || q==417 || q==419 || q == 423 ) {
        tp++;
        t=1;
        qp=q;
    }
    else{
        fn++;
        t=1;
        }
    }*/
}
 else{
  //   ttest1[c][1]=Integer.toString(0);
    /* if(q!=qp && ff==0 && t==0){
     if(q == 412 || q == 413 || q== 414 || q== 415 || q==416 || q==417 || q==419 || q == 423 ){
        fp++;
        ff=1;
        qp=q;
    }
    else{
        tn++;
        ff=1;
         }
    }*/
              }
ttest[c][3]=Float.toString(/*Integer.parseInt(test[s4][4])*/testrow);
if(Integer.parseInt(test[s3][2])> a3){
    a3 = Integer.parseInt(test[s1][1]);
}
if(Integer.parseInt(test[s3][2]) > 80){
  //  ttest1[c][3]=Integer.toString(1);
  /*  if(q!=qp && t==0 && ff==0){
    if(q == 412 || q == 413 || q== 414 || q== 415 || q==416 || q==417 || q==419 || q == 423 ){
        tp++;
        t=1;
        qp=q;
    }
    else{
        fn++;
        t=1;
        }
    }*/
}
 else{
 //    ttest1[c][3]=Integer.toString(0);
   /*  if(q!=qp && ff==0 && t==0){
     if(q == 412 || q == 413 || q== 414 || q== 415 || q==416 || q==417 || q==419 || q == 423 ){
        fp++;
        ff=1;
        qp=q;
    }
    else{
        tn++;
        ff=1;
         }
    }*/
              }

ttest[c][2]=Float.toString(Float.parseFloat(test[s1][1]))/*Float.toString(ent1)*/;

if(Integer.parseInt(test[s1][1])> a2){
    a2 = Integer.parseInt(test[s1][1]);
}
if(Integer.parseInt(test[s1][1]) > 600){
 //   ttest1[c][2]=Integer.toString(1);
   /* if(q!=qp && t==0 && ff==0){
    if(q == 412 || q == 413 || q== 414 || q== 415 || q==416 || q==417 || q==419 || q == 423 ){
        tp++;
        t=1;
        qp=q;
    }
    else{
        fn++;
        t=1;
        }
    }*/
}
 else{
//     ttest1[c][2]=Integer.toString(0);
   /*  if(q!=qp && ff==0 && t==0){
     if(q == 412 || q == 413 || q== 414 || q== 415 || q==416 || q==417 || q==419 || q == 423 ){
        fp++;
        ff=1;
        qp=q;
    }
    else{
        tn++;
        ff=1;
         }
    }*/
              }
//ttest[c][5]=Float.toString((Float.parseFloat(test[s1][1])/Float.parseFloat(test[s1][5])));
//if(Float.parseFloat(test[s4][1]) == (float)0)
//ttest[c][5]=Float.toString( (Float.parseFloat(test[s4][4]) * sum1)/(Float.parseFloat(test[s4][1])  + E) *sumext );
//else
  //  ttest[c][5]=Float.toString( (Float.parseFloat(test[s4][4]) * sum1)/(Float.parseFloat(test[s4][1]) * sumext) );
//if(Math.abs(sumext - Float.parseFloat(test[s4][4]) ) == (float) 0 && (int) Math.abs(sumext - Float.parseFloat(test[s4][4]) ) == 0)
String ttest5=null;
if(Math.abs(sumext - (Float.parseFloat(test[s4][4])/Float.parseFloat(test[s4][5]))) ==0)
    E= (float) 0.5;
ttest5=Float.toString( ((Float.parseFloat(test[s4][4]))/Float.parseFloat(test[s4][5])) / (float) Math.abs(sumext - (Float.parseFloat(test[s4][4])/Float.parseFloat(test[s4][5]))+E ));
System.out.println("tt==       "+Math.abs(sumext - (Float.parseFloat(test[s4][4])/Float.parseFloat(test[s4][5]))));
E = (float) 0;
if(Float.parseFloat(ttest5) == (float) 0)
    E=(float) 0.5;
ttest[c][5] = Float.toString(Float.parseFloat(ttest[c][4]) / (Float.parseFloat(ttest5) +E));
System.out.println("tt3==       "+ttest[c][5]);

//else
  //ttest[c][5]=Float.toString( (Float.parseFloat(test[s4][4])) / Math.abs(sumext - Float.parseFloat(test[s4][4]) ));
//System.out.println("ent1:   "+  (float) 0 / Math.abs(sumext - Float.parseFloat(test[s4][4]) +E));
//System.out.println("fff:   "+ Float.parseFloat(test[s4][4]));
if(q==1){
    ttest[c][6] = Integer.toString(0);
    distance[q][0] = /*test[s1][1]*/ttest5;
    distance[q][1] = ttest[c][4];
              }
else {
    distance[q][0] = /*test[s1][1]*/ttest5;
    distance[q][1] = ttest[c][4];
 float m1 =             (float) Math.pow(( Float.parseFloat(distance[q][0]) - Float.parseFloat(distance[q-1][0]) ) , 2);
 float m2 =             (float) Math.pow(( Float.parseFloat(distance[q][1]) - Float.parseFloat(distance[q-1][1]) ) , 2);
ttest[c][6]=Float.toString( (float) Math.sqrt((m1)+(m2)) );
              }
if(q==1){
    ttest[c][1] = Integer.toString(0);
    distancee[q][0] = Float.toString(testrow);
    distancee[q][1] = ttest[c][5];
    System.out.println(distancee[q][1] + "   jh1 " + q);
              }
else if(q>1){
    distancee[q][0] = Float.toString(testrow);
    distancee[q][1] = ttest[c][5];
 float m3 =             (float) Math.pow(( Float.parseFloat(distancee[q][0]) - Float.parseFloat(distancee[q-1][0]) ) , 2);
 System.out.println(distancee[q-1][1] + "   jh " + q);
 float m4 =             (float) Math.pow(( Float.parseFloat(distancee[q][1]) - Float.parseFloat(distancee[q-1][1]) ) , 2);
ttest[c][1]=Float.toString( (float) Math.sqrt((m3)+(m4)) );

              }
if(ent1> a5){
    a5 = ent1;
}
if(ent1 > 83){
   // ttest1[c][5]=Integer.toString(1);
   /* if(q!=qp && t==0 && ff==0){
    if(q == 412 || q == 413 || q== 414 || q== 415 || q==416 || q==417 || q==419 || q == 423 ){
        tp++;
        t=1;
        qp=q;
    }
    else{
        fn++;
        t=1;
        }
    }*/
}
 else{
 //    ttest1[c][5]=Integer.toString(0);
    /* if(q!=qp && ff==0 && t==0){
     if(q == 412 || q == 413 || q== 414 || q== 415 || q==416 || q==417 || q==419 || q == 423 ){
        fp++;
        ff=1;
        qp=q;
    }
    else{
        tn++;
        ff=1;
         }
    }*/
              }
//Integer.parseInt(test[b1][0])/Integer.parseInt(test[s1][1])
//sum = Integer.parseInt(test[s1][2]);
//sum1 = Integer.parseInt(test[s1][3]);
//sum=Integer.parseInt(test[s1][1])/Integer.parseInt(test[s1][2]);
//ttest[c][1]=Float.toString(s);
//ttest[c][1]=Float.toString(ent);
//ttest[c][2]=Float.toString(ent1);
//ttest[c][2]=/*test[s1][2]*/Float.toString(sum);
//ttest[c][3]=/*test[s1][3]*/Float.toString(sum1);
//ttest[c][4]=/*test[s1][3]*/test[s1][2];
/*if((ttest1[c][1].equals("1") /*&& ttest1[c][2].equals("1")) || ttest1[c][2].equals("1") ||ttest1[c][3].equals("1") ||ttest1[c][4].equals("1") ||ttest1[c][5].equals("1")  ){
    ttest2[cc][0]= Integer.toString(q);
    if(ttest1[c][1].equals("1"))
    ttest2[cc][1]= "1";
    if(ttest1[c][2].equals("1"))
    ttest2[cc][1]= "2";
    if(ttest1[c][3].equals("1"))
    ttest2[cc][1]= "3";
    if(ttest1[c][4].equals("1"))
    ttest2[cc][1]= "4";
    if(ttest1[c][5].equals("1"))
    ttest2[cc][1]= "5";
    cc++;
}*/
if(Float.parseFloat(ttest[c][5]) >100 /*|| Float.parseFloat(ttest[c][4])>=50 || Float.parseFloat(ttest[c][1]) >5 || Float.parseFloat(ttest[c][6])>= 60*/){
    if(Float.parseFloat(ttest[c][5]) > 100)
    ttest1[cc][5] = Integer.toString(q);
   /* if(Float.parseFloat(ttest[c][4])>= 50)
    ttest1[cc][4]=Integer.toString(q);
    if(Float.parseFloat(ttest[c][1]) >5)
    ttest1[cc][1] = Integer.toString(q);
    if(Float.parseFloat(ttest[c][6])>= 60)
    ttest1[cc][6]=Integer.toString(q);*/
    cc++;
}
c++;
//cc++;

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
            }//if
        }
  //      for(int q=STime ; q<=ETime ; q++){
        try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/alll.csv");
                for(int m = 0; m < c; m++)  //write matrix train to csv file
         {

                 wf.append(String.valueOf(ttest[m][0]));
                 wf.append(',');
                 wf.append(String.valueOf(ttest[m][1]));
                 wf.append(',');
                 wf.append(String.valueOf(ttest[m][2]));
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
          try{
               FileWriter wf = new FileWriter("D:/elmi/payan name/dataset/algr.csv");

  for(int m = 0; m < cc; m++)  //write matrix train to csv file
         {
                // wf.append(ttest1[m][1]);
                 //wf.append(',');
                 wf.append(ttest1[m][5]);
                 //wf.append(',');
                 //wf.append(ttest1[m][4]);
                 //wf.append(',');
                 //wf.append(ttest1[m][6]);
               //  wf.append(',');
                 //wf.append(Float.toString(tn));
                // wf.append(',');
                // wf.append(Float.toString(fp));
                 //wf.append(',');
                 //wf.append(Float.toString(a5));






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
     //   }
    }

}
