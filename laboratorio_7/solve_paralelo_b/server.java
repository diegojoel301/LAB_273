import java.net.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Duration;

public class Main
{
   public static void main(String[] args) throws IOException
   {
      
      int port = 9000; // Puerto del servidor en escucha
      ServerSocket serverSocket = new ServerSocket(port); // Creacion del server socket
      boolean flag = true;
      while(flag)
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
            String mensaje_a_cliente = "", inputLine;

            while ((inputLine = in.readLine()) != null) // Esperando a que el cliente nos envie datos mediante el flujo entrante
            { 
               if(inputLine.equals("9999")) //activa la bandera
               {
                  flag = false;
                  break;
               }
               mensaje_a_cliente = "\nCantidad de dias para concluir el anio: " + f(inputLine) + "\nFIN";
               out.println(mensaje_a_cliente); // Mediante el flujo de salida enviamos el mensaje al cliente
            }
         }
         catch (IOException e)
         {
            System.out.println(e.getMessage());
         }
      }
      
      
   }

   public static long f(String date)
   {
      LocalDate date2 = LocalDate.parse(date);
      LocalDate date1 = LocalDate.of(2024, 01, 01);
      Duration difference = Duration.between(date2.atStartOfDay(), date1.atStartOfDay());

      return difference.toDays();
   }
}
