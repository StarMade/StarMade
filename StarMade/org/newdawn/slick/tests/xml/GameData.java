/*  1:   */package org.newdawn.slick.tests.xml;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.ArrayList;
/*  5:   */
/* 10:   */public class GameData
/* 11:   */{
/* 12:12 */  private ArrayList entities = new ArrayList();
/* 13:   */  
/* 18:   */  private void add(Entity entity)
/* 19:   */  {
/* 20:20 */    this.entities.add(entity);
/* 21:   */  }
/* 22:   */  
/* 27:   */  public void dump(String prefix)
/* 28:   */  {
/* 29:29 */    System.out.println(prefix + "GameData");
/* 30:30 */    for (int i = 0; i < this.entities.size(); i++) {
/* 31:31 */      ((Entity)this.entities.get(i)).dump(prefix + "\t");
/* 32:   */    }
/* 33:   */  }
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.xml.GameData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */