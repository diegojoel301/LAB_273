import java.net.*;
import java.io.*;

public class Main
{
   public static void main(String[] args) throws IOException
   {
      try {
         DatagramSocket miSocket = new DatagramSocket(9000); // Creacion de Socket servidor

         boolean flag = true;

         while(flag)
         {
            byte[ ] buffer = new byte[100]; // el buffer de bytes sera de 100 bytes = 800 bits
            // Creamos un datagrama vacio con el buffer ya creado y con la respectiva longitud de este buffer
            DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length); 
            miSocket.receive(datagrama); // Esperamos a un cliente

            String mensaje = new String(datagrama.getData(), 0, datagrama.getLength()); // Casteamos de bytes a string de los datos que nos envio el cliente
            //System.out.println(mensaje.length());
            if(mensaje.length() != 0)
            {
               int port_client =  datagrama.getPort(); // puerto por el cual el cliente envia el datagrama

               InetAddress dst = datagrama.getAddress(); // Direccion IP del cliente 

               String salida = "Cantidad de vocales: " + Integer.toString(f(mensaje)) + "\n";

               byte[] n_buffer = salida.getBytes(); // aplicamos la funcion para conteo de vocales y lo convertimos a bytes

               DatagramPacket nuevo_datagrama = new DatagramPacket(n_buffer, n_buffer.length, dst, port_client);

               miSocket.send(nuevo_datagrama);
            }
            else
            {
               flag = false;
            }
         }
         miSocket.close( );
      }
      catch (Exception ex) { ex.printStackTrace( );}

   }

   public static int f(String str)
   {
      int c = 0;
      for(int i = 0; i < str.length(); i++)
      {
         if(str.charAt(i) == 'a' || str.charAt(i) == 'A' ||
            str.charAt(i) == 'e' || str.charAt(i) == 'E' ||
            str.charAt(i) == 'i' || str.charAt(i) == 'I' ||
            str.charAt(i) == 'o' || str.charAt(i) == 'O' ||
            str.charAt(i) == 'u' || str.charAt(i) == 'U')
            c++;
      }
      return c;
   }
}
