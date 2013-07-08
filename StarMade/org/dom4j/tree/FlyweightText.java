/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import org.dom4j.Element;
/*  4:   */import org.dom4j.Node;
/*  5:   */import org.dom4j.Text;
/*  6:   */
/* 31:   */public class FlyweightText
/* 32:   */  extends AbstractText
/* 33:   */  implements Text
/* 34:   */{
/* 35:   */  protected String text;
/* 36:   */  
/* 37:   */  public FlyweightText(String text)
/* 38:   */  {
/* 39:39 */    this.text = text;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public String getText() {
/* 43:43 */    return this.text;
/* 44:   */  }
/* 45:   */  
/* 46:   */  protected Node createXPathResult(Element parent) {
/* 47:47 */    return new DefaultText(parent, getText());
/* 48:   */  }
/* 49:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightText
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */