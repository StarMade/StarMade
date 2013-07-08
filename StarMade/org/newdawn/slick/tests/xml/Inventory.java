/*  1:   */package org.newdawn.slick.tests.xml;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.ArrayList;
/*  5:   */
/* 10:   */public class Inventory
/* 11:   */{
/* 12:12 */  private ArrayList items = new ArrayList();
/* 13:   */  
/* 18:   */  private void add(Item item)
/* 19:   */  {
/* 20:20 */    this.items.add(item);
/* 21:   */  }
/* 22:   */  
/* 27:   */  public void dump(String prefix)
/* 28:   */  {
/* 29:29 */    System.out.println(prefix + "Inventory");
/* 30:30 */    for (int i = 0; i < this.items.size(); i++) {
/* 31:31 */      ((Item)this.items.get(i)).dump(prefix + "\t");
/* 32:   */    }
/* 33:   */  }
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.xml.Inventory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */