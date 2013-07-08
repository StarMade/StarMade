/*  1:   */package org.schema.game.common.controller.elements.door;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*  4:   */import java.util.Collection;
/*  5:   */import java.util.Iterator;
/*  6:   */import ka;
/*  7:   */import le;
/*  8:   */import org.schema.game.common.data.element.ElementCollection;
/*  9:   */import s;
/* 10:   */
/* 11:   */public class DoorUnit extends ElementCollection
/* 12:   */{
/* 13:13 */  private q significator = new q();
/* 14:14 */  private javax.vecmath.Vector3f topMin = new javax.vecmath.Vector3f();
/* 15:15 */  private javax.vecmath.Vector3f topMax = new javax.vecmath.Vector3f();
/* 16:   */  
/* 17:   */  private boolean active;
/* 18:   */  
/* 20:   */  public void activate(le paramle, boolean paramBoolean)
/* 21:   */  {
/* 22:22 */    paramle = new q();
/* 23:23 */    for (Iterator localIterator = getNeighboringCollection().iterator(); localIterator.hasNext();) {
/* 24:24 */      getPosFromIndex(((Long)localIterator.next()).longValue(), paramle);
/* 25:   */      s locals;
/* 26:26 */      (locals = new s()).a(paramle.a, paramle.b, paramle.c, paramBoolean ? 2 : -2);
/* 27:27 */      ((ka)getController()).getBlockActivationBuffer().enqueue(locals);
/* 28:   */    }
/* 29:   */  }
/* 30:   */  
/* 39:   */  public void cleanUp()
/* 40:   */  {
/* 41:41 */    super.cleanUp();
/* 42:   */  }
/* 43:   */  
/* 44:   */  public q getSignificator() {
/* 45:45 */    return this.significator;
/* 46:   */  }
/* 47:   */  
/* 48:   */  public boolean isActive() {
/* 49:49 */    return this.active;
/* 50:   */  }
/* 51:   */  
/* 56:   */  public void refreshDoorCapabilities() {}
/* 57:   */  
/* 62:   */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 63:   */  {
/* 64:64 */    this.significator.a = (getMax().a - (getMax().a - getMin().a) / 2);
/* 65:65 */    this.significator.b = getMax().b;
/* 66:66 */    this.significator.c = (getMax().c - (getMax().c - getMin().c) / 2);
/* 67:   */    
/* 68:68 */    this.topMin.set(getMin().a, getMax().b, getMin().c);
/* 69:69 */    this.topMax.set(getMax().a, getMax().b, getMax().c);
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.door.DoorUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */