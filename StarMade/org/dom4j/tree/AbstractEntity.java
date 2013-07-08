/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.Writer;
/*  5:   */import org.dom4j.Element;
/*  6:   */import org.dom4j.Entity;
/*  7:   */import org.dom4j.Visitor;
/*  8:   */
/* 25:   */public abstract class AbstractEntity
/* 26:   */  extends AbstractNode
/* 27:   */  implements Entity
/* 28:   */{
/* 29:   */  public short getNodeType()
/* 30:   */  {
/* 31:31 */    return 5;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public String getPath(Element context)
/* 35:   */  {
/* 36:36 */    Element parent = getParent();
/* 37:   */    
/* 38:38 */    return (parent != null) && (parent != context) ? parent.getPath(context) + "/text()" : "text()";
/* 39:   */  }
/* 40:   */  
/* 42:   */  public String getUniquePath(Element context)
/* 43:   */  {
/* 44:44 */    Element parent = getParent();
/* 45:   */    
/* 46:46 */    return (parent != null) && (parent != context) ? parent.getUniquePath(context) + "/text()" : "text()";
/* 47:   */  }
/* 48:   */  
/* 49:   */  public String toString()
/* 50:   */  {
/* 51:51 */    return super.toString() + " [Entity: &" + getName() + ";]";
/* 52:   */  }
/* 53:   */  
/* 54:   */  public String getStringValue() {
/* 55:55 */    return "&" + getName() + ";";
/* 56:   */  }
/* 57:   */  
/* 58:   */  public String asXML() {
/* 59:59 */    return "&" + getName() + ";";
/* 60:   */  }
/* 61:   */  
/* 62:   */  public void write(Writer writer) throws IOException {
/* 63:63 */    writer.write("&");
/* 64:64 */    writer.write(getName());
/* 65:65 */    writer.write(";");
/* 66:   */  }
/* 67:   */  
/* 68:   */  public void accept(Visitor visitor) {
/* 69:69 */    visitor.visit(this);
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractEntity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */