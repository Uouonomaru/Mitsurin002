package com.originalgames.mitsurin.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Paths;
import java.util.ArrayList;

public class XMLController {
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document document;
    private Element rootElement;
    private NodeList nodeList;

    // コンストラクタ
    public XMLController(String xmlFilePath) {
        try {
            dbFactory = DocumentBuilderFactory.newInstance();              // DocumentBuilderFactoryインスタンスを生成
            dBuilder = dbFactory.newDocumentBuilder();                     // DocumentBuilderFactoryよりDocumentBuilderインスタンスを生成
            document = dBuilder.parse(Paths.get(xmlFilePath).toFile());    // 指定ファイルパスよりファイルのDocument作成
            rootElement = document.getDocumentElement();                   // Documentのルート内要素を取得
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 子要素のうち，指定された要素をArrayListとして取得
    public ArrayList<Element> getNodeList(String elementName) {
        nodeList = rootElement.getChildNodes();                         // ルート内の子要素をリストで取得
        ArrayList<Element> nodes = new ArrayList<>();                   // ArrayListを初期化
        for(int i = 0; i < nodeList.getLength(); i++) {
            // 各子要素のノードタイプがELEMENT_NODEである場合(ここまだ若干理解不足です...)
            if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element node = (Element)nodeList.item(i);               // Element型で指定インデックスの要素を取得
                // 指定要素が見つかった場合
                if(node.getNodeName().equals(elementName)) {
                    nodes.add(node);
                }
            }
        }

        return nodes;
    }

    // 指定要素内の指定した内部要素を取得する
    public ArrayList<String> getAttribute(String elementName, String innerElementName) {
        ArrayList<Element> nodes = this.getNodeList(elementName);                   // 指定要素のノードリストを取得
        ArrayList<String> attributes = new ArrayList<>();
        for(int i = 0; i < nodes.size(); i++) {
            // 要素の内部要素リストを取得
            NodeList innerNodeList = nodes.get(i).getChildNodes();
            for(int j = 0; j < innerNodeList.getLength(); j++) {
                if(innerNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element innerNode = (Element) innerNodeList.item(j);
                    // 探索している要素があればその値を取得する
                    if (innerNode.getNodeName().equals(innerElementName)) {
                        attributes.add(innerNode.getTextContent());
                    }
                }
            }
        }

        return attributes;
    }
}