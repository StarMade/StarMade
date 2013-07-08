/*  1:   */package org.dom4j.dom;
/*  2:   */
/*  3:   */import org.w3c.dom.Attr;
/*  4:   */import org.w3c.dom.DOMException;
/*  5:   */import org.w3c.dom.NamedNodeMap;
/*  6:   */import org.w3c.dom.Node;
/*  7:   */
/* 20:   */public class DOMAttributeNodeMap
/* 21:   */  implements NamedNodeMap
/* 22:   */{
/* 23:   */  private DOMElement element;
/* 24:   */  
/* 25:   */  public DOMAttributeNodeMap(DOMElement element)
/* 26:   */  {
/* 27:27 */    this.element = element;
/* 28:   */  }
/* 29:   */  
/* 31:   */  public void foo()
/* 32:   */    throws DOMException
/* 33:   */  {}
/* 34:   */  
/* 35:   */  public Node getNamedItem(String name)
/* 36:   */  {
/* 37:37 */    return this.element.getAttributeNode(name);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public Node setNamedItem(Node arg) throws DOMException {
/* 41:41 */    if ((arg instanceof Attr)) {
/* 42:42 */      return this.element.setAttributeNode((Attr)arg);
/* 43:   */    }
/* 44:44 */    throw new DOMException((short)9, "Node is not an Attr: " + arg);
/* 45:   */  }
/* 46:   */  
/* 47:   */  public Node removeNamedItem(String name)
/* 48:   */    throws DOMException
/* 49:   */  {
/* 50:50 */    Attr attr = this.element.getAttributeNode(name);
/* 51:   */    
/* 52:52 */    if (attr == null) {
/* 53:53 */      throw new DOMException((short)8, "No attribute named " + name);
/* 54:   */    }
/* 55:   */    
/* 57:57 */    return this.element.removeAttributeNode(attr);
/* 58:   */  }
/* 59:   */  
/* 60:   */  public Node item(int index) {
/* 61:61 */    return DOMNodeHelper.asDOMAttr(this.element.attribute(index));
/* 62:   */  }
/* 63:   */  
/* 64:   */  public int getLength() {
/* 65:65 */    return this.element.attributeCount();
/* 66:   */  }
/* 67:   */  
/* 68:   */  public Node getNamedItemNS(String namespaceURI, String localName) {
/* 69:69 */    return this.element.getAttributeNodeNS(namespaceURI, localName);
/* 70:   */  }
/* 71:   */  
/* 72:   */  public Node setNamedItemNS(Node arg) throws DOMException {
/* 73:73 */    if ((arg instanceof Attr)) {
/* 74:74 */      return this.element.setAttributeNodeNS((Attr)arg);
/* 75:   */    }
/* 76:76 */    throw new DOMException((short)9, "Node is not an Attr: " + arg);
/* 77:   */  }
/* 78:   */  
/* 80:   */  public Node removeNamedItemNS(String namespaceURI, String localName)
/* 81:   */    throws DOMException
/* 82:   */  {
/* 83:83 */    Attr attr = this.element.getAttributeNodeNS(namespaceURI, localName);
/* 84:   */    
/* 86:86 */    if (attr != null) {
/* 87:87 */      return this.element.removeAttributeNode(attr);
/* 88:   */    }
/* 89:   */    
/* 90:90 */    return attr;
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMAttributeNodeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */