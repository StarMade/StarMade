/*  1:   */package org.newdawn.slick.command;
/*  2:   */
/*  7:   */public class BasicCommand
/*  8:   */  implements Command
/*  9:   */{
/* 10:   */  private String name;
/* 11:   */  
/* 16:   */  public BasicCommand(String name)
/* 17:   */  {
/* 18:18 */    this.name = name;
/* 19:   */  }
/* 20:   */  
/* 25:   */  public String getName()
/* 26:   */  {
/* 27:27 */    return this.name;
/* 28:   */  }
/* 29:   */  
/* 32:   */  public int hashCode()
/* 33:   */  {
/* 34:34 */    return this.name.hashCode();
/* 35:   */  }
/* 36:   */  
/* 39:   */  public boolean equals(Object other)
/* 40:   */  {
/* 41:41 */    if ((other instanceof BasicCommand)) {
/* 42:42 */      return ((BasicCommand)other).name.equals(this.name);
/* 43:   */    }
/* 44:   */    
/* 45:45 */    return false;
/* 46:   */  }
/* 47:   */  
/* 50:   */  public String toString()
/* 51:   */  {
/* 52:52 */    return "[Command=" + this.name + "]";
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.command.BasicCommand
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */