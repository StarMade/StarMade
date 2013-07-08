/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import org.dom4j.Element;
/*   4:    */import org.dom4j.tree.DefaultComment;
/*   5:    */import org.w3c.dom.Comment;
/*   6:    */import org.w3c.dom.DOMException;
/*   7:    */import org.w3c.dom.Document;
/*   8:    */import org.w3c.dom.NamedNodeMap;
/*   9:    */import org.w3c.dom.Node;
/*  10:    */import org.w3c.dom.NodeList;
/*  11:    */
/*  22:    */public class DOMComment
/*  23:    */  extends DefaultComment
/*  24:    */  implements Comment
/*  25:    */{
/*  26:    */  public DOMComment(String text)
/*  27:    */  {
/*  28: 28 */    super(text);
/*  29:    */  }
/*  30:    */  
/*  31:    */  public DOMComment(Element parent, String text) {
/*  32: 32 */    super(parent, text);
/*  33:    */  }
/*  34:    */  
/*  36:    */  public boolean supports(String feature, String version)
/*  37:    */  {
/*  38: 38 */    return DOMNodeHelper.supports(this, feature, version);
/*  39:    */  }
/*  40:    */  
/*  41:    */  public String getNamespaceURI() {
/*  42: 42 */    return DOMNodeHelper.getNamespaceURI(this);
/*  43:    */  }
/*  44:    */  
/*  45:    */  public String getPrefix() {
/*  46: 46 */    return DOMNodeHelper.getPrefix(this);
/*  47:    */  }
/*  48:    */  
/*  49:    */  public void setPrefix(String prefix) throws DOMException {
/*  50: 50 */    DOMNodeHelper.setPrefix(this, prefix);
/*  51:    */  }
/*  52:    */  
/*  53:    */  public String getLocalName() {
/*  54: 54 */    return DOMNodeHelper.getLocalName(this);
/*  55:    */  }
/*  56:    */  
/*  57:    */  public String getNodeName() {
/*  58: 58 */    return "#comment";
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
/*  97: 97 */    return null;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public Document getOwnerDocument() {
/* 101:101 */    return DOMNodeHelper.getOwnerDocument(this);
/* 102:    */  }
/* 103:    */  
/* 104:    */  public Node insertBefore(Node newChild, Node refChild) throws DOMException
/* 105:    */  {
/* 106:106 */    checkNewChildNode(newChild);
/* 107:    */    
/* 108:108 */    return DOMNodeHelper.insertBefore(this, newChild, refChild);
/* 109:    */  }
/* 110:    */  
/* 111:    */  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/* 112:    */  {
/* 113:113 */    checkNewChildNode(newChild);
/* 114:    */    
/* 115:115 */    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/* 116:    */  }
/* 117:    */  
/* 118:    */  public Node removeChild(Node oldChild) throws DOMException
/* 119:    */  {
/* 120:120 */    return DOMNodeHelper.removeChild(this, oldChild);
/* 121:    */  }
/* 122:    */  
/* 123:    */  public Node appendChild(Node newChild) throws DOMException
/* 124:    */  {
/* 125:125 */    checkNewChildNode(newChild);
/* 126:    */    
/* 127:127 */    return DOMNodeHelper.appendChild(this, newChild);
/* 128:    */  }
/* 129:    */  
/* 130:    */  private void checkNewChildNode(Node newChild) throws DOMException
/* 131:    */  {
/* 132:132 */    throw new DOMException((short)3, "Comment nodes cannot have children");
/* 133:    */  }
/* 134:    */  
/* 135:    */  public boolean hasChildNodes()
/* 136:    */  {
/* 137:137 */    return DOMNodeHelper.hasChildNodes(this);
/* 138:    */  }
/* 139:    */  
/* 140:    */  public Node cloneNode(boolean deep) {
/* 141:141 */    return DOMNodeHelper.cloneNode(this, deep);
/* 142:    */  }
/* 143:    */  
/* 144:    */  public void normalize() {
/* 145:145 */    DOMNodeHelper.normalize(this);
/* 146:    */  }
/* 147:    */  
/* 148:    */  public boolean isSupported(String feature, String version) {
/* 149:149 */    return DOMNodeHelper.isSupported(this, feature, version);
/* 150:    */  }
/* 151:    */  
/* 152:    */  public boolean hasAttributes() {
/* 153:153 */    return DOMNodeHelper.hasAttributes(this);
/* 154:    */  }
/* 155:    */  
/* 156:    */  public String getData()
/* 157:    */    throws DOMException
/* 158:    */  {
/* 159:159 */    return DOMNodeHelper.getData(this);
/* 160:    */  }
/* 161:    */  
/* 162:    */  public void setData(String data) throws DOMException {
/* 163:163 */    DOMNodeHelper.setData(this, data);
/* 164:    */  }
/* 165:    */  
/* 166:    */  public int getLength() {
/* 167:167 */    return DOMNodeHelper.getLength(this);
/* 168:    */  }
/* 169:    */  
/* 170:    */  public String substringData(int offset, int count) throws DOMException {
/* 171:171 */    return DOMNodeHelper.substringData(this, offset, count);
/* 172:    */  }
/* 173:    */  
/* 174:    */  public void appendData(String arg) throws DOMException {
/* 175:175 */    DOMNodeHelper.appendData(this, arg);
/* 176:    */  }
/* 177:    */  
/* 178:    */  public void insertData(int offset, String arg) throws DOMException {
/* 179:179 */    DOMNodeHelper.insertData(this, offset, arg);
/* 180:    */  }
/* 181:    */  
/* 182:    */  public void deleteData(int offset, int count) throws DOMException {
/* 183:183 */    DOMNodeHelper.deleteData(this, offset, count);
/* 184:    */  }
/* 185:    */  
/* 186:    */  public void replaceData(int offset, int count, String arg) throws DOMException
/* 187:    */  {
/* 188:188 */    DOMNodeHelper.replaceData(this, offset, count, arg);
/* 189:    */  }
/* 190:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMComment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */