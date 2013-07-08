/*   1:    */package org.dom4j.datatype;
/*   2:    */
/*   3:    */import com.sun.msv.datatype.DatabindableDatatype;
/*   4:    */import com.sun.msv.datatype.SerializationContext;
/*   5:    */import com.sun.msv.datatype.xsd.XSDatatype;
/*   6:    */import org.dom4j.Element;
/*   7:    */import org.dom4j.Namespace;
/*   8:    */import org.dom4j.QName;
/*   9:    */import org.dom4j.tree.AbstractAttribute;
/*  10:    */import org.relaxng.datatype.DatatypeException;
/*  11:    */import org.relaxng.datatype.ValidationContext;
/*  12:    */
/*  38:    */public class DatatypeAttribute
/*  39:    */  extends AbstractAttribute
/*  40:    */  implements SerializationContext, ValidationContext
/*  41:    */{
/*  42:    */  private Element parent;
/*  43:    */  private QName qname;
/*  44:    */  private XSDatatype datatype;
/*  45:    */  private Object data;
/*  46:    */  private String text;
/*  47:    */  
/*  48:    */  public DatatypeAttribute(QName qname, XSDatatype datatype)
/*  49:    */  {
/*  50: 50 */    this.qname = qname;
/*  51: 51 */    this.datatype = datatype;
/*  52:    */  }
/*  53:    */  
/*  54:    */  public DatatypeAttribute(QName qname, XSDatatype datatype, String text) {
/*  55: 55 */    this.qname = qname;
/*  56: 56 */    this.datatype = datatype;
/*  57: 57 */    this.text = text;
/*  58: 58 */    this.data = convertToValue(text);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public String toString() {
/*  62: 62 */    return getClass().getName() + hashCode() + " [Attribute: name " + getQualifiedName() + " value \"" + getValue() + "\" data: " + getData() + "]";
/*  63:    */  }
/*  64:    */  
/*  71:    */  public XSDatatype getXSDatatype()
/*  72:    */  {
/*  73: 73 */    return this.datatype;
/*  74:    */  }
/*  75:    */  
/*  77:    */  public String getNamespacePrefix(String uri)
/*  78:    */  {
/*  79: 79 */    Element parentElement = getParent();
/*  80:    */    
/*  81: 81 */    if (parentElement != null) {
/*  82: 82 */      Namespace namespace = parentElement.getNamespaceForURI(uri);
/*  83:    */      
/*  84: 84 */      if (namespace != null) {
/*  85: 85 */        return namespace.getPrefix();
/*  86:    */      }
/*  87:    */    }
/*  88:    */    
/*  89: 89 */    return null;
/*  90:    */  }
/*  91:    */  
/*  94:    */  public String getBaseUri()
/*  95:    */  {
/*  96: 96 */    return null;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public boolean isNotation(String notationName)
/* 100:    */  {
/* 101:101 */    return false;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public boolean isUnparsedEntity(String entityName)
/* 105:    */  {
/* 106:106 */    return true;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public String resolveNamespacePrefix(String prefix)
/* 110:    */  {
/* 111:111 */    if (prefix.equals(getNamespacePrefix())) {
/* 112:112 */      return getNamespaceURI();
/* 113:    */    }
/* 114:114 */    Element parentElement = getParent();
/* 115:    */    
/* 116:116 */    if (parentElement != null) {
/* 117:117 */      Namespace namespace = parentElement.getNamespaceForPrefix(prefix);
/* 118:    */      
/* 120:120 */      if (namespace != null) {
/* 121:121 */        return namespace.getURI();
/* 122:    */      }
/* 123:    */    }
/* 124:    */    
/* 126:126 */    return null;
/* 127:    */  }
/* 128:    */  
/* 130:    */  public QName getQName()
/* 131:    */  {
/* 132:132 */    return this.qname;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public String getValue() {
/* 136:136 */    return this.text;
/* 137:    */  }
/* 138:    */  
/* 139:    */  public void setValue(String value) {
/* 140:140 */    validate(value);
/* 141:    */    
/* 142:142 */    this.text = value;
/* 143:143 */    this.data = convertToValue(value);
/* 144:    */  }
/* 145:    */  
/* 146:    */  public Object getData() {
/* 147:147 */    return this.data;
/* 148:    */  }
/* 149:    */  
/* 150:    */  public void setData(Object data) {
/* 151:151 */    String s = this.datatype.convertToLexicalValue(data, this);
/* 152:152 */    validate(s);
/* 153:153 */    this.text = s;
/* 154:154 */    this.data = data;
/* 155:    */  }
/* 156:    */  
/* 157:    */  public Element getParent() {
/* 158:158 */    return this.parent;
/* 159:    */  }
/* 160:    */  
/* 161:    */  public void setParent(Element parent) {
/* 162:162 */    this.parent = parent;
/* 163:    */  }
/* 164:    */  
/* 165:    */  public boolean supportsParent() {
/* 166:166 */    return true;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public boolean isReadOnly() {
/* 170:170 */    return false;
/* 171:    */  }
/* 172:    */  
/* 173:    */  protected void validate(String txt) throws IllegalArgumentException
/* 174:    */  {
/* 175:    */    try
/* 176:    */    {
/* 177:177 */      this.datatype.checkValid(txt, this);
/* 178:    */    } catch (DatatypeException e) {
/* 179:179 */      throw new IllegalArgumentException(e.getMessage());
/* 180:    */    }
/* 181:    */  }
/* 182:    */  
/* 183:    */  protected Object convertToValue(String txt) {
/* 184:184 */    if ((this.datatype instanceof DatabindableDatatype)) {
/* 185:185 */      DatabindableDatatype bindable = this.datatype;
/* 186:    */      
/* 187:187 */      return bindable.createJavaObject(txt, this);
/* 188:    */    }
/* 189:189 */    return this.datatype.createValue(txt, this);
/* 190:    */  }
/* 191:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.DatatypeAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */