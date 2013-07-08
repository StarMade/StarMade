/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import org.dom4j.CDATA;
/*   4:    */import org.dom4j.Element;
/*   5:    */import org.dom4j.tree.DefaultCDATA;
/*   6:    */import org.w3c.dom.CDATASection;
/*   7:    */import org.w3c.dom.DOMException;
/*   8:    */import org.w3c.dom.Document;
/*   9:    */import org.w3c.dom.NamedNodeMap;
/*  10:    */import org.w3c.dom.Node;
/*  11:    */import org.w3c.dom.NodeList;
/*  12:    */import org.w3c.dom.Text;
/*  13:    */
/*  24:    */public class DOMCDATA
/*  25:    */  extends DefaultCDATA
/*  26:    */  implements CDATASection
/*  27:    */{
/*  28:    */  public DOMCDATA(String text)
/*  29:    */  {
/*  30: 30 */    super(text);
/*  31:    */  }
/*  32:    */  
/*  33:    */  public DOMCDATA(Element parent, String text) {
/*  34: 34 */    super(parent, text);
/*  35:    */  }
/*  36:    */  
/*  38:    */  public boolean supports(String feature, String version)
/*  39:    */  {
/*  40: 40 */    return DOMNodeHelper.supports(this, feature, version);
/*  41:    */  }
/*  42:    */  
/*  43:    */  public String getNamespaceURI() {
/*  44: 44 */    return DOMNodeHelper.getNamespaceURI(this);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public String getPrefix() {
/*  48: 48 */    return DOMNodeHelper.getPrefix(this);
/*  49:    */  }
/*  50:    */  
/*  51:    */  public void setPrefix(String prefix) throws DOMException {
/*  52: 52 */    DOMNodeHelper.setPrefix(this, prefix);
/*  53:    */  }
/*  54:    */  
/*  55:    */  public String getLocalName() {
/*  56: 56 */    return DOMNodeHelper.getLocalName(this);
/*  57:    */  }
/*  58:    */  
/*  59:    */  public String getNodeName() {
/*  60: 60 */    return "#cdata-section";
/*  61:    */  }
/*  62:    */  
/*  64:    */  public String getNodeValue()
/*  65:    */    throws DOMException
/*  66:    */  {
/*  67: 67 */    return DOMNodeHelper.getNodeValue(this);
/*  68:    */  }
/*  69:    */  
/*  70:    */  public void setNodeValue(String nodeValue) throws DOMException {
/*  71: 71 */    DOMNodeHelper.setNodeValue(this, nodeValue);
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Node getParentNode() {
/*  75: 75 */    return DOMNodeHelper.getParentNode(this);
/*  76:    */  }
/*  77:    */  
/*  78:    */  public NodeList getChildNodes() {
/*  79: 79 */    return DOMNodeHelper.getChildNodes(this);
/*  80:    */  }
/*  81:    */  
/*  82:    */  public Node getFirstChild() {
/*  83: 83 */    return DOMNodeHelper.getFirstChild(this);
/*  84:    */  }
/*  85:    */  
/*  86:    */  public Node getLastChild() {
/*  87: 87 */    return DOMNodeHelper.getLastChild(this);
/*  88:    */  }
/*  89:    */  
/*  90:    */  public Node getPreviousSibling() {
/*  91: 91 */    return DOMNodeHelper.getPreviousSibling(this);
/*  92:    */  }
/*  93:    */  
/*  94:    */  public Node getNextSibling() {
/*  95: 95 */    return DOMNodeHelper.getNextSibling(this);
/*  96:    */  }
/*  97:    */  
/*  98:    */  public NamedNodeMap getAttributes() {
/*  99: 99 */    return null;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public Document getOwnerDocument() {
/* 103:103 */    return DOMNodeHelper.getOwnerDocument(this);
/* 104:    */  }
/* 105:    */  
/* 106:    */  public Node insertBefore(Node newChild, Node refChild) throws DOMException
/* 107:    */  {
/* 108:108 */    checkNewChildNode(newChild);
/* 109:    */    
/* 110:110 */    return DOMNodeHelper.insertBefore(this, newChild, refChild);
/* 111:    */  }
/* 112:    */  
/* 113:    */  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/* 114:    */  {
/* 115:115 */    checkNewChildNode(newChild);
/* 116:    */    
/* 117:117 */    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public Node removeChild(Node oldChild) throws DOMException
/* 121:    */  {
/* 122:122 */    return DOMNodeHelper.removeChild(this, oldChild);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public Node appendChild(Node newChild) throws DOMException
/* 126:    */  {
/* 127:127 */    checkNewChildNode(newChild);
/* 128:    */    
/* 129:129 */    return DOMNodeHelper.appendChild(this, newChild);
/* 130:    */  }
/* 131:    */  
/* 132:    */  private void checkNewChildNode(Node newChild) throws DOMException
/* 133:    */  {
/* 134:134 */    throw new DOMException((short)3, "CDATASection nodes cannot have children");
/* 135:    */  }
/* 136:    */  
/* 137:    */  public boolean hasChildNodes()
/* 138:    */  {
/* 139:139 */    return DOMNodeHelper.hasChildNodes(this);
/* 140:    */  }
/* 141:    */  
/* 142:    */  public Node cloneNode(boolean deep) {
/* 143:143 */    return DOMNodeHelper.cloneNode(this, deep);
/* 144:    */  }
/* 145:    */  
/* 146:    */  public void normalize() {
/* 147:147 */    DOMNodeHelper.normalize(this);
/* 148:    */  }
/* 149:    */  
/* 150:    */  public boolean isSupported(String feature, String version) {
/* 151:151 */    return DOMNodeHelper.isSupported(this, feature, version);
/* 152:    */  }
/* 153:    */  
/* 154:    */  public boolean hasAttributes() {
/* 155:155 */    return DOMNodeHelper.hasAttributes(this);
/* 156:    */  }
/* 157:    */  
/* 158:    */  public String getData()
/* 159:    */    throws DOMException
/* 160:    */  {
/* 161:161 */    return DOMNodeHelper.getData(this);
/* 162:    */  }
/* 163:    */  
/* 164:    */  public void setData(String data) throws DOMException {
/* 165:165 */    DOMNodeHelper.setData(this, data);
/* 166:    */  }
/* 167:    */  
/* 168:    */  public int getLength() {
/* 169:169 */    return DOMNodeHelper.getLength(this);
/* 170:    */  }
/* 171:    */  
/* 172:    */  public String substringData(int offset, int count) throws DOMException {
/* 173:173 */    return DOMNodeHelper.substringData(this, offset, count);
/* 174:    */  }
/* 175:    */  
/* 176:    */  public void appendData(String arg) throws DOMException {
/* 177:177 */    DOMNodeHelper.appendData(this, arg);
/* 178:    */  }
/* 179:    */  
/* 180:    */  public void insertData(int offset, String arg) throws DOMException {
/* 181:181 */    DOMNodeHelper.insertData(this, offset, arg);
/* 182:    */  }
/* 183:    */  
/* 184:    */  public void deleteData(int offset, int count) throws DOMException {
/* 185:185 */    DOMNodeHelper.deleteData(this, offset, count);
/* 186:    */  }
/* 187:    */  
/* 188:    */  public void replaceData(int offset, int count, String arg) throws DOMException
/* 189:    */  {
/* 190:190 */    DOMNodeHelper.replaceData(this, offset, count, arg);
/* 191:    */  }
/* 192:    */  
/* 193:    */  public Text splitText(int offset)
/* 194:    */    throws DOMException
/* 195:    */  {
/* 196:196 */    if (isReadOnly()) {
/* 197:197 */      throw new DOMException((short)7, "CharacterData node is read only: " + this);
/* 198:    */    }
/* 199:    */    
/* 200:200 */    String text = getText();
/* 201:201 */    int length = text != null ? text.length() : 0;
/* 202:    */    
/* 203:203 */    if ((offset < 0) || (offset >= length)) {
/* 204:204 */      throw new DOMException((short)1, "No text at offset: " + offset);
/* 205:    */    }
/* 206:    */    
/* 207:207 */    String start = text.substring(0, offset);
/* 208:208 */    String rest = text.substring(offset);
/* 209:209 */    setText(start);
/* 210:    */    
/* 211:211 */    Element parent = getParent();
/* 212:212 */    CDATA newText = createCDATA(rest);
/* 213:    */    
/* 214:214 */    if (parent != null) {
/* 215:215 */      parent.add(newText);
/* 216:    */    }
/* 217:    */    
/* 218:218 */    return DOMNodeHelper.asDOMText(newText);
/* 219:    */  }
/* 220:    */  
/* 224:    */  protected CDATA createCDATA(String text)
/* 225:    */  {
/* 226:226 */    return new DOMCDATA(text);
/* 227:    */  }
/* 228:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMCDATA
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */