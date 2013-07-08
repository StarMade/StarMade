/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  15:    */public final class ej
/*  16:    */  extends dO
/*  17:    */  implements dP
/*  18:    */{
/*  19:    */  private ConvexHullShape a;
/*  20:    */  
/*  21:    */  public ej()
/*  22:    */  {
/*  23:    */    ObjectArrayList localObjectArrayList;
/*  24: 24 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  25: 25 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  26: 26 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  27: 27 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  28: 28 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  29: 29 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30:    */    
/*  31: 31 */    this.a = new ConvexHullShape(localObjectArrayList);
/*  32:    */  }
/*  33:    */  
/*  34:    */  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer)
/*  35:    */  {
/*  36: 36 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  37: 37 */      short s2 = s1;
/*  38: 38 */      int i = paramInt;
/*  39: 39 */      boolean bool = false;
/*  40: 40 */      switch (paramInt)
/*  41:    */      {
/*  45:    */      case 2: 
/*  46: 46 */        if (s2 == 2) {
/*  47: 47 */          i = 3;
/*  48: 48 */          s2 = 2;
/*  49: 49 */          bool = true;
/*  50:    */        }
/*  51: 51 */        else if (s2 == 3) {
/*  52: 52 */          i = 3;
/*  53: 53 */          s2 = 1;
/*  54: 54 */          bool = true; } break;
/*  55:    */      
/*  58:    */      case 1: 
/*  59: 59 */        if (s2 == 0)
/*  60: 60 */          s2 = 3; break;
/*  61:    */      
/*  64:    */      case 0: 
/*  65: 65 */        if (s2 == 3)
/*  66: 66 */          s2 = 2; break;
/*  67:    */      
/*  70:    */      case 4: 
/*  71: 71 */        s2 = 0;
/*  72:    */      }
/*  73:    */      
/*  74:    */      
/*  75: 75 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/*  76:    */    }
/*  77:    */  }
/*  78:    */  
/*  79:    */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer)
/*  80:    */  {
/*  81: 81 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  82: 82 */      short s2 = s1;
/*  83: 83 */      int i = paramInt1;
/*  84: 84 */      boolean bool = false;
/*  85: 85 */      switch (paramInt1)
/*  86:    */      {
/*  90:    */      case 2: 
/*  91: 91 */        if (s2 == 2) {
/*  92: 92 */          i = 3;
/*  93: 93 */          s2 = 2;
/*  94: 94 */          bool = true;
/*  95:    */        }
/*  96: 96 */        else if (s2 == 3) {
/*  97: 97 */          i = 3;
/*  98: 98 */          s2 = 1;
/*  99: 99 */          bool = true; } break;
/* 100:    */      
/* 103:    */      case 1: 
/* 104:104 */        if (s2 == 0)
/* 105:105 */          s2 = 3; break;
/* 106:    */      
/* 109:    */      case 0: 
/* 110:110 */        if (s2 == 3)
/* 111:111 */          s2 = 2; break;
/* 112:    */      
/* 115:    */      case 4: 
/* 116:116 */        s2 = 0;
/* 117:    */      }
/* 118:    */      
/* 119:    */      
/* 120:120 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
/* 121:    */    }
/* 122:    */  }
/* 123:    */  
/* 124:    */  protected final ConvexShape a()
/* 125:    */  {
/* 126:126 */    return this.a;
/* 127:    */  }
/* 128:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ej
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */