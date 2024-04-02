package db;

import java.sql.*;
import javax.swing.JOptionPane;

public class MySQLConnection {
    private static Connection connection = null;
    
    public static boolean connectDB() {
        String nameDB = "loginMD5";
        String user = "root";
        String password = "Jesus+2004";
        String url = "jdbc:mysql://localhost:3306/" + nameDB;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establecer la conexión
            connection = DriverManager.getConnection(url, user, password);
            if(connection != null){
                return true;
            }
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(
                    null,
                    "Error: No se encontró el driver de MySQL",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(
                    null,
                    "Fallo en la conexión de la base de datos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return false;
    }
    
    public static Connection getConnection(){
        return connection;
    }
    
    public static void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(
                    null, 
                    "Error para cerrar la conexión", 
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}