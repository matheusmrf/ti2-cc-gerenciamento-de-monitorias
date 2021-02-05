package model;

import org.json.JSONObject;

public class Monitoria implements JsonFormatter{
	
	private int id;
	private String dia;
	private String hora;
	private String local;
	private boolean presencial;
	private String monitor;
	private String materia;
	
	public Monitoria(){
		this.dia = "";
		this.hora = "";
		this.local = "";
		this.presencial = false;
		this.monitor = "";
		this.materia = "";
	}
	
	public Monitoria(int id, String dia, String hora, String local, boolean presencial, String monitor, String materia){
		this.id = id;
		this.dia = dia;
		this.hora = hora;
		this.local = local;
		this.presencial = presencial;
		this.monitor = monitor;
		this.materia = materia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hra) {
		this.hora = hra;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public boolean getPresencial() {
		return presencial;
	}

	public void setPresencial(boolean presencial) {
		this.presencial = presencial;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		return "Monitoria [dia=" + dia + ", hra=" + hora + ", local=" + local + ", presencial=" + presencial
				+ ", monitor=" + monitor + ", materia=" + materia + "]";
	}
	
	/**
	 * Converte um monitoria para formato JSON
	 */
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("dia", this.getDia());
		obj.put("hora", this.getHora());
		obj.put("presencial", this.getPresencial());
		obj.put("monitor_id", this.getMonitor());
		obj.put("materia", this.getMateria());
		obj.put("local_monitoria", this.getLocal());
		return obj;
	}

}
