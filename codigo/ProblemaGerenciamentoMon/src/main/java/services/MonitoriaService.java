package services;

import model.*;
import dao.*;
import app.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.json.JSONArray;

import spark.Request;
import spark.Response;

public class MonitoriaService {
	
	private DAOMonitoria conexao;

	public MonitoriaService() {
		// TODO Auto-generated constructor stub
		conexao = new DAOMonitoria();
	}
	
	public Object publicarMonitoria(Request request, Response response) {
		conexao.conectar();
		String dia = request.queryParams("monDia");
		String hora = request.queryParams("monHora");
		String local = request.queryParams("monLocal");
		boolean presencial = Boolean.parseBoolean(request.queryParams("monPresencial"));
		String monitor = request.queryParams("monIdMonitor");
		String materia = request.queryParams("monMateria");
		 
		// Evitar id Duplicado
		int maiorId = 0;
		Monitoria[]mn = conexao.getAll();
				
		if (mn != null) 
			for(int i = 0; i < mn.length; i++) 
				if(maiorId < mn[i].getId()) 
					maiorId = mn[i].getId();
							
		maiorId++;
		
		Monitoria monitoriaCriada = new Monitoria(maiorId, dia, hora, local, presencial, monitor, materia);
		
		boolean resp = conexao.add(monitoriaCriada);
		conexao.close();
		if(resp == true)
			return "200";
		else
			return "Error";
	}
	
	public Object getAllMonitorias() {
		StringBuffer returnValue = new StringBuffer("[");
		conexao.conectar();
		if(conexao.getAll() != null) {
			Monitoria[]m=conexao.getAll();
			for(int i = 0; i < m.length; i++) {
				if(i != m.length-1)
					returnValue.append(m[i].toJson()+",");
				else
					returnValue.append(m[i].toJson());
			}
		}
		returnValue.append("]");
		conexao.close();
		return returnValue.toString();
	}
	
	public Object deletarMonitoria(Request request) {
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
