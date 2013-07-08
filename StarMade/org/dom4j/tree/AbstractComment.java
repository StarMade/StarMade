/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.Writer;
/*  5:   */import org.dom4j.Comment;
/*  6:   */import org.dom4j.Element;
/*  7:   */import org.dom4j.Visitor;
/*  8:   */
/* 26:   */public abstract class AbstractComment
/* 27:   */  extends AbstractCharacterData
/* 28:   */  implements Comment
/* 29:   */{
/* 30:   */  public short getNodeType()
/* 31:   */  {
/* 32:32 */    return 8;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public String getPath(Element context) {
/* 36:36 */    Element parent = getParent();
/* 37:   */    
/* 38:38 */    return (parent != null) && (parent != context) ? parent.getPath(context) + "/comment()" : "comment()";
/* 39:   */  }
/* 40:   */  
/* 41:   */  public String getUniquePath(Element context)
/* 42:   */  {
/* 43:43 */    Element parent = getParent();
/* 44:   */    
/* 45:45 */    return (parent != null) && (parent != context) ? parent.getUniquePath(context) + "/comment()" : "comment()";
/* 46:   */  }
/* 47:   */  
/* 48:   */  public String toString()
/* 49:   */  {
/* 50:50 */    return super.toString() + " [Comment: \"" + getText() + "\"]";
/* 51:   */  }
/* 52:   */  
/* 53:   */  public String asXML() {
/* 54:54 */    return "<!--" + getText() + "-->";
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void write(Writer writer) throws IOException {
/* 58:58 */    writer.write("<!--");
/* 59:59 */    writer.write(getText());
/* 60:60 */    writer.write("-->");
/* 61:   */  }
/* 62:   */  
/* 63:   */  public void accept(Visitor visitor) {
/* 64:64 */    visitor.visit(this);
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractComment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */