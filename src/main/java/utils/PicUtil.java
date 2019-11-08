package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author jixnLbj
 * @date 2019-11-05 17:00
 * @desc 图片处理
 **/

public class PicUtil {

    public static BufferedImage getPicFromPath(String path) throws IOException {
        return ImageIO.read(new FileInputStream(new File(path)));
    }

    //图片缩放
    public static BufferedImage zoomBySize(BufferedImage bi, Integer width, Integer height) {
        if (width == null || height == null) {
            return null;
        }
        Image image = bi.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage newbi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D  = newbi.createGraphics();
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.dispose();
        return newbi;
    }

    //图片灰度化
    public static BufferedImage greyful(BufferedImage bi) {
        BufferedImage newbi = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics2D  = newbi.createGraphics();
        graphics2D.drawImage(bi, 0, 0, null);
        graphics2D.dispose();
        return newbi;
    }

    public static BufferedImage myGreyful(BufferedImage bi, int threshold) {
        BufferedImage newbi = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                int rgb = bi.getRGB(i, j);
                newbi.setRGB(i, j, grey(getRed(rgb), getGreen(rgb), getBlue(rgb), threshold));
            }
        }
        return newbi;
    }

    //按照灰度阈值黑白化图片
    public static int grey(int r, int g, int b, int threshold) {
        int gray = (r * 77 + g * 151 + b * 28) >> 8;
        if (gray >= threshold) {
            return colorToRGB(0, 255, 255, 255);
        } else {
            return colorToRGB(0, 0, 0, 0);
        }
    }

    private static int colorToRGB(int alpha, int red, int green, int blue) {
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;
        return newPixel;
    }

//    public static int getRegion(int rgb) {
//
//    }

    public static int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    public static int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    public static int getBlue(int rgb) {
        return (rgb) & 0xFF;
    }

}
