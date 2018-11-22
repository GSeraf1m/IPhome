package exemplo.repository;

import java.util.List;
import exemplo.model.UsuarioComida;

public interface UsuarioComidaDAO{
	public UsuarioComida cadastrar(UsuarioComida ureceita);
	public void excluir(long id);
	public List<UsuarioComida> buscarTodosFavPorUsuario(long idUsuario);
}