package br.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDB_Postgres {

     private static final String url = 
             "jdbc:postgresql://localhost:5432/rotas2018";
     private static final String user = "postgres";
     private static final String pass = "1234";
     
    public static void main(String args[]){
        new ConectaDB_Postgres().getConexao();
    }
    
    public Connection getConexao(){
        Connection conn = null;
          
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        }catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return conn;
    }
    
    
}
