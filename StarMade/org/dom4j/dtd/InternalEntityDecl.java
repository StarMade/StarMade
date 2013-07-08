/*   1:    */package org.dom4j.dtd;
/*   2:    */
/*   8:    */public class InternalEntityDecl
/*   9:    */{
/*  10:    */  private String name;
/*  11:    */  
/*  16:    */  private String value;
/*  17:    */  
/*  22:    */  public InternalEntityDecl() {}
/*  23:    */  
/*  28:    */  public InternalEntityDecl(String name, String value)
/*  29:    */  {
/*  30: 30 */    this.name = name;
/*  31: 31 */    this.value = value;
/*  32:    */  }
/*  33:    */  
/*  38:    */  public String getName()
/*  39:    */  {
/*  40: 40 */    return this.name;
/*  41:    */  }
/*  42:    */  
/*  48:    */  public void setName(String name)
/*  49:    */  {
/*  50: 50 */    this.name = name;
/*  51:    */  }
/*  52:    */  
/*  57:    */  public String getValue()
/*  58:    */  {
/*  59: 59 */    return this.value;
/*  60:    */  }
/*  61:    */  
/*  67:    */  public void setValue(String value)
/*  68:    */  {
/*  69: 69 */    this.value = value;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public String toString() {
/*  73: 73 */    StringBuffer buffer = new StringBuffer("<!ENTITY ");
/*  74:    */    
/*  75: 75 */    if (this.name.startsWith("%")) {
/*  76: 76 */      buffer.append("% ");
/*  77: 77 */      buffer.append(this.name.substring(1));
/*  78:    */    } else {
/*  79: 79 */      buffer.append(this.name);
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    buffer.append(" \"");
/*  83: 83 */    buffer.append(escapeEntityValue(this.value));
/*  84: 84 */    buffer.append("\">");
/*  85:    */    
/*  86: 86 */    return buffer.toString();
/*  87:    */  }
/*  88:    */  
/*  89:    */  private String escapeEntityValue(String text) {
/*  90: 90 */    StringBuffer result = new StringBuffer();
/*  91:    */    
/*  92: 92 */    for (int i = 0; i < text.length(); i++) {
/*  93: 93 */      char c = text.charAt(i);
/*  94:    */      
/*  95: 95 */      switch (c) {
/*  96:    */      case '<': 
/*  97: 97 */        result.append("&#38;#60;");
/*  98:    */        
/*  99: 99 */        break;
/* 100:    */      
/* 101:    */      case '>': 
/* 102:102 */        result.append("&#62;");
/* 103:    */        
/* 104:104 */        break;
/* 105:    */      
/* 106:    */      case '&': 
/* 107:107 */        result.append("&#38;#38;");
/* 108:    */        
/* 109:109 */        break;
/* 110:    */      
/* 111:    */      case '\'': 
/* 112:112 */        result.append("&#39;");
/* 113:    */        
/* 114:114 */        break;
/* 115:    */      
/* 116:    */      case '"': 
/* 117:117 */        result.append("&#34;");
/* 118:    */        
/* 119:119 */        break;
/* 120:    */      
/* 122:    */      default: 
/* 123:123 */        if (c < ' ') {
/* 124:124 */          result.append("&#" + c + ";");
/* 125:    */        } else {
/* 126:126 */          result.append(c);
/* 127:    */        }
/* 128:    */        
/* 129:    */        break;
/* 130:    */      }
/* 131:    */      
/* 132:    */    }
/* 133:133 */    return result.toString();
/* 134:    */  }
/* 135:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dtd.InternalEntityDecl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */