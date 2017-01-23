package servidorsocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Miguel
 */
public class ServidorSocket extends Thread {

    public static int puerto = 5555;

    @Override
    public void run() {
        try {
            System.out.println("Creando socket servidor");
            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizando el bind");
            InetSocketAddress addr = new InetSocketAddress("localhost", puerto);
            serverSocket.bind(addr);
            puerto++;
            
            System.out.println("Aceptando conexiones");
            Socket newSocket = serverSocket.accept();
            
            if(puerto<=5557){
            Thread cli = new ServidorSocket();
            cli.start();
            }
            
            System.out.println("Conexión recibida");
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            int num1 = is.read();
            int num2 = is.read();
            byte[] operador = new byte[3];
            is.read(operador);
            String operacion = new String(operador);
            System.out.println("primer numero recibido: " + num1);
            System.out.println("segundo numero recibido: " + num2);
            System.out.println("Mensaje recibido: " + new String(operador));
            int resultado = 0;
            if (operacion.equalsIgnoreCase("sum")) {
                resultado = (num1 + num2);
            } else if (operacion.equalsIgnoreCase("res")) {
                resultado = (num1 - num2);
            } else if (operacion.equalsIgnoreCase("div")) {
                resultado = (num1 / num2);
            } else if (operacion.equalsIgnoreCase("mul")) {
                resultado = (num1 * num2);
            } else {
                System.out.println("No ha realizado ninguna operación");
            }
            System.out.println("Resultado: " + resultado);
            os.write(resultado);

            System.out.println("Cerrando el nuevo socket");
            newSocket.close();
            System.out.println("Cerrando el socket servidor");
            serverSocket.close();
            System.out.println("Terminado");
        } catch (IOException e) {
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ServidorSocket().start();
    }
}
