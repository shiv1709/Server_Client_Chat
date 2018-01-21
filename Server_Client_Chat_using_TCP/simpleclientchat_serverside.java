import java.net.*;
import java.io.*;

class simpleclientchat_serverside
{
	public static void main(String args[]) throws Exception
	{
		try
		{
			ServerSocket srvsoc=new ServerSocket(6564);
			Socket soc=srvsoc.accept();
			DataInputStream din= new DataInputStream(soc.getInputStream());
			DataOutputStream dout= new DataOutputStream(soc.getOutputStream());
			BufferedReader bfr= new BufferedReader(new InputStreamReader(System.in));
			String str=" ",str2=" ";
			while(!str.equals("stop"))
			{
				str=din.readUTF();
				System.out.println("Client says : "+str);
				str2=bfr.readLine();
				dout.writeUTF(str2);
				dout.flush();
			}
			din.close();
			soc.close();
			srvsoc.close();
			}
		catch(Exception ex)
		{
			System.out.println("Exception occurs");
			ex.printStackTrace();
		}
	}
}
