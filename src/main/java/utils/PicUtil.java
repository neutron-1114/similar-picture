package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author tongzi
 * @data 2019-11-05 17:00
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

}
