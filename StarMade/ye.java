/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import it.unimi.dsi.fastutil.ints.IntSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.util.Collection;
/*   7:    */import java.util.HashMap;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.List;
/*  10:    */import java.util.Map.Entry;
/*  11:    */
/*  69:    */public class ye
/*  70:    */{
/*  71:    */  private xN jdField_a_of_type_XN;
/*  72: 72 */  private final Int2ObjectOpenHashMap jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
/*  73: 73 */  private final Int2ObjectOpenHashMap jdField_b_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
/*  74:    */  
/*  77:    */  private boolean jdField_a_of_type_Boolean;
/*  78:    */  
/*  80:    */  private Transform[] jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform;
/*  81:    */  
/*  83: 83 */  private final HashMap jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*  84:    */  
/*  95:    */  public final Transform[] a()
/*  96:    */  {
/*  97: 97 */    if (this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform == null) {
/*  98: 98 */      this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform = new Transform[this.jdField_b_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.size()];
/*  99: 99 */      for (int i = 0; i < this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform.length; i++) {
/* 100:100 */        this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[i] = new Transform();
/* 101:101 */        this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[i].setIdentity();
/* 102:    */      }
/* 103:103 */      if ((!jdField_b_of_type_Boolean) && (this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform.length != 29)) throw new AssertionError(this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform.length);
/* 104:    */    }
/* 105:105 */    for (Map.Entry localEntry : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.entrySet()) {
/* 106:106 */      Transform localTransform = (Transform)this.jdField_b_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get((Integer)localEntry.getKey());
/* 107:107 */      ((xN)localEntry.getValue()).a(localTransform);
/* 108:108 */      this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[((xN)localEntry.getValue()).jdField_a_of_type_Int].set(localTransform);
/* 109:    */    }
/* 110:110 */    return this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform;
/* 111:    */  }
/* 112:    */  
/* 120:    */  public final wL a(String paramString)
/* 121:    */  {
/* 122:    */    wL localwL;
/* 123:    */    
/* 130:130 */    if ((localwL = (wL)this.jdField_a_of_type_JavaUtilHashMap.get(paramString)) == null) {
/* 131:131 */      throw new IllegalArgumentException("animation '" + paramString + "' not found in " + this.jdField_a_of_type_JavaUtilHashMap);
/* 132:    */    }
/* 133:133 */    return localwL;
/* 134:    */  }
/* 135:    */  
/* 162:    */  public final Collection a()
/* 163:    */  {
/* 164:164 */    return this.jdField_a_of_type_JavaUtilHashMap.keySet();
/* 165:    */  }
/* 166:    */  
/* 169:    */  public final xN a(String paramString)
/* 170:    */  {
/* 171:171 */    return (xN)this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(a(paramString));
/* 172:    */  }
/* 173:    */  
/* 174:    */  public final int a() {
/* 175:175 */    return this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.size();
/* 176:    */  }
/* 177:    */  
/* 178:    */  public final int a(String paramString) {
/* 179:179 */    for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator(); localIterator.hasNext();) { xN localxN;
/* 180:180 */      if ((localxN = (xN)localIterator.next()).jdField_a_of_type_JavaLangString.equals(paramString)) {
/* 181:181 */        return localxN.jdField_a_of_type_Int;
/* 182:    */      }
/* 183:    */    }
/* 184:184 */    System.err.println("WARNING: bone not found in skeleton: " + paramString);
/* 185:185 */    if (!jdField_b_of_type_Boolean) throw new AssertionError();
/* 186:186 */    return -1;
/* 187:    */  }
/* 188:    */  
/* 193:    */  public final Int2ObjectOpenHashMap a()
/* 194:    */  {
/* 195:195 */    return this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap;
/* 196:    */  }
/* 197:    */  
/* 202:    */  public final xN a()
/* 203:    */  {
/* 204:204 */    return this.jdField_a_of_type_XN;
/* 205:    */  }
/* 206:    */  
/* 207:    */  public final void a(xq paramxq)
/* 208:    */  {
/* 209:209 */    ye localye = this; Integer localInteger; Transform localTransform; for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet().iterator(); localIterator.hasNext(); localye.jdField_b_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(localInteger, localTransform)) { localInteger = (Integer)localIterator.next();(localTransform = new Transform()).setIdentity();
/* 210:    */    }
/* 211:211 */    this.jdField_a_of_type_XN.a(paramxq);
/* 212:    */    
/* 213:213 */    this.jdField_a_of_type_XN.e();
/* 214:    */    
/* 215:215 */    this.jdField_a_of_type_Boolean = true;
/* 216:    */  }
/* 217:    */  
/* 220:    */  public final boolean a()
/* 221:    */  {
/* 222:222 */    return this.jdField_a_of_type_Boolean;
/* 223:    */  }
/* 224:    */  
/* 241:    */  public final void a()
/* 242:    */  {
/* 243:243 */    this.jdField_a_of_type_XN.d();
/* 244:    */  }
/* 245:    */  
/* 254:    */  public final void a(List paramList)
/* 255:    */  {
/* 256:256 */    for (wL localwL : paramList) {
/* 257:257 */      this.jdField_a_of_type_JavaUtilHashMap.put(localwL.jdField_a_of_type_JavaLangString, localwL);
/* 258:    */    }
/* 259:    */  }
/* 260:    */  
/* 265:    */  public final void a(xN paramxN)
/* 266:    */  {
/* 267:267 */    this.jdField_a_of_type_XN = paramxN;
/* 268:    */  }
/* 269:    */  
/* 276:    */  public final void b(xq paramxq)
/* 277:    */  {
/* 278:278 */    if (!this.jdField_a_of_type_Boolean) {
/* 279:279 */      a(paramxq);
/* 280:    */    }
/* 281:281 */    this.jdField_a_of_type_XN.a(paramxq);
/* 282:    */  }
/* 283:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ye
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */