/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import org.dom4j.QName;
/*   4:    */import org.dom4j.tree.DefaultAttribute;
/*   5:    */import org.w3c.dom.Attr;
/*   6:    */import org.w3c.dom.DOMException;
/*   7:    */import org.w3c.dom.Document;
/*   8:    */import org.w3c.dom.NamedNodeMap;
/*   9:    */import org.w3c.dom.Node;
/*  10:    */import org.w3c.dom.NodeList;
/*  11:    */
/*  24:    */public class DOMAttribute
/*  25:    */  extends DefaultAttribute
/*  26:    */  implements Attr
/*  27:    */{
/*  28:    */  public DOMAttribute(QName qname)
/*  29:    */  {
/*  30: 30 */    super(qname);
/*  31:    */  }
/*  32:    */  
/*  33:    */  public DOMAttribute(QName qname, String value) {
/*  34: 34 */    super(qname, value);
/*  35:    */  }
/*  36:    */  
/*  37:    */  public DOMAttribute(org.dom4j.Element parent, QName qname, String value) {
/*  38: 38 */    super(parent, qname, value);
/*  39:    */  }
/*  40:    */  
/*  42:    */  public boolean supports(String feature, String version)
/*  43:    */  {
/*  44: 44 */    return DOMNodeHelper.supports(this, feature, version);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public String getNamespaceURI() {
/*  48: 48 */    return getQName().getNamespaceURI();
/*  49:    */  }
/*  50:    */  
/*  51:    */  public String getPrefix() {
/*  52: 52 */    return getQName().getNamespacePrefix();
/*  53:    */  }
/*  54:    */  
/*  55:    */  public void setPrefix(String prefix) throws DOMException {
/*  56: 56 */    DOMNodeHelper.setPrefix(this, prefix);
/*  57:    */  }
/*  58:    */  
/*  59:    */  public String getLocalName() {
/*  60: 60 */    return getQName().getName();
/*  61:    */  }
/*  62:    */  
/*  63:    */  public String getNodeName() {
/*  64: 64 */    return getName();
/*  65:    */  }
/*  66:    */  
/*  68:    */  public String getNodeValue()
/*  69:    */    throws DOMException
/*  70:    */  {
/*  71: 71 */    return DOMNodeHelper.getNodeValue(this);
/*  72:    */  }
/*  73:    */  
/*  74:    */  public void setNodeValue(String nodeValue) throws DOMException {
/*  75: 75 */    DOMNodeHelper.setNodeValue(this, nodeValue);
/*  76:    */  }
/*  77:    */  
/*  80:    */  public Node getParentNode()
/*  81:    */  {
/*  82: 82 */    return null;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public NodeList getChildNodes() {
/*  86: 86 */    return DOMNodeHelper.getChildNodes(this);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public Node getFirstChild() {
/*  90: 90 */    return DOMNodeHelper.getFirstChild(this);
/*  91:    */  }
/*  92:    */  
/*  93:    */  public Node getLastChild() {
/*  94: 94 */    return DOMNodeHelper.getLastChild(this);
/*  95:    */  }
/*  96:    */  
/*  97:    */  public Node getPreviousSibling() {
/*  98: 98 */    return DOMNodeHelper.getPreviousSibling(this);
/*  99:    */  }
/* 100:    */  
/* 101:    */  public Node getNextSibling() {
/* 102:102 */    return DOMNodeHelper.getNextSibling(this);
/* 103:    */  }
/* 104:    */  
/* 105:    */  public NamedNodeMap getAttributes() {
/* 106:106 */    return null;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public Document getOwnerDocument() {
/* 110:110 */    return DOMNodeHelper.getOwnerDocument(this);
/* 111:    */  }
/* 112:    */  
/* 113:    */  public Node insertBefore(Node newChild, Node refChild) throws DOMException
/* 114:    */  {
/* 115:115 */    checkNewChildNode(newChild);
/* 116:    */    
/* 117:117 */    return DOMNodeHelper.insertBefore(this, newChild, refChild);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/* 121:    */  {
/* 122:122 */    checkNewChildNode(newChild);
/* 123:    */    
/* 124:124 */    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/* 125:    */  }
/* 126:    */  
/* 127:    */  public Node removeChild(Node oldChild) throws DOMException
/* 128:    */  {
/* 129:129 */    return DOMNodeHelper.removeChild(this, oldChild);
/* 130:    */  }
/* 131:    */  
/* 132:    */  public Node appendChild(Node newChild) throws DOMException
/* 133:    */  {
/* 134:134 */    checkNewChildNode(newChild);
/* 135:    */    
/* 136:136 */    return DOMNodeHelper.appendChild(this, newChild);
/* 137:    */  }
/* 138:    */  
/* 139:    */  private void checkNewChildNode(Node newChild) throws DOMException
/* 140:    */  {
/* 141:141 */    int nodeType = newChild.getNodeType();
/* 142:    */    
/* 143:143 */    if ((nodeType != 3) && (nodeType != 5))
/* 144:    */    {
/* 145:145 */      throw new DOMException((short)3, "The node cannot be a child of attribute");
/* 146:    */    }
/* 147:    */  }
/* 148:    */  
/* 149:    */  public boolean hasChildNodes()
/* 150:    */  {
/* 151:151 */    return DOMNodeHelper.hasChildNodes(this);
/* 152:    */  }
/* 153:    */  
/* 154:    */  public Node cloneNode(boolean deep) {
/* 155:155 */    return DOMNodeHelper.cloneNode(this, deep);
/* 156:    */  }
/* 157:    */  
/* 158:    */  public void normalize() {
/* 159:159 */    DOMNodeHelper.normalize(this);
/* 160:    */  }
/* 161:    */  
/* 162:    */  public boolean isSupported(String feature, String version) {
/* 163:163 */    return DOMNodeHelper.isSupported(this, feature, version);
/* 164:    */  }
/* 165:    */  
/* 166:    */  public boolean hasAttributes() {
/* 167:167 */    return DOMNodeHelper.hasAttributes(this);
/* 168:    */  }
/* 169:    */  
/* 172:    */  public boolean getSpecified()
/* 173:    */  {
/* 174:174 */    return true;
/* 175:    */  }
/* 176:    */  
/* 178:    */  public org.w3c.dom.Element getOwnerElement()
/* 179:    */  {
/* 180:180 */    return DOMNodeHelper.asDOMElement(getParent());
/* 181:    */  }
/* 182:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */