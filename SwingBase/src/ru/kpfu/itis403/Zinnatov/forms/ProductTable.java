package ru.kpfu.itis403.Zinnatov.forms;

import ru.kpfu.itis403.Zinnatov.repositories.ProductRepository;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProductTable {

    public static void GUI() {
        JFrame frame = new JFrame("Table");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String [] columns = new String[] {"name","price","weight","manufacturer","category"};
        String[][] data = ProductRepository.getTable();
        JTable table = new JTable(data, columns);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().add(scrollPane);

        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    }