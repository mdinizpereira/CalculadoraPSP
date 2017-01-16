package clientesocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author Miguel
 */
public class ClienteSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try{
			System.out.println("Creando socket cliente");
			Socket cliente=new Socket();
			System.out.println("Estableciendo la conexión");
			
			InetSocketAddress addr=new InetSocketAddress("localhost",5555);
			cliente.connect(addr);

			InputStream is = cliente.getInputStream();
			OutputStream os= cliente.getOutputStream();

			System.out.println("Enviando mensaje");

			int num1 = 2;
                        int num2 = 4;
                        String operador = "suma";
			os.write(num1);
                        os.write(num2);
                        os.write(operador.getBytes());
                        
			System.out.println("Mensaje enviado");

			System.out.println("Cerrando el socket cliente");

			cliente.close();

			System.out.println("Terminado");

			}catch (IOException e) {
				e.printStackTrace();
			}
    }
    
}
