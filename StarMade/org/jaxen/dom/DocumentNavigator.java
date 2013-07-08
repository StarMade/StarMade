/*    1:     */package org.jaxen.dom;
/*    2:     */
/*    3:     */import java.io.IOException;
/*    4:     */import java.util.Collection;
/*    5:     */import java.util.HashMap;
/*    6:     */import java.util.Iterator;
/*    7:     */import java.util.NoSuchElementException;
/*    8:     */import javax.xml.parsers.DocumentBuilder;
/*    9:     */import javax.xml.parsers.DocumentBuilderFactory;
/*   10:     */import javax.xml.parsers.ParserConfigurationException;
/*   11:     */import org.jaxen.DefaultNavigator;
/*   12:     */import org.jaxen.FunctionCallException;
/*   13:     */import org.jaxen.JaxenConstants;
/*   14:     */import org.jaxen.Navigator;
/*   15:     */import org.jaxen.XPath;
/*   16:     */import org.jaxen.saxpath.SAXPathException;
/*   17:     */import org.w3c.dom.Attr;
/*   18:     */import org.w3c.dom.Document;
/*   19:     */import org.w3c.dom.NamedNodeMap;
/*   20:     */import org.w3c.dom.Node;
/*   21:     */import org.w3c.dom.NodeList;
/*   22:     */import org.w3c.dom.ProcessingInstruction;
/*   23:     */import org.xml.sax.SAXException;
/*   24:     */
/*  106:     */public class DocumentNavigator
/*  107:     */  extends DefaultNavigator
/*  108:     */{
/*  109:     */  private static final long serialVersionUID = 8460943068889528115L;
/*  110: 110 */  private static final DocumentNavigator SINGLETON = new DocumentNavigator();
/*  111:     */  
/*  132:     */  public static Navigator getInstance()
/*  133:     */  {
/*  134: 134 */    return SINGLETON;
/*  135:     */  }
/*  136:     */  
/*  150:     */  public Iterator getChildAxisIterator(Object contextNode)
/*  151:     */  {
/*  152: 152 */    Node node = (Node)contextNode;
/*  153:     */    
/*  154: 154 */    if ((node.getNodeType() == 1) || (node.getNodeType() == 9))
/*  155:     */    {
/*  156: 156 */      new NodeIterator((Node)contextNode, // INTERNAL ERROR //

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.dom.DocumentNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */