/*  1:   */package org.newdawn.slick.command;
/*  2:   */
/*  8:   */public class KeyControl
/*  9:   */  implements Control
/* 10:   */{
/* 11:   */  private int keycode;
/* 12:   */  
/* 17:   */  public KeyControl(int keycode)
/* 18:   */  {
/* 19:19 */    this.keycode = keycode;
/* 20:   */  }
/* 21:   */  
/* 24:   */  public boolean equals(Object o)
/* 25:   */  {
/* 26:26 */    if ((o instanceof KeyControl)) {
/* 27:27 */      return ((KeyControl)o).keycode == this.keycode;
/* 28:   */    }
/* 29:   */    
/* 30:30 */    return false;
/* 31:   */  }
/* 32:   */  
/* 35:   */  public int hashCode()
/* 36:   */  {
/* 37:37 */    return this.keycode;
/* 38:   */  }
/* 39:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.command.KeyControl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */