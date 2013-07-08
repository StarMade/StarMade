/*  1:   */import org.schema.game.common.data.world.Segment;
/*  2:   */
/* 11:   */public final class uz
/* 12:   */  extends uC
/* 13:   */{
/* 14:   */  private boolean a;
/* 15:   */  
/* 16:   */  public uz(q paramq1, uu[] paramArrayOfuu, q paramq2, q paramq3, int paramInt)
/* 17:   */  {
/* 18:18 */    super(paramq1, paramArrayOfuu, paramq2, paramq3, paramInt, (byte)0);
/* 19:   */  }
/* 20:   */  
/* 25:   */  protected final short a(q paramq)
/* 26:   */  {
/* 27:27 */    if (paramq.equals(this.c)) {
/* 28:28 */      this.a = true;
/* 29:29 */      return 120;
/* 30:   */    }
/* 31:31 */    return 32767;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public final uF a(Segment paramSegment) {
/* 35:   */    uE localuE;
/* 36:36 */    (localuE = new uE()).a(this, paramSegment);
/* 37:37 */    this.a = false;
/* 38:   */    
/* 39:39 */    return localuE;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public final boolean a() {
/* 43:43 */    return this.a;
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */