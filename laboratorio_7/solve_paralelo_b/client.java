import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Main
{
   public static void main(String[] args) throws IOException
   {
      Scanner scan = new Scanner(System.in);
      String server = "localhost";
      int port = 9000;

      boolean flag = true;

      try
      {
         Socket client_socket = new Socket(server, port);
         while(flag)
         {
            

            // Flujo de salida
            System.out.print("[+] Ingresa Fecha(ej. 2023-04-20): ");
            String entrada = scan.nextLine();

            PrintWriter out = new PrintWriter(client_socket.getOutputStream(), true);

            out.println(entrada);

            if(entrada.equals("9999"))
            {
               flag = false;
               continue;
            }

            BufferedReader in = new BufferedReader(
                new InputStreamReader(client_socket.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) // Esperando a que el cliente nos envie datos mediante el flujo entrante
            { 
               if(inputLine.equals("FIN"))
                  break;
               System.out.println(inputLine);
            }
            
   
         }
         client_socket.close();
         
      }
      catch (IOException e)
      {
            System.out.println(e.getMessage());
      }
   }
}
