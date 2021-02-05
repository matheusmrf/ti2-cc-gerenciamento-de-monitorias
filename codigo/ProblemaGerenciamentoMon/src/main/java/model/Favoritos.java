package model;

import org.json.JSONObject;

public class Favoritos implements JsonFormatter{
	
	private int id;
	private int aluno_id;
	private int monitoria_id;
	
	public Favoritos() {
		this.id = -1;
		this.aluno_id = -1;
		this.monitoria_id = -1;
	}
	
	public Favoritos(int id, int aluno_id, int monitoria_id) {
		this.id = id;
		this.aluno_id = aluno_id;
		this.monitoria_id = monitoria_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAluno_id() {
		return aluno_id;
	}

	public void setAluno_id(int aluno_id) {
		this.aluno_id = aluno_id;
	}

	public int getMonitoria_id() {
		return monitoria_id;
	}

	public void setMonitoria_id(int monitoria_id) {
		this.monitoria_id = monitoria_id;
	}
	
	/**
	 * Converte favorito para formato JSON
	 */
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("aluno_id", this.getAluno_id());
		obj.put("monitoria_id", this.getMonitoria_id());
		return obj;
	}

}
