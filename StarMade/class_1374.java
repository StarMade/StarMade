import org.lwjgl.opengl.GL20;

final class class_1374
  extends class_1377
{
  class_1374(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public final void a(int paramInt)
  {
    GL20.glBindAttribLocation(paramInt, 3, "indices");
    GL20.glBindAttribLocation(paramInt, 4, "weights");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1374
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */