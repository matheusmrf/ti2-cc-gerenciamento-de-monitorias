package services;

import model.*;
import dao.*;
import app.*;

import org.json.JSONArray;

import spark.Request;
import spark.Response;

public class AlunoService {

	private DAOAlunos conexao;
	
	public AlunoService() {
		// TODO Auto-generated constructor stub
		conexao = new DAOAlunos();
	}
	
	public Object getAllAlunoLogs() {
		StringBuffer returnValue = new StringBuffer("[");
		conexao.conectar();
		if(conexao.getAll() != null) {
			Aluno[]a = conexao.getAll();
			for (int i = 0; i < a.length; i++) {
				if(i != a.length-1)
					returnValue.append(a[i].toJson()+",");
				else
					returnValue.append(a[i].toJson());
			}
		}
		returnValue.append("]");
		conexao.close();
		return returnValue.toString();
	}

}
