/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  15:    */public final class ek
/*  16:    */  extends dO
/*  17:    */  implements dP
/*  18:    */{
/*  19:    */  private ConvexHullShape a;
/*  20:    */  
/*  21:    */  public ek()
/*  22:    */  {
/*  23:    */    ObjectArrayList localObjectArrayList;
/*  24: 24 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  25: 25 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  26: 26 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  27: 27 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  28: 28 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  29: 29 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  30:    */    
/*  31: 31 */    this.a = new ConvexHullShape(localObjectArrayList);
/*  32:    */  }
/*  33:    */  
/*  34:    */  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/*  35: 35 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  36: 36 */      short s2 = s1;
/*  37: 37 */      int i = paramInt;
/*  38: 38 */      boolean bool = false;
/*  39: 39 */      switch (paramInt)
/*  40:    */      {
/*  44:    */      case 2: 
/*  45: 45 */        if (s2 == 0) {
/*  46: 46 */          i = 3;
/*  47: 47 */          s2 = 0;
/*  48: 48 */          bool = true;
/*  49:    */        }
/*  50: 50 */        else if (s2 == 1) {
/*  51: 51 */          i = 3;
/*  52: 52 */          s2 = 3;
/*  53: 53 */          bool = true;
/*  54: 54 */        } else if (s2 == 2) {}
/*  55:    */        break;
/*  56: 56 */      case 1:  if ((s2 != 3) || (goto 134) || 
/*  57:    */        
/*  62: 62 */          (s2 != 1)) {}
/*  63: 63 */        break;
/*  64:    */      
/*  67:    */      case 0: 
/*  68: 68 */        if (s2 == 2)
/*  69: 69 */          s2 = 1; break;
/*  70:    */      
/*  73:    */      case 5: 
/*  74: 74 */        s2 = 0;
/*  75:    */      }
/*  76:    */      
/*  77:    */      
/*  78: 78 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/*  79:    */    }
/*  80:    */  }
/*  81:    */  
/*  82:    */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  83: 83 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  84: 84 */      short s2 = s1;
/*  85: 85 */      int i = paramInt1;
/*  86: 86 */      boolean bool = false;
/*  87: 87 */      switch (paramInt1)
/*  88:    */      {
/*  92:    */      case 2: 
/*  93: 93 */        if (s2 == 0) {
/*  94: 94 */          i = 3;
/*  95: 95 */          s2 = 0;
/*  96: 96 */          bool = true;
/*  97:    */        }
/*  98: 98 */        else if (s2 == 1) {
/*  99: 99 */          i = 3;
/* 100:100 */          s2 = 3;
/* 101:101 */          bool = true;
/* 102:102 */        } else if (s2 == 2) {}
/* 103:    */        break;
/* 104:104 */      case 1:  if ((s2 != 3) || (goto 134) || 
/* 105:    */        
/* 110:110 */          (s2 != 1)) {}
/* 111:111 */        break;
/* 112:    */      
/* 115:    */      case 0: 
/* 116:116 */        if (s2 == 2)
/* 117:117 */          s2 = 1; break;
/* 118:    */      
/* 121:    */      case 5: 
/* 122:122 */        s2 = 0;
/* 123:    */      }
/* 124:    */      
/* 125:    */      
/* 126:126 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 130:    */  protected final ConvexShape a()
/* 131:    */  {
/* 132:132 */    return this.a;
/* 133:    */  }
/* 134:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ek
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */