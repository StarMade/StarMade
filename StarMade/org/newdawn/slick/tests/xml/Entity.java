package org.newdawn.slick.tests.xml;

import java.io.PrintStream;

public class Entity
{
  private float field_1876;
  private float field_1877;
  private Inventory invent;
  private Stats stats;
  
  private void add(Inventory inventory)
  {
    this.invent = inventory;
  }
  
  private void add(Stats stats)
  {
    this.stats = stats;
  }
  
  public void dump(String prefix)
  {
    System.out.println(prefix + "Entity " + this.field_1876 + "," + this.field_1877);
    this.invent.dump(prefix + "\t");
    this.stats.dump(prefix + "\t");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.xml.Entity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */