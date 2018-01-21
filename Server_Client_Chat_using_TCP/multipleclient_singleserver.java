/*

***********************

Author@Shiv Pratap Singh
Date-21 Jan 2018
***********************

*/

import java.net.*;
import java.io.*;

public class multipleclient_singleserver
{
	static final int port_no=6564;
	public static void main(String args[])
	{
		Socket soc=null;
		ServerSocket srvsoc=null;
		System.out.println("Server starts listening");
		try
		{
			srvsoc=new ServerSocket(port_no);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Server start Error occurs");
		}

		while(true)
		{
			try
			{
				soc=srvsoc.accept();
				System.out.println("Connection Established ");
				ServerThread srvthread=new ServerThread(soc);
				srvthread.run();
			}
			catch(Exception  ex)
			{
				ex.printStackTrace();
				System.out.println("Connection Error occurs");
			}
		}
	} 
}
class ServerThread extends Thread
{
	String str=" ",str2=" ";
	BufferedReader bfr=null;
	DataInputStream din=null;
	DataOutputStream dout=null;
	Socket soc=null;
	public ServerThread(Socket soc)
	{
		this.soc=soc;
	}
	public void run()
	{
		try
		{
			din=new DataInputStream(soc.getInputStream());
			dout= new DataOutputStream(soc.getOutputStream());
			bfr= new BufferedReader(new InputStreamReader(System.in));
		}
		catch(IOException ex)
		{	
			System.out.println("IO error occurs in server thread");
			ex.printStackTrace();
		}
		try
		{
			while(!str.equals("stop"))
			{
				str=din.readUTF();
				System.out.println("Client with ip adress"+soc.getRemoteSocketAddress().toString()+"says : "+str);
				str2=bfr.readLine();
				dout.writeUTF(str2);
				dout.flush();
			}
		}
		catch(IOException ex)
		{
			str=this.getName();
			System.out.println("IO Error/Client "+str+"terminated abruptly");
		}
		catch(NullPointerException ex)
		{
			str=this.getName();
			System.out.println("Client "+str+ " closed");
		}
		finally
		{	//using try catch in finally to avoid exceptions in connection closing
			try
			{
				System.out.println("Connection closing");
				if(din!=null)
				{
					din.close();
				}
				if(dout!=null)
				{
					dout.close();
				}
				if(soc!=null)
				{
					soc.close();
				}
			}
			catch(IOException ex)
			{
				System.out.println("Socket close error");
			}
		}
	}
}