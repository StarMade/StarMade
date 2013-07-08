/*  1:   */import org.schema.game.common.controller.SegmentController;
/*  2:   */import org.schema.schine.network.StateInterface;
/*  3:   */
/*  5:   */public final class ks
/*  6:   */  extends kp
/*  7:   */{
/*  8:   */  public ks(StateInterface paramStateInterface, jA paramjA)
/*  9:   */  {
/* 10:10 */    super(paramStateInterface, paramjA);
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
/* 34:   */  protected final void b() {}
/* 35:   */  
/* 42:   */  public final boolean b()
/* 43:   */  {
/* 44:44 */    return a(kq.c).a();
/* 45:   */  }
/* 46:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ks
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */