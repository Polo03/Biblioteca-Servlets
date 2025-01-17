package Controlador;

import Modelo.DAOGenerico;
import Modelo.Libro;
import Modelo.Prestamo;

import java.util.List;

public class ControladorPrestamo {
    private DAOGenerico<Prestamo> daoPrestamo;

    public ControladorPrestamo() {
        daoPrestamo = new DAOGenerico(Prestamo.class);
    }

    public boolean addPrestamo(Prestamo prestamo) {
        return daoPrestamo.add(prestamo);
    }

    public List<Prestamo> getAllPrestamos(){
        return daoPrestamo.getAll();
    }

    public Prestamo getPrestamoById(Integer id) {
        return daoPrestamo.getById(id);
    }

    public Prestamo updatePrestamo(Prestamo prestamo) {
        return daoPrestamo.update(prestamo);
    }

    public boolean deleteAllPrestamos() {
        return daoPrestamo.deleteAll();
    }

    public boolean deletePrestamo(Integer id) {
        return daoPrestamo.delete(getPrestamoById(id));
    }

}
