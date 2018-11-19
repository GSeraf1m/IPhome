package exemplo.model;

public class Usuario {
	
	private long idUsuario;
	private String email;
	private String nomeUsuario;
	private String senha;
	private String fotoUsuario;


	public Usuario() {
	}

	public Usuario(long idUsuario, String email, String nomeUsuario, String senha,String fotoUsuario) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.fotoUsuario = fotoUsuario;

	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}
	
	
}