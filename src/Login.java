import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.time.LocalDate;

public class Login extends JFrame{

    private JPanel panelMain;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    
    // create constructor for current user object
    Person currentUser = new Person();

    Login() {
        this.setTitle("Contact Project");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        centerFrame();

        // on Enter key move to password field
        txtUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key==10) {
                    txtPassword.requestFocus();
                }
            }
        });

        // on Enter key try login
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key==10) {
                    login();
                }
            }
        });

        btnLogin.addActionListener(event -> {

            if(!txtUsername.getText().isEmpty() && !txtPassword.getPassword().toString().isEmpty()) {

                login();

            }else{
                JOptionPane.showMessageDialog(this,"please enter your username and password...");
            }
        });

    }

    private void login() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db?useSSL=false","root","mysql1234");
            Statement statement = conn.createStatement();

            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());
            String query = "SELECT * FROM user WHERE username = '"+username+"' AND password = '"+password+"'";

            ResultSet result = statement.executeQuery(query);

            if(result.next()){      //if result found from database

                // adding detail about current user
                String name = result.getString("firstname") + " " + result.getString("lastname");
                String nid = result.getString("nid");
                String email = result.getString("email");
                String phoneNumber = result.getString("phone_number");
                LocalDate dob = result.getObject("dob",LocalDate.class);

                currentUser = new Person(name,nid,email, phoneNumber, dob);

                // close login frame
                dispose();

                // open next frame
                openScreen();

            }else{

                JOptionPane.showMessageDialog(this,"username or password incorrect..");
                txtPassword.setText("");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void centerFrame() {
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        setLocation(dx, dy);
    }

    private void openScreen() {
        ImageIcon img = new ImageIcon("./img/icon.png");

        Screen screen = new Screen();
        screen.setVisible(true);
        screen.setIconImage(img.getImage());


        Person tom = new Person("Tom Holland","T2602200038810C","tomholland@marvel.com", "57104790", "26/02/2000");
        Person alex = new Person("Alex Witchcraft","A10319903002871","alexwitchy@gmail.com", "59749950", "06/10/1990");
        Person alice = new Person("Alice Wonderland","A1410199930420C","alicewonderland@gmail.com", "54720829", "14/10/1999");

        screen.addPerson(currentUser);
        screen.addPerson(tom);
        screen.addPerson(alex);
        screen.addPerson(alice);
    }

    public static void main(String[] args) {
        ImageIcon img = new ImageIcon("./img/icon.png");

        Login login = new Login();
        login.setVisible(true);
        login.setIconImage(img.getImage());
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null,
                    "setLookAndFeel didn`t work: \n" + exc, "UI Failure",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
