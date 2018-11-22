package exemplo.model;

public abstract class Comida{
	public long id;
	public String nome;
	
	public Comida(){
	}
	
	public Comida(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

}
