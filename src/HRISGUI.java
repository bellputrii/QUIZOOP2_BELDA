import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HRISGUI extends JFrame{
    private JFrame frame;

    private JTextField nameField;
    private JTextField dateOfBirthField;
    private JComboBox<String> departementComboBox;
    private JComboBox<String> positionComboBox;
    private JButton addEmployeeButton;
    private JButton showDataButton;

    private JTable dataTable;
    private JButton backButtonShowData;
    private JButton backButtonInputPanel;

    // Dummy data for testing
    private ArrayList<Employee> employeeList = new ArrayList<>();

    public HRISGUI() {
        frame = new JFrame("HRIS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        showInputPanel();
        frame.pack();
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private void showInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);

        JLabel dateOfBirthLabel = new JLabel("Date of Birth (DD/MM/YYYY):");
        dateOfBirthField = new JTextField(15);

        JLabel departementLabel = new JLabel("Departement:");
        String[] departments = {"Product", "HR"};
        departementComboBox = new JComboBox<>(departments);

        JLabel positionLabel = new JLabel("Position:");
        String[] positions = {"Employee", "Developer", "HR Staff", "Manager"};
        positionComboBox = new JComboBox<>(positions);

        addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String dateOfBirth = dateOfBirthField.getText();
                String department = departementComboBox.getSelectedItem().toString();
                String position = positionComboBox.getSelectedItem().toString();

                Employee employee;
                switch (position) {
                    case "Developer":
                        employee = new Developer(name, dateOfBirth);
                        break;
                    case "HR Staff":
                        employee = new HRStaff(name, dateOfBirth);
                        break;
                    case "Manager":
                        employee = new Manager(name, dateOfBirth);
                        break;
                    default:
                        employee = new Employee(name, dateOfBirth, department.substring(0, 1), 0) {
                        };
                        break;
                }

                // Add employee to the list
                employeeList.add(employee);

                JOptionPane.showMessageDialog(HRISGUI.this, "Employee added: " + name + ", ID: " + employee.getId());
            }
        });

        showDataButton = new JButton("Show Data");
        showDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataPanel();
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(dateOfBirthLabel);
        inputPanel.add(dateOfBirthField);
        inputPanel.add(departementLabel);
        inputPanel.add(departementComboBox);
        inputPanel.add(positionLabel);
        inputPanel.add(positionComboBox);
        inputPanel.add(addEmployeeButton);
        inputPanel.add(showDataButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(inputPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void showDataPanel() {
        JPanel dataPanel = new JPanel(new BorderLayout());

        // Column names for the table
        String[] columnNames = {"Name", "Date of Birth", "Departement", "Position", "ID"};

        // Data for the table
        Object[][] data = new Object[employeeList.size()][columnNames.length];
        for (int i = 0; i < employeeList.size(); i++) {
            Employee emp = employeeList.get(i);
            data[i] = new Object[]{emp.getName(), emp.getDateOfBirth(), emp.getKodeDepartemen(), emp.getClass().getSimpleName(), emp.getId()};
        }

        // Creating the table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Creating the JTable
        dataTable = new JTable(model);

        // Adding the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(dataTable);
        dataPanel.add(scrollPane, BorderLayout.CENTER);

        // Adding back button
        backButtonShowData = new JButton("Back");
        backButtonShowData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInputPanel();
            }
        });
        dataPanel.add(backButtonShowData, BorderLayout.SOUTH);

        // Displaying the data panel
        frame.getContentPane().removeAll();
        frame.getContentPane().add(dataPanel);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HRISGUI();
            }
        });
    }
}
