/*  1:   */package org.newdawn.slick.command;
/*  2:   */
/*  7:   */public class MouseButtonControl
/*  8:   */  implements Control
/*  9:   */{
/* 10:   */  private int button;
/* 11:   */  
/* 16:   */  public MouseButtonControl(int button)
/* 17:   */  {
/* 18:18 */    this.button = button;
/* 19:   */  }
/* 20:   */  
/* 23:   */  public boolean equals(Object o)
/* 24:   */  {
/* 25:25 */    if ((o instanceof MouseButtonControl))
/* 26:   */    {
/* 27:27 */      return ((MouseButtonControl)o).button == this.button;
/* 28:   */    }
/* 29:   */    
/* 30:30 */    return false;
/* 31:   */  }
/* 32:   */  
/* 35:   */  public int hashCode()
/* 36:   */  {
/* 37:37 */    return this.button;
/* 38:   */  }
/* 39:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.command.MouseButtonControl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */