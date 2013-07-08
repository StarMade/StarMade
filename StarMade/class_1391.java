import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_1391
  extends class_1385
{
  public final void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, ByteBuffer paramByteBuffer)
  {
    GL11.glEnable(32879);
    GL11.glBindTexture(32879, this.field_1582);
    GlUtil.f1();
    GL11.glTexParameteri(32879, 10241, 9729);
    GL11.glTexParameteri(32879, 10240, 9729);
    GL11.glTexParameteri(32879, 10242, 10497);
    GL11.glTexParameteri(32879, 10243, 10497);
    GL11.glTexParameteri(32879, 32882, 10497);
    GlUtil.f1();
    if (paramInt5 == 8194)
    {
      GL12.glTexImage3D(32879, paramInt1, 6408, paramInt2, paramInt3, paramInt4, 0, 6403, 5121, paramByteBuffer);
      GlUtil.f1();
      a2(paramInt1, paramByteBuffer == null ? paramInt2 * paramInt3 * paramInt4 : paramByteBuffer.remaining());
    }
    else
    {
      GL12.glTexImage3D(32879, paramInt1, 6408, paramInt2, paramInt3, paramInt4, 0, paramInt5, 5121, paramByteBuffer);
      GlUtil.f1();
      a2(paramInt1, paramByteBuffer == null ? paramInt2 * paramInt3 * paramInt4 << 2 : paramByteBuffer.remaining());
    }
    GlUtil.f1();
    GL11.glBindTexture(32879, 0);
    GL11.glDisable(32879);
    GlUtil.f1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1391
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */