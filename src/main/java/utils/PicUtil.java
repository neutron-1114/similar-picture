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

    public final static String BASEPATH = "/Users/yundongjiutian/IdeaProjects/similar_picture/src/main/resources/pic/";

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

    public static void main(String[] args) throws IOException {
        BufferedImage bi = getPicFromPath(BASEPATH + "pic2.jpg");
        BufferedImage bi2 = getPicFromPath(BASEPATH + "pic3.jpeg");
        BufferedImage grey1 = greyful(zoomBySize(bi, 8, 8));
        BufferedImage grey2 = greyful(zoomBySize(bi2, 8, 8));
        byte[] average1 = HashImg.hash(grey1);
        byte[] average2 = HashImg.hash(grey2);
        System.out.println(HashImg.diff(average1, average2));
    }



}
