/*  1:   */package org.dom4j.dtd;
/*  2:   */
/*  8:   */public class ElementDecl
/*  9:   */{
/* 10:   */  private String name;
/* 11:   */  
/* 15:   */  private String model;
/* 16:   */  
/* 21:   */  public ElementDecl() {}
/* 22:   */  
/* 27:   */  public ElementDecl(String name, String model)
/* 28:   */  {
/* 29:29 */    this.name = name;
/* 30:30 */    this.model = model;
/* 31:   */  }
/* 32:   */  
/* 37:   */  public String getName()
/* 38:   */  {
/* 39:39 */    return this.name;
/* 40:   */  }
/* 41:   */  
/* 47:   */  public void setName(String name)
/* 48:   */  {
/* 49:49 */    this.name = name;
/* 50:   */  }
/* 51:   */  
/* 56:   */  public String getModel()
/* 57:   */  {
/* 58:58 */    return this.model;
/* 59:   */  }
/* 60:   */  
/* 66:   */  public void setModel(String model)
/* 67:   */  {
/* 68:68 */    this.model = model;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public String toString() {
/* 72:72 */    return "<!ELEMENT " + this.name + " " + this.model + ">";
/* 73:   */  }
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dtd.ElementDecl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */