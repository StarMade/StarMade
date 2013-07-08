/*   1:    */import java.nio.FloatBuffer;
/*   2:    */import org.lwjgl.BufferUtils;
/*   3:    */import org.lwjgl.opengl.GL11;
/*   4:    */import org.lwjgl.opengl.GL13;
/*   5:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   6:    */
/*  18:    */public final class zw
/*  19:    */  implements xg, zr
/*  20:    */{
/*  21:    */  private xi jdField_a_of_type_Xi;
/*  22:    */  private xi b;
/*  23:    */  private xi c;
/*  24:    */  private float[] jdField_a_of_type_ArrayOfFloat;
/*  25: 25 */  private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(10);
/*  26:    */  
/*  27:    */  public zw(xi paramxi1, xi paramxi2, xi paramxi3)
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
/*  56: 56 */    (localzj = zk.o).a = this;
/*  57:    */    
/*  59: 59 */    this.c.d();
/*  60: 60 */    GL11.glClear(16640);
/*  61: 61 */    this.jdField_a_of_type_Xi.a(localzj);
/*  62: 62 */    this.c.b();
/*  63:    */  }
/*  64:    */  
/*  73:    */  public final void d()
/*  74:    */  {
/*  75: 75 */    GL13.glActiveTexture(33984);
/*  76: 76 */    GL11.glBindTexture(3553, 0);
/*  77: 77 */    GL13.glActiveTexture(33985);
/*  78: 78 */    GL11.glBindTexture(3553, 0);
/*  79: 79 */    GL13.glActiveTexture(33984);
/*  80:    */  }
/*  81:    */  
/*  95:    */  public final void c() {}
/*  96:    */  
/* 110:    */  public final void a(zj paramzj)
/* 111:    */  {
/* 112:112 */    GlUtil.c(paramzj, "MVP", zu.jdField_a_of_type_JavaNioFloatBuffer);
/* 113:    */    
/* 121:121 */    GlUtil.a(paramzj, "Height", this.b.c);
/* 122:    */    
/* 124:124 */    this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 125:125 */    GlUtil.a(paramzj, "Weight", this.jdField_a_of_type_JavaNioFloatBuffer);
/* 126:    */    
/* 130:130 */    GL13.glActiveTexture(33985);
/* 131:131 */    GL11.glBindTexture(3553, this.b.a());
/* 132:132 */    GlUtil.a(paramzj, "BlurTex", 1);
/* 133:    */    
/* 134:134 */    GL13.glActiveTexture(33984);
/* 135:    */  }
/* 136:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */