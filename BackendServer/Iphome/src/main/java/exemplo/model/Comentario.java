package exemplo.model;

public class Comentario {
	private long idComentario;
	private String texto;
	private String data;
	private int nota;
	private Usuario usuario;
	private long idReceita;
	
	public Comentario(){
	}

	public Comentario(long idComentario, String texto, String data, int nota,
			Usuario usuario, long idReceita) {
		super();
		this.idComentario = idComentario;
		this.texto = texto;
		this.data = data;
		this.nota = nota;
		this.usuario = usuario;
		this.idReceita = idReceita;
	}

	public long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(long idComentario) {
		this.idComentario = idComentario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(long l) {
		this.idReceita = l;
	}
	
	public String toString(){
		return texto;
	}
}