package services;

import dao.DAOFavoritos;
import spark.Request;
import spark.Response;

import model.*;
import dao.DAOMonitoria;

public class FavoritosService {
	
	private final DAOFavoritos conexao;

	public FavoritosService() {
		// TODO Auto-generated constructor stub
		conexao = new DAOFavoritos();
	}
	
	public Object getAllFavoritosAluno(Request request, Response response) {
		DAOMonitoria conexaoMonitoria = new DAOMonitoria();
		StringBuffer returnValue = new StringBuffer("[");
		//conexoes
		conexao.conectar();
		conexaoMonitoria.conectar();
		int id = Integer.parseInt(request.queryParams("userId"));
		
		Favoritos[]favAlun = conexao.getAllAluno(id);
		if(favAlun != null) {
			int[]idMonitorias=new int[favAlun.length];
			for(int i = 0; i < favAlun.length; i++)
					idMonitorias[i] = favAlun[i].getMonitoria_id();
			
			//atividades do aluno
			
			
			if(conexaoMonitoria.getAllMonAluno(idMonitorias) != null) {
				Monitoria[]m=conexaoMonitoria.getAllMonAluno(idMonitorias);
				
				for(int i = 0;i < m.length; i++) {
					if(i != m.length-1)
						returnValue.append(m[i].toJson()+",");
					else
						returnValue.append(m[i].toJson());
				}
			}
			returnValue.append("]");
		}
		conexao.close();
		return returnValue.toString();
	}
	
	public Object addFavorito(Request request) {
		conexao.conectar();
		int alunoId = Integer.parseInt(request.queryParams("userId"));
		int monitoriaId = Integer.parseInt(request.queryParams("idMonitoria"));
		
		// Evitar id Duplicado
		int maiorId = 0;
		Favoritos[]fav = conexao.getAll();
				
		if (fav != null) 
			for(int i = 0; i < fav.length; i++) 
				if(maiorId < fav[i].getId()) 
					maiorId = fav[i].getId();
							
		maiorId++;
		
		Favoritos resultado = new Favoritos(maiorId, alunoId, monitoriaId);
		boolean resp = conexao.add(resultado);
		conexao.close();
		if(resp == true)
			return "200";
		else
			return "Error";
	}
	
	public Object deletarFavoritoAluno(Request request) {
		conexao.conectar();
		int id = Integer.parseInt(request.queryParams("idMonitoria"));
		boolean resp = conexao.delete(id);
		conexao.close();
		if(resp == true)
			return "200";
		else
			return "Error";
	}

}
