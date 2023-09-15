package com.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML TextField txtTitulo;

    @FXML TextArea txtNota;

    @FXML ListView<Anotacoes> listaNotas = new ListView<>();

    ArrayList<Anotacoes> notas = new ArrayList<>();

    @FXML

    private AnotacoesDao anotacoesDao = new AnotacoesDao();

    public void adicionar(){

        String titulo = txtTitulo.getText();
        String conteudo = txtNota.getText();
        
        var nota = new Anotacoes(0, titulo, conteudo);
        notas.add(nota);
        mostrarNotas();

        txtTitulo.clear();
        txtNota.clear();

        try {
            anotacoesDao.inserir(nota);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarNotas() {
    try {
        List<Anotacoes> notas = anotacoesDao.select();
        listaNotas.getItems().addAll(notas);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void clicar(){
        var nota = listaNotas.getSelectionModel().getSelectedItem();

        System.out.println(nota);
        
    }

}
