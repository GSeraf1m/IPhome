package exemplo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Receita;
import exemplo.model.Usuario;
import exemplo.model.UsuarioComida;

public class UsuarioReceitaDAO implements UsuarioComidaDAO{
	
private ConexaoMysql conexao;
	
	public UsuarioReceitaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "iphome");
	}
	
//	--------------------------ADICIONAR AOS FAVORITOS---------------------------------------------------------------------------------
	public UsuarioComida cadastrar(UsuarioComida ureceita) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO usuario_receita VALUES(null, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, ureceita.getIdComida());
			statement.setLong(2, ureceita.getIdUsuario());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				ureceita.setIdUsuarioComida(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return ureceita;
	}
	//--------------------EXCLUIR-----------------------------------------------------------------------------------------------
				public void excluir(long id) {
					this.conexao.abrirConexao();
					String sqlDelete = "DELETE FROM usuario_receita WHERE id_usuario_receita=?;";
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
					String sqlSelect = "SELECT * FROM usuario_receita WHERE id_usuario=?;";
					PreparedStatement statement;
					UsuarioComida usuarioReceita = null;
					List<UsuarioComida> listaReceitasFav = new ArrayList<UsuarioComida>();
					try {
						statement = this.conexao.getConexao().prepareStatement(sqlSelect);
						statement.setLong(1, idUsuario);
						ResultSet rs = statement.executeQuery();
						
						while(rs.next()) {
							usuarioReceita = new UsuarioComida();
							usuarioReceita.setIdUsuarioComida(rs.getLong("id_usuario_receita"));
							
							Usuario usuario = new Usuario();
							usuario.setIdUsuario(rs.getLong("id_usuario"));
							usuarioReceita.setIdUsuario(usuario.getIdUsuario());
							
							Receita receita = new Receita();
							receita.setId(rs.getLong("id_receita"));
							usuarioReceita.setIdComida(receita.getId());
							listaReceitasFav.add(usuarioReceita);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						this.conexao.fecharConexao();
					}
					return listaReceitasFav;
				}
}