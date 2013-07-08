/*  1:   */import org.schema.game.common.controller.SegmentController;
/*  2:   */import org.schema.schine.network.StateInterface;
/*  3:   */
/*  5:   */public final class ku
/*  6:   */  extends kp
/*  7:   */{
/*  8:   */  public ku(StateInterface paramStateInterface, ki paramki)
/*  9:   */  {
/* 10:10 */    super(paramStateInterface, paramki);
/* 11:   */  }
/* 12:   */  
/* 16:   */  public final String getUniqueIdentifier()
/* 17:   */  {
/* 18:18 */    return this.a.getUniqueIdentifier() + "_AI";
/* 19:   */  }
/* 20:   */  
/* 22:   */  public final boolean isVolatile()
/* 23:   */  {
/* 24:24 */    return false;
/* 25:   */  }
/* 26:   */  
/* 33:   */  protected final void b() {}
/* 34:   */  
/* 41:   */  public final boolean b()
/* 42:   */  {
/* 43:43 */    return a(kq.c).a();
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ku
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */