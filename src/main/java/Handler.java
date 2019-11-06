import utils.HashImg;
import utils.PicUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author tongzi
 * @data 2019-11-06 18:35
 * @desc Handler
 **/

public class Handler {

    public final static String BASEPATH = "/Users/yundongjiutian/IdeaProjects/similar_picture/src/main/resources/pic/";

    public static void main(String[] args) throws IOException {
        BufferedImage bi = PicUtil.getPicFromPath(BASEPATH + "pic2.jpg");
        BufferedImage bi2 = PicUtil.getPicFromPath(BASEPATH + "pic3.jpeg");
        method1(bi, bi2);
    }

    private static void method1(BufferedImage bi1, BufferedImage bi2) {
        BufferedImage grey1 = PicUtil.greyful(PicUtil.zoomBySize(bi1, 64, 64));
        BufferedImage grey2 = PicUtil.greyful(PicUtil.zoomBySize(bi2, 64, 64));
        byte[] average1 = HashImg.hash(grey1);
        byte[] average2 = HashImg.hash(grey2);
        System.out.println(HashImg.diff(average1, average2));
    }



}
