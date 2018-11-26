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
import exemplo.repository.*;

@Controller    
@RequestMapping(path="/usuario_receita/") 
public class UsuarioReceitaController implements UsuarioComidaController{

//-----------------------------ADICIONAR AOS FAVORITOS-------------------------------------------------------------------------
	private UsuarioReceitaDAO urDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<UsuarioComida> cadastrar(@RequestBody UsuarioComida ureceita) {
		urDAO = new UsuarioReceitaDAO();
		ureceita = urDAO.cadastrar(ureceita);
		return new ResponseEntity<UsuarioComida>(ureceita, HttpStatus.CREATED);
	}
//---------------------------BUSCAR TODOS----------------------------------------------------------------------------------------
	@RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Comida>> listaIngredientesFav(@PathVariable long id) {
		
		urDAO = new UsuarioReceitaDAO();
		List<Comida> listaReceitasFav = urDAO.buscarTodosFavPorUsuario(id);		
		return new ResponseEntity<List<Comida>>(listaReceitasFav, HttpStatus.OK);
	}
//---------------------------EXCLUIR----------------------------------------------------------------------------------------------
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		urDAO = new UsuarioReceitaDAO();
		urDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
}