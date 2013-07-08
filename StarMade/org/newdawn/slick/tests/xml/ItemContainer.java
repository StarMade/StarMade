/*  1:   */package org.newdawn.slick.tests.xml;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.ArrayList;
/*  5:   */
/*  9:   */public class ItemContainer
/* 10:   */  extends Item
/* 11:   */{
/* 12:12 */  private ArrayList items = new ArrayList();
/* 13:   */  
/* 18:   */  private void add(Item item)
/* 19:   */  {
/* 20:20 */    this.items.add(item);
/* 21:   */  }
/* 22:   */  
/* 29:   */  private void setName(String name)
/* 30:   */  {
/* 31:31 */    this.name = name;
/* 32:   */  }
/* 33:   */  
/* 40:   */  private void setCondition(int condition)
/* 41:   */  {
/* 42:42 */    this.condition = condition;
/* 43:   */  }
/* 44:   */  
/* 49:   */  public void dump(String prefix)
/* 50:   */  {
/* 51:51 */    System.out.println(prefix + "Item Container " + this.name + "," + this.condition);
/* 52:52 */    for (int i = 0; i < this.items.size(); i++) {
/* 53:53 */      ((Item)this.items.get(i)).dump(prefix + "\t");
/* 54:   */    }
/* 55:   */  }
/* 56:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.xml.ItemContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */