package cn.inkroom.software.version.common.reader;

import cn.inkroom.software.version.common.enums.MessageType;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DomXmlReader implements MessageReader {

    private Document document;

    @Override
    public String getOther() {
        return getNodeValue("other");
    }

    @Override
    public boolean read(byte[] bytes) {
        try {
            String message = new String(bytes);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            document = builder.parse(message);
            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MessageType getType() {
        String type = document.getElementsByTagName("type").item(0).getNodeValue();
        return MessageType.valueOf(type);
    }

    @Override
    public String getApp() {
        return getNodeValue("app");
    }

    @Override
    public String getOperator() {
        return getNodeValue("operator");
    }

    @Override
    public String getCommand() {
        return getNodeValue("command");
    }

    private String getNodeValue(String tag) {
        NodeList nodeList = document.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getNodeValue();
        }
        return null;
    }

    @Override
    public int getPort() {
        String value = getNodeValue("port");
        return value == null ? -1 : Integer.parseInt(value);
    }

    @Override
    public int getVersion() {
        String value = getNodeValue("version");
        return value == null ? -1 : Integer.parseInt(value);
    }
}
