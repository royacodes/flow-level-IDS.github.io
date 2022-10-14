

package processdata;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnetpcap.Pcap;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;

import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;


import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

import java.sql.*;
import java.text.DateFormat;
import java.util.LinkedList;

public class ExtractH {

    public void Extract(String filename) {
        	Date dNow = new Date( );
        final SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS");


        System.out.println("Starting Time Date: " + ft.format(dNow));
    //	String username = "root"; // mySQL username
    //	String password = "123456"; // mySQL password
    //	String url = "jdbc:mysql://localhost/IDS_D1"; // Connect to database IDS
    //	Class.forName("com.mysql.jdbc.Driver");

    //	final Connection con = DriverManager.getConnection(url, username, password); // Connect
    	final Statement stmt;
	//	stmt = con.createStatement(); // for Query
		//final String tbl="dataset"; // Table name
		final String FILENAME = filename; // path of pcap file

        final StringBuilder errbuf = new StringBuilder();


        final Pcap pcap = Pcap.openOffline(FILENAME, errbuf); // read pcap file

        if (pcap == null) {
            System.err.println(errbuf); // Error is stored in errbuf
            return;
        }
        final LinkedList<String[]> rows = new LinkedList<String[]>();
       // final  int co=0;
        final FinalCounter test = new FinalCounter(0);
        String file2 = "C://Users//public//out.txt";
             try{
            final  FileWriter wf = new FileWriter(file2);

        pcap.loop(Pcap.LOOP_INFINITE, new JPacketHandler<StringBuilder>() {


            final Tcp tcp = new Tcp();
            final Udp udp = new Udp();
            //final IPV4 ipv4 = new IPV4();
            final Ip4 ip = new Ip4();
            final Icmp icmp= new Icmp();
            final Ethernet ethernet=new Ethernet();
            final Arp arp= new Arp();
            int i=0;
int c=0;

            public void nextPacket(JPacket packet, StringBuilder errbuf) {



               int j=(int) packet.getFrameNumber();

               i=0;

             Date packetTimeStamp = new Date(packet.getCaptureHeader().timestampInMillis());

            	byte[] dIP;
				byte[] sIP;
				String[] packetInfo= new String[7];

				if (packet.hasHeader(Tcp.ID)) {

                        i = 1;
                        packet.getHeader(tcp);
                        dIP = packet.getHeader(ip).destination();
                        sIP = packet.getHeader(ip).source();
                        String protocol = ip.typeEnum().name();
                        String sourceIP = org.jnetpcap.packet.format.FormatUtils.ip(sIP);
                        String destinationIP = org.jnetpcap.packet.format.FormatUtils.ip(dIP);
                        packetInfo[0] = Integer.toString(j);

                        packetInfo[1] = sourceIP;
                        packetInfo[2] = destinationIP;
                        packetInfo[3] = protocol;
                        packetInfo[4] = ft.format(packetTimeStamp).toString();
                         packetInfo[5]=Integer.toString(tcp.source());
                        packetInfo[6]=Integer.toString(tcp.destination());

                         String val=packetInfo[0]+","+packetInfo[1]+","+packetInfo[2]+","+packetInfo[3]+","+packetInfo[4]+","+ packetInfo[5]+","+packetInfo[6];
                             //  String query = "INSERT INTO "+tbl+" VALUES ("+val+")";
//rows.addLast(val.split(","));
test.increment();
                        try {
                            wf.append(val);
            wf.append('\r');
               wf.append('\n');
               wf.flush();
                 } catch (IOException ex) {
                            Logger.getLogger(ExtractH.class.getName()).log(Level.SEVERE, null, ex);
                        }
					/*	try {

							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}*/
                        c++;
                        System.out.println(val +"   ,"+ c);

                    }
				else if (packet.hasHeader(Udp.ID)) {
					i=1;
					packet.getHeader(udp);
            		dIP = packet.getHeader(ip).destination();
            		sIP = packet.getHeader(ip).source();
            		String protocol =  ip.typeEnum().name();
            		String sourceIP = org.jnetpcap.packet.format.FormatUtils.ip(sIP);
            		String destinationIP = org.jnetpcap.packet.format.FormatUtils.ip(dIP);
            		packetInfo[0]=Integer.toString(j);
            		packetInfo[1]=sourceIP;
            		packetInfo[2]=destinationIP;
            		packetInfo[3]=protocol;
                        packetInfo[4]=ft.format(packetTimeStamp).toString();
                         packetInfo[5]=Integer.toString(udp.source());
                    packetInfo[6]=Integer.toString(udp.destination());
                         String val=packetInfo[0]+","+packetInfo[1]+","+packetInfo[2]+","+packetInfo[3]+","+packetInfo[4]+","+ packetInfo[5]+","+packetInfo[6];
                              //  String query = "INSERT INTO "+tbl+" VALUES ("+val+")";
//rows.addLast(val.split(","));
test.increment();
          try {
                            wf.append(val);
            wf.append('\r');
               wf.append('\n');
               wf.flush();
                 } catch (IOException ex) {
                            Logger.getLogger(ExtractH.class.getName()).log(Level.SEVERE, null, ex);
                        }
					/*	try {

							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}*/
                          c++;
                        System.out.println(val +"   ,"+ c);



                                }
				else if (packet.hasHeader(Icmp.ID)) {
					i=1;
					packet.getHeader(icmp);

            		dIP = packet.getHeader(ip).destination();
            		sIP = packet.getHeader(ip).source();
            		String protocol =  ip.typeEnum().name();
            		String sourceIP = org.jnetpcap.packet.format.FormatUtils.ip(sIP);
            		String destinationIP = org.jnetpcap.packet.format.FormatUtils.ip(dIP);
            		packetInfo[0]=Integer.toString(j);
            		packetInfo[1]=sourceIP;
            		packetInfo[2]=destinationIP;
            		packetInfo[3]=protocol;
                        packetInfo[4]=ft.format(packetTimeStamp).toString();
                        packetInfo[5]=Integer.toString(ip.sourceToInt());
                        packetInfo[6]=Integer.toString(ip.destinationToInt());
                         String val=packetInfo[0]+","+packetInfo[1]+","+packetInfo[2]+","+packetInfo[3]+","+packetInfo[4]+","+ packetInfo[5]+","+packetInfo[6];
                             //   String query = "INSERT INTO "+tbl+" VALUES ("+val+")";
//rows.addLast(val.split(","));
test.increment();
          try {
                            wf.append(val);
            wf.append('\r');
               wf.append('\n');
               wf.flush();
                 } catch (IOException ex) {
                            Logger.getLogger(ExtractH.class.getName()).log(Level.SEVERE, null, ex);
                        }
					/*	try {

							stmt.executeUpdate(query);

						} catch (SQLException e) {
							e.printStackTrace();
					}*/
                          c++;
                        System.out.println(val +"   ,"+ c);

                }

            } // end nextPacket
        }, errbuf); //end loop
int ro = test.getVal();
String [][] testpcp = new String[ro][1];
 //       testpcp = rows.toArray(new String[ro][1]);




         wf.close();
      }
    catch(Exception e)
    {
      e.printStackTrace();
    }
        pcap.close();
      //  stmt.close();
		//con.close();
		Date dNow1 = new Date( );

		System.out.println("Ending Time Date: " + ft.format(dNow1));
    }

}
