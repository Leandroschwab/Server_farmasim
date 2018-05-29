package serverFarmasim;

import java.io.IOException;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadCliente extends Thread {
	private Socket clientSocket;
	private int iValor;
	  public ThreadCliente(Socket clientSocket, int iValor) {
	    this.clientSocket = clientSocket; 
	    this.iValor = iValor;
	  }
	   
	public void run(){
		try {
	        Scanner din = new Scanner(clientSocket.getInputStream());
	        String valor = din.nextLine();
			
			System.out.println("Servidor: recebeu o valor " + valor);
			try {
				System.out.println("Servidor: esperou");
				Thread.sleep(500);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String enviar = Integer.toString(iValor);
	        PrintWriter dout = new PrintWriter(clientSocket.getOutputStream(), true);
	        dout.println(enviar);
			
			System.out.println("Servidor: enviou o valor " + enviar);
			din.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
