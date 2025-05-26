import controlador.logincontroller;
import vista.loginFrame;

public class main {
    public static void main(String[] args) {
        loginFrame login = new loginFrame();
        new logincontroller(login);
        login.setVisible(true);
    }
}