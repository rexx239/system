 
import java.util.Scanner; 
public class LeakyBucket 
{ 
 public static void main(String args[]) 
 { 
  int n, outgoing, store, bucketSize; 
  int incoming[];  
  Scanner scan = new Scanner(System.in); 
  System.out.println("Enter number of inputs"); 
  n = scan.nextInt(); 
  incoming = new int[n]; 
  for(int i = 0 ;i< n ; i++) 
  { 
   System.out.println("Enter incoming packet size "+(i+1)); 
   incoming[i] = scan.nextInt();  
  }  
  System.out.println("Enter bucket size"); 
  bucketSize = scan.nextInt(); 
  System.out.println("Enter outgoing rate"); 
  outgoing = scan.nextInt(); 
  store = 0; 
  int i = 0; 
  System.out.println("Packet Recieved | Packet Dropped | Packet Sent | Packet Left"); 
 
  do 
  {
    int pktReceived = 0, pktSent = 0,pktDrop = 0; 
   if(i < n) 
   { 
    pktReceived = incoming[i]; 
 
    if(pktReceived  <= (bucketSize - store)) 
    { 
     store += pktReceived ; 
    } 
    else 
    { 
     
     pktDrop = pktReceived -(bucketSize - store); 
     store = bucketSize; 
    } 
   } 
   if(store > outgoing)  
   { 
    store -= outgoing; 
    pktSent = outgoing; 
   } 
   else 
   { 
    pktSent = store; 
    store = 0; 
   } 
   System.out.println(pktReceived +"\t\t"+pktDrop+"\t\t"+pktSent+"\t\t"+store); 
   try
   { 
    Thread.sleep(2000); 
   } 
   catch(Exception e) 
   { 
   } 
   i++; 
    
  }while(store != 0 || i < n);  
 
 }
}