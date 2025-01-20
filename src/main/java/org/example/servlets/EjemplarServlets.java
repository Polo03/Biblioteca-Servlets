package org.example.servlets;

import Controlador.ControladorEjemplar;
import Controlador.ControladorLibro;
import Controlador.ControladorPrestamo;
import Modelo.Ejemplar;
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
import java.util.List;

@WebServlet(name = "ejemplares", value = "/ejemplares")
public class EjemplarServlets extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String isbn = request.getParameter("isbn");
        String estado = request.getParameter("estado");
        String accion = request.getParameter("accion");
        Integer idInt = id != null && !id.isEmpty() ? Integer.parseInt(id) : null;
//        Integer isbnInt = isbn != null && !isbn.isEmpty() ? Integer.parseInt(isbn) : null;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ObjectMapper conversorJson = new ObjectMapper();
        conversorJson.registerModule(new JavaTimeModule());
        String json_response;
        ControladorEjemplar controladorEjemplar = new ControladorEjemplar();
        ControladorLibro controladorLibro = new ControladorLibro();
        try {
            switch (accion) {
                case "insert":
                    if(controladorLibro.getLibroByIsbn(isbn)!=null) {
                        controladorEjemplar.addEjemplar(new Ejemplar(0, controladorLibro.getLibroByIsbn(isbn), estado));
                        out.println("<p>Ejemplar insertado correctamente.</p>");
                    }else{
                        out.println("<p>Libro no existente.</p>");
                    }
                    break;
                case "update":
                    if(controladorEjemplar.getEjemplarById(idInt)!=null) {
                        if(controladorLibro.getLibroByIsbn(isbn)!=null) {
                            controladorEjemplar.updateEjemplar(new Ejemplar(idInt, controladorLibro.getLibroByIsbn(isbn), estado));
                            out.println("<p>Ejemplar actualizado correctamente.</p>");
                        }else{
                            out.println("<p>Libro no existente.</p>");
                        }
                    }else{
                        out.println("<p>Ejemplar no existente.</p>");
                    }


                    break;
                case "delete":
                    if(controladorEjemplar.getEjemplarById(idInt)!=null) {
                        controladorEjemplar.deleteEjemplar(idInt);
                        out.println("<p>Ejemplar eliminado correctamente.</p>");
                    }else{
                        out.println("<p>Ejemplar no existente.</p>");
                    }
                    break;
                case "selectAll":
                    out.println("<h3>Todos los Ejemplares:</h3>");

                    List<Ejemplar> listaEjemplares  = controladorEjemplar.getAllEjemplares();
                    out.println("<p> En java " + listaEjemplares +"</p>");

                    json_response = conversorJson.writeValueAsString(listaEjemplares);
                    out.println("<p> En java json " + json_response +"</p>");
                    break;
                case "selectById":
                    out.println("<h3>Prestamo:</h3>");
                    Ejemplar prestamo  = controladorEjemplar.getEjemplarById(idInt);
                    if(prestamo!=null) {
                        out.println("<p> En java " + prestamo +"</p>");
                        json_response = conversorJson.writeValueAsString(prestamo);
                        out.println("<p> En java json " + json_response +"</p>");
                    }else{
                        out.println("<p>Prestamo no existente.</p>");
                    }
                    break;
                default:
                    out.println("<p>Acción no válida.</p>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

    }
}
