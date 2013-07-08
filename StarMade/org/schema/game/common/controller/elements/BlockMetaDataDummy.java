/*  1:   */package org.schema.game.common.controller.elements;
/*  2:   */
/*  3:   */import q;
/*  4:   */
/*  5:   */public abstract class BlockMetaDataDummy
/*  6:   */{
/*  7:   */  public q pos;
/*  8:   */  
/*  9:   */  public void fromTagStructure(Ah paramAh)
/* 10:   */  {
/* 11:11 */    paramAh = (Ah[])paramAh.a();
/* 12:12 */    this.pos = ((q)paramAh[0].a());
/* 13:13 */    fromTagStructrePriv(paramAh[1]);
/* 14:   */  }
/* 15:   */  
/* 16:   */  protected abstract void fromTagStructrePriv(Ah paramAh);
/* 17:   */  
/* 18:   */  public q getControllerPos() {
/* 19:19 */    return this.pos;
/* 20:   */  }
/* 21:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.BlockMetaDataDummy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */