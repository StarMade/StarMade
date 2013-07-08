import com.bulletphysics.collision.shapes.ConvexHullShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.util.ObjectArrayList;
import java.nio.FloatBuffer;
import javax.vecmath.Vector3f;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;

public final class class_277
  extends class_384
  implements class_378
{
  private ConvexHullShape field_102;
  
  public class_277()
  {
    ObjectArrayList localObjectArrayList;
    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, -0.5F, -0.5F));
    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
    this.field_102 = new ConvexHullShape(localObjectArrayList);
  }
  
  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer)
  {
    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1))
    {
      short s2 = s1;
      int i = paramInt;
      boolean bool = false;
      switch (paramInt)
      {
      case 2: 
        if (s2 == 0)
        {
          i = 3;
          s2 = 0;
          bool = true;
        }
        else if (s2 == 1)
        {
          i = 3;
          s2 = 3;
          bool = true;
        }
        break;
      case 1: 
        if ((s2 == 2) || (s2 != 3) || (goto 134) || (s2 != 1)) {}
        break;
      case 0: 
        if (s2 == 2) {
          s2 = 1;
        }
        break;
      case 5: 
        s2 = 0;
      }
      a4(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
    }
  }
  
  public final void a1(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer)
  {
    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1))
    {
      short s2 = s1;
      int i = paramInt1;
      boolean bool = false;
      switch (paramInt1)
      {
      case 2: 
        if (s2 == 0)
        {
          i = 3;
          s2 = 0;
          bool = true;
        }
        else if (s2 == 1)
        {
          i = 3;
          s2 = 3;
          bool = true;
        }
        break;
      case 1: 
        if ((s2 == 2) || (s2 != 3) || (goto 134) || (s2 != 1)) {}
        break;
      case 0: 
        if (s2 == 2) {
          s2 = 1;
        }
        break;
      case 5: 
        s2 = 0;
      }
      a3(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
    }
  }
  
  protected final ConvexShape a2()
  {
    return this.field_102;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_277
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */