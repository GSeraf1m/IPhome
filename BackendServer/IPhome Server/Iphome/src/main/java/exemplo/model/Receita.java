package exemplo.model;

import java.util.ArrayList;

public class Receita extends Comida{
	private String descricaoReceita;
	private String fotoReceita;
	private ArrayList<Ingrediente> listaIngredientes;
	
	public Receita() {
	super();
	}

	public Receita(long idReceita, String nomeReceita, String descricaoReceita,	String fotoReceita, ArrayList<Ingrediente> listaIngredientes) {
		super();
		this.descricaoReceita = descricaoReceita;
		this.fotoReceita = fotoReceita;
		this.listaIngredientes = listaIngredientes;
	}

	public String getDescricaoReceita() {
		return descricaoReceita;
	}

	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
	}

	public String getFotoReceita() {
		return fotoReceita;
	}

	public void setFotoReceita(String fotoReceita) {
		this.fotoReceita = fotoReceita;
	}

	public ArrayList<Ingrediente> getListaIngredientes() {
		return listaIngredientes;
	}

	public void setListaIngredientes(ArrayList<Ingrediente> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}
	public String toString(){
		return nome;
	}
	

}
