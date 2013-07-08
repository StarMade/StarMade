/*   1:    */import javax.vecmath.Vector3f;
/*   2:    */import org.schema.game.network.objects.NetworkShip;
/*   3:    */import org.schema.schine.network.objects.remote.RemoteVector3f;
/*   4:    */
/*  26:    */public final class tm
/*  27:    */  extends sJ
/*  28:    */{
/*  29:    */  private long jdField_a_of_type_Long;
/*  30:    */  
/*  31:    */  public tm(kd paramkd, boolean paramBoolean)
/*  32:    */  {
/*  33: 33 */    super("Turret_Ent", paramkd);
/*  34: 34 */    if (paramkd.isOnServer()) {
/*  35: 35 */      this.a = new tl(this, paramBoolean);
/*  36:    */    }
/*  37:    */  }
/*  38:    */  
/*  42:    */  public final void a(lb paramlb)
/*  43:    */  {
/*  44: 44 */    if (System.currentTimeMillis() - this.jdField_a_of_type_Long < 5000L) {
/*  45: 45 */      return;
/*  46:    */    }
/*  47: 47 */    if ((paramlb instanceof kd)) {
/*  48: 48 */      kd localkd = (kd)paramlb;
/*  49:    */      
/*  50: 50 */      if (((paramlb = ((vf)a()).a().a(localkd, a())) == lZ.b) || (paramlb == lZ.a))
/*  51:    */      {
/*  52: 52 */        this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  53: 53 */        ((sL)this.a).a(localkd);
/*  54:    */      }
/*  55:    */    }
/*  56:    */  }
/*  57:    */  
/*  58: 58 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  59:    */  
/*  64:    */  public final void b(xq paramxq)
/*  65:    */  {
/*  66: 66 */    super.b(paramxq);
/*  67:    */    
/*  70: 70 */    if ((a() instanceof to)) {
/*  71: 71 */      a().a().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  72: 72 */      a().a().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  73: 73 */      a().a().targetPosition.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  74: 74 */      a().a().targetVelocity.set(new Vector3f(0.0F, 0.0F, 0.0F));return;
/*  75:    */    }
/*  76: 76 */    if ((a() instanceof tn)) {
/*  77: 77 */      a().a().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  78: 78 */      a().a().targetPosition.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  79: 79 */      a().a().targetVelocity.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  80:    */      
/*  82: 82 */      this.jdField_a_of_type_JavaxVecmathVector3f.set(((tn)a()).a());
/*  83: 83 */      a().a().orientationDir.set(this.jdField_a_of_type_JavaxVecmathVector3f);
/*  84: 84 */      a(paramxq, this.jdField_a_of_type_JavaxVecmathVector3f);return;
/*  85:    */    }
/*  86: 86 */    if ((a() instanceof tp)) {
/*  87: 87 */      a().a().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  88: 88 */      a(paramxq, this.jdField_a_of_type_JavaxVecmathVector3f);
/*  89:    */      
/*  90: 90 */      paramxq = new Vector3f();
/*  91: 91 */      Vector3f localVector3f = new Vector3f();
/*  92:    */      
/*  93: 93 */      paramxq.set(((tp)a()).a());
/*  94: 94 */      localVector3f.set(((tp)a()).b());
/*  95:    */      
/*  97: 97 */      if (paramxq.length() > 0.0F) {
/*  98: 98 */        a().a().targetPosition.set(paramxq);
/*  99: 99 */        a().a().targetVelocity.set(localVector3f);
/* 100:    */        
/* 102:102 */        a(paramxq, localVector3f);
/* 103:    */        
/* 104:104 */        a(paramxq);
/* 105:105 */        b(paramxq);
/* 106:    */        
/* 107:107 */        ((tp)a()).a().set(0.0F, 0.0F, 0.0F);
/* 108:    */        
/* 109:109 */        a().a(new tz());
/* 110:    */      }
/* 111:111 */      return; }
/* 112:112 */    a().a().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/* 113:113 */    a().a().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     tm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */