/*  1:   */package org.dom4j.bean;
/*  2:   */
/*  3:   */import org.dom4j.Element;
/*  4:   */import org.dom4j.QName;
/*  5:   */import org.dom4j.tree.AbstractAttribute;
/*  6:   */
/* 23:   */public class BeanAttribute
/* 24:   */  extends AbstractAttribute
/* 25:   */{
/* 26:   */  private final BeanAttributeList beanList;
/* 27:   */  private final int index;
/* 28:   */  
/* 29:   */  public BeanAttribute(BeanAttributeList beanList, int index)
/* 30:   */  {
/* 31:31 */    this.beanList = beanList;
/* 32:32 */    this.index = index;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public QName getQName() {
/* 36:36 */    return this.beanList.getQName(this.index);
/* 37:   */  }
/* 38:   */  
/* 39:   */  public Element getParent() {
/* 40:40 */    return this.beanList.getParent();
/* 41:   */  }
/* 42:   */  
/* 43:   */  public String getValue() {
/* 44:44 */    Object data = getData();
/* 45:   */    
/* 46:46 */    return data != null ? data.toString() : null;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void setValue(String data) {
/* 50:50 */    this.beanList.setData(this.index, data);
/* 51:   */  }
/* 52:   */  
/* 53:   */  public Object getData() {
/* 54:54 */    return this.beanList.getData(this.index);
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void setData(Object data) {
/* 58:58 */    this.beanList.setData(this.index, data);
/* 59:   */  }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.bean.BeanAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */