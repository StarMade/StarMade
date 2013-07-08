package org.newdawn.slick.tests.xml;

import java.io.PrintStream;
import java.util.ArrayList;

public class ItemContainer
  extends Item
{
  private ArrayList items = new ArrayList();
  
  private void add(Item item)
  {
    this.items.add(item);
  }
  
  private void setName(String name)
  {
    this.name = name;
  }
  
  private void setCondition(int condition)
  {
    this.condition = condition;
  }
  
  public void dump(String prefix)
  {
    System.out.println(prefix + "Item Container " + this.name + "," + this.condition);
    for (int local_i = 0; local_i < this.items.size(); local_i++) {
      ((Item)this.items.get(local_i)).dump(prefix + "\t");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.xml.ItemContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */