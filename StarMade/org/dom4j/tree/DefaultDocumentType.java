/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */
/*  39:    */public class DefaultDocumentType
/*  40:    */  extends AbstractDocumentType
/*  41:    */{
/*  42:    */  protected String elementName;
/*  43:    */  private String publicID;
/*  44:    */  private String systemID;
/*  45:    */  private List internalDeclarations;
/*  46:    */  private List externalDeclarations;
/*  47:    */  
/*  48:    */  public DefaultDocumentType() {}
/*  49:    */  
/*  50:    */  public DefaultDocumentType(String elementName, String systemID)
/*  51:    */  {
/*  52: 52 */    this.elementName = elementName;
/*  53: 53 */    this.systemID = systemID;
/*  54:    */  }
/*  55:    */  
/*  69:    */  public DefaultDocumentType(String elementName, String publicID, String systemID)
/*  70:    */  {
/*  71: 71 */    this.elementName = elementName;
/*  72: 72 */    this.publicID = publicID;
/*  73: 73 */    this.systemID = systemID;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public String getElementName() {
/*  77: 77 */    return this.elementName;
/*  78:    */  }
/*  79:    */  
/*  80:    */  public void setElementName(String elementName) {
/*  81: 81 */    this.elementName = elementName;
/*  82:    */  }
/*  83:    */  
/*  88:    */  public String getPublicID()
/*  89:    */  {
/*  90: 90 */    return this.publicID;
/*  91:    */  }
/*  92:    */  
/*  98:    */  public void setPublicID(String publicID)
/*  99:    */  {
/* 100:100 */    this.publicID = publicID;
/* 101:    */  }
/* 102:    */  
/* 107:    */  public String getSystemID()
/* 108:    */  {
/* 109:109 */    return this.systemID;
/* 110:    */  }
/* 111:    */  
/* 117:    */  public void setSystemID(String systemID)
/* 118:    */  {
/* 119:119 */    this.systemID = systemID;
/* 120:    */  }
/* 121:    */  
/* 122:    */  public List getInternalDeclarations() {
/* 123:123 */    return this.internalDeclarations;
/* 124:    */  }
/* 125:    */  
/* 126:    */  public void setInternalDeclarations(List internalDeclarations) {
/* 127:127 */    this.internalDeclarations = internalDeclarations;
/* 128:    */  }
/* 129:    */  
/* 130:    */  public List getExternalDeclarations() {
/* 131:131 */    return this.externalDeclarations;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public void setExternalDeclarations(List externalDeclarations) {
/* 135:135 */    this.externalDeclarations = externalDeclarations;
/* 136:    */  }
/* 137:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultDocumentType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */