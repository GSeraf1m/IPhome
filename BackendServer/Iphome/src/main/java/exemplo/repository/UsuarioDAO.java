package exemplo.repository;

import java.sql.PreparedStatement;    
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Usuario;


public class UsuarioDAO {
	
	private ConexaoMysql conexao;
	
	public UsuarioDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "iphome");
	}
	//-----------------------------CADASTRAR-------------------------------------------------------

	public Usuario cadastrar(Usuario usuario) {
		// ABRIR A CONEX�O COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO usuario VALUES(null, ?, ?, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO USU�RIO
			statement.setString(1, usuario.getEmail());
			statement.setString(2, usuario.getNomeUsuario());
			statement.setString(3, usuario.getSenha());
			statement.setString(4, usuario.getFotoUsuario());
			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				usuario.setIdUsuario(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEX�O COM O BANCO
			this.conexao.fecharConexao();
		}
		return usuario;
	}
	
	//--------------------------------EDITAR---------------------------------------
		// id_usuario=1;
		public void editar(Usuario usuario) {
			// ABRIR A CONEX�O COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE usuario SET nome_usuario=?, email=?, senha=?, foto_usuario=? WHERE id_usuario=?;";

			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
				// SQL A SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
				// USU�RIO
				statement.setString(1, usuario.getEmail());
				statement.setString(2, usuario.getNomeUsuario());
				statement.setString(3, usuario.getSenha());
				statement.setString(4, usuario.getFotoUsuario());
				statement.setLong(5, usuario.getIdUsuario());

				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}
		
		
//		---------------------------------EXCLUIR-------------------------------------
		// DELETE FROM usuario WHERE id_usuario=3;
			public void excluir(long id) {
				// ABRIR A CONEX�O COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM usuario WHERE id_usuario=?;";
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
				// SQL A SER EXECUTADO
				try {
					PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
					statement.setLong(1, id);

					statement.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
			}
		
			//------------------------------BUSCAR TODOS-------------------------------------------------------------

			// SELECT * FROM usuario;
			public List<Usuario> buscarTodos() {
				// ABRIR A CONEX�O COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM usuario;";
				PreparedStatement statement;
				Usuario usuario = null;
				List<Usuario> listaUsuarios = new ArrayList<Usuario>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setFotoUsuario(rs.getString("foto_usuario"));
						listaUsuarios.add(usuario);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaUsuarios;
			}
		
			//--------------------------------BUSCAR POR ID-----------------------------------------------		

			// SELECT * FROM usuario WHERE id_usuario=2;
			public Usuario buscarPorId(long id) {
				// ABRIR A CONEXaO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM usuario WHERE id_usuario=?;";
				PreparedStatement statement;
				Usuario usuario = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setFotoUsuario(rs.getString("foto_usuario"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return usuario;
			}	
			
			//------------------------------BUSCAR POR LOGIN E SENHA------------------------------


			// SELECT * FROM usuario WHERE email=? AND senha=?;
			public Usuario buscarPorLoginESenha(String email, String senha) {
				// ABRIR A CONEXaO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM usuario WHERE email=? AND senha=?;";
				PreparedStatement statement;
				Usuario usuario = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setString(1, email);
					statement.setString(2, senha);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setFotoUsuario(rs.getString("foto_usuario"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return usuario;
			}
	}