package utils;

import java.awt.image.BufferedImage;

/**
 * @author jinxLbj
 * @date 2019-11-06 18:44
 * @desc 直方图方法（未完成）
 **/

public class HistogramImg {

    //获取图片的直方图值
    private static int[] getHistogram(BufferedImage bi) {
        int[] histogram = new int[64];
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                int rgb = bi.getRGB(i, j);
                int red = getRegion(getRed(rgb));
                int green = getRegion(getGreen(rgb));
                int blue = getRegion(getBlue(rgb));
            }
        }
        return histogram;
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
