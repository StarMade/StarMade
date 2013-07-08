package org.schema.game.common.data.element.meta;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class MetaObjectManager
{
  public static final short RECIPE = -10;
  public static final short LOG_BOOK = -11;
  private Int2ObjectOpenHashMap map = new Int2ObjectOpenHashMap();
  
  public void deserialize(DataInputStream paramDataInputStream)
  {
    int i = paramDataInputStream.readInt();
    short s = paramDataInputStream.readShort();
    MetaObject localMetaObject;
    if ((localMetaObject = (MetaObject)this.map.get(i)) == null)
    {
      (localMetaObject = instantiate(s)).setId(i);
      this.map.put(i, localMetaObject);
    }
    localMetaObject.deserialize(paramDataInputStream);
  }
  
  public static MetaObject instantiate(short paramShort)
  {
    switch (paramShort)
    {
    case -11: 
      return new Logbook();
    case -10: 
      return new Recipe();
    }
    throw new IllegalArgumentException("UNKNOWN OID: " + paramShort);
  }
  
  public void serialize(DataOutputStream paramDataOutputStream, MetaObject paramMetaObject)
  {
    paramDataOutputStream.writeInt(paramMetaObject.getId());
    paramDataOutputStream.writeShort(paramMetaObject.getObjectBlockID());
    paramMetaObject.serialize(paramDataOutputStream);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.meta.MetaObjectManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */