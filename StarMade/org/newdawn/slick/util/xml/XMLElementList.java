/*  1:   */package org.newdawn.slick.util.xml;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import java.util.Collection;
/*  5:   */
/* 11:   */public class XMLElementList
/* 12:   */{
/* 13:13 */  private ArrayList list = new ArrayList();
/* 14:   */  
/* 26:   */  public void add(XMLElement element)
/* 27:   */  {
/* 28:28 */    this.list.add(element);
/* 29:   */  }
/* 30:   */  
/* 35:   */  public int size()
/* 36:   */  {
/* 37:37 */    return this.list.size();
/* 38:   */  }
/* 39:   */  
/* 45:   */  public XMLElement get(int i)
/* 46:   */  {
/* 47:47 */    return (XMLElement)this.list.get(i);
/* 48:   */  }
/* 49:   */  
/* 55:   */  public boolean contains(XMLElement element)
/* 56:   */  {
/* 57:57 */    return this.list.contains(element);
/* 58:   */  }
/* 59:   */  
/* 64:   */  public void addAllTo(Collection collection)
/* 65:   */  {
/* 66:66 */    collection.addAll(this.list);
/* 67:   */  }
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.xml.XMLElementList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */