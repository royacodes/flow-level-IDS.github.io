/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package processdata;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Draw extends JFrame {
    public int k ,b1 , b2  , b3 , b4;
    public int t[] ;//temp
    public int B[][];// m
    public int U[][]; // L
    int N1[] , N2[]; // mokhtasate gereha

    public Draw(int n/*tedad gereh*/ , int[] temp /*tu har khushe chanta hast*/ , int L[][]/*tu kodum khushe kodum gereh hast*/,int m[][]/*matrise mojaverat*/){
         super("OPDX");
        setSize(1000,600);
        setVisible(true);
        t= new int[k];
        U = new int[k][k];
        B = new int [k][k];
        k=n;
        B=m;
        U=L;
        t=temp;
    }//constructor

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif" , Font.BOLD , 12));
        b1=20;
        b2=40;
        b3=b1+20;
        b4=b2+20;
        N1 = new int [k];
        N2 = new int[k];
        N1[0]=b3;
        N2[0]=b4;
        for(int i = 0 ; i<k ; i++){
            if(t[i]!=0){
                g.drawRect(b1, b2 , 50*t[i], 50*t[i]);
                for(int j=0 ; j<k ; j++){
                    if(U[i][j]==1 ){


                        N1[j]=b3;
                        N2[j]=b4;

                        if(t[i]>1){
                          b3 =b3+20;
                        if(b3 >= b1+(50*t[i])){
                            b3=b1+20;
                            b4=b4+20;

                            }
                       }

                    }

                }
                //N1[h]=N;
                 b1=b1+(70*t[i])+30;
                 b2=b2;
                 if(b1 >=900 ){
                 b1=20;
                 b1= b1+(70*t[i])+30;
                 b2= b2+(70*t[i])+30;
                 if(b2 >= 500) System.out.print("error");
                }
                   b3 =b1+20;
                        if(b3 >= b1+(50*t[i])){
                            b3=b1+20;
                            b4=b2+20;
                        }
            }
        }
        for(int j=0 ; j<k ; j++){
         for(int a=0 ; a<k ; a++){
                            if(B[j][a]==1 && j!=a){
                                if(N1[j]==N1[a])
                                    N1[a]=N1[a]+20;

                                if(N2[j]==N2[a])
                                    N2[a]=N2[a]+20;

                            }
                        }
        }
        g.setColor(Color.red);
        for(int i =0; i<k ; i++)
         g.fillOval(N1[i],N2[i], 10, 10);
        g.setColor(Color.blue);
    for(int i=0 ; i<k ; i++){
        for(int j=0 ; j<k ; j++){
            if(B[i][j]==1 && i!=j){
                g.setColor(Color.blue);
                g.drawLine(N1[i], N2[i], N1[j], N2[j]);
                    g.drawString(">", N1[j]-2, N2[j]+4);
            }
            if(B[i][j]==1 && i==j){
                g.setColor(Color.GREEN);
                g.drawString("**", N1[i], N2[i]);
            }
        }
    }
        }
    }





