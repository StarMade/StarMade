/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import org.dom4j.DocumentFactory;
/*  4:   */import org.dom4j.Namespace;
/*  5:   */import org.dom4j.QName;
/*  6:   */
/* 27:   */public class FlyweightAttribute
/* 28:   */  extends AbstractAttribute
/* 29:   */{
/* 30:   */  private QName qname;
/* 31:   */  protected String value;
/* 32:   */  
/* 33:   */  public FlyweightAttribute(QName qname)
/* 34:   */  {
/* 35:35 */    this.qname = qname;
/* 36:   */  }
/* 37:   */  
/* 38:   */  public FlyweightAttribute(QName qname, String value) {
/* 39:39 */    this.qname = qname;
/* 40:40 */    this.value = value;
/* 41:   */  }
/* 42:   */  
/* 51:   */  public FlyweightAttribute(String name, String value)
/* 52:   */  {
/* 53:53 */    this.qname = getDocumentFactory().createQName(name);
/* 54:54 */    this.value = value;
/* 55:   */  }
/* 56:   */  
/* 67:   */  public FlyweightAttribute(String name, String value, Namespace namespace)
/* 68:   */  {
/* 69:69 */    this.qname = getDocumentFactory().createQName(name, namespace);
/* 70:70 */    this.value = value;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public String getValue() {
/* 74:74 */    return this.value;
/* 75:   */  }
/* 76:   */  
/* 77:   */  public QName getQName() {
/* 78:78 */    return this.qname;
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */