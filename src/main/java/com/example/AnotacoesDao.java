package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnotacoesDao {
    private final String HOST = "oracle.fiap.com.br";
    private final String PORT = "1521";
    private final String DATABASE = "orcl";
    private final String USER = "rm99570";
    private final String PASS = "310505";
    private final String URL = "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + DATABASE; // JDBC URL

    public void inserir(Anotacoes anotacao) throws SQLException {
        var con = DriverManager.getConnection(URL, USER, PASS);

        var sql = "INSERT INTO blocodenotas (titulo, conteudo) VALUES (?,?)";
        var instrucao = con.prepareStatement(sql);
        instrucao.setString(1,anotacao.titulo());
        instrucao.setString(2,anotacao.conteudo());

        instrucao.executeUpdate();
        con.close();
    }

    public List<Anotacoes> select() throws SQLException {
        var con = DriverManager.getConnection(URL, USER, PASS);
        var notas = new ArrayList<Anotacoes>();

        var result = con.createStatement().executeQuery("SELECT id, titulo, conteudo FROM blocodenotas");
        
        while(result.next()){
            notas.add(new Anotacoes(
                result.getInt("id"),
                result.getString("titulo"),
                result.getString("conteudo")
            ));
        }

        con.close();
        return notas;
    }

    /*public void atualizar(Anotacoes anotacao) throws SQLException {

        var con = DriverManager.getConnection(URL, USER, PASS);
        var sql = "UPDATE blocodenotas SET titulo=?, conteudo=? WHERE id=? ";
        var instrucao = con.prepareStatement(sql);

        instrucao.setString(1, anotacao.titulo());
        instrucao.setString(2, anotacao.conteudo()); 
        instrucao.setInt(3, anotacao.id()); 
        instrucao.executeUpdate();
        con.commit();
        con.close();
    }*/
    
}
