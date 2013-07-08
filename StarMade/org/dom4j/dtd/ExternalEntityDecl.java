/*   1:    */package org.dom4j.dtd;
/*   2:    */
/*   7:    */public class ExternalEntityDecl
/*   8:    */{
/*   9:    */  private String name;
/*  10:    */  
/*  14:    */  private String publicID;
/*  15:    */  
/*  19:    */  private String systemID;
/*  20:    */  
/*  25:    */  public ExternalEntityDecl() {}
/*  26:    */  
/*  31:    */  public ExternalEntityDecl(String name, String publicID, String systemID)
/*  32:    */  {
/*  33: 33 */    this.name = name;
/*  34: 34 */    this.publicID = publicID;
/*  35: 35 */    this.systemID = systemID;
/*  36:    */  }
/*  37:    */  
/*  42:    */  public String getName()
/*  43:    */  {
/*  44: 44 */    return this.name;
/*  45:    */  }
/*  46:    */  
/*  52:    */  public void setName(String name)
/*  53:    */  {
/*  54: 54 */    this.name = name;
/*  55:    */  }
/*  56:    */  
/*  61:    */  public String getPublicID()
/*  62:    */  {
/*  63: 63 */    return this.publicID;
/*  64:    */  }
/*  65:    */  
/*  71:    */  public void setPublicID(String publicID)
/*  72:    */  {
/*  73: 73 */    this.publicID = publicID;
/*  74:    */  }
/*  75:    */  
/*  80:    */  public String getSystemID()
/*  81:    */  {
/*  82: 82 */    return this.systemID;
/*  83:    */  }
/*  84:    */  
/*  90:    */  public void setSystemID(String systemID)
/*  91:    */  {
/*  92: 92 */    this.systemID = systemID;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public String toString() {
/*  96: 96 */    StringBuffer buffer = new StringBuffer("<!ENTITY ");
/*  97:    */    
/*  98: 98 */    if (this.name.startsWith("%")) {
/*  99: 99 */      buffer.append("% ");
/* 100:100 */      buffer.append(this.name.substring(1));
/* 101:    */    } else {
/* 102:102 */      buffer.append(this.name);
/* 103:    */    }
/* 104:    */    
/* 105:105 */    if (this.publicID != null) {
/* 106:106 */      buffer.append(" PUBLIC \"");
/* 107:107 */      buffer.append(this.publicID);
/* 108:108 */      buffer.append("\" ");
/* 109:    */      
/* 110:110 */      if (this.systemID != null) {
/* 111:111 */        buffer.append("\"");
/* 112:112 */        buffer.append(this.systemID);
/* 113:113 */        buffer.append("\" ");
/* 114:    */      }
/* 115:115 */    } else if (this.systemID != null) {
/* 116:116 */      buffer.append(" SYSTEM \"");
/* 117:117 */      buffer.append(this.systemID);
/* 118:118 */      buffer.append("\" ");
/* 119:    */    }
/* 120:    */    
/* 121:121 */    buffer.append(">");
/* 122:    */    
/* 123:123 */    return buffer.toString();
/* 124:    */  }
/* 125:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dtd.ExternalEntityDecl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */