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
     * el codigo de este proyecto es identico al clienteSocket y su javadoc
     * está explicado en este mismo
     */
    public static void main(String[] args) {
        try {
            System.out.println("Creando socket cliente");
            Socket cliente = new Socket();
            System.out.println("Estableciendo la conexión");

            InetSocketAddress addr = new InetSocketAddress("localhost", 5557);
            cliente.connect(addr);

            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();

            System.out.println("Enviando mensaje");
            int num1 = Integer.parseInt(JOptionPane.showInputDialog("Introduce el primer numero"));
            String petuser = JOptionPane.showInputDialog("Introduce el operador");
            int num2 = Integer.parseInt(JOptionPane.showInputDialog("Introduce el segundo numero"));

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
            os.write(num1);
            os.write(num2);
            os.write(operador.getBytes());

            System.out.println("Mensaje enviado");

            JOptionPane.showMessageDialog(null, "Resultado= " + is.read());
            System.out.println("Cerrando el socket cliente");

            cliente.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
