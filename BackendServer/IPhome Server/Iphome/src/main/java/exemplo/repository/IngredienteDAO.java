package exemplo.repository;

import java.sql.PreparedStatement;   
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Ingrediente;



public class IngredienteDAO {
	
	private ConexaoMysql conexao;
	
	public IngredienteDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "iphome");
	}

	// INSERT INTO usuario VALUES(null, 'Rodrigo', 'remor', '123');
	
	
	
	
//---------------------------EDITAR------------------------------------------------------------

	
		/*public void editar(Ingrediente ingrediente) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE ingrediente SET nome_ingrediente=?, categoria=? WHERE id_ingrediente=?;";

			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
				// SQL À SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGAÇÕES PELOS VALORES QUE ESTÃO NO OBJETO
				// USU�aRIO
				statement.setString(1, ingrediente.getNomeIngrediente());
				statement.setString(2, ingrediente.getCategoria());
				statement.setLong(3, ingrediente.getIdIngrediente());

				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}*/
		
		// DELETE FROM usuario WHERE id_usuario=3;
	
	
	

//---------------------------------------BUSCAR TODOS----------------------------------------------
		
			// SELECT * FROM usuario;
			public List<Ingrediente> buscarTodos() {
				// ABRIR A CONEXÃO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM ingrediente;";
				PreparedStatement statement;
				Ingrediente ingrediente = null;
				List<Ingrediente> listaIngredientes = new ArrayList<Ingrediente>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						ingrediente = new Ingrediente();
						ingrediente.setId(rs.getLong("id_ingrediente"));
						ingrediente.setNome(rs.getString("nome_ingrediente"));
						ingrediente.setCategoria(rs.getString("categoria"));
						listaIngredientes.add(ingrediente);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaIngredientes;
			}
		
			// SELECT * FROM usuario WHERE id_usuario=2;
			public Ingrediente buscarPorId(long id) {
				// ABRIR A CONEXÃO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM ingrediente WHERE id_ingrediente=?;";
				PreparedStatement statement;
				Ingrediente ingrediente = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						ingrediente = new Ingrediente();
						ingrediente.setId(rs.getLong("id_ingrediente"));
						ingrediente.setNome(rs.getString("nome_ingrediente"));
						ingrediente.setCategoria(rs.getString("categoria"));
	
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return ingrediente;
			}	
//----------------------------------BUSCAR POR CATEGORIA---------------------------------------------------------------------
						public List<Ingrediente> buscarTodosPorCategoria(String categoria) {
							// ABRIR A CONEXÃO COM O BANCO
							this.conexao.abrirConexao();
							// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
							String sqlSelect = "SELECT * FROM ingrediente WHERE categoria=?;";
							PreparedStatement statement;
							Ingrediente ingrediente = null;
							List<Ingrediente> listaIngredientes = new ArrayList<Ingrediente>();
							try {
								statement = this.conexao.getConexao().prepareStatement(sqlSelect);
								statement.setString(1, categoria);
								ResultSet rs = statement.executeQuery();
								
								while(rs.next()) {
									// Converter um objeto ResultSet em um objeto Usuario
									ingrediente = new Ingrediente();
									ingrediente.setId(rs.getLong("id_ingrediente"));
									ingrediente.setNome(rs.getString("nome_ingrediente"));
									ingrediente.setCategoria(rs.getString("categoria"));
									listaIngredientes.add(ingrediente);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							} finally {
								this.conexao.fecharConexao();
							}
							return listaIngredientes;
						}
	}
