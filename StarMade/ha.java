/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.HashMap;
/*   3:    */import javax.vecmath.Vector4f;
/*   4:    */import org.schema.game.common.data.player.faction.FactionNotFoundException;
/*   5:    */
/*  30:    */public final class ha
/*  31:    */  extends yz
/*  32:    */  implements ys
/*  33:    */{
/*  34:    */  private yx jdField_a_of_type_Yx;
/*  35:    */  private lP jdField_a_of_type_LP;
/*  36:    */  private mc jdField_a_of_type_Mc;
/*  37:    */  private boolean i;
/*  38:    */  boolean jdField_a_of_type_Boolean;
/*  39:    */  boolean jdField_b_of_type_Boolean;
/*  40:    */  boolean jdField_c_of_type_Boolean;
/*  41:    */  boolean jdField_d_of_type_Boolean;
/*  42:    */  private yr jdField_a_of_type_Yr;
/*  43:    */  private yN jdField_a_of_type_YN;
/*  44:    */  private yP jdField_a_of_type_YP;
/*  45:    */  private yP jdField_b_of_type_YP;
/*  46:    */  private yP jdField_c_of_type_YP;
/*  47:    */  private yP jdField_d_of_type_YP;
/*  48:    */  private yP e;
/*  49:    */  private yw jdField_a_of_type_Yw;
/*  50:    */  private yw jdField_b_of_type_Yw;
/*  51:    */  private yw jdField_c_of_type_Yw;
/*  52:    */  private yw jdField_d_of_type_Yw;
/*  53:    */  private String jdField_b_of_type_JavaLangString;
/*  54:    */  int jdField_a_of_type_Int;
/*  55:    */  
/*  56:    */  public ha(ct paramct, lP paramlP, int paramInt)
/*  57:    */  {
/*  58: 58 */    super(paramct);
/*  59: 59 */    this.jdField_a_of_type_LP = paramlP;
/*  60: 60 */    this.jdField_a_of_type_Mc = paramlP.a();
/*  61: 61 */    this.jdField_a_of_type_Int = paramInt;
/*  62: 62 */    this.jdField_d_of_type_Boolean = this.jdField_a_of_type_Mc.a(paramInt);
/*  63: 63 */    this.jdField_a_of_type_Boolean = this.jdField_a_of_type_Mc.b(paramInt);
/*  64: 64 */    this.jdField_b_of_type_Boolean = this.jdField_a_of_type_Mc.c(paramInt);
/*  65: 65 */    this.jdField_c_of_type_Boolean = this.jdField_a_of_type_Mc.d(paramInt);
/*  66:    */    
/*  67: 67 */    this.jdField_b_of_type_JavaLangString = this.jdField_a_of_type_Mc.a()[paramInt];
/*  68: 68 */    this.jdField_a_of_type_Yx = new yx(a(), 410.0F, 90.0F, paramInt % 2 == 0 ? new Vector4f(0.1F, 0.1F, 0.1F, 1.0F) : new Vector4f(0.2F, 0.2F, 0.2F, 1.0F));
/*  69:    */  }
/*  70:    */  
/* 113:    */  public final void c()
/* 114:    */  {
/* 115:    */    try
/* 116:    */    {
/* 117:117 */      Object localObject = this;int j = ((ct)a()).a().a().a(); lP locallP; if ((locallP = ((ct)((ha)localObject).a()).a().a().a(j)) == null) throw new FactionNotFoundException(Integer.valueOf(j)); if ((localObject = (lX)locallP.a().get(((ct)((ha)localObject).a()).a().getName())) == null) { throw new FactionNotFoundException(Integer.valueOf(j));
/* 118:    */      }
/* 119:119 */      if ((localObject = localObject).d(this.jdField_a_of_type_LP))
/* 120:    */      {
/* 121:121 */        this.jdField_a_of_type_Yr = new yr(a(), 400.0F, 100.0F);
/* 122:    */        
/* 123:123 */        this.jdField_c_of_type_YP = new yP(100, 20, a());
/* 124:124 */        this.jdField_d_of_type_YP = new yP(100, 20, a());
/* 125:125 */        this.e = new yP(100, 20, a());
/* 126:    */        
/* 127:127 */        this.jdField_b_of_type_YP = new yP(100, 20, a());
/* 128:128 */        this.jdField_a_of_type_YP = new yP(100, 20, a());
/* 129:    */        
/* 130:130 */        this.jdField_b_of_type_YP.a("Edit");
/* 131:131 */        this.jdField_c_of_type_YP.a("Kick");
/* 132:132 */        this.jdField_d_of_type_YP.a("Invite");
/* 133:133 */        this.e.a("Permission-Edit");
/* 134:134 */        this.jdField_a_of_type_YP.a("Permissions");
/* 135:    */        
/* 136:136 */        this.jdField_a_of_type_Yw = new hb(this, a());
/* 137:    */        
/* 164:164 */        this.jdField_b_of_type_Yw = new hc(this, a());
/* 165:    */        
/* 192:192 */        this.jdField_c_of_type_Yw = new hd(this, a());
/* 193:    */        
/* 220:220 */        this.jdField_d_of_type_Yw = new he(this, a());
/* 221:    */        
/* 247:247 */        this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_YP);
/* 248:248 */        this.jdField_b_of_type_YP.a(0.0F, 20.0F, 0.0F);
/* 249:    */        
/* 252:252 */        this.jdField_c_of_type_YP.a(70.0F, 20.0F, 0.0F);
/* 253:253 */        this.jdField_d_of_type_YP.a(140.0F, 20.0F, 0.0F);
/* 254:254 */        this.e.a(210.0F, 20.0F, 0.0F);
/* 255:    */        
/* 256:256 */        this.jdField_a_of_type_Yr.a(this.jdField_b_of_type_YP);
/* 257:257 */        this.jdField_a_of_type_Yr.a(this.jdField_c_of_type_YP);
/* 258:258 */        this.jdField_a_of_type_Yr.a(this.jdField_d_of_type_YP);
/* 259:259 */        this.jdField_a_of_type_Yr.a(this.e);
/* 260:    */        
/* 261:261 */        this.jdField_a_of_type_Yw.a(0.0F, 35.0F, 0.0F);
/* 262:262 */        this.jdField_b_of_type_Yw.a(70.0F, 35.0F, 0.0F);
/* 263:263 */        this.jdField_c_of_type_Yw.a(140.0F, 35.0F, 0.0F);
/* 264:264 */        this.jdField_d_of_type_Yw.a(210.0F, 35.0F, 0.0F);
/* 265:    */        
/* 266:266 */        this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_Yw);
/* 267:267 */        this.jdField_a_of_type_Yr.a(this.jdField_b_of_type_Yw);
/* 268:268 */        this.jdField_a_of_type_Yr.a(this.jdField_c_of_type_Yw);
/* 269:269 */        this.jdField_a_of_type_Yr.a(this.jdField_d_of_type_Yw);
/* 270:    */        
/* 271:271 */        this.jdField_a_of_type_Yr.a().y = 20.0F;
/* 272:    */        
/* 273:273 */        this.jdField_a_of_type_Yx.a(this.jdField_a_of_type_Yr);
/* 274:    */        
/* 275:275 */        this.jdField_a_of_type_YN = new yN(a(), 200, 20, new hf(this), new hg(this));
/* 276:    */        
/* 380:380 */        this.jdField_a_of_type_Yx.a(this.jdField_a_of_type_YN);
/* 381:    */      }
/* 382:    */      
/* 383:383 */      if (!((lX)localObject).d(this.jdField_a_of_type_LP))
/* 384:    */      {
/* 385:385 */        (localObject = new yP(100, 20, a())).a("You don't have permission to edit this member!");
/* 386:386 */        this.jdField_a_of_type_Yx.a((yz)localObject);
/* 387:    */      }
/* 388:    */    }
/* 389:    */    catch (FactionNotFoundException localFactionNotFoundException) {
/* 390:    */      yP localyP;
/* 391:391 */      (localyP = new yP(100, 20, a())).a("You don't have permission to edit this!");
/* 392:392 */      this.jdField_a_of_type_Yx.a(localyP);
/* 393:393 */      localFactionNotFoundException.printStackTrace();
/* 394:    */    }
/* 395:395 */    a(this.jdField_a_of_type_Yx);
/* 396:    */  }
/* 397:    */  
/* 409:    */  public final void a() {}
/* 410:    */  
/* 421:    */  public final void b()
/* 422:    */  {
/* 423:423 */    k();
/* 424:    */  }
/* 425:    */  
/* 426:    */  public final float a()
/* 427:    */  {
/* 428:428 */    return this.jdField_a_of_type_Yx.a();
/* 429:    */  }
/* 430:    */  
/* 431:    */  public final float b()
/* 432:    */  {
/* 433:433 */    return this.jdField_a_of_type_Yx.b();
/* 434:    */  }
/* 435:    */  
/* 437:    */  public final void a(yz paramyz, xp paramxp)
/* 438:    */  {
/* 439:439 */    System.err.println("TODO CALLBACK");
/* 440:    */  }
/* 441:    */  
/* 442:    */  public final boolean a()
/* 443:    */  {
/* 444:444 */    return false;
/* 445:    */  }
/* 446:    */  
/* 449:    */  public final boolean b()
/* 450:    */  {
/* 451:451 */    return this.jdField_a_of_type_Boolean;
/* 452:    */  }
/* 453:    */  
/* 464:    */  public final boolean c()
/* 465:    */  {
/* 466:466 */    return this.jdField_b_of_type_Boolean;
/* 467:    */  }
/* 468:    */  
/* 480:    */  public final boolean d()
/* 481:    */  {
/* 482:482 */    return this.jdField_c_of_type_Boolean;
/* 483:    */  }
/* 484:    */  
/* 496:    */  public final boolean e()
/* 497:    */  {
/* 498:498 */    return this.jdField_d_of_type_Boolean;
/* 499:    */  }
/* 500:    */  
/* 511:    */  public final String a()
/* 512:    */  {
/* 513:513 */    return this.jdField_b_of_type_JavaLangString;
/* 514:    */  }
/* 515:    */  
/* 526:    */  public final boolean f()
/* 527:    */  {
/* 528:528 */    return this.i;
/* 529:    */  }
/* 530:    */  
/* 534:    */  public final void a(boolean paramBoolean)
/* 535:    */  {
/* 536:536 */    this.i = paramBoolean;
/* 537:    */  }
/* 538:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ha
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */