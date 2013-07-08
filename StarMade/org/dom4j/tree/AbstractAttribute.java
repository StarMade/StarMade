/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.Writer;
/*   5:    */import org.dom4j.Attribute;
/*   6:    */import org.dom4j.Element;
/*   7:    */import org.dom4j.Namespace;
/*   8:    */import org.dom4j.Node;
/*   9:    */import org.dom4j.QName;
/*  10:    */import org.dom4j.Visitor;
/*  11:    */
/*  25:    */public abstract class AbstractAttribute
/*  26:    */  extends AbstractNode
/*  27:    */  implements Attribute
/*  28:    */{
/*  29:    */  public short getNodeType()
/*  30:    */  {
/*  31: 31 */    return 2;
/*  32:    */  }
/*  33:    */  
/*  34:    */  public void setNamespace(Namespace namespace) {
/*  35: 35 */    String msg = "This Attribute is read only and cannot be changed";
/*  36: 36 */    throw new UnsupportedOperationException(msg);
/*  37:    */  }
/*  38:    */  
/*  39:    */  public String getText() {
/*  40: 40 */    return getValue();
/*  41:    */  }
/*  42:    */  
/*  43:    */  public void setText(String text) {
/*  44: 44 */    setValue(text);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public void setValue(String value) {
/*  48: 48 */    String msg = "This Attribute is read only and cannot be changed";
/*  49: 49 */    throw new UnsupportedOperationException(msg);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public Object getData() {
/*  53: 53 */    return getValue();
/*  54:    */  }
/*  55:    */  
/*  56:    */  public void setData(Object data) {
/*  57: 57 */    setValue(data == null ? null : data.toString());
/*  58:    */  }
/*  59:    */  
/*  60:    */  public String toString() {
/*  61: 61 */    return super.toString() + " [Attribute: name " + getQualifiedName() + " value \"" + getValue() + "\"]";
/*  62:    */  }
/*  63:    */  
/*  64:    */  public String asXML()
/*  65:    */  {
/*  66: 66 */    return getQualifiedName() + "=\"" + getValue() + "\"";
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void write(Writer writer) throws IOException {
/*  70: 70 */    writer.write(getQualifiedName());
/*  71: 71 */    writer.write("=\"");
/*  72: 72 */    writer.write(getValue());
/*  73: 73 */    writer.write("\"");
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void accept(Visitor visitor) {
/*  77: 77 */    visitor.visit(this);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public Namespace getNamespace()
/*  81:    */  {
/*  82: 82 */    return getQName().getNamespace();
/*  83:    */  }
/*  84:    */  
/*  85:    */  public String getName() {
/*  86: 86 */    return getQName().getName();
/*  87:    */  }
/*  88:    */  
/*  89:    */  public String getNamespacePrefix() {
/*  90: 90 */    return getQName().getNamespacePrefix();
/*  91:    */  }
/*  92:    */  
/*  93:    */  public String getNamespaceURI() {
/*  94: 94 */    return getQName().getNamespaceURI();
/*  95:    */  }
/*  96:    */  
/*  97:    */  public String getQualifiedName() {
/*  98: 98 */    return getQName().getQualifiedName();
/*  99:    */  }
/* 100:    */  
/* 101:    */  public String getPath(Element context) {
/* 102:102 */    StringBuffer result = new StringBuffer();
/* 103:    */    
/* 104:104 */    Element parent = getParent();
/* 105:    */    
/* 106:106 */    if ((parent != null) && (parent != context)) {
/* 107:107 */      result.append(parent.getPath(context));
/* 108:108 */      result.append("/");
/* 109:    */    }
/* 110:    */    
/* 111:111 */    result.append("@");
/* 112:    */    
/* 113:113 */    String uri = getNamespaceURI();
/* 114:114 */    String prefix = getNamespacePrefix();
/* 115:    */    
/* 116:116 */    if ((uri == null) || (uri.length() == 0) || (prefix == null) || (prefix.length() == 0))
/* 117:    */    {
/* 118:118 */      result.append(getName());
/* 119:    */    } else {
/* 120:120 */      result.append(getQualifiedName());
/* 121:    */    }
/* 122:    */    
/* 123:123 */    return result.toString();
/* 124:    */  }
/* 125:    */  
/* 126:    */  public String getUniquePath(Element context) {
/* 127:127 */    StringBuffer result = new StringBuffer();
/* 128:    */    
/* 129:129 */    Element parent = getParent();
/* 130:    */    
/* 131:131 */    if ((parent != null) && (parent != context)) {
/* 132:132 */      result.append(parent.getUniquePath(context));
/* 133:133 */      result.append("/");
/* 134:    */    }
/* 135:    */    
/* 136:136 */    result.append("@");
/* 137:    */    
/* 138:138 */    String uri = getNamespaceURI();
/* 139:139 */    String prefix = getNamespacePrefix();
/* 140:    */    
/* 141:141 */    if ((uri == null) || (uri.length() == 0) || (prefix == null) || (prefix.length() == 0))
/* 142:    */    {
/* 143:143 */      result.append(getName());
/* 144:    */    } else {
/* 145:145 */      result.append(getQualifiedName());
/* 146:    */    }
/* 147:    */    
/* 148:148 */    return result.toString();
/* 149:    */  }
/* 150:    */  
/* 151:    */  protected Node createXPathResult(Element parent) {
/* 152:152 */    return new DefaultAttribute(parent, getQName(), getValue());
/* 153:    */  }
/* 154:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */