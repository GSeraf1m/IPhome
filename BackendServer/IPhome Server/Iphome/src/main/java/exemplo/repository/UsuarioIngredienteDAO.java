package exemplo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Ingrediente;
import exemplo.model.Usuario;
import exemplo.model.UsuarioComida;

public class UsuarioIngredienteDAO implements UsuarioComidaDAO{ 
	
	private ConexaoMysql conexao;

	public UsuarioIngredienteDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "iphome");
	}
	

//	--------------------------ADICIONAR AOS FAVORITOS---------------------------------------------------------------------------------
	public UsuarioComida cadastrar(UsuarioComida uingrediente) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO usuario_ingrediente VALUES(null, ?, ?);";
			try {
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				statement.setLong(1, uingrediente.getIdComida());
				statement.setLong(2, uingrediente.getIdUsuario());
				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if(rs.next()){
					uingrediente.setIdUsuarioComida(rs.getLong(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return uingrediente;
		}
	//--------------------EXCLUIR-----------------------------------------------------------------------------------------------
				public void excluir(long id) {
					this.conexao.abrirConexao();
					String sqlDelete = "DELETE FROM usuario_ingrediente WHERE id_usuario_ingrediente=?;";
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
	//--------------------BUSCAR TODOS------------------------------------------------------------------------------------------
				public List<UsuarioComida> buscarTodosFavPorUsuario(long idUsuario) {
					this.conexao.abrirConexao();
					String sqlSelect = "SELECT * FROM usuario_ingrediente WHERE id_usuario=?;";
					PreparedStatement statement;
					UsuarioComida usuarioIngrediente = null;
					List<UsuarioComida> listaIngredientesFav = new ArrayList<UsuarioComida>();
					try {
						statement = this.conexao.getConexao().prepareStatement(sqlSelect);
						statement.setLong(1, idUsuario);
						ResultSet rs = statement.executeQuery();
						
						while(rs.next()) {
							usuarioIngrediente = new UsuarioComida();
							usuarioIngrediente.setIdUsuarioComida(rs.getLong("id_usuario_ingrediente"));
							
							Usuario usuario = new Usuario();
							usuario.setIdUsuario(rs.getLong("id_usuario"));
							usuarioIngrediente.setIdUsuario(usuario.getIdUsuario());
							
							Ingrediente ingrediente = new Ingrediente();
							ingrediente.setId(rs.getLong("id_ingrediente"));
							usuarioIngrediente.setIdComida(ingrediente.getId());
							listaIngredientesFav.add(usuarioIngrediente);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						this.conexao.fecharConexao();
					}
					return listaIngredientesFav;
				}
}