/*   1:    */package org.dom4j.datatype;
/*   2:    */
/*   3:    */import com.sun.msv.datatype.DatabindableDatatype;
/*   4:    */import com.sun.msv.datatype.SerializationContext;
/*   5:    */import com.sun.msv.datatype.xsd.XSDatatype;
/*   6:    */import org.dom4j.Element;
/*   7:    */import org.dom4j.Namespace;
/*   8:    */import org.dom4j.Node;
/*   9:    */import org.dom4j.QName;
/*  10:    */import org.dom4j.tree.DefaultElement;
/*  11:    */import org.relaxng.datatype.DatatypeException;
/*  12:    */import org.relaxng.datatype.ValidationContext;
/*  13:    */
/*  33:    */public class DatatypeElement
/*  34:    */  extends DefaultElement
/*  35:    */  implements SerializationContext, ValidationContext
/*  36:    */{
/*  37:    */  private XSDatatype datatype;
/*  38:    */  private Object data;
/*  39:    */  
/*  40:    */  public DatatypeElement(QName qname, XSDatatype datatype)
/*  41:    */  {
/*  42: 42 */    super(qname);
/*  43: 43 */    this.datatype = datatype;
/*  44:    */  }
/*  45:    */  
/*  46:    */  public DatatypeElement(QName qname, int attributeCount, XSDatatype type) {
/*  47: 47 */    super(qname, attributeCount);
/*  48: 48 */    this.datatype = type;
/*  49:    */  }
/*  50:    */  
/*  51:    */  public String toString() {
/*  52: 52 */    return getClass().getName() + hashCode() + " [Element: <" + getQualifiedName() + " attributes: " + attributeList() + " data: " + getData() + " />]";
/*  53:    */  }
/*  54:    */  
/*  61:    */  public XSDatatype getXSDatatype()
/*  62:    */  {
/*  63: 63 */    return this.datatype;
/*  64:    */  }
/*  65:    */  
/*  67:    */  public String getNamespacePrefix(String uri)
/*  68:    */  {
/*  69: 69 */    Namespace namespace = getNamespaceForURI(uri);
/*  70:    */    
/*  71: 71 */    return namespace != null ? namespace.getPrefix() : null;
/*  72:    */  }
/*  73:    */  
/*  76:    */  public String getBaseUri()
/*  77:    */  {
/*  78: 78 */    return null;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public boolean isNotation(String notationName)
/*  82:    */  {
/*  83: 83 */    return false;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public boolean isUnparsedEntity(String entityName)
/*  87:    */  {
/*  88: 88 */    return true;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public String resolveNamespacePrefix(String prefix) {
/*  92: 92 */    Namespace namespace = getNamespaceForPrefix(prefix);
/*  93:    */    
/*  94: 94 */    if (namespace != null) {
/*  95: 95 */      return namespace.getURI();
/*  96:    */    }
/*  97:    */    
/*  98: 98 */    return null;
/*  99:    */  }
/* 100:    */  
/* 102:    */  public Object getData()
/* 103:    */  {
/* 104:104 */    if (this.data == null) {
/* 105:105 */      String text = getTextTrim();
/* 106:    */      
/* 107:107 */      if ((text != null) && (text.length() > 0)) {
/* 108:108 */        if ((this.datatype instanceof DatabindableDatatype)) {
/* 109:109 */          DatabindableDatatype bind = this.datatype;
/* 110:110 */          this.data = bind.createJavaObject(text, this);
/* 111:    */        } else {
/* 112:112 */          this.data = this.datatype.createValue(text, this);
/* 113:    */        }
/* 114:    */      }
/* 115:    */    }
/* 116:    */    
/* 117:117 */    return this.data;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void setData(Object data) {
/* 121:121 */    String s = this.datatype.convertToLexicalValue(data, this);
/* 122:122 */    validate(s);
/* 123:123 */    this.data = data;
/* 124:124 */    setText(s);
/* 125:    */  }
/* 126:    */  
/* 127:    */  public Element addText(String text) {
/* 128:128 */    validate(text);
/* 129:    */    
/* 130:130 */    return super.addText(text);
/* 131:    */  }
/* 132:    */  
/* 133:    */  public void setText(String text) {
/* 134:134 */    validate(text);
/* 135:135 */    super.setText(text);
/* 136:    */  }
/* 137:    */  
/* 146:    */  protected void childAdded(Node node)
/* 147:    */  {
/* 148:148 */    this.data = null;
/* 149:149 */    super.childAdded(node);
/* 150:    */  }
/* 151:    */  
/* 157:    */  protected void childRemoved(Node node)
/* 158:    */  {
/* 159:159 */    this.data = null;
/* 160:160 */    super.childRemoved(node);
/* 161:    */  }
/* 162:    */  
/* 163:    */  protected void validate(String text) throws IllegalArgumentException {
/* 164:    */    try {
/* 165:165 */      this.datatype.checkValid(text, this);
/* 166:    */    } catch (DatatypeException e) {
/* 167:167 */      throw new IllegalArgumentException(e.getMessage());
/* 168:    */    }
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.DatatypeElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */