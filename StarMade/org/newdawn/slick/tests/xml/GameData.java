package org.newdawn.slick.tests.xml;

import java.io.PrintStream;
import java.util.ArrayList;

public class GameData
{
  private ArrayList entities = new ArrayList();
  
  private void add(Entity entity)
  {
    this.entities.add(entity);
  }
  
  public void dump(String prefix)
  {
    System.out.println(prefix + "GameData");
    for (int local_i = 0; local_i < this.entities.size(); local_i++) {
      ((Entity)this.entities.get(local_i)).dump(prefix + "\t");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.xml.GameData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */