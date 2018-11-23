package exemplo.repository;

import java.sql.PreparedStatement;  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Ingrediente;
import exemplo.model.Receita;



public class ReceitaDAO {
	
	private ConexaoMysql conexao;
	
	public ReceitaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "iphome");
	}
	
	
	
//	---------------------------CADASTRAR------------------------------------------------------------
/*
	public Receita cadastrar(Receita receita) {
		// ABRIR A CONEXï¿½O COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERAï¿½ï¿½O QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO receita VALUES(null, ?, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL ï¿½ SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGAï¿½ï¿½ES PELOS VALORES QUE ESTï¿½O NO OBJETO USUï¿½RIO

			statement.setString(1, receita.getNomeReceita());
			statement.setString(2, receita.getDescricaoReceita());
			statement.setString(3, receita.getFotoReceita());
			// EXECUTAR A INSTRUï¿½ï¿½O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				receita.setIdReceita(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXï¿½O COM O BANCO
			this.conexao.fecharConexao();
		}
		return receita;
	}
	// UPDATE usuario SET nome='Rodrigo', login='remor222', senha='1' WHERE
		// id_usuario=1;

*/
	
//---------------------------EDITAR------------------------------------------------------------

	
	/*	public void editar(Receita receita) {
			// ABRIR A CONEXï¿½O COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAï¿½ï¿½O QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE usuario SET nome_receita=?, descricao_receita=?, foto_receita=? WHERE id_receita=?;";

			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
				// SQL ï¿½ SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGAï¿½ï¿½ES PELOS VALORES QUE ESTï¿½O NO OBJETO
				// USUï¿½RIO
				statement.setString(1, receita.getNomeReceita());
				statement.setString(2, receita.getDescricaoReceita());
				statement.setString(2, receita.getFotoReceita());
				statement.setLong(4, receita.getIdReceita());

				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}
		*/
		// DELETE FROM usuario WHERE id_usuario=3;
	
	
	

//---------------------------EXCLUIR------------------------------------------------------------

	
		/*	public void excluir(long id) {
				// ABRIR A CONEXï¿½O COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAï¿½ï¿½O QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM receita WHERE id_receita=?;";
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
				// SQL ï¿½ SER EXECUTADO
				try {
					PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
					statement.setLong(1, id);

					statement.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
			}*/
		
			// SELECT * FROM usuario;
			public List<Receita> buscarTodos() {
				// ABRIR A CONEXï¿½O COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAï¿½ï¿½O QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM receita;";
				PreparedStatement statement;
				Receita receita = null;
				List<Receita> listaReceitas = new ArrayList<Receita>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						receita = new Receita();
						receita.setId(rs.getLong("id_receita"));
						receita.setNome(rs.getString("nome_receita"));
						receita.setDescricaoReceita(rs.getString("descricao_receita"));
						receita.setFotoReceita(rs.getString("foto_receita"));
						listaReceitas.add(receita);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaReceitas;
			}
		
			// SELECT * FROM usuario WHERE id_usuario=2;
			public Receita buscarPorId(long id) {
				// ABRIR A CONEXï¿½O COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAï¿½ï¿½O QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM receita WHERE id_receita=?;";
				PreparedStatement statement;
				Receita receita = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						receita = new Receita();
						receita.setId(rs.getLong("id_receita"));
						receita.setNome(rs.getString("nome_receita"));
						receita.setDescricaoReceita(rs.getString("descricao_receita"));
						receita.setFotoReceita(rs.getString("foto_receita"));
	
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return receita;
			}
	public List<Receita> buscarPorIdIngrediente(long idIngrediente){
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM receita_ingrediente WHERE id_ingrediente=?;";
		PreparedStatement statement;
		Receita receita = null;
		List<Receita> listaReceitas = new ArrayList<Receita>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idIngrediente);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				receita = new Receita();
				ReceitaDAO rdao = new ReceitaDAO();
				receita = rdao.buscarPorId(rs.getLong("id_receita"));
				listaReceitas.add(receita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaReceitas;
	}
	public List<Receita> buscarTodosPorIdIngrediente(ArrayList<Long> ingredientes){
		  this.conexao.abrirConexao();
		  String in = "";
		  for(int i=0;i<ingredientes.size();i++) {
			  if (i==0){
				  in+="?";
			  }else{
				  in+=",?";
			  }
		  }
		  String sqlInsert = "SELECT * FROM receita_ingrediente WHERE id_ingrediente IN ("+in+");";
		  PreparedStatement statement;
		  Receita receita;
		  List<Receita> receitasBruto = new ArrayList<Receita>();
		  try {
		  statement = this.conexao.getConexao().prepareStatement(sqlInsert);
		  int index=1;
			for(long id:ingredientes){
			    statement.setLong(index++,id);
			}
			    ResultSet rs = statement.executeQuery();
			    while(rs.next()) {
				     receita = new Receita();
				     ReceitaDAO rDAO = new ReceitaDAO();
				     receita = rDAO.buscarPorId(rs.getLong("id_receita"));
				     receitasBruto.add(receita);
			}
		  }catch(SQLException e){
		   e.printStackTrace();
		  }finally {
			  this.conexao.fecharConexao();
		  }
		ArrayList<Receita> receitasCerto = new ArrayList<Receita>();
		 for(int i=0;i<receitasBruto.size();i++){
			boolean temEmTodos=true;
			 for(int j=0;j<receitasCerto.size();j++){
				 if(receitasBruto.get(i).getId()==receitasCerto.get(j).getId()){
					 temEmTodos=false;
			 }
			 }
			 if(temEmTodos){
				 receitasCerto.add(receitasBruto.get(i));
			 }
		 }
		ArrayList<Receita> receitasPronto = new ArrayList<Receita>();
		for(int i=0;i<receitasCerto.size();i++){
			IngredienteDAO iDAO = new IngredienteDAO();
			List<Long> ingredientesReceita = iDAO.buscarTodosIdPorIdReceita(receitasCerto.get(i).getId());
			int cont=0;
			for(int j=0;j<ingredientes.size();j++){
				for(int k=0;k<ingredientesReceita.size();k++){
					if(ingredientes.get(j)==ingredientesReceita.get(k)){
						cont++;
					}
				}
			}
			if(cont==ingredientes.size()){
				receitasPronto.add(receitasCerto.get(i));
			}
		}
		 
		 return receitasPronto;
		 }
/*
			// SELECT * FROM usuario WHERE login=? AND senha=?;
			public Usuario buscarPorEmailENome(String email, String nomeUsuario) {
				// ABRIR A CONEXï¿½O COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAï¿½ï¿½O QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM ingrediente WHERE email=? AND nome_usuario=?;";
				PreparedStatement statement;
				Usuario usuario = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setString(1, email);
					statement.setString(2, nomeUsuario);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return usuario;
			}*/
}
