import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class GUI extends JFrame {



    DisplayTextArea dataDisplayTextArea;
    QueryingDB db;

    public GUI() throws IOException, SQLException {
        this.setLayout(new GridLayout(1, 2));
        this.setTitle("Coffee Shop Storage");

        db = new QueryingDB();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        this.add(mainPanel);

        //text area
        JPanel rightPanel = new JPanel(new GridLayout(1, 1));
        this.add(rightPanel);
        rightPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        rightPanel.setBorder(new TitledBorder("ALL PRODUCTS"));
        dataDisplayTextArea = new DisplayTextArea();
        rightPanel.add(dataDisplayTextArea);
        dataDisplayTextArea.setEditable(false);
        dataDisplayTextArea.displayAllCoffee();
        //Text area finish


        //addBrandPanel
        JPanel addBrandPanel = new JPanel(new GridLayout(6, 4, 1, 1));
        mainPanel.add(addBrandPanel);
        addBrandPanel.setBorder(BorderFactory.createRaisedBevelBorder());


        JTextField brandTf = new JTextField();
        JTextField typeTf = new JTextField();
        JTextField priceTf = new JTextField();
        JTextField quantityTf = new JTextField();
        JLabel descriptionLabel = new JLabel("NEW PRODUCT DETAILS");
        addBrandPanel.setBorder(new TitledBorder("EDIT PRODUCT MENU"));
        descriptionLabel.setForeground(Color.RED);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel brandLabel = createLabel("Brand");
        JLabel typeLabel = createLabel("Type");
        JLabel priceLabel = createLabel("Price");
        JLabel quantityLabel = createLabel("Quantity");
        JButton addBrandButton = new JButton("ADD BRAND");

        addBrandPanel.add(descriptionLabel);
        addBrandPanel.add(new JLabel());
        addBrandPanel.add(brandLabel);
        addBrandPanel.add(brandTf);
        addBrandPanel.add(typeLabel);
        addBrandPanel.add(typeTf);
        addBrandPanel.add(priceLabel);
        addBrandPanel.add(priceTf);
        addBrandPanel.add(quantityLabel);
        addBrandPanel.add(quantityTf);
        addBrandPanel.add(new JLabel());
        addBrandPanel.add(addBrandButton);


        addBrandButton.addActionListener(e -> {
            String brand = brandTf.getText();
            String type = typeTf.getText();
            Double priceKg = null;
            Double quantity = null;
            String errorMsg = "PLEASE : ";
            try {
                priceKg = Double.parseDouble(priceTf.getText());
            } catch (Exception ex) {
                errorMsg = errorMsg + "Invalid Price,  ";
                priceTf.setText("");
            }
            try {
                quantity = Double.parseDouble(quantityTf.getText());
            } catch (Exception ex) {
                errorMsg = errorMsg + "Invalid Quantity,  ";
                quantityTf.setText("");
            }

            boolean isBrandValid = brand.equals("");
            if (isBrandValid) {
                errorMsg = errorMsg + "Empty brand,  ";
            }
            boolean isTypeValid = type.equals("");
            if (isTypeValid) {
                errorMsg = errorMsg + "Empty type,  ";
            }

            if (priceKg == null || quantity == null || isBrandValid || isTypeValid) {
                JOptionPane.showMessageDialog(null, errorMsg);
                return;
            }


            CoffeeShop coffee = new CoffeeShop(brand, type, priceKg, quantity);
            try {
                db.addBrandQuerry(coffee);
                dataDisplayTextArea.displayAllCoffee();

            } catch (SQLException | InputMismatchException | IOException ex) {
                JOptionPane.showMessageDialog(null, "Error");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        //addBrandPanel


        // Remove record
        JPanel removePanel = new JPanel(new GridLayout(4, 2, 1, 1));
        mainPanel.add(removePanel);
        removePanel.setBorder(BorderFactory.createRaisedBevelBorder());


        JLabel removeLabel = createLabel("Remove by ID");
        JTextField removeTf = new JTextField("");
        JButton removeButton = new JButton("Remove");
        JLabel removeProductByIdDescription = createLabel("REMOVE PRODUCT BY ID");

        removePanel.add(removeProductByIdDescription);
        removeProductByIdDescription.setForeground(Color.RED);
        removePanel.add(new JLabel());
        removePanel.add(removeLabel);
        removePanel.add(removeTf);
        removePanel.add(new JLabel());
        removePanel.add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    dataDisplayTextArea.displayAllCoffee();

                    if(db.searchBrandById(Integer.parseInt(removeTf.getText())) != null){
                        db.removeBrandByIdQuerry(Integer.parseInt(removeTf.getText()));
                        dataDisplayTextArea.displayAllCoffee();
                    }
                    else {
                        throw new Exception();

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "Enter valid number");
                    removeTf.setText("");
                }

            }


        });

        //Quantity in stock
        JPanel updateQntPanel = new JPanel(new GridLayout(4, 2, 1, 1));
        mainPanel.add(updateQntPanel);
        updateQntPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        JLabel updateQntLabel = createLabel("Update Quantity in stock");
        JLabel idLabel = createLabel("ID");
        JTextField updateQntTextField = new JTextField("");
        JTextField updateByIDTextfield = new JTextField("");
        JButton updateQntButton = new JButton("Update Quantity");
        JLabel updateQuantityById = createLabel("UPDATE QUANTITY BY ID");

        updateQntPanel.add(updateQuantityById);
        updateQuantityById.setForeground(Color.RED);
        updateQntPanel.add(new JLabel());
        updateQntPanel.add(idLabel);
        updateQntPanel.add(updateByIDTextfield);
        updateQntPanel.add(updateQntLabel);
        updateQntPanel.add(updateQntTextField);
        updateQntPanel.add(new JLabel());
        updateQntPanel.add(updateQntButton);
        updateQntButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if(db.searchBrandById(Integer.parseInt(updateByIDTextfield.getText())) != null){
                        db.updateQuantityById(Integer.parseInt(updateByIDTextfield.getText()), Double.parseDouble(updateQntTextField.getText()));
                        dataDisplayTextArea.displayAllCoffee();
                    }
                    else {
                       throw new Exception();

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Enter valid ID  and Quantity");
                }
            }
        });

        //Quantity in stock end

        // Search by ID and Display details
        JPanel searchByIDPanel = new JPanel(new GridLayout(4, 2, 1, 1));
        mainPanel.add(searchByIDPanel);
        searchByIDPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        JLabel searchIdLabel = createLabel("Search by ID");
        JTextField searchByIdTf = new JTextField("");
        JButton searchButton = new JButton("Search");
        JButton resetButton = new JButton("Reset all products");
        JLabel searchByIdLabel = createLabel("SEARCH PRODUCT BY ID");
        searchByIdLabel.setForeground(Color.RED);
        searchByIDPanel.add(searchByIdLabel);
        searchByIDPanel.add(new JLabel());
        searchByIDPanel.add(searchIdLabel);
        searchByIDPanel.add(searchByIdTf);


        searchByIDPanel.add(resetButton);
        searchByIDPanel.add(searchButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    dataDisplayTextArea.displayAllCoffee();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Enter valid ID");
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataDisplayTextArea.displaySearchedProduct(Integer.parseInt(searchByIdTf.getText()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "Please enter a valid ID");

                }
            }
        });

        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public static void main(String[] args) throws SQLException, IOException {

        new GUI();

    }
}







