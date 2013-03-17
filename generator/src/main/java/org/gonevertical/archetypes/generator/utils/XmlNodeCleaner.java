package org.gonevertical.archetypes.generator.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlNodeCleaner {

  public XmlNodeCleaner() {
  }

  public void removeParentNodeWithExpression(String filePath, String xpathExp) {
    try {
      removeParentNodeWithExpressionOrThrow(filePath, xpathExp);
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

  public void removeParentNodeWithExpressionOrThrow(String filePath, String xpathExp) throws SAXException, IOException,
      ParserConfigurationException, XPathExpressionException, TransformerException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    Document document = dbf.newDocumentBuilder().parse(new File(filePath));

    XPathFactory xpf = XPathFactory.newInstance();
    XPath xpath = xpf.newXPath();
    XPathExpression expression = xpath.compile(xpathExp);

    NodeList foundNodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
    if (foundNodes == null) {
      System.out.println("XPath didn't find " + xpathExp);
      return;
    }

    for (int i = 0; i < foundNodes.getLength(); i++) {
      Node foundNode = foundNodes.item(i);
      foundNode.getParentNode().getParentNode().removeChild(foundNode.getParentNode());
    }

    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer t = tf.newTransformer();

    DOMSource source = new DOMSource(document);
    StreamResult result = new StreamResult(new File(filePath));
    t.transform(source, result);
    t.transform(source, new StreamResult(System.out));
  }
  
  public void removeNode(String filePath, String xpathExp) {
    try {
      removeNodeOrThrow(filePath, xpathExp);
    } catch (XPathExpressionException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

  public void removeNodeOrThrow(String filePath, String xpathExp) throws SAXException, IOException,
      ParserConfigurationException, XPathExpressionException, TransformerException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    Document document = dbf.newDocumentBuilder().parse(new File(filePath));

    XPathFactory xpf = XPathFactory.newInstance();
    XPath xpath = xpf.newXPath();
    XPathExpression expression = xpath.compile(xpathExp);

    NodeList foundNodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
    if (foundNodes == null) {
      System.out.println("XPath didn't find " + xpathExp);
      return;
    }

    for (int i = 0; i < foundNodes.getLength(); i++) {
      Node foundNode = foundNodes.item(i);
      foundNode.getParentNode().removeChild(foundNode);
    }

    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer t = tf.newTransformer();

    DOMSource source = new DOMSource(document);
    StreamResult result = new StreamResult(new File(filePath));
    t.transform(source, result);
    
    // to console
    //t.transform(source, new StreamResult(System.out));
  }

}
