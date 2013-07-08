/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import q;
/*  4:   */
/*  5:   */public class DockingRequest
/*  6:   */{
/*  7:   */  public boolean dock;
/*  8:   */  public String id;
/*  9:   */  public q pos;
/* 10:   */  
/* 11:   */  public DockingRequest() {}
/* 12:   */  
/* 13:   */  public DockingRequest(boolean paramBoolean, String paramString, q paramq)
/* 14:   */  {
/* 15:15 */    set(paramBoolean, paramString, paramq);
/* 16:   */  }
/* 17:   */  
/* 18:   */  public void set(boolean paramBoolean, String paramString, q paramq) {
/* 19:19 */    this.dock = paramBoolean;
/* 20:20 */    this.id = paramString;
/* 21:21 */    this.pos = paramq;
/* 22:   */  }
/* 23:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.DockingRequest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */