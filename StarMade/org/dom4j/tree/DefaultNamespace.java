/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import org.dom4j.Element;
/*   4:    */import org.dom4j.Namespace;
/*   5:    */
/*  29:    */public class DefaultNamespace
/*  30:    */  extends Namespace
/*  31:    */{
/*  32:    */  private Element parent;
/*  33:    */  
/*  34:    */  public DefaultNamespace(String prefix, String uri)
/*  35:    */  {
/*  36: 36 */    super(prefix, uri);
/*  37:    */  }
/*  38:    */  
/*  48:    */  public DefaultNamespace(Element parent, String prefix, String uri)
/*  49:    */  {
/*  50: 50 */    super(prefix, uri);
/*  51: 51 */    this.parent = parent;
/*  52:    */  }
/*  53:    */  
/*  59:    */  protected int createHashCode()
/*  60:    */  {
/*  61: 61 */    int hashCode = super.createHashCode();
/*  62:    */    
/*  63: 63 */    if (this.parent != null) {
/*  64: 64 */      hashCode ^= this.parent.hashCode();
/*  65:    */    }
/*  66:    */    
/*  67: 67 */    return hashCode;
/*  68:    */  }
/*  69:    */  
/*  78:    */  public boolean equals(Object object)
/*  79:    */  {
/*  80: 80 */    if ((object instanceof DefaultNamespace)) {
/*  81: 81 */      DefaultNamespace that = (DefaultNamespace)object;
/*  82:    */      
/*  83: 83 */      if (that.parent == this.parent) {
/*  84: 84 */        return super.equals(object);
/*  85:    */      }
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    return false;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public int hashCode() {
/*  92: 92 */    return super.hashCode();
/*  93:    */  }
/*  94:    */  
/*  95:    */  public Element getParent() {
/*  96: 96 */    return this.parent;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public void setParent(Element parent) {
/* 100:100 */    this.parent = parent;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public boolean supportsParent() {
/* 104:104 */    return true;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public boolean isReadOnly() {
/* 108:108 */    return false;
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultNamespace
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */