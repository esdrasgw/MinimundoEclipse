package controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionController {
	public Connection ConnectToDatabase()
    {
        Connection con = null;

        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
            if (con != null) System.out.println("Connected");
            else System.out.println("Connection failed");
        } catch (Exception e) {
            System.out.println(e);   
        }

        return con;
    }
}
