package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FiltersAndAdjustments {
    public FiltersAndAdjustments(){

    }
    public BufferedImage adjutByScale(BufferedImage image){
        double aspectRatio = (double) image.getWidth() / image.getHeight();

        int newWidth = 1000;
        int newHeight =600;

        // שמירה על יחס האורך והרוחב המקורי
        if (1000 / (double) 600 < aspectRatio) {
            newHeight = (int) (1000 / aspectRatio);
        } else {
            newWidth = (int) (600 * aspectRatio);
        }

        // יצירת תמונה חדשה בגודל המחושב
        Image tmp = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        // ציור התמונה המכווצת לתוך התמונה החדשה
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }
}
