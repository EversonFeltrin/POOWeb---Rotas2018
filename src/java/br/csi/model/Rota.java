package br.csi.model;

import java.util.ArrayList;

public class Rota {

    private int id;
    private String nome;
    private ArrayList<Cliente> pontosDeEntrega;
    private Funcionario entregador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Cliente> getPontosDeEntrega() {
        return pontosDeEntrega;
    }

    public void setPontosDeEntrega(ArrayList<Cliente> pontosDeEntrega) {
        this.pontosDeEntrega = pontosDeEntrega;
    }

    public Funcionario getEntregador() {
        return entregador;
    }

    public void setEntregador(Funcionario entregador) {
        this.entregador = entregador;
    }
    
}
