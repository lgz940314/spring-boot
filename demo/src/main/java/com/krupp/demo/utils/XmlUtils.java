package com.krupp.demo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.dom4j.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/9/27
 */
public class XmlUtils {

    /**
     * 将json字符串转换成xml
     * @param json json字符串
     * @param parentElement xml根节点
     * @throws Exception
     * @return
     */
    public static Element jsonToXml(String json, Element parentElement) throws Exception{
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        return toXml(jsonObject, parentElement, null);
    }

    /**将json字符串转换成xml
     * @param jsonElement 待解析json对象元素
     * @param parentElement 上一层xml的dom对象
     * @param name 属性名字（可以是标签名字，也可以是标签属性的名字）
     * @return
     */
    public static Element toXml(JsonElement jsonElement, Element parentElement, String name){
        if (jsonElement instanceof JsonArray) {
            JsonArray sonJsonArray = (JsonArray)jsonElement;
            for (int i = 0; i < sonJsonArray.size(); i++) {
                JsonElement arrayElement = sonJsonArray.get(i);
                toXml(arrayElement, parentElement, name);
            }
        }else if(jsonElement instanceof JsonObject){
            JsonObject sonJsonObject = (JsonObject)jsonElement;
            Element currentElement = null;
            if (name!=null) {
                currentElement = DocumentHelper.createElement(name);
            }
            Set<Map.Entry<String, JsonElement>> set = sonJsonObject.entrySet();
            for(Map.Entry<String, JsonElement> s:set){
                toXml(s.getValue(), currentElement!=null?currentElement:parentElement, s.getKey());
            }
            if (currentElement!=null) {
                parentElement.add(currentElement);
            }
        }else {
            addAttribute(parentElement, name, jsonElement.getAsString());
        }
        return parentElement;
    }

    public static void addAttribute(Element element, String name, String value){
        element.addAttribute(name, value);
    }

    /**
     * String 转 org.dom4j.Document
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Document strToDocument(String xml){
        try {
            //加上xml标签是为了获取最外层的标签，如果不需要可以去掉
            return DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            return null;
        }
    }

    /**
     * org.dom4j.Document 转  com.alibaba.fastjson.JSONObject
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static JSONObject documentToJSONObject(String xml){
        return elementToJSONObject(strToDocument(xml).getRootElement());
    }

    /**
     * org.dom4j.Element 转  com.alibaba.fastjson.JSONObject
     * @param node
     * @return
     */
    public static JSONObject elementToJSONObject(Element node) {
        JSONObject result = new JSONObject();
        // 当前节点的名称、文本内容和属性
        List<Attribute> listAttr = node.attributes();// 当前节点的所有属性的list
        for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
            result.put(attr.getName(), attr.getValue());
        }
        // 递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();// 所有一级子节点的list
        if (!listElement.isEmpty()) {
            for (Element e : listElement) {// 遍历所有一级子节点
                if (e.attributes().isEmpty() && e.elements().isEmpty()) // 判断一级节点是否有属性和子节点
                {
                    result.put(e.getName(), e.getTextTrim());// 沒有则将当前节点作为上级节点的属性对待
                } else {
                    if (!result.containsKey(e.getName())) // 判断父节点是否存在该一级节点名称的属性
                    {
                        result.put(e.getName(), new JSONArray());// 没有则创建
                    }
                    ((JSONArray) result.get(e.getName())).add(elementToJSONObject(e));// 将该一级节点放入该节点名称的属性对应的值中
                }
            }
        }
        return result;
    }

}
