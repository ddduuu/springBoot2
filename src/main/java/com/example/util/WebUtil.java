package com.example.util;

import java.util.ResourceBundle;

/**
 * @author dzh
 * @since 2020-05-15
 */
public class WebUtil {
    /**
     * 获取配置文件中的value
     * @param proName 配置文件名称
     * @param proValue 获取的value
     * */
    public static String getPropertiesValue(String proName,String proValue){
        ResourceBundle bundle = ResourceBundle.getBundle(proName);
        if (bundle.containsKey(proValue)){
            proValue = bundle.getString(proValue);
            return proValue;
        }else{
            return "";
        }
    }


}
