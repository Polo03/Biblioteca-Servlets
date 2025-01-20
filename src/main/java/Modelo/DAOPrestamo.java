package Modelo;

import jakarta.persistence.Query;

public class DAOPrestamo extends DAOGenerico<Prestamo> {

    public DAOPrestamo(Class<Prestamo> clase) {
        super(clase);
    }

    public boolean tiene3PrestamosActivos(Integer idUsuario){
        Query query = em.createQuery("SELECT COUNT(p) FROM Prestamo p WHERE p.usuario = :usuario");
        query.setParameter("usuario", idUsuario);
        return (long) query.getSingleResult() >= 3;
    }

    public boolean ejemplarDiponible(Integer idEjemplar){
        Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.ejemplar = :ejemplar");
        query.setParameter("ejemplar", idEjemplar);

        Prestamo p = (Prestamo) query.getSingleResult();

        return p.getEjemplar().getEstado().equals("Disponible");

    }
}
