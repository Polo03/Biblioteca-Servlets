package org.example.servlets;

import Controlador.ControladorLibro;
import Modelo.DAOGenerico;
import Modelo.Libro;
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

@WebServlet(name = "libros", value = "/libros")
public class LibroServlets extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ObjectMapper conversorJson = new ObjectMapper();
        conversorJson.registerModule(new JavaTimeModule());
        String json_response;
        ControladorLibro controlador = new ControladorLibro();

        try {
            switch (accion) {
                case "insert":
                    controlador.addLibro(new Libro(isbn, titulo, autor));
                    out.println("<p>Libro insertado correctamente.</p>");
                    break;
                case "update":
                    controlador.updateLibro(new Libro(isbn, titulo, autor));
                    out.println("<p>Libro actualizado correctamente.</p>");
                    break;
                case "deleteAll":
                    controlador.deleteAllLibros();
                    out.println("<p>Libros eliminado correctamente.</p>");
                    break;
                case "deleteById":
                    controlador.deleteLibro(isbn);
                    out.println("<p>Libro eliminado correctamente.</p>");
                    break;
                case "selectAll":
                    out.println("<h3>Todos los Libros:</h3>");

                    List<Libro> listaLibros  = controlador.getAllLibros();
                    out.println("<p> En java " + listaLibros +"</p>");

                    json_response = conversorJson.writeValueAsString(listaLibros);
                    out.println("<p> En java json " + json_response +"</p>");
                    break;
                case "selectById":
                    out.println("<h3>Libro:</h3>");

                    Libro libro  = controlador.getLibroById(isbn);
                    out.println("<p> En java " + libro +"</p>");

                    json_response = conversorJson.writeValueAsString(libro);
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
