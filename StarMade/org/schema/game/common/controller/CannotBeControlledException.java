package org.schema.game.common.controller;

import org.schema.game.common.data.element.ElementInformation;

public class CannotBeControlledException
  extends Exception
{
  private static final long serialVersionUID = 4188138482695970846L;
  public final ElementInformation field_1757;
  public final ElementInformation field_1758;
  
  public CannotBeControlledException(ElementInformation paramElementInformation1, ElementInformation paramElementInformation2)
  {
    this.field_1758 = paramElementInformation1;
    this.field_1757 = paramElementInformation2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.CannotBeControlledException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */