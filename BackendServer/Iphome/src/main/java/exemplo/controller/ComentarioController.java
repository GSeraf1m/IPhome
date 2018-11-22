package exemplo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.Comentario;
import exemplo.repository.ComentarioDAO;

@Controller    
@RequestMapping(path="/comentario/") 
public class ComentarioController {

	
	private ComentarioDAO cDAO;
	
	
	//-------------------------------CADASTRAR----------------------------------------------------
	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	
	public ResponseEntity<Comentario> cadastrar(@RequestBody Comentario comentario) {
		cDAO = new ComentarioDAO();
		comentario = cDAO.cadastrar(comentario);
		return new ResponseEntity<Comentario>(comentario, HttpStatus.CREATED);

	}
	//-------------------------------LISTAR OS COMENTARIOS----------------------------------------------------
	
	/*
	@RequestMapping(value = "listar/", method = RequestMethod.GET)
    public ResponseEntity<List<Comentario>> listaTodosComentarios() {
		
		cDAO = new ComentarioDAO();
		List<Comentario> listaComentarios = cDAO.buscarTodos();		
		return new ResponseEntity<List<Comentario>>(listaComentarios, HttpStatus.OK);
	}*/
	
	//-------------------------------EDITAR----------------------------------------------------
	
	/*
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Comentario comentario){
		cDAO = new ComentarioDAO();
		cDAO.editar(comentario);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	*/
	//-------------------------------EXCLUIR----------------------------------------------------
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		cDAO = new ComentarioDAO();
		cDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	//-------------------------------BUSCAR POR ID----------------------------------------------------
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Comentario> buscarPorId(@PathVariable long id) {
		cDAO = new ComentarioDAO();
		Comentario comentario = cDAO.buscarPorId(id);
		if(comentario!=null) {
			return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
		}		
		return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
	}
	
	//-------------------------------BUSCAR POR RECEITA----------------------------------------------------
	
	
/*
	@RequestMapping(value = "{login}/{senha}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorLoginESenha(@PathVariable String login, @PathVariable String senha) {
		cDAO = new UsuarioDAO();
		Usuario usuario = cDAO.buscarPorLoginESenha(login, senha);
		if(usuario!=null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}		
		return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
	}*/
	
	@RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Comentario>> buscarTodosPorIdReceita(@PathVariable long id) {
		
		cDAO = new ComentarioDAO();
		List<Comentario> listaComentarios = cDAO.buscarTodosPorIdReceita(id);		
		return new ResponseEntity<List<Comentario>>(listaComentarios, HttpStatus.OK);
	}
}
