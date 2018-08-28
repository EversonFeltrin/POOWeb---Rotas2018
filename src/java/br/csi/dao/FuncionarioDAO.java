/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.csi.dao;

import br.csi.model.Funcionario;
import br.csi.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
CREATE TABLE fucnionario(
    id serail, 
    id_usuario int.
    salario float,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
    PRIMARY KEY (id)
)

INSERT INTO funcionario(id_usuario, salario) VALUES (2, 2345,32)
select * from funcionario
*/
public class FuncionarioDAO {
    public static void main (String args[]){
        /*Funcionario f = new Funcionario();
        FuncionarioDAO fDAO = new FuncionarioDAO();
        f = fDAO.read(2);
        System.out.println("Nome: " + f.getNome());
        System.out.println("Salario: " + f.getSalario());
        /*if (fDAO.delete(2)){
            System.out.println("Funcionario excluido");
        }*/
        
        Funcionario teste = new Funcionario();
        teste.setIdUsuario(3);
        /*teste.setSalario((float) 1688.96);
        new FuncionarioDAO().create(teste);
        */
        teste.setSalario((float) 6000.75);
        new FuncionarioDAO().update(teste);
        
    }
    
    public boolean create(Funcionario funcionario){
        try(Connection conn = new ConectaDB_Postgres().getConexao()){
            String sql = "INSERT INTO funcionario(id_usuario, salario) "
                        +"VALUES (?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, funcionario.getIdUsuario());
            pre.setFloat(2, funcionario.getSalario());
            if (pre.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public Funcionario read(int id){
        try(Connection conn = new ConectaDB_Postgres().getConexao()){
            String sql = "SELECT *"
                        +" FROM usuario as u, funcionario as f"
                        +" WHERE f.id_usuario = u.id"
                        +" AND f.id = ?;";
            
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1,id);
            //System.out.println(sql);
            ResultSet rs = pre.executeQuery();
            //System.out.println(rs);
            while(rs.next()){
                Funcionario func = new Funcionario();
                func.setId(id);
                func.setNome(rs.getString("nome"));
                func.setEmail(rs.getString("email"));
                func.setSenha(rs.getString("senha"));
                func.setSalario(rs.getFloat("salario"));
                //System.out.println(func);
                return func;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id){
        try(Connection conn = new ConectaDB_Postgres().getConexao()){
            String sql = "DELETE FROM funcionario "
                        +"WHERE id_usuario = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1,id);
            if(pre.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Funcionario funcionario){
        try(Connection conn = new ConectaDB_Postgres().getConexao()){
            String sql = "UPDATE funcionario "
                       + "SET salario = ?"
                       + "WHERE id_usuario = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setFloat(1, funcionario.getSalario());
            pre.setInt(2, funcionario.getIdUsuario());
            if (pre.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

