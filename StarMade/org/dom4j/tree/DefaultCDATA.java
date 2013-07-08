/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import org.dom4j.Element;
/*  4:   */
/* 26:   */public class DefaultCDATA
/* 27:   */  extends FlyweightCDATA
/* 28:   */{
/* 29:   */  private Element parent;
/* 30:   */  
/* 31:   */  public DefaultCDATA(String text)
/* 32:   */  {
/* 33:33 */    super(text);
/* 34:   */  }
/* 35:   */  
/* 43:   */  public DefaultCDATA(Element parent, String text)
/* 44:   */  {
/* 45:45 */    super(text);
/* 46:46 */    this.parent = parent;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void setText(String text) {
/* 50:50 */    this.text = text;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public Element getParent() {
/* 54:54 */    return this.parent;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void setParent(Element parent) {
/* 58:58 */    this.parent = parent;
/* 59:   */  }
/* 60:   */  
/* 61:   */  public boolean supportsParent() {
/* 62:62 */    return true;
/* 63:   */  }
/* 64:   */  
/* 65:   */  public boolean isReadOnly() {
/* 66:66 */    return false;
/* 67:   */  }
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultCDATA
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */