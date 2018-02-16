package com.test.aimprosoft.inject;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;


public class InjectParser {

    private static Document document;

    public void init(String xmlFile) throws ServletException{
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(xmlFile);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setValidating(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(in);
        } catch (Exception e){
            throw new ServletException("Can not init xml: ");
        }

    }
    public Object newInstance(String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        NodeList nodeList = document.getElementsByTagName("bean");

        for(int i = 0; i < nodeList.getLength(); i++) {

            NamedNodeMap namedNodeMap = nodeList.item(i).getAttributes();
            Node nodeName = namedNodeMap.getNamedItem("name");
            String name = nodeName.getTextContent();

            if (name.equals(beanName)) {
                Node nodeClass = namedNodeMap.getNamedItem("class");
                String clazz = nodeClass.getTextContent();

                return Class.forName(clazz).newInstance();
            }
        }
        return null;
    }

}
