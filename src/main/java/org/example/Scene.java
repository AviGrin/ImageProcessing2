package org.example;

import org.w3c.dom.Node;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Scene extends JPanel {
    private BufferedImage originalImage;
    private BufferedImage newImage;
    private BufferedImage combinedImage;
    private Graphics2D g2d;
    private FiltersAndAdjustments filters;
    private int lineX;
    public boolean isPressed =false;


    public Scene () {
        filters=new FiltersAndAdjustments();
        this.setBounds(100, 0, 1000, 600);
        this.setBackground(Color.GRAY);
        this.setFocusable(true);
        this.requestFocus();
        JButton openButton = new JButton("פתח תמונה");
        openButton.addActionListener(e -> {
            openImage();
            this.remove(openButton);
        });
        openButton.setSize(100,50);
        add(openButton);

    }
    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                originalImage = ImageIO.read(file);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            originalImage=filters.adjutByScale(originalImage);
            newImage=originalImage;
            combinedImage= new BufferedImage(originalImage.getWidth(),originalImage.getHeight(),BufferedImage.TYPE_INT_ARGB);
            lineX=originalImage.getWidth()/2;
            g2d = combinedImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, null);
            BufferedImage temp = newImage.getSubimage(0,0,lineX,originalImage.getHeight());
            g2d.drawImage(temp, 0, 0,null);
            g2d.setColor(Color.RED);
            g2d.fillRect(temp.getWidth()-1, 0, 2, combinedImage.getHeight());
//            addMouseListener(new MouseAdapter() {
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    if(e.getX()>lineX-2&&e.getX()<lineX+2&&e.getX()>originalImage.getWidth()&&e.getY()<originalImage.getHeight()){
//                        {
//                            isPressed=true;
//                            lineX=e.getX();
//
//                        }
//
//
//                    }
//                    repaint();
//                }
//
//                @Override
//                public void mouseReleased(MouseEvent e) {
//
//
//                    isPressed=false;
//                    repaint();
//                }
//            });
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {

                        if(e.getX()>0&&e.getX()<combinedImage.getWidth()-1&&e.getX()<originalImage.getWidth()){
                            lineX = e.getX();
                            repaint();
                        }

                }
            });
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    if(e.getX()>0&&e.getX()<combinedImage.getWidth()-1&&e.getX()<originalImage.getWidth()){
                        lineX = e.getX();
                        repaint();
                    }
                    repaint();
                }
            });

            repaint();

        }
    }
    public  void repaint()
    {
        if(originalImage!=null){
            g2d.drawImage(originalImage, 0, 0, null);
            BufferedImage temp = newImage.getSubimage(0,0,lineX,originalImage.getHeight());
            g2d.drawImage(temp, 0, 0,null);
            g2d.setColor(Color.blue);
            g2d.fillRect(lineX-1, 0, 3, combinedImage.getHeight());}
        super.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(originalImage!=null) {
            g.drawImage(combinedImage,0,0,this);


        }
        }
}
