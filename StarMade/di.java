/*  1:   */import org.lwjgl.opengl.GL11;
/*  2:   */import org.lwjgl.opengl.GL13;
/*  3:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  4:   */
/*  7:   */public final class di
/*  8:   */  implements zr
/*  9:   */{
/* 10:   */  private int a;
/* 11:   */  
/* 12:   */  public di(int paramInt)
/* 13:   */  {
/* 14:14 */    this.a = paramInt;
/* 15:   */  }
/* 16:   */  
/* 17:   */  public final void d()
/* 18:   */  {
/* 19:19 */    GL11.glBindTexture(3553, 0);
/* 20:   */  }
/* 21:   */  
/* 28:   */  public final void a(zj paramzj)
/* 29:   */  {
/* 30:30 */    GlUtil.a(paramzj, "selectionColor", 0.7F, 0.7F, 0.7F, 1.0F);
/* 31:   */    
/* 32:32 */    GL13.glActiveTexture(33984);
/* 33:33 */    GL11.glBindTexture(3553, this.a);
/* 34:34 */    GlUtil.a(paramzj, "mainTexA", 0);
/* 35:   */  }
/* 36:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     di
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */