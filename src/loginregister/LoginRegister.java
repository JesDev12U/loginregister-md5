package loginregister;
import forms.Login;
import db.MySQLConnection;
import javax.swing.JOptionPane;

public class LoginRegister {

    public static void main(String[] args) {
        if(MySQLConnection.connectDB()) {
            Login login = new Login();
            login.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Error para conectarse con la base de datos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
