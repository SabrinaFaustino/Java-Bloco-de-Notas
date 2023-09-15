package com.example;

public record Anotacoes(Integer id, String titulo, String conteudo) {

    @Override
    public String toString(){
        return titulo;
    }

}
