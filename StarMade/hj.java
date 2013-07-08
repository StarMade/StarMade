/*   1:    */import java.util.HashMap;
/*   2:    */import org.schema.schine.network.client.ClientState;
/*   3:    */
/*  15:    */public final class hj
/*  16:    */  extends yr
/*  17:    */{
/*  18:    */  private yG a;
/*  19:    */  private ys b;
/*  20:    */  
/*  21:    */  public hj(ClientState paramClientState, ys paramys)
/*  22:    */  {
/*  23: 23 */    super(paramClientState, 530.0F, 60.0F);
/*  24: 24 */    this.jdField_b_of_type_Ys = paramys;
/*  25:    */  }
/*  26:    */  
/*  30:    */  public final void b()
/*  31:    */  {
/*  32:    */    Object localObject;
/*  33:    */    
/*  35: 35 */    if ((localObject = (ct)a()).a().h() != 0) {
/*  36:    */      lP locallP;
/*  37: 37 */      if ((locallP = ((ct)localObject).a().a(((ct)localObject).a().h())) != null)
/*  38:    */      {
/*  39: 39 */        if (((localObject = (lX)locallP.a().get(((ct)localObject).a().getName())) != null) && (((lX)localObject).a(locallP))) {
/*  40: 40 */          super.b();
/*  41:    */        }
/*  42:    */      }
/*  43:    */    }
/*  44:    */  }
/*  45:    */  
/*  53:    */  public final void c()
/*  54:    */  {
/*  55: 55 */    this.jdField_a_of_type_YG = new yG(this.jdField_b_of_type_Float, this.jdField_a_of_type_Float, a());
/*  56:    */    
/*  61: 61 */    yr localyr = new yr(a(), 100.0F, 40.0F);
/*  62:    */    
/*  63:    */    yP localyP1;
/*  64: 64 */    (localyP1 = new yP(100, 20, a())).a("Public Faction");
/*  65: 65 */    hk localhk = new hk(a());
/*  66:    */    
/*  87:    */    yP localyP2;
/*  88:    */    
/* 108:108 */    (localyP2 = new yP(100, 20, a())).a("Consider neutral enemy");
/* 109:109 */    hl localhl = new hl(a());
/* 110:    */    
/* 131:    */    yP localyP3;
/* 132:    */    
/* 153:153 */    (localyP3 = new yP(100, 20, a())).a("Declare war on hostile action");
/* 154:154 */    hm localhm = new hm(a());
/* 155:    */    
/* 177:    */    yN localyN1;
/* 178:    */    
/* 199:199 */    (localyN1 = new yN(a(), 90, 20, "Post News", this.jdField_b_of_type_Ys, ((ct)a()).a().a.a.a)).a = "POST_NEWS";
/* 200:200 */    localyN1.a().x = 260.0F;
/* 201:    */    
/* 211:    */    hn localhn;
/* 212:    */    
/* 222:222 */    (localhn = new hn(a(), "Roles", this.jdField_b_of_type_Ys, ((ct)a()).a().a.a.a)).a = "ROLES";
/* 223:223 */    localhn.a().x = 430.0F;
/* 224:    */    
/* 225:    */    yN localyN2;
/* 226:    */    
/* 227:227 */    (localyN2 = new yN(a(), 120, 20, "Edit Description", this.jdField_b_of_type_Ys, ((ct)a()).a().a.a.a)).a = "EDIT_DESC";
/* 228:228 */    localyN2.a().x = 130.0F;
/* 229:    */    
/* 238:    */    yN localyN3;
/* 239:    */    
/* 248:248 */    (localyN3 = new yN(a(), 60, 20, "Offers", new ho(this), ((ct)a()).a().a.a.a)).a().x = 360.0F;
/* 249:    */    
/* 250:250 */    localhk.a().y = 23.0F;
/* 251:    */    
/* 254:254 */    localyP1.a().x = 35.0F;
/* 255:255 */    localyP1.a().y = 32.0F;
/* 256:    */    
/* 257:257 */    localhm.a().x = 145.0F;
/* 258:258 */    localhm.a().y = 23.0F;
/* 259:259 */    localyP3.a().x = 180.0F;
/* 260:260 */    localyP3.a().y = 32.0F;
/* 261:    */    
/* 264:264 */    localhl.a().x = 350.0F;
/* 265:265 */    localhl.a().y = 23.0F;
/* 266:266 */    localyP2.a().x = 385.0F;
/* 267:267 */    localyP2.a().y = 32.0F;
/* 268:    */    
/* 270:270 */    localyr.a(localhk);
/* 271:271 */    localyr.a(localyP1);
/* 272:272 */    localyr.a(localyP2);
/* 273:273 */    localyr.a(localhl);
/* 274:274 */    localyr.a(localyP3);
/* 275:275 */    localyr.a(localhm);
/* 276:276 */    localyr.a(localyN1);
/* 277:277 */    localyr.a(localyN2);
/* 278:278 */    localyr.a(localyN3);
/* 279:279 */    localyr.a(localhn);
/* 280:    */    
/* 281:281 */    this.jdField_a_of_type_YG.c(localyr);
/* 282:282 */    a(this.jdField_a_of_type_YG);
/* 283:283 */    super.c();
/* 284:    */  }
/* 285:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */