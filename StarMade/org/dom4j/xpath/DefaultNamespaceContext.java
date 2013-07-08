/*  1:   */package org.dom4j.xpath;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */import org.dom4j.Document;
/*  5:   */import org.dom4j.Element;
/*  6:   */import org.dom4j.Namespace;
/*  7:   */import org.dom4j.Node;
/*  8:   */import org.jaxen.NamespaceContext;
/*  9:   */
/* 25:   */public class DefaultNamespaceContext
/* 26:   */  implements NamespaceContext, Serializable
/* 27:   */{
/* 28:   */  private final Element element;
/* 29:   */  
/* 30:   */  public DefaultNamespaceContext(Element element)
/* 31:   */  {
/* 32:32 */    this.element = element;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public static DefaultNamespaceContext create(Object node) {
/* 36:36 */    Element element = null;
/* 37:   */    
/* 38:38 */    if ((node instanceof Element)) {
/* 39:39 */      element = (Element)node;
/* 40:40 */    } else if ((node instanceof Document)) {
/* 41:41 */      Document doc = (Document)node;
/* 42:42 */      element = doc.getRootElement();
/* 43:43 */    } else if ((node instanceof Node)) {
/* 44:44 */      element = ((Node)node).getParent();
/* 45:   */    }
/* 46:   */    
/* 47:47 */    if (element != null) {
/* 48:48 */      return new DefaultNamespaceContext(element);
/* 49:   */    }
/* 50:   */    
/* 51:51 */    return null;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public String translateNamespacePrefixToUri(String prefix) {
/* 55:55 */    if ((prefix != null) && (prefix.length() > 0)) {
/* 56:56 */      Namespace ns = this.element.getNamespaceForPrefix(prefix);
/* 57:   */      
/* 58:58 */      if (ns != null) {
/* 59:59 */        return ns.getURI();
/* 60:   */      }
/* 61:   */    }
/* 62:   */    
/* 63:63 */    return null;
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.xpath.DefaultNamespaceContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */