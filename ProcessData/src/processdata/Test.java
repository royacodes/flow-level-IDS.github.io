

package processdata;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class Test {


    
    public static void main(String[] args) throws ClassNotFoundException,
    SQLException  {

        String file = "D://elmi//payan name//dataset//out.txt";
        DiscIP dp = new DiscIP();
      //  dp.DiscIP();
        String filee = "D:/elmi/payan name/dataset/dataIP.txt";

        MapData mp = new MapData();
      //  mp.Map(file, filee);
        mp.convert(5);

    }

}
