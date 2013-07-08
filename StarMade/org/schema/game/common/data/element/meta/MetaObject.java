/*  1:   */package org.schema.game.common.data.element.meta;
/*  2:   */
/*  3:   */import Ah;
/*  4:   */import java.io.DataInputStream;
/*  5:   */import java.io.DataOutputStream;
/*  6:   */
/*  9:   */public abstract class MetaObject
/* 10:   */{
/* 11:   */  private int id;
/* 12:   */  
/* 13:   */  public abstract void serialize(DataOutputStream paramDataOutputStream);
/* 14:   */  
/* 15:   */  public abstract void deserialize(DataInputStream paramDataInputStream);
/* 16:   */  
/* 17:   */  public abstract short getObjectBlockID();
/* 18:   */  
/* 19:   */  public int getId()
/* 20:   */  {
/* 21:21 */    return this.id;
/* 22:   */  }
/* 23:   */  
/* 25:   */  public void setId(int paramInt)
/* 26:   */  {
/* 27:27 */    this.id = paramInt;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public abstract Ah getBytesTag();
/* 31:   */  
/* 32:   */  public abstract void fromTag(Ah paramAh);
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.meta.MetaObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */