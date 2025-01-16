package Modelo;

import jakarta.persistence.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DAOGenerico<T> {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Class<T> clase;

    String nombreClase;

    public DAOGenerico(Class<T> clase) {
        this.clase = clase;
        nombreClase = clase.getSimpleName();
    }

    public boolean addLibro(T object){
        tx.begin();
        em.persist(object);
        tx.commit();
        return false;
    }

    //SELECT *
    public List<T> getAll(){
        return em.createQuery("SELECT t FROM "+nombreClase+" t").getResultList();
    }

    //UPDATE
    public T updateLibro(T object){
        tx.begin();
        object = em.merge(object);
        tx.commit();
        return object;
    }
    //DELETE WHERE libro.isbn
    public boolean delete(T object){
        tx.begin();
        em.remove(object);
        tx.commit();
        return true;
    }

}
