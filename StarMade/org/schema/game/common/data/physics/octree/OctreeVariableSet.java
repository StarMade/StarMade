/*   1:    */package org.schema.game.common.data.physics.octree;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.util.HashMap;
/*   5:    */import javax.vecmath.Matrix3f;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import o;
/*   8:    */import org.lwjgl.BufferUtils;
/*   9:    */
/*  12:    */public class OctreeVariableSet
/*  13:    */{
/*  14:    */  public boolean dr;
/*  15: 15 */  boolean first = true;
/*  16: 16 */  o min = new o();
/*  17: 17 */  o max = new o();
/*  18: 18 */  Vector3f tmpMin = new Vector3f();
/*  19: 19 */  Vector3f tmpMax = new Vector3f();
/*  20: 20 */  Vector3f tmpMinOut = new Vector3f();
/*  21: 21 */  Vector3f tmpMaxOut = new Vector3f();
/*  22: 22 */  Vector3f tmpMin2 = new Vector3f();
/*  23: 23 */  Vector3f tmpMax2 = new Vector3f();
/*  24: 24 */  Vector3f tmpMinOut2 = new Vector3f();
/*  25: 25 */  Vector3f tmpMaxOut2 = new Vector3f();
/*  26: 26 */  Vector3f tmpDistTest = new Vector3f();
/*  27:    */  
/*  31:    */  private boolean cacheInitialized;
/*  32:    */  
/*  35:    */  public static final int MAX_IDS = 4097;
/*  36:    */  
/*  39: 39 */  public TreeCache[] treeCache = new TreeCache[4096];
/*  40:    */  
/*  42: 42 */  float[] param = new float[1];
/*  43:    */  
/*  44: 44 */  Vector3f normal = new Vector3f();
/*  45:    */  
/*  46: 46 */  short gen = 0;
/*  47:    */  
/*  48: 48 */  private OctreeLevel tmp = new OctreeLevel();
/*  49: 49 */  public HashMap map = new HashMap();
/*  50:    */  
/*  52: 52 */  public ByteBuffer mapV = BufferUtils.createByteBuffer(7020);
/*  53:    */  
/*  56:    */  public int maxLevel;
/*  57:    */  
/*  59: 59 */  public Vector3f localHalfExtents = new Vector3f();
/*  60: 60 */  public Vector3f localCenter = new Vector3f();
/*  61: 61 */  public Matrix3f abs_b = new Matrix3f();
/*  62: 62 */  public Vector3f center = new Vector3f();
/*  63: 63 */  public Vector3f extend = new Vector3f();
/*  64: 64 */  public Vector3f tmpAB = new Vector3f();
/*  65:    */  public boolean debug;
/*  66: 66 */  public final Vector3f aabbHalfExtent = new Vector3f();
/*  67: 67 */  public final Vector3f aabbCenter = new Vector3f();
/*  68: 68 */  public final Vector3f source = new Vector3f();
/*  69: 69 */  public final Vector3f target = new Vector3f();
/*  70: 70 */  public final Vector3f r = new Vector3f();
/*  71: 71 */  public final Vector3f hitNormal = new Vector3f();
/*  72:    */  
/*  73: 73 */  static { localHalfExtend = new Vector3f();
/*  74: 74 */    localHalfExtends = new Vector3f[4];
/*  75: 75 */    localCentersAdd = new Vector3f[4];
/*  76:    */    
/*  78: 78 */    int i = 8;
/*  79: 79 */    for (int j = 0; j < localHalfExtends.length; j++) {
/*  80: 80 */      Vector3f localVector3f1 = new Vector3f(-i, -i, -i);
/*  81: 81 */      Vector3f localVector3f2 = new Vector3f(i, i, i);
/*  82:    */      
/*  83: 83 */      localCentersAdd[j] = new Vector3f();
/*  84: 84 */      localHalfExtends[j] = new Vector3f();
/*  85: 85 */      localHalfExtends[j].sub(localVector3f2, localVector3f1);
/*  86: 86 */      localHalfExtends[j].scale(0.5F);
/*  87:    */      
/*  88: 88 */      localHalfExtends[j].x += 0.1F;
/*  89: 89 */      localHalfExtends[j].y += 0.1F;
/*  90: 90 */      localHalfExtends[j].z += 0.1F;
/*  91:    */      
/*  92: 92 */      localCentersAdd[j].set(i, i, i);
/*  93: 93 */      i /= 2;
/*  94:    */    }
/*  95:    */  }
/*  96:    */  
/* 107:    */  public static Vector3f localHalfExtend;
/* 108:    */  
/* 117:    */  public static Vector3f[] localHalfExtends;
/* 118:    */  
/* 127:    */  public void get(short paramShort, o paramo)
/* 128:    */  {
/* 129:129 */    paramShort *= 3;
/* 130:130 */    paramo.b(this.mapV.get(paramShort), this.mapV.get(paramShort + 1), this.mapV.get(paramShort + 2));
/* 131:    */  }
/* 132:    */  
/* 133:133 */  public void get(short paramShort, Vector3f paramVector3f) { paramShort *= 3;
/* 134:134 */    paramVector3f.set(this.mapV.get(paramShort), this.mapV.get(paramShort + 1), this.mapV.get(paramShort + 2));
/* 135:    */  }
/* 136:    */  
/* 137:137 */  public byte getX(short paramShort) { return this.mapV.get(paramShort * 3); }
/* 138:    */  
/* 139:    */  public byte getY(short paramShort) {
/* 140:140 */    return this.mapV.get(paramShort * 3 + 1);
/* 141:    */  }
/* 142:    */  
/* 143:143 */  public byte getZ(short paramShort) { return this.mapV.get(paramShort * 3 + 2); }
/* 144:    */  
/* 156:    */  public static Vector3f[] localCentersAdd;
/* 157:    */  
/* 168:    */  public static int nodes;
/* 169:    */  
/* 179:    */  public short getId(byte paramByte, int paramInt1, int paramInt2)
/* 180:    */  {
/* 181:181 */    this.tmp.level = paramByte;
/* 182:182 */    this.tmp.index = paramInt1;
/* 183:183 */    this.tmp.id = paramInt2;
/* 184:    */    
/* 185:185 */    return ((Short)this.map.get(this.tmp)).shortValue();
/* 186:    */  }
/* 187:    */  
/* 188:188 */  public void initializeCache() { if (!this.cacheInitialized)
/* 189:    */    {
/* 190:190 */      for (int i = 0; i < this.treeCache.length; i++) {
/* 191:191 */        this.treeCache[i] = new TreeCache();
/* 192:    */      }
/* 193:193 */      this.cacheInitialized = true;
/* 194:    */    }
/* 195:    */  }
/* 196:    */  
/* 197:    */  public short put(byte paramByte, int paramInt1, int paramInt2, o paramo) { OctreeLevel localOctreeLevel;
/* 198:198 */    (localOctreeLevel = new OctreeLevel()).level = paramByte;
/* 199:199 */    localOctreeLevel.index = paramInt1;
/* 200:200 */    localOctreeLevel.id = paramInt2;
/* 201:201 */    assert (!this.map.containsKey(localOctreeLevel)) : (paramByte + "; " + paramInt1 + "; " + paramInt2 + ": " + this.map);
/* 202:202 */    this.map.put(localOctreeLevel, Short.valueOf(this.gen));
/* 203:203 */    this.mapV.put(this.gen * 3, paramo.a);
/* 204:204 */    this.mapV.put(this.gen * 3 + 1, paramo.b);
/* 205:205 */    this.mapV.put(this.gen * 3 + 2, paramo.c);
/* 206:206 */    paramByte = this.gen;
/* 207:207 */    this.gen = ((short)(this.gen + 1));
/* 208:    */    
/* 209:209 */    return paramByte;
/* 210:    */  }
/* 211:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */