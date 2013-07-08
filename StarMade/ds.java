/*  1:   */import com.bulletphysics.linearmath.Transform;
/*  2:   */import org.schema.schine.graphicsengine.camera.Camera;
/*  3:   */
/* 16:   */public final class ds
/* 17:   */  extends Camera
/* 18:   */{
/* 19:   */  private Transform a;
/* 20:   */  
/* 21:   */  public ds(yh paramyh)
/* 22:   */  {
/* 23:23 */    super(new dC(paramyh));
/* 24:24 */    ((xb)a()).a(paramyh);
/* 25:25 */    this.a = new Transform();
/* 26:   */    
/* 27:27 */    this.a.setIdentity();
/* 28:   */    
/* 29:29 */    this.a = new wX(this);
/* 30:   */  }
/* 31:   */  
/* 87:   */  public final void a(xq paramxq)
/* 88:   */  {
/* 89:89 */    ((wX)this.a).a.set(this.a);
/* 90:90 */    super.a(paramxq);
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ds
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */