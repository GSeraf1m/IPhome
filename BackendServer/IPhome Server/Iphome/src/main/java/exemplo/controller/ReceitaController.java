package exemplo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.Receita;
import exemplo.repository.ReceitaDAO;

@Controller    
@RequestMapping(path="/receita/") 
public class ReceitaController {

	
	private ReceitaDAO rDAO;
	
	
	
	//--------------------CADASTRAR---------------------------------------------------
	  
	/*
	  @RequestMapping(value = "cadastrar/", method = RequestMethod.POST)
	
	public ResponseEntity<Receita> cadastrar(@RequestBody Receita receita) {
		rDAO = new ReceitaDAO();
		receita = rDAO.cadastrar(receita);
		return new ResponseEntity<Receita>(receita, HttpStatus.CREATED);

	}
	*/
	
	//------------------LISTAR-------------------------------------------------------
	@RequestMapping(value = "listar/", method = RequestMethod.GET)
    public ResponseEntity<List<Receita>> listaTodasReceita() {
		
		rDAO = new ReceitaDAO();
		List<Receita> listaReceita = rDAO.buscarTodos();		
		return new ResponseEntity<List<Receita>>(listaReceita, HttpStatus.OK);
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
	public ResponseEntity<Receita> buscarPorId(@PathVariable long id) {
		rDAO = new ReceitaDAO();
		Receita receita = rDAO.buscarPorId(id);
		if(receita!=null) {
			return new ResponseEntity<Receita>(receita, HttpStatus.OK);
		}		
		return new ResponseEntity<Receita>(HttpStatus.NOT_FOUND);
	}
}
