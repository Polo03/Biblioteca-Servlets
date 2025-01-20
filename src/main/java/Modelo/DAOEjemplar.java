package Modelo;

import jakarta.persistence.Query;

public class DAOEjemplar extends DAOGenerico<Ejemplar>{
    public DAOEjemplar(Class<Ejemplar> clase) {
        super(clase);
    }

    public long controlarStock(){
        Query query = em.createQuery("SELECT COUNT(e) FROM Ejemplar e WHERE e.estado = :estado");
        query.setParameter("estado", "disponible");
        return (long) query.getSingleResult();
    }
}
