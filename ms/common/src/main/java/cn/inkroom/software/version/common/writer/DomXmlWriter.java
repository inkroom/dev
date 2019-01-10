package cn.inkroom.software.version.common.writer;

import cn.inkroom.software.version.common.app.Application;
import cn.inkroom.software.version.common.enums.MessageType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class DomXmlWriter implements MessageWriter {

    private Document document;
    private Element root;

    public DomXmlWriter() {
        init();
    }

    private void init() {
        document = null;
        root = null;
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            document = builder.newDocument();
            root = document.createElement("version");
//            document.adoptNode(root);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MessageWriter writeType(MessageType type) {
        appendRootElement("type", type.toString());
        return this;
    }

    @Override
    public MessageWriter writeApp(String app) {
        appendRootElement("app", app);
        return this;
    }

    @Override
    public MessageWriter writeOperator(String operator) {
        appendRootElement("operator", operator);
        return this;
    }

    @Override
    public MessageWriter writeCommand(String command) {
        appendRootElement("command", command);
        return this;
    }

    @Override
    public MessageWriter writePort(int port) {
        appendRootElement("port", port + "");
        return this;
    }

    @Override
    public MessageWriter writeVersion(int version) {
        appendRootElement("version", version + "");
        return this;
    }

    @Override
    public MessageWriter writeContexts(List<Application> applications) {

        NodeList nodeList = document.getElementsByTagName("contexts");
        Node node = null;
        if (nodeList == null || nodeList.getLength() == 0) {
            Element element = document.createElement("contexts");
            node = element;
            root.appendChild(element);
        } else {
            node = nodeList.item(0);
        }

        for (int i = 0; i < applications.size(); i++) {
            Element element = document.createElement("context");
            element.setAttribute("name", applications.get(i).getContext());
            element.setAttribute("app", applications.get(i).getName());
            element.setAttribute("status", applications.get(i).getStatus());

            node.appendChild(element);
        }

        return this;
    }

    @Override
    public MessageWriter clear() {
        init();
        return this;
    }

    @Override
    public String getMessage() {
        return toString();
    }

    private void appendRootElement(String tagName, String value) {
        NodeList nodeList = document.getElementsByTagName(tagName);
        if (nodeList == null || nodeList.getLength() == 0) {
            Element element = document.createElement(tagName);
            element.setNodeValue(value);
            root.appendChild(element);
        } else if (nodeList.getLength() == 1) {
            Node node = nodeList.item(0);
            node.setNodeValue(value);
        }
    }


    @Override
    public String toString() {

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = null;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(outStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outStream.toString();
    }
}
