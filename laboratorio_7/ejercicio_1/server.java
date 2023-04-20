import java.net.*;
import java.io.*;

public class Main
{
   public static void main(String[] args) throws IOException
   {
      int port = 9000; // Puerto del servidor en escucha
      ServerSocket serverSocket = new ServerSocket(port); // Creacion del server socket
      while(true)
      {
         Socket clientSocket = serverSocket.accept(); // Espera a que un cliente se conecte (Socket de Bienvenida)

         // Gracias a la clase PrintWriter podemos escribir datos formateados en el flujo de salida del socket de cliente
         // Mediante esta clase podemos enviar datos a travez de este flujo de salida el cual sera la salida
         // correspondiente a este ejercicio que es en si enviar informacion del socket cliente y el mensaje que el
         // cliente nos envie

         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

         // BufferedReader nos ayudara a leer los datos de un flujo en este caso del flujo del socket cliente
         // Mediante la variable in obtendremos todo el flujo de bits que nos envie el cliente

         BufferedReader in = new BufferedReader(
             new InputStreamReader(clientSocket.getInputStream()));
         try
         {
            String mensaje_a_cliente;

            mensaje_a_cliente = "IP Cliente: "+ clientSocket.getInetAddress().getHostAddress() + "\nPuerto: " + Integer.toString(clientSocket.getPort());

            String inputLine;
            while ((inputLine = in.readLine()) != null) // Esperando a que el cliente nos envie datos mediante el flujo entrante
            { 
               mensaje_a_cliente += "\nMensaje del cliente: " + inputLine;
               out.println(mensaje_a_cliente); // Mediante el flujo de salida enviamos el mensaje al cliente
            }
         }
         catch (IOException e)
         {
            System.out.println(e.getMessage());
         }
      }
   }
}
