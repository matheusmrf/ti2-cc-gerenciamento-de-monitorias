package model;

import org.json.JSONObject;

public class Aluno implements JsonFormatter{
	private int id;
	private String nome;
	private String cpf;
	private String senha;
	private int is_monitor;
	private String curso;
	
	public Aluno() {
		this.id = -1;
		this.nome = "";
		this.cpf = "";
		this.senha = "";
		this.is_monitor = -1;
		this.curso = "";
	}
	
	public Aluno(int id, String nome, String cpf, String senha, int is_monitor, String curso) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.is_monitor = is_monitor;
		this.curso = curso;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public int getIs_monitor() {
		return is_monitor;
	}

	public void setIs_monitor(int is_monitor) {
		this.is_monitor = is_monitor;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	/**
	 * Converte um aluno para formato JSON
	 */
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("nome", this.getNome());
		obj.put("cpf", this.getCpf());
		obj.put("senha", this.getSenha());
		obj.put("is_monitor", this.getIs_monitor());
		obj.put("curso", this.getCurso());
		return obj;
	}
	
}
