/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import org.dom4j.Element;
/*   4:    */import org.dom4j.tree.DefaultText;
/*   5:    */import org.w3c.dom.DOMException;
/*   6:    */import org.w3c.dom.Document;
/*   7:    */import org.w3c.dom.NamedNodeMap;
/*   8:    */import org.w3c.dom.Node;
/*   9:    */import org.w3c.dom.NodeList;
/*  10:    */
/*  23:    */public class DOMText
/*  24:    */  extends DefaultText
/*  25:    */  implements org.w3c.dom.Text
/*  26:    */{
/*  27:    */  public DOMText(String text)
/*  28:    */  {
/*  29: 29 */    super(text);
/*  30:    */  }
/*  31:    */  
/*  32:    */  public DOMText(Element parent, String text) {
/*  33: 33 */    super(parent, text);
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
/*  46:    */  public String getPrefix() {
/*  47: 47 */    return DOMNodeHelper.getPrefix(this);
/*  48:    */  }
/*  49:    */  
/*  50:    */  public void setPrefix(String prefix) throws DOMException {
/*  51: 51 */    DOMNodeHelper.setPrefix(this, prefix);
/*  52:    */  }
/*  53:    */  
/*  54:    */  public String getLocalName() {
/*  55: 55 */    return DOMNodeHelper.getLocalName(this);
/*  56:    */  }
/*  57:    */  
/*  58:    */  public String getNodeName() {
/*  59: 59 */    return "#text";
/*  60:    */  }
/*  61:    */  
/*  63:    */  public String getNodeValue()
/*  64:    */    throws DOMException
/*  65:    */  {
/*  66: 66 */    return DOMNodeHelper.getNodeValue(this);
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void setNodeValue(String nodeValue) throws DOMException {
/*  70: 70 */    DOMNodeHelper.setNodeValue(this, nodeValue);
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Node getParentNode() {
/*  74: 74 */    return DOMNodeHelper.getParentNode(this);
/*  75:    */  }
/*  76:    */  
/*  77:    */  public NodeList getChildNodes() {
/*  78: 78 */    return DOMNodeHelper.getChildNodes(this);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public Node getFirstChild() {
/*  82: 82 */    return DOMNodeHelper.getFirstChild(this);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public Node getLastChild() {
/*  86: 86 */    return DOMNodeHelper.getLastChild(this);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public Node getPreviousSibling() {
/*  90: 90 */    return DOMNodeHelper.getPreviousSibling(this);
/*  91:    */  }
/*  92:    */  
/*  93:    */  public Node getNextSibling() {
/*  94: 94 */    return DOMNodeHelper.getNextSibling(this);
/*  95:    */  }
/*  96:    */  
/*  97:    */  public NamedNodeMap getAttributes() {
/*  98: 98 */    return null;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public Document getOwnerDocument() {
/* 102:102 */    return DOMNodeHelper.getOwnerDocument(this);
/* 103:    */  }
/* 104:    */  
/* 105:    */  public Node insertBefore(Node newChild, Node refChild) throws DOMException
/* 106:    */  {
/* 107:107 */    checkNewChildNode(newChild);
/* 108:    */    
/* 109:109 */    return DOMNodeHelper.insertBefore(this, newChild, refChild);
/* 110:    */  }
/* 111:    */  
/* 112:    */  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/* 113:    */  {
/* 114:114 */    checkNewChildNode(newChild);
/* 115:    */    
/* 116:116 */    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/* 117:    */  }
/* 118:    */  
/* 119:    */  public Node removeChild(Node oldChild) throws DOMException
/* 120:    */  {
/* 121:121 */    return DOMNodeHelper.removeChild(this, oldChild);
/* 122:    */  }
/* 123:    */  
/* 124:    */  public Node appendChild(Node newChild) throws DOMException
/* 125:    */  {
/* 126:126 */    checkNewChildNode(newChild);
/* 127:    */    
/* 128:128 */    return DOMNodeHelper.appendChild(this, newChild);
/* 129:    */  }
/* 130:    */  
/* 131:    */  private void checkNewChildNode(Node newChild) throws DOMException
/* 132:    */  {
/* 133:133 */    throw new DOMException((short)3, "Text nodes cannot have children");
/* 134:    */  }
/* 135:    */  
/* 136:    */  public boolean hasChildNodes()
/* 137:    */  {
/* 138:138 */    return DOMNodeHelper.hasChildNodes(this);
/* 139:    */  }
/* 140:    */  
/* 141:    */  public Node cloneNode(boolean deep) {
/* 142:142 */    return DOMNodeHelper.cloneNode(this, deep);
/* 143:    */  }
/* 144:    */  
/* 145:    */  public void normalize() {
/* 146:146 */    DOMNodeHelper.normalize(this);
/* 147:    */  }
/* 148:    */  
/* 149:    */  public boolean isSupported(String feature, String version) {
/* 150:150 */    return DOMNodeHelper.isSupported(this, feature, version);
/* 151:    */  }
/* 152:    */  
/* 153:    */  public boolean hasAttributes() {
/* 154:154 */    return DOMNodeHelper.hasAttributes(this);
/* 155:    */  }
/* 156:    */  
/* 157:    */  public String getData()
/* 158:    */    throws DOMException
/* 159:    */  {
/* 160:160 */    return DOMNodeHelper.getData(this);
/* 161:    */  }
/* 162:    */  
/* 163:    */  public void setData(String data) throws DOMException {
/* 164:164 */    DOMNodeHelper.setData(this, data);
/* 165:    */  }
/* 166:    */  
/* 167:    */  public int getLength() {
/* 168:168 */    return DOMNodeHelper.getLength(this);
/* 169:    */  }
/* 170:    */  
/* 171:    */  public String substringData(int offset, int count) throws DOMException {
/* 172:172 */    return DOMNodeHelper.substringData(this, offset, count);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void appendData(String arg) throws DOMException {
/* 176:176 */    DOMNodeHelper.appendData(this, arg);
/* 177:    */  }
/* 178:    */  
/* 179:    */  public void insertData(int offset, String arg) throws DOMException {
/* 180:180 */    DOMNodeHelper.insertData(this, offset, arg);
/* 181:    */  }
/* 182:    */  
/* 183:    */  public void deleteData(int offset, int count) throws DOMException {
/* 184:184 */    DOMNodeHelper.deleteData(this, offset, count);
/* 185:    */  }
/* 186:    */  
/* 187:    */  public void replaceData(int offset, int count, String arg) throws DOMException
/* 188:    */  {
/* 189:189 */    DOMNodeHelper.replaceData(this, offset, count, arg);
/* 190:    */  }
/* 191:    */  
/* 192:    */  public org.w3c.dom.Text splitText(int offset)
/* 193:    */    throws DOMException
/* 194:    */  {
/* 195:195 */    if (isReadOnly()) {
/* 196:196 */      throw new DOMException((short)7, "CharacterData node is read only: " + this);
/* 197:    */    }
/* 198:    */    
/* 199:199 */    String text = getText();
/* 200:200 */    int length = text != null ? text.length() : 0;
/* 201:    */    
/* 202:202 */    if ((offset < 0) || (offset >= length)) {
/* 203:203 */      throw new DOMException((short)1, "No text at offset: " + offset);
/* 204:    */    }
/* 205:    */    
/* 206:206 */    String start = text.substring(0, offset);
/* 207:207 */    String rest = text.substring(offset);
/* 208:208 */    setText(start);
/* 209:    */    
/* 210:210 */    Element parent = getParent();
/* 211:211 */    org.dom4j.Text newText = createText(rest);
/* 212:    */    
/* 213:213 */    if (parent != null) {
/* 214:214 */      parent.add(newText);
/* 215:    */    }
/* 216:    */    
/* 217:217 */    return DOMNodeHelper.asDOMText(newText);
/* 218:    */  }
/* 219:    */  
/* 223:    */  protected org.dom4j.Text createText(String text)
/* 224:    */  {
/* 225:225 */    return new DOMText(text);
/* 226:    */  }
/* 227:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMText
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */