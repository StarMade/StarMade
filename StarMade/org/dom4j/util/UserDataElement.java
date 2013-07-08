/*  1:   */package org.dom4j.util;
/*  2:   */
/*  3:   */import org.dom4j.DocumentFactory;
/*  4:   */import org.dom4j.Element;
/*  5:   */import org.dom4j.QName;
/*  6:   */import org.dom4j.tree.DefaultElement;
/*  7:   */
/* 24:   */public class UserDataElement
/* 25:   */  extends DefaultElement
/* 26:   */{
/* 27:   */  private Object data;
/* 28:   */  
/* 29:   */  public UserDataElement(String name)
/* 30:   */  {
/* 31:31 */    super(name);
/* 32:   */  }
/* 33:   */  
/* 34:   */  public UserDataElement(QName qname) {
/* 35:35 */    super(qname);
/* 36:   */  }
/* 37:   */  
/* 38:   */  public Object getData() {
/* 39:39 */    return this.data;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public void setData(Object data) {
/* 43:43 */    this.data = data;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public String toString() {
/* 47:47 */    return super.toString() + " userData: " + this.data;
/* 48:   */  }
/* 49:   */  
/* 50:   */  public Object clone() {
/* 51:51 */    UserDataElement answer = (UserDataElement)super.clone();
/* 52:   */    
/* 53:53 */    if (answer != this) {
/* 54:54 */      answer.data = getCopyOfUserData();
/* 55:   */    }
/* 56:   */    
/* 57:57 */    return answer;
/* 58:   */  }
/* 59:   */  
/* 69:   */  protected Object getCopyOfUserData()
/* 70:   */  {
/* 71:71 */    return this.data;
/* 72:   */  }
/* 73:   */  
/* 74:   */  protected Element createElement(String name) {
/* 75:75 */    Element answer = getDocumentFactory().createElement(name);
/* 76:76 */    answer.setData(getCopyOfUserData());
/* 77:   */    
/* 78:78 */    return answer;
/* 79:   */  }
/* 80:   */  
/* 81:   */  protected Element createElement(QName qName) {
/* 82:82 */    Element answer = getDocumentFactory().createElement(qName);
/* 83:83 */    answer.setData(getCopyOfUserData());
/* 84:   */    
/* 85:85 */    return answer;
/* 86:   */  }
/* 87:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.UserDataElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */