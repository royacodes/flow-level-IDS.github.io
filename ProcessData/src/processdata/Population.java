
package processdata;

import java.util.*;


public class Population {
private  int classnumber,x[],Ni ;
private float U;
public  List <Integer> mylist;


public  Population(){
       mylist =new ArrayList<Integer>();
}

public void AddNode(int n){
    mylist.add((n));
}

   public  ArrayList<Integer>getNode(){
       
       return   (ArrayList) mylist;
   }

   public void SetUi(float u,int ni){
        U=u;
        Ni=ni;
   }
   public float GetUi(){
       return U;
   }
   public int GetNi(){
       return Ni;
   }
   
 

}


