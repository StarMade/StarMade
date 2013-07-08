/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import org.dom4j.CDATA;
/*  4:   */import org.dom4j.Element;
/*  5:   */import org.dom4j.Node;
/*  6:   */
/* 31:   */public class FlyweightCDATA
/* 32:   */  extends AbstractCDATA
/* 33:   */  implements CDATA
/* 34:   */{
/* 35:   */  protected String text;
/* 36:   */  
/* 37:   */  public FlyweightCDATA(String text)
/* 38:   */  {
/* 39:39 */    this.text = text;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public String getText() {
/* 43:43 */    return this.text;
/* 44:   */  }
/* 45:   */  
/* 46:   */  protected Node createXPathResult(Element parent) {
/* 47:47 */    return new DefaultCDATA(parent, getText());
/* 48:   */  }
/* 49:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightCDATA
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */