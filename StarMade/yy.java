/*  1:   */import javax.vecmath.Vector4f;
/*  2:   */import org.schema.schine.network.client.ClientState;
/*  3:   */
/*  7:   */public final class yy
/*  8:   */  extends yx
/*  9:   */{
/* 10:   */  private yz a;
/* 11:   */  
/* 12:   */  public yy(ClientState paramClientState, float paramFloat1, float paramFloat2, Vector4f paramVector4f, yz paramyz)
/* 13:   */  {
/* 14:14 */    super(paramClientState, paramFloat1, paramFloat2, paramVector4f);
/* 15:15 */    this.a = paramVector4f;
/* 16:16 */    this.a = paramyz;
/* 17:   */  }
/* 18:   */  
/* 26:   */  public final void b()
/* 27:   */  {
/* 28:28 */    if (this.a != null) {
/* 29:29 */      this.a.b();
/* 30:30 */      if (this.a.g) {
/* 31:31 */        this.a.l();
/* 32:   */      }
/* 33:   */    }
/* 34:   */    
/* 36:36 */    super.b();
/* 37:   */  }
/* 38:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */