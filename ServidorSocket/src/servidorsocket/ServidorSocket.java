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
    //variable puerto que aumenta con cada cliente nuevo que se conecta
    public static int puerto = 5555;

    @Override
    public void run() {
        try {
            System.out.println("Creando socket servidor");
            //crea un socket servidor
            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizando el bind");
            /**
             * se crea un direccion IP y se le asigna al servidor, una vez asignada
             * la variable puerto aumenta 1 unidad.
             */
            InetSocketAddress addr = new InetSocketAddress("localhost", puerto);
            serverSocket.bind(addr);
            puerto++;
            
            System.out.println("Aceptando conexiones");
            /**
             * el socket servidor crea un nuevo socket que esta a espera de
             * recibir una petición de conexion de un cliente
             */
            Socket newSocket = serverSocket.accept();
            
            /**
             * si el puerto es menor o igual que 5557 creará un nuevo hilo y lo lanzará
             * esperando por la siguiente conexion del proximo cliente.
             * como la variable puerto se inicializa en 5555 solo podrá albergar 3 clientes
             */
            if(puerto<=5557){
            Thread cli = new ServidorSocket();
            cli.start();
            }
            /**
             * se crean los flujos para el nuevo socket
             */
            System.out.println("Conexión recibida");
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();
            
            /**
             * por el flujo de entrada se reciben dos numeros (2 bytes) y
             * un array de 3 bytes como el operador
             * se muestra por pantalla lo recibido del cliente
             * según lo recibido como operador (una cadena de texto de 3 letras)
             * @resultado será la devolución de la operación de los 2 numeros
             * recibidos en funcion del operador
             */
            int num1 = is.read();
            int num2 = is.read();
            byte[] operador = new byte[3];
            is.read(operador);
            String operacion = new String(operador);
            System.out.println("primer numero recibido: " + num1);
            System.out.println("segundo numero recibido: " + num2);
            System.out.println("Mensaje recibido: " + operacion);
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
            /**
             * tras calcular el resultado de la operacion por el flujo de salida
             * se escribe el resultado.
             */
            os.write(resultado);
            /**
             * se cierran los sockets
             */
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
