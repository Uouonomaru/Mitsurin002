package com.originalgames.mitsurin.xml;

import com.originalgames.mitsurin.log.ErrorWriter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class XMLReader extends XMLController {
    // error
    private ErrorWriter errorWriter;

    public XMLReader(String filepath) {
        super(filepath);
        // error writer
        errorWriter = new ErrorWriter(this);
    }

    // get node(s)
    // 全ノード取得
    public ArrayList<Element> getNode() {
        NodeList nodeList = super.getNodeList();
        ArrayList<Element> nodes = new ArrayList<>();

        for(int i = 0; i < nodeList.getLength(); i++) {
            if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element node = (Element) (nodeList.item(i));
                nodes.add(node);
            }
        }

        return nodes;
    }

    // ノード名が一致するノード
    public ArrayList<Element> getNode(String nodeName) {
        // get NodeList
        NodeList nodeList = super.getNodeList();
        // new ArrayList (data type : Element)
        ArrayList<Element> nodes = new ArrayList<>();

        for(int i = 0; i < nodeList.getLength(); i++) {
            // if node type is ELEMENT_NODE
            if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                // get node(data type : Element)
                Element node = (Element) (nodeList.item(i));
                if(node.getNodeName().equals(nodeName)) {
                    nodes.add(node);
                }
            }
        }

        return nodes;
    }

    // idが一致するノード
    public Element getNode(int nodeId) {
        // convert from int to String
        String id = String.valueOf(nodeId);
        // get NodeList
        NodeList nodeList = super.getNodeList();

        for(int i = 0; i < nodeList.getLength(); i++) {
            // if node type is ELEMENT_NODE
            if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                // get node (data type : Element)
                Element node = (Element) (nodeList.item(i));
                if(node.getAttribute("id").equals(id)) {
                    return node;
                }
            }
        }
        return null;
    }

    // get node name
    // 全ノード名の取得
    public ArrayList<String> getNodeName() {
        // get NodeList
        ArrayList<Element> nodes = this.getNode();
        // new ArrayList (data type : String)
        ArrayList<String> names = new ArrayList<>();

        for(int i = 0; i < nodes.size(); i++) {
            Element node = nodes.get(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                String name = node.getNodeName();
                names.add(name);
            }
        }
        return names;
    }

    // id指定でノード名を取得
    public String getNodeName(int nodeId) {
        // get node by nodeId
        Element node = this.getNode(nodeId);

        String nodeName;
        try {
            nodeName = node.getNodeName();
        }catch (NullPointerException e) {
            errorWriter.appendErrorLog(String.valueOf(e));
            return "";
        }

        return nodeName;
    }

    // ノード指定でノード名取得
    public String getNodeName(Element node) {
        String nodeName;
        try {
            nodeName = node.getNodeName();
        }catch (NullPointerException e) {
            errorWriter.appendErrorLog(String.valueOf(e));
            return "";
        }

        return nodeName;
    }

    // get node id
    // 全ノードID取得
    public ArrayList<Integer> getNodeId() {
        // get all node
        ArrayList<Element> nodes = this.getNode();
        ArrayList<Integer> nodeIds = new ArrayList<>();

        for(int i = 0; i < nodes.size(); i++) {
            Element node = nodes.get(i);
            int nodeId = Integer.parseInt(node.getAttribute("id"));
            nodeIds.add(nodeId);
        }

        return nodeIds;
    }

    // 指定ノード名のノードのノードIDを取得
    public ArrayList<Integer> getNodeId(String nodeName) {
        // get nodes by nodeName
        ArrayList<Element> nodes = this.getNode(nodeName);
        ArrayList<Integer> nodeIds = new ArrayList<>();

        for(int i = 0; i < nodes.size(); i++) {
            Element node = nodes.get(i);
            String name = this.getNodeName(node);
            if(name.equals(nodeName)) {
                int nodeId = Integer.parseInt(node.getAttribute("id"));
                nodeIds.add(nodeId);
            }
        }

        return nodeIds;
    }

    // 指定ノードのノードID取得
    public int getNodeId(Element node) {
        String nodeId = node.getAttribute("id");

        return Integer.parseInt(nodeId);
    }

    // 全ノード数取得
    public int getNodeAmount() {
        // get amount of all nodes
        ArrayList<Element> nodes = this.getNode();

        return nodes.size();
    }

    // 指定ノード内エレメント取得
    public ArrayList<Element> getElement(Element node) {
        // get elementList(data type : NodeList) of parent node(argument[node])
        NodeList elementList = node.getChildNodes();
        // new ArrayList(data type : Element)
        ArrayList<Element> elements = new ArrayList<>();

        for(int i = 0; i < elementList.getLength(); i++) {
            if(elementList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) (elementList.item(i));
                elements.add(element);
            }
        }

        return elements;
    }

    // 指定ノード内の指定エレメント取得
    public Element getElement(Element node, String elementName) {
        // get elements by node
        ArrayList<Element> elements = this.getElement(node);

        for(int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            if(element.getNodeName().equals(elementName)) {
                return element;
            }
        }

        return null;
    }

    // 指定IDノード内エレメント取得
    public ArrayList<Element> getElement(int nodeId){
        // get node by nodeId
        Element node = this.getNode(nodeId);

        return this.getElement(node);
    }

    // 指定IDノード内の指定エレメント取得
    public Element getElement(int nodeId, String elementName) {
        // get elements by nodeId
        ArrayList<Element> elements = this.getElement(nodeId);

        for(int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            if(element.getNodeName().equals(elementName)) {
                return element;
            }
        }

        return null;
    }

    // 指定ノード内エレメント名取得
    public ArrayList<String> getElementName(Element node) {
        // get child elements of parent node
        ArrayList<Element> elements = this.getElement(node);
        // new ArrayList(data type : Element)
        ArrayList<String> names = new ArrayList<>();

        for(int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            if(element.getNodeType() == Node.ELEMENT_NODE) {
                names.add(element.getNodeName());
            }
        }

        return names;
    }

    // 指定IDノード内のエレメント名取得
    public ArrayList<String> getElementName(int nodeId) {
        Element node = this.getNode(nodeId);

        return this.getElementName(node);
    }

    // 指定エレメント内のコンテンツを取得
    public Object getContent(Element element) {
        Object content = element.getTextContent();

        return content;
    }
}
