/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.ArrayList;
/*   3:    */import java.util.HashMap;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.List;
/*   6:    */import java.util.Observable;
/*   7:    */import java.util.Observer;
/*   8:    */import java.util.TreeMap;
/*   9:    */import org.lwjgl.input.Mouse;
/*  10:    */import org.newdawn.slick.Color;
/*  11:    */import org.schema.game.common.data.element.ElementInformation;
/*  12:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  13:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  14:    */import org.schema.schine.network.client.ClientState;
/*  15:    */
/*  41:    */public class iw
/*  42:    */  extends yz
/*  43:    */  implements Observer, ys
/*  44:    */{
/*  45:    */  private yE jdField_a_of_type_YE;
/*  46:    */  private yG jdField_a_of_type_YG;
/*  47:    */  private yA jdField_a_of_type_YA;
/*  48:    */  private yG jdField_b_of_type_YG;
/*  49:    */  private is jdField_a_of_type_Is;
/*  50:    */  private yP jdField_a_of_type_YP;
/*  51:    */  private HashMap jdField_a_of_type_JavaUtilHashMap;
/*  52:    */  private it jdField_a_of_type_It;
/*  53:    */  private yr jdField_a_of_type_Yr;
/*  54: 54 */  private boolean jdField_a_of_type_Boolean = true;
/*  55:    */  private boolean jdField_b_of_type_Boolean;
/*  56: 56 */  private boolean c = true;
/*  57:    */  
/*  58:    */  public iw(ClientState paramClientState) {
/*  59: 59 */    super(paramClientState);
/*  60: 60 */    paramClientState.addObserver(this);
/*  61: 61 */    paramClientState = this;e();paramClientState.jdField_a_of_type_YE = new yE(xe.a().a("shop-panel-gui-"), paramClientState.a());paramClientState.jdField_b_of_type_YG = new yG(209.0F, 362.0F, paramClientState.a());paramClientState.jdField_a_of_type_YG = new yG(209.0F, 362.0F, paramClientState.a());paramClientState.jdField_a_of_type_YA = new yA(paramClientState.a());paramClientState.jdField_a_of_type_Yr = new yr(paramClientState.a());paramClientState.jdField_a_of_type_It = new it(paramClientState.a(), paramClientState.a());paramClientState.jdField_a_of_type_YG.c(paramClientState.jdField_a_of_type_YA);paramClientState.jdField_a_of_type_JavaUtilHashMap = new HashMap();paramClientState.jdField_a_of_type_Is = new is(paramClientState.a(), paramClientState.jdField_a_of_type_JavaUtilHashMap);paramClientState.jdField_b_of_type_YG.c(paramClientState.jdField_a_of_type_Is);paramClientState.a(paramClientState.jdField_a_of_type_YE);paramClientState.jdField_a_of_type_Yr.a(paramClientState.jdField_b_of_type_YG);paramClientState.jdField_a_of_type_Yr.a(paramClientState.jdField_a_of_type_It);paramClientState.jdField_a_of_type_It.a(464.0F, 89.0F, 0.0F);paramClientState.jdField_a_of_type_YG.a(252.0F, 89.0F, 0.0F);paramClientState.jdField_b_of_type_YG.a(252.0F, 89.0F, 0.0F);paramClientState.jdField_a_of_type_YE.a(paramClientState.jdField_a_of_type_Yr);
/*  62:    */  }
/*  63:    */  
/*  65:    */  public final void a(yz paramyz)
/*  66:    */  {
/*  67: 67 */    this.jdField_a_of_type_YE.a(paramyz);
/*  68:    */  }
/*  69:    */  
/*  71:    */  public final void a(yz paramyz, xp paramxp)
/*  72:    */  {
/*  73: 73 */    if (paramyz == this.jdField_a_of_type_YE) {
/*  74: 74 */      paramyz = this; if (this.jdField_a_of_type_YE.a_()) for (paramxp = paramyz.a().getMouseEvents().iterator(); paramxp.hasNext();) { Object localObject; if (((localObject = (xp)paramxp.next()).jdField_a_of_type_Int == 0) && (!((xp)localObject).jdField_a_of_type_Boolean)) { if (paramyz.a().getDragging() != null) { paramyz.a().getDragging(); if (paramyz.a().getDragging() != paramyz) { if (System.currentTimeMillis() - paramyz.a().getDragging().a() > 50L) { System.err.println("NOW DROPPING " + paramyz.a().getDragging());hR localhR = (hR)paramyz.a().getDragging();localObject = paramyz;localhR.a(false); kf localkf; if ((localkf = ((ct)((iw)localObject).a()).a()) != null) ((iw)localObject).a().a(localhR.a(), localhR.a(true), localkf); else ((ct)((iw)localObject).a()).a().b("ERROR: not in shop dist"); } else { System.err.println("NO DROP: time dragged to short"); } paramyz.a().setDragging(null); } } if ((paramyz.a().getDragging() != null) && (paramyz.a().getDragging() == paramyz)) System.err.println("NO DROP: dragging and target are the same"); if (paramyz.a().getDragging() != null) paramyz.a().getDragging(); } } return;
/*  75:    */    }
/*  76:    */    
/*  79: 79 */    if ((Mouse.getEventButtonState()) && (Mouse.getEventButton() == 0))
/*  80:    */    {
/*  82: 82 */      if ((paramyz instanceof yD)) {
/*  83: 83 */        xe.b("0022_menu_ui - enter");
/*  84: 84 */        paramxp = null; if ((a().jdField_a_of_type_YD != null) && (paramyz != a().jdField_a_of_type_YD)) {
/*  85: 85 */          a().jdField_a_of_type_YD.a(false);
/*  86:    */          
/*  87: 87 */          if (a().jdField_a_of_type_YD.a() != ((yD)paramyz).a()) {
/*  88: 88 */            a().jdField_a_of_type_YD.a().e();
/*  89:    */          }
/*  90:    */        }
/*  91:    */        
/*  92: 92 */        ((yD)paramyz).a(true);
/*  93: 93 */        a().jdField_a_of_type_YD = ((yD)paramyz);return;
/*  94:    */      }
/*  95: 95 */      paramxp = null; if (paramyz.a != null) {
/*  96: 96 */        if (paramyz.a.equals("ELEMENTS")) {
/*  97: 97 */          xe.b("0022_menu_ui - enter");
/*  98: 98 */          this.jdField_a_of_type_Boolean = true;
/*  99: 99 */          this.jdField_b_of_type_Boolean = true;return;
/* 100:    */        }
/* 101:    */        
/* 102:102 */        if (!d) { throw new AssertionError("not known command: '" + paramyz.a + "'");
/* 103:    */        }
/* 104:    */      }
/* 105:    */    }
/* 106:    */  }
/* 107:    */  
/* 125:    */  public final void a() {}
/* 126:    */  
/* 143:    */  public final void b(yz paramyz)
/* 144:    */  {
/* 145:145 */    this.jdField_a_of_type_YE.b(paramyz);
/* 146:    */  }
/* 147:    */  
/* 151:    */  public final void b()
/* 152:    */  {
/* 153:153 */    if (this.c)
/* 154:    */    {
/* 165:165 */      localObject1 = this;this.jdField_a_of_type_YA.clear();((iw)localObject1).jdField_a_of_type_YA.a((ys)localObject1); Object localObject2; for (Iterator localIterator = ((ct)((iw)localObject1).a()).a().a.iterator(); localIterator.hasNext(); ((iw)localObject1).jdField_a_of_type_YA.a((yD)localObject2)) { lL locallL = (lL)localIterator.next();System.err.println("[GUI] adding catalog entry: " + locallL);localObject2 = locallL.a; yP localyP1; (localyP1 = new yP(256, 25, d.d(), ((iw)localObject1).a())).b = new ArrayList();localyP1.b.add(localObject2);localyP1.a(new Color(0.5F, 0.5F, 0.5F, 1.0F)); yP localyP2; (localyP2 = new yP(256, 25, d.d(), ((iw)localObject1).a())).b = new ArrayList();localyP2.b.add(localObject2);(localObject2 = new yD(localyP1, localyP2, ((iw)localObject1).a())).a = locallL; }
/* 166:166 */      this.c = false;
/* 167:    */    }
/* 168:168 */    if (this.jdField_b_of_type_Boolean) {
/* 169:169 */      localObject1 = this;this.jdField_a_of_type_YP.a.r = 1.0F;((iw)localObject1).jdField_a_of_type_YP.a.g = 1.0F;((iw)localObject1).jdField_a_of_type_YP.a.b = 1.0F;((iw)localObject1).jdField_a_of_type_YE.b(((iw)localObject1).jdField_a_of_type_Yr);((iw)localObject1).jdField_a_of_type_YE.a(((iw)localObject1).jdField_a_of_type_Yr);((iw)localObject1).jdField_b_of_type_Boolean = false;
/* 170:    */    }
/* 171:    */    
/* 173:173 */    Object localObject1 = null;
/* 174:174 */    if ((this.jdField_a_of_type_Boolean) && (a().jdField_a_of_type_Short >= 0))
/* 175:    */    {
/* 176:176 */      (localObject1 = (iu)this.jdField_a_of_type_JavaUtilHashMap.get(Short.valueOf(a().jdField_a_of_type_Short))).a(469.0F, 265.0F, 0.0F);
/* 177:177 */      this.jdField_a_of_type_Yr.a((yz)localObject1);
/* 178:    */    }
/* 179:    */    
/* 181:181 */    GlUtil.d();
/* 182:182 */    r();
/* 183:183 */    this.jdField_a_of_type_YE.b();
/* 184:184 */    GlUtil.c();
/* 185:    */    
/* 186:186 */    if (localObject1 != null) {
/* 187:187 */      this.jdField_a_of_type_Yr.b((yz)localObject1);
/* 188:    */    }
/* 189:    */  }
/* 190:    */  
/* 193:    */  public final float a()
/* 194:    */  {
/* 195:195 */    return this.jdField_a_of_type_YE.a();
/* 196:    */  }
/* 197:    */  
/* 201:    */  private bz a()
/* 202:    */  {
/* 203:203 */    return ((ct)a()).a().a.a.a;
/* 204:    */  }
/* 205:    */  
/* 206:    */  public final float b()
/* 207:    */  {
/* 208:208 */    return this.jdField_a_of_type_YE.b();
/* 209:    */  }
/* 210:    */  
/* 253:    */  public static void e()
/* 254:    */  {
/* 255:255 */    iv.a.clear();
/* 256:    */    short[] arrayOfShort;
/* 257:257 */    int i = (arrayOfShort = ElementKeyMap.typeList()).length; for (int j = 0; j < i; j++) { short s;
/* 258:    */      ElementInformation localElementInformation;
/* 259:259 */      if ((localElementInformation = ElementKeyMap.getInfo(s = arrayOfShort[j])).isShoppable()) {
/* 260:260 */        iv.a.put(Integer.valueOf(localElementInformation.getBuildIconNum()), Short.valueOf(s));
/* 261:    */      }
/* 262:    */    }
/* 263:    */  }
/* 264:    */  
/* 267:    */  public final boolean a()
/* 268:    */  {
/* 269:269 */    return false;
/* 270:    */  }
/* 271:    */  
/* 294:    */  public final void c()
/* 295:    */  {
/* 296:296 */    this.jdField_a_of_type_YE.c();
/* 297:297 */    this.jdField_b_of_type_YG.c();
/* 298:    */    
/* 299:    */    yr localyr;
/* 300:300 */    (localyr = new yr(a(), 104.0F, 25.0F)).g = true;
/* 301:301 */    localyr.a = "SHOP-ELEMENTS-ANCHOR";
/* 302:302 */    localyr.a = "ELEMENTS";
/* 303:303 */    localyr.a(this);
/* 304:304 */    localyr.a(252.0F, 66.0F, 0.0F);
/* 305:305 */    this.jdField_a_of_type_YE.a(localyr);
/* 306:    */    
/* 314:314 */    this.jdField_a_of_type_YE.a(this);
/* 315:315 */    this.jdField_a_of_type_YE.g = true;
/* 316:    */    
/* 318:318 */    this.jdField_a_of_type_YP = new yP(64, 20, d.b(), a());
/* 319:319 */    this.jdField_a_of_type_YP.b = new ArrayList();
/* 320:320 */    this.jdField_a_of_type_YP.a = "SHOP-ELEMENTS-BUTTON";
/* 321:321 */    this.jdField_a_of_type_YP.b.add("Blocks");
/* 322:322 */    this.jdField_a_of_type_YP.a(10.0F, 2.0F, 0.0F);
/* 323:323 */    this.jdField_a_of_type_YP.a(new Color(1.0F, 1.0F, 1.0F, 1.0F));
/* 324:324 */    this.jdField_a_of_type_YP.c();
/* 325:325 */    localyr.a(this.jdField_a_of_type_YP);
/* 326:    */    
/* 336:336 */    this.g = true;
/* 337:    */  }
/* 338:    */  
/* 401:    */  public void update(Observable paramObservable, Object paramObject)
/* 402:    */  {
/* 403:403 */    if ("CATALOG_UPDATE".equals(paramObject)) {
/* 404:404 */      this.c = true;
/* 405:    */    }
/* 406:    */  }
/* 407:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     iw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */