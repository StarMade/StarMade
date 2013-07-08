/*  1:   */import javax.vecmath.Vector4f;
/*  2:   */import org.schema.schine.network.client.ClientState;
/*  3:   */
/* 11:   */public final class gc
/* 12:   */  extends eX
/* 13:   */{
/* 14:   */  private ys b;
/* 15:   */  
/* 16:   */  public gc(ClientState paramClientState, ys paramys, lL paramlL)
/* 17:   */  {
/* 18:18 */    super(paramClientState, paramys, "Rate", "Rate " + paramlL.a);
/* 19:19 */    this.a = false;
/* 20:20 */    this.b = paramys;
/* 21:   */  }
/* 22:   */  
/* 26:   */  public final void c()
/* 27:   */  {
/* 28:28 */    super.c();
/* 29:   */    
/* 30:30 */    for (int i = 0; i < 10; i++) {
/* 31:31 */      float f = i / 10.0F;
/* 32:   */      
/* 34:   */      yN localyN;
/* 35:   */      
/* 37:37 */      (localyN = new yN(a(), 30, 30, new Vector4f(1.0F - f, f, 0.2F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.e(), String.valueOf(i + 1), this.b)).a = new Integer(i);
/* 38:38 */      if (i < 9) {
/* 39:39 */        localyN.b(6, 0);
/* 40:   */      }
/* 41:41 */      int j = i * 40;
/* 42:   */      
/* 45:45 */      localyN.a(j, 35.0F, 0.0F);
/* 46:46 */      this.a.a(localyN);
/* 47:   */    }
/* 48:   */  }
/* 49:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */