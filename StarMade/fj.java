/*  1:   */import java.util.concurrent.ConcurrentHashMap;
/*  2:   */import org.schema.schine.network.client.ClientState;
/*  3:   */
/* 29:   */final class fj
/* 30:   */  extends yD
/* 31:   */{
/* 32:   */  private lE a;
/* 33:   */  
/* 34:   */  public fj(fi paramfi, lE paramlE, ClientState paramClientState)
/* 35:   */  {
/* 36:36 */    super(new fk(paramClientState), new fk(paramClientState), paramClientState);
/* 37:   */    
/* 38:38 */    this.a = paramlE;
/* 39:   */    
/* 40:40 */    a(null);
/* 41:41 */    this.a = paramlE;
/* 42:   */    
/* 43:43 */    fi.a(paramfi).put(paramlE, this);
/* 44:   */  }
/* 45:   */  
/* 49:   */  public final boolean equals(Object paramObject)
/* 50:   */  {
/* 51:51 */    return this.a.equals(((fj)paramObject).a);
/* 52:   */  }
/* 53:   */  
/* 57:   */  public final int hashCode()
/* 58:   */  {
/* 59:59 */    return this.a.getId();
/* 60:   */  }
/* 61:   */  
/* 62:   */  public final void a(xq paramxq)
/* 63:   */  {
/* 64:64 */    ((fk)this.a).a(this.a.getName(), String.valueOf(this.a.f()), String.valueOf(this.a.e()), String.valueOf(this.a.g()), String.valueOf(this.a.h()));
/* 65:65 */    ((fk)this.b).a(this.a.getName(), String.valueOf(this.a.f()), String.valueOf(this.a.e()), String.valueOf(this.a.g()), String.valueOf(this.a.h()));
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */