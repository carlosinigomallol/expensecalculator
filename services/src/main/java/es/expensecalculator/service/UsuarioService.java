package es.expensecalculator.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.expensecalculator.dao.IUsuarioDAO;
import es.expensecalculator.model.Usuario;
import es.expensecalculator.web.param.UsuarioParam;

@Transactional(readOnly = true)
public class UsuarioService implements IUsuarioService {

	/** DAO de las cuenta-titular */
	IUsuarioDAO usuarioDAO;

	@Override
	public Usuario getUsuarioById(long id) {
		return usuarioDAO.getUsuarioById(id);
	}

	@Override
	public List<Usuario> getUsuarioListWithRestrictions(UsuarioParam filter) {
		return usuarioDAO.getUsuarioListWithRestrictions(filter);
	}

	@Transactional(readOnly = false)
	public void addUsuario(Usuario usuario) {
		usuarioDAO.addUsuario(usuario);
	}

	@Transactional(readOnly = false)
	public void actualizarUsuario(Usuario usuario) {
		usuarioDAO.updateUsuario(usuario);
	}

	@Transactional(readOnly = false)
	public void borrarUsuario(Usuario usuario) {
		usuarioDAO.deleteUsuario(usuario);

	}

	public IUsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(IUsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	
		

}
