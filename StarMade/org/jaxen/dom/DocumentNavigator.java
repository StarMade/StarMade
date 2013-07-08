package org.jaxen.dom;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jaxen.DefaultNavigator;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenConstants;
import org.jaxen.Navigator;
import org.jaxen.XPath;
import org.jaxen.saxpath.SAXPathException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.SAXException;

public class DocumentNavigator
  extends DefaultNavigator
{
  private static final long serialVersionUID = 8460943068889528115L;
  private static final DocumentNavigator SINGLETON = new DocumentNavigator();
  
  public static Navigator getInstance()
  {
    return SINGLETON;
  }
  
  public Iterator getChildAxisIterator(Object contextNode)
  {
    Node node = (Node)contextNode;
    if ((node.getNodeType() == 1) || (node.getNodeType() == 9)) {
      new NodeIterator((Node)contextNode, // INTERNAL ERROR //

/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.dom.DocumentNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */