package serverFarmasim;

import java.io.IOException;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ThreadCliente extends Thread {
	private Socket clientSocket;

	  public ThreadCliente(Socket clientSocket) {
	    this.clientSocket = clientSocket; 
	  }
	   
	public void run(){
		try {
	        Scanner din = new Scanner(clientSocket.getInputStream());
	        String msgRecebida = din.nextLine();
	        System.out.println("Servidor: recebeu o valor " + msgRecebida);
	        String aMsgRec[]= msgRecebida.split("-;-");
	        System.out.println("Servidor: aMsgRec[0] " + aMsgRec[0]);
	        if(aMsgRec[0].equals("login")) {	
	        	opLogin();
	        }
			String enviar = "nada";
	        PrintWriter dout = new PrintWriter(clientSocket.getOutputStream(), true);
	        dout.println(enviar);
			System.out.println("Servidor: enviou o valor " + enviar);
			din.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private void opLogin() {
		System.out.println("Servidor: enviou o valor ");
		
	}
}
