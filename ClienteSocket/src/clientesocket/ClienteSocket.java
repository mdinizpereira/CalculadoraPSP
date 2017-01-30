package clientesocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class ClienteSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            /**
             * se crea el socket
             */
            System.out.println("Creando socket cliente");
            Socket cliente = new Socket();
            System.out.println("Estableciendo la conexión");
            /**
             * se define la direccion a la que se tiene que conectar
             */
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            cliente.connect(addr);
            /**
             * se crean los flujos del socket
             */
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();
            
            /**
             * Por interfaz gráfica de JOptionPane se recogen 2 numeros y el operador
             */
            System.out.println("Enviando mensaje");
            int num1 = Integer.parseInt(JOptionPane.showInputDialog("Introduce el primer numero"));
            String petuser = JOptionPane.showInputDialog("Introduce el operador");
            int num2 = Integer.parseInt(JOptionPane.showInputDialog("Introduce el segundo numero"));
            /**
             * según el operador tecleado por el usuario en la variable @petuser
             * @operador obtendrá un nuevo valor siendo 3 letras que definan la
             * operación elegida por el usuario
             */
            String operador = "";
            if (petuser.equalsIgnoreCase("+")) {
                operador = "sum";
            } else if (petuser.equalsIgnoreCase("-")) {
                operador = "res";
            } else if (petuser.equalsIgnoreCase("/")) {
                operador = "div";
            } else if (petuser.equalsIgnoreCase("*")) {
                operador = "mul";
            }
            /**
             * por el flujo de salida se le envía al servidor primero los 2 numeros
             * y posteriormente el operador
             */
            os.write(num1);
            os.write(num2);
            os.write(operador.getBytes());
            System.out.println("Mensaje enviado");
            
            /**
             * Después de enviar los datos y que el servidor los haya procesado y enviado
             * el resultado, el cliente lee por el flujo de entrada lo que le envia
             * el servidor y lo muestra por JOptionPane.
             * posteriormente se cierra la conexion
             */
            JOptionPane.showMessageDialog(null, "Resultado= " + is.read());
            System.out.println("Cerrando el socket cliente");
            cliente.close();
            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
