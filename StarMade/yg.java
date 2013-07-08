/*    1:     */import java.io.PrintStream;
/*    2:     */import java.nio.BufferOverflowException;
/*    3:     */import java.nio.FloatBuffer;
/*    4:     */import java.nio.IntBuffer;
/*    5:     */import java.util.Collection;
/*    6:     */import java.util.Iterator;
/*    7:     */import org.lwjgl.BufferUtils;
/*    8:     */import org.lwjgl.input.Mouse;
/*    9:     */import org.lwjgl.opengl.GL11;
/*   10:     */import org.lwjgl.opengl.GL13;
/*   11:     */import org.lwjgl.opengl.GL15;
/*   12:     */import org.lwjgl.util.glu.GLU;
/*   13:     */import org.lwjgl.util.vector.Matrix4f;
/*   14:     */import org.schema.schine.graphicsengine.camera.Camera;
/*   15:     */import org.schema.schine.graphicsengine.core.GlUtil;
/*   16:     */
/*   81:     */public class yg
/*   82:     */  extends ya
/*   83:     */{
/*   84:  84 */  private int jdField_b_of_type_Int = 1;
/*   85:     */  
/*   86:  86 */  private int jdField_c_of_type_Int = 0;
/*   87:     */  
/*   93:  93 */  private boolean jdField_a_of_type_Boolean = false;
/*   94:     */  
/*   96:  96 */  private boolean jdField_b_of_type_Boolean = true;
/*   97:     */  
/*   99:  99 */  private boolean jdField_c_of_type_Boolean = true;
/*  100:     */  
/*  101:     */  private javax.vecmath.Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*  102:     */  
/*  103:     */  private int jdField_d_of_type_Int;
/*  104:     */  private int jdField_e_of_type_Int;
/*  105: 105 */  private boolean jdField_d_of_type_Boolean = false;
/*  106:     */  
/*  108: 108 */  private boolean jdField_f_of_type_Boolean = false;
/*  109:     */  
/*  110: 110 */  private IntBuffer jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  111:     */  
/*  112:     */  private IntBuffer jdField_b_of_type_JavaNioIntBuffer;
/*  113:     */  
/*  114: 114 */  private IntBuffer jdField_c_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  115: 115 */  private boolean jdField_g_of_type_Boolean = true;
/*  116:     */  
/*  119: 119 */  private boolean jdField_h_of_type_Boolean = true;
/*  120:     */  
/*  121:     */  private int jdField_f_of_type_Int;
/*  122:     */  
/*  123:     */  private int jdField_g_of_type_Int;
/*  124:     */  private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer;
/*  125:     */  private static FloatBuffer jdField_c_of_type_JavaNioFloatBuffer;
/*  126:     */  private static FloatBuffer jdField_d_of_type_JavaNioFloatBuffer;
/*  127:     */  private static int jdField_h_of_type_Int;
/*  128:     */  private static int jdField_i_of_type_Int;
/*  129:     */  private static int j;
/*  130:     */  private static int k;
/*  131:     */  private static FloatBuffer jdField_e_of_type_JavaNioFloatBuffer;
/*  132:     */  public int a;
/*  133:     */  private static IntBuffer jdField_d_of_type_JavaNioIntBuffer;
/*  134:     */  private static FloatBuffer jdField_f_of_type_JavaNioFloatBuffer;
/*  135:     */  private static FloatBuffer jdField_g_of_type_JavaNioFloatBuffer;
/*  136:     */  private static FloatBuffer jdField_h_of_type_JavaNioFloatBuffer;
/*  137:     */  
/*  138:     */  public static void a(yg paramyg)
/*  139:     */  {
/*  140: 140 */    if (paramyg.jdField_a_of_type_JavaxVecmathVector4f != null) {
/*  141: 141 */      GlUtil.a(paramyg.jdField_a_of_type_JavaxVecmathVector4f.x, paramyg.jdField_a_of_type_JavaxVecmathVector4f.y, paramyg.jdField_a_of_type_JavaxVecmathVector4f.z, paramyg.jdField_a_of_type_JavaxVecmathVector4f.w);
/*  142:     */    } else {
/*  143: 143 */      GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  144:     */    }
/*  145: 145 */    if (k != paramyg.jdField_c_of_type_Int) {
/*  146: 146 */      GL15.glBindBuffer(34962, paramyg.jdField_b_of_type_JavaNioIntBuffer.get(paramyg.jdField_c_of_type_Int));
/*  147: 147 */      GL11.glTexCoordPointer(2, 5126, 0, 0L);
/*  148: 148 */      k = paramyg.jdField_c_of_type_Int;
/*  149:     */    }
/*  150: 150 */    GL11.glDrawArrays(7, 0, 4);
/*  151:     */  }
/*  152:     */  
/*  153:     */  public static void b(yg paramyg) {
/*  154: 154 */    k = -1;
/*  155: 155 */    if (paramyg.jdField_h_of_type_Boolean) {
/*  156: 156 */      paramyg.c();
/*  157:     */    }
/*  158:     */    
/*  159: 159 */    if (xu.y.b()) {
/*  160: 160 */      n();
/*  161:     */    }
/*  162:     */    
/*  163: 163 */    GL11.glDisable(2896);
/*  164: 164 */    if (paramyg.jdField_b_of_type_Boolean) {
/*  165: 165 */      GL11.glEnable(2929);
/*  166:     */    } else {
/*  167: 167 */      GL11.glDisable(2929);
/*  168:     */    }
/*  169:     */    
/*  170: 170 */    if (paramyg.jdField_c_of_type_Boolean) {
/*  171: 171 */      GL11.glEnable(3042);
/*  172: 172 */      GL11.glBlendFunc(770, 771);
/*  174:     */    }
/*  175:     */    else
/*  176:     */    {
/*  178: 178 */      GL11.glDisable(3042);
/*  179:     */    }
/*  180:     */    
/*  181: 181 */    paramyg.jdField_a_of_type_ZB.a().a();
/*  182:     */    
/*  183: 183 */    if ((paramyg.jdField_g_of_type_Boolean) && (xu.N.b()))
/*  184:     */    {
/*  185: 185 */      GL11.glEnableClientState(32884);
/*  186:     */      
/*  187: 187 */      GL11.glEnableClientState(32888);
/*  188:     */      
/*  189: 189 */      GL11.glEnableClientState(32885);
/*  190:     */      
/*  192: 192 */      GL15.glBindBuffer(34962, paramyg.jdField_a_of_type_JavaNioIntBuffer.get(0));
/*  193:     */      
/*  194: 194 */      GL11.glVertexPointer(3, 5126, 0, 0L);
/*  195:     */      
/*  196: 196 */      GL13.glActiveTexture(33984);
/*  197:     */      
/*  198: 198 */      if (paramyg.jdField_a_of_type_JavaxVecmathVector4f != null) {
/*  199: 199 */        GlUtil.a(paramyg.jdField_a_of_type_JavaxVecmathVector4f.x, paramyg.jdField_a_of_type_JavaxVecmathVector4f.y, paramyg.jdField_a_of_type_JavaxVecmathVector4f.z, paramyg.jdField_a_of_type_JavaxVecmathVector4f.w);
/*  200:     */      } else {
/*  201: 201 */        GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  202:     */      }
/*  203:     */      
/*  204: 204 */      GL15.glBindBuffer(34962, paramyg.jdField_b_of_type_JavaNioIntBuffer.get(paramyg.jdField_c_of_type_Int));
/*  205:     */      
/*  206: 206 */      GL11.glTexCoordPointer(2, 5126, 0, 0L);
/*  207:     */      
/*  208: 208 */      GL15.glBindBuffer(34962, paramyg.jdField_c_of_type_JavaNioIntBuffer.get(0));
/*  209:     */      
/*  210: 210 */      GL11.glNormalPointer(5126, 0, 0L);return;
/*  211:     */    }
/*  212: 212 */    if (!jdField_i_of_type_Boolean) { throw new AssertionError();
/*  213:     */    }
/*  214:     */  }
/*  215:     */  
/*  216:     */  public static void c(yg paramyg)
/*  217:     */  {
/*  218: 218 */    GL11.glDisableClientState(32884);
/*  219:     */    
/*  220: 220 */    GL11.glDisableClientState(32888);
/*  221:     */    
/*  222: 222 */    GL11.glDisableClientState(32885);
/*  223:     */    
/*  224: 224 */    paramyg.jdField_a_of_type_ZB.a();zC.b();
/*  225:     */    
/*  227: 227 */    GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  228:     */    
/*  229: 229 */    GL11.glDisable(3042);
/*  230: 230 */    GL11.glEnable(2929);
/*  231: 231 */    GL11.glDisable(2903);
/*  232: 232 */    GL11.glEnable(2896);
/*  233: 233 */    if (xu.y.b()) {
/*  234: 234 */      n();
/*  235:     */    }
/*  236:     */  }
/*  237:     */  
/*  238:     */  public static void d(yg paramVarArgs)
/*  239:     */  {
/*  240: 240 */    if (paramVarArgs.jdField_h_of_type_Boolean) {
/*  241: 241 */      paramVarArgs.c();
/*  242:     */    }
/*  243:     */    
/*  244: 244 */    if (paramVarArgs.h()) {
/*  245: 245 */      return;
/*  246:     */    }
/*  247:     */    
/*  248: 248 */    if (xu.y.b()) {
/*  249: 249 */      n();
/*  250:     */    }
/*  251: 251 */    GlUtil.d();
/*  252:     */    
/*  254: 254 */    GL11.glDisable(2896);
/*  255: 255 */    if (paramVarArgs.jdField_b_of_type_Boolean) {
/*  256: 256 */      GL11.glEnable(2929);
/*  257:     */    } else {
/*  258: 258 */      GL11.glDisable(2929);
/*  259:     */    }
/*  260:     */    
/*  261: 261 */    if (paramVarArgs.jdField_c_of_type_Boolean) {
/*  262: 262 */      GL11.glEnable(3042);
/*  263: 263 */      GL11.glBlendFunc(770, 771);
/*  265:     */    }
/*  266:     */    else
/*  267:     */    {
/*  269: 269 */      GL11.glDisable(3042);
/*  270:     */    }
/*  271:     */    
/*  272: 272 */    paramVarArgs.jdField_a_of_type_ZB.a().a();
/*  273:     */    
/*  274: 274 */    if ((paramVarArgs.jdField_g_of_type_Boolean) && (xu.N.b()))
/*  275:     */    {
/*  276: 276 */      GL11.glEnableClientState(32884);
/*  277:     */      
/*  278: 278 */      GL11.glEnableClientState(32888);
/*  279:     */      
/*  280: 280 */      GL11.glEnableClientState(32885);
/*  281:     */      
/*  283: 283 */      GL15.glBindBuffer(34962, paramVarArgs.jdField_a_of_type_JavaNioIntBuffer.get(0));
/*  284:     */      
/*  285: 285 */      GL11.glVertexPointer(3, 5126, 0, 0L);
/*  286:     */      
/*  287: 287 */      GL13.glActiveTexture(33984);
/*  288:     */      
/*  289: 289 */      if (paramVarArgs.jdField_a_of_type_JavaxVecmathVector4f != null) {
/*  290: 290 */        GlUtil.a(paramVarArgs.jdField_a_of_type_JavaxVecmathVector4f.x, paramVarArgs.jdField_a_of_type_JavaxVecmathVector4f.y, paramVarArgs.jdField_a_of_type_JavaxVecmathVector4f.z, paramVarArgs.jdField_a_of_type_JavaxVecmathVector4f.w);
/*  291:     */      } else {
/*  292: 292 */        GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  293:     */      }
/*  294:     */      
/*  295: 295 */      GL15.glBindBuffer(34962, paramVarArgs.jdField_b_of_type_JavaNioIntBuffer.get(paramVarArgs.jdField_c_of_type_Int));
/*  296:     */      
/*  297: 297 */      GL11.glTexCoordPointer(2, 5126, 0, 0L);
/*  298:     */      
/*  302: 302 */      GL15.glBindBuffer(34962, paramVarArgs.jdField_c_of_type_JavaNioIntBuffer.get(0));
/*  303:     */      
/*  304: 304 */      GL11.glNormalPointer(5126, 0, 0L);
/*  305:     */      
/*  308: 308 */      GlUtil.d();
/*  309:     */      
/*  338: 338 */      if (paramVarArgs.jdField_f_of_type_Boolean) {
/*  339: 339 */        GlUtil.a(paramVarArgs.a());
/*  340: 340 */        jdField_e_of_type_JavaNioFloatBuffer.put(0, paramVarArgs.a.x);
/*  341: 341 */        jdField_e_of_type_JavaNioFloatBuffer.put(5, -paramVarArgs.a.y);
/*  342: 342 */        jdField_e_of_type_JavaNioFloatBuffer.put(10, paramVarArgs.a.z);
/*  343:     */        
/*  344: 344 */        jdField_e_of_type_JavaNioFloatBuffer.put(12, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m30);
/*  345: 345 */        jdField_e_of_type_JavaNioFloatBuffer.put(13, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m31);
/*  346: 346 */        jdField_e_of_type_JavaNioFloatBuffer.put(14, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m32);
/*  347: 347 */        jdField_e_of_type_JavaNioFloatBuffer.rewind();
/*  348: 348 */        GlUtil.a(jdField_e_of_type_JavaNioFloatBuffer);
/*  349:     */      }
/*  350: 350 */      GL11.glDrawArrays(7, 0, 4);
/*  351: 351 */      GlUtil.c();
/*  352:     */      
/*  356: 356 */      GL11.glDisableClientState(32884);
/*  357:     */      
/*  358: 358 */      GL11.glDisableClientState(32888);
/*  359:     */      
/*  360: 360 */      GL11.glDisableClientState(32885);
/*  361:     */    } else {
/*  362: 362 */      float f1 = paramVarArgs.jdField_a_of_type_ZB.a().jdField_b_of_type_Int * paramVarArgs.a.x;
/*  363:     */      
/*  369: 369 */      float f2 = paramVarArgs.jdField_a_of_type_ZB.a().jdField_a_of_type_Int * paramVarArgs.a.y;
/*  370:     */      
/*  371: 371 */      GL11.glBegin(7);
/*  372:     */      
/*  373: 373 */      if (paramVarArgs.jdField_a_of_type_Boolean) {
/*  374: 374 */        GL11.glTexCoord2f(0.0F, 0.0F);
/*  375: 375 */        GL11.glVertex3f(-f1 / 2.0F, -f2 / 2.0F, 0.0F);
/*  376: 376 */        GL11.glTexCoord2f(0.0F, 1.0F);
/*  377: 377 */        GL11.glVertex3f(-f1 / 2.0F, f2 / 2.0F, 0.0F);
/*  378: 378 */        GL11.glTexCoord2f(1.0F, 1.0F);
/*  379: 379 */        GL11.glVertex3f(f1 / 2.0F, f2 / 2.0F, 0.0F);
/*  380: 380 */        GL11.glTexCoord2f(1.0F, 0.0F);
/*  381: 381 */        GL11.glVertex3f(f1 / 2.0F, -f2 / 2.0F, 0.0F);
/*  382:     */      } else {
/*  383: 383 */        GL11.glTexCoord2f(0.0F, 0.0F);
/*  384: 384 */        GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/*  385: 385 */        GL11.glTexCoord2f(0.0F, 1.0F);
/*  386: 386 */        GL11.glVertex3f(0.0F, f2, 0.0F);
/*  387: 387 */        GL11.glTexCoord2f(1.0F, 1.0F);
/*  388: 388 */        GL11.glVertex3f(f1, f2, 0.0F);
/*  389: 389 */        GL11.glTexCoord2f(1.0F, 0.0F);
/*  390: 390 */        GL11.glVertex3f(f1, 0.0F, 0.0F);
/*  391:     */      }
/*  392:     */      
/*  393: 393 */      GL11.glEnd();
/*  394:     */    }
/*  395: 395 */    paramVarArgs.jdField_a_of_type_ZB.a();zC.b();
/*  396:     */    
/*  397: 397 */    GlUtil.c();
/*  398:     */    
/*  400: 400 */    GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  401:     */    
/*  402: 402 */    GL11.glDisable(3042);
/*  403: 403 */    GL11.glEnable(2929);
/*  404: 404 */    GL11.glDisable(2903);
/*  405: 405 */    GL11.glEnable(2896);
/*  406: 406 */    if (xu.y.b()) {
/*  407: 407 */      n();
/*  408:     */    }
/*  409:     */  }
/*  410:     */  
/*  411:     */  static
/*  412:     */  {
/*  413: 131 */    jdField_h_of_type_Int = 12;
/*  414:     */    
/*  415: 133 */    jdField_i_of_type_Int = 8;
/*  416:     */    
/*  417: 135 */    j = 12;
/*  418:     */    
/*  419: 137 */    k = -1;
/*  420:     */    
/*  693: 411 */    jdField_e_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  694:     */    
/*  697:     */    Matrix4f localMatrix4f;
/*  698:     */    
/*  700: 418 */    (localMatrix4f = new Matrix4f()).setIdentity();
/*  701: 419 */    localMatrix4f.scale(new org.lwjgl.util.vector.Vector3f(0.01F, -0.01F, 0.01F));
/*  702: 420 */    localMatrix4f.store(jdField_e_of_type_JavaNioFloatBuffer);
/*  703:     */    
/*  704: 422 */    jdField_e_of_type_JavaNioFloatBuffer.rewind();
/*  705:     */    
/*  706: 424 */    jdField_d_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(16);
/*  707: 425 */    jdField_f_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  708: 426 */    jdField_g_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  709: 427 */    jdField_h_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(3);
/*  710: 428 */    BufferUtils.createFloatBuffer(1);
/*  711:     */  }
/*  712:     */  
/*  762:     */  public static void a(yg paramyg, xZ[] paramArrayOfxZ, Camera paramCamera)
/*  763:     */  {
/*  764: 482 */    if (paramyg.jdField_h_of_type_Boolean) {
/*  765: 483 */      paramyg.c();
/*  766:     */    }
/*  767:     */    
/*  768: 486 */    if (paramyg.h()) {
/*  769: 487 */      return;
/*  770:     */    }
/*  771:     */    
/*  773: 491 */    int m = (paramArrayOfxZ.length > 0) && ((paramArrayOfxZ[0] instanceof xY)) ? 1 : 0;
/*  774: 492 */    int n = (paramArrayOfxZ.length > 0) && ((paramArrayOfxZ[0] instanceof yc)) ? 1 : 0;
/*  775:     */    
/*  777: 495 */    if (xu.y.b()) {
/*  778: 496 */      n();
/*  779:     */    }
/*  780: 498 */    GlUtil.d();
/*  781:     */    
/*  782: 500 */    yc localyc = null;
/*  783:     */    
/*  784: 502 */    GL11.glDisable(2896);
/*  785: 503 */    if (paramyg.jdField_b_of_type_Boolean) {
/*  786: 504 */      GL11.glEnable(2929);
/*  787:     */    } else {
/*  788: 506 */      GL11.glDisable(2929);
/*  789:     */    }
/*  790:     */    
/*  791: 509 */    if (paramyg.jdField_c_of_type_Boolean) {
/*  792: 510 */      GL11.glEnable(3042);
/*  793: 511 */      if (paramyg.jdField_a_of_type_Int == 0) {
/*  794: 512 */        GL11.glBlendFunc(770, 771);
/*  795:     */      } else {
/*  796: 514 */        GL11.glBlendFunc(770, 1);
/*  797:     */      }
/*  798:     */      
/*  800:     */    }
/*  801:     */    else
/*  802:     */    {
/*  803: 521 */      GL11.glDisable(3042);
/*  804:     */    }
/*  805: 523 */    float f1 = 0.0F;
/*  806: 524 */    float f2 = 0.0F;
/*  807: 525 */    if (n != 0) {
/*  808: 526 */      f1 = Mouse.getX();
/*  809: 527 */      f2 = Mouse.getY();
/*  810:     */    }
/*  811:     */    
/*  812: 530 */    paramyg.jdField_a_of_type_ZB.a().a();
/*  813:     */    
/*  815: 533 */    if ((paramyg.jdField_g_of_type_Boolean) && (xu.N.b())) {
/*  816: 534 */      zk.u.c();
/*  817:     */      
/*  818: 536 */      GlUtil.a(zk.u, "selectionColor", 1.0F, 1.0F, 1.0F, 1.0F);
/*  819: 537 */      GlUtil.a(zk.u, "mainTexA", 0);
/*  820:     */      
/*  821: 539 */      GL11.glEnableClientState(32884);
/*  822:     */      
/*  823: 541 */      GL11.glEnableClientState(32888);
/*  824:     */      
/*  825: 543 */      GL11.glEnableClientState(32885);
/*  826:     */      
/*  828: 546 */      GL15.glBindBuffer(34962, paramyg.jdField_a_of_type_JavaNioIntBuffer.get(0));
/*  829:     */      
/*  830: 548 */      GL11.glVertexPointer(3, 5126, 0, 0L);
/*  831:     */      
/*  832: 550 */      GL13.glActiveTexture(33984);
/*  833:     */      
/*  834: 552 */      if (paramyg.jdField_a_of_type_JavaxVecmathVector4f != null) {
/*  835: 553 */        GlUtil.a(paramyg.jdField_a_of_type_JavaxVecmathVector4f.x, paramyg.jdField_a_of_type_JavaxVecmathVector4f.y, paramyg.jdField_a_of_type_JavaxVecmathVector4f.z, paramyg.jdField_a_of_type_JavaxVecmathVector4f.w);
/*  836:     */      } else {
/*  837: 555 */        GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  838:     */      }
/*  839:     */      
/*  847: 565 */      GL15.glBindBuffer(34962, paramyg.jdField_c_of_type_JavaNioIntBuffer.get(0));
/*  848: 566 */      GL11.glNormalPointer(5126, 0, 0L);
/*  849:     */      
/*  852: 570 */      GlUtil.d();
/*  853: 571 */      int i1 = -1;
/*  854:     */      
/*  855: 573 */      javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f(paramCamera.f());
/*  856: 574 */      javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f(paramCamera.e());
/*  857: 575 */      localVector3f1.scale(0.3F);
/*  858: 576 */      localVector3f2.scale(0.3F);
/*  859: 577 */      long l = System.currentTimeMillis();
/*  860: 578 */      for (int i2 = 0; i2 < paramArrayOfxZ.length; i2++) {
/*  861: 579 */        xZ localxZ = paramArrayOfxZ[i2];
/*  862:     */        
/*  863: 581 */        if (paramCamera.a(localxZ.a())) {
/*  864: 582 */          paramyg.b(localxZ.a(paramyg));
/*  865:     */          
/*  868: 586 */          if (paramyg.jdField_c_of_type_Int != i1) {
/*  869: 587 */            GL15.glBindBuffer(34962, paramyg.jdField_b_of_type_JavaNioIntBuffer.get(paramyg.jdField_c_of_type_Int));
/*  870:     */            
/*  871: 589 */            GL11.glTexCoordPointer(2, 5126, 0, 0L);
/*  872: 590 */            i1 = paramyg.jdField_c_of_type_Int;
/*  873:     */          }
/*  874: 592 */          if (m != 0) {
/*  875: 593 */            xY localxY = (xY)localxZ;
/*  876: 594 */            GlUtil.a(zk.u, "selectionColor", localxY.a().x, localxY.a().y, localxY.a().z, localxY.a().w);
/*  877: 595 */            GlUtil.a(localxY.a().x, localxY.a().y, localxY.a().z, localxY.a().w);
/*  878:     */          }
/*  879: 597 */          GlUtil.d();
/*  880: 598 */          if (!paramyg.jdField_a_of_type_Boolean) {
/*  881: 599 */            GlUtil.c(localxZ.a().x + localVector3f1.x + localVector3f2.x, localxZ.a().y + localVector3f1.y + localVector3f2.y, localxZ.a().z + localVector3f1.z + localVector3f2.z);
/*  882:     */          } else {
/*  883: 601 */            GlUtil.c(localxZ.a().x, localxZ.a().y, localxZ.a().z);
/*  884:     */          }
/*  885:     */          
/*  886: 604 */          float f5 = localxZ.a(l);
/*  887: 605 */          jdField_e_of_type_JavaNioFloatBuffer.put(0, f5);
/*  888: 606 */          jdField_e_of_type_JavaNioFloatBuffer.put(5, -f5);
/*  889: 607 */          jdField_e_of_type_JavaNioFloatBuffer.put(10, f5);
/*  890:     */          
/*  891: 609 */          jdField_e_of_type_JavaNioFloatBuffer.put(12, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m30);
/*  892: 610 */          jdField_e_of_type_JavaNioFloatBuffer.put(13, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m31);
/*  893: 611 */          jdField_e_of_type_JavaNioFloatBuffer.put(14, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m32);
/*  894: 612 */          jdField_e_of_type_JavaNioFloatBuffer.rewind();
/*  895: 613 */          GlUtil.a(jdField_e_of_type_JavaNioFloatBuffer);
/*  896: 614 */          if ((n != 0) && (((yc)localxZ).b()))
/*  897:     */          {
/*  898: 616 */            org.lwjgl.util.vector.Vector4f localVector4f = new org.lwjgl.util.vector.Vector4f();
/*  899: 617 */            Matrix4f.transform(xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f, new org.lwjgl.util.vector.Vector4f(0.0F, 0.0F, 0.0F, 1.0F), localVector4f);
/*  900: 618 */            Matrix4f.transform(xe.b, new org.lwjgl.util.vector.Vector4f(localVector4f), localVector4f);
/*  901:     */            
/*  902: 620 */            float f6 = localVector4f.z / localVector4f.w * 0.5F + 0.5F;
/*  903:     */            
/*  904: 622 */            float f9 = f6;float f8 = f2;float f7 = f1;yg localyg = paramyg;Matrix4f localMatrix4f1 = xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f;Matrix4f localMatrix4f2 = xe.b;jdField_d_of_type_JavaNioIntBuffer.rewind();jdField_d_of_type_JavaNioIntBuffer.put(xe.jdField_a_of_type_JavaNioIntBuffer);xe.jdField_a_of_type_JavaNioIntBuffer.rewind();jdField_d_of_type_JavaNioIntBuffer.rewind();jdField_d_of_type_JavaNioIntBuffer.put(0, 0);jdField_d_of_type_JavaNioIntBuffer.put(1, 0);jdField_d_of_type_JavaNioIntBuffer.put(2, jdField_d_of_type_JavaNioIntBuffer.get(2));jdField_d_of_type_JavaNioIntBuffer.put(3, jdField_d_of_type_JavaNioIntBuffer.get(3)); if (jdField_g_of_type_JavaNioFloatBuffer == null) { jdField_f_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);jdField_g_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16); } jdField_g_of_type_JavaNioFloatBuffer.rewind();localMatrix4f1.store(jdField_g_of_type_JavaNioFloatBuffer);jdField_g_of_type_JavaNioFloatBuffer.rewind();jdField_f_of_type_JavaNioFloatBuffer.rewind();localMatrix4f2.store(jdField_f_of_type_JavaNioFloatBuffer);jdField_f_of_type_JavaNioFloatBuffer.rewind();GLU.gluUnProject(f7, f8, f9, jdField_g_of_type_JavaNioFloatBuffer, jdField_f_of_type_JavaNioFloatBuffer, jdField_d_of_type_JavaNioIntBuffer, jdField_h_of_type_JavaNioFloatBuffer);f7 = jdField_h_of_type_JavaNioFloatBuffer.get(0);f8 = jdField_h_of_type_JavaNioFloatBuffer.get(1);jdField_h_of_type_JavaNioFloatBuffer.get(2);f7 += localyg.jdField_d_of_type_Int / 2;f8 += localyg.jdField_e_of_type_Int / 2;int i4 = (f7 < localyg.jdField_d_of_type_Int) && (f7 > 0.0F) ? 1 : 0;int i3 = (f8 < localyg.jdField_e_of_type_Int) && (f8 > 0.0F) ? 1 : 0; if (((i4 != 0) && (i3 != 0) ? 1 : 0) != 0) {
/*  905: 623 */              if (localyc != null) {
/*  906: 624 */                if (localyc.a() > ((yc)localxZ).a()) {
/*  907: 625 */                  localyc.a();
/*  908: 626 */                  ((yc)localxZ).a(f6);
/*  909: 627 */                  localyc = (yc)localxZ;
/*  910:     */                } else {
/*  911: 629 */                  ((yc)localxZ).a();
/*  912:     */                }
/*  913:     */              } else {
/*  914: 632 */                ((yc)localxZ).a(f6);
/*  915: 633 */                localyc = (yc)localxZ;
/*  916:     */              }
/*  917:     */            } else {
/*  918: 636 */              ((yc)localxZ).a();
/*  919:     */            }
/*  920:     */          }
/*  921:     */          
/*  923: 641 */          GL11.glDrawArrays(7, 0, 4);
/*  924:     */          
/*  925: 643 */          GlUtil.c();
/*  926:     */        }
/*  927:     */      }
/*  928:     */      
/*  930: 648 */      GlUtil.c();
/*  931: 649 */      zj.e();
/*  932:     */      
/*  935: 653 */      GL11.glDisableClientState(32884);
/*  936:     */      
/*  937: 655 */      GL11.glDisableClientState(32888);
/*  938:     */      
/*  939: 657 */      GL11.glDisableClientState(32885);
/*  940:     */    } else {
/*  941: 659 */      float f3 = paramyg.jdField_a_of_type_ZB.a().jdField_b_of_type_Int * paramyg.a.x;
/*  942:     */      
/*  948: 666 */      float f4 = paramyg.jdField_a_of_type_ZB.a().jdField_a_of_type_Int * paramyg.a.y;
/*  949:     */      
/*  950: 668 */      GL11.glBegin(7);
/*  951:     */      
/*  952: 670 */      if (paramyg.jdField_a_of_type_Boolean) {
/*  953: 671 */        GL11.glTexCoord2f(0.0F, 0.0F);
/*  954: 672 */        GL11.glVertex3f(-f3 / 2.0F, -f4 / 2.0F, 0.0F);
/*  955: 673 */        GL11.glTexCoord2f(0.0F, 1.0F);
/*  956: 674 */        GL11.glVertex3f(-f3 / 2.0F, f4 / 2.0F, 0.0F);
/*  957: 675 */        GL11.glTexCoord2f(1.0F, 1.0F);
/*  958: 676 */        GL11.glVertex3f(f3 / 2.0F, f4 / 2.0F, 0.0F);
/*  959: 677 */        GL11.glTexCoord2f(1.0F, 0.0F);
/*  960: 678 */        GL11.glVertex3f(f3 / 2.0F, -f4 / 2.0F, 0.0F);
/*  961:     */      } else {
/*  962: 680 */        GL11.glTexCoord2f(0.0F, 0.0F);
/*  963: 681 */        GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/*  964: 682 */        GL11.glTexCoord2f(0.0F, 1.0F);
/*  965: 683 */        GL11.glVertex3f(0.0F, f4, 0.0F);
/*  966: 684 */        GL11.glTexCoord2f(1.0F, 1.0F);
/*  967: 685 */        GL11.glVertex3f(f3, f4, 0.0F);
/*  968: 686 */        GL11.glTexCoord2f(1.0F, 0.0F);
/*  969: 687 */        GL11.glVertex3f(f3, 0.0F, 0.0F);
/*  970:     */      }
/*  971:     */      
/*  972: 690 */      GL11.glEnd();
/*  973:     */    }
/*  974: 692 */    paramyg.jdField_a_of_type_ZB.a();zC.b();
/*  975:     */    
/*  976: 694 */    GlUtil.c();
/*  977:     */    
/*  979: 697 */    GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  980:     */    
/*  981: 699 */    GL11.glDisable(3042);
/*  982: 700 */    GL11.glEnable(2929);
/*  983: 701 */    GL11.glDisable(2903);
/*  984: 702 */    GL11.glEnable(2896);
/*  985: 703 */    if (xu.y.b()) {
/*  986: 704 */      n();
/*  987:     */    }
/*  988:     */  }
/*  989:     */  
/*  990:     */  public static void a(yg paramyg, Collection paramCollection, Camera paramCamera)
/*  991:     */  {
/*  992: 710 */    if (paramyg.jdField_h_of_type_Boolean) {
/*  993: 711 */      paramyg.c();
/*  994:     */    }
/*  995:     */    
/*  996: 714 */    if (paramyg.h()) {
/*  997: 715 */      return;
/*  998:     */    }
/*  999:     */    
/* 1001: 719 */    int m = (paramCollection.size() > 0) && ((paramCollection.iterator().next() instanceof xY)) ? 1 : 0;
/* 1002:     */    
/* 1004: 722 */    if (xu.y.b()) {
/* 1005: 723 */      n();
/* 1006:     */    }
/* 1007: 725 */    GlUtil.d();
/* 1008:     */    
/* 1010: 728 */    GL11.glDisable(2896);
/* 1011: 729 */    if (paramyg.jdField_b_of_type_Boolean) {
/* 1012: 730 */      GL11.glEnable(2929);
/* 1013:     */    } else {
/* 1014: 732 */      GL11.glDisable(2929);
/* 1015:     */    }
/* 1016:     */    
/* 1017: 735 */    xY localxY = null; if (paramyg.jdField_c_of_type_Boolean) {
/* 1018: 736 */      GL11.glEnable(3042);
/* 1019: 737 */      if (paramyg.jdField_a_of_type_Int == 0) {
/* 1020: 738 */        GL11.glBlendFunc(770, 771);
/* 1021:     */      } else {
/* 1022: 740 */        GL11.glBlendFunc(770, 1);
/* 1023:     */      }
/* 1024:     */      
/* 1026:     */    }
/* 1027:     */    else
/* 1028:     */    {
/* 1029: 747 */      GL11.glDisable(3042);
/* 1030:     */    }
/* 1031:     */    
/* 1032: 750 */    paramyg.jdField_a_of_type_ZB.a().a();
/* 1033:     */    
/* 1035: 753 */    if ((paramyg.jdField_g_of_type_Boolean) && (xu.N.b()))
/* 1036:     */    {
/* 1037: 755 */      zk.u.c();
/* 1038:     */      
/* 1039: 757 */      GlUtil.a(zk.u, "selectionColor", 1.0F, 1.0F, 1.0F, 1.0F);
/* 1040: 758 */      GlUtil.a(zk.u, "mainTexA", 0);
/* 1041:     */      
/* 1042: 760 */      GL11.glEnableClientState(32884);
/* 1043:     */      
/* 1044: 762 */      GL11.glEnableClientState(32888);
/* 1045:     */      
/* 1046: 764 */      GL11.glEnableClientState(32885);
/* 1047:     */      
/* 1049: 767 */      GL15.glBindBuffer(34962, paramyg.jdField_a_of_type_JavaNioIntBuffer.get(0));
/* 1050:     */      
/* 1051: 769 */      GL11.glVertexPointer(3, 5126, 0, 0L);
/* 1052:     */      
/* 1053: 771 */      GL13.glActiveTexture(33984);
/* 1054:     */      
/* 1055: 773 */      if (paramyg.jdField_a_of_type_JavaxVecmathVector4f != null) {
/* 1056: 774 */        GlUtil.a(paramyg.jdField_a_of_type_JavaxVecmathVector4f.x, paramyg.jdField_a_of_type_JavaxVecmathVector4f.y, paramyg.jdField_a_of_type_JavaxVecmathVector4f.z, paramyg.jdField_a_of_type_JavaxVecmathVector4f.w);
/* 1057:     */      } else {
/* 1058: 776 */        GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/* 1059:     */      }
/* 1060:     */      
/* 1066: 784 */      GL15.glBindBuffer(34962, paramyg.jdField_c_of_type_JavaNioIntBuffer.get(0));
/* 1067:     */      
/* 1068: 786 */      GL11.glNormalPointer(5126, 0, 0L);
/* 1069:     */      
/* 1071: 789 */      GlUtil.d();
/* 1072:     */      
/* 1074: 792 */      int n = -1;
/* 1075:     */      
/* 1076: 794 */      javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f(paramCamera.f());
/* 1077: 795 */      javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f(paramCamera.e());
/* 1078: 796 */      localVector3f1.scale(0.3F);
/* 1079: 797 */      localVector3f2.scale(0.3F);
/* 1080: 798 */      long l = System.currentTimeMillis();
/* 1081: 799 */      for (xZ localxZ : paramCollection) {
/* 1082: 800 */        if ((paramCamera.a(localxZ.a())) && (localxZ.a(paramyg) >= 0))
/* 1083:     */        {
/* 1084: 802 */          paramyg.b(localxZ.a(paramyg));
/* 1085:     */          
/* 1088: 806 */          localxY = null; if (paramyg.jdField_c_of_type_Int != n) {
/* 1089: 807 */            GL15.glBindBuffer(34962, paramyg.jdField_b_of_type_JavaNioIntBuffer.get(paramyg.jdField_c_of_type_Int));
/* 1090:     */            
/* 1091: 809 */            GL11.glTexCoordPointer(2, 5126, 0, 0L);
/* 1092: 810 */            n = paramyg.jdField_c_of_type_Int;
/* 1093:     */          }
/* 1094: 812 */          if (m != 0) {
/* 1095: 813 */            localxY = (xY)localxZ;
/* 1096: 814 */            GlUtil.a(zk.u, "selectionColor", localxY.a().x, localxY.a().y, localxY.a().z, localxY.a().w);
/* 1097: 815 */            GlUtil.a(localxY.a().x, localxY.a().y, localxY.a().z, localxY.a().w);
/* 1098:     */          }
/* 1099:     */          
/* 1100: 818 */          GlUtil.d();
/* 1101: 819 */          if (!paramyg.jdField_a_of_type_Boolean) {
/* 1102: 820 */            GlUtil.c(localxZ.a().x + localVector3f1.x + localVector3f2.x, localxZ.a().y + localVector3f1.y + localVector3f2.y, localxZ.a().z + localVector3f1.z + localVector3f2.z);
/* 1103:     */          } else {
/* 1104: 822 */            GlUtil.c(localxZ.a().x, localxZ.a().y, localxZ.a().z);
/* 1105:     */          }
/* 1106:     */          
/* 1107: 825 */          float f3 = localxZ.a(l);
/* 1108: 826 */          jdField_e_of_type_JavaNioFloatBuffer.put(0, f3);
/* 1109: 827 */          jdField_e_of_type_JavaNioFloatBuffer.put(5, -f3);
/* 1110: 828 */          jdField_e_of_type_JavaNioFloatBuffer.put(10, f3);
/* 1111:     */          
/* 1112: 830 */          jdField_e_of_type_JavaNioFloatBuffer.put(12, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m30);
/* 1113: 831 */          jdField_e_of_type_JavaNioFloatBuffer.put(13, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m31);
/* 1114: 832 */          jdField_e_of_type_JavaNioFloatBuffer.put(14, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m32);
/* 1115: 833 */          jdField_e_of_type_JavaNioFloatBuffer.rewind();
/* 1116: 834 */          GlUtil.a(jdField_e_of_type_JavaNioFloatBuffer);
/* 1117:     */          
/* 1119: 837 */          GL11.glDrawArrays(7, 0, 4);
/* 1120: 838 */          GlUtil.c();
/* 1121:     */        }
/* 1122:     */      }
/* 1123:     */      
/* 1125: 843 */      GlUtil.c();
/* 1126: 844 */      zj.e();
/* 1127:     */      
/* 1130: 848 */      GL11.glDisableClientState(32884);
/* 1131:     */      
/* 1132: 850 */      GL11.glDisableClientState(32888);
/* 1133:     */      
/* 1134: 852 */      GL11.glDisableClientState(32885);
/* 1135:     */    } else {
/* 1136: 854 */      paramCollection = null;float f1 = paramyg.jdField_a_of_type_ZB.a().jdField_b_of_type_Int * paramyg.a.x;
/* 1137:     */      
/* 1143: 861 */      float f2 = paramyg.jdField_a_of_type_ZB.a().jdField_a_of_type_Int * paramyg.a.y;
/* 1144:     */      
/* 1145: 863 */      GL11.glBegin(7);
/* 1146:     */      
/* 1147: 865 */      if (paramyg.jdField_a_of_type_Boolean) {
/* 1148: 866 */        GL11.glTexCoord2f(0.0F, 0.0F);
/* 1149: 867 */        GL11.glVertex3f(-f1 / 2.0F, -f2 / 2.0F, 0.0F);
/* 1150: 868 */        GL11.glTexCoord2f(0.0F, 1.0F);
/* 1151: 869 */        GL11.glVertex3f(-f1 / 2.0F, f2 / 2.0F, 0.0F);
/* 1152: 870 */        GL11.glTexCoord2f(1.0F, 1.0F);
/* 1153: 871 */        GL11.glVertex3f(f1 / 2.0F, f2 / 2.0F, 0.0F);
/* 1154: 872 */        GL11.glTexCoord2f(1.0F, 0.0F);
/* 1155: 873 */        GL11.glVertex3f(f1 / 2.0F, -f2 / 2.0F, 0.0F);
/* 1156:     */      } else {
/* 1157: 875 */        GL11.glTexCoord2f(0.0F, 0.0F);
/* 1158: 876 */        GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/* 1159: 877 */        GL11.glTexCoord2f(0.0F, 1.0F);
/* 1160: 878 */        GL11.glVertex3f(0.0F, f2, 0.0F);
/* 1161: 879 */        GL11.glTexCoord2f(1.0F, 1.0F);
/* 1162: 880 */        GL11.glVertex3f(f1, f2, 0.0F);
/* 1163: 881 */        GL11.glTexCoord2f(1.0F, 0.0F);
/* 1164: 882 */        GL11.glVertex3f(f1, 0.0F, 0.0F);
/* 1165:     */      }
/* 1166:     */      
/* 1167: 885 */      GL11.glEnd();
/* 1168:     */    }
/* 1169: 887 */    paramyg.jdField_a_of_type_ZB.a();zC.b();
/* 1170:     */    
/* 1171: 889 */    GlUtil.c();
/* 1172:     */    
/* 1174: 892 */    GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/* 1175:     */    
/* 1176: 894 */    GL11.glDisable(3042);
/* 1177: 895 */    GL11.glEnable(2929);
/* 1178: 896 */    GL11.glDisable(2903);
/* 1179: 897 */    GL11.glEnable(2896);
/* 1180: 898 */    if (xu.y.b()) {
/* 1181: 899 */      n();
/* 1182:     */    }
/* 1183:     */  }
/* 1184:     */  
/* 1190:     */  private yg(int paramInt1, int paramInt2)
/* 1191:     */  {
/* 1192: 910 */    this.jdField_a_of_type_ZB = new zB();
/* 1193: 911 */    this.jdField_d_of_type_Int = paramInt1;
/* 1194: 912 */    this.jdField_e_of_type_Int = paramInt2;
/* 1195:     */  }
/* 1196:     */  
/* 1202:     */  public yg(zC paramzC)
/* 1203:     */  {
/* 1204: 922 */    this(paramzC.jdField_b_of_type_Int, paramzC.jdField_a_of_type_Int);
/* 1205: 923 */    a().c(paramzC);
/* 1206:     */  }
/* 1207:     */  
/* 1210:     */  public final void b()
/* 1211:     */  {
/* 1212:     */    
/* 1213:     */    
/* 1214: 932 */    if (this.jdField_d_of_type_Boolean) {
/* 1215: 933 */      GL11.glScalef(1.0F, -1.0F, 1.0F);
/* 1216:     */    }
/* 1217: 935 */    r();
/* 1218: 936 */    d(this);
/* 1219: 937 */    GlUtil.c();
/* 1220:     */  }
/* 1221:     */  
/* 1224:     */  public final int a()
/* 1225:     */  {
/* 1226: 944 */    return this.jdField_e_of_type_Int;
/* 1227:     */  }
/* 1228:     */  
/* 1231:     */  public final int b()
/* 1232:     */  {
/* 1233: 951 */    return this.jdField_b_of_type_Int;
/* 1234:     */  }
/* 1235:     */  
/* 1259:     */  public final javax.vecmath.Vector4f a()
/* 1260:     */  {
/* 1261: 979 */    return this.jdField_a_of_type_JavaxVecmathVector4f;
/* 1262:     */  }
/* 1263:     */  
/* 1265:     */  public final int c()
/* 1266:     */  {
/* 1267: 985 */    return this.jdField_d_of_type_Int;
/* 1268:     */  }
/* 1269:     */  
/* 1319:     */  public final void c()
/* 1320:     */  {
/* 1321:1039 */    if (!this.jdField_h_of_type_Boolean) {
/* 1322:1040 */      return;
/* 1323:     */    }
/* 1324:1042 */    if (this.jdField_g_of_type_Boolean)
/* 1325:     */    {
/* 1326:1044 */      int m = 1;
/* 1327:1045 */      int n = 1;
/* 1328:1046 */      n[] arrayOfn1 = null; if (this.jdField_b_of_type_Int > 1) {
/* 1329:1047 */        if (this.jdField_f_of_type_Int != this.jdField_g_of_type_Int) {
/* 1330:1048 */          System.err.println("UNEVEN PARTS OF SPRITE " + this.jdField_f_of_type_Int + " / " + this.jdField_g_of_type_Int);
/* 1331:     */        }
/* 1332:1050 */        m = this.jdField_f_of_type_Int;
/* 1333:1051 */        n = this.jdField_g_of_type_Int;
/* 1334:     */      }
/* 1335:     */      
/* 1336:1054 */      float f1 = 1.0F / m;
/* 1337:1055 */      float f2 = 1.0F / n;
/* 1338:     */      
/* 1340:1058 */      if (jdField_b_of_type_JavaNioFloatBuffer == null) {
/* 1341:1059 */        jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(jdField_h_of_type_Int);
/* 1342:1060 */        jdField_c_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(jdField_i_of_type_Int);
/* 1343:1061 */        jdField_d_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(j);
/* 1344:     */      } else {
/* 1345:1063 */        jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 1346:1064 */        jdField_c_of_type_JavaNioFloatBuffer.rewind();
/* 1347:1065 */        jdField_d_of_type_JavaNioFloatBuffer.rewind();
/* 1348:     */      }
/* 1349:     */      int i3;
/* 1350:     */      try
/* 1351:     */      {
/* 1352:1070 */        arrayOfn1 = new n[4];
/* 1353:1071 */        n[] arrayOfn2 = new n[4];
/* 1354:1072 */        n localn1 = null; if (this.jdField_a_of_type_Boolean) {
/* 1355:1073 */          arrayOfn1[3] = new n(-this.jdField_d_of_type_Int / 2, -this.jdField_e_of_type_Int / 2, 0.0F);
/* 1356:1074 */          arrayOfn2[3] = new n(0.0F, 0.0F, 1.0F);
/* 1357:     */          
/* 1358:1076 */          arrayOfn1[2] = new n(this.jdField_d_of_type_Int / 2, -this.jdField_e_of_type_Int / 2, 0.0F);
/* 1359:1077 */          arrayOfn2[2] = new n(0.0F, 0.0F, 1.0F);
/* 1360:     */          
/* 1361:1079 */          arrayOfn1[1] = new n(this.jdField_d_of_type_Int / 2, this.jdField_e_of_type_Int / 2, 0.0F);
/* 1362:1080 */          arrayOfn2[1] = new n(0.0F, 0.0F, 1.0F);
/* 1363:     */          
/* 1364:1082 */          arrayOfn1[0] = new n(-this.jdField_d_of_type_Int / 2, this.jdField_e_of_type_Int / 2, 0.0F);
/* 1365:1083 */          arrayOfn2[0] = new n(0.0F, 0.0F, 1.0F);
/* 1366:     */        } else {
/* 1367:1085 */          arrayOfn1[3] = new n(0.0F, 0.0F, 0.0F);
/* 1368:1086 */          arrayOfn2[3] = new n(0.0F, 0.0F, 1.0F);
/* 1369:     */          
/* 1370:1088 */          arrayOfn1[2] = new n(this.jdField_d_of_type_Int, 0.0F, 0.0F);
/* 1371:1089 */          arrayOfn2[2] = new n(0.0F, 0.0F, 1.0F);
/* 1372:     */          
/* 1373:1091 */          arrayOfn1[1] = new n(this.jdField_d_of_type_Int, this.jdField_e_of_type_Int, 0.0F);
/* 1374:1092 */          arrayOfn2[1] = new n(0.0F, 0.0F, 1.0F);
/* 1375:     */          
/* 1376:1094 */          arrayOfn1[0] = new n(0.0F, this.jdField_e_of_type_Int, 0.0F);
/* 1377:1095 */          arrayOfn2[0] = new n(0.0F, 0.0F, 1.0F);
/* 1378:     */        }
/* 1379:     */        
/* 1381:1099 */        for (i3 = 0; i3 < arrayOfn1.length; i3++) {
/* 1382:1100 */          localn1 = arrayOfn1[i3];
/* 1383:1101 */          n localn2 = arrayOfn2[i3];
/* 1384:     */          
/* 1385:1103 */          jdField_b_of_type_JavaNioFloatBuffer.put(localn1.a);
/* 1386:1104 */          jdField_b_of_type_JavaNioFloatBuffer.put(localn1.b);
/* 1387:1105 */          jdField_b_of_type_JavaNioFloatBuffer.put(localn1.c);
/* 1388:     */          
/* 1389:1107 */          jdField_d_of_type_JavaNioFloatBuffer.put(localn2.a);
/* 1390:1108 */          jdField_d_of_type_JavaNioFloatBuffer.put(localn2.b);
/* 1391:1109 */          jdField_d_of_type_JavaNioFloatBuffer.put(localn2.c);
/* 1392:     */        }
/* 1393:     */      } catch (BufferOverflowException localBufferOverflowException) {
/* 1394:1112 */        
/* 1395:     */        
/* 1399:1117 */          localBufferOverflowException;
/* 1400:     */      }
/* 1401:     */      
/* 1407:1120 */      jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 1408:     */      
/* 1409:1122 */      jdField_d_of_type_JavaNioFloatBuffer.rewind();
/* 1410:     */      
/* 1414:1127 */      GL15.glGenBuffers(this.jdField_a_of_type_JavaNioIntBuffer);
/* 1415:1128 */      GL15.glBindBuffer(34962, this.jdField_a_of_type_JavaNioIntBuffer.get(0));
/* 1416:     */      
/* 1417:1130 */      GL15.glBufferData(34962, jdField_b_of_type_JavaNioFloatBuffer, 35044);
/* 1418:     */      
/* 1419:1132 */      this.jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(this.jdField_b_of_type_Int);
/* 1420:1133 */      GL15.glGenBuffers(this.jdField_b_of_type_JavaNioIntBuffer);
/* 1421:     */      
/* 1425:1138 */      int i1 = 0;
/* 1426:     */      
/* 1427:1140 */      for (int i2 = 0; i2 < n; i2++) {
/* 1428:1141 */        for (i3 = 0; i3 < m; i3++)
/* 1429:     */        {
/* 1430:1143 */          float f6 = (i2 + 1) * f2;float f5 = i2 * f2;float f4 = (i3 + 1) * f1;float f3 = i3 * f1; javax.vecmath.Vector3f[] arrayOfVector3f; (arrayOfVector3f = new javax.vecmath.Vector3f[4])[3] = new javax.vecmath.Vector3f(f3, f5, 0.0F);arrayOfVector3f[2] = new javax.vecmath.Vector3f(f4, f5, 0.0F);arrayOfVector3f[1] = new javax.vecmath.Vector3f(f4, f6, 0.0F);arrayOfVector3f[0] = new javax.vecmath.Vector3f(f3, f6, 0.0F);Object localObject = arrayOfVector3f;
/* 1431:     */          
/* 1432:1145 */          jdField_c_of_type_JavaNioFloatBuffer.rewind();
/* 1433:     */          
/* 1434:1147 */          for (int i4 = 0; i4 < localObject.length; i4++) {
/* 1435:1148 */            jdField_c_of_type_JavaNioFloatBuffer.put(localObject[i4].x);
/* 1436:1149 */            jdField_c_of_type_JavaNioFloatBuffer.put(localObject[i4].y);
/* 1437:     */          }
/* 1438:1151 */          jdField_c_of_type_JavaNioFloatBuffer.rewind();
/* 1439:     */          
/* 1440:1153 */          GL15.glBindBuffer(34962, this.jdField_b_of_type_JavaNioIntBuffer.get(i1));
/* 1441:     */          
/* 1442:1155 */          GL15.glBufferData(34962, jdField_c_of_type_JavaNioFloatBuffer, 35044);
/* 1443:1156 */          i1++;
/* 1444:     */        }
/* 1445:     */      }
/* 1446:     */      
/* 1448:1161 */      GL15.glGenBuffers(this.jdField_c_of_type_JavaNioIntBuffer);
/* 1449:1162 */      GL15.glBindBuffer(34962, this.jdField_c_of_type_JavaNioIntBuffer.get(0));
/* 1450:     */      
/* 1451:1164 */      GL15.glBufferData(34962, jdField_d_of_type_JavaNioFloatBuffer, 35044);
/* 1452:     */    }
/* 1453:     */    
/* 1457:1170 */    this.jdField_h_of_type_Boolean = false;
/* 1458:     */  }
/* 1459:     */  
/* 1464:     */  public final void a(boolean paramBoolean)
/* 1465:     */  {
/* 1466:1179 */    this.jdField_f_of_type_Boolean = paramBoolean;
/* 1467:     */  }
/* 1468:     */  
/* 1473:     */  public final void d()
/* 1474:     */  {
/* 1475:1188 */    this.jdField_c_of_type_Boolean = true;
/* 1476:     */  }
/* 1477:     */  
/* 1482:     */  public final void b(boolean paramBoolean)
/* 1483:     */  {
/* 1484:1197 */    this.jdField_b_of_type_Boolean = paramBoolean;
/* 1485:     */  }
/* 1486:     */  
/* 1489:     */  public final void c(boolean paramBoolean)
/* 1490:     */  {
/* 1491:1204 */    this.jdField_d_of_type_Boolean = paramBoolean;
/* 1492:     */  }
/* 1493:     */  
/* 1496:     */  public final void a(int paramInt)
/* 1497:     */  {
/* 1498:1211 */    this.jdField_e_of_type_Int = paramInt;
/* 1499:     */  }
/* 1500:     */  
/* 1504:     */  public final void a(int paramInt1, int paramInt2)
/* 1505:     */  {
/* 1506:1219 */    this.jdField_b_of_type_Int = (paramInt1 * paramInt2);
/* 1507:1220 */    this.jdField_f_of_type_Int = paramInt1;
/* 1508:1221 */    this.jdField_g_of_type_Int = paramInt2;
/* 1509:     */  }
/* 1510:     */  
/* 1522:     */  public final void d(boolean paramBoolean)
/* 1523:     */  {
/* 1524:1237 */    this.jdField_a_of_type_Boolean = paramBoolean;
/* 1525:     */  }
/* 1526:     */  
/* 1530:     */  public final void b(int paramInt)
/* 1531:     */  {
/* 1532:1245 */    if ((!jdField_i_of_type_Boolean) && ((paramInt >= this.jdField_b_of_type_Int) || (paramInt < 0))) throw new AssertionError("tried to set " + paramInt + " / " + this.jdField_b_of_type_Int);
/* 1533:1246 */    this.jdField_c_of_type_Int = paramInt;
/* 1534:     */  }
/* 1535:     */  
/* 1536:     */  public final void c(javax.vecmath.Vector4f paramVector4f) {
/* 1537:1250 */    this.jdField_a_of_type_JavaxVecmathVector4f = paramVector4f;
/* 1538:     */  }
/* 1539:     */  
/* 1542:     */  public final void c(int paramInt)
/* 1543:     */  {
/* 1544:1257 */    this.jdField_d_of_type_Int = paramInt;
/* 1545:     */  }
/* 1546:     */  
/* 1550:     */  public final void a(xq paramxq)
/* 1551:     */  {
/* 1552:1265 */    System.err.println("Cannot update static Sprite");
/* 1553:     */  }
/* 1554:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */