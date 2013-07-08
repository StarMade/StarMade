/*  1:   */import org.lwjgl.opengl.GL11;
/*  2:   */import org.lwjgl.opengl.GL13;
/*  3:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  4:   */
/* 15:   */public final class zv
/* 16:   */  implements xg, zr
/* 17:   */{
/* 18:   */  private xi jdField_a_of_type_Xi;
/* 19:   */  private xi b;
/* 20:   */  private zy jdField_a_of_type_Zy;
/* 21:   */  
/* 22:   */  public zv(xi paramxi1, xi paramxi2, zy paramzy)
/* 23:   */  {
/* 24:24 */    this.jdField_a_of_type_Xi = paramxi1;
/* 25:   */    
/* 26:26 */    this.b = paramxi2;
/* 27:27 */    this.jdField_a_of_type_Zy = paramzy;
/* 28:   */  }
/* 29:   */  
/* 32:   */  public final void a() {}
/* 33:   */  
/* 35:   */  public final void b()
/* 36:   */  {
/* 37:   */    zj localzj;
/* 38:   */    
/* 39:39 */    (localzj = zk.n).a = this;
/* 40:   */    
/* 41:41 */    this.b.d();
/* 42:42 */    GL11.glClear(16640);
/* 43:43 */    this.jdField_a_of_type_Xi.a(localzj);
/* 44:44 */    this.b.b();
/* 45:   */  }
/* 46:   */  
/* 55:   */  public final void d()
/* 56:   */  {
/* 57:57 */    GL13.glActiveTexture(33984);
/* 58:58 */    GL11.glBindTexture(3553, 0);
/* 59:59 */    GL13.glActiveTexture(33985);
/* 60:60 */    GL11.glBindTexture(3553, 0);
/* 61:61 */    GL13.glActiveTexture(33984);
/* 62:   */  }
/* 63:   */  
/* 72:   */  public final void c() {}
/* 73:   */  
/* 81:   */  public final void a(zj paramzj)
/* 82:   */  {
/* 83:83 */    zy.d();
/* 84:   */    
/* 85:85 */    GlUtil.c(paramzj, "MVP", zu.a);
/* 86:   */    
/* 88:88 */    GlUtil.a(paramzj, "LumThresh", 1.0F - ((Float)xu.ae.a()).floatValue());
/* 89:   */    
/* 90:90 */    GL13.glActiveTexture(33984);
/* 91:91 */    GL11.glBindTexture(3553, this.jdField_a_of_type_Xi.a());
/* 92:92 */    GlUtil.a(paramzj, "RenderTex", 0);
/* 93:   */    
/* 94:94 */    GL13.glActiveTexture(33985);
/* 95:95 */    GL11.glBindTexture(3553, this.jdField_a_of_type_Zy.a);
/* 96:96 */    GlUtil.a(paramzj, "SilhouetteTex", 1);
/* 97:97 */    GL13.glActiveTexture(33984);
/* 98:   */  }
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */