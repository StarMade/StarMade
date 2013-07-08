import java.io.PrintStream;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTFramebufferMultisample;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.schema.schine.graphicsengine.core.GLException;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_925
{
  private IntBuffer jdField_field_1148_of_type_JavaNioIntBuffer;
  public int field_1148;
  private int jdField_field_1149_of_type_Int;
  public boolean field_1148;
  public boolean field_1150;
  private boolean field_1151;
  private boolean jdField_field_1149_of_type_Boolean;
  public int field_1150;
  public int field_1151;
  private boolean jdField_field_1152_of_type_Boolean = true;
  private class_925 jdField_field_1148_of_type_Class_925;
  private boolean jdField_field_1153_of_type_Boolean = true;
  private int jdField_field_1152_of_type_Int;
  private int jdField_field_1153_of_type_Int;
  
  private static int b()
  {
    return ((Integer)class_949.field_1238.a4()).intValue();
  }
  
  public class_925(int paramInt1, int paramInt2)
  {
    this.jdField_field_1150_of_type_Int = paramInt1;
    this.jdField_field_1151_of_type_Int = paramInt2;
  }
  
  public final void a()
  {
    System.out.println("[FBO] cleaning up FBO ");
    class_925 localclass_925 = this;
    if (this.jdField_field_1148_of_type_JavaNioIntBuffer != null)
    {
      System.out.println("[FBO] deleting Frame buffers ");
      GL30.glDeleteFramebuffers(localclass_925.jdField_field_1148_of_type_JavaNioIntBuffer);
    }
    else
    {
      System.out.println("[FBO] no Frame buffers to clean up ");
    }
    localclass_925 = this;
    IntBuffer localIntBuffer;
    (localIntBuffer = BufferUtils.createIntBuffer(1)).put(0, localclass_925.jdField_field_1152_of_type_Int);
    localIntBuffer.rewind();
    GL30.glDeleteRenderbuffers(localIntBuffer);
    localIntBuffer.put(0, localclass_925.jdField_field_1153_of_type_Int);
    localIntBuffer.rewind();
    GL30.glDeleteRenderbuffers(localIntBuffer);
    System.out.println("[FBO] deleting  fbo textures " + this.jdField_field_1148_of_type_Int);
    GL11.glDeleteTextures(this.jdField_field_1148_of_type_Int);
    if (this.jdField_field_1148_of_type_Class_925 != null)
    {
      System.out.println("[FBO] deleting BLIN fbo textures " + this.jdField_field_1148_of_type_Class_925.jdField_field_1148_of_type_Int);
      GL11.glDeleteTextures(this.jdField_field_1148_of_type_Class_925.jdField_field_1148_of_type_Int);
    }
    if (this.jdField_field_1149_of_type_Int != 0)
    {
      System.out.println("[FBO] deleting depth fbo texture " + this.jdField_field_1149_of_type_Int);
      GL11.glDeleteTextures(this.jdField_field_1149_of_type_Int);
    }
  }
  
  private static int c()
  {
    IntBuffer localIntBuffer;
    GL30.glGenRenderbuffers(localIntBuffer = BufferUtils.createIntBuffer(1));
    return localIntBuffer.get(0);
  }
  
  private void a1(int paramInt1, int paramInt2, int paramInt3)
  {
    IntBuffer localIntBuffer = BufferUtils.createIntBuffer(16);
    GL11.glGetInteger(34024, localIntBuffer);
    localIntBuffer.rewind();
    int i = localIntBuffer.get(0);
    if ((paramInt1 > i) || (paramInt2 > i)) {
      throw new GLException("height or width of renderbuffer store exceeds max");
    }
    if ((b() == 0) || (!this.jdField_field_1153_of_type_Boolean))
    {
      GL30.glRenderbufferStorage(36161, paramInt3, paramInt2, paramInt1);
      return;
    }
    System.err.println("CREATING MULTISAMPLED RENDERBUFFER. SAMPLES: " + b());
    EXTFramebufferMultisample.glRenderbufferStorageMultisampleEXT(36161, b(), paramInt3, paramInt2, paramInt1);
  }
  
  public final void b1()
  {
    if ((this.jdField_field_1148_of_type_Boolean) && (b() > 0))
    {
      class_925 localclass_925 = this;
      GL30.glBindFramebuffer(36008, localclass_925.jdField_field_1148_of_type_JavaNioIntBuffer.get(0));
      GL30.glBindFramebuffer(36009, localclass_925.jdField_field_1148_of_type_Class_925.jdField_field_1148_of_type_JavaNioIntBuffer.get(0));
      GL30.glBlitFramebuffer(0, 0, localclass_925.jdField_field_1150_of_type_Int, localclass_925.jdField_field_1151_of_type_Int, 0, 0, localclass_925.jdField_field_1150_of_type_Int, localclass_925.jdField_field_1151_of_type_Int, 16384, 9728);
      GL30.glBindFramebuffer(36008, 0);
      GL30.glBindFramebuffer(36009, 0);
    }
    GL30.glBindRenderbuffer(36161, 0);
    GL30.glBindFramebuffer(36160, 0);
    GL11.glViewport(0, 0, class_933.b(), class_933.a());
    this.jdField_field_1148_of_type_Boolean = false;
  }
  
  private static void f()
  {
    GL11.glBegin(7);
    GL11.glTexCoord2f(0.0F, 0.0F);
    GL11.glVertex2f(0.0F, 0.0F);
    GL11.glTexCoord2f(0.0F, 1.0F);
    GL11.glVertex2f(0.0F, class_933.a());
    GL11.glTexCoord2f(1.0F, 1.0F);
    GL11.glVertex2f(class_933.b(), class_933.a());
    GL11.glTexCoord2f(1.0F, 0.0F);
    GL11.glVertex2f(class_933.b(), 0.0F);
    GL11.glEnd();
  }
  
  public final void c1()
  {
    h();
    f();
    g();
  }
  
  public final void a2(class_1377 paramclass_1377)
  {
    h();
    paramclass_1377.b();
    f();
    paramclass_1377.d();
    g();
  }
  
  public final void d()
  {
    GL11.glViewport(0, 0, this.jdField_field_1150_of_type_Int, this.jdField_field_1151_of_type_Int);
    int i = this.jdField_field_1148_of_type_JavaNioIntBuffer.get(0);
    GL30.glBindFramebuffer(36160, i);
    if (!this.jdField_field_1150_of_type_Boolean)
    {
      i = this.jdField_field_1153_of_type_Int;
      GL30.glBindRenderbuffer(36161, i);
    }
    if (!this.jdField_field_1152_of_type_Boolean)
    {
      i = this.jdField_field_1152_of_type_Int;
      GL30.glBindRenderbuffer(36161, i);
    }
    this.jdField_field_1148_of_type_Boolean = true;
  }
  
  private static void g()
  {
    GL11.glEnable(2884);
    GL11.glBindTexture(3553, 0);
    GL11.glDisable(3553);
    GL11.glEnable(2896);
    GL11.glDisable(3042);
    GL11.glEnable(2929);
    GlUtil.a12(5889);
    GlUtil.c2();
    GlUtil.a12(5888);
    GlUtil.c2();
  }
  
  public final int a3()
  {
    if (b() > 0) {
      return this.jdField_field_1148_of_type_Class_925.jdField_field_1148_of_type_Int;
    }
    return this.jdField_field_1148_of_type_Int;
  }
  
  public final void e()
  {
    if (this.jdField_field_1151_of_type_Boolean) {
      return;
    }
    this.jdField_field_1151_of_type_Boolean = true;
    if (this.jdField_field_1149_of_type_Boolean) {
      a();
    }
    System.out.println("[FrameBuffer] creating frame buffer  " + this.jdField_field_1150_of_type_Int + " / " + this.jdField_field_1151_of_type_Int);
    if ((b() > 0) && (this.jdField_field_1153_of_type_Boolean))
    {
      this.jdField_field_1148_of_type_Class_925 = new class_925(this.jdField_field_1150_of_type_Int, this.jdField_field_1151_of_type_Int);
      this.jdField_field_1148_of_type_Class_925.jdField_field_1152_of_type_Boolean = this.jdField_field_1152_of_type_Boolean;
      this.jdField_field_1148_of_type_Class_925.jdField_field_1150_of_type_Boolean = this.jdField_field_1150_of_type_Boolean;
      this.jdField_field_1148_of_type_Class_925.jdField_field_1153_of_type_Boolean = false;
      this.jdField_field_1152_of_type_Boolean = false;
      this.jdField_field_1150_of_type_Boolean = false;
      this.jdField_field_1148_of_type_Class_925.e();
    }
    class_925 localclass_9251 = this;
    this.jdField_field_1148_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
    GL30.glGenFramebuffers(localclass_9251.jdField_field_1148_of_type_JavaNioIntBuffer);
    int j = this.jdField_field_1148_of_type_JavaNioIntBuffer.get(0);
    GL30.glBindFramebuffer(36160, j);
    GlUtil.f1();
    int m;
    int k;
    if (this.jdField_field_1152_of_type_Boolean)
    {
      localclass_9251 = this;
      System.out.println("[FrameBuffer] initializing frame buffer textures ");
      GL11.glGenTextures(GlUtil.a8());
      localclass_9251.jdField_field_1148_of_type_Int = GlUtil.a8().get(0);
      GL11.glBindTexture(3553, localclass_9251.jdField_field_1148_of_type_Int);
      GlUtil.f1();
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GlUtil.f1();
      GL11.glTexParameteri(3553, 10242, 10496);
      GL11.glTexParameteri(3553, 10243, 10496);
      GlUtil.f1();
      GL11.glTexImage2D(3553, 0, 6408, localclass_9251.jdField_field_1150_of_type_Int, localclass_9251.jdField_field_1151_of_type_Int, 0, 6408, 5121, null);
      GlUtil.f1();
      j = this.jdField_field_1148_of_type_Int;
      GL30.glFramebufferTexture2D(36160, 36064, 3553, j, 0);
      GlUtil.f1();
    }
    else
    {
      localclass_9251 = this;
      this.jdField_field_1152_of_type_Int = c();
      j = localclass_9251.jdField_field_1152_of_type_Int;
      GL30.glBindRenderbuffer(36161, j);
      j = localclass_9251.jdField_field_1151_of_type_Int;
      m = localclass_9251.jdField_field_1150_of_type_Int;
      localclass_9251.a1(j, m, 6408);
      k = localclass_9251.jdField_field_1152_of_type_Int;
      GL30.glFramebufferRenderbuffer(36160, 36064, 36161, k);
      GL30.glBindRenderbuffer(36161, 0);
    }
    GlUtil.f1();
    if (this.jdField_field_1150_of_type_Boolean)
    {
      localclass_9251 = this;
      System.err.println("creating depth texture");
      GL11.glGenTextures(GlUtil.a8());
      k = GlUtil.a8().get(0);
      GL11.glBindTexture(3553, k);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexParameteri(3553, 33085, 3);
      GlUtil.f1();
      GL11.glTexParameteri(3553, 10241, 9987);
      GlUtil.f1();
      GL11.glTexParameteri(3553, 10240, 9729);
      GlUtil.f1();
      GL11.glTexParameteri(3553, 33169, 1);
      GL11.glTexParameteri(3553, 34891, 32841);
      GL11.glTexImage2D(3553, 0, 6408, localclass_9251.jdField_field_1150_of_type_Int, localclass_9251.jdField_field_1151_of_type_Int, 0, 6408, 5121, null);
      GlUtil.f1();
      this.jdField_field_1149_of_type_Int = k;
      int i = this.jdField_field_1149_of_type_Int;
      GL30.glFramebufferTexture2D(36160, 36096, 3553, i, 0);
      GlUtil.f1();
    }
    else
    {
      class_925 localclass_9252 = this;
      this.jdField_field_1153_of_type_Int = c();
      j = localclass_9252.jdField_field_1153_of_type_Int;
      GL30.glBindRenderbuffer(36161, j);
      m = localclass_9252.jdField_field_1151_of_type_Int;
      k = localclass_9252.jdField_field_1150_of_type_Int;
      localclass_9252.a1(m, k, 6402);
      j = localclass_9252.jdField_field_1153_of_type_Int;
      GL30.glFramebufferRenderbuffer(36160, 36096, 36161, j);
      GL30.glBindRenderbuffer(36161, 0);
    }
    GlUtil.f1();
    if ((j = GL30.glCheckFramebufferStatus(36160)) != 36053)
    {
      String str = "unknown";
      switch (j)
      {
      case 36061: 
        str = "GL_FRAMEBUFFER_UNSUPPORTED_EXn";
        break;
      case 36054: 
        str = "INCOMPLETE_ATTACHMENT";
        break;
      case 36055: 
        str = "FRAMEBUFFER_MISSING_ATTACHMENT";
        break;
      case 36182: 
        str = "GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE";
        break;
      case 36059: 
        str = "INCOMPLETE_DRAW_BUFFER";
        break;
      case 36060: 
        str = "INCOMPLETE_READ_BUFFER";
        break;
      case 36006: 
        str = "BINDING";
      }
      throw new GLException("FrameBuffer Exception: " + str + ": (" + j + ")");
    }
    GlUtil.f1();
    GL30.glBindFramebuffer(36160, 0);
    GL11.glBindTexture(3553, 0);
    this.jdField_field_1149_of_type_Boolean = true;
    this.jdField_field_1151_of_type_Boolean = false;
    GlUtil.f1();
  }
  
  private void h()
  {
    GlUtil.a12(5889);
    GlUtil.d1();
    GlUtil.b2();
    GlUtil.a14(class_933.b(), 0.0F, class_933.a());
    GlUtil.a12(5888);
    GlUtil.d1();
    GlUtil.b2();
    GL11.glDisable(2896);
    GL11.glDisable(2929);
    GL11.glEnable(3553);
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, a3());
    GL11.glDisable(2884);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_925
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */