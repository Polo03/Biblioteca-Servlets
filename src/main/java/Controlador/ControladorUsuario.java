package Controlador;

import Modelo.DAOGenerico;
import Modelo.Usuario;

import java.util.List;

public class ControladorUsuario {
    private DAOGenerico<Usuario> daoUsuario;

    public ControladorUsuario() {
        daoUsuario = new DAOGenerico(Usuario.class);
    }

    public boolean addUsuario(Usuario usuario) {
        return daoUsuario.add(usuario);
    }

    public List<Usuario> getAllUsuarios(){
        return daoUsuario.getAll();
    }

    public Usuario getUsuarioById(Integer id) {
        return daoUsuario.getById(id);
    }

    public Usuario updateUsuario(Usuario usuario) {
        return daoUsuario.update(usuario);
    }

    public boolean deleteAllUsuarios() {
        return daoUsuario.deleteAll();
    }

    public boolean deleteUsuario(Integer id) {
        return daoUsuario.delete(getUsuarioById(id));
    }

}
