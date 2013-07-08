/*  1:   */import java.util.Iterator;
/*  2:   */import java.util.List;
/*  3:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  4:   */import org.schema.schine.network.client.ClientState;
/*  5:   */
/*  8:   */public class yr
/*  9:   */  extends yz
/* 10:   */{
/* 11:   */  protected float a;
/* 12:   */  protected float b;
/* 13:   */  
/* 14:14 */  public yr(ClientState paramClientState) { super(paramClientState); }
/* 15:   */  
/* 16:   */  public yr(ClientState paramClientState, float paramFloat1, float paramFloat2) {
/* 17:17 */    this(paramClientState);
/* 18:18 */    this.b = paramFloat1;
/* 19:19 */    this.a = paramFloat2;
/* 20:   */  }
/* 21:   */  
/* 24:   */  public void a() {}
/* 25:   */  
/* 28:   */  protected final void d() {}
/* 29:   */  
/* 32:   */  public void b()
/* 33:   */  {
/* 34:34 */    GlUtil.d();
/* 35:35 */    r();
/* 36:36 */    for (Iterator localIterator = this.a.iterator(); localIterator.hasNext();) {
/* 37:37 */      ((xM)localIterator.next()).b();
/* 38:   */    }
/* 39:   */    
/* 40:40 */    if (this.g) {
/* 41:41 */      l();
/* 42:   */    }
/* 43:   */    
/* 46:46 */    GlUtil.c();
/* 47:   */  }
/* 48:   */  
/* 50:   */  public float a()
/* 51:   */  {
/* 52:52 */    return this.a;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public float b()
/* 56:   */  {
/* 57:57 */    return this.b;
/* 58:   */  }
/* 59:   */  
/* 60:   */  public void c() {}
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */