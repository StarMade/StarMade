/*  1:   */import org.lwjgl.opengl.GL11;
/*  2:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  3:   */
/* 23:   */public class zd
/* 24:   */  extends yX
/* 25:   */  implements zr
/* 26:   */{
/* 27:27 */  private zj jdField_a_of_type_Zj = zk.jdField_a_of_type_Zj;
/* 28:   */  private boolean b;
/* 29:   */  private static zC jdField_a_of_type_ZC;
/* 30:   */  
/* 31:   */  public zd(yW paramyW, float paramFloat) {
/* 32:32 */    super(paramyW, paramFloat);
/* 33:   */    
/* 34:34 */    if ((!c) && (zk.jdField_a_of_type_Zj == null)) throw new AssertionError();
/* 35:35 */    this.jdField_a_of_type_Boolean = true;
/* 36:   */  }
/* 37:   */  
/* 45:   */  public final void b()
/* 46:   */  {
/* 47:47 */    if (!this.b) {
/* 48:48 */      c();
/* 49:   */    }
/* 50:50 */    if (this.jdField_a_of_type_YW.b() > 0)
/* 51:   */    {
/* 52:52 */      this.jdField_a_of_type_Zj.a = this;
/* 53:53 */      this.jdField_a_of_type_Zj.b();
/* 54:54 */      super.b();
/* 55:55 */      this.jdField_a_of_type_Zj.d();
/* 56:   */    }
/* 57:   */  }
/* 58:   */  
/* 59:   */  public final void d()
/* 60:   */  {
/* 61:61 */    GL11.glBindTexture(3553, 0);
/* 62:   */  }
/* 63:   */  
/* 72:   */  public final void c()
/* 73:   */  {
/* 74:74 */    if (jdField_a_of_type_ZC == null) {
/* 75:75 */      jdField_a_of_type_ZC = xe.a().a("starSprite").a().a();
/* 76:   */    }
/* 77:77 */    super.c();
/* 78:78 */    this.b = true;
/* 79:   */  }
/* 80:   */  
/* 93:   */  public final void a(zj paramzj)
/* 94:   */  {
/* 95:95 */    GlUtil.a(paramzj, "time", 0.0F);
/* 96:96 */    GL11.glBindTexture(3553, jdField_a_of_type_ZC.c);
/* 97:97 */    GlUtil.a(paramzj, "tex", 0);
/* 98:   */  }
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zd
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */