package pack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by sziszka on 2017.05.10..
 */
@WebServlet(name = "DatabaseServlet")
public class DatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("hidden");
        int id = Integer.parseInt(value);
        String input = String.format("DELETE FROM list " +
                "WHERE id=\"%s\"", id);
        System.out.println("Database-es id: " + id);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "admin");
            Statement statement = connection.createStatement();
            statement.executeUpdate(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "admin");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM list", Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.executeQuery("SELECT * FROM list");
            out.println("<html><head><meta charset='UTF-8'><title>Todos</title></head>");
            out.println("<body>");
            while (result.next()) {
                out.println("<colspan id=" + result.getInt(1) + ">" + result.getString("activity") + "<button id=" + result.getInt(1) + " class='butt'>Delete</button></br></colspan>");
                System.out.println(result.getInt(1));
            }
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
