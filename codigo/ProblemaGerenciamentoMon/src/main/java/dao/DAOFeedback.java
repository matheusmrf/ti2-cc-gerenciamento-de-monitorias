package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.*;

public class DAOFeedback{

	private Connection conexao;
	
	public DAOFeedback() {
		conexao = null;
	}

	public boolean conectar() {
		boolean status = false;

		try {
			Class.forName(CredenciaisDB.driverName);
			conexao = DriverManager.getConnection(CredenciaisDB.url, CredenciaisDB.username, CredenciaisDB.password);
			status = (conexao == null);
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	
	public boolean add(Feedback fb) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO feedback (id, mensagem, aluno_id) "
					       + "VALUES ("+fb.getId()+", '"+fb.getMsg()+"', "+fb.getAluno_id()+");");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM feedback WHERE id = "+id+";");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Feedback[] getAll() {
		Feedback[] feedback = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM feedback ORDER BY id;");		
	         if(rs.next()){
	             rs.last();
	             feedback = new Feedback[rs.getRow()];
	             rs.beforeFirst();
	             
	             for(int i = 0; rs.next(); i++) {
	                feedback[i] = new Feedback(rs.getInt("id"), rs.getString("mensagem"), rs.getInt("aluno_id"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return feedback;
	}
}