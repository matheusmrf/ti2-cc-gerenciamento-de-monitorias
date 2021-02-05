package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.*;

public class DAOFavoritos{

	private Connection conexao;
	
	public DAOFavoritos() {
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

	
	public boolean add(Favoritos fav) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO favoritos (id, aluno_id, monitoria_id) "
					       + "VALUES ("+fav.getId()+", "+fav.getAluno_id()+", "+fav.getMonitoria_id()+");");
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
			st.executeUpdate("DELETE FROM favoritos WHERE monitoria_id = " + id+";");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Favoritos[] getAll() {
		Favoritos[] favoritos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM favoritos;"); // talvez filtrar por id de aluno
	         if(rs.next()){
	             rs.last();
	             favoritos = new Favoritos[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                favoritos[i] = new Favoritos(rs.getInt("id"), rs.getInt("aluno_id"), rs.getInt("monitoria_id"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return favoritos;
	}
	
	public Favoritos[] getAllAluno(int id) {
		Favoritos[] favoritos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM favoritos WHERE aluno_id = "+id+";"); // talvez filtrar por id de aluno
	         if(rs.next()){
	             rs.last();
	             favoritos = new Favoritos[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                favoritos[i] = new Favoritos(rs.getInt("id"), rs.getInt("aluno_id"), rs.getInt("monitoria_id"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return favoritos;
	}
}