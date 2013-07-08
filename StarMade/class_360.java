import com.bulletphysics.collision.shapes.ConvexShape;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;

public class class_360
  extends class_384
{
  public final void a1(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer)
  {
    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1))
    {
      short s2 = s1;
      int i = paramInt1;
      boolean bool = false;
      switch (paramInt1)
      {
      case 3: 
        s2 = 0;
        break;
      case 2: 
        s2 = 0;
        break;
      case 0: 
        if (s2 == 0)
        {
          i = 1;
          s2 = 3;
          bool = true;
        }
        else if ((s2 != 1) && (s2 != 2) && (s2 == 3))
        {
          i = 1;
          s2 = 0;
          bool = true;
        }
        break;
      case 1: 
        if (s2 != 0) {
          if (s2 == 1)
          {
            i = 0;
            s2 = 2;
            bool = true;
          }
          else if (s2 == 2)
          {
            i = 0;
            s2 = 1;
            bool = true;
          }
        }
        break;
      case 4: 
        if ((s2 == 3) && (goto 263) && (s2 != 0)) {
          if (s2 == 1)
          {
            i = 5;
            s2 = 2;
            bool = true;
          }
          else if (s2 == 2)
          {
            i = 5;
            s2 = 1;
            bool = true;
          }
          else if (s2 != 3) {}
        }
        break;
      case 5: 
        if (s2 == 0)
        {
          i = 4;
          s2 = 3;
          bool = true;
        }
        else if ((s2 != 1) && (s2 != 2) && (s2 == 3))
        {
          i = 4;
          s2 = 0;
          bool = true;
        }
        break;
      }
      a3(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
    }
  }
  
  protected final ConvexShape a2()
  {
    if (!field_102) {
      throw new AssertionError();
    }
    throw new IllegalArgumentException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_360
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */