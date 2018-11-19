package exemplo.controller;   
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.Ingrediente;
import exemplo.repository.IngredienteDAO;

@Controller    
@RequestMapping(path="/ingrediente/") 
public class IngredienteController {

	
	private IngredienteDAO iDAO;
	
	
	
	/*--------------------CADASTRAR---------------------------------------------------
	  
	
	  @RequestMapping(value = "cadastrar/", method = RequestMethod.POST)
	
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		uDAO = new UsuarioDAO();
		usuario = uDAO.cadastrar(usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);

	}*/
	
	
	//------------------LISTAR-------------------------------------------------------
	@RequestMapping(value = "listar/", method = RequestMethod.GET)
    public ResponseEntity<List<Ingrediente>> listaTodosIngrediente() {
		
		iDAO = new IngredienteDAO();
		List<Ingrediente> listaIngredientes = iDAO.buscarTodos();		
		return new ResponseEntity<List<Ingrediente>>(listaIngredientes, HttpStatus.OK);
	}
	
	
    //------------------EDITAR-------------------------------------------------------
	/*@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Ingrediente ingrediente){
		iDAO = new IngredienteDAO();
		iDAO.editar(ingrediente);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}*/
	
	
	//------------------EXCLUIR-------------------------------------------------------
	/*@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		iDAO = new IngredienteDAO();
		iDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}*/
	
	
	//------------------BUSCAR--------------------------------------------------------
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Ingrediente> buscarPorId(@PathVariable long id) {
		iDAO = new IngredienteDAO();
		Ingrediente ingrediente = iDAO.buscarPorId(id);
		if(ingrediente!=null) {
			return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
		}		
		return new ResponseEntity<Ingrediente>(HttpStatus.NOT_FOUND);
	}
    //-------------------BUSCAR-----------------------------------------------------------
	@RequestMapping(value = "categoria/{categoria}", method = RequestMethod.GET)
	public ResponseEntity<List<Ingrediente>> buscarTodosPorCategoria( @PathVariable String categoria) {
		iDAO = new IngredienteDAO();
		List<Ingrediente> listaIngredientes = iDAO.buscarTodosPorCategoria(categoria);
		if(listaIngredientes!=null) {
			return new ResponseEntity<List<Ingrediente>>(listaIngredientes, HttpStatus.OK);
		}		
		return new ResponseEntity<List<Ingrediente>>(HttpStatus.NOT_FOUND);
	}
}
