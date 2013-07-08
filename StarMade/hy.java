/*   1:    */import java.util.Collection;
/*   2:    */import java.util.Iterator;
/*   3:    */import java.util.Observable;
/*   4:    */import java.util.Observer;
/*   5:    */import javax.vecmath.Vector4f;
/*   6:    */import org.schema.schine.network.client.ClientState;
/*   7:    */
/*  24:    */public final class hy
/*  25:    */  extends yz
/*  26:    */  implements Observer
/*  27:    */{
/*  28:    */  private yG jdField_a_of_type_YG;
/*  29:    */  private yA jdField_a_of_type_YA;
/*  30:    */  private int jdField_a_of_type_Int;
/*  31:    */  private int jdField_b_of_type_Int;
/*  32:    */  private ys jdField_b_of_type_Ys;
/*  33:    */  private boolean jdField_a_of_type_Boolean;
/*  34:    */  
/*  35:    */  public hy(ClientState paramClientState, ys paramys)
/*  36:    */  {
/*  37: 37 */    super(paramClientState);
/*  38: 38 */    this.jdField_a_of_type_Int = 540;
/*  39: 39 */    this.jdField_b_of_type_Int = 345;
/*  40: 40 */    this.jdField_b_of_type_Ys = paramys;
/*  41:    */    
/*  42: 42 */    ((ct)super.a()).a().a().addObserver(this);
/*  43:    */  }
/*  44:    */  
/*  49:    */  public final void a() {}
/*  50:    */  
/*  54:    */  public final void b()
/*  55:    */  {
/*  56: 56 */    if (this.jdField_a_of_type_Boolean) {
/*  57: 57 */      e();
/*  58: 58 */      this.jdField_a_of_type_Boolean = false;
/*  59:    */    }
/*  60:    */    
/*  61: 61 */    k();
/*  62:    */  }
/*  63:    */  
/*  64:    */  public final void c()
/*  65:    */  {
/*  66: 66 */    this.jdField_a_of_type_YG = new yG(this.jdField_a_of_type_Int, this.jdField_b_of_type_Int, (ct)super.a());
/*  67: 67 */    this.jdField_a_of_type_YA = new yA((ct)super.a());
/*  68: 68 */    this.jdField_a_of_type_YA.a(this.jdField_b_of_type_Ys);
/*  69: 69 */    this.jdField_a_of_type_YA.g = true;
/*  70:    */    
/*  72: 72 */    this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YA);
/*  73:    */    
/*  74: 74 */    e();
/*  75:    */    
/*  76: 76 */    a(this.jdField_a_of_type_YG);
/*  77:    */  }
/*  78:    */  
/*  79:    */  private void e()
/*  80:    */  {
/*  81: 81 */    this.jdField_a_of_type_YA.clear();
/*  82:    */    
/*  83: 83 */    Object localObject1 = ((ct)super.a()).a().a();
/*  84: 84 */    int i = 0;
/*  85: 85 */    for (localObject1 = ((lT)localObject1).a().iterator(); ((Iterator)localObject1).hasNext();) { Object localObject2 = (lP)((Iterator)localObject1).next();
/*  86:    */      
/*  87: 87 */      yA localyA = new yA((ct)super.a());
/*  88:    */      
/*  89: 89 */      yx localyx = new yx((ct)super.a(), 510.0F, 80.0F, i % 2 == 0 ? new Vector4f(0.0F, 0.0F, 0.0F, 0.0F) : new Vector4f(0.1F, 0.1F, 0.1F, 0.5F));
/*  90:    */      
/*  93:    */      hz localhz;
/*  94:    */      
/*  96: 96 */      (localhz = new hz((ct)super.a(), (lP)localObject2)).c();
/*  97: 97 */      localyx.a(localhz);
/*  98: 98 */      localyA.a(new yD(localyx, localyx, (ct)super.a()));
/*  99:    */      
/* 100:100 */      (
/* 101:    */      
/* 109:109 */        localObject2 = new hA((ct)super.a(), localyA, new hB(this, (ct)super.a(), (lP)localObject2, "+", i), new hB(this, (ct)super.a(), (lP)localObject2, "-", i))).addObserver(this);
/* 110:    */      
/* 111:111 */      this.jdField_a_of_type_YA.a(new yD((yz)localObject2, (yz)localObject2, (ct)super.a()));
/* 112:112 */      i++;
/* 113:    */    }
/* 114:    */  }
/* 115:    */  
/* 272:    */  public final float a()
/* 273:    */  {
/* 274:274 */    return this.jdField_a_of_type_Int;
/* 275:    */  }
/* 276:    */  
/* 277:    */  public final float b()
/* 278:    */  {
/* 279:279 */    return this.jdField_b_of_type_Int;
/* 280:    */  }
/* 281:    */  
/* 288:    */  public final void update(Observable paramObservable, Object paramObject)
/* 289:    */  {
/* 290:290 */    if ((paramObservable instanceof yC)) {
/* 291:291 */      this.jdField_a_of_type_YA.f();return;
/* 292:    */    }
/* 293:293 */    this.jdField_a_of_type_Boolean = true;
/* 294:    */  }
/* 295:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */