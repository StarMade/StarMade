/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import org.dom4j.tree.DefaultDocumentType;
/*   4:    */import org.w3c.dom.DOMException;
/*   5:    */import org.w3c.dom.Document;
/*   6:    */import org.w3c.dom.DocumentType;
/*   7:    */import org.w3c.dom.NamedNodeMap;
/*   8:    */import org.w3c.dom.Node;
/*   9:    */import org.w3c.dom.NodeList;
/*  10:    */
/*  24:    */public class DOMDocumentType
/*  25:    */  extends DefaultDocumentType
/*  26:    */  implements DocumentType
/*  27:    */{
/*  28:    */  public DOMDocumentType() {}
/*  29:    */  
/*  30:    */  public DOMDocumentType(String elementName, String systemID)
/*  31:    */  {
/*  32: 32 */    super(elementName, systemID);
/*  33:    */  }
/*  34:    */  
/*  35:    */  public DOMDocumentType(String name, String publicID, String systemID) {
/*  36: 36 */    super(name, publicID, systemID);
/*  37:    */  }
/*  38:    */  
/*  40:    */  public boolean supports(String feature, String version)
/*  41:    */  {
/*  42: 42 */    return DOMNodeHelper.supports(this, feature, version);
/*  43:    */  }
/*  44:    */  
/*  45:    */  public String getNamespaceURI() {
/*  46: 46 */    return DOMNodeHelper.getNamespaceURI(this);
/*  47:    */  }
/*  48:    */  
/*  49:    */  public String getPrefix() {
/*  50: 50 */    return DOMNodeHelper.getPrefix(this);
/*  51:    */  }
/*  52:    */  
/*  53:    */  public void setPrefix(String prefix) throws DOMException {
/*  54: 54 */    DOMNodeHelper.setPrefix(this, prefix);
/*  55:    */  }
/*  56:    */  
/*  57:    */  public String getLocalName() {
/*  58: 58 */    return DOMNodeHelper.getLocalName(this);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public String getNodeName() {
/*  62: 62 */    return getName();
/*  63:    */  }
/*  64:    */  
/*  66:    */  public String getNodeValue()
/*  67:    */    throws DOMException
/*  68:    */  {
/*  69: 69 */    return null;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public void setNodeValue(String nodeValue) throws DOMException
/*  73:    */  {}
/*  74:    */  
/*  75:    */  public Node getParentNode() {
/*  76: 76 */    return DOMNodeHelper.getParentNode(this);
/*  77:    */  }
/*  78:    */  
/*  79:    */  public NodeList getChildNodes() {
/*  80: 80 */    return DOMNodeHelper.getChildNodes(this);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public Node getFirstChild() {
/*  84: 84 */    return DOMNodeHelper.getFirstChild(this);
/*  85:    */  }
/*  86:    */  
/*  87:    */  public Node getLastChild() {
/*  88: 88 */    return DOMNodeHelper.getLastChild(this);
/*  89:    */  }
/*  90:    */  
/*  91:    */  public Node getPreviousSibling() {
/*  92: 92 */    return DOMNodeHelper.getPreviousSibling(this);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public Node getNextSibling() {
/*  96: 96 */    return DOMNodeHelper.getNextSibling(this);
/*  97:    */  }
/*  98:    */  
/*  99:    */  public NamedNodeMap getAttributes() {
/* 100:100 */    return null;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public Document getOwnerDocument() {
/* 104:104 */    return DOMNodeHelper.getOwnerDocument(this);
/* 105:    */  }
/* 106:    */  
/* 107:    */  public Node insertBefore(Node newChild, Node refChild) throws DOMException
/* 108:    */  {
/* 109:109 */    checkNewChildNode(newChild);
/* 110:    */    
/* 111:111 */    return DOMNodeHelper.insertBefore(this, newChild, refChild);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/* 115:    */  {
/* 116:116 */    checkNewChildNode(newChild);
/* 117:    */    
/* 118:118 */    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/* 119:    */  }
/* 120:    */  
/* 121:    */  public Node removeChild(Node oldChild) throws DOMException
/* 122:    */  {
/* 123:123 */    return DOMNodeHelper.removeChild(this, oldChild);
/* 124:    */  }
/* 125:    */  
/* 126:    */  public Node appendChild(Node newChild) throws DOMException
/* 127:    */  {
/* 128:128 */    checkNewChildNode(newChild);
/* 129:    */    
/* 130:130 */    return DOMNodeHelper.appendChild(this, newChild);
/* 131:    */  }
/* 132:    */  
/* 133:    */  private void checkNewChildNode(Node newChild) throws DOMException
/* 134:    */  {
/* 135:135 */    throw new DOMException((short)3, "DocumentType nodes cannot have children");
/* 136:    */  }
/* 137:    */  
/* 138:    */  public boolean hasChildNodes()
/* 139:    */  {
/* 140:140 */    return DOMNodeHelper.hasChildNodes(this);
/* 141:    */  }
/* 142:    */  
/* 143:    */  public Node cloneNode(boolean deep) {
/* 144:144 */    return DOMNodeHelper.cloneNode(this, deep);
/* 145:    */  }
/* 146:    */  
/* 147:    */  public void normalize() {
/* 148:148 */    DOMNodeHelper.normalize(this);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public boolean isSupported(String feature, String version) {
/* 152:152 */    return DOMNodeHelper.isSupported(this, feature, version);
/* 153:    */  }
/* 154:    */  
/* 155:    */  public boolean hasAttributes() {
/* 156:156 */    return DOMNodeHelper.hasAttributes(this);
/* 157:    */  }
/* 158:    */  
/* 160:    */  public NamedNodeMap getEntities()
/* 161:    */  {
/* 162:162 */    return null;
/* 163:    */  }
/* 164:    */  
/* 165:    */  public NamedNodeMap getNotations() {
/* 166:166 */    return null;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public String getPublicId() {
/* 170:170 */    return getPublicID();
/* 171:    */  }
/* 172:    */  
/* 173:    */  public String getSystemId() {
/* 174:174 */    return getSystemID();
/* 175:    */  }
/* 176:    */  
/* 177:    */  public String getInternalSubset() {
/* 178:178 */    return getElementName();
/* 179:    */  }
/* 180:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMDocumentType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */