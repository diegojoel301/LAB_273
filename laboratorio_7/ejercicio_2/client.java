import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Main
{
   public static void main(String[] args) throws IOException
   {
      Scanner scan = new Scanner(System.in);

      String host = "localhost";
      int puerto = 9000;
      boolean flag = true;
      DatagramSocket miSocket = new DatagramSocket();
      try
      {
         while(flag)
         {
            InetAddress dst = InetAddress.getByName(host);
            
            System.out.print("[+] Introduce Mensaje: ");
            String entrada = scan.nextLine();

            byte[ ] buffer = entrada.getBytes( );
            DatagramPacket datagrama = new DatagramPacket(buffer, entrada.length(), dst, puerto);
            miSocket.send(datagrama);

            if(entrada.isEmpty())
            {
               System.out.println("[!] Terminando....");
               flag = false;
               continue;
            }

            byte[] buffer_server = new byte[100];

            DatagramPacket datagrama_server = new DatagramPacket(buffer_server, buffer_server.length);

            miSocket.receive(datagrama_server); // Esperamos a la respuesta del servidor

            String cantidad = new String(datagrama_server.getData(), 0, datagrama_server.getLength()); // Casteamos de bytes a string de los datos que nos envio el servidor

            System.out.println(cantidad);
         }
         miSocket.close();
         System.exit(0);
      }
      catch (Exception ex) { ex.printStackTrace( ); }

   }
}
