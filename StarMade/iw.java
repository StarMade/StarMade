/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import java.util.TreeMap;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public class iw extends yz
/*     */   implements Observer, ys
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yG jdField_a_of_type_YG;
/*     */   private yA jdField_a_of_type_YA;
/*     */   private yG jdField_b_of_type_YG;
/*     */   private is jdField_a_of_type_Is;
/*     */   private yP jdField_a_of_type_YP;
/*     */   private HashMap jdField_a_of_type_JavaUtilHashMap;
/*     */   private it jdField_a_of_type_It;
/*     */   private yr jdField_a_of_type_Yr;
/*  54 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private boolean jdField_b_of_type_Boolean;
/*  56 */   private boolean c = true;
/*     */ 
/*     */   public iw(ClientState paramClientState) {
/*  59 */     super(paramClientState);
/*  60 */     paramClientState.addObserver(this);
/*  61 */     paramClientState = this; e(); paramClientState.jdField_a_of_type_YE = new yE(xe.a().a("shop-panel-gui-"), paramClientState.a()); paramClientState.jdField_b_of_type_YG = new yG(209.0F, 362.0F, paramClientState.a()); paramClientState.jdField_a_of_type_YG = new yG(209.0F, 362.0F, paramClientState.a()); paramClientState.jdField_a_of_type_YA = new yA(paramClientState.a()); paramClientState.jdField_a_of_type_Yr = new yr(paramClientState.a()); paramClientState.jdField_a_of_type_It = new it(paramClientState.a(), paramClientState.a()); paramClientState.jdField_a_of_type_YG.c(paramClientState.jdField_a_of_type_YA); paramClientState.jdField_a_of_type_JavaUtilHashMap = new HashMap(); paramClientState.jdField_a_of_type_Is = new is(paramClientState.a(), paramClientState.jdField_a_of_type_JavaUtilHashMap); paramClientState.jdField_b_of_type_YG.c(paramClientState.jdField_a_of_type_Is); paramClientState.a(paramClientState.jdField_a_of_type_YE); paramClientState.jdField_a_of_type_Yr.a(paramClientState.jdField_b_of_type_YG); paramClientState.jdField_a_of_type_Yr.a(paramClientState.jdField_a_of_type_It); paramClientState.jdField_a_of_type_It.a(464.0F, 89.0F, 0.0F); paramClientState.jdField_a_of_type_YG.a(252.0F, 89.0F, 0.0F); paramClientState.jdField_b_of_type_YG.a(252.0F, 89.0F, 0.0F); paramClientState.jdField_a_of_type_YE.a(paramClientState.jdField_a_of_type_Yr);
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz)
/*     */   {
/*  67 */     this.jdField_a_of_type_YE.a(paramyz);
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  73 */     if (paramyz == this.jdField_a_of_type_YE) {
/*  74 */       paramyz = this; if (this.jdField_a_of_type_YE.a_()) for (paramxp = paramyz.a().getMouseEvents().iterator(); paramxp.hasNext(); )
/*     */         {
/*  74 */           Object localObject;
/*  74 */           if (((localObject = (xp)paramxp.next()).jdField_a_of_type_Int == 0) && (!((xp)localObject).jdField_a_of_type_Boolean)) { if (paramyz.a().getDragging() != null) { paramyz.a().getDragging(); if (paramyz.a().getDragging() != paramyz) { if (System.currentTimeMillis() - paramyz.a().getDragging().a() > 50L) { System.err.println("NOW DROPPING " + paramyz.a().getDragging()); hR localhR = (hR)paramyz.a().getDragging(); localObject = paramyz; localhR.a(false);
/*     */                   kf localkf;
/*  74 */                   if ((localkf = ((ct)((iw)localObject).a()).a()) != null) ((iw)localObject).a().a(localhR.a(), localhR.a(true), localkf); else ((ct)((iw)localObject).a()).a().b("ERROR: not in shop dist");  } else { System.err.println("NO DROP: time dragged to short"); } paramyz.a().setDragging(null); }  } if ((paramyz.a().getDragging() != null) && (paramyz.a().getDragging() == paramyz)) System.err.println("NO DROP: dragging and target are the same"); if (paramyz.a().getDragging() != null) paramyz.a().getDragging();  } 
/*     */         } return;
/*     */     }
/*     */ 
/*  79 */     if ((Mouse.getEventButtonState()) && (Mouse.getEventButton() == 0))
/*     */     {
/*  82 */       if ((paramyz instanceof yD)) {
/*  83 */         xe.b("0022_menu_ui - enter");
/*  84 */         paramxp = null; if ((a().jdField_a_of_type_YD != null) && (paramyz != a().jdField_a_of_type_YD)) {
/*  85 */           a().jdField_a_of_type_YD.a(false);
/*     */ 
/*  87 */           if (a().jdField_a_of_type_YD.a() != ((yD)paramyz).a()) {
/*  88 */             a().jdField_a_of_type_YD.a().e();
/*     */           }
/*     */         }
/*     */ 
/*  92 */         ((yD)paramyz).a(true);
/*  93 */         a().jdField_a_of_type_YD = ((yD)paramyz); return;
/*     */       }
/*  95 */       paramxp = null; if (paramyz.a != null) {
/*  96 */         if (paramyz.a.equals("ELEMENTS")) {
/*  97 */           xe.b("0022_menu_ui - enter");
/*  98 */           this.jdField_a_of_type_Boolean = true;
/*  99 */           this.jdField_b_of_type_Boolean = true; return;
/*     */         }
/*     */ 
/* 102 */         if (!d) throw new AssertionError("not known command: '" + paramyz.a + "'");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b(yz paramyz)
/*     */   {
/* 145 */     this.jdField_a_of_type_YE.b(paramyz);
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 153 */     if (this.c)
/*     */     {
/* 165 */       localObject1 = this; this.jdField_a_of_type_YA.clear(); ((iw)localObject1).jdField_a_of_type_YA.a((ys)localObject1);
/*     */       Object localObject2;
/* 165 */       for (Iterator localIterator = ((ct)((iw)localObject1).a()).a().a.iterator(); localIterator.hasNext(); ((iw)localObject1).jdField_a_of_type_YA.a((yD)localObject2)) { lL locallL = (lL)localIterator.next(); System.err.println("[GUI] adding catalog entry: " + locallL); localObject2 = locallL.a;
/*     */         yP localyP1;
/* 165 */         (localyP1 = new yP(256, 25, d.d(), ((iw)localObject1).a())).b = new ArrayList(); localyP1.b.add(localObject2); localyP1.a(new Color(0.5F, 0.5F, 0.5F, 1.0F));
/*     */         yP localyP2;
/* 165 */         (localyP2 = new yP(256, 25, d.d(), ((iw)localObject1).a())).b = new ArrayList(); localyP2.b.add(localObject2); (localObject2 = new yD(localyP1, localyP2, ((iw)localObject1).a())).a = locallL; }
/* 166 */       this.c = false;
/*     */     }
/* 168 */     if (this.jdField_b_of_type_Boolean) {
/* 169 */       localObject1 = this; this.jdField_a_of_type_YP.a.r = 1.0F; ((iw)localObject1).jdField_a_of_type_YP.a.g = 1.0F; ((iw)localObject1).jdField_a_of_type_YP.a.b = 1.0F; ((iw)localObject1).jdField_a_of_type_YE.b(((iw)localObject1).jdField_a_of_type_Yr); ((iw)localObject1).jdField_a_of_type_YE.a(((iw)localObject1).jdField_a_of_type_Yr); ((iw)localObject1).jdField_b_of_type_Boolean = false;
/*     */     }
/*     */ 
/* 173 */     Object localObject1 = null;
/* 174 */     if ((this.jdField_a_of_type_Boolean) && (a().jdField_a_of_type_Short >= 0)) {
/* 175 */       (
/* 176 */         localObject1 = (iu)this.jdField_a_of_type_JavaUtilHashMap.get(Short.valueOf(a().jdField_a_of_type_Short)))
/* 176 */         .a(469.0F, 265.0F, 0.0F);
/* 177 */       this.jdField_a_of_type_Yr.a((yz)localObject1);
/*     */     }
/*     */ 
/* 181 */     GlUtil.d();
/* 182 */     r();
/* 183 */     this.jdField_a_of_type_YE.b();
/* 184 */     GlUtil.c();
/*     */ 
/* 186 */     if (localObject1 != null)
/* 187 */       this.jdField_a_of_type_Yr.b((yz)localObject1);
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 195 */     return this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   private bz a()
/*     */   {
/* 203 */     return ((ct)a()).a().a.a.a;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 208 */     return this.jdField_a_of_type_YE.b();
/*     */   }
/*     */ 
/*     */   public static void e()
/*     */   {
/* 255 */     iv.a.clear();
/*     */     short[] arrayOfShort;
/* 257 */     int i = (arrayOfShort = ElementKeyMap.typeList()).length; for (int j = 0; j < i; j++)
/*     */     {
/*     */       short s;
/*     */       ElementInformation localElementInformation;
/* 259 */       if ((
/* 259 */         localElementInformation = ElementKeyMap.getInfo(s = arrayOfShort[j]))
/* 259 */         .isShoppable())
/* 260 */         iv.a.put(Integer.valueOf(localElementInformation.getBuildIconNum()), Short.valueOf(s));
/*     */     }
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 269 */     return false;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 296 */     this.jdField_a_of_type_YE.c();
/* 297 */     this.jdField_b_of_type_YG.c();
/*     */     yr localyr;
/* 299 */     (
/* 300 */       localyr = new yr(a(), 104.0F, 25.0F)).g = 
/* 300 */       true;
/* 301 */     localyr.a = "SHOP-ELEMENTS-ANCHOR";
/* 302 */     localyr.a = "ELEMENTS";
/* 303 */     localyr.a(this);
/* 304 */     localyr.a(252.0F, 66.0F, 0.0F);
/* 305 */     this.jdField_a_of_type_YE.a(localyr);
/*     */ 
/* 314 */     this.jdField_a_of_type_YE.a(this);
/* 315 */     this.jdField_a_of_type_YE.g = true;
/*     */ 
/* 318 */     this.jdField_a_of_type_YP = new yP(64, 20, d.b(), a());
/* 319 */     this.jdField_a_of_type_YP.b = new ArrayList();
/* 320 */     this.jdField_a_of_type_YP.a = "SHOP-ELEMENTS-BUTTON";
/* 321 */     this.jdField_a_of_type_YP.b.add("Blocks");
/* 322 */     this.jdField_a_of_type_YP.a(10.0F, 2.0F, 0.0F);
/* 323 */     this.jdField_a_of_type_YP.a(new Color(1.0F, 1.0F, 1.0F, 1.0F));
/* 324 */     this.jdField_a_of_type_YP.c();
/* 325 */     localyr.a(this.jdField_a_of_type_YP);
/*     */ 
/* 336 */     this.g = true;
/*     */   }
/*     */ 
/*     */   public void update(Observable paramObservable, Object paramObject)
/*     */   {
/* 403 */     if ("CATALOG_UPDATE".equals(paramObject))
/* 404 */       this.c = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     iw
 * JD-Core Version:    0.6.2
 */