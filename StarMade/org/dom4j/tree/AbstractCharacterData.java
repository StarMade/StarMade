/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import org.dom4j.CharacterData;
/*  4:   */import org.dom4j.Element;
/*  5:   */
/* 22:   */public abstract class AbstractCharacterData
/* 23:   */  extends AbstractNode
/* 24:   */  implements CharacterData
/* 25:   */{
/* 26:   */  public String getPath(Element context)
/* 27:   */  {
/* 28:28 */    Element parent = getParent();
/* 29:   */    
/* 30:30 */    return (parent != null) && (parent != context) ? parent.getPath(context) + "/text()" : "text()";
/* 31:   */  }
/* 32:   */  
/* 33:   */  public String getUniquePath(Element context)
/* 34:   */  {
/* 35:35 */    Element parent = getParent();
/* 36:   */    
/* 37:37 */    return (parent != null) && (parent != context) ? parent.getUniquePath(context) + "/text()" : "text()";
/* 38:   */  }
/* 39:   */  
/* 40:   */  public void appendText(String text)
/* 41:   */  {
/* 42:42 */    setText(getText() + text);
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractCharacterData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */