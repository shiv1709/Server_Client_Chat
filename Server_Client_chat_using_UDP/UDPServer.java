import java.net.*;
import java.io.*;

public class UDPServer{
	
	public static void main(String args[])
	{
		try
		{
			DatagramSocket serverSocket=new DatagramSocket(6564);
			BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in));
			System.out.println("The server is ready ...");
			String serversentence=" ",clientsentence;
			while(!serversentence.equals("stop"))
			{
				byte[] receiveData=new byte[1024];
 				byte[] sendData=new  byte[1024];
				DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);
				serverSocket.receive(receivePacket);
				clientsentence=new String(receivePacket.getData());
 				System.out.println("CLIENT says : "+clientsentence);
 				InetAddress IPAddress=receivePacket.getAddress();
 				int port=receivePacket.getPort();
 				serversentence=inFromUser.readLine();
 				sendData=serversentence.getBytes();
 				DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,IPAddress,port);
 				serverSocket.send(sendPacket);
			}
			if(serverSocket!=null)
			serverSocket.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			ex.printStackTrace();
		}
	} 
}