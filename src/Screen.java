import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Screen extends JFrame{
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JList listPeople;
    private JButton saveNewButton;
    private JButton saveExistingButton;
    private JTextField txtName;
    private JTextField txtNID;
    private JTextField txtPhoneNumber;
    private JTextField txtEmail;
    private JTextField txtDOB;
    private JLabel labelAge;
    private JPanel panelMain;
    private JButton newContactButton;
    private ArrayList<Person> people;

    private DefaultListModel listPeopleModel;

    Screen() {
        this.setTitle("Contact Project");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        centerFrame();

        people = new ArrayList<Person>();

        listPeopleModel = new DefaultListModel();
        listPeople.setModel(listPeopleModel);

        saveExistingButton.setEnabled(false);

        saveExistingButton.addActionListener(e -> {
            int index = listPeople.getSelectedIndex();
            if (index >=0){
                Person p = people.get(index);
                p.setName(txtName.getText());
                p.setNid(txtNID.getText());
                p.setEmail(txtEmail.getText());
                p.setPhoneNumber(txtPhoneNumber.getText());
                p.setDateOfBirth(txtDOB.getText());
                refreshPeopleList();
            }
        });

        saveNewButton.addActionListener(e -> {
            Person p = new Person(
                    txtName.getText(),
                    txtNID.getText(),
                    txtPhoneNumber.getText(),
                    txtEmail.getText(),
                    txtDOB.getText()
            );
            people.add(p);
            refreshPeopleList();

        });

        newContactButton.addActionListener(e -> {
            txtName.setText("");
            txtNID.setText("");
            txtEmail.setText("");
            txtPhoneNumber.setText("");
            txtDOB.setText("");

            newContactButton.setEnabled(true);
            newContactButton.setText("Clear");
            saveExistingButton.setEnabled(false);
            saveNewButton.setEnabled(true);
            labelAge.setText("");
        });

        listPeople.addListSelectionListener(e -> {
            int index = listPeople.getSelectedIndex();
            if(index>=0){
                Person p = people.get(index);
                txtName.setText(p.getName());
                txtNID.setText(p.getNid());
                txtEmail.setText(p.getEmail());
                txtPhoneNumber.setText(p.getPhoneNumber());
                txtDOB.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                labelAge.setText(String.valueOf(p.getAge()) + " years");
                saveExistingButton.setEnabled(true);
                saveNewButton.setEnabled(false);
                newContactButton.setText("New Contact");
            }
            else{
                saveExistingButton.setEnabled(false);
            }
        });

    }

    private void centerFrame() {
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        setLocation(dx, dy);
    }

    public void refreshPeopleList(){
        listPeopleModel.removeAllElements();
        for (Person p : people){
            listPeopleModel.addElement(p.getName());
        }
    }

    //function to add new Person object to the list people
    public void addPerson(Person p){
        people.add(p);
        refreshPeopleList();
    }

}
