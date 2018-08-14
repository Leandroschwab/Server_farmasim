import java.io.IOException;

import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import _functions.ConnMySQL;

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
	        
	        
	        if(aMsgRec[0].equals("Login")) {	
	        	String msgEnviar = opLogin(aMsgRec[1],aMsgRec[2].toUpperCase());
			    PrintWriter dout = new PrintWriter(clientSocket.getOutputStream(), true);
		        dout.println(msgEnviar);
				System.out.println("Servidor: enviou o valor " + msgEnviar);
	        }
	        
	        

			din.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private String opLogin(String id,String senha) {
		String msgEnviar;
		try {
			System.out.println("Servidor: opLogin...");
			
			Connection conn = ConnMySQL.openConnMySQL();
			
			String sql = "SELECT `nome-paciente`, `password` FROM `receitas` WHERE `id-receita`=" + id + "";
	
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				try (ResultSet rs = stmt.executeQuery()) {
					if(rs.next()) {
						String senhaDB = rs.getString("password");
						if (senhaDB.equals(senha)) {
							String nomePaciente = rs.getString("nome-paciente");
							System.out.println("=========================");
							System.out.println("Nome = " + nomePaciente);
							System.out.println("=========================");
							msgEnviar = "login" + "-;-SucessoLogin";
							
							
							
						}else {
							System.out.println("=========================");
							System.out.println("Login invalido "+ senha +" "+ senhaDB);
							System.out.println("=========================");
							msgEnviar = "login" + "-;-FalhaLogin-;-Senha incorreta";
						}
					}else {
						System.out.println("=========================");
						System.out.println("Login invalido não encontrado");
						System.out.println("=========================");
						msgEnviar = "login" + "-;-FalhaLogin-;-Senha incorreta";
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msgEnviar;
	}
}
