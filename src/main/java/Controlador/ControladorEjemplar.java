package Controlador;

import Modelo.DAOGenerico;
import Modelo.Ejemplar;

import java.util.List;

public class ControladorEjemplar {
    private DAOGenerico<Ejemplar> daoEjemplar;

    public ControladorEjemplar() {
        daoEjemplar = new DAOGenerico(Ejemplar.class);
    }

    public boolean addEjemplar(Ejemplar ejemplar) {
        return daoEjemplar.add(ejemplar);
    }

    public List<Ejemplar> getAllEjemplares(){
        return daoEjemplar.getAll();
    }

    public Ejemplar getEjemplarById(int id) {
        return daoEjemplar.getById(id);
    }

    public Ejemplar updateEjemplar(Ejemplar ejemplar) {
        return daoEjemplar.update(ejemplar);
    }

    public boolean deleteAllEjemplares() {
        return daoEjemplar.deleteAll();
    }

    public boolean deleteEjemplar(Integer id) {
        return daoEjemplar.delete(getEjemplarById(id));
    }

}
