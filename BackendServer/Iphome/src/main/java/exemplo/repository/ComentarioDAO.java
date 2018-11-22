package exemplo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Comentario;
import exemplo.model.Receita;
import exemplo.model.Usuario;



public class ComentarioDAO {
	
	private ConexaoMysql conexao;
	
	public ComentarioDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "iphome");
	}


//	--------------------------CADASTRAR----------------------------------------------------------------------------------
	
	
	// INSERT INTO usuario VALUES(null, 'Rodrigo', 'remor', '123');
	public Comentario cadastrar(Comentario comentario) {
		// ABRIR A CONEXÃ¯Â¿Â½O COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERAÃ¯Â¿Â½Ã¯Â¿Â½O QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO comentario VALUES(null, ?, ?, ?, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL Ã¯Â¿Â½ SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGAÃ¯Â¿Â½Ã¯Â¿Â½ES PELOS VALORES QUE ESTÃ¯Â¿Â½O NO OBJETO USUÃ¯Â¿Â½RIO
			statement.setString(1, comentario.getTexto());
			statement.setString(2,comentario.getData());
			statement.setInt(3, comentario.getNota());
			statement.setLong(4, comentario.getUsuario().getIdUsuario());
			statement.setLong(5, comentario.getIdReceita());
			

			
			// EXECUTAR A INSTRUÃ¯Â¿Â½Ã¯Â¿Â½O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				comentario.setIdComentario(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXÃ¯Â¿Â½O COM O BANCO
			this.conexao.fecharConexao();
		}
		return comentario;
	}
	
	
//	--------------------------DELETAR----------------------------------------------------------------------------------

		
		
		// DELETE FROM usuario WHERE id_usuario=3;
			public void excluir(long id) {
				// ABRIR A CONEXÃƒO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAÃ‡ÃƒO QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM comentario WHERE id_comentario=?;";
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
				// SQL Ã€ SER EXECUTADO
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
		
			
//	--------------------------BUSCAR TODOS----------------------------------------------------------------------------------

		/*	
			
			// SELECT * FROM usuario;
			public List<Comentario> buscarTodos() {
				// ABRIR A CONEXÃƒO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAÃ‡ÃƒO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM comentario;";
				PreparedStatement statement;
				Comentario comentario = null;
				List<Comentario> listaComentarios = new ArrayList<Comentario>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						comentario = new Comentario();
						comentario.setIdComentario(rs.getLong("id_comentario"));
						comentario.setTexto(rs.getString("texto"));
						comentario.setData(rs.getDate("data"));
						comentario.setNota(rs.getInt("nota"));
						listaComentarios.add(comentario);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaComentarios;
			}
		
			*/
//	--------------------------BUSCAR----------------------------------------------------------------------------------


			// SELECT * FROM usuario WHERE id_usuario=2;
			public Comentario buscarPorId(long id) {
				// ABRIR A CONEXÃƒO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAÃ‡ÃƒO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM comentario INNER JOIN usuario ON comentario.id_usuario = usuario.id_usuario INNER JOIN receita ON comentario.id_receita = receita.id_receita WHERE id_comentario=?;";
				PreparedStatement statement;
				Comentario comentario = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						comentario = new Comentario();
						comentario.setIdComentario(rs.getLong("id_comentario"));
						comentario.setTexto(rs.getString("texto"));
						comentario.setData(rs.getString("data_comentario"));
						comentario.setNota(rs.getInt("nota"));
						
						
//						UsuarioDAO uDAO = new UsuarioDAO();
						Usuario usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setFotoUsuario(rs.getString("foto_usuario"));
						comentario.setUsuario(usuario);
						
						
						Receita receita = new Receita();
						receita.setId(rs.getLong("id_receita"));
						comentario.setIdReceita(receita.getId());
						
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return comentario;
			}
			
			
//			--------------------------BUSCAR----------------------------------------------------------------------------------


//		********************************************************************************************************************
//		*******************************CRIAR MÃ‰TODO PARA BUSCAR POR RECEITA*************************************************
//	    ********************************************************************************************************************

		/*	
		// SELECT * FROM usuario WHERE login=? AND senha=?;
			public Comentario buscarPorIdReceita(long idReceita) {
				// ABRIR A CONEXÃƒO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAÃ‡ÃƒO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM comentario INNER JOIN usuario ON comentario.id_usuario = usuario.id_usuario INNER JOIN receita ON comentario.id_receita = receita.id_receita WHERE id_comentario=?;";
				PreparedStatement statement;
				Comentario comentario = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, idReceita);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						comentario = new Comentario();
						comentario.setIdComentario(rs.getLong("id_comentario"));
						comentario.setTexto(rs.getString("texto"));
						comentario.setData(rs.getString("data_comentario"));
						comentario.setNota(rs.getInt("nota"));
						
						Usuario usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setFotoUsuario(rs.getString("foto_usuario"));
						comentario.setUsuario(usuario);
						
						Receita receita = new Receita();
						receita.setIdReceita(rs.getLong("id_receita"));
						receita.setNomeReceita(rs.getString("nome_receita"));
						receita.setDescricaoReceita(rs.getString("descricao_receita"));
						receita.setFotoReceita(rs.getString("foto_receita"));
						comentario.setReceita(receita);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return comentario;
			}
			*/
			
			
			
			//-------------------------------------------------BUSCAR TODOS POR ID RECEITA---------------------------------------------
			public List<Comentario> buscarTodosPorIdReceita(long idReceita) {
				// ABRIR A CONEXÃƒO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAÃ‡ÃƒO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM comentario INNER JOIN usuario ON comentario.id_usuario = usuario.id_usuario WHERE id_receita=?;";
				PreparedStatement statement;
				Comentario comentario = null;
				List<Comentario> listaComentarios = new ArrayList<Comentario>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, idReceita);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						comentario = new Comentario();
						comentario.setIdComentario(rs.getLong("id_comentario"));
						comentario.setTexto(rs.getString("texto"));
						comentario.setData(rs.getString("data_comentario"));
						comentario.setNota(rs.getInt("nota"));
						
						
//						UsuarioDAO uDAO = new UsuarioDAO();
						Usuario usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setFotoUsuario(rs.getString("foto_usuario"));
						comentario.setUsuario(usuario);
						
						
						Receita receita = new Receita();
						receita.setId(rs.getLong("id_receita"));
//						receita.setNomeReceita(rs.getString("nome_receita"));
//						receita.setDescricaoReceita(rs.getString("descricao_receita"));
//						receita.setFotoReceita(rs.getString("foto_receita"));
						comentario.setIdReceita(receita.getId());
						listaComentarios.add(comentario);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaComentarios;
			}
			

	}