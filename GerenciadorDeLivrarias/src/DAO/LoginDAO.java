package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Funcionario;

public class LoginDAO {

	
	
	public boolean Logar(String login, String senha) throws SQLException{
		
		Connection conexao = ModuloConexao.ModuloConexao();	
		
		String sql = "select * from tb_funcionario where nome = ? and senha = ?";
		PreparedStatement stmt = conexao.prepareCall(sql);
		
		try {
			
			stmt = conexao.prepareCall(sql);
			stmt.setString(1, login);
			stmt.setString(2, senha);
            stmt.execute();
            stmt.close(); 
            JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!");
            return true;
		} 
		
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}
	
	public void ConsultarPeloEmail (Funcionario funcionario) {
		
		Connection conexao = ModuloConexao.ModuloConexao();	
		PreparedStatement stmt = null;
		ResultSet rs;
		
		String sql = "Select * from tb_funcionario where email = ?";

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, funcionario.getEmail());
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				funcionario.setNome(rs.getString("nome"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setCelular(rs.getString("celular"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setDataCadastro(rs.getString("funcionario_desde"));
				funcionario.setCargo(rs.getString("cargo"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setStatus(rs.getBoolean("status"));
				funcionario.setIdEndereco(rs.getInt("id_endereco"));

			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
	}	
}
