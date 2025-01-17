package org.example.servlets;

import Controlador.ControladorEjemplar;
import Controlador.ControladorPrestamo;
import Controlador.ControladorUsuario;
import Modelo.Prestamo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "prestamos", value = "/prestamos")
public class PrestamoServlets extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String usuarioId = request.getParameter("usuario_id");
        String ejemplarId = request.getParameter("ejemplar_id");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaDevolucion = request.getParameter("fechaDevolucion");
        String accion = request.getParameter("accion");
        Integer idInt = id != null && !id.isEmpty() ? Integer.parseInt(id) : null;
        Integer usuarioIdInt = usuarioId != null && !usuarioId.isEmpty() ? Integer.parseInt(usuarioId) : null;
        Integer ejemplarIdInt = ejemplarId != null && !ejemplarId.isEmpty() ? Integer.parseInt(ejemplarId) : null;
        LocalDate fechaInicioDate = fechaInicio != null && !fechaInicio.isEmpty() ? LocalDate.parse(fechaInicio) : null;
        LocalDate fechaDevolucionDate = fechaDevolucion != null && !fechaDevolucion.isEmpty() ? LocalDate.parse(fechaDevolucion) : null;


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ObjectMapper conversorJson = new ObjectMapper();
        conversorJson.registerModule(new JavaTimeModule());
        String json_response;
        ControladorPrestamo controladorPrestamos = new ControladorPrestamo();
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        ControladorEjemplar controladorEjemplar = new ControladorEjemplar();

        try {
            switch (accion) {
                case "insert":
                    controladorPrestamos.addPrestamo(new Prestamo(0, controladorUsuario.getUsuarioById(usuarioIdInt),controladorEjemplar.getEjemplarById(ejemplarIdInt), fechaInicioDate, fechaDevolucionDate));
                    out.println("<p>Prestamo insertado correctamente.</p>");
                    break;
                case "update":
                    controladorPrestamos.updatePrestamo(new Prestamo(idInt, controladorUsuario.getUsuarioById(usuarioIdInt),controladorEjemplar.getEjemplarById(ejemplarIdInt), fechaInicioDate, fechaDevolucionDate));
                    out.println("<p>Prestamo actualizado correctamente.</p>");
                    break;
                case "deleteAll":
                    controladorPrestamos.deleteAllPrestamos();
                    out.println("<p>Prestamos eliminado correctamente.</p>");
                    break;
                case "deleteById":
                    controladorPrestamos.deletePrestamo(idInt);
                    out.println("<p>Prestamo eliminado correctamente.</p>");
                    break;
                case "selectAll":
                    out.println("<h3>Todos los Prestamos:</h3>");

                    List<Prestamo> listaPrestamos  = controladorPrestamos.getAllPrestamos();
                    out.println("<p> En java " + listaPrestamos +"</p>");

                    json_response = conversorJson.writeValueAsString(listaPrestamos);
                    out.println("<p> En java json " + json_response +"</p>");
                    break;
                case "selectById":
                    out.println("<h3>Prestamo:</h3>");

                    Prestamo prestamo  = controladorPrestamos.getPrestamoById(idInt);
                    out.println("<p> En java " + prestamo +"</p>");

                    json_response = conversorJson.writeValueAsString(prestamo);
                    out.println("<p> En java json " + json_response +"</p>");
                    break;
                default:
                    out.println("<p>Acción no válida.</p>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
