package org.newdawn.slick.command;

public class MouseButtonControl
  implements Control
{
  private int button;
  
  public MouseButtonControl(int button)
  {
    this.button = button;
  }
  
  public boolean equals(Object local_o)
  {
    if ((local_o instanceof MouseButtonControl)) {
      return ((MouseButtonControl)local_o).button == this.button;
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.button;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.command.MouseButtonControl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */