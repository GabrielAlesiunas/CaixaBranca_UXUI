package login;

// IMPORT ERRADO - "java.sqp"
import java.sqp.Connection;

// IMPORT CORRETO – "java.sql"
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    // Variável de conexão com o banco
    Connection conn = null;

    // Método para conectar ao banco
    public Connection conectarDB() {
        try {
            // Carrega o driver do MySQL
            // Código incorreto - "com.mysql.Driver.Manager"
            Class.forName("com.mysql.jdbc.Driver");
            
            // (Código correto - "com.mysql.jdbc.Driver")
            Class.forName("com.mysql.jdbc.Driver");

            // URL de conexão com login e senha
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";

            // Estabelece a conexão
            conn = DriverManager.getConnection(url);

        } catch (Exception e) {
            // Captura qualquer erro na conexão
            System.out.println("Erro ao conectar: " + e.getMessage());
        }

        // Retorna o objeto Connection
        return conn;
    }

    // Variáveis de estado do usuário
    public String nome = "";
    public boolean result = false;

    // Método que verifica login e senha no banco
    public boolean verificarUsuario(String login, String senha) {

        // Monta a query SQL
        // ⚠ Isso é vulnerável a SQL Injection
        String sql = "";
        sql += "SELECT nome FROM usuarios ";
        sql += "WHERE login = '" + login + "'";
        sql += " AND senha = '" + senha + "'";

        // Obtém conexão
        Connection conn = conectarDB();

        try {
            // Cria um Statement para executar a query
            Statement st = conn.createStatement();

            // Executa a consulta e guarda o resultado
            ResultSet rs = st.executeQuery(sql);

            // Se encontrou resultado...
            if (rs.next()) {
                result = true; // login válido
                nome = rs.getString("nome"); // pega o nome do usuário
            }

        } catch (Exception e) {
            // Captura qualquer erro durante a consulta
            System.out.println("Erro ao verificar usuário: " + e.getMessage());
        }

        // Retorna true se achou o usuário
        return result;
    }
}


// 1) O código foi devidamente documentado?
R: Não, esse código não possui nenhum comentário ou explicação sobre a parte lógica dele.

// 2) As variáveis e constantes possuem nomenclatura?
R: Em partes sim. A classe (User) está bem genérica, poderia ter colocado UserDAO ou UserModel. 
Uma váriavel que está errada é a (logi), o correto seria colocar login, o restante está no padrão.

// 3) Existem legibilidade e organização no código?
R: Não. No código tem vários erros de identação, blocos de código sem fechar. Método conectarDB não está declarado adequadamente.
O bloco try-catch está fora do método.

// 4) Todos os NullPointers foram tratados?
R: Não. A váriavel conn pode ser null para caso a conexão falhar. Conn, st ou rs não é verificado se são null antes de utilizados. 

// 5) As conexões utilizadas foram fechadas?
R: Não. o Código de Connection, Statement e ResultSet.
Poderiamos fechar dentro de um try:
try (Connection conn = conectarDB();
     PreparedStatement ps = conn.prepareStatement(sql);
     ResultSet rs = ps.executeQuery()) {
    ...
}



package login;

import java.sqp.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    // 1
    Connection conn = null;
    // 2
    try {
    // 3
        Class.forName("com.mysql.Driver.Manager").newInstace();
    // 4
        String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
    // 5
        conn = DriverManager.getConnection(url);
    // 6
    } catch (Exception e) {}
    // 7
    return conn;}
    // 8
    public String nome = "";
    // 9
    public boolean result = false;
    // 10
    public boolean verificarUsuario(String logi, String senha){
    // 11
        String sql = "";
    // 12
        Connection conn = conectarDB();
    // 13
        sql += "select nome from usuarios";
        sql +="where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "'";
    // 14
        try{
    // 15
            Statement st = conn.createStatement();
    // 16
            ResultSet rs = st.executeQuery(sql);
    // 17
            if(rs.next()){
    // 18
                result = true;
    // 19
                nome = rs.getString("nome");}
    // 20
        }catch (Exception e) {}
    // 21
        return result;}
    // 22
}

