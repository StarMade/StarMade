package org.schema.game.common.controller.elements.thrust;

import class_48;
import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import org.schema.game.common.data.element.ElementCollection;

public class ThrusterUnit
  extends ElementCollection
{
  private final class_48 significator = new class_48();
  float thrust;
  private final HashMap lastElements = new HashMap();
  private final Point pointTmp = new Point();
  private final class_48 abspos = new class_48();
  
  protected void onAdd(long paramLong)
  {
    long l1 = paramLong / 4294705156L;
    long l2 = (paramLong -= l1 * 4294705156L) / 65534L;
    paramLong = (int)(paramLong - l2 * 65534L - 32767L);
    int i = (int)(l2 - 32767L);
    int j = (int)(l1 - 32767L);
    this.pointTmp.setLocation(paramLong, i);
    if (!getLastElements().containsKey(this.pointTmp))
    {
      localObject = new Point(paramLong, i);
      getLastElements().put(localObject, new class_48(paramLong, i, j));
      return;
    }
    Object localObject = (class_48)getLastElements().get(this.pointTmp);
    if (j < ((class_48)localObject).field_477) {
      ((class_48)getLastElements().get(this.pointTmp)).b(paramLong, i, j);
    }
  }
  
  public boolean addElement(long paramLong)
  {
    boolean bool;
    if ((bool = super.addElement(paramLong)))
    {
      getPosFromIndex(paramLong, this.abspos);
      onAdd(paramLong);
    }
    return bool;
  }
  
  protected void onRemove(class_48 paramclass_48)
  {
    super.onRemove(paramclass_48);
    paramclass_48 = new Point(paramclass_48.field_475, paramclass_48.field_476);
    class_48 localclass_481 = (class_48)getLastElements().remove(paramclass_48);
    class_48 localclass_482 = new class_48(localclass_481);
    for (int i = localclass_481.field_477 - 1; i > getMin().field_477; i--)
    {
      localclass_482.field_477 = i;
      long l = ElementCollection.getIndex(localclass_482);
      if (getNeighboringCollection().contains(Long.valueOf(l))) {
        getLastElements().put(paramclass_48, localclass_482);
      }
    }
  }
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  public void refreshThrusterCapabilities()
  {
    this.thrust = Math.max(0.0F, getMax().field_477 - getMin().field_477);
    this.thrust += Math.max(0.0F, getMax().field_475 - getMin().field_475);
    this.thrust += Math.max(0.0F, getMax().field_476 - getMin().field_476);
    float f = (float)Math.pow(size(), 1.125D);
    this.thrust += f;
    this.thrust = Math.max(1.0F, this.thrust);
  }
  
  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 <= this.significator.field_475) && (paramInt2 <= this.significator.field_476) && (paramInt3 < this.significator.field_477)) {
      this.significator.b(paramInt1, paramInt2, paramInt3);
    }
  }
  
  public HashMap getLastElements()
  {
    return this.lastElements;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.thrust.ThrusterUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */