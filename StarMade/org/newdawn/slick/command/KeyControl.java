package org.newdawn.slick.command;

public class KeyControl
  implements Control
{
  private int keycode;
  
  public KeyControl(int keycode)
  {
    this.keycode = keycode;
  }
  
  public boolean equals(Object local_o)
  {
    if ((local_o instanceof KeyControl)) {
      return ((KeyControl)local_o).keycode == this.keycode;
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.keycode;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.command.KeyControl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */