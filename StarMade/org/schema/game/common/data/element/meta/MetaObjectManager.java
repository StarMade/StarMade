/*  1:   */package org.schema.game.common.data.element.meta;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  4:   */import java.io.DataInputStream;
/*  5:   */import java.io.DataOutputStream;
/*  6:   */
/* 10:   */public class MetaObjectManager
/* 11:   */{
/* 12:   */  public static final short RECIPE = -10;
/* 13:   */  public static final short LOG_BOOK = -11;
/* 14:14 */  private Int2ObjectOpenHashMap map = new Int2ObjectOpenHashMap();
/* 15:   */  
/* 16:   */  public void deserialize(DataInputStream paramDataInputStream)
/* 17:   */  {
/* 18:18 */    int i = paramDataInputStream.readInt();
/* 19:19 */    short s = paramDataInputStream.readShort();
/* 20:   */    
/* 21:   */    MetaObject localMetaObject;
/* 22:22 */    if ((localMetaObject = (MetaObject)this.map.get(i)) == null)
/* 23:   */    {
/* 24:24 */      (localMetaObject = instantiate(s)).setId(i);
/* 25:25 */      this.map.put(i, localMetaObject);
/* 26:   */    }
/* 27:27 */    localMetaObject.deserialize(paramDataInputStream);
/* 28:   */  }
/* 29:   */  
/* 30:   */  public static MetaObject instantiate(short paramShort)
/* 31:   */  {
/* 32:32 */    switch (paramShort) {
/* 33:33 */    case -11:  return new Logbook();
/* 34:34 */    case -10:  return new Recipe();
/* 35:   */    }
/* 36:36 */    throw new IllegalArgumentException("UNKNOWN OID: " + paramShort);
/* 37:   */  }
/* 38:   */  
/* 39:   */  public void serialize(DataOutputStream paramDataOutputStream, MetaObject paramMetaObject) {
/* 40:40 */    paramDataOutputStream.writeInt(paramMetaObject.getId());
/* 41:41 */    paramDataOutputStream.writeShort(paramMetaObject.getObjectBlockID());
/* 42:42 */    paramMetaObject.serialize(paramDataOutputStream);
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.meta.MetaObjectManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */