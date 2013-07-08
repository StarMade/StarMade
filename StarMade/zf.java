/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import javax.vecmath.Vector4f;
/*   7:    */import org.lwjgl.BufferUtils;
/*   8:    */import org.lwjgl.opengl.GL11;
/*   9:    */import org.lwjgl.opengl.GL15;
/*  10:    */import org.schema.schine.graphicsengine.camera.Camera;
/*  11:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  12:    */
/*  23:    */public class zf
/*  24:    */  implements xg
/*  25:    */{
/*  26:    */  yW jdField_a_of_type_YW;
/*  27: 27 */  private IntBuffer jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  28: 28 */  private IntBuffer jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  29:    */  private static FloatBuffer jdField_a_of_type_JavaNioFloatBuffer;
/*  30:    */  private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer;
/*  31: 31 */  public static float a; private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  32:    */  
/*  33: 33 */  static { jdField_a_of_type_Float = 0.5F; }
/*  34:    */  
/*  37: 37 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  38:    */  
/*  39: 39 */  private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  40:    */  
/*  42: 42 */  private Vector3f d = new Vector3f();
/*  43: 43 */  private Vector3f e = new Vector3f();
/*  44: 44 */  private Vector3f f = new Vector3f();
/*  45:    */  
/*  46: 46 */  private Vector3f g = new Vector3f();
/*  47: 47 */  private Vector3f h = new Vector3f();
/*  48: 48 */  private Vector4f jdField_a_of_type_JavaxVecmathVector4f = new Vector4f();
/*  49: 49 */  private Vector3f i = new Vector3f();
/*  50: 50 */  private Vector3f[] jdField_a_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
/*  51: 51 */  private Vector3f[] jdField_b_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
/*  52: 52 */  private Vector3f[] jdField_c_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
/*  53: 53 */  private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  54: 54 */  boolean jdField_a_of_type_Boolean = true;
/*  55: 55 */  private static float[] jdField_a_of_type_ArrayOfFloat = { 0.0F, 0.5F, 0.75F, 0.25F };
/*  56:    */  
/*  57:    */  private int jdField_a_of_type_Int;
/*  58:    */  
/*  59: 59 */  private Vector3f j = new Vector3f();
/*  60:    */  
/*  61: 61 */  private Vector3f k = new Vector3f();
/*  62: 62 */  private Vector3f l = new Vector3f();
/*  63: 63 */  private Vector3f m = new Vector3f();
/*  64:    */  
/*  65: 65 */  private zf() { new Vector3f();
/*  66: 66 */    new Vector3f();
/*  67: 67 */    new Vector3f();
/*  68:    */    
/*  69: 69 */    new Vector3f();
/*  70:    */    
/*  71: 71 */    this.jdField_b_of_type_JavaxVecmathVector4f = new Vector4f();
/*  72:    */    
/*  74: 74 */    this.jdField_a_of_type_YW = null;
/*  75:    */    
/*  76: 76 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*  77:    */    
/*  78: 78 */    for (int n = 0; n < this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f.length; n++) {
/*  79: 79 */      this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[n] = new Vector3f();
/*  80: 80 */      this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[n] = new Vector3f();
/*  81: 81 */      this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[n] = new Vector3f();
/*  82:    */    }
/*  83:    */  }
/*  84:    */  
/*  85:    */  public zf(byte paramByte)
/*  86:    */  {
/*  87: 87 */    this();
/*  88:    */  }
/*  89:    */  
/*  95:    */  public void b()
/*  96:    */  {
/*  97: 97 */    if (this.jdField_a_of_type_YW.b() > 1) {
/*  98: 98 */      zf localzf = this;yV localyV = this.jdField_a_of_type_YW.a;jdField_a_of_type_JavaNioFloatBuffer.rewind();jdField_b_of_type_JavaNioFloatBuffer.rewind();jdField_a_of_type_JavaNioFloatBuffer.limit(jdField_a_of_type_JavaNioFloatBuffer.capacity());jdField_b_of_type_JavaNioFloatBuffer.limit(jdField_b_of_type_JavaNioFloatBuffer.capacity());localzf.jdField_a_of_type_JavaxVecmathVector3f = GlUtil.c(localzf.jdField_a_of_type_JavaxVecmathVector3f);localzf.jdField_b_of_type_JavaxVecmathVector3f = GlUtil.b(localzf.jdField_b_of_type_JavaxVecmathVector3f);localzf.jdField_c_of_type_JavaxVecmathVector3f = GlUtil.a(localzf.jdField_c_of_type_JavaxVecmathVector3f);int n = Math.min(512, localzf.jdField_a_of_type_YW.b());int i1 = localzf.jdField_a_of_type_YW.a() % localzf.jdField_a_of_type_YW.a.a();float f1 = 0.0F;float f2 = 1.0F / n;localzf.jdField_a_of_type_Int = 0; int i2; int i3; int i4; if (localzf.jdField_a_of_type_Boolean) { i1 = i1 + n - 1; for (i2 = 0; i2 < n; i2++) { i3 = i1 % localzf.jdField_a_of_type_YW.a.a();i4 = (i1 - 1) % localzf.jdField_a_of_type_YW.a.a();localzf.a(i3, i4, localyV, f1, i2, n);f1 += f2;i1--; } } else { for (i2 = 0; i2 < n; i2++) { i3 = i1 % localzf.jdField_a_of_type_YW.a.a();i4 = (i1 + 1) % localzf.jdField_a_of_type_YW.a.a();localzf.a(i3, i4, localyV, f1, i2, n);f1 += f2;i1++; } } jdField_a_of_type_JavaNioFloatBuffer.flip();jdField_b_of_type_JavaNioFloatBuffer.flip();GL15.glBindBuffer(34962, localzf.jdField_a_of_type_JavaNioIntBuffer.get(0));GL15.glBufferData(34962, jdField_a_of_type_JavaNioFloatBuffer, 35048);GL15.glBindBuffer(34962, localzf.jdField_b_of_type_JavaNioIntBuffer.get(0));GL15.glBufferData(34962, jdField_b_of_type_JavaNioFloatBuffer, 35048);
/*  99:    */      
/* 100:100 */      if ((jdField_a_of_type_JavaNioFloatBuffer.position() == 0 ? 0 : 1) != 0) {
/* 101:101 */        GlUtil.d();
/* 102:    */        
/* 103:103 */        GL11.glDisable(2884);
/* 104:    */        
/* 105:105 */        GL11.glDisable(2896);
/* 106:106 */        GL11.glEnable(3042);
/* 107:107 */        GL11.glBlendFunc(770, 771);
/* 108:    */        
/* 109:109 */        localzf = this;GL11.glEnableClientState(32884);GL11.glEnableClientState(32888);GL15.glBindBuffer(34962, localzf.jdField_a_of_type_JavaNioIntBuffer.get(0));GL11.glVertexPointer(4, 5126, 0, 0L);GL15.glBindBuffer(34962, localzf.jdField_b_of_type_JavaNioIntBuffer.get(0));GL11.glTexCoordPointer(4, 5126, 0, 0L);GL11.glDrawArrays(8, 0, localzf.jdField_a_of_type_Int);GL15.glBindBuffer(34962, 0);GL11.glDisableClientState(32884);GL11.glDisableClientState(32888);
/* 110:    */        
/* 111:111 */        GL11.glDisable(3042);
/* 112:112 */        GlUtil.c();
/* 113:    */        
/* 114:114 */        GL11.glDisable(2903);
/* 115:115 */        GL11.glEnable(2884);
/* 116:116 */        GL11.glEnable(2896);
/* 117:    */      }
/* 118:    */    }
/* 119:    */  }
/* 120:    */  
/* 167:    */  private Vector4f jdField_b_of_type_JavaxVecmathVector4f;
/* 168:    */  
/* 213:    */  private void a(int paramInt1, int paramInt2, yV paramyV, float paramFloat1, float paramFloat2, int paramInt3)
/* 214:    */  {
/* 215:    */    try
/* 216:    */    {
/* 217:217 */      int n = paramFloat2 > 0.0F ? 1 : 0;
/* 218:    */      
/* 220:220 */      paramyV.a(paramInt1, this.d);
/* 221:221 */      if (paramFloat2 >= paramInt3 - 2) {
/* 222:222 */        this.f.set(this.g);
/* 223:    */      } else {
/* 224:224 */        paramyV.a(paramInt2, this.e);
/* 225:225 */        this.f.sub(this.e, this.d);
/* 226:    */      }
/* 227:    */      
/* 229:229 */      if (n != 0) {
/* 230:230 */        this.h.cross(this.f, this.g);
/* 231:    */      }
/* 232:    */      
/* 233:233 */      this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/* 234:234 */      GlUtil.a(this.d, this.f, xe.a().a(), this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 235:    */      
/* 236:236 */      paramyV.d(paramInt1, this.i);
/* 237:    */      
/* 238:238 */      this.jdField_a_of_type_JavaxVecmathVector4f.set(this.d.x, this.d.y, this.d.z, paramyV.a(paramInt1));
/* 239:    */      
/* 241:241 */      GlUtil.a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform, jdField_a_of_type_Float, this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f);
/* 242:    */      
/* 251:251 */      this.j.sub(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[3], this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[0]);
/* 252:252 */      this.k.sub(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[2], this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[1]);
/* 253:253 */      this.j.scale(0.5F);
/* 254:254 */      this.k.scale(0.5F);
/* 255:    */      
/* 258:258 */      this.l.add(this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[0], this.j);
/* 259:259 */      this.m.add(this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[1], this.k);
/* 260:    */      
/* 261:261 */      if (paramFloat2 > 0.0F) {
/* 262:262 */        this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[0].set(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[0]);
/* 263:263 */        this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[1].set(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[1]);
/* 264:264 */        this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[2].set(this.m);
/* 265:265 */        this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[3].set(this.l);
/* 266:266 */        GlUtil.a(jdField_a_of_type_JavaNioFloatBuffer, this.jdField_a_of_type_JavaxVecmathVector4f.w, this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f, jdField_a_of_type_JavaNioFloatBuffer.position() - 8);
/* 267:    */      }
/* 268:    */      
/* 271:271 */      if (paramFloat2 > 0.0F) {
/* 272:272 */        this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[0].set(this.l);
/* 273:273 */        this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[1].set(this.m);
/* 274:274 */        this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[2].set(this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[2]);
/* 275:275 */        this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[3].set(this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[3]);
/* 276:276 */        GlUtil.a(jdField_a_of_type_JavaNioFloatBuffer, this.jdField_a_of_type_JavaxVecmathVector4f.w, this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f, -1);
/* 277:277 */        this.jdField_a_of_type_Int += 2;
/* 278:    */      } else {
/* 279:279 */        GlUtil.a(jdField_a_of_type_JavaNioFloatBuffer, this.jdField_a_of_type_JavaxVecmathVector4f.w, this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f, -1);
/* 280:280 */        this.jdField_a_of_type_Int += 2;
/* 281:    */      }
/* 282:    */      
/* 285:285 */      for (paramInt1 = 0; paramInt1 < 2; paramInt1++) {
/* 286:286 */        this.jdField_b_of_type_JavaxVecmathVector4f.set(0.0F, paramFloat2 * 0.05F, paramFloat1, jdField_a_of_type_ArrayOfFloat[paramInt1]);
/* 287:287 */        GlUtil.a(jdField_b_of_type_JavaNioFloatBuffer, this.jdField_b_of_type_JavaxVecmathVector4f);
/* 288:    */      }
/* 289:    */      
/* 290:290 */      for (paramInt1 = 0; paramInt1 < 4; paramInt1++) {
/* 291:291 */        if (paramFloat2 > 0.0F) {
/* 292:292 */          this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[paramInt1].set(this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[paramInt1]);
/* 293:    */        } else {
/* 294:294 */          this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[paramInt1].set(this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[paramInt1]);
/* 295:    */        }
/* 296:    */      }
/* 297:    */    } catch (Exception localException) {
/* 298:298 */      System.err.println("[ERROR][TRAILDRAWER] " + localException.getClass().getSimpleName() + " " + localException.getMessage());
/* 299:    */    }
/* 300:    */    
/* 301:301 */    this.g.set(this.f);
/* 302:    */  }
/* 303:    */  
/* 351:    */  public void c()
/* 352:    */  {
/* 353:353 */    zf localzf = this; if (jdField_a_of_type_JavaNioFloatBuffer == null) jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4104); else jdField_a_of_type_JavaNioFloatBuffer.rewind(); GL15.glGenBuffers(localzf.jdField_a_of_type_JavaNioIntBuffer);GL15.glBindBuffer(34962, localzf.jdField_a_of_type_JavaNioIntBuffer.get(0));GL15.glBufferData(34962, jdField_a_of_type_JavaNioFloatBuffer, 35048);GL15.glBindBuffer(34962, 0); if (jdField_b_of_type_JavaNioFloatBuffer == null) jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4104); else jdField_b_of_type_JavaNioFloatBuffer.rewind(); GL15.glGenBuffers(localzf.jdField_b_of_type_JavaNioIntBuffer);GL15.glBindBuffer(34962, localzf.jdField_b_of_type_JavaNioIntBuffer.get(0));GL15.glBufferData(34962, jdField_b_of_type_JavaNioFloatBuffer, 35048);GL15.glBindBuffer(34962, 0);
/* 354:    */  }
/* 355:    */  
/* 359:    */  public final void a(yW paramyW)
/* 360:    */  {
/* 361:361 */    this.jdField_a_of_type_YW = paramyW;
/* 362:    */  }
/* 363:    */  
/* 364:    */  public final void a() {}
/* 365:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */