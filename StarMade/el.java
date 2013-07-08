/*   1:    */import com.bulletphysics.collision.shapes.ConvexHullShape;
/*   2:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */
/*  15:    */public final class el
/*  16:    */  extends dO
/*  17:    */  implements dP
/*  18:    */{
/*  19:    */  private ConvexHullShape a;
/*  20:    */  
/*  21:    */  public el()
/*  22:    */  {
/*  23:    */    ObjectArrayList localObjectArrayList;
/*  24: 24 */    (localObjectArrayList = new ObjectArrayList()).add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  25: 25 */    localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  26: 26 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  27: 27 */    localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  28: 28 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  29: 29 */    localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  30:    */    
/*  33: 33 */    this.a = new ConvexHullShape(localObjectArrayList);
/*  34:    */  }
/*  35:    */  
/*  36:    */  public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/*  37: 37 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  38: 38 */      short s2 = s1;
/*  39: 39 */      int i = paramInt;
/*  40: 40 */      boolean bool = false;
/*  41: 41 */      switch (paramInt)
/*  42:    */      {
/*  46:    */      case 2: 
/*  47: 47 */        if (s2 == 3) {
/*  48: 48 */          i = 3;
/*  49: 49 */          s2 = 1;
/*  50: 50 */          bool = true;
/*  51:    */        }
/*  52: 52 */        else if (s2 == 0) {
/*  53: 53 */          i = 3;
/*  54: 54 */          s2 = 0;
/*  55: 55 */          bool = true; } break;
/*  56:    */      
/*  60:    */      case 1: 
/*  61: 61 */        break;
/*  62:    */      
/*  63:    */      case 0: 
/*  64: 64 */        s2 = 0;
/*  65: 65 */        break;
/*  66:    */      
/*  67:    */      case 5: 
/*  68: 68 */        if (s2 == 3)
/*  69: 69 */          s2 = 0; break;
/*  70:    */      
/*  73:    */      case 4: 
/*  74: 74 */        if (s2 == 0) {
/*  75: 75 */          s2 = 3;
/*  76:    */        }
/*  77:    */        break;
/*  78:    */      }
/*  79:    */      
/*  80: 80 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, 8421504.0F, paramFloatBuffer);
/*  81:    */    }
/*  82:    */  }
/*  83:    */  
/*  84:    */  public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  85: 85 */    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  86: 86 */      short s2 = s1;
/*  87: 87 */      int i = paramInt1;
/*  88: 88 */      boolean bool = false;
/*  89: 89 */      switch (paramInt1)
/*  90:    */      {
/*  94:    */      case 2: 
/*  95: 95 */        if (s2 == 3) {
/*  96: 96 */          i = 3;
/*  97: 97 */          s2 = 1;
/*  98: 98 */          bool = true;
/*  99:    */        }
/* 100:100 */        else if (s2 == 0) {
/* 101:101 */          i = 3;
/* 102:102 */          s2 = 0;
/* 103:103 */          bool = true; } break;
/* 104:    */      
/* 108:    */      case 1: 
/* 109:109 */        break;
/* 110:    */      
/* 111:    */      case 0: 
/* 112:112 */        s2 = 0;
/* 113:113 */        break;
/* 114:    */      
/* 115:    */      case 5: 
/* 116:116 */        if (s2 == 3)
/* 117:117 */          s2 = 0; break;
/* 118:    */      
/* 121:    */      case 4: 
/* 122:122 */        if (s2 == 0) {
/* 123:123 */          s2 = 3;
/* 124:    */        }
/* 125:    */        break;
/* 126:    */      }
/* 127:    */      
/* 128:128 */      a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
/* 129:    */    }
/* 130:    */  }
/* 131:    */  
/* 132:    */  protected final ConvexShape a()
/* 133:    */  {
/* 134:134 */    return this.a;
/* 135:    */  }
/* 136:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     el
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */