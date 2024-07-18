package org.example;
import javax.swing.*;

public class Window extends JFrame {
    public static final int WIDTH = 1115;
    public static final int HEIGHT = 600;

    public Window () {
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        Menu menu = new Menu();
        this.add(menu);
        Scene scene = new Scene();
        this.add(scene);
        this.setVisible(true);
    }

}
