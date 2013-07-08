import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Collections;
import java.util.WeakHashMap;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public final class class_329
{
  public static FloatBuffer a(int paramInt)
  {
    (paramInt = ByteBuffer.allocateDirect(4 * paramInt).order(ByteOrder.nativeOrder()).asFloatBuffer()).clear();
    return paramInt;
  }
  
  static
  {
    new Vector2f();
    new Vector3f();
    new class_36();
    Collections.synchronizedMap(new WeakHashMap());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_329
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */