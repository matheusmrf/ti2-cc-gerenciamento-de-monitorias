package model;

import org.json.JSONObject;

public class Feedback implements JsonFormatter{
	
	private int id;
	private String msg;
	private int aluno_id;

	public Feedback() {
		this.id = -1;
		this.msg = "";
		this.aluno_id = -1;
	}
	
	public Feedback(int id, String msg, int aluno_id) {
		this.id = id;
		this.msg = msg;
		this.aluno_id = aluno_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getAluno_id() {
		return aluno_id;
	}

	public void setAluno_id(int aluno_id) {
		this.aluno_id = aluno_id;
	}
	
	/**
	 * Converte um feedback para formato JSON
	 */
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("mensagem", this.getMsg());
		obj.put("aluno_id", this.getAluno_id());
		return obj;
	}
	

}
