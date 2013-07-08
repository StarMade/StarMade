/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.Iterator;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import org.schema.common.FastMath;
/*   8:    */import org.schema.game.common.controller.SegmentController;
/*   9:    */import org.schema.game.common.data.element.ElementInformation;
/*  10:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  11:    */import org.schema.schine.network.NetworkStateContainer;
/*  12:    */import org.schema.schine.network.StateInterface;
/*  13:    */import org.schema.schine.network.objects.Sendable;
/*  14:    */
/*  24:    */public class me
/*  25:    */  implements Ak, xZ
/*  26:    */{
/*  27:    */  private short jdField_a_of_type_Short;
/*  28:    */  private int jdField_a_of_type_Int;
/*  29:    */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*  30: 30 */  private long jdField_a_of_type_Long = -1L;
/*  31:    */  
/*  32:    */  private int jdField_b_of_type_Int;
/*  33: 33 */  private static int c = 180000;
/*  34: 34 */  private int d = -1;
/*  35: 35 */  private int e = 0;
/*  36: 36 */  private static int f = 10;
/*  37: 37 */  private static int g = c / f;
/*  38:    */  
/*  40: 40 */  public me() { this.jdField_a_of_type_Long = System.currentTimeMillis(); }
/*  41:    */  
/*  42:    */  public me(int paramInt1, short paramShort, int paramInt2, Vector3f paramVector3f) {
/*  43: 43 */    this();
/*  44: 44 */    a(paramInt1, paramShort, paramInt2, paramVector3f);
/*  45:    */  }
/*  46:    */  
/*  47: 47 */  public final void a(int paramInt1, short paramShort, int paramInt2, Vector3f paramVector3f) { this.jdField_b_of_type_Int = paramInt1;
/*  48: 48 */    this.jdField_a_of_type_Short = paramShort;
/*  49: 49 */    this.jdField_a_of_type_Int = paramInt2;
/*  50: 50 */    this.jdField_a_of_type_JavaxVecmathVector3f = paramVector3f;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public final boolean a(long paramLong)
/*  54:    */  {
/*  55: 55 */    long l2 = paramLong;paramLong = this;long l1 = l2 - paramLong.jdField_a_of_type_Long;
/*  56: 56 */    this.e = ((int)(l1 / g));
/*  57: 57 */    return l1 < c;
/*  58:    */  }
/*  59:    */  
/*  61:    */  public final boolean a()
/*  62:    */  {
/*  63: 63 */    boolean bool = this.d != this.e;
/*  64: 64 */    this.d = this.e;
/*  65: 65 */    return bool;
/*  66:    */  }
/*  67:    */  
/*  69:    */  public final short a()
/*  70:    */  {
/*  71: 71 */    return this.jdField_a_of_type_Short;
/*  72:    */  }
/*  73:    */  
/*  75:    */  public final void a(short paramShort)
/*  76:    */  {
/*  77: 77 */    this.jdField_a_of_type_Short = paramShort;
/*  78:    */  }
/*  79:    */  
/*  81:    */  public final int a()
/*  82:    */  {
/*  83: 83 */    return this.jdField_a_of_type_Int;
/*  84:    */  }
/*  85:    */  
/*  87:    */  public final void a(int paramInt)
/*  88:    */  {
/*  89: 89 */    this.jdField_a_of_type_Int = paramInt;
/*  90:    */  }
/*  91:    */  
/*  93:    */  public final Vector3f a()
/*  94:    */  {
/*  95: 95 */    return this.jdField_a_of_type_JavaxVecmathVector3f;
/*  96:    */  }
/*  97:    */  
/*  99:    */  public final void a(Vector3f paramVector3f)
/* 100:    */  {
/* 101:101 */    this.jdField_a_of_type_JavaxVecmathVector3f = paramVector3f;
/* 102:    */  }
/* 103:    */  
/* 105:    */  public final long a()
/* 106:    */  {
/* 107:107 */    return this.jdField_a_of_type_Long;
/* 108:    */  }
/* 109:    */  
/* 111:    */  public final void a(long paramLong)
/* 112:    */  {
/* 113:113 */    this.jdField_a_of_type_Long = paramLong;
/* 114:    */  }
/* 115:    */  
/* 118:    */  public final int b()
/* 119:    */  {
/* 120:120 */    return this.jdField_b_of_type_Int;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public final int a(yg paramyg)
/* 124:    */  {
/* 125:125 */    if (this.jdField_a_of_type_Short == 0) {
/* 126:126 */      System.err.println("[CLIENT] WARNING: TRIED TO DRAW FREE ITEM, BUT type == TYPE_NONE");
/* 127:127 */      return -1;
/* 128:    */    }
/* 129:129 */    if (this.jdField_a_of_type_Short == -2) {
/* 130:130 */      if (paramyg.b().startsWith("build-icons-extra")) {
/* 131:131 */        return 0;
/* 132:    */      }
/* 133:133 */      return -1;
/* 134:    */    }
/* 135:    */    
/* 136:136 */    int i = ElementKeyMap.getInfo(this.jdField_a_of_type_Short).getBuildIconNum() / 256;
/* 137:137 */    if (paramyg.b().startsWith("build-icons-" + i.b(i))) {
/* 138:138 */      return ElementKeyMap.getInfo(this.jdField_a_of_type_Short).getBuildIconNum() % 256;
/* 139:    */    }
/* 140:140 */    return -1;
/* 141:    */  }
/* 142:    */  
/* 145:    */  public Ah toTagStructure()
/* 146:    */  {
/* 147:147 */    Ah localAh1 = new Ah(Aj.j, null, this.jdField_a_of_type_JavaxVecmathVector3f);
/* 148:148 */    Ah localAh2 = new Ah(Aj.c, null, Short.valueOf(this.jdField_a_of_type_Short));
/* 149:149 */    Ah localAh3 = new Ah(Aj.d, null, Integer.valueOf(this.jdField_a_of_type_Int));
/* 150:150 */    return new Ah(Aj.n, null, new Ah[] { localAh1, localAh2, localAh3, new Ah(Aj.a, null, null) });
/* 151:    */  }
/* 152:    */  
/* 153:    */  public void fromTagStructure(Ah paramAh) {
/* 154:154 */    paramAh = (Ah[])paramAh.a();
/* 155:155 */    this.jdField_a_of_type_JavaxVecmathVector3f = ((Vector3f)paramAh[0].a());
/* 156:156 */    this.jdField_a_of_type_Short = ((Short)paramAh[1].a()).shortValue();
/* 157:157 */    this.jdField_a_of_type_Int = ((Integer)paramAh[2].a()).intValue();
/* 158:    */  }
/* 159:    */  
/* 160:    */  public String getUniqueIdentifier() {
/* 161:161 */    return null;
/* 162:    */  }
/* 163:    */  
/* 164:    */  public boolean isVolatile() {
/* 165:165 */    return false;
/* 166:    */  }
/* 167:    */  
/* 168:    */  public final float a(long paramLong) {
/* 169:169 */    long l = paramLong - this.jdField_a_of_type_Long;
/* 170:170 */    return 0.001F + 0.009F * (1.0F - (float)l / c);
/* 171:    */  }
/* 172:    */  
/* 173:173 */  public final void b(int paramInt) { this.jdField_b_of_type_Int = paramInt; }
/* 174:    */  
/* 176:176 */  private static q jdField_a_of_type_Q = new q();
/* 177:177 */  private static Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 178:178 */  private static q jdField_b_of_type_Q = new q();
/* 179:    */  
/* 180:180 */  public final boolean a(mx parammx) { Iterator localIterator; synchronized (parammx.a().getState().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 181:181 */      for (localIterator = parammx.a().getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) { Object localObject1;
/* 182:182 */        if (((localObject1 = (Sendable)localIterator.next()) instanceof SegmentController))
/* 183:    */        {
/* 186:186 */          if ((localObject1 = (SegmentController)localObject1).getSectorId() == parammx.a()) {
/* 187:187 */            jdField_b_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 188:    */            
/* 191:191 */            ((SegmentController)localObject1).getWorldTransformInverse().transform(jdField_b_of_type_JavaxVecmathVector3f);
/* 192:    */            
/* 193:193 */            if (((SegmentController)localObject1).getSegmentBuffer().a().a(jdField_b_of_type_JavaxVecmathVector3f))
/* 194:    */            {
/* 195:195 */              int i = Math.round(jdField_b_of_type_JavaxVecmathVector3f.x) + 8;
/* 196:    */              
/* 197:197 */              int j = Math.round(jdField_b_of_type_JavaxVecmathVector3f.y) + 8;
/* 198:198 */              int k = Math.round(jdField_b_of_type_JavaxVecmathVector3f.z) + 8;
/* 199:199 */              jdField_a_of_type_Q.b(i, j, k);
/* 200:    */              
/* 201:201 */              le localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false);
/* 202:    */              
/* 203:203 */              System.err.println(localObject1 + " POINT INSIDE " + this + ":    " + this.jdField_a_of_type_JavaxVecmathVector3f + " -trans> " + jdField_a_of_type_Q + ": " + localle);
/* 204:    */              
/* 205:205 */              if ((localle != null) && (localle.a() != 0))
/* 206:    */              {
/* 209:209 */                jdField_b_of_type_Q.b(jdField_a_of_type_Q);
/* 210:    */                
/* 212:212 */                for (parammx = 1; parammx < 8;)
/* 213:    */                {
/* 220:220 */                  jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/* 221:221 */                  jdField_a_of_type_Q.jdField_b_of_type_Int -= parammx;
/* 222:    */                  
/* 223:223 */                  if (((localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || (localle.a() == 0)) break;
/* 224:224 */                  jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/* 225:    */                  
/* 228:228 */                  jdField_a_of_type_Q.jdField_a_of_type_Int += parammx;
/* 229:    */                  
/* 230:230 */                  if (((localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || (localle.a() == 0)) break;
/* 231:231 */                  jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/* 232:    */                  
/* 235:235 */                  jdField_a_of_type_Q.jdField_a_of_type_Int -= parammx;
/* 236:    */                  
/* 237:237 */                  if (((localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || (localle.a() == 0)) break;
/* 238:238 */                  jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/* 239:    */                  
/* 242:242 */                  jdField_a_of_type_Q.c += parammx;
/* 243:    */                  
/* 244:244 */                  if (((localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || (localle.a() == 0)) break;
/* 245:245 */                  jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/* 246:    */                  
/* 249:249 */                  jdField_a_of_type_Q.c -= parammx;
/* 250:    */                  
/* 251:251 */                  if (((localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || (localle.a() == 0)) break;
/* 252:252 */                  jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/* 253:    */                  
/* 258:258 */                  jdField_a_of_type_Q.jdField_b_of_type_Int += parammx;
/* 259:    */                  
/* 260:260 */                  if (((localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || (localle.a() == 0)) break;
/* 261:261 */                  parammx++;
/* 262:    */                }
/* 263:    */                
/* 265:265 */                jdField_a_of_type_Q.c(jdField_b_of_type_Q);
/* 266:    */                
/* 267:267 */                this.jdField_a_of_type_JavaxVecmathVector3f.x += jdField_a_of_type_Q.jdField_a_of_type_Int;
/* 268:268 */                this.jdField_a_of_type_JavaxVecmathVector3f.y += jdField_a_of_type_Q.jdField_b_of_type_Int;
/* 269:269 */                this.jdField_a_of_type_JavaxVecmathVector3f.z += jdField_a_of_type_Q.c;
/* 270:    */                
/* 271:271 */                this.jdField_a_of_type_JavaxVecmathVector3f.x = (FastMath.a(this.jdField_a_of_type_JavaxVecmathVector3f.x) + 0.5F);
/* 272:272 */                this.jdField_a_of_type_JavaxVecmathVector3f.y = (FastMath.a(this.jdField_a_of_type_JavaxVecmathVector3f.y) + 0.5F);
/* 273:273 */                this.jdField_a_of_type_JavaxVecmathVector3f.z = (FastMath.a(this.jdField_a_of_type_JavaxVecmathVector3f.z) + 0.5F);
/* 274:    */                
/* 275:275 */                System.err.println("[ITEM][COLLISION] warping item out of collision " + this.jdField_a_of_type_JavaxVecmathVector3f);
/* 276:276 */                return true;
/* 277:    */              }
/* 278:    */            }
/* 279:    */          }
/* 280:    */        }
/* 281:    */      }
/* 282:    */    }
/* 283:    */    
/* 284:284 */    return false;
/* 285:    */  }
/* 286:    */  
/* 289:    */  public String toString()
/* 290:    */  {
/* 291:291 */    return "(ITEM[" + this.jdField_b_of_type_Int + "]: type " + this.jdField_a_of_type_Short + "; #" + this.jdField_a_of_type_Int + "; " + this.jdField_a_of_type_JavaxVecmathVector3f + ")";
/* 292:    */  }
/* 293:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     me
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */