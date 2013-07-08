/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import org.dom4j.Element;
/*  4:   */
/* 26:   */public class DefaultEntity
/* 27:   */  extends FlyweightEntity
/* 28:   */{
/* 29:   */  private Element parent;
/* 30:   */  
/* 31:   */  public DefaultEntity(String name)
/* 32:   */  {
/* 33:33 */    super(name);
/* 34:   */  }
/* 35:   */  
/* 43:   */  public DefaultEntity(String name, String text)
/* 44:   */  {
/* 45:45 */    super(name, text);
/* 46:   */  }
/* 47:   */  
/* 57:   */  public DefaultEntity(Element parent, String name, String text)
/* 58:   */  {
/* 59:59 */    super(name, text);
/* 60:60 */    this.parent = parent;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public void setName(String name) {
/* 64:64 */    this.name = name;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public void setText(String text) {
/* 68:68 */    this.text = text;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public Element getParent() {
/* 72:72 */    return this.parent;
/* 73:   */  }
/* 74:   */  
/* 75:   */  public void setParent(Element parent) {
/* 76:76 */    this.parent = parent;
/* 77:   */  }
/* 78:   */  
/* 79:   */  public boolean supportsParent() {
/* 80:80 */    return true;
/* 81:   */  }
/* 82:   */  
/* 83:   */  public boolean isReadOnly() {
/* 84:84 */    return false;
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultEntity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */