package com.originalgames.mitsurin.xml;

import com.originalgames.mitsurin.log.ErrorWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Paths;

public abstract class XMLController {
    // file path
    private String filepath;
    // DocumentBuilderFactory
    private static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    // DocumentBuilder
    private DocumentBuilder documentBuilder;
    // Document
    private Document document;
    // root node
    private Element root;
    // NodeList immediately below the root
    private NodeList nodeList;

    // error
    private ErrorWriter errorWriter;

    /**
     * scheme and calling of XML file
     * <xml version="1.0" encoding="UTF-8">
     *     <root>                           top node (depth : 0) : root
     *         <node id="0">                depth 1 : node, node id : from "0"
     *             <element></element>      depth 2 : element
     *             ...
     *         </node>
     *         <node id="1">
     *             <element></element>
     *             ...
     *         </node>
     *         ...
     *     </root>
     */

    XMLController(String filepath) {
        // set file path
        this.filepath = filepath;
        // error writer
        errorWriter = new ErrorWriter(this);

        try {
            // create new DocumentBuilder
            documentBuilder = XMLController.documentBuilderFactory.newDocumentBuilder();
            // create new Document from filepath(xml file)
            document = documentBuilder.parse(Paths.get(filepath).toFile());
        }catch (ParserConfigurationException e) {
            errorWriter.appendErrorLog(String.valueOf(e));
            errorWriter.appendErrorLog("force quit");
            System.exit(1);
        }catch (SAXException e) {
            errorWriter.appendErrorLog(String.valueOf(e));
            errorWriter.appendErrorLog("force quit");
            System.exit(1);
        }catch (IOException e) {
            errorWriter.appendErrorLog(String.valueOf(e));
            errorWriter.appendErrorLog("force quit");
            System.exit(1);
        }

        // get root node from Document
        root = document.getDocumentElement();
        // get NodeList from root node
        nodeList = root.getChildNodes();
    }

    // reconstruct this instance
    public XMLController reConstruct() {
        try {
            // update Document
            document = documentBuilder.parse(Paths.get(filepath).toFile());
        }catch (SAXException e) {
            errorWriter.appendErrorLog(String.valueOf(e));
            return this;
        }catch (IOException e) {
            errorWriter.appendErrorLog(String.valueOf(e));
            return this;
        }

        // update root node from Document
        root = document.getDocumentElement();
        // update NodeList from root node
        nodeList = root.getChildNodes();

        return this;
    }

    // getter
    public String getFilepath() {
        return filepath;
    }

    protected DocumentBuilder getDocumentBuilder() {
        return documentBuilder;
    }

    protected Document getDocument() {
        return document;
    }

    protected Element getRoot() {
        return root;
    }

    protected NodeList getNodeList() {
        return nodeList;
    }

    // setter
    protected void setDocument(Document document) {
        this.document = document;
    }

    protected void setRoot(Element root) {
        this.root = root;
    }

    protected void setNodeList(NodeList nodeList) {
        this.nodeList = nodeList;
    }
}
