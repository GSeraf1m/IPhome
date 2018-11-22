package exemplo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.UsuarioComida;

public interface UsuarioComidaController {
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<UsuarioComida> cadastrar(@RequestBody UsuarioComida ureceita);
	
	@RequestMapping(value = "listar/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioComida>> listaIngredientesFav(@PathVariable long id);
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id);
}
