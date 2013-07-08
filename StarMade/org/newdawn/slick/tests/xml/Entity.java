/*  1:   */package org.newdawn.slick.tests.xml;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */
/* 15:   */public class Entity
/* 16:   */{
/* 17:   */  private float x;
/* 18:   */  private float y;
/* 19:   */  private Inventory invent;
/* 20:   */  private Stats stats;
/* 21:   */  
/* 22:   */  private void add(Inventory inventory)
/* 23:   */  {
/* 24:24 */    this.invent = inventory;
/* 25:   */  }
/* 26:   */  
/* 31:   */  private void add(Stats stats)
/* 32:   */  {
/* 33:33 */    this.stats = stats;
/* 34:   */  }
/* 35:   */  
/* 40:   */  public void dump(String prefix)
/* 41:   */  {
/* 42:42 */    System.out.println(prefix + "Entity " + this.x + "," + this.y);
/* 43:43 */    this.invent.dump(prefix + "\t");
/* 44:44 */    this.stats.dump(prefix + "\t");
/* 45:   */  }
/* 46:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.xml.Entity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */