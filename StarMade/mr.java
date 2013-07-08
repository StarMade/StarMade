/*   1:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   2:    */import com.bulletphysics.linearmath.Transform;
/*   3:    */import javax.vecmath.Matrix3f;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */import org.schema.game.client.view.SegmentDrawer;
/*   6:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*   7:    */import org.schema.game.common.controller.SegmentController;
/*   8:    */import org.schema.game.common.data.world.Segment;
/*   9:    */
/*  75:    */public class mr
/*  76:    */  extends mw
/*  77:    */{
/*  78:    */  private boolean jdField_d_of_type_Boolean;
/*  79: 79 */  private boolean jdField_e_of_type_Boolean = false;
/*  80:    */  
/*  85: 85 */  private boolean jdField_f_of_type_Boolean = true;
/*  86:    */  
/*  88:    */  public long a;
/*  89:    */  
/*  91:    */  private CubeMeshBufferContainer jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer;
/*  92:    */  
/*  94:    */  private dG jdField_a_of_type_DG;
/*  95:    */  
/*  97:    */  private dG jdField_b_of_type_DG;
/*  98:    */  
/* 100:    */  public Object a;
/* 101:    */  
/* 103:    */  public long b;
/* 104:    */  
/* 106:    */  public boolean a;
/* 107:    */  
/* 109:    */  private boolean jdField_g_of_type_Boolean;
/* 110:    */  
/* 111:    */  public ms a;
/* 112:    */  
/* 113:    */  public int a;
/* 114:    */  
/* 115:    */  public boolean b;
/* 116:    */  
/* 117:    */  private int jdField_b_of_type_Int;
/* 118:    */  
/* 119:    */  public long c;
/* 120:    */  
/* 122:    */  public mr(SegmentController paramSegmentController)
/* 123:    */  {
/* 124:124 */    super(paramSegmentController);this.jdField_a_of_type_JavaLangObject = new Object();this.jdField_a_of_type_Ms = ms.jdField_a_of_type_Ms;this.jdField_a_of_type_Int = 0;
/* 125:    */  }
/* 126:    */  
/* 130:    */  public final void a()
/* 131:    */  {
/* 132:132 */    synchronized (this.jdField_a_of_type_JavaLangObject)
/* 133:    */    {
/* 134:134 */      dG localdG = this.jdField_b_of_type_DG;
/* 135:    */      
/* 136:136 */      if ((!h) && (this.jdField_b_of_type_DG == this.jdField_a_of_type_DG)) { throw new AssertionError();
/* 137:    */      }
/* 138:138 */      this.jdField_b_of_type_DG = this.jdField_a_of_type_DG;
/* 139:    */      
/* 140:140 */      SegmentDrawer.a.a(localdG);
/* 141:    */      
/* 142:142 */      this.jdField_a_of_type_DG = null;
/* 143:    */      
/* 144:144 */      return;
/* 145:    */    }
/* 146:    */  }
/* 147:    */  
/* 164:    */  public final void a(boolean paramBoolean)
/* 165:    */  {
/* 166:166 */    super.a(paramBoolean);
/* 167:    */    
/* 169:169 */    e(true);
/* 170:    */    
/* 172:172 */    ((ct)this.a.getState()).a().a().jdField_d_of_type_Boolean = true;
/* 173:    */  }
/* 174:    */  
/* 182:    */  public final void b()
/* 183:    */  {
/* 184:184 */    synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 185:185 */      mr localmr1 = this; synchronized (this.jdField_a_of_type_JavaLangObject) { SegmentDrawer.a.a(localmr1.jdField_b_of_type_DG);localmr1.jdField_b_of_type_DG = null; }
/* 186:186 */      mr localmr2 = this; synchronized (this.jdField_a_of_type_JavaLangObject) { SegmentDrawer.a.a(localmr2.jdField_a_of_type_DG);localmr2.jdField_a_of_type_DG = null; }
/* 187:187 */      return;
/* 188:    */    }
/* 189:    */  }
/* 190:    */  
/* 197:    */  public final void a(Vector3f paramVector3f1, Vector3f paramVector3f2)
/* 198:    */  {
/* 199:    */    Transform localTransform1;
/* 200:    */    
/* 205:205 */    if (!(localTransform1 = this.a.getWorldTransformClient()).equals(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform)) {
/* 206:206 */      paramVector3f1.set(this.jdField_a_of_type_Q.jdField_a_of_type_Int, this.jdField_a_of_type_Q.jdField_b_of_type_Int, this.jdField_a_of_type_Q.c);
/* 207:207 */      paramVector3f2.set(paramVector3f1);
/* 208:208 */      paramVector3f2.x += 8.0F;
/* 209:209 */      paramVector3f2.y += 8.0F;
/* 210:210 */      paramVector3f2.z += 8.0F;
/* 211:    */      
/* 212:212 */      paramVector3f1.x -= 8.0F;
/* 213:213 */      paramVector3f1.y -= 8.0F;
/* 214:214 */      paramVector3f1.z -= 8.0F;
/* 215:    */      
/* 218:218 */      Vector3f localVector3f4 = this.jdField_g_of_type_JavaxVecmathVector3f;Vector3f localVector3f3 = this.jdField_f_of_type_JavaxVecmathVector3f;Transform localTransform2 = localTransform1;Vector3f localVector3f2 = paramVector3f2;Vector3f localVector3f1 = paramVector3f1; if ((!h) && (localVector3f1.x > localVector3f2.x)) throw new AssertionError(); if ((!h) && (localVector3f1.y > localVector3f2.y)) throw new AssertionError(); if ((!h) && (localVector3f1.z > localVector3f2.z)) throw new AssertionError(); jdField_a_of_type_JavaxVecmathVector3f.sub(localVector3f2, localVector3f1);jdField_a_of_type_JavaxVecmathVector3f.scale(0.5F);jdField_a_of_type_JavaxVecmathVector3f.x += 0.0F;jdField_a_of_type_JavaxVecmathVector3f.y += 0.0F;jdField_a_of_type_JavaxVecmathVector3f.z += 0.0F;jdField_b_of_type_JavaxVecmathVector3f.add(localVector3f2, localVector3f1);jdField_b_of_type_JavaxVecmathVector3f.scale(0.5F);jdField_a_of_type_JavaxVecmathMatrix3f.set(localTransform2.basis);MatrixUtil.absolute(jdField_a_of_type_JavaxVecmathMatrix3f);c.set(jdField_b_of_type_JavaxVecmathVector3f);localTransform2.transform(c);jdField_a_of_type_JavaxVecmathMatrix3f.getRow(0, jdField_e_of_type_JavaxVecmathVector3f);jdField_d_of_type_JavaxVecmathVector3f.x = jdField_e_of_type_JavaxVecmathVector3f.dot(jdField_a_of_type_JavaxVecmathVector3f);jdField_a_of_type_JavaxVecmathMatrix3f.getRow(1, jdField_e_of_type_JavaxVecmathVector3f);jdField_d_of_type_JavaxVecmathVector3f.y = jdField_e_of_type_JavaxVecmathVector3f.dot(jdField_a_of_type_JavaxVecmathVector3f);jdField_a_of_type_JavaxVecmathMatrix3f.getRow(2, jdField_e_of_type_JavaxVecmathVector3f);jdField_d_of_type_JavaxVecmathVector3f.z = jdField_e_of_type_JavaxVecmathVector3f.dot(jdField_a_of_type_JavaxVecmathVector3f);localVector3f3.sub(c, jdField_d_of_type_JavaxVecmathVector3f);localVector3f4.add(c, jdField_d_of_type_JavaxVecmathVector3f);
/* 219:219 */      this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(localTransform1);
/* 220:    */    }
/* 221:221 */    paramVector3f1.set(this.jdField_f_of_type_JavaxVecmathVector3f);
/* 222:222 */    paramVector3f2.set(this.jdField_g_of_type_JavaxVecmathVector3f); }
/* 223:    */  
/* 224:224 */  private static Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 225:225 */  private static Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 226:226 */  private static Matrix3f jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/* 227:227 */  private static Vector3f c = new Vector3f();
/* 228:228 */  private static Vector3f jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/* 229:229 */  private static Vector3f jdField_e_of_type_JavaxVecmathVector3f = new Vector3f();
/* 230:230 */  private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 231:231 */  private Vector3f jdField_f_of_type_JavaxVecmathVector3f = new Vector3f();
/* 232:232 */  private Vector3f jdField_g_of_type_JavaxVecmathVector3f = new Vector3f();
/* 233:    */  
/* 255:    */  public float a;
/* 256:    */  
/* 279:    */  public final CubeMeshBufferContainer a()
/* 280:    */  {
/* 281:281 */    if ((!h) && (this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer != null)) { throw new AssertionError();
/* 282:    */    }
/* 283:283 */    CubeMeshBufferContainer localCubeMeshBufferContainer = dI.a();
/* 284:284 */    this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer = localCubeMeshBufferContainer;
/* 285:285 */    return localCubeMeshBufferContainer;
/* 286:    */  }
/* 287:    */  
/* 289:    */  public final CubeMeshBufferContainer b()
/* 290:    */  {
/* 291:291 */    return this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer;
/* 292:    */  }
/* 293:    */  
/* 295:    */  public final dG a()
/* 296:    */  {
/* 297:297 */    return this.jdField_b_of_type_DG;
/* 298:    */  }
/* 299:    */  
/* 302:    */  public final dG b()
/* 303:    */  {
/* 304:304 */    return this.jdField_a_of_type_DG;
/* 305:    */  }
/* 306:    */  
/* 308:    */  public final int a()
/* 309:    */  {
/* 310:310 */    return this.jdField_b_of_type_Int;
/* 311:    */  }
/* 312:    */  
/* 320:    */  public final boolean a()
/* 321:    */  {
/* 322:322 */    return this.jdField_f_of_type_Boolean;
/* 323:    */  }
/* 324:    */  
/* 327:    */  public final boolean b()
/* 328:    */  {
/* 329:329 */    return this.jdField_g_of_type_Boolean;
/* 330:    */  }
/* 331:    */  
/* 337:    */  public final boolean c()
/* 338:    */  {
/* 339:339 */    return this.jdField_e_of_type_Boolean;
/* 340:    */  }
/* 341:    */  
/* 348:    */  public final boolean d()
/* 349:    */  {
/* 350:350 */    return this.jdField_d_of_type_Boolean;
/* 351:    */  }
/* 352:    */  
/* 353:    */  public final void c()
/* 354:    */  {
/* 355:355 */    if (this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer != null) {
/* 356:356 */      dI.a(this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer);
/* 357:357 */      this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer = null;
/* 358:    */    }
/* 359:    */  }
/* 360:    */  
/* 363:    */  public final void b(boolean paramBoolean)
/* 364:    */  {
/* 365:365 */    if (this.jdField_g_of_type_Boolean != paramBoolean) {
/* 366:366 */      this.a.getSegmentBuffer().a(paramBoolean ? 1 : -1, this);
/* 367:    */    }
/* 368:368 */    this.jdField_g_of_type_Boolean = paramBoolean;
/* 369:    */  }
/* 370:    */  
/* 380:    */  public final void c(boolean paramBoolean)
/* 381:    */  {
/* 382:382 */    this.jdField_f_of_type_Boolean = paramBoolean;
/* 383:    */  }
/* 384:    */  
/* 394:    */  public final void d(boolean paramBoolean)
/* 395:    */  {
/* 396:396 */    this.jdField_e_of_type_Boolean = paramBoolean;
/* 397:    */  }
/* 398:    */  
/* 399:    */  public final void e(boolean paramBoolean)
/* 400:    */  {
/* 401:401 */    if (paramBoolean)
/* 402:    */    {
/* 403:403 */      this.jdField_f_of_type_Boolean = true;
/* 404:    */    }
/* 405:405 */    this.jdField_d_of_type_Boolean = paramBoolean;
/* 406:    */  }
/* 407:    */  
/* 417:    */  public final void a(dG paramdG)
/* 418:    */  {
/* 419:419 */    this.jdField_a_of_type_DG = paramdG;
/* 420:    */  }
/* 421:    */  
/* 422:    */  public final void a(int paramInt) {
/* 423:423 */    this.jdField_b_of_type_Int = paramInt;
/* 424:    */  }
/* 425:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */