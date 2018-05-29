package serverFarmasim;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;

public class TCPServer {

		public static void main(String[] args) throws Exception {
			int iValor = 1; 
			while (true) {
				iValor = iValor+1; 
			try (ServerSocket serverSocket = new ServerSocket(1050)) {
				System.out.println("Servidor: Aguardando requisições...");
				Socket clientSocket = serverSocket.accept();
	
	//			InputStream socketIn = clientSocket.getInputStream();
	//			DataInputStream din = new DataInputStream(socketIn);
				
	            Scanner din = new Scanner(clientSocket.getInputStream());
	            String valor = din.nextLine();
				
				System.out.println("Servidor: recebeu o valor " + valor);
	
	
	//			OutputStream socketOut = clientSocket.getOutputStream();
	//			DataOutputStream dout = new DataOutputStream(socketOut);
				String enviar = Integer.toString(iValor);
	            PrintWriter dout = new PrintWriter(clientSocket.getOutputStream(), true);
	            dout.println(enviar);
				
				System.out.println("Servidor: enviou o valor " + enviar);

				
			}
		}
	}
}
