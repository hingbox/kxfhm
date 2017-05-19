package com.fh.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by hingbox on 2017/5/19 10:14.
 * Email:hingbox@163.com
 * Version:v1.0
 */
public class ImagesGenerator {
    /**
     * 将图片进行合成
     * @param bigPath 主图图片路径
     * @param smallPath 商品图片路径
     * @param erweimaPath 二维码图片路径
     */
    public static final void overlapImage(String bigPath, String smallPath, String erweimaPath) {
        try {
            BufferedImage big = ImageIO.read(new File(bigPath));
            //URL url = new URL("http://img.blog.csdn.net/20150906104118760");
            BufferedImage small = ImageIO.read(new File(smallPath));
            BufferedImage erweima = ImageIO.read(new File(erweimaPath));
            int width = 2015;
            int height = 1136;
            Image image = big.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage bufferedImage2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = bufferedImage2.createGraphics();
            int x = 707;
            int y = 268;
            int x1 = 684;
            int y1 = 245;
            g.drawImage(image, 0, 0, null);
            g.drawImage(small, x + 320, y - 5, 800, 600, null);
            g.drawImage(erweima, x1 - 575, y1 + 100, 596, 596, null);
            Font font = new Font("宋体", Font.PLAIN, 40);
            g.setFont(font);
            g.setPaint(Color.DARK_GRAY);
            int numWidth = x + 320;
            int numHright = y + 650;
            int num = 0;
            g.drawString("商品名称:", numWidth, numHright);
            num += 50;
            g.setPaint(Color.DARK_GRAY);
            Font font1 = new Font("宋体", Font.BOLD, 40);
            g.setFont(font1);
            g.drawString("售价:", numWidth, numHright + num);
            num += 50;
            Font font2 = new Font("宋体", Font.PLAIN, 40);
            g.setFont(font2);
            g.setPaint(Color.DARK_GRAY);
            g.drawString("原产地:", numWidth, numHright + num);
            num += 50;
            g.drawString("配送方式:", numWidth, numHright + num);
            g.dispose();
            ImageIO.write(bufferedImage2, "jpg", new File("e:\\picturedemo\\hehe.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void main(String[] args) {
        overlapImage("e:\\picturedemo\\a.jpg", "e:\\picturedemo\\b.jpg", "e:\\picturedemo\\c.jpg");
    }

    /**
     * 多张图片合成
     * @param file1
     * @param file2
     * @param file3
     * @param file4
     * @throws Exception
     */
    public void ImageComposition(String file1,String file2,String file3,String file4) throws Exception {
        //创建四个文件对象（注：这里四张图片的宽度都是相同的，因此下文定义BufferedImage宽度就取第一只的宽度就行了）
        File _file1 = new File(file1);
        File _file2 = new File(file2);
        File _file3 = new File(file3);
        File _file4 = new File(file4);

        Image src1 = ImageIO.read(_file1);
        Image src2 = ImageIO.read(_file2);
        Image src3 = ImageIO.read(_file3);
        Image src4 = ImageIO.read(_file4);

        //获取图片的宽度
        int width = src1.getWidth(null);
        //获取各个图像的高度
        int height1 = src1.getHeight(null);
        int height2 = src2.getHeight(null);
        int height3 = src3.getHeight(null);
        int height4 = src4.getHeight(null);

        //构造一个类型为预定义图像类型之一的 BufferedImage。 宽度为第一只的宽度，高度为各个图片高度之和
        BufferedImage tag = new BufferedImage(width, height1 + height2 + height3 + height4, BufferedImage.TYPE_INT_RGB);
        //创建输出流
        FileOutputStream out = new FileOutputStream("E:\\picturedemo\\treasureMap.jpg");
        //绘制合成图像
        Graphics g = tag.createGraphics();
        g.drawImage(src1, 0, 0, width, height1, null);
        g.drawImage(src2, 0, height1, width , height2, null);
        g.drawImage(src3, 0, height1 + height2, width, height3, null);
        g.drawImage(src4, 0, height1 + height2 + height3, width, height4, null);
        // 释放此图形的上下文以及它使用的所有系统资源。
        g.dispose();
        //将绘制的图像生成至输出流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(tag);
        //关闭输出流
        out.close();
        System.out.println("藏宝图出来了");
    }

    /**
     * 根据提供的文字生成jpg图片
     * @param s String  文字
     * @param smallWidth int  每个字的宽度和高度是一样的
     * @param bgcolor Color  背景色
     * @param fontcolor Color  字色
     * @param fontPath String 字体文件
     * @param jpgname String jpg图片名
     * @return
     */
    private String createJpgByFont(String s, int smallWidth,Color bgcolor,Color fontcolor,String fontPath,String jpgname) {
        try {
            BufferedImage bimage = new BufferedImage(s.length()*smallWidth,
                    smallWidth,BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bimage.createGraphics();
            g.setColor(bgcolor); //背景色
            g.fillRect(0, 0, smallWidth, smallWidth); //画一个矩形
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //去除锯齿(当设置的字体过大的时候,会出现锯齿)
            g.setColor(fontcolor); //字的颜色
            File file = new File(fontPath);  //字体文件
            Font font = Font.createFont(Font.TRUETYPE_FONT, file); //根据字体文件所在位置,创建新的字体对象(此语句在jdk1.5下面才支持)
            g.setFont(font.deriveFont((float) smallWidth));   //font.deriveFont(float f)复制当前 Font 对象并应用新设置字体的大小
            g.drawString(s,0, smallWidth); //在指定坐标除添加文字
            g.dispose();
            FileOutputStream out = new FileOutputStream(jpgname); //指定输出文件
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
            param.setQuality(50f, true);
            encoder.encode(bimage, param); //存盘
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println( "createJpgByFont Failed!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将多个小图片合成一张大jpg图  (小的jpg图片按照行列顺序平铺)
     * @param smallJPG ArrayList 一组小的jpg图片
     * @param bigWidth int 大图宽度
     * @param smallHeigh int  单个文字生成的小图的宽度和高度是一致的
     * @return
     */
    private void createBigJPG(ArrayList smallJPG, int bigWidth, int smallHeigh,int smallWidth, Color bgColor , String picName) {
        try {
            if (bigWidth < smallWidth) //如果大图片的高度比小图片的高度还小 直接返回
                return;
            int colCount = bigWidth / smallWidth; //每行放置的字数
            int leftMargin = (int) ((bigWidth - colCount * smallWidth) / 2f); //左边距
            int rowCount = smallJPG.size();  //小图行数
            int setWidth = bigWidth; //每列中间不留空隙，只留左右边距
            int setHeight = smallWidth * rowCount ;
            //按照大图片宽高绘制一个背景图片
            BufferedImage bufImage = new BufferedImage(setWidth, setHeight,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImage.createGraphics();
            g.setColor(bgColor); //背景的颜色
            g.fillRect(0, 0, setWidth, setHeight);
            int y = 0; //纵坐标
            for (int i = 0; i < rowCount; i++) { //遍历每行
                ArrayList col = (ArrayList) (smallJPG.get(i));
                int x = leftMargin; //横坐标  可能会出现左边距
                for (int j = 0; j < col.size(); j++) {
                    String jpgname = (String) (col.get(j));
                    ImageIcon icon = new ImageIcon(jpgname);
                    Image img = icon.getImage();
                    int imgWidth = img.getHeight(null);
                    g.drawImage(img, x, y, null);
                    x += imgWidth;
                }
                y += (smallWidth);
            }
            g.dispose();
            FileOutputStream out = new FileOutputStream(picName);  //指定输出文件
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  //设置文件格式
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufImage); //从图片缓冲中读取
            param.setQuality(50f, true);
            encoder.encode(bufImage, param); //存盘
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println( "createBigJPG Failed!");
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        try {
            ImagesGenerator test = new ImagesGenerator();
            test.ImageComposition("E:\\picturedemo\\a.jpg","E:\\picturedemo\\b.jpg","E:\\picturedemo\\c.jpg","E:\\picturedemo\\d.jpg");
        } catch (Exception e) {
            System.out.print(e);
        }
    }*/
}
