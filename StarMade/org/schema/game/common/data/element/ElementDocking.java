/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  3:   */import le;
/*  4:   */
/*  5:   */public class ElementDocking {
/*  6:   */  public final le from;
/*  7:   */  public final le to;
/*  8:   */  
/*  9:   */  public ElementDocking(le paramle1, le paramle2) {
/* 10:10 */    this.from = paramle1;
/* 11:11 */    this.to = paramle2;
/* 12:   */  }
/* 13:   */  
/* 14:   */  public boolean equals(Object paramObject) {
/* 15:15 */    return equalsDock((ElementDocking)paramObject);
/* 16:   */  }
/* 17:   */  
/* 18:18 */  public boolean equalsDock(ElementDocking paramElementDocking) { return (this.from.equals(paramElementDocking.from)) && (this.to.equals(paramElementDocking.to)); }
/* 19:   */  
/* 20:   */  public int hashCode()
/* 21:   */  {
/* 22:22 */    return this.from.hashCode() + this.to.hashCode();
/* 23:   */  }
/* 24:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementDocking
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */