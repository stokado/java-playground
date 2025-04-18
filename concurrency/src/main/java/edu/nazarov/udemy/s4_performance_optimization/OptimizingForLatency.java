package edu.nazarov.udemy.s4_performance_optimization;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OptimizingForLatency {
    private static final String SOURCE_FILE = ClassLoader.getSystemResource("performance_optimization/many-flowers.jpg").getPath();
    private static final String DESTINATION_FILE = "./out/many-flowers.jpg";

    public static void main(String[] args) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        long startTime = System.currentTimeMillis();

        int numberOfThreads = 1;

        recolorMultithreaded(originalImage, resultImage, numberOfThreads);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        File outputFile = new File(DESTINATION_FILE);
        ImageIO.write(resultImage, "jpg", outputFile);
        System.out.println("Finished in " + elapsedTime + " ms");
    }

    private static void recolorMultithreaded(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) {
        if (numberOfThreads == 1) {
            recolorSingleThreaded(originalImage, resultImage);
            return;
        }
        List<Thread> threads = new ArrayList<>(numberOfThreads);
        int width = originalImage.getWidth();
        int height = originalImage.getHeight() / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadMultiplier = i;

            Thread thread = new Thread(() -> {
                int leftCorner = 0;
                int topCorner = height * threadMultiplier;

                recolorImage(originalImage, resultImage, leftCorner, topCorner, width, height);
            });
            threads.add(thread);
        }

        threads.forEach(Thread::start);

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    private static void recolorSingleThreaded(BufferedImage originalImage, BufferedImage resultImage) {
        recolorImage(originalImage, resultImage, 0, 0, originalImage.getWidth(), originalImage.getHeight());
    }

    private static void recolorImage(BufferedImage originalImage, BufferedImage resultImage,
                                     int leftCorner, int topCorner,
                                     int width, int height) {
        for (int x = leftCorner; x < leftCorner + width && x < originalImage.getWidth(); x++) {
            for (int y = topCorner; y < topCorner + height && y < originalImage.getHeight(); y++) {
                recolorPixel(originalImage, resultImage, x, y);
            }
        }
    }

    private static void recolorPixel(BufferedImage originalImage, BufferedImage resultImage, int x, int y) {
        int rgb = originalImage.getRGB(x, y);

        int red = getRed(rgb);
        int green = getGreen(rgb);
        int blue = getBlue(rgb);

        int newRed;
        int newGreen;
        int newBlue;

        if (isShadeOfGray(red, green, blue)) {
            newRed = Math.min(255, red + 10);
            newGreen = Math.max(0, green - 80);
            newBlue = Math.max(0, blue - 20);
        } else {
            newRed = red;
            newGreen = green;
            newBlue = blue;
        }

        int newRgb = createRGBFromColors(newRed, newGreen, newBlue);
        setRGB(resultImage, x, y, newRgb);
    }

    private static void setRGB(BufferedImage image, int x, int y, int rgb) {
        image.getRaster().setDataElements(x, y, image.getColorModel().getDataElements(rgb, null));
    }

    private static boolean isShadeOfGray(int red, int green, int blue) {
        return Math.abs(red - green) < 30 && Math.abs(red - blue) < 30 && Math.abs(green - blue) < 30;
    }

    private static int createRGBFromColors(int red, int green, int blue) {
        int rgb = 0;

        rgb |= blue;
        rgb |= green << 8;
        rgb |= red << 16;

        rgb |= 0xFF000000;
        return rgb;
    }

    private static int getBlue(int rgb) {
        return rgb & 0x000000FF;
    }

    private static int getGreen(int rgb) {
        return (rgb & 0x0000FF00) >> 8;
    }

    private static int getRed(int rgb) {
        return (rgb & 0x00FF0000) >> 16;
    }
}
