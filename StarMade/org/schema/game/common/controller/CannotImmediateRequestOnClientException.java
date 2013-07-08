package org.schema.game.common.controller;

import class_48;

public class CannotImmediateRequestOnClientException
  extends RuntimeException
{
  private static final long serialVersionUID = -7838434728382878333L;
  private final class_48 field_2208;
  
  public CannotImmediateRequestOnClientException(class_48 paramclass_48)
  {
    this.field_2208 = new class_48(paramclass_48);
  }
  
  public final class_48 a()
  {
    return this.field_2208;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.CannotImmediateRequestOnClientException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */