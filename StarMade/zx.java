/*   1:    */import java.nio.FloatBuffer;
/*   2:    */import org.lwjgl.BufferUtils;
/*   3:    */import org.lwjgl.opengl.GL11;
/*   4:    */import org.lwjgl.opengl.GL13;
/*   5:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   6:    */
/*  18:    */public final class zx
/*  19:    */  implements xg, zr
/*  20:    */{
/*  21:    */  private xi jdField_a_of_type_Xi;
/*  22:    */  private xi b;
/*  23:    */  private xi c;
/*  24:    */  private float[] jdField_a_of_type_ArrayOfFloat;
/*  25: 25 */  private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(10);
/*  26:    */  
/*  27:    */  public zx(xi paramxi1, xi paramxi2, xi paramxi3)
/*  28:    */  {
/*  29: 29 */    this.jdField_a_of_type_Xi = paramxi1;
/*  30:    */    
/*  31: 31 */    this.b = paramxi2;
/*  32: 32 */    this.c = paramxi3;
/*  33: 33 */    this.jdField_a_of_type_ArrayOfFloat = zu.a();
/*  34: 34 */    this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  35: 35 */    for (paramxi1 = 9; paramxi1 >= 0; paramxi1--) {
/*  36: 36 */      this.jdField_a_of_type_JavaNioFloatBuffer.put(this.jdField_a_of_type_ArrayOfFloat[paramxi1]);
/*  37:    */    }
/*  38: 38 */    this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  39:    */  }
/*  40:    */  
/*  45:    */  public final void a() {}
/*  46:    */  
/*  50:    */  public final void b()
/*  51:    */  {
/*  52:    */    zj localzj;
/*  53:    */    
/*  56: 56 */    (localzj = zk.p).a = this;
/*  57: 57 */    this.jdField_a_of_type_Xi.a(localzj);
/*  58:    */  }
/*  59:    */  
/*  67:    */  public final void d()
/*  68:    */  {
/*  69: 69 */    GL13.glActiveTexture(33984);
/*  70: 70 */    GL11.glBindTexture(3553, 0);
/*  71: 71 */    GL13.glActiveTexture(33985);
/*  72: 72 */    GL11.glBindTexture(3553, 0);
/*  73: 73 */    GL13.glActiveTexture(33984);
/*  74:    */  }
/*  75:    */  
/*  89:    */  public final void c() {}
/*  90:    */  
/* 104:    */  public final void a(zj paramzj)
/* 105:    */  {
/* 106:106 */    GlUtil.c(paramzj, "MVP", zu.jdField_a_of_type_JavaNioFloatBuffer);
/* 107:    */    
/* 115:115 */    GlUtil.a(paramzj, "Width", this.b.b);
/* 116:    */    
/* 120:120 */    this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 121:121 */    GlUtil.a(paramzj, "Weight", this.jdField_a_of_type_JavaNioFloatBuffer);
/* 122:    */    
/* 123:123 */    GL13.glActiveTexture(33984);
/* 124:124 */    GL11.glBindTexture(3553, this.jdField_a_of_type_Xi.a());
/* 125:125 */    GlUtil.a(paramzj, "RenderTex", 0);
/* 126:    */    
/* 127:127 */    GL13.glActiveTexture(33985);
/* 128:128 */    GL11.glBindTexture(3553, this.c.a());
/* 129:129 */    GlUtil.a(paramzj, "BlurTex", 1);
/* 130:    */    
/* 131:131 */    GL13.glActiveTexture(33984);
/* 132:    */  }
/* 133:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */