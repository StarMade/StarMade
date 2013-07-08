/*   1:    */package org.dom4j.dtd;
/*   2:    */
/*   7:    */public class AttributeDecl
/*   8:    */{
/*   9:    */  private String elementName;
/*  10:    */  
/*  14:    */  private String attributeName;
/*  15:    */  
/*  19:    */  private String type;
/*  20:    */  
/*  23:    */  private String value;
/*  24:    */  
/*  27:    */  private String valueDefault;
/*  28:    */  
/*  32:    */  public AttributeDecl() {}
/*  33:    */  
/*  37:    */  public AttributeDecl(String elementName, String attributeName, String type, String valueDefault, String value)
/*  38:    */  {
/*  39: 39 */    this.elementName = elementName;
/*  40: 40 */    this.attributeName = attributeName;
/*  41: 41 */    this.type = type;
/*  42: 42 */    this.value = value;
/*  43: 43 */    this.valueDefault = valueDefault;
/*  44:    */  }
/*  45:    */  
/*  50:    */  public String getElementName()
/*  51:    */  {
/*  52: 52 */    return this.elementName;
/*  53:    */  }
/*  54:    */  
/*  60:    */  public void setElementName(String elementName)
/*  61:    */  {
/*  62: 62 */    this.elementName = elementName;
/*  63:    */  }
/*  64:    */  
/*  69:    */  public String getAttributeName()
/*  70:    */  {
/*  71: 71 */    return this.attributeName;
/*  72:    */  }
/*  73:    */  
/*  79:    */  public void setAttributeName(String attributeName)
/*  80:    */  {
/*  81: 81 */    this.attributeName = attributeName;
/*  82:    */  }
/*  83:    */  
/*  88:    */  public String getType()
/*  89:    */  {
/*  90: 90 */    return this.type;
/*  91:    */  }
/*  92:    */  
/*  98:    */  public void setType(String type)
/*  99:    */  {
/* 100:100 */    this.type = type;
/* 101:    */  }
/* 102:    */  
/* 107:    */  public String getValue()
/* 108:    */  {
/* 109:109 */    return this.value;
/* 110:    */  }
/* 111:    */  
/* 117:    */  public void setValue(String value)
/* 118:    */  {
/* 119:119 */    this.value = value;
/* 120:    */  }
/* 121:    */  
/* 126:    */  public String getValueDefault()
/* 127:    */  {
/* 128:128 */    return this.valueDefault;
/* 129:    */  }
/* 130:    */  
/* 136:    */  public void setValueDefault(String valueDefault)
/* 137:    */  {
/* 138:138 */    this.valueDefault = valueDefault;
/* 139:    */  }
/* 140:    */  
/* 141:    */  public String toString() {
/* 142:142 */    StringBuffer buffer = new StringBuffer("<!ATTLIST ");
/* 143:143 */    buffer.append(this.elementName);
/* 144:144 */    buffer.append(" ");
/* 145:145 */    buffer.append(this.attributeName);
/* 146:146 */    buffer.append(" ");
/* 147:147 */    buffer.append(this.type);
/* 148:148 */    buffer.append(" ");
/* 149:    */    
/* 150:150 */    if (this.valueDefault != null) {
/* 151:151 */      buffer.append(this.valueDefault);
/* 152:    */      
/* 153:153 */      if (this.valueDefault.equals("#FIXED")) {
/* 154:154 */        buffer.append(" \"");
/* 155:155 */        buffer.append(this.value);
/* 156:156 */        buffer.append("\"");
/* 157:    */      }
/* 158:    */    } else {
/* 159:159 */      buffer.append("\"");
/* 160:160 */      buffer.append(this.value);
/* 161:161 */      buffer.append("\"");
/* 162:    */    }
/* 163:    */    
/* 164:164 */    buffer.append(">");
/* 165:    */    
/* 166:166 */    return buffer.toString();
/* 167:    */  }
/* 168:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dtd.AttributeDecl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */