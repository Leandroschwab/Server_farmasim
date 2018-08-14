import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import _functions.Manipulador;




public class TCPServer {

	public static void main(String[] args){
		ServerSocket serverSocket = null;
		
		try {

			int porta = Integer.parseInt(Manipulador.getProps("prop.server.porta"));
			serverSocket = new ServerSocket(porta);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			System.out.println("Servidor: Aguardando requisições...");
			while (true) {
				System.out.println("Servidor: total Threads " + java.lang.Thread.activeCount());
				Socket clientSocket = serverSocket.accept();
				new ThreadCliente(clientSocket).start();
			}
		}catch (IOException e) {
			e.printStackTrace();
			
		}finally {
	        if (serverSocket != null) {
	            try {
	                serverSocket.close();
	            } catch (IOException e2) {
	    			e2.printStackTrace();
	            }
	        }
		}
	}
}
