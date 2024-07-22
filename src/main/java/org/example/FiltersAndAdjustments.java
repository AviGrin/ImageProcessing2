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
    public BufferedImage BlackToWhite(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color current = new Color(image.getRGB(x, y));
                if (current.getRed() > 127){
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }
                else {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
                if (current.getGreen() > 127){
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }
                else {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
                if (current.getBlue() > 127){
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }
                else {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        return image;
    }
    public BufferedImage showBorders(BufferedImage image) {
        final int MIN_DIFF_FOR_BORDER = 30;
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage borderImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                boolean border = false;
                Color current = new Color(image.getRGB(x, y));

                if (x + 1 < width) {
                    Color right = new Color(image.getRGB(x + 1, y));
                    int redDiff = Math.abs(current.getRed() - right.getRed());
                    int greenDiff = Math.abs(current.getGreen() - right.getGreen());
                    int blueDiff = Math.abs(current.getBlue() - right.getBlue());
                    int totalDiff = redDiff + greenDiff + blueDiff;
                    if (MIN_DIFF_FOR_BORDER < totalDiff) {
                        border = true;
                    }
                }

                if (!border && y + 1 < height) {
                    Color down = new Color(image.getRGB(x, y + 1));
                    int redDiff = Math.abs(current.getRed() - down.getRed());
                    int greenDiff = Math.abs(current.getGreen() - down.getGreen());
                    int blueDiff = Math.abs(current.getBlue() - down.getBlue());
                    int totalDiff = redDiff + greenDiff + blueDiff;
                    if (MIN_DIFF_FOR_BORDER < totalDiff) {
                        border = true;
                    }
                }

                if (border) {
                    borderImage.setRGB(x, y, Color.BLUE.getRGB());
                } else {
                    borderImage.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        return borderImage;
    }
}
