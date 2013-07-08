package org.schema.game.common.data.element.meta;

import class_69;
import class_79;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Logbook
  extends MetaObject
{
  private String txt;
  
  public void serialize(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeUTF(this.txt);
  }
  
  public void deserialize(DataInputStream paramDataInputStream)
  {
    this.txt = paramDataInputStream.readUTF();
  }
  
  public short getObjectBlockID()
  {
    return -11;
  }
  
  public class_69 getBytesTag()
  {
    return new class_69(class_79.field_556, null, this.txt);
  }
  
  public void fromTag(class_69 paramclass_69)
  {
    this.txt = ((String)paramclass_69.a4());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.meta.Logbook
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */