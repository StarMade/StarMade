/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  3:   */import q;
/*  4:   */
/*  9:   */public class ControlledElementContainer
/* 10:   */{
/* 11:   */  public long from;
/* 12:   */  public q to;
/* 13:   */  public short controlledType;
/* 14:14 */  public boolean add = false;
/* 15:   */  public boolean send;
/* 16:   */  
/* 17:   */  public ControlledElementContainer(long paramLong, q paramq, short paramShort, boolean paramBoolean1, boolean paramBoolean2)
/* 18:   */  {
/* 19:19 */    this.from = paramLong;
/* 20:20 */    this.to = paramq;
/* 21:21 */    this.controlledType = paramShort;
/* 22:22 */    this.add = paramBoolean1;
/* 23:23 */    this.send = paramBoolean2;
/* 24:   */  }
/* 25:   */  
/* 28:   */  public boolean equals(Object paramObject)
/* 29:   */  {
/* 30:30 */    return (((ControlledElementContainer)paramObject).add == this.add) && (((ControlledElementContainer)paramObject).from == this.from) && (((ControlledElementContainer)paramObject).to.equals(this.to)) && (((ControlledElementContainer)paramObject).controlledType == this.controlledType);
/* 31:   */  }
/* 32:   */  
/* 36:   */  public int hashCode()
/* 37:   */  {
/* 38:38 */    return (int)(this.from + this.to.hashCode());
/* 39:   */  }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ControlledElementContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */