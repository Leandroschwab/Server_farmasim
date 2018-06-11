package _functions;

import java.sql.Connection;
import java.sql.DriverManager;

//import serverFarmasim._functions.Manipulador;


public class ConnMySQL {
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/farmasim?useSSL=false&&serverTimezone=UTC";

    // Conectar ao banco
    public static Connection openConnMySQL() throws Exception {

        // Capturar a conexão
        Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        System.out.println("Conectou no banco de dados");
        // Retorna a conexao aberta
        return conn;
        
    }


}
