package org.newdawn.slick.util.pathfinding;

import java.io.Serializable;
import java.util.ArrayList;

public class Path
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private ArrayList steps = new ArrayList();
  
  public int getLength()
  {
    return this.steps.size();
  }
  
  public Step getStep(int index)
  {
    return (Step)this.steps.get(index);
  }
  
  public int getX(int index)
  {
    return getStep(index).field_570;
  }
  
  public int getY(int index)
  {
    return getStep(index).field_571;
  }
  
  public void appendStep(int local_x, int local_y)
  {
    this.steps.add(new Step(local_x, local_y));
  }
  
  public void prependStep(int local_x, int local_y)
  {
    this.steps.add(0, new Step(local_x, local_y));
  }
  
  public boolean contains(int local_x, int local_y)
  {
    return this.steps.contains(new Step(local_x, local_y));
  }
  
  public class Step
    implements Serializable
  {
    private int field_570;
    private int field_571;
    
    public Step(int local_x, int local_y)
    {
      this.field_570 = local_x;
      this.field_571 = local_y;
    }
    
    public int getX()
    {
      return this.field_570;
    }
    
    public int getY()
    {
      return this.field_571;
    }
    
    public int hashCode()
    {
      return this.field_570 * this.field_571;
    }
    
    public boolean equals(Object other)
    {
      if ((other instanceof Step))
      {
        Step local_o = (Step)other;
        return (local_o.field_570 == this.field_570) && (local_o.field_571 == this.field_571);
      }
      return false;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.Path
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */