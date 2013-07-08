/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import org.dom4j.Element;
/*   4:    */import org.dom4j.tree.DefaultNamespace;
/*   5:    */import org.w3c.dom.DOMException;
/*   6:    */import org.w3c.dom.Document;
/*   7:    */import org.w3c.dom.NamedNodeMap;
/*   8:    */import org.w3c.dom.Node;
/*   9:    */import org.w3c.dom.NodeList;
/*  10:    */
/*  23:    */public class DOMNamespace
/*  24:    */  extends DefaultNamespace
/*  25:    */  implements Node
/*  26:    */{
/*  27:    */  public DOMNamespace(String prefix, String uri)
/*  28:    */  {
/*  29: 29 */    super(prefix, uri);
/*  30:    */  }
/*  31:    */  
/*  32:    */  public DOMNamespace(Element parent, String prefix, String uri) {
/*  33: 33 */    super(parent, prefix, uri);
/*  34:    */  }
/*  35:    */  
/*  37:    */  public boolean supports(String feature, String version)
/*  38:    */  {
/*  39: 39 */    return DOMNodeHelper.supports(this, feature, version);
/*  40:    */  }
/*  41:    */  
/*  42:    */  public String getNamespaceURI() {
/*  43: 43 */    return DOMNodeHelper.getNamespaceURI(this);
/*  44:    */  }
/*  45:    */  
/*  47:    */  public void setPrefix(String prefix)
/*  48:    */    throws DOMException
/*  49:    */  {
/*  50: 50 */    DOMNodeHelper.setPrefix(this, prefix);
/*  51:    */  }
/*  52:    */  
/*  53:    */  public String getLocalName() {
/*  54: 54 */    return DOMNodeHelper.getLocalName(this);
/*  55:    */  }
/*  56:    */  
/*  57:    */  public String getNodeName() {
/*  58: 58 */    return getName();
/*  59:    */  }
/*  60:    */  
/*  62:    */  public String getNodeValue()
/*  63:    */    throws DOMException
/*  64:    */  {
/*  65: 65 */    return DOMNodeHelper.getNodeValue(this);
/*  66:    */  }
/*  67:    */  
/*  68:    */  public void setNodeValue(String nodeValue) throws DOMException {
/*  69: 69 */    DOMNodeHelper.setNodeValue(this, nodeValue);
/*  70:    */  }
/*  71:    */  
/*  72:    */  public Node getParentNode() {
/*  73: 73 */    return DOMNodeHelper.getParentNode(this);
/*  74:    */  }
/*  75:    */  
/*  76:    */  public NodeList getChildNodes() {
/*  77: 77 */    return DOMNodeHelper.getChildNodes(this);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public Node getFirstChild() {
/*  81: 81 */    return DOMNodeHelper.getFirstChild(this);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public Node getLastChild() {
/*  85: 85 */    return DOMNodeHelper.getLastChild(this);
/*  86:    */  }
/*  87:    */  
/*  88:    */  public Node getPreviousSibling() {
/*  89: 89 */    return DOMNodeHelper.getPreviousSibling(this);
/*  90:    */  }
/*  91:    */  
/*  92:    */  public Node getNextSibling() {
/*  93: 93 */    return DOMNodeHelper.getNextSibling(this);
/*  94:    */  }
/*  95:    */  
/*  96:    */  public NamedNodeMap getAttributes() {
/*  97: 97 */    return DOMNodeHelper.getAttributes(this);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public Document getOwnerDocument() {
/* 101:101 */    return DOMNodeHelper.getOwnerDocument(this);
/* 102:    */  }
/* 103:    */  
/* 104:    */  public Node insertBefore(Node newChild, Node refChild) throws DOMException
/* 105:    */  {
/* 106:106 */    return DOMNodeHelper.insertBefore(this, newChild, refChild);
/* 107:    */  }
/* 108:    */  
/* 109:    */  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/* 110:    */  {
/* 111:111 */    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public Node removeChild(Node oldChild) throws DOMException
/* 115:    */  {
/* 116:116 */    return DOMNodeHelper.removeChild(this, oldChild);
/* 117:    */  }
/* 118:    */  
/* 119:    */  public Node appendChild(Node newChild) throws DOMException
/* 120:    */  {
/* 121:121 */    return DOMNodeHelper.appendChild(this, newChild);
/* 122:    */  }
/* 123:    */  
/* 124:    */  public boolean hasChildNodes() {
/* 125:125 */    return DOMNodeHelper.hasChildNodes(this);
/* 126:    */  }
/* 127:    */  
/* 128:    */  public Node cloneNode(boolean deep) {
/* 129:129 */    return DOMNodeHelper.cloneNode(this, deep);
/* 130:    */  }
/* 131:    */  
/* 132:    */  public void normalize() {
/* 133:133 */    DOMNodeHelper.normalize(this);
/* 134:    */  }
/* 135:    */  
/* 136:    */  public boolean isSupported(String feature, String version) {
/* 137:137 */    return DOMNodeHelper.isSupported(this, feature, version);
/* 138:    */  }
/* 139:    */  
/* 140:    */  public boolean hasAttributes() {
/* 141:141 */    return DOMNodeHelper.hasAttributes(this);
/* 142:    */  }
/* 143:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMNamespace
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */