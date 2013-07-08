/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  19:    */public final class em
/*  20:    */  extends dO
/*  21:    */  implements dP
/*  22:    */{
/*  23:    */  private ConvexHullShape a;
/*  24:    */  
/*  25:    */  public em()
/*  26:    */  {
/*  27:    */    ObjectArrayList localObjectArrayList;
/*  28: 28 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  29: 29 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  30: 30 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  31: 31 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  32: 32 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  33: 33 */    localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  34:    */    
/*  35: 35 */    this.a = new ConvexHullShape(localObjectArrayList);
/*  36:    */  }
/*  37:    */  
/*  38:    */  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/*  39: 39 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  40: 40 */      short s2 = s1;
/*  41: 41 */      int i = paramInt;
/*  42: 42 */      boolean bool = false;
/*  43: 43 */      switch (paramInt)
/*  44:    */      {
/*  48:    */      case 2: 
/*  49: 49 */        if (s2 == 1) {
/*  50: 50 */          i = 3;
/*  51: 51 */          s2 = 3;
/*  52: 52 */          bool = true;
/*  53:    */        }
/*  54: 54 */        else if (s2 == 2) {
/*  55: 55 */          i = 3;
/*  56: 56 */          s2 = 2;
/*  57: 57 */          bool = true; } break;
/*  58:    */      
/*  62:    */      case 0: 
/*  63: 63 */        break;
/*  64:    */      
/*  65:    */      case 1: 
/*  66: 66 */        s2 = 0;
/*  67: 67 */        break;
/*  68:    */      
/*  69:    */      case 5: 
/*  70: 70 */        if (s2 == 2)
/*  71: 71 */          s2 = 1; break;
/*  72:    */      
/*  75:    */      case 4: 
/*  76: 76 */        if (s2 == 1) {
/*  77: 77 */          s2 = 2;
/*  78:    */        }
/*  79:    */        break;
/*  80:    */      }
/*  81:    */      
/*  82: 82 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/*  83:    */    }
/*  84:    */  }
/*  85:    */  
/*  86: 86 */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer) { for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  87: 87 */      short s2 = s1;
/*  88: 88 */      int i = paramInt1;
/*  89: 89 */      boolean bool = false;
/*  90: 90 */      switch (paramInt1)
/*  91:    */      {
/*  95:    */      case 2: 
/*  96: 96 */        if (s2 == 1) {
/*  97: 97 */          i = 3;
/*  98: 98 */          s2 = 3;
/*  99: 99 */          bool = true;
/* 100:    */        }
/* 101:101 */        else if (s2 == 2) {
/* 102:102 */          i = 3;
/* 103:103 */          s2 = 2;
/* 104:104 */          bool = true; } break;
/* 105:    */      
/* 109:    */      case 0: 
/* 110:110 */        break;
/* 111:    */      
/* 112:    */      case 1: 
/* 113:113 */        s2 = 0;
/* 114:114 */        break;
/* 115:    */      
/* 116:    */      case 5: 
/* 117:117 */        if (s2 == 2)
/* 118:118 */          s2 = 1; break;
/* 119:    */      
/* 122:    */      case 4: 
/* 123:123 */        if (s2 == 1) {
/* 124:124 */          s2 = 2;
/* 125:    */        }
/* 126:    */        break;
/* 127:    */      }
/* 128:    */      
/* 129:129 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
/* 130:    */    }
/* 131:    */  }
/* 132:    */  
/* 135:    */  protected final ConvexShape a()
/* 136:    */  {
/* 137:137 */    return this.a;
/* 138:    */  }
/* 139:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     em
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */