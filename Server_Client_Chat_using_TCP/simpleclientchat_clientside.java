import java.net.*;
import java.io.*;

class simpleclientchat_clientside
{
	public static void main(String args[])
	{	
		try
		{
			Socket soc= new Socket("localhost",6564);//One can select any port number between 1024 and 65535
			DataInputStream din= new DataInputStream(soc.getInputStream());
			DataOutputStream dout= new DataOutputStream(soc.getOutputStream());
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			String str=" ",str2=" ";
			while(!str.equals("stop"))
			{	
				str=br.readLine();
				dout.writeUTF(str);
				dout.flush();
				str2=din.readUTF();
				System.out.println("Server says : "+str2);
				
			}
			din.close();
			soc.close();
			dout.close();
		}
		catch(Exception ex)
		{
			System.out.println("Exception occurs");
			ex.printStackTrace();
		}
	}
}