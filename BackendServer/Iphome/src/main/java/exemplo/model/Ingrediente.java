package exemplo.model;

public class Ingrediente extends Comida{
	private String categoria;
	
	public Ingrediente(){
	}

	public Ingrediente(long idIngrediente, String nomeIngrediente, String categoria) {
		super();
		this.categoria = categoria;
	}
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String toString(){
		return nome;
	}

}
