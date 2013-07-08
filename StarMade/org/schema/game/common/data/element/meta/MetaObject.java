package org.schema.game.common.data.element.meta;

import class_69;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class MetaObject
{
  private int field_1882;
  
  public abstract void serialize(DataOutputStream paramDataOutputStream);
  
  public abstract void deserialize(DataInputStream paramDataInputStream);
  
  public abstract short getObjectBlockID();
  
  public int getId()
  {
    return this.field_1882;
  }
  
  public void setId(int paramInt)
  {
    this.field_1882 = paramInt;
  }
  
  public abstract class_69 getBytesTag();
  
  public abstract void fromTag(class_69 paramclass_69);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.meta.MetaObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */