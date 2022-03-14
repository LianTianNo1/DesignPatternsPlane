package ui;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Constructor;

public class Factory {
    public static Object getBean(Plane plane) {
        Object obj = null;

        try {
            // 创建文档工厂对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // 获取到文档
            Document document = documentBuilder.parse(new File("Factory.xml"));
            // 获取到所有的brandName节点
            NodeList nodeList = document.getElementsByTagName("brandName");
            Node node = nodeList.item(0).getFirstChild();
            // 获取配置文件定义的值
            String name = "ui." + node.getNodeValue().trim();

            Class c = Class.forName(name);
            //  获取具体的类
            Constructor constructor = c.getConstructor(Plane.class);
            //  如果构造器私有，设置这个为true
            constructor.setAccessible(true);
            //  创建一个对象
            obj = constructor.newInstance(plane);
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            return obj;
        }
    }
}
