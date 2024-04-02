package users;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.MySQLConnection;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterUsers {
    private JTextField nom;
    private JTextField apPat;
    private JTextField apMat;
    private JTextField username;
    private JPasswordField password;
    private JPasswordField password2;
    
    public RegisterUsers(
            JTextField nom, 
            JTextField apPat, 
            JTextField apMat,
            JTextField username,
            JPasswordField password,
            JPasswordField password2) {
        this.nom = nom;
        this.apPat = apPat;
        this.apMat = apMat;
        this.username = username;
        this.password = password;
        this.password2 = password2;
    }
    
    public boolean addUser() {
        try {
            if(MySQLConnection.connectDB()) {
                Connection connection = MySQLConnection.getConnection();
                String query = """
                               insert into users values (default, ?, ?, 
                               ?, ?, ?);
                               """;
                PreparedStatement ps = connection.prepareStatement(query);
                Encoder encoder = new Encoder();
                ps.setString(1, nom.getText());
                ps.setString(2, apPat.getText());
                ps.setString(3, apMat.getText());
                ps.setString(4, username.getText());
                ps.setString(5, 
                        encoder.encode(
                                String.valueOf(
                                        password.getPassword())
                        ));
                ps.executeUpdate();
                MySQLConnection.closeConnection();
                return true;
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error al conectar la base de datos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Hubo un error\n" + e,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }
    
    public boolean existUser() {
        try {
            if(MySQLConnection.connectDB()) {
                Connection connection = MySQLConnection.getConnection();
                String query = "select * from users where username=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, username.getText());
                ResultSet rs = ps.executeQuery();
                return rs.next();
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error para conectarse con la base de datos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                MySQLConnection.closeConnection();
                return true;
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error para verificar la existencia del usuario\n" + e,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return true;
        }
    }
    
    public boolean samePasswords() {
        return String.valueOf(password.getPassword()).equals(
                String.valueOf(password2.getPassword()));
    }
}