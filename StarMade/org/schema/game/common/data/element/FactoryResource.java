/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  3:   */public class FactoryResource {
/*  4:   */  public final int count;
/*  5:   */  public final short type;
/*  6:   */  
/*  7:   */  public FactoryResource(int paramInt, short paramShort) {
/*  8: 8 */    this.count = paramInt;
/*  9: 9 */    this.type = paramShort;
/* 10:   */  }
/* 11:   */  
/* 12:   */  public String toString() {
/* 13:13 */    return "(" + ElementKeyMap.getInfo(this.type) + " x" + this.count + ")";
/* 14:   */  }
/* 15:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.FactoryResource
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */