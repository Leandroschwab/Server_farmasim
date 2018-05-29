package serverFarmasim;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread{
	public static void main(String args[]){
		ServerSocket s = null;
		try{ //tentando criar uma conexao
			s = new ServerSocket(40000);//Cria um SocketServer com porta 40000
			while(true){
	           /* Cria um objeto Socket, mas passando informa��es caracter�sticas de um servidor,
	            *no qual somente abre uma porta e aguarda a conex�o de um cliente 
	            */
				Socket conexao = s.accept();
				//cria uma thread que envia a conexao
				Thread t = new Servidor(conexao);
				//inicia a thread t
				t.start();
			}
		}catch(IOException e){
			System.out.println("IOException "+e);
		}
	}
	private Socket conexao;
	public Servidor(Socket s){//recebe o valor do socket enviado na thread
		conexao = s;
	}
	public void run(){
		try{
			// Cria uma buffer que ir� armazenar as informa��es enviadas pelo cliente
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            // Cria uma stream de s�ida para retorno das informa��es ao cliente
            DataOutputStream outToClient = new DataOutputStream(conexao.getOutputStream());
            // Faz a leitura das informa��es enviadas pelo cliente as amazenam na vari�vel "EscritaCliente"
            String EscritaCliente = inFromClient.readLine();
            // Imprime a String modificada no console do servidor
            System.out.println("Voce Digitou: "+EscritaCliente.length()+" Digitos");
            // Imprime a String modificada no console do cliente
            outToClient.writeBytes("Voce Digitou: "+EscritaCliente.length()+" Digitos");
		}catch(IOException e){
			System.out.println("IOException "+e);
		}
	}
}