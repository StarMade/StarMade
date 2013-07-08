/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.nio.ByteBuffer;
/*   6:    */import java.nio.FloatBuffer;
/*   7:    */import java.nio.IntBuffer;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.HashSet;
/*  10:    */import java.util.Iterator;
/*  11:    */import java.util.Map;
/*  12:    */import java.util.Map.Entry;
/*  13:    */import java.util.Set;
/*  14:    */import javax.vecmath.AxisAngle4f;
/*  15:    */import javax.vecmath.Matrix3f;
/*  16:    */import javax.vecmath.Vector3f;
/*  17:    */import org.lwjgl.BufferUtils;
/*  18:    */import org.lwjgl.opengl.GL11;
/*  19:    */import org.lwjgl.opengl.GL15;
/*  20:    */import org.schema.game.common.controller.SegmentController;
/*  21:    */import org.schema.game.common.data.element.ControlElementMap;
/*  22:    */import org.schema.game.common.data.element.ControlElementMapper;
/*  23:    */import org.schema.game.common.data.element.ElementCollection;
/*  24:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  25:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  26:    */
/*  32:    */public class eo
/*  33:    */  implements xg
/*  34:    */{
/*  35:    */  private final SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*  36: 36 */  private final Map jdField_a_of_type_JavaUtilMap = new HashMap();
/*  37:    */  
/*  38:    */  private int jdField_a_of_type_Int;
/*  39:    */  
/*  40: 40 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  41: 41 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  42: 42 */  private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  43: 43 */  private Vector3f jdField_d_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 1.0F, 0.0F);
/*  44: 44 */  private Vector3f e = new Vector3f(); private static Vector3f[] jdField_a_of_type_ArrayOfJavaxVecmathVector3f;
/*  45: 45 */  static { jdField_a_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
/*  46: 46 */    jdField_b_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
/*  47: 47 */    jdField_c_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
/*  48: 48 */    jdField_d_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
/*  49:    */    
/*  50: 50 */    for (int i = 0; i < 8; i++) {
/*  51: 51 */      jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
/*  52: 52 */      jdField_c_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
/*  53: 53 */      jdField_b_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
/*  54: 54 */      jdField_d_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f(); } }
/*  55:    */  
/*  56:    */  private static Vector3f[] jdField_b_of_type_ArrayOfJavaxVecmathVector3f;
/*  57: 57 */  private static Vector3f[] jdField_c_of_type_ArrayOfJavaxVecmathVector3f; private static Vector3f[] jdField_d_of_type_ArrayOfJavaxVecmathVector3f; private Matrix3f jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/*  58: 58 */  private AxisAngle4f jdField_a_of_type_JavaxVecmathAxisAngle4f = new AxisAngle4f();
/*  59:    */  private int jdField_b_of_type_Int;
/*  60: 60 */  private boolean jdField_a_of_type_Boolean = true;
/*  61:    */  private boolean jdField_b_of_type_Boolean;
/*  62:    */  private int jdField_c_of_type_Int;
/*  63:    */  private int jdField_d_of_type_Int;
/*  64:    */  private zB jdField_a_of_type_ZB;
/*  65: 65 */  private static IntBuffer jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  66: 66 */  private static IntBuffer jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  67: 67 */  private static IntBuffer jdField_c_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  68:    */  
/*  69:    */  public eo(SegmentController paramSegmentController)
/*  70:    */  {
/*  71: 71 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public final void a()
/*  75:    */  {
/*  76: 76 */    if (this.jdField_a_of_type_Int != 0) {
/*  77: 77 */      GL15.glDeleteBuffers(this.jdField_a_of_type_Int);
/*  78:    */    }
/*  79:    */  }
/*  80:    */  
/*  99:    */  public final void b()
/* 100:    */  {
/* 101:101 */    if (this.jdField_a_of_type_Boolean) {
/* 102:102 */      eo localeo = this;this.jdField_a_of_type_JavaUtilMap.clear(); for (Iterator localIterator = localeo.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getControlElementMap().getControllingMap().getAll().entrySet().iterator(); localIterator.hasNext();) { Map.Entry localEntry; if (((ObjectOpenHashSet)(localEntry = (Map.Entry)localIterator.next()).getValue()).size() > 0) { short s = 0;ObjectIterator localObjectIterator = ((ObjectOpenHashSet)localEntry.getValue()).iterator(); while ((localObjectIterator.hasNext()) && ((s = ((ja)localObjectIterator.next()).jdField_a_of_type_Short) == 32767)) {} if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(s))) localeo.jdField_a_of_type_JavaUtilMap.put(ElementCollection.getPosFromIndex(((Long)localEntry.getKey()).longValue(), new q()), localEntry.getValue()); else System.err.println("the type is not a factory type: " + s); } }
/* 103:103 */      e();
/* 104:104 */      this.jdField_a_of_type_Boolean = false;
/* 105:    */    }
/* 106:    */    
/* 109:109 */    if ((!this.jdField_b_of_type_Boolean) && (this.jdField_a_of_type_JavaUtilMap.size() > 0))
/* 110:    */    {
/* 111:111 */      GL11.glEnable(2903);
/* 112:112 */      GL11.glEnable(2896);
/* 113:113 */      this.jdField_a_of_type_ZB.a();
/* 114:    */      
/* 117:117 */      GL11.glEnableClientState(32884);
/* 118:118 */      GL11.glEnableClientState(32885);
/* 119:119 */      GL11.glEnableClientState(32888);
/* 120:    */      
/* 123:123 */      GL15.glBindBuffer(34962, this.jdField_d_of_type_Int);
/* 124:    */      
/* 125:125 */      GL11.glTexCoordPointer(3, 5126, 0, 0L);
/* 126:    */      
/* 128:128 */      GL15.glBindBuffer(34962, this.jdField_c_of_type_Int);
/* 129:    */      
/* 130:130 */      GL11.glNormalPointer(5126, 0, 0L);
/* 131:    */      
/* 133:133 */      GL15.glBindBuffer(34962, this.jdField_a_of_type_Int);
/* 134:    */      
/* 135:135 */      GL11.glVertexPointer(3, 5126, 0, 0L);
/* 136:    */      
/* 138:138 */      GL11.glDrawArrays(7, 0, this.jdField_b_of_type_Int);
/* 139:    */      
/* 140:140 */      GL15.glBindBuffer(34962, 0);
/* 141:    */      
/* 142:142 */      GL11.glDisableClientState(32884);
/* 143:143 */      GL11.glDisableClientState(32885);
/* 144:144 */      GL11.glDisableClientState(32888);
/* 145:145 */      GL11.glDisable(2903);
/* 146:    */    }
/* 147:    */  }
/* 148:    */  
/* 150:    */  private void e()
/* 151:    */  {
/* 152:152 */    int i = 0;
/* 153:153 */    for (Object localObject1 = this.jdField_a_of_type_JavaUtilMap.entrySet().iterator(); ((Iterator)localObject1).hasNext();) {
/* 154:154 */      for (localObject2 = ((ObjectOpenHashSet)((Map.Entry)((Iterator)localObject1).next()).getValue()).iterator(); ((Iterator)localObject2).hasNext();) { ((Iterator)localObject2).next();
/* 155:155 */        i++;
/* 156:    */      }
/* 157:    */    }
/* 158:    */    
/* 160:160 */    this.jdField_b_of_type_Boolean = (i == 0);
/* 161:161 */    if (this.jdField_b_of_type_Boolean) {
/* 162:162 */      System.err.println("NOTHING TO DRAW");
/* 163:163 */      return;
/* 164:    */    }
/* 165:165 */    System.err.println("UPDATING CONNECTIONS: " + i);
/* 166:166 */    this.jdField_b_of_type_Int = (i << 1 << 1 << 3);
/* 167:    */    
/* 168:168 */    localObject1 = GlUtil.a(this.jdField_b_of_type_Int * 3 << 2, 0).asFloatBuffer();
/* 169:169 */    FloatBuffer localFloatBuffer = GlUtil.a(this.jdField_b_of_type_Int * 3 << 2, 1).asFloatBuffer();
/* 170:170 */    Object localObject2 = GlUtil.a(this.jdField_b_of_type_Int * 3 << 2, 2).asFloatBuffer();
/* 171:171 */    i = 0;
/* 172:    */    
/* 175:175 */    for (Iterator localIterator = this.jdField_a_of_type_JavaUtilMap.entrySet().iterator(); localIterator.hasNext();) { localObject3 = (Map.Entry)localIterator.next();
/* 176:176 */      this.jdField_a_of_type_JavaxVecmathVector3f.set(-8.0F + ((q)((Map.Entry)localObject3).getKey()).jdField_a_of_type_Int, -8.0F + ((q)((Map.Entry)localObject3).getKey()).jdField_b_of_type_Int, -8.0F + ((q)((Map.Entry)localObject3).getKey()).jdField_c_of_type_Int);
/* 177:177 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransformClient().transform(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 178:178 */      for (localObject3 = ((ObjectOpenHashSet)((Map.Entry)localObject3).getValue()).iterator(); ((Iterator)localObject3).hasNext();) { ja localja = (ja)((Iterator)localObject3).next();
/* 179:179 */        this.jdField_b_of_type_JavaxVecmathVector3f.set(-8.0F + localja.jdField_a_of_type_Int, -8.0F + localja.jdField_b_of_type_Int, -8.0F + localja.jdField_c_of_type_Int);
/* 180:180 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransformClient().transform(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 181:181 */        i += a(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f, (FloatBuffer)localObject1, localFloatBuffer, (FloatBuffer)localObject2);
/* 182:    */      } }
/* 183:    */    Object localObject3;
/* 184:184 */    if ((!jdField_c_of_type_Boolean) && (this.jdField_b_of_type_Int != i)) { throw new AssertionError(this.jdField_b_of_type_Int + "/" + i);
/* 185:    */    }
/* 186:186 */    if (this.jdField_a_of_type_Int == 0)
/* 187:    */    {
/* 189:189 */      GL15.glGenBuffers(jdField_a_of_type_JavaNioIntBuffer);
/* 190:    */      
/* 191:191 */      this.jdField_a_of_type_Int = jdField_a_of_type_JavaNioIntBuffer.get(0);
/* 192:    */      
/* 193:193 */      GL15.glGenBuffers(jdField_b_of_type_JavaNioIntBuffer);
/* 194:    */      
/* 195:195 */      this.jdField_c_of_type_Int = jdField_b_of_type_JavaNioIntBuffer.get(0);
/* 196:    */      
/* 197:197 */      GL15.glGenBuffers(jdField_c_of_type_JavaNioIntBuffer);
/* 198:    */      
/* 199:199 */      this.jdField_d_of_type_Int = jdField_c_of_type_JavaNioIntBuffer.get(0);
/* 200:    */    }
/* 201:    */    
/* 202:202 */    GL15.glBindBuffer(34962, this.jdField_a_of_type_Int);
/* 203:    */    
/* 205:205 */    ((FloatBuffer)localObject1).flip();
/* 206:206 */    System.err.println("BUFFER LIMIT " + ((FloatBuffer)localObject1).limit() + " / " + (this.jdField_b_of_type_Int * 3 << 2) + " (" + this.jdField_b_of_type_Int + ")");
/* 207:207 */    GL15.glBufferData(34962, (FloatBuffer)localObject1, 35044);
/* 208:    */    
/* 210:210 */    GL15.glBindBuffer(34962, this.jdField_c_of_type_Int);
/* 211:211 */    localFloatBuffer.flip();
/* 212:212 */    GL15.glBufferData(34962, localFloatBuffer, 35044);
/* 213:    */    
/* 214:214 */    GL15.glBindBuffer(34962, this.jdField_d_of_type_Int);
/* 215:215 */    ((FloatBuffer)localObject2).flip();
/* 216:216 */    GL15.glBufferData(34962, (FloatBuffer)localObject2, 35044);
/* 217:    */    
/* 218:218 */    GL15.glBindBuffer(34962, 0);
/* 219:    */    
/* 221:221 */    this.jdField_a_of_type_ZB = new zB();
/* 222:222 */    this.jdField_a_of_type_ZB.f();
/* 223:223 */    this.jdField_a_of_type_ZB.c(new float[] { 0.9F, 0.9F, 0.9F, 1.0F });
/* 224:    */  }
/* 225:    */  
/* 227:    */  private int a(Vector3f paramVector3f1, Vector3f paramVector3f2, FloatBuffer paramFloatBuffer1, FloatBuffer paramFloatBuffer2, FloatBuffer paramFloatBuffer3)
/* 228:    */  {
/* 229:229 */    this.jdField_c_of_type_JavaxVecmathVector3f.sub(paramVector3f2, paramVector3f1);
/* 230:230 */    this.e.cross(this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f);
/* 231:    */    
/* 232:232 */    float f1 = 0.0F;
/* 233:    */    
/* 234:234 */    float f2 = 0.0F;
/* 235:235 */    for (int i = 0; i < 8; 
/* 236:    */        
/* 237:237 */        i++)
/* 238:    */    {
/* 239:239 */      this.jdField_a_of_type_JavaxVecmathAxisAngle4f.set(this.jdField_c_of_type_JavaxVecmathVector3f, f1);
/* 240:240 */      this.jdField_a_of_type_JavaxVecmathMatrix3f.set(this.jdField_a_of_type_JavaxVecmathAxisAngle4f);
/* 241:241 */      jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i].set(0.0F, 0.1F, 0.0F);
/* 242:    */      
/* 243:243 */      this.jdField_a_of_type_JavaxVecmathMatrix3f.transform(jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i]);
/* 244:    */      
/* 245:245 */      jdField_b_of_type_ArrayOfJavaxVecmathVector3f[i].set(jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i]);
/* 246:    */      
/* 247:247 */      jdField_c_of_type_ArrayOfJavaxVecmathVector3f[i].set(jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i]);
/* 248:    */      
/* 249:249 */      jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i].add(paramVector3f1);
/* 250:250 */      jdField_c_of_type_ArrayOfJavaxVecmathVector3f[i].add(paramVector3f2);
/* 251:251 */      jdField_d_of_type_ArrayOfJavaxVecmathVector3f[i].set(0.0F, f2, 0.0F);
/* 252:252 */      f2 += 0.125F;
/* 253:253 */      f1 += 0.7853982F;
/* 254:    */    }
/* 255:    */    
/* 257:257 */    for (paramVector3f1 = 0; paramVector3f1 < 8; 
/* 258:    */        
/* 259:259 */        paramVector3f1++) {
/* 260:260 */      GlUtil.a(paramFloatBuffer1, jdField_a_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/* 261:261 */      GlUtil.a(paramFloatBuffer2, jdField_b_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/* 262:262 */      jdField_d_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1].x = 0.0F;
/* 263:263 */      GlUtil.a(paramFloatBuffer3, jdField_d_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/* 264:    */      
/* 266:266 */      GlUtil.a(paramFloatBuffer1, jdField_a_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/* 267:267 */      GlUtil.a(paramFloatBuffer2, jdField_b_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/* 268:268 */      jdField_d_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)].x = 0.0F;
/* 269:269 */      GlUtil.a(paramFloatBuffer3, jdField_d_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/* 270:    */      
/* 271:271 */      GlUtil.a(paramFloatBuffer1, jdField_c_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/* 272:272 */      GlUtil.a(paramFloatBuffer2, jdField_b_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/* 273:273 */      jdField_d_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)].x = 1.0F;
/* 274:274 */      GlUtil.a(paramFloatBuffer3, jdField_d_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/* 275:    */      
/* 276:276 */      GlUtil.a(paramFloatBuffer1, jdField_c_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/* 277:277 */      GlUtil.a(paramFloatBuffer2, jdField_b_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/* 278:278 */      jdField_d_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1].x = 1.0F;
/* 279:279 */      GlUtil.a(paramFloatBuffer3, jdField_d_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/* 280:    */    }
/* 281:    */    
/* 283:283 */    return 32;
/* 284:    */  }
/* 285:    */  
/* 299:    */  public final void d()
/* 300:    */  {
/* 301:301 */    this.jdField_a_of_type_Boolean = true;
/* 302:    */  }
/* 303:    */  
/* 306:    */  public final SegmentController a()
/* 307:    */  {
/* 308:308 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/* 309:    */  }
/* 310:    */  
/* 311:    */  public final void c() {}
/* 312:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     eo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */