package users;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import db.MySQLConnection;
import javax.swing.JOptionPane;
        
public class LoginUsers {
    private JTextField username;
    private JPasswordField password;
    
    public LoginUsers(JTextField username, JPasswordField password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean correctCredetials() {
        Encoder encoder = new Encoder();
        return encoder.decode(getPasswordUsr())
                .equals(String.valueOf(password.getPassword()));
    }
    
    private String getPasswordUsr() {
        try {
            if(MySQLConnection.connectDB()) {
                Connection connection = MySQLConnection.getConnection();
                String query = """
                               select password_usr from users 
                               where username=?
                               """;
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, username.getText());
                ResultSet rs = ps.executeQuery();
                String passwd = "";
                while(rs.next()) {
                    passwd = rs.getString("password_usr");
                }
                MySQLConnection.closeConnection();
                return passwd;
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error para conectarse a la base de datos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return "";
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Hubo un error\n" + e,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return "";
        }
    }
}
