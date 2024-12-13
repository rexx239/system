//server

import java.net.*; 
public class UDPServer 
{ 
 public static void main(String[] args) 
 { 
  DatagramSocket skt=null; 
  try 
  { 
   skt=new DatagramSocket(6788);  
   byte[] buffer = new byte[1000]; 
   System.out.println("Listening on port 6788"); 
   while(true) 
   { 
    DatagramPacket request = new DatagramPacket(buffer,buffer.length); 
    skt.receive(request); 
    String message = new String(request.getData());  
    System.out.println("server received request "); 
    String toUpper = message.toUpperCase(); 
    byte[] sendMsg= toUpper.getBytes();  
    System.out.println("server sending response "); 
    DatagramPacket reply = new DatagramPacket(sendMsg,sendMsg.length, 
    request.getAddress(),request.getPort()); 
    skt.send(reply); 
   } 
  } 
  catch(Exception ex)
  { 
  } 
 } 
}

//client

import java.net.*; 
import java.util.Scanner; 
public class UDPClient 
{ 
 public static void main(String[] args) 
 { 
  DatagramSocket skt; 
  Scanner scan = new Scanner(System.in); 
  try 
  { 
   System.out.println("Enter Message:"); 
   String msg= scan.next();    
   skt=new DatagramSocket();  
 
   byte[] b = msg.getBytes(); 
   InetAddress host=InetAddress.getByName("127.0.0.1");  
   int serverPort=6788; 
   DatagramPacket request =new DatagramPacket (b,b.length,host,serverPort);  
   skt.send(request); 
   byte[] buffer =new byte[1000]; 
   DatagramPacket reply= new DatagramPacket(buffer,buffer.length);  
   skt.receive(reply);
   String s1 = new String(reply.getData()); 
   System.out.println("Client received: " + s1.trim());  
   skt.close(); 
  } 
  catch(Exception ex) 
  { 
  } 
 } 
}