package Controlador;

import Modelo.Libro;
import Modelo.DAOGenerico;

import java.util.List;

public class ControladorLibro {

    private DAOGenerico<Libro> daoLibro;

    public ControladorLibro() {
        daoLibro = new DAOGenerico(Libro.class);
    }

    public boolean addLibro(Libro libro) {
        return daoLibro.add(libro);
    }

    public List<Libro> getAllLibros(){
        return daoLibro.getAll();
    }

    public Libro getLibroById(String id) {
        return daoLibro.getById(id);
    }

    public Libro updateLibro(Libro libro) {
        return daoLibro.update(libro);
    }

    public boolean deleteAllLibros() {
        return daoLibro.deleteAll();
    }

    public boolean deleteLibro(String isbn) {
        return daoLibro.delete(getLibroById(isbn));
    }
}