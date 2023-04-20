import java.io.*;
import java.net.*;
import java.util.Date;

public class Main
{
	public static void main(String args[])
	{

		ServerSocket servidor = null;
		Socket sc = null;

		DataInputStream in;
		DataOutputStream out;

		final int port = 9876;

		try
		{
			servidor = new ServerSocket(port);

			System.out.println("Servidor iniciado");

			while(true)
			{
				sc = servidor.accept();

				System.out.println("Cliente conectado");

				in = new DataInputStream(sc.getInputStream());
				out = new DataOutputStream(sc.getOutputStream());

				String mensaje = in.readUTF();

				System.out.println(mensaje);

				out.writeUTF("Hola como estas xD?");

				sc.close();

			}

		}
		catch(IOException ex)
		{

		}
		
	}
}
