package Modelo;

import jakarta.persistence.*;
import java.util.List;


public class DAOGenerico<T> {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidad-biblioteca");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Class<T> clase;
    String nombreClase;

    public DAOGenerico(Class<T> clase) {
        this.clase = clase;
        nombreClase = clase.getSimpleName();
    }

    //ADD
    public boolean add(T object){
        tx.begin();
        em.persist(object);
        tx.commit();
        return false;
    }

    //SELECT *
    public List<T> getAll(){
        return em.createQuery("SELECT t FROM "+nombreClase+" t").getResultList();
    }

    //SELECT BY ID
    public T getById(Object id) {
        T entity = em.find(clase, id);
        return entity; // Retorna un Optional, que puede ser vacío si no se encuentra la entidad
    }

    //UPDATE
    public T update(T object){
        tx.begin();
        object = em.merge(object);
        tx.commit();
        return object;
    }

    //DELETE BY ID
    public boolean delete(Object entity){
        tx.begin();
        em.remove(entity);
        tx.commit();
        return true;
    }

    //DELETE ALL
    public boolean deleteAll(){
        tx.begin();
        em.createQuery("DELETE FROM " + nombreClase + " e").executeUpdate();
        tx.commit();
        return true;
    }
}
