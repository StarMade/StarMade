/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.Writer;
/*  5:   */import org.dom4j.Text;
/*  6:   */import org.dom4j.Visitor;
/*  7:   */
/* 24:   */public abstract class AbstractText
/* 25:   */  extends AbstractCharacterData
/* 26:   */  implements Text
/* 27:   */{
/* 28:   */  public short getNodeType()
/* 29:   */  {
/* 30:30 */    return 3;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public String toString() {
/* 34:34 */    return super.toString() + " [Text: \"" + getText() + "\"]";
/* 35:   */  }
/* 36:   */  
/* 37:   */  public String asXML() {
/* 38:38 */    return getText();
/* 39:   */  }
/* 40:   */  
/* 41:   */  public void write(Writer writer) throws IOException {
/* 42:42 */    writer.write(getText());
/* 43:   */  }
/* 44:   */  
/* 45:   */  public void accept(Visitor visitor) {
/* 46:46 */    visitor.visit(this);
/* 47:   */  }
/* 48:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractText
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */