package org.newdawn.slick.command;

public class BasicCommand
  implements Command
{
  private String name;
  
  public BasicCommand(String name)
  {
    this.name = name;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int hashCode()
  {
    return this.name.hashCode();
  }
  
  public boolean equals(Object other)
  {
    if ((other instanceof BasicCommand)) {
      return ((BasicCommand)other).name.equals(this.name);
    }
    return false;
  }
  
  public String toString()
  {
    return "[Command=" + this.name + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.command.BasicCommand
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */