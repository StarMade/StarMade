import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public final class class_1395
{
  public int field_1590;
  public int field_1591;
  public int field_1592;
  
  public class_1395(int paramInt)
  {
    this.field_1592 = paramInt;
  }
  
  public final void a()
  {
    GL13.glActiveTexture(33984);
    GL11.glEnable(3553);
    GL11.glBindTexture(3553, this.field_1592);
  }
  
  public static void b()
  {
    GL11.glBindTexture(3553, 0);
    GL11.glDisable(3553);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1395
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */