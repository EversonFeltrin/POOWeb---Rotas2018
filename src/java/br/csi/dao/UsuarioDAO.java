package br.csi.dao;

import br.csi.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    create database rotas2018;
    create table usuario(
    id serial,
    nome varchar(50) not null,
    email varchar(200) unique not null,
    senha varchar(100) not null,
    primary key(id));
)

 INSET INTO usuario(nome, email, senha) 
 VALUES('alencar', 'alencar.machado@ufsm.br', '123');
INSET INTO usuario(nome, email, senha) 
 VALUES('facada', 'facada@ufsm.br', '123');
INSET INTO usuario(nome, email, senha) 
 VALUES('zoreia', 'zoreia@ufsm.br', '123');
INSET INTO usuario(nome, email, senha) 
 VALUES('kocalitro', 'kocalitro@ufsm.br', '123');

select * from usuario;


*/

public class UsuarioDAO {

    public static void main(String args[]){
       
       /*for(Usuario u : new UsuarioDAO().getUsuarios()){
                
            System.out.println("Nome: "+u.getNome());
                
       }*/
        
       Usuario u = new Usuario();
       u.setNome("Crystal");
       u.setEmail("crystal@gmail.com");
       u.setSenha("123");
       
       new UsuarioDAO().create(u);
       
    }
    
    public boolean create(Usuario usuario){
        try(Connection conn = new ConectaDB_Postgres().getConexao()){
            String sql = "INSERT INTO usuario(nome,email,senha)" + " VALUES(?, ?, ?);";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,usuario.getNome());
            pre.setString(2,usuario.getEmail());
            pre.setString(3,usuario.getSenha());
            //para retornar o id mudar retorno para int e realiar nova 
            //consulta no if pra retorno de id da tabela
            if(pre.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public Usuario read(int id){
        try(Connection conn = new ConectaDB_Postgres().getConexao()){
            String sql = "SELECT * FROM usuario" + "WHERE id = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Usuario u = new Usuario();
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean update(Usuario usuario){
        
        try(Connection conn = new ConectaDB_Postgres().getConexao()){
            String sql = "UPDATE usuario"
                        +"SET nome = ?, email = ?, senha = ?"
                        + "WHERE id = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, usuario.getNome());
            pre.setString(2, usuario.getEmail());
            pre.setString(3, usuario.getSenha());
            if (pre.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(int id){
        try(Connection conn = new ConectaDB_Postgres().getConexao()){
            String sql = "DELETE FROM usuario" + "WHERE id = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            if (pre.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return false;
    }
    
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(Connection conn = new ConectaDB_Postgres().getConexao()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
            while(rs.next()){
                Usuario us = new Usuario();
                us.setId( rs.getInt("id") );
                us.setNome( rs.getString("nome") );
                us.setEmail( rs.getString("email") );
                us.setSenha( rs.getString("senha") );
                usuarios.add(us);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return usuarios;
    }
    
}
