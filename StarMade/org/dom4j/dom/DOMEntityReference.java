/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import org.dom4j.Element;
/*   4:    */import org.dom4j.tree.DefaultEntity;
/*   5:    */import org.w3c.dom.DOMException;
/*   6:    */import org.w3c.dom.Document;
/*   7:    */import org.w3c.dom.EntityReference;
/*   8:    */import org.w3c.dom.NamedNodeMap;
/*   9:    */import org.w3c.dom.Node;
/*  10:    */import org.w3c.dom.NodeList;
/*  11:    */
/*  24:    */public class DOMEntityReference
/*  25:    */  extends DefaultEntity
/*  26:    */  implements EntityReference
/*  27:    */{
/*  28:    */  public DOMEntityReference(String name)
/*  29:    */  {
/*  30: 30 */    super(name);
/*  31:    */  }
/*  32:    */  
/*  33:    */  public DOMEntityReference(String name, String text) {
/*  34: 34 */    super(name, text);
/*  35:    */  }
/*  36:    */  
/*  37:    */  public DOMEntityReference(Element parent, String name, String text) {
/*  38: 38 */    super(parent, name, text);
/*  39:    */  }
/*  40:    */  
/*  42:    */  public boolean supports(String feature, String version)
/*  43:    */  {
/*  44: 44 */    return DOMNodeHelper.supports(this, feature, version);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public String getNamespaceURI() {
/*  48: 48 */    return DOMNodeHelper.getNamespaceURI(this);
/*  49:    */  }
/*  50:    */  
/*  51:    */  public String getPrefix() {
/*  52: 52 */    return DOMNodeHelper.getPrefix(this);
/*  53:    */  }
/*  54:    */  
/*  55:    */  public void setPrefix(String prefix) throws DOMException {
/*  56: 56 */    DOMNodeHelper.setPrefix(this, prefix);
/*  57:    */  }
/*  58:    */  
/*  59:    */  public String getLocalName() {
/*  60: 60 */    return DOMNodeHelper.getLocalName(this);
/*  61:    */  }
/*  62:    */  
/*  63:    */  public String getNodeName() {
/*  64: 64 */    return getName();
/*  65:    */  }
/*  66:    */  
/*  68:    */  public String getNodeValue()
/*  69:    */    throws DOMException
/*  70:    */  {
/*  71: 71 */    return null;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public void setNodeValue(String nodeValue) throws DOMException
/*  75:    */  {}
/*  76:    */  
/*  77:    */  public Node getParentNode() {
/*  78: 78 */    return DOMNodeHelper.getParentNode(this);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public NodeList getChildNodes() {
/*  82: 82 */    return DOMNodeHelper.getChildNodes(this);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public Node getFirstChild() {
/*  86: 86 */    return DOMNodeHelper.getFirstChild(this);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public Node getLastChild() {
/*  90: 90 */    return DOMNodeHelper.getLastChild(this);
/*  91:    */  }
/*  92:    */  
/*  93:    */  public Node getPreviousSibling() {
/*  94: 94 */    return DOMNodeHelper.getPreviousSibling(this);
/*  95:    */  }
/*  96:    */  
/*  97:    */  public Node getNextSibling() {
/*  98: 98 */    return DOMNodeHelper.getNextSibling(this);
/*  99:    */  }
/* 100:    */  
/* 101:    */  public NamedNodeMap getAttributes() {
/* 102:102 */    return null;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public Document getOwnerDocument() {
/* 106:106 */    return DOMNodeHelper.getOwnerDocument(this);
/* 107:    */  }
/* 108:    */  
/* 109:    */  public Node insertBefore(Node newChild, Node refChild) throws DOMException
/* 110:    */  {
/* 111:111 */    checkNewChildNode(newChild);
/* 112:    */    
/* 113:113 */    return DOMNodeHelper.insertBefore(this, newChild, refChild);
/* 114:    */  }
/* 115:    */  
/* 116:    */  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/* 117:    */  {
/* 118:118 */    checkNewChildNode(newChild);
/* 119:    */    
/* 120:120 */    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/* 121:    */  }
/* 122:    */  
/* 123:    */  public Node removeChild(Node oldChild) throws DOMException
/* 124:    */  {
/* 125:125 */    return DOMNodeHelper.removeChild(this, oldChild);
/* 126:    */  }
/* 127:    */  
/* 128:    */  public Node appendChild(Node newChild) throws DOMException
/* 129:    */  {
/* 130:130 */    checkNewChildNode(newChild);
/* 131:    */    
/* 132:132 */    return DOMNodeHelper.appendChild(this, newChild);
/* 133:    */  }
/* 134:    */  
/* 135:    */  private void checkNewChildNode(Node newChild) throws DOMException
/* 136:    */  {
/* 137:137 */    int nodeType = newChild.getNodeType();
/* 138:    */    
/* 139:139 */    if ((nodeType != 1) && (nodeType != 3) && (nodeType != 8) && (nodeType != 7) && (nodeType != 4) && (nodeType != 5))
/* 140:    */    {
/* 145:145 */      throw new DOMException((short)3, "Given node cannot be a child of an entity reference");
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
/* 169:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMEntityReference
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */