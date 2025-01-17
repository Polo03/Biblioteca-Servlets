package org.example.servlets;

import Controlador.ControladorLibro;
import Controlador.ControladorUsuario;
import Modelo.Libro;
import Modelo.Usuario;
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

@WebServlet(name = "usuarios", value = "/usuarios")
public class UsuarioServlets extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String tipo = request.getParameter("tipo");
        String penalizacionHasta = request.getParameter("penalizacionHasta");
        String accion = request.getParameter("accion");
        Integer idInt = id != null && !id.isEmpty() ? Integer.parseInt(id) : null;
        LocalDate penalizacionDate = penalizacionHasta != null && !penalizacionHasta.isEmpty()
                ? LocalDate.parse(penalizacionHasta)
                : null;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ObjectMapper conversorJson = new ObjectMapper();
        conversorJson.registerModule(new JavaTimeModule());
        String json_response;
        ControladorUsuario controlador = new ControladorUsuario();

        try {
            switch (accion) {
                case "insert":
                    controlador.addUsuario(new Usuario(0, dni, nombre, email, password, tipo, penalizacionDate));
                    out.println("<p>Usuario insertado correctamente.</p>");
                    break;
                case "update":
                    if(controlador.getUsuarioById(idInt) != null) {
                        controlador.updateUsuario(new Usuario(idInt, dni, nombre, email, password, tipo, penalizacionDate));
                        out.println("<p>Usuario actualizado correctamente.</p>");
                    }else
                        out.println("<p>Usuario no encontrado.</p>");
                    break;
                case "deleteAll":
                    controlador.deleteAllUsuarios();
                    out.println("<p>Usuarios eliminado correctamente.</p>");
                    break;
                case "deleteById":
                    if(controlador.getUsuarioById(idInt) != null) {
                        controlador.deleteUsuario(idInt);
                        out.println("<p>Usuario eliminado correctamente.</p>");
                    }else
                        out.println("<p>Usuario no encontrado.</p>");
                    break;
                case "selectAll":
                    out.println("<h3>Todos los Usuarios:</h3>");

                    List<Usuario> listaUsuarios  = controlador.getAllUsuarios();
                    out.println("<p> En java " + listaUsuarios +"</p>");

                    json_response = conversorJson.writeValueAsString(listaUsuarios);
                    out.println("<p> En java json " + json_response +"</p>");
                    break;
                case "selectById":
                    out.println("<h3>Usuario:</h3>");

                    Usuario usuario  = controlador.getUsuarioById(idInt);
                    if(usuario != null) {
                        out.println("<p> En java " + usuario +"</p>");
                        json_response = conversorJson.writeValueAsString(usuario);
                        out.println("<p> En java json " + json_response +"</p>");
                    }else
                        out.println("<p>Usuario no encontrado.</p>");

                    break;
                default:
                    out.println("<p>Acción no válida.</p>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
