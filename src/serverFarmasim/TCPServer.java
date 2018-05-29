package serverFarmasim;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;




public class TCPServer {

	public static void main(String[] args){
		int iValor = 1; 
	
		try {
			ServerSocket serverSocket = new ServerSocket(1050);
			System.out.println("Servidor: Aguardando requisições...");
			while (true) {
				System.out.println("Servidor: total Threads " + java.lang.Thread.activeCount());
				Socket clientSocket = serverSocket.accept();
				new ThreadCliente(clientSocket,iValor).start();
				iValor = iValor+1; 
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
	

