
package processdata;

import java.util.*;
import java.util.Random;

public class Individual {

public static int[][] Graph;
public int population[][];
public  int Fittness[];
public float MQ[],best;
private  int N,K=5;
private  int R=10;
private Random random=new Random();
public static float Pc=(float) 0.8;
public  static int index,elit[] ;
Graphic gr = new Graphic();
public int nu=0;
public int grt;


public void Fitness(int graph[][],int n,int pop[][] , int greater , int tag[] , float tempMQ[] ){
    List <Integer> mylist =new ArrayList<Integer>();
    N=n;
    grt=greater;
    elit=new int[N];
    Graph=new int[N][N];
    int passcrom []=new int[N];
    population=new int[R*N][N];
    Fittness=new int[R*N];
    population=pop;
    Graph=graph;
    MQ=new  float[R*N];
boolean stop;
int sm=0;
int numclass;
 for(int i=0;i<R*N;i++){
     if(tag[i]==1){
         MQ[i]=tempMQ[i];
         sm=sm+1;
     }
     else if(tag[i] == 0) {
      numclass=0;

     for(int j=0;j<N;j++){
         passcrom[j]=population[i][j];
         stop=false;
           for (Integer x : mylist){
                  if((x==population[i][j])){
                     stop=true;
                  }
              }
              mylist.add(population[i][j]);

           if(!stop)numclass+=1;

  }
    MQ[i]=CalInnerEdge(passcrom ,numclass);
  //  System.out.println("MQ  "+MQ[i]+", "+i);
    Elitism(i);
    //System.out.println("elit  "+elit+", "+index);
      mylist.clear();
     // System.out.println("fitness" + i);
     }
    }
System.out.println(sm);
}

private float  CalInnerEdge(int [] X , int claster){
int y=claster;
int x []=new int [N];
int great=0;
x=X;
    Population[] mypop=new Population[y];
     List <Integer> list=new ArrayList<Integer>();

    int k=0;
    for(int i=0;i<x.length&&k<y;i++){

       if(x[i]!=0){
        mypop[k]=new Population();
         mypop[k].AddNode(i);
        for(int j=i+1;j<x.length;j++){
            if(x[i]==x[j]){
                mypop[k].AddNode(j); x[j]=0;}
        }

         k++;

    }
      // System.out.println("calinneredge"+i);
    }
     for (int i=0;i<mypop.length;i++){
         int Node;
         float u=0;
         list=mypop[i].getNode();
         great=0;
       /*  for(int j=0 ;j<list.size();j++){
            Node=list.get(j);
             for(int p=0;p<list.size();p++){
                 int node=list.get(p);
                 if(Graph[Node][node]>great)great=Graph[Node][node];
             }
          //  System.out.println("calinneredge"+i);
         }*/
         for(int j=0 ;j<list.size();j++){
            Node=list.get(j);
             for(int p=0;p<list.size();p++){
                 int node=list.get(p);
                 if(Graph[Node][node]!=0){u=(((float)(Graph[Node][node])/((float)grt)))+u;}//System.out.println("uin:  "+u+", "+Node+" ,"+node+" gr, "+great+" ,"+Graph[Node][node]);}
             }
          //  System.out.println("calinneredge"+i);
         }
      //System.out.println("u:  "+u+", "+i);
        mypop[i].SetUi(u,(list.size())*(list.size()));

    }
    List <Integer> list1=new ArrayList<Integer>();
   float Eij=0,Ai=0;
   great=0;

    for(int i=0 ;i<mypop.length;i++){

      list=mypop[i].getNode();

       for(int p=i+1;p<mypop.length;p++)  {
              float num=0;float eij=0;
              great=0;
              list1=mypop[p].getNode();

           //   for(int j=0;j<list.size();j++){
             //        for(int q=0;q<list1.size();q++){
                //         if(Graph[list.get(j)][list1.get(q)]>great /*&& great!=0*/)great=(Graph[list.get(j)][list1.get(q)]);
                  //       if(Graph[list1.get(q)][list.get(j)]>great /*&& great!=0*/)great=(Graph[list1.get(q)][list.get(j)]);
                    // }
             //}
//System.out.println("great:   "+great+", "+p);
             for(int j=0;j<list.size();j++){
                     for(int q=0;q<list1.size();q++){
                         if(Graph[list.get(j)][list1.get(q)]!=0 && great!=0)num=(((float)(Graph[list.get(j)][list1.get(q)])/((float)grt)))+num;
                         if(Graph[list1.get(q)][list.get(j)]!=0 && great!=0)num=(((float)Graph[list1.get(q)][list.get(j)])/((float)grt))+num;
                     }
             }
  //            System.out.println("num:  "+num+",  "+p);
              eij=(float)num/(2*list.size()*list1.size());
    //          System.out.println("eij:  "+eij+",  "+p);
                Eij=(float)Eij+eij;

         }

Ai+=(float)((float)mypop[i].GetUi()/(float)mypop[i].GetNi());
//System.out.println("calinneredge"+i);
    }

   if(claster>1)
   return (Ai/y)-(Eij/((y*(y-1))/2));
 // return (Eij/((y*(y-1))/2))-(Ai/y);
   else {
      return Ai;

    }
       // return Eij;
}

public int [] Tournment(){
   float bestmq; int Cromosom[]=new int[N];
     int b=random.nextInt(R*N);
     int Final=b;
     bestmq=MQ[b];
    for(int i=0;i<K-1;i++){
     b=random.nextInt(R*N);
     if(MQ[b]>bestmq) Final=b;
  //   System.out.println("tornoment"+i);
    }
     for(int j=0;j<N;j++){
       Cromosom[j] = population[Final][j];
      //System.out.println("tornoment"+j);
     }
     return Cromosom;
}

public int[][] Crossover(int [][]newpopulation){
    int[][]pop=new int[R*N][N];
    int[] cromosome1 =new int[N];
     int []cromosome2=new int[N];
    population=newpopulation;
      int temp;
        for(int i=0; i<R*N;i+=2){
            int k=i+1;
            for(int j=0;j<N;j++){

                cromosome1[j]=population[i][j];
                cromosome2[j]=population[k][j];
            }
                float cr=random.nextFloat();
    if(cr<Pc){
    int r1=random.nextInt(N),r2;
   r2 = r1+ (int)(Math.random() * ((N - r1)));
   if(r1==r2) {
      if(r2>0&&r2<N-1||r2==0)r2+=1;
       else if(r2 == N - 1)
       r2 -= 1;
   }
          for (int p =0; p<N ; p++)
        {
                if ((p <=r2) & (p >= r1) || (p==r2) || (p==r1))
                {
                    temp = cromosome1[p];
                    cromosome1[p] = cromosome2[p];
                   cromosome2[p] = temp;
                }}

         float Pm=(float) (Math.log(N)/Math.log(2));

       float m= random.nextFloat();
       int s1=random.nextInt(N);
       int s2=random.nextInt(N);
       if(m<0.004*Pm){

            temp = cromosome1[s1];
            cromosome1[s1] = cromosome1[s2];
            cromosome1[s2] = temp;
    }
        if(m<0.004*Pm){

            temp = cromosome2[s1];
            cromosome2[s1] = cromosome2[s2];
            cromosome2[s2] = temp;
    }
                    }
                 for(int l=0;l<N;l++){
       pop[i][l] =cromosome1[l];
       pop[i+1][l]=cromosome2[l];
            }

    }

     return pop;
    }
public void  Elitism(int in){
float Max=MQ[0];
index=0;
elit=population[index];
for(int i=1;i<=in;i++){
    if(MQ[i]>Max){ Max=MQ[i];index=i;elit=population[index];}
}
}
public float p(){
    return MQ[index];
}
public int []o(){
    return elit;
}
public float [] getMQ(){
    return MQ;
}

}
