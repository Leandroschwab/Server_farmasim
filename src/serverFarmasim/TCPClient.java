package serverFarmasim;


import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;
public class TCPClient{

	public static void main(String[] args) throws Exception {
		int iValor = 1; 
		while (true) {
			try (Socket socket = new Socket("192.168.0.182", 1050)) {
			
			System.out.println("Cliente: conectado ao servidor");
            String valor = "nao";


            PrintWriter dout = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("Cliente: valor " + valor + " enviado para o servidor");
			dout.println(valor);
			
            Scanner din = new Scanner(socket.getInputStream());
            String valorIncrementado = din.nextLine();
    		System.out.println("Cliente: recebido do servidor o valor " + valorIncrementado + " contador "+ iValor);
            
            iValor = iValor+1; 
            din.close();
			Thread.sleep(1000);
						}
		}
	}
}
