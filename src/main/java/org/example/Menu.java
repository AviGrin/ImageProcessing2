package org.example;
import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private Scene scene;
    private boolean didHePickPicture;
    public Menu (Scene scene) {
        this.scene=scene;
        didHePickPicture=false;
        this.setBounds(0, 0, 100, 600);
        this.setBackground(Color.RED);

    }
    public  void PickedPicture(){
        didHePickPicture=true;
        JButton blackToWhite = new JButton("From black to white");
        blackToWhite.setBounds(5,5,90,60);
        blackToWhite.addActionListener(e -> {
            scene.BlackToWhite();


        });
        this.add(blackToWhite);
        repaint();



    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!didHePickPicture) {

            // הגדרת צבע הטקסט
            g.setColor(Color.BLACK);
            // הגדרת הפונט של הטקסט
            g.setFont(new Font("Arial", Font.BOLD, 15));
            // כתיבת הטקסט על הפאנל
            g.drawString("בבקשה,", 0, 200);
            g.drawString("בחר תמונה", 0, 300);
        }

    }
}

