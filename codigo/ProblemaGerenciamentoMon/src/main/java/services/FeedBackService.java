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

public class FeedBackService {
	
	private DAOFeedback conexao;

	public FeedBackService() {
		// TODO Auto-generated constructor stub
		conexao = new DAOFeedback();
	}
	
	public Object publicarFeedBack(Request request) {
		conexao.conectar();
		String campoFb = request.queryParams("fbDescricao");
		int id = Integer.parseInt(request.queryParams("userId"));
		
		// Evitar id Duplicado
		int maiorId = 0;
		Feedback[]fb = conexao.getAll();
		
		if (fb != null) 
			for(int i = 0; i < fb.length; i++) 
				if(maiorId < fb[i].getId()) 
					maiorId = fb[i].getId();
					
		maiorId++;
		
		Feedback result = new Feedback(maiorId, campoFb, id);
		boolean resp = conexao.add(result);
		conexao.close();
		if(resp == true)
			return "200";
		else
			return "Error";
	}
	
	public Object getAllFeedBack() {
		StringBuffer returnValue = new StringBuffer("[");
		conexao.conectar();
		if(conexao.getAll() != null) {
			Feedback[]f=conexao.getAll();
			for (int i = 0; i < f.length; i++) {
				if(i != f.length-1)
					returnValue.append(f[i].toJson()+",");
				else
					returnValue.append(f[i].toJson());
			}
		}
		returnValue.append("]");
		conexao.close();
		return returnValue.toString();
	}
	
	public Object deletarFeedBack(Request request) {
		conexao.conectar();
		int id = Integer.parseInt(request.queryParams("idFeedback"));
		boolean resp = conexao.delete(id);
		conexao.close();
		if(resp == true)
			return "200";
		else
			return "Error";
	}

}
