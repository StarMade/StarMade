/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import java.util.Map;
/*   4:    */import org.dom4j.Element;
/*   5:    */import org.dom4j.tree.DefaultProcessingInstruction;
/*   6:    */import org.w3c.dom.DOMException;
/*   7:    */import org.w3c.dom.Document;
/*   8:    */import org.w3c.dom.NamedNodeMap;
/*   9:    */import org.w3c.dom.Node;
/*  10:    */import org.w3c.dom.NodeList;
/*  11:    */import org.w3c.dom.ProcessingInstruction;
/*  12:    */
/*  26:    */public class DOMProcessingInstruction
/*  27:    */  extends DefaultProcessingInstruction
/*  28:    */  implements ProcessingInstruction
/*  29:    */{
/*  30:    */  public DOMProcessingInstruction(String target, Map values)
/*  31:    */  {
/*  32: 32 */    super(target, values);
/*  33:    */  }
/*  34:    */  
/*  35:    */  public DOMProcessingInstruction(String target, String values) {
/*  36: 36 */    super(target, values);
/*  37:    */  }
/*  38:    */  
/*  39:    */  public DOMProcessingInstruction(Element parent, String target, String val) {
/*  40: 40 */    super(parent, target, val);
/*  41:    */  }
/*  42:    */  
/*  44:    */  public boolean supports(String feature, String version)
/*  45:    */  {
/*  46: 46 */    return DOMNodeHelper.supports(this, feature, version);
/*  47:    */  }
/*  48:    */  
/*  49:    */  public String getNamespaceURI() {
/*  50: 50 */    return DOMNodeHelper.getNamespaceURI(this);
/*  51:    */  }
/*  52:    */  
/*  53:    */  public String getPrefix() {
/*  54: 54 */    return DOMNodeHelper.getPrefix(this);
/*  55:    */  }
/*  56:    */  
/*  57:    */  public void setPrefix(String prefix) throws DOMException {
/*  58: 58 */    DOMNodeHelper.setPrefix(this, prefix);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public String getLocalName() {
/*  62: 62 */    return DOMNodeHelper.getLocalName(this);
/*  63:    */  }
/*  64:    */  
/*  65:    */  public String getNodeName() {
/*  66: 66 */    return getName();
/*  67:    */  }
/*  68:    */  
/*  70:    */  public String getNodeValue()
/*  71:    */    throws DOMException
/*  72:    */  {
/*  73: 73 */    return DOMNodeHelper.getNodeValue(this);
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void setNodeValue(String nodeValue) throws DOMException {
/*  77: 77 */    DOMNodeHelper.setNodeValue(this, nodeValue);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public Node getParentNode() {
/*  81: 81 */    return DOMNodeHelper.getParentNode(this);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public NodeList getChildNodes() {
/*  85: 85 */    return DOMNodeHelper.getChildNodes(this);
/*  86:    */  }
/*  87:    */  
/*  88:    */  public Node getFirstChild() {
/*  89: 89 */    return DOMNodeHelper.getFirstChild(this);
/*  90:    */  }
/*  91:    */  
/*  92:    */  public Node getLastChild() {
/*  93: 93 */    return DOMNodeHelper.getLastChild(this);
/*  94:    */  }
/*  95:    */  
/*  96:    */  public Node getPreviousSibling() {
/*  97: 97 */    return DOMNodeHelper.getPreviousSibling(this);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public Node getNextSibling() {
/* 101:101 */    return DOMNodeHelper.getNextSibling(this);
/* 102:    */  }
/* 103:    */  
/* 104:    */  public NamedNodeMap getAttributes() {
/* 105:105 */    return null;
/* 106:    */  }
/* 107:    */  
/* 108:    */  public Document getOwnerDocument() {
/* 109:109 */    return DOMNodeHelper.getOwnerDocument(this);
/* 110:    */  }
/* 111:    */  
/* 112:    */  public Node insertBefore(Node newChild, Node refChild) throws DOMException
/* 113:    */  {
/* 114:114 */    checkNewChildNode(newChild);
/* 115:    */    
/* 116:116 */    return DOMNodeHelper.insertBefore(this, newChild, refChild);
/* 117:    */  }
/* 118:    */  
/* 119:    */  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/* 120:    */  {
/* 121:121 */    checkNewChildNode(newChild);
/* 122:    */    
/* 123:123 */    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/* 124:    */  }
/* 125:    */  
/* 126:    */  public Node removeChild(Node oldChild) throws DOMException
/* 127:    */  {
/* 128:128 */    return DOMNodeHelper.removeChild(this, oldChild);
/* 129:    */  }
/* 130:    */  
/* 131:    */  public Node appendChild(Node newChild) throws DOMException
/* 132:    */  {
/* 133:133 */    checkNewChildNode(newChild);
/* 134:    */    
/* 135:135 */    return DOMNodeHelper.appendChild(this, newChild);
/* 136:    */  }
/* 137:    */  
/* 138:    */  private void checkNewChildNode(Node newChild) throws DOMException
/* 139:    */  {
/* 140:140 */    throw new DOMException((short)3, "PI nodes cannot have children");
/* 141:    */  }
/* 142:    */  
/* 143:    */  public boolean hasChildNodes()
/* 144:    */  {
/* 145:145 */    return DOMNodeHelper.hasChildNodes(this);
/* 146:    */  }
/* 147:    */  
/* 148:    */  public Node cloneNode(boolean deep) {
/* 149:149 */    return DOMNodeHelper.cloneNode(this, deep);
/* 150:    */  }
/* 151:    */  
/* 152:    */  public void normalize() {
/* 153:153 */    DOMNodeHelper.normalize(this);
/* 154:    */  }
/* 155:    */  
/* 156:    */  public boolean isSupported(String feature, String version) {
/* 157:157 */    return DOMNodeHelper.isSupported(this, feature, version);
/* 158:    */  }
/* 159:    */  
/* 160:    */  public boolean hasAttributes() {
/* 161:161 */    return DOMNodeHelper.hasAttributes(this);
/* 162:    */  }
/* 163:    */  
/* 166:    */  public String getData()
/* 167:    */  {
/* 168:168 */    return getText();
/* 169:    */  }
/* 170:    */  
/* 171:    */  public void setData(String data) throws DOMException {
/* 172:172 */    if (isReadOnly()) {
/* 173:173 */      throw new DOMException((short)7, "This ProcessingInstruction is read only");
/* 174:    */    }
/* 175:    */    
/* 176:176 */    setText(data);
/* 177:    */  }
/* 178:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMProcessingInstruction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */