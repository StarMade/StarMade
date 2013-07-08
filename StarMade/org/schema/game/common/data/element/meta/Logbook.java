/*  1:   */package org.schema.game.common.data.element.meta;
/*  2:   */
/*  3:   */import Ah;
/*  4:   */import Aj;
/*  5:   */import java.io.DataInputStream;
/*  6:   */import java.io.DataOutputStream;
/*  7:   */
/*  9:   */public class Logbook
/* 10:   */  extends MetaObject
/* 11:   */{
/* 12:   */  private String txt;
/* 13:   */  
/* 14:   */  public void serialize(DataOutputStream paramDataOutputStream)
/* 15:   */  {
/* 16:16 */    paramDataOutputStream.writeUTF(this.txt);
/* 17:   */  }
/* 18:   */  
/* 19:   */  public void deserialize(DataInputStream paramDataInputStream)
/* 20:   */  {
/* 21:21 */    this.txt = paramDataInputStream.readUTF();
/* 22:   */  }
/* 23:   */  
/* 25:   */  public short getObjectBlockID()
/* 26:   */  {
/* 27:27 */    return -11;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public Ah getBytesTag()
/* 31:   */  {
/* 32:32 */    return new Ah(Aj.i, null, this.txt);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void fromTag(Ah paramAh)
/* 36:   */  {
/* 37:37 */    this.txt = ((String)paramAh.a());
/* 38:   */  }
/* 39:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.meta.Logbook
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */