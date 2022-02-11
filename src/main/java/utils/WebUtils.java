package utils;

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;

import java.util.Map;

/**
 * @author liaoke
 * @create 2021-10-28-15:03
 */
public class WebUtils {


    public  static <T> T copyParamBean(Map value, T bean){
        try {
            //获取的bean的set方法进行赋值
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    public static int parseInt(String strInt,int defaultValue){

        try {
            int i = Integer.parseInt(strInt);
            return i;
        }catch (NumberFormatException e){
      //      e.printStackTrace();
        }

        return defaultValue;
    }

}
