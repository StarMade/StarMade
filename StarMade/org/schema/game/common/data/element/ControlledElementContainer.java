package org.schema.game.common.data.element;

import class_48;

public class ControlledElementContainer
{
  public long from;
  public class_48 field_1664;
  public short controlledType;
  public boolean add = false;
  public boolean send;
  
  public ControlledElementContainer(long paramLong, class_48 paramclass_48, short paramShort, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.from = paramLong;
    this.field_1664 = paramclass_48;
    this.controlledType = paramShort;
    this.add = paramBoolean1;
    this.send = paramBoolean2;
  }
  
  public boolean equals(Object paramObject)
  {
    return (((ControlledElementContainer)paramObject).add == this.add) && (((ControlledElementContainer)paramObject).from == this.from) && (((ControlledElementContainer)paramObject).field_1664.equals(this.field_1664)) && (((ControlledElementContainer)paramObject).controlledType == this.controlledType);
  }
  
  public int hashCode()
  {
    return (int)(this.from + this.field_1664.hashCode());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ControlledElementContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */