/*   1:    */package org.schema.game.common.controller.elements.thrust;
/*   2:    */
/*   3:    */import java.awt.Point;
/*   4:    */import java.util.Collection;
/*   5:    */import java.util.HashMap;
/*   6:    */import org.schema.game.common.data.element.ElementCollection;
/*   7:    */import q;
/*   8:    */
/*  12:    */public class ThrusterUnit
/*  13:    */  extends ElementCollection
/*  14:    */{
/*  15: 15 */  private final q significator = new q();
/*  16:    */  
/*  17:    */  float thrust;
/*  18:    */  
/*  19: 19 */  private final HashMap lastElements = new HashMap();
/*  20:    */  
/*  22: 22 */  private final Point pointTmp = new Point();
/*  23: 23 */  private final q abspos = new q();
/*  24:    */  
/*  26:    */  protected void onAdd(long paramLong)
/*  27:    */  {
/*  28: 28 */    long l1 = paramLong / 4294705156L;
/*  29:    */    
/*  31: 31 */    long l2 = (paramLong = paramLong - l1 * 4294705156L) / 65534L;
/*  32:    */    
/*  34: 34 */    paramLong = (int)(paramLong - l2 * 65534L - 32767L);
/*  35:    */    
/*  37: 37 */    int i = (int)(l2 - 32767L);
/*  38: 38 */    int j = (int)(l1 - 32767L);
/*  39:    */    
/*  40: 40 */    this.pointTmp.setLocation(paramLong, i);
/*  41: 41 */    if (!getLastElements().containsKey(this.pointTmp)) {
/*  42: 42 */      localObject = new Point(paramLong, i);
/*  43: 43 */      getLastElements().put(localObject, new q(paramLong, i, j));
/*  44: 44 */      return; }
/*  45: 45 */    Object localObject = (q)getLastElements().get(this.pointTmp);
/*  46: 46 */    if (j < ((q)localObject).c) {
/*  47: 47 */      ((q)getLastElements().get(this.pointTmp)).b(paramLong, i, j);
/*  48:    */    }
/*  49:    */  }
/*  50:    */  
/*  54:    */  public boolean addElement(long paramLong)
/*  55:    */  {
/*  56:    */    boolean bool;
/*  57:    */    
/*  59: 59 */    if ((bool = super.addElement(paramLong))) {
/*  60: 60 */      getPosFromIndex(paramLong, this.abspos);
/*  61:    */      
/*  62: 62 */      onAdd(paramLong);
/*  63:    */    }
/*  64: 64 */    return bool;
/*  65:    */  }
/*  66:    */  
/*  71:    */  protected void onRemove(q paramq)
/*  72:    */  {
/*  73: 73 */    super.onRemove(paramq);
/*  74: 74 */    paramq = new Point(paramq.a, paramq.b);
/*  75: 75 */    q localq1 = (q)getLastElements().remove(paramq);
/*  76: 76 */    q localq2 = new q(localq1);
/*  77: 77 */    for (int i = localq1.c - 1; i > getMin().c; i--) {
/*  78: 78 */      localq2.c = i;
/*  79:    */      
/*  80: 80 */      long l = ElementCollection.getIndex(localq2);
/*  81: 81 */      if (getNeighboringCollection().contains(Long.valueOf(l))) {
/*  82: 82 */        getLastElements().put(paramq, localq2);
/*  83:    */      }
/*  84:    */    }
/*  85:    */  }
/*  86:    */  
/*  88:    */  public q getSignificator()
/*  89:    */  {
/*  90: 90 */    return this.significator;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void refreshThrusterCapabilities() {
/*  94: 94 */    this.thrust = Math.max(0.0F, getMax().c - getMin().c);
/*  95: 95 */    this.thrust += Math.max(0.0F, getMax().a - getMin().a);
/*  96: 96 */    this.thrust += Math.max(0.0F, getMax().b - getMin().b);
/*  97: 97 */    float f = (float)Math.pow(size(), 1.125D);
/*  98:    */    
/*  99: 99 */    this.thrust += f;
/* 100:100 */    this.thrust = Math.max(1.0F, this.thrust);
/* 101:    */  }
/* 102:    */  
/* 108:    */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 109:    */  {
/* 110:110 */    if ((paramInt1 <= this.significator.a) && (paramInt2 <= this.significator.b) && (paramInt3 < this.significator.c)) {
/* 111:111 */      this.significator.b(paramInt1, paramInt2, paramInt3);
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 118:    */  public HashMap getLastElements()
/* 119:    */  {
/* 120:120 */    return this.lastElements;
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.thrust.ThrusterUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */