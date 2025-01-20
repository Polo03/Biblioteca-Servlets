package Controlador;

import Modelo.DAOGenerico;
import Modelo.DAOPrestamo;
import Modelo.Libro;
import Modelo.Prestamo;

import java.util.List;

public class ControladorPrestamo {
    private DAOPrestamo daoPrestamo;

    public ControladorPrestamo() {
        daoPrestamo = new DAOPrestamo(Prestamo.class);
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

    public boolean tiene3PrestamosActivos(Integer idUsuario) {return daoPrestamo.tiene3PrestamosActivos(idUsuario);}

    public boolean ejemplarDisponible(Integer idEjemplar){return daoPrestamo.ejemplarDiponible(idEjemplar);}
}
