package dao;
import java.sql.*;

import model.*;

public class DAOMonitoria{

	private Connection conexao;
	
	public DAOMonitoria() {
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
	
	public boolean add(Monitoria m) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO monitoria (id, dia, hora, presencial, monitor_id, materia, local_monitoria) "
					       + "VALUES ("+m.getId()+", '"+m.getDia()+"', '"+m.getHora()+"', "+m.getPresencial()+", '"+
					m.getMonitor()+"', '"+m.getMateria()+"', '"+m.getLocal()+"');");
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
			st.executeUpdate("DELETE FROM monitoria WHERE id = "+id+";");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Monitoria[] getAllMonAluno(int id[]) {
		Monitoria[]monitoria = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			monitoria = new Monitoria[id.length];
			for(int i = 0; i < id.length; i++) {
				String k = id[i] + "";
				ResultSet rs = st.executeQuery("SELECT * FROM monitoria WHERE id = "+k+";");
				rs.next();
		        monitoria[i] = new Monitoria(rs.getInt("id"), rs.getString("dia"), rs.getString("hora"), 
		        							 rs.getString("local_monitoria"), rs.getBoolean("presencial"),
		        							 rs.getString("monitor_id"), rs.getString("materia"));
		             
		        
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return monitoria;
	}

	
	public Monitoria[] getAll() {
		Monitoria[] monitorias = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM monitoria ORDER BY materia;");		
	         if(rs.next()){
	             rs.last();
	             monitorias = new Monitoria[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                monitorias[i] = new Monitoria(rs.getInt("id"), rs.getString("dia"), rs.getString("hora"), 
	                		                  rs.getString("local_monitoria"), rs.getBoolean("presencial"),
	                		                  	rs.getString("monitor_id"), rs.getString("materia"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return monitorias;
	}
}
