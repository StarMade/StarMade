/*  1:   */package org.dom4j.util;
/*  2:   */
/*  3:   */import org.dom4j.QName;
/*  4:   */import org.dom4j.tree.DefaultAttribute;
/*  5:   */
/* 23:   */public class UserDataAttribute
/* 24:   */  extends DefaultAttribute
/* 25:   */{
/* 26:   */  private Object data;
/* 27:   */  
/* 28:   */  public UserDataAttribute(QName qname)
/* 29:   */  {
/* 30:30 */    super(qname);
/* 31:   */  }
/* 32:   */  
/* 33:   */  public UserDataAttribute(QName qname, String text) {
/* 34:34 */    super(qname, text);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public Object getData() {
/* 38:38 */    return this.data;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public void setData(Object data) {
/* 42:42 */    this.data = data;
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.UserDataAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */