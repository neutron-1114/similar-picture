package utils;

import java.awt.image.BufferedImage;

/**
 * @author tongzi
 * @data 2019-11-05 16:52
 * @desc 通过哈希计算图片相似
 **/

public class HashImg {

    //获取图片的平均灰度值
    public static double getAverage(BufferedImage bi) {
        double total = 0d;
        int weight = bi.getWidth();
        int height = bi.getHeight();
        for (int i = 0; i < weight; i++) {
            for (int j = 0; j < height; j++) {
                total += bi.getRGB(i, j);
            }
        }
        return total / (weight * height);
    }

    //按照平均值计算出图片的哈希
    public static byte[] hash(BufferedImage bi) {
        int weight = bi.getWidth();
        int height = bi.getHeight();
        double average = getAverage(bi);
        byte[] bytes = new byte[weight * height];
        for (int i = 0; i < weight; i++) {
            for (int j = 0; j < height; j++) {
                if (bi.getRGB(i, j) > average) {
                    bytes[weight * i + j] = 1;
                } else {
                    bytes[weight * i + j] = 0;
                }
            }
        }
        return bytes;
    }

    //判断hash的不同
    public static int diff(byte[] average1, byte[] average2) {
        int diff = 0;
        for (int i = 0; i < average1.length; i++) {
            if (average1[i] != average2[i]) {
                diff++;
            }
        }
        return diff;
    }

}
