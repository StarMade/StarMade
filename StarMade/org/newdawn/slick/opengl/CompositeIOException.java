package org.newdawn.slick.opengl;

import java.io.IOException;
import java.util.ArrayList;

public class CompositeIOException
  extends IOException
{
  private ArrayList exceptions = new ArrayList();
  
  public void addException(Exception local_e)
  {
    this.exceptions.add(local_e);
  }
  
  public String getMessage()
  {
    String msg = "Composite Exception: \n";
    for (int local_i = 0; local_i < this.exceptions.size(); local_i++) {
      msg = msg + "\t" + ((IOException)this.exceptions.get(local_i)).getMessage() + "\n";
    }
    return msg;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.CompositeIOException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */