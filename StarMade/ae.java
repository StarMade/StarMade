/*   1:    */import javax.vecmath.Vector3f;
/*   2:    */import org.lwjgl.input.Keyboard;
/*   3:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   4:    */
/*  23:    */public final class ae
/*  24:    */  extends U
/*  25:    */{
/*  26:    */  private Camera a;
/*  27:    */  
/*  28:    */  public ae(ct paramct)
/*  29:    */  {
/*  30: 30 */    super(paramct);
/*  31: 31 */    paramct = this; if (this.a == null) { xc localxc = new xc();paramct.a = new Camera(localxc); } if (xe.a() == null) { xe.a(paramct.a);
/*  32:    */    }
/*  33:    */  }
/*  34:    */  
/*  37:    */  public final void a(xp paramxp)
/*  38:    */  {
/*  39: 39 */    super.a(paramxp);
/*  40:    */  }
/*  41:    */  
/*  55:    */  public final void b(boolean paramBoolean)
/*  56:    */  {
/*  57: 57 */    wV.a = paramBoolean;
/*  58: 58 */    if (paramBoolean) {
/*  59: 59 */      xc localxc = new xc();
/*  60: 60 */      this.a = new Camera(localxc);
/*  61: 61 */      if (xe.a() != null) {
/*  62: 62 */        this.a.a().set(xe.a().a());
/*  63:    */      }
/*  64: 64 */      xe.a(this.a);
/*  65:    */    }
/*  66: 66 */    super.b(paramBoolean);
/*  67:    */  }
/*  68:    */  
/*  74:    */  public final void a(xq paramxq)
/*  75:    */  {
/*  76: 76 */    wV.a = true;
/*  77:    */    
/*  78: 78 */    xa localxa = xe.a().a();
/*  79:    */    
/*  81: 81 */    Vector3f localVector3f1 = new Vector3f(localxa.a.c());
/*  82: 82 */    Vector3f localVector3f2 = new Vector3f(localxa.a.f());
/*  83: 83 */    Vector3f localVector3f3 = new Vector3f(localxa.a.d());
/*  84:    */    
/*  87: 87 */    float f = Keyboard.isKeyDown(42) ? 50.0F : 5.0F;
/*  88: 88 */    localVector3f1.scale(f * paramxq.a());
/*  89: 89 */    localVector3f2.scale(f * paramxq.a());
/*  90: 90 */    localVector3f3.scale(f * paramxq.a());
/*  91:    */    
/*  94: 94 */    if ((!Keyboard.isKeyDown(17)) || (!Keyboard.isKeyDown(31))) {
/*  95: 95 */      if (Keyboard.isKeyDown(17)) {
/*  96: 96 */        localxa.a().add(localVector3f1);
/*  97:    */      }
/*  98: 98 */      if (Keyboard.isKeyDown(31)) {
/*  99: 99 */        localVector3f1.scale(-1.0F);
/* 100:100 */        localxa.a().add(localVector3f1);
/* 101:    */      }
/* 102:    */    }
/* 103:103 */    if ((!Keyboard.isKeyDown(30)) || (!Keyboard.isKeyDown(32))) {
/* 104:104 */      if (Keyboard.isKeyDown(30)) {
/* 105:105 */        localxa.a().add(localVector3f3);
/* 106:    */      }
/* 107:107 */      if (Keyboard.isKeyDown(32)) {
/* 108:108 */        localVector3f3.scale(-1.0F);
/* 109:109 */        localxa.a().add(localVector3f3);
/* 110:    */      }
/* 111:    */    }
/* 112:112 */    if ((!Keyboard.isKeyDown(16)) || (!Keyboard.isKeyDown(18))) {
/* 113:113 */      if (Keyboard.isKeyDown(16)) {
/* 114:114 */        localxa.a().add(localVector3f2);
/* 115:    */      }
/* 116:116 */      if (Keyboard.isKeyDown(18)) {
/* 117:117 */        localVector3f2.scale(-1.0F);
/* 118:118 */        localxa.a().add(localVector3f2);
/* 119:    */      }
/* 120:    */    }
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ae
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */