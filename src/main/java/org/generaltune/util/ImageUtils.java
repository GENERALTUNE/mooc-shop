package org.generaltune.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.*;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by zhumin on 2017/7/23.
 */
public class ImageUtils {
    protected static Logger logger = LoggerFactory.getLogger(ImageUtils.class);

    public static Dimension getImageSize(String fileName) {
        if (null == fileName) {
            return null;
        }
        Dimension d = null;
        Image image = null;
        image = Toolkit.getDefaultToolkit().getImage(fileName);
        MediaTracker mediaTracker = new MediaTracker(new Container());
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        } catch (InterruptedException e) {
            LoggerUtils.error(logger, "getImageSize exception,image file:" + fileName, e);
        }
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        d = new Dimension(width, height);
        return d;
    }

    public static Boolean isValidType(String type) {
        if (type == null) {
            return false;
        }

        if (type.length() >= 5 && type.substring(0, 5).equals("image")) {
            return true;
        }
        if (type.equals("gif")) {
            return true;
        }
        if (type.equals("jpg")) {
            return true;
        }
        if (type.equals("jpeg")) {
            return true;
        }
        if (type.equals("png")) {
            return true;
        }
        if (type.equals("bmp")) {
            return true;
        }
        return false;
    }

    /**
     * 获得指定图片文件的类型
     *
     * @param fileName
     * @return
     */
    public static String getImageType(String fileName) {
        if (null == fileName) {
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            LoggerUtils.error(logger, "image file:" + fileName + " not found!", e);
            return null;
        }
        return getImageType(fis);
    }

    /**
     * 获得指定图片文件的类型
     * TODO: 将原来根据类型返回type改为直接返回名称，使用前务必请再调试下
     * @param fis
     * @return
     */
    public static String getImageType(FileInputStream fis) {
        int leng = 0;
        try {
            leng = fis.available();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        BufferedInputStream buff = new BufferedInputStream(fis);
        byte[] mapObj = new byte[leng];
        try {
            buff.read(mapObj, 0, leng);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                buff.close();
            } catch (IOException ie) {
                // ignore
            }
        }
        String type = "";
        ByteArrayInputStream bais = null;
        MemoryCacheImageInputStream mcis = null;
        try {
            bais = new ByteArrayInputStream(mapObj);
            mcis = new MemoryCacheImageInputStream(bais);
            Iterator<?> itr = ImageIO.getImageReaders(mcis);
            if (itr.hasNext()) {
                ImageReader reader = (ImageReader) itr.next();
                type = reader.getFormatName();
            }
        } catch (IOException ex) {
            LoggerUtils.error(logger, "getImageType:", ex);
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException ioe) {
                    //ignore
                }
            }
            if (mcis != null) {
                try {
                    mcis.close();
                } catch (IOException ioe) {
                    //ignore
                }
            }
        }
        return type;
    }


    /**
     * according last dot get extension by file name
     *
     * @param fileName
     * @return extension or null;
     */
    public static String getExt(String fileName) {
        try {
            if (fileName != null && fileName.length() > 0) {
                int lastDotAt = fileName.lastIndexOf(".");
                if (lastDotAt != -1) {
                    fileName = fileName.substring(lastDotAt + 1, fileName.length());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fileName;
    }

    /**
     * 得到MD5加密后的文件名
     *
     * @param name
     * @return name
     * @author lichunping
     * @since 1.0
     */
    public static String getMD5FileName(String name) {
        if (name != null) {
            String ext = getExt(name);
            Date d = new Date();
            String tmp = name + d.getTime() + Math.random() * 1000;
            return StringUtils.md5(tmp) + "." + ext;
        }
        return name;
    }
}
