package utils;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jinxLbj
 * @date 2019-11-08 12:20
 * @desc 使用大律法
 **/

public class OtsuImg {

    public static int compare(BufferedImage bi1, BufferedImage bi2) {
        bi1 = PicUtil.zoomBySize(bi1, 64, 64);
        bi2 = PicUtil.zoomBySize(bi2, 64, 64);
        int[] greys1 = buildGreysArray(bi1);
        int[] greys2 = buildGreysArray(bi2);
        int threshold1 = getBestThreshold(greys1);
        int threshold2 = getBestThreshold(greys2);
        BufferedImage newPic1 = PicUtil.myGreyful(bi1, threshold1);
        BufferedImage newPic2 = PicUtil.myGreyful(bi2, threshold2);
        int[] newgreys1 = buildGreysArray(newPic1);
        int[] newgreys2 = buildGreysArray(newPic2);
        int diff = 0;
        for (int i = 0; i < newgreys1.length; i++) {
            if (newgreys1[i] != newgreys2[i]) {
                diff++;
            }
        }
        return diff;
    }

    //获取默认的灰度数组
    public static int[] buildGreysArray(BufferedImage bi) {
        int[] greys = new int[bi.getWidth() * bi.getHeight()];
        BufferedImage newbi = PicUtil.greyful(bi);
        for (int i = 0; i < newbi.getWidth(); i++) {
            for (int j = 0; j < newbi.getHeight(); j++) {
                int rgb = newbi.getRGB(i, j);
                int red = PicUtil.getRed(rgb);
                int green = PicUtil.getGreen(rgb);
                int blue = PicUtil.getBlue(rgb);
                int gray = (red * 77 + green * 151 + blue * 28) >> 8;
                greys[i * newbi.getWidth() + j] = gray;
            }
        }
        return greys;
    }

    //获取最好的黑白阈值
    private static int getBestThreshold(int[] greys) {
        double max = 0;
        int best = 0;
        for (int i = 0; i < 256; i++) {
            double small = 0;
            double smalltotal = 0;
            double big = 0;
            double bigtotal = 0;
            for (int grey : greys) {
                if (grey < i) {
                    small++;
                    smalltotal += grey;
                } else {
                    big++;
                    bigtotal += grey;
                }
            }
            double diff = (small / greys.length) * (big / greys.length) * ((smalltotal / small) - (bigtotal / big)) * ((smalltotal / small) - (bigtotal / big));
            if (diff > max) {
                max = diff;
                best = i;
            }
        }
        return best;
    }

}
