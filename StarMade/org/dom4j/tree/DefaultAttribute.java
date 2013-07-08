/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import org.dom4j.Element;
/*   4:    */import org.dom4j.Namespace;
/*   5:    */import org.dom4j.QName;
/*   6:    */
/*  21:    */public class DefaultAttribute
/*  22:    */  extends FlyweightAttribute
/*  23:    */{
/*  24:    */  private Element parent;
/*  25:    */  
/*  26:    */  public DefaultAttribute(QName qname)
/*  27:    */  {
/*  28: 28 */    super(qname);
/*  29:    */  }
/*  30:    */  
/*  31:    */  public DefaultAttribute(QName qname, String value) {
/*  32: 32 */    super(qname, value);
/*  33:    */  }
/*  34:    */  
/*  35:    */  public DefaultAttribute(Element parent, QName qname, String value) {
/*  36: 36 */    super(qname, value);
/*  37: 37 */    this.parent = parent;
/*  38:    */  }
/*  39:    */  
/*  48:    */  public DefaultAttribute(String name, String value)
/*  49:    */  {
/*  50: 50 */    super(name, value);
/*  51:    */  }
/*  52:    */  
/*  63:    */  public DefaultAttribute(String name, String value, Namespace namespace)
/*  64:    */  {
/*  65: 65 */    super(name, value, namespace);
/*  66:    */  }
/*  67:    */  
/*  81:    */  public DefaultAttribute(Element parent, String name, String value, Namespace namespace)
/*  82:    */  {
/*  83: 83 */    super(name, value, namespace);
/*  84: 84 */    this.parent = parent;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public void setValue(String value) {
/*  88: 88 */    this.value = value;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public Element getParent() {
/*  92: 92 */    return this.parent;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public void setParent(Element parent) {
/*  96: 96 */    this.parent = parent;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public boolean supportsParent() {
/* 100:100 */    return true;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public boolean isReadOnly() {
/* 104:104 */    return false;
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */