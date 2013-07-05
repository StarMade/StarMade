package org.newdawn.slick.loading;

import java.io.IOException;

public abstract interface DeferredResource
{
  public abstract void load()
    throws IOException;

  public abstract String getDescription();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.loading.DeferredResource
 * JD-Core Version:    0.6.2
 */