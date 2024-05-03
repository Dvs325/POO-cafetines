package sv.edu.sv.poocafetines;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;

import javax.swing.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet1", value = "/login")
public class LoginServlet1 extends HttpServlet {

    public Connection conexion;
    public Statement s;
    public ResultSet rs;

    //probvando la base******
    public LoginServlet1() {
        try {
            //obtenemos el driver para mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Se obtiene una conexión con la base de datos.
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cafetines", "root", "");
            //IMPORTANTE: usuario por defecto es root y sin contraseña (password vacio)
            // Objeto de clase Statement: permite ejecutar sentencias SQL sin parámetros
            s = conexion.createStatement();
            // ResultSet: almacena tabla devuelta por la consulta hecha a MySql
            // realizada por objeto s al invocar su metodo executeQuery
            rs = s.executeQuery("select * from login");
            //Se recorre las filas de ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                //Podemos mostrar los datos de otra forma ver mas abajo e la guia.
              /*  System.out.println("ID: " + rs.getInt(1) +
                        "\nNombre: " + rs.getString(2) +
                        "\nEdad: " + rs.getString(3) +
                        "\nTelefono: " + rs.getString(4));
                System.out.println("**********************************");*/
                JOptionPane.showMessageDialog(null,"Usuario: "+rs.getString(1)+
                        "\nContraseña: "+rs.getString(2));
            }
            // Se cierra la conexión con la base de datos. NUNCA OLVIDE CERRARLA
            conexion.close();
        } catch (ClassNotFoundException e1) {
            //Error si no puedo leer el driver de MySQL
            System.out.println("ERROR:No encuentro el driver de la BD: " +
                    e1.getMessage());
        } catch (SQLException e2) {
            //Error SQL: login/password sentencia sql erronea
            System.out.println("ERROR:Fallo en SQL: " + e2.getMessage());
        }
    }

    //***********************
    //public state
   /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/




    private String message;

    public void init() {
        message = "Mi primer login";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        //JOptionPane.showMessageDialog(null, "Hola, es mi primer login");

        new LoginServlet1();
    }

    public void destroy() {
    }

}

/*
package sv.edu.sv.poocafetines;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "LoginServlet1", value = "/login")
public class LoginServlet1 {

    private String message;

    public void init() {
        message = "Mi primer login";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }

}
*/