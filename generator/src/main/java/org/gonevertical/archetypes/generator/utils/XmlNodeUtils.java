package org.gonevertical.archetypes.generator.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XmlNodeUtils {

  public XmlNodeUtils() {
  }

  public String findNodeValue(String filePath, String xpathExp) {
    String value = null;
    try {
      value = findNodeValueOrThrow(filePath, xpathExp);
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
    return value;
  }

  public String findNodeValueOrThrow(String filePath, String xpathExp)
      throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    Document document = dbf.newDocumentBuilder().parse(new File(filePath));

    XPathFactory xpf = XPathFactory.newInstance();
    XPath xpath = xpf.newXPath();
    XPathExpression expression = xpath.compile(xpathExp);

    Node foundNode = (Node) expression.evaluate(document, XPathConstants.NODE);
    if (foundNode == null) {
      System.out.println("XPath didn't find " + xpathExp);
      return null;
    }

    String value = foundNode.getTextContent();
    System.out.println("foundNode=" + foundNode.getTextContent());

    return value;
  }

}
