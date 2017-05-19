package com.fh.util;

import com.elab.core.utils.ObjectUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by hingbox on 2017/5/19 10:14.
 * Email:hingbox@163.com
 * Version:v1.0
 */
public class HtmlUtils {
    /**
     * list数据组装html元素
     * @param params
     * @param data
     * @return
     */
    public static StringBuffer listToHtml(LinkedHashMap<String, Object> params, List<Map<String, Object>> data) {
        StringBuffer buffer = new StringBuffer();
        if ((params != null && params.size() > 0) && (data !=null && data.size()>0)) {
            buffer.append("<div><table width='100%' border='1' cellspacing='0'>");
            buffer.append("<tr>");
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String value = entry.getValue().toString();
                buffer.append("<td align='center'>" + value + "</td>");
            }
            buffer.append("</tr>");
            for(Map map:data){
                iterator = params.entrySet().iterator();
                buffer.append("<tr>");
                while(iterator.hasNext()){
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String key = entry.getKey().toString();
                    String attr = ObjectUtils.isNotEmpty(map.get(key))?map.get(key).toString():"";
                    buffer.append("<td align='center' style='text-align: center;'>" + attr + "</td>");
                }
                buffer.append("</tr>");
            }
            buffer.append("</table></div>");
        }
        return  buffer;
    }

    /**
     * list数据组装html元素
     * @param params
     * @param data
     * @return
     */
    public static StringBuffer listObjToHtml(LinkedHashMap<String, Object> params, List data) {

        StringBuffer buffer = new StringBuffer();
        if ((params != null && params.size() > 0) && (data !=null && data.size()>0)) {
            buffer.append("<div><table width='100%' border='1' cellspacing='0' style='text-align: center;'>");
            buffer.append("<tr>");
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String value = entry.getValue().toString();
                buffer.append("<td>" + value + "</td>");
            }
            buffer.append("</tr>");
            for(Object tempObj : data){
                Map map = null;
                try {
                    map = convertBean(tempObj);
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                iterator = params.entrySet().iterator();
                buffer.append("<tr>");
                while(iterator.hasNext()){
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String key = entry.getKey().toString();
                    String attr = ObjectUtils.isNotEmpty(map.get(key))?map.get(key).toString():"";
                    buffer.append("<td>" + attr + "</td>");
                }
                buffer.append("</tr>");
            }
            buffer.append("</table></div>");
        }
        return  buffer;
    }

    /**
     * list数据组装html元素电话加url
     * @param params
     * @param data
     * @return
     */
    public static StringBuffer listToHtmlWithUrl(LinkedHashMap<String, Object> params, List<Map<String, Object>> data) {
        StringBuffer buffer = new StringBuffer();
        if ((params != null && params.size() > 0) && (data !=null && data.size()>0)) {
            buffer.append("<div><table width='100%' border='1' cellspacing='0'>");
            buffer.append("<tr>");
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String value = entry.getValue().toString();
                buffer.append("<td>" + value + "</td>");
            }
            buffer.append("</tr>");
            for(Map map:data){
                iterator = params.entrySet().iterator();
                buffer.append("<tr>");
                while(iterator.hasNext()){
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String key = entry.getKey().toString();
                    String attr = ObjectUtils.isNotEmpty(map.get(key))?map.get(key).toString():"";
                    if (key.equals("token")) {continue;}
                    if (key.equals("phone")) {
                        buffer.append("<td><a href=http://m.elab-plus.com/sky-forest/index.html#/kpb/user-behavior-list/" + map.get("token") + ">" + attr + "</a></td>");
                    }
                    else {
                        buffer.append("<td>" + attr + "</td>");
                    }
                }
                buffer.append("</tr>");
            }
            buffer.append("</table></div>");
        }
        return  buffer;
    }


    /**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
}
