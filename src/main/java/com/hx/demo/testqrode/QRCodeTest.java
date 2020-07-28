package com.hx.demo.testqrode;

import java.util.Random;

/**
 * @author dzh
 * @since 2020-07-28
 */
public class QRCodeTest {
    public static void main(String[] args) throws Exception {
        //二维码信息
        String text = "http://www.baidu.com";
        //嵌入二维码图片路径
        String imgPath = "D:\\test\\123.jpg";
        //生成二维码存放路径及名称
        String qrCodePath = "D:\\test\\"+new Random().nextInt(9999999) + ".jpg";
        //生成二维码
        QRCodeUtil.encode(text,imgPath,qrCodePath,true);
        //解析二维码
        String decode = QRCodeUtil.decode(qrCodePath);
        System.out.println(decode);

    }
}
