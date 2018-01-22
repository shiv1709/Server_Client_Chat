/*

***********************

Author@Shiv Pratap Singh
Date-22 Jan 2018
***********************

*/

import java.io.*;
import java.net.*;
 public class UDPClient{
 	public static void main (String args[]){
 	try{

 		DatagramSocket clientSocket=new DatagramSocket();
 		BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in));
 		InetAddress host =InetAddress.getByName("localhost");
 		String serversentence,clientsentence=" ";
		while(!clientsentence.equals("stop"))
 		{
	 		byte[] sendData=new byte[1024];
	 		byte[] receiveData=new byte[1024];
	 		clientsentence=inFromUser.readLine();
	 		sendData=clientsentence.getBytes();
	 		DatagramPacket sendPacket =new DatagramPacket(sendData,sendData.length,host,6564);
	 		clientSocket.send(sendPacket);
	 			DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);
	 		clientSocket.receive(receivePacket);
	 		serversentence=new String(receivePacket.getData());
	 		System.out.println("SERVER says :"+serversentence);
 		}
 		if(clientSocket!=null)
 		clientSocket.close();
 	}
 	catch(Exception ex)
 	{
 	System.out.println(ex);
 	ex.printStackTrace();
 	}
 }
}