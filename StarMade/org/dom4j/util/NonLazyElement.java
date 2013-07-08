/*  1:   */package org.dom4j.util;
/*  2:   */
/*  3:   */import org.dom4j.Namespace;
/*  4:   */import org.dom4j.QName;
/*  5:   */import org.dom4j.tree.BaseElement;
/*  6:   */
/* 20:   */public class NonLazyElement
/* 21:   */  extends BaseElement
/* 22:   */{
/* 23:   */  public NonLazyElement(String name)
/* 24:   */  {
/* 25:25 */    super(name);
/* 26:26 */    this.attributes = createAttributeList();
/* 27:27 */    this.content = createContentList();
/* 28:   */  }
/* 29:   */  
/* 30:   */  public NonLazyElement(QName qname) {
/* 31:31 */    super(qname);
/* 32:32 */    this.attributes = createAttributeList();
/* 33:33 */    this.content = createContentList();
/* 34:   */  }
/* 35:   */  
/* 36:   */  public NonLazyElement(String name, Namespace namespace) {
/* 37:37 */    super(name, namespace);
/* 38:38 */    this.attributes = createAttributeList();
/* 39:39 */    this.content = createContentList();
/* 40:   */  }
/* 41:   */  
/* 42:   */  public NonLazyElement(QName qname, int attributeCount) {
/* 43:43 */    super(qname);
/* 44:44 */    this.attributes = createAttributeList(attributeCount);
/* 45:45 */    this.content = createContentList();
/* 46:   */  }
/* 47:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.NonLazyElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */