/*  1:   */import javax.vecmath.Vector3f;
/*  2:   */import org.lwjgl.opengl.GL11;
/*  3:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  4:   */import org.schema.schine.network.client.ClientState;
/*  5:   */
/* 11:   */public final class ih
/* 12:   */  extends yz
/* 13:   */{
/* 14:   */  private ct a;
/* 15:   */  
/* 16:   */  public ih(ClientState paramClientState)
/* 17:   */  {
/* 18:18 */    super(paramClientState);
/* 19:19 */    this.a = ((ct)paramClientState);
/* 20:   */  }
/* 21:   */  
/* 23:   */  public final void a() {}
/* 24:   */  
/* 26:   */  public final void b()
/* 27:   */  {
/* 28:   */    kd localkd;
/* 29:   */    
/* 30:30 */    if ((localkd = this.a.a()) != null)
/* 31:   */    {
/* 32:32 */      Vector3f localVector3f1 = new Vector3f(1.0F, 0.0F, 0.0F);
/* 33:33 */      Vector3f localVector3f2 = new Vector3f(0.0F, 1.0F, 0.0F);
/* 34:34 */      Vector3f localVector3f3 = new Vector3f(0.0F, -1.0F, 0.0F);
/* 35:   */      
/* 36:36 */      Vector3f localVector3f4 = GlUtil.e(new Vector3f(), localkd.getWorldTransform());
/* 37:37 */      Vector3f localVector3f5 = GlUtil.f(new Vector3f(), localkd.getWorldTransform());
/* 38:38 */      GlUtil.b(new Vector3f(), localkd.getWorldTransform());
/* 39:   */      
/* 40:40 */      float f = localVector3f2.dot(localVector3f5);
/* 41:41 */      localVector3f3.dot(localVector3f5);
/* 42:   */      
/* 45:45 */      f = (f + 1.0F) / 2.0F * 128.0F;
/* 46:   */      
/* 51:51 */      localVector3f1.dot(localVector3f4);
/* 52:   */      
/* 53:53 */      GL11.glBegin(1);
/* 54:54 */      GlUtil.a(1.0F, 1.0F, 1.0F, 0.4F);
/* 55:55 */      GL11.glVertex2f(0.0F, f);
/* 56:56 */      GL11.glVertex2f(128.0F, f);
/* 57:57 */      GL11.glEnd();
/* 58:   */    }
/* 59:   */  }
/* 60:   */  
/* 63:   */  public final void c() {}
/* 64:   */  
/* 66:   */  public final float a()
/* 67:   */  {
/* 68:68 */    return 128.0F;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public final float b()
/* 72:   */  {
/* 73:73 */    return 128.0F;
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ih
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */