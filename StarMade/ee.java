/*  1:   */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*  2:   */import com.bulletphysics.collision.shapes.ConvexShape;
/*  3:   */import com.bulletphysics.util.ObjectArrayList;
/*  4:   */import java.nio.FloatBuffer;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*  7:   */
/* 15:   */public final class ee
/* 16:   */  extends dO
/* 17:   */  implements dP
/* 18:   */{
/* 19:   */  private ConvexHullShape a;
/* 20:   */  
/* 21:   */  public ee()
/* 22:   */  {
/* 23:   */    ObjectArrayList localObjectArrayList;
/* 24:24 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, -0.5F, -0.5F));
/* 25:25 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/* 26:26 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/* 27:27 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/* 28:28 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/* 29:29 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/* 30:   */    
/* 31:31 */    this.a = new ConvexHullShape(localObjectArrayList);
/* 32:   */  }
/* 33:   */  
/* 34:34 */  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) { for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/* 35:35 */      short s2 = s1;
/* 36:36 */      int i = paramInt;
/* 37:37 */      boolean bool = false;
/* 38:38 */      switch (paramInt)
/* 39:   */      {
/* 43:   */      case 2: 
/* 44:44 */        if (s2 == 0) {
/* 45:45 */          i = 3;
/* 46:46 */          s2 = 0;
/* 47:47 */          bool = true;
/* 48:   */        }
/* 49:49 */        else if (s2 == 1) {
/* 50:50 */          i = 3;
/* 51:51 */          s2 = 3;
/* 52:52 */          bool = true;
/* 53:53 */        } else if (s2 == 2) {}
/* 54:   */        break;
/* 55:55 */      case 1:  if ((s2 != 3) || (goto 134) || 
/* 56:   */        
/* 61:61 */          (s2 != 1)) {}
/* 62:62 */        break;
/* 63:   */      
/* 66:   */      case 0: 
/* 67:67 */        if (s2 == 2)
/* 68:68 */          s2 = 1; break;
/* 69:   */      
/* 72:   */      case 5: 
/* 73:73 */        s2 = 0;
/* 74:   */      }
/* 75:   */      
/* 76:   */      
/* 77:77 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/* 78:   */    }
/* 79:   */  }
/* 80:   */  
/* 82:   */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer) {}
/* 83:   */  
/* 85:   */  protected final ConvexShape a()
/* 86:   */  {
/* 87:87 */    return this.a;
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ee
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */