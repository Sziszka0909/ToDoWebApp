package pack;

import jdk.nashorn.internal.objects.Global;

import javax.xml.bind.annotation.XmlElementDecl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by sziszka on 2017.05.08..
 */
public class ListServlet extends javax.servlet.http.HttpServlet {
    int key = 76;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String activity = request.getParameter("activity");
        System.out.println(activity);
        String update = String.format("INSERT INTO list(activity) " +
                "VALUES(\"%s\");", activity);
        String res = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "admin");
            PreparedStatement statement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate(update, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.getGeneratedKeys();
            PrintWriter out = response.getWriter();
            while (result.next()) {
                key = result.getInt(1);
                System.out.println(key);
                out.println(key);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //request.getRequestDispatcher("./index.html").forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
