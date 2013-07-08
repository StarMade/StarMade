package org.schema.game.common.data.element;

import class_796;

public class ElementDocking
{
  public final class_796 from;
  public final class_796 field_1740;
  
  public ElementDocking(class_796 paramclass_7961, class_796 paramclass_7962)
  {
    this.from = paramclass_7961;
    this.field_1740 = paramclass_7962;
  }
  
  public boolean equals(Object paramObject)
  {
    return equalsDock((ElementDocking)paramObject);
  }
  
  public boolean equalsDock(ElementDocking paramElementDocking)
  {
    return (this.from.equals(paramElementDocking.from)) && (this.field_1740.equals(paramElementDocking.field_1740));
  }
  
  public int hashCode()
  {
    return this.from.hashCode() + this.field_1740.hashCode();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ElementDocking
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */