package org.schema.schine.graphicsengine.core.settings;

public class PrefixNotFoundException
  extends Exception
{
  private static final long serialVersionUID = -8677548242927628561L;
  
  public PrefixNotFoundException(String paramString)
  {
    super("ERROR: prefix not found: " + paramString);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.graphicsengine.core.settings.PrefixNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */