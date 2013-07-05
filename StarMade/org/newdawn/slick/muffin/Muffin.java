package org.newdawn.slick.muffin;

import java.io.IOException;
import java.util.HashMap;

public abstract interface Muffin
{
  public abstract void saveFile(HashMap paramHashMap, String paramString)
    throws IOException;

  public abstract HashMap loadFile(String paramString)
    throws IOException;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.muffin.Muffin
 * JD-Core Version:    0.6.2
 */