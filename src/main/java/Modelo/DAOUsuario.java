package Modelo;

import jakarta.persistence.Query;

public class DAOUsuario extends DAOGenerico<Usuario>{
    public DAOUsuario(Class<Usuario> clase) {
        super(clase);
    }

    public boolean tienePenalizacionUsuario(Integer id){
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :id");
        query.setParameter("id", id);

        Usuario usuario = (Usuario) query.getSingleResult();

        if(usuario.getPenalizacionHasta() != null)
            return true;


        return false;
    }
}
