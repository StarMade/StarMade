/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.StringWriter;
/*  5:   */import java.io.Writer;
/*  6:   */import org.dom4j.CDATA;
/*  7:   */import org.dom4j.Visitor;
/*  8:   */
/* 26:   */public abstract class AbstractCDATA
/* 27:   */  extends AbstractCharacterData
/* 28:   */  implements CDATA
/* 29:   */{
/* 30:   */  public short getNodeType()
/* 31:   */  {
/* 32:32 */    return 4;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public String toString() {
/* 36:36 */    return super.toString() + " [CDATA: \"" + getText() + "\"]";
/* 37:   */  }
/* 38:   */  
/* 39:   */  public String asXML() {
/* 40:40 */    StringWriter writer = new StringWriter();
/* 41:   */    try
/* 42:   */    {
/* 43:43 */      write(writer);
/* 44:   */    }
/* 45:   */    catch (IOException e) {}
/* 46:   */    
/* 48:48 */    return writer.toString();
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void write(Writer writer) throws IOException {
/* 52:52 */    writer.write("<![CDATA[");
/* 53:   */    
/* 54:54 */    if (getText() != null) {
/* 55:55 */      writer.write(getText());
/* 56:   */    }
/* 57:   */    
/* 58:58 */    writer.write("]]>");
/* 59:   */  }
/* 60:   */  
/* 61:   */  public void accept(Visitor visitor) {
/* 62:62 */    visitor.visit(this);
/* 63:   */  }
/* 64:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractCDATA
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */