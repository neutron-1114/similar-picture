package utils;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;

/**
 * @author jinxLbj
 * @date 2019-11-06 18:44
 * @desc 直方图方法
 **/

public class HistogramImg {

    //获取图片的直方图值
    public static int[] getHistogram(BufferedImage bi) {
        int[] histogram = new int[64];
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                int rgb = bi.getRGB(i, j);
                int red = getRegion(getRed(rgb));
                int green = getRegion(getGreen(rgb));
                int blue = getRegion(getBlue(rgb));
                histogram[red * 16 + green * 4 + blue]++;
            }
        }
        return histogram;
    }

    public static double calculate(int[] histogram1, int[] histogram2) {
        //x1*x2 + y1*y2 + z1*z2 + .....
        //(x1^2 + y1^2 + z1^2 + .....) * (x2^2 + y2^2 + z2^2 + .....)
        BigDecimal child = BigDecimal.valueOf(0);
        for (int i = 0; i < histogram1.length; i++) {
            child = child.add(BigDecimal.valueOf(histogram1[i]).multiply(BigDecimal.valueOf(histogram2[i])));
        }
        BigDecimal h1 = BigDecimal.valueOf(0);
        BigDecimal h2 = BigDecimal.valueOf(0);
        for (int i = 0; i < histogram1.length; i++) {
            h1 = h1.add(BigDecimal.valueOf(histogram1[i]).pow(2));
            h2 = h2.add(BigDecimal.valueOf(histogram2[i]).pow(2));
        }
        double parent = Math.sqrt(Double.valueOf(h1.toString())) * Math.sqrt(Double.valueOf(h2.toString()));
        return Double.valueOf(child.toString()) / parent;
    }

    private static int getRegion(int number) {
        if (number < 64) {
            return 0;
        } else if (number < 128) {
            return 1;
        } else if (number < 192) {
            return 2;
        }
        return 3;
    }

    private static int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    private static int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    private static int getBlue(int rgb) {
        return (rgb) & 0xFF;
    }

}
