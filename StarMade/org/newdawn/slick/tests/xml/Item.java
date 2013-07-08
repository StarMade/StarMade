package org.newdawn.slick.tests.xml;

import java.io.PrintStream;

public class Item
{
  protected String name;
  protected int condition;
  
  public void dump(String prefix)
  {
    System.out.println(prefix + "Item " + this.name + "," + this.condition);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.xml.Item
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */