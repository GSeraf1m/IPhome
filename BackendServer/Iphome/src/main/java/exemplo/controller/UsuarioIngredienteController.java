package exemplo.controller;
  
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.Comida;
import exemplo.model.UsuarioComida;
import exemplo.repository.UsuarioIngredienteDAO;

@Controller    
@RequestMapping(path="/usuario_ingrediente/") 
public class UsuarioIngredienteController implements UsuarioComidaController{

//-----------------------------ADICIONAR AOS FAVORITOS-------------------------------------------------------------------------
	private UsuarioIngredienteDAO uiDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	
	public ResponseEntity<UsuarioComida> cadastrar(@RequestBody UsuarioComida uingrediente) {
		uiDAO = new UsuarioIngredienteDAO();
		uingrediente = uiDAO.cadastrar(uingrediente);
		return new ResponseEntity<UsuarioComida>(uingrediente, HttpStatus.CREATED);

	}
//---------------------------BUSCAR TODOS----------------------------------------------------------------------------------------
	@RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Comida>> listaIngredientesFav(@PathVariable long id) {
		
		uiDAO = new UsuarioIngredienteDAO();
		List<Comida> listaIngredientesFav = uiDAO.buscarTodosFavPorUsuario(id);		
		return new ResponseEntity<List<Comida>>(listaIngredientesFav, HttpStatus.OK);
	}
//---------------------------EXCLUIR----------------------------------------------------------------------------------------------
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		uiDAO = new UsuarioIngredienteDAO();
		uiDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
}