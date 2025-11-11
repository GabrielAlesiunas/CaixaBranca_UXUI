package login;

import java.sqp.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    Connection conn = null;
    try{
        Class.forName("com.mysql.Driver.Manager").newInstace();
        String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
        conn = DriverManager.getConnection(url);
    }catch (Exception e) {}
    return conn;}
    public String nome = "";
    public boolean result = false;
    public boolean verificarUsuario(String logi, String senha){
        String sql = "";
        Connection conn = conectarDB();

        sql += "select nome from usuarios";
        sql +="where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                result = true;
                nome = rs.getString("nome");}
        }catch (Exception e) {}
        return result;}
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
