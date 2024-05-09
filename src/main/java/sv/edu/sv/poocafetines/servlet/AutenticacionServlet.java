package sv.edu.sv.poocafetines.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import sv.edu.sv.poocafetines.modelos.Usuarios;
import sv.edu.sv.poocafetines.ConexionBD;

/*import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;*/
import java.io.*;
import java.sql.*;

@WebServlet("/login")
public class AutenticacionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Usuarios usuario = validarCredenciales(username, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            redireccionarSegunRol(usuario.getRol(), response);
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }

    private Usuarios validarCredenciales(String username, String password) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement("SELECT * FROM usuarios WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setUsername(resultSet.getString("username"));
                usuario.setRol(resultSet.getString("rol"));
                // Otros datos del usuario
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void redireccionarSegunRol(String rol, HttpServletResponse response) throws IOException {
        switch (rol) {
            case "admin":
                response.sendRedirect("admin.jsp");
                break;
            case "estudiante":
                response.sendRedirect("estudiante.jsp");
                break;
            case "docente":
                response.sendRedirect("docente.jsp");
                break;
            default:
                response.sendRedirect("login.jsp?error=2");
                break;
        }
    }
}