package org.example.servlets;

import Modelo.DAOGenerico;
import Modelo.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "libroServlet", value = "/libroServlet")
public class LibroServlets extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOGenerico<Libro> daoLibro=new DAOGenerico<>(Libro.class);
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet LibroServlets</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet LibroServlets</h1>");
        out.println("<h1>"+daoLibro.getAll()+"</h1>");
        out.println("</body>");
        out.println("</html>");

    }
}
