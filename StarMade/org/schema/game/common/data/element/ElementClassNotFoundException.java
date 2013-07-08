package org.schema.game.common.data.element;

public class ElementClassNotFoundException
  extends RuntimeException
{
  private static final long serialVersionUID = 1177407588913436017L;
  
  public ElementClassNotFoundException(short paramShort)
  {
    super("class for type " + paramShort + " not found");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ElementClassNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */