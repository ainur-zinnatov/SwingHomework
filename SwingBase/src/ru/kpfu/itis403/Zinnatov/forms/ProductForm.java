package ru.kpfu.itis403.Zinnatov.forms;

import com.sun.org.apache.xpath.internal.SourceTree;
import ru.kpfu.itis403.Zinnatov.entities.Product;
import ru.kpfu.itis403.Zinnatov.exceptions.DBException;
import ru.kpfu.itis403.Zinnatov.exceptions.ProductException;
import ru.kpfu.itis403.Zinnatov.repositories.ProductRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.kpfu.itis403.Zinnatov.forms.ProductTable.GUI;

public class ProductForm {
    public static void createGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        final JFrame frame = new JFrame("Product");

        JPanel panel = new JPanel();

        panel.setLayout(null);



//Name
        final JTextField textName = new JTextField();
        textName.setBounds(100, 10, 220, 23);
        panel.add(textName);


        final JLabel labelName = new JLabel("Name");
       labelName.setBounds(10, 10, 220, 30);
        panel.add(labelName);

//Price

        final JTextField textPrice = new JTextField();
        textPrice.setBounds(100, 40, 220, 23);
        panel.add(textPrice);


        final JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(10, 40, 220, 30);
        panel.add(priceLabel);
//Weight

        final JTextField textWeight = new JTextField();
        textWeight.setBounds(100, 70, 220, 23);
        panel.add(textWeight);


        final JLabel weightLabel = new JLabel("Weight");
        weightLabel.setBounds(10, 70, 220, 30);
        panel.add(weightLabel);
//Category

        final JTextField textCategory = new JTextField();
        textCategory.setBounds(100, 100, 220, 23);
        panel.add(textCategory);


        final JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(10, 100, 220, 30);
        panel.add(categoryLabel);

//manufacture
        String[] items = { "Russia", "USA", "China", "Germany", "Japan", "India","France"};

        final JLabel manufactureLabel = new JLabel("Manufacture");
        manufactureLabel.setBounds(10, 130, 220, 30);
        panel.add(manufactureLabel);

        JComboBox combo = new JComboBox(items);
        combo.setBounds(100, 130, 220, 30);
        panel.add(combo);
 //exception lable
        final JLabel exceptLabel = new JLabel("");
        exceptLabel.setBounds(10, 160, 300, 60);
        exceptLabel.setForeground(Color.red);
        panel.add(exceptLabel);
//button
        final JButton InputButton = new JButton("Add");
        InputButton.setVerticalTextPosition(AbstractButton.CENTER);
        InputButton.setHorizontalTextPosition(AbstractButton.LEADING);
        InputButton.setBounds(230, 220, 100, 30);
        panel.add(InputButton);

        InputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {


                    Product product = new Product(textName.getText(), Integer.parseInt(textPrice.getText()), Integer.parseInt(textWeight.getText()), getManufacturer(combo.getSelectedItem().toString()),textCategory.getText());

                    ProductRepository productRepository = new ProductRepository();
                    try {
                        productRepository.checkProduct(product);
                        productRepository.add(product);

                        exceptLabel.setText("Product added!");
                        exceptLabel.setForeground(Color.GREEN);
                    } catch (ProductException e1) {
                        exceptLabel.setForeground(Color.RED);
                        exceptLabel.setText(e1.getMessage());
                    } catch (DBException e2) {
                        exceptLabel.setForeground(Color.RED);
                        exceptLabel.setText(e2.getMessage());
                    }

                }catch (NumberFormatException n){
                    exceptLabel.setForeground(Color.RED);

                    exceptLabel.setText("NumberFormatException");
                }

            }
        });
//button
        final JButton ViewTable = new JButton("GetTable");
        ViewTable.setVerticalTextPosition(AbstractButton.CENTER);
        ViewTable.setHorizontalTextPosition(AbstractButton.LEADING);
        ViewTable.setBounds(100, 220, 100, 30);
        panel.add(ViewTable);

       ViewTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI();
            }});
                frame.getContentPane().add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(350, 300));
                frame.setResizable(false);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }

            private static Product.Manufacturer getManufacturer(String country) {
                for (Product.Manufacturer m : Product.Manufacturer.values()) {
                    if (m.getCountry().equals(country)) {
                        return m;
                    }
                }
                return null;
            }

            public static void main(String[] args) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        createGUI();
                    }
                });
            }
        }
