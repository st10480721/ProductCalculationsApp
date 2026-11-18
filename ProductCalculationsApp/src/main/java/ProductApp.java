import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {
    private JTextField txtCustomerName;
    private JTextField txtPrice;
    private JComboBox<String> cmbProducts;

    public ProductApp() {
        setTitle("Product App");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Customer Name Label & Text Field
        JLabel lblCustomer = new JLabel("Customer Name:");
        lblCustomer.setBounds(30, 30, 120, 25);
        add(lblCustomer);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(150, 30, 180, 25);
        add(txtCustomerName);

        // Product ComboBox
        JLabel lblProduct = new JLabel("Select Product:");
        lblProduct.setBounds(30, 75, 120, 25);
        add(lblProduct);

        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        cmbProducts = new JComboBox<>(products);
        cmbProducts.setBounds(150, 75, 180, 25);
        add(cmbProducts);

        // Price Label & Text Field
        JLabel lblPrice = new JLabel("Enter Price:");
        lblPrice.setBounds(30, 120, 120, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(150, 120, 180, 25);
        add(txtPrice);

        // Process Button
        JButton btnProcess = new JButton("PROCESS");
        btnProcess.setBounds(30, 170, 300, 30);
        add(btnProcess);
        btnProcess.addActionListener(e -> processSalesData());

        // Menu Bar Setup (Q2.4)
        JMenuBar menuBar = new JMenuBar();
        
        // File Menu
        JMenu menuFile = new JMenu("File");
        JMenuItem itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(e -> System.exit(0));
        menuFile.add(itemExit);

        // Tools Menu
        JMenu menuTools = new JMenu("Tools");
        JMenuItem itemProcess = new JMenuItem("Process");
        itemProcess.addActionListener(e -> processSalesData());
        
        JMenuItem itemClear = new JMenuItem("Clear");
        itemClear.addActionListener(e -> clearForm());

        menuTools.add(itemProcess);
        menuTools.add(itemClear);

        menuBar.add(menuFile);
        menuBar.add(menuTools);
        setJMenuBar(menuBar);
    }

    private void processSalesData() {
        try {
            String customer = txtCustomerName.getText();
            String product = (String) cmbProducts.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText());

            ProductCalculations calc = new ProductCalculations(price);
            double vat = calc.GetVAT();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            // Display Message Dialog
            String message = String.format("Customer Name: %s\nProduct: %s\nVAT: R %.1f\nCOMMISSION: R %.1f\nTOTAL: R %.1f",
                    customer, product, vat, commission, total);
            JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);

            // Save to data.txt file
            try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt"))) {
                writer.println("DATA LOG");
                writer.println("**************************************************");
                writer.println("Customer Name: " + customer);
                writer.println("Product: " + product);
                writer.println("VAT: R " + vat);
                writer.println("COMMISSION: R " + commission);
                writer.println("TOTAL: R " + total);
                writer.println("**************************************************\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error writing to file.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProducts.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ProductApp().setVisible(true));
    }
}