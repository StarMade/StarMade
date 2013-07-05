/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*     */ 
/*     */ public class bm extends U
/*     */   implements ys
/*     */ {
/*     */   private le a;
/*     */   private boolean d;
/*     */ 
/*     */   public bm(ct paramct)
/*     */   {
/*  31 */     super(paramct);
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  36 */     paramxp = null; if ((this.c) && (!this.jdField_a_of_type_Boolean))
/*     */     {
/*     */       Object localObject;
/*  37 */       for (paramxp = a().getMouseEvents().iterator(); paramxp.hasNext(); 
/*  47 */         ((bm)localObject).notifyObservers(((bm)localObject).a))
/*  38 */         if (((
/*  38 */           localObject = (xp)paramxp.next()).jdField_a_of_type_Int == 0) && 
/*  38 */           (!((xp)localObject).jdField_a_of_type_Boolean) && 
/*  39 */           ((paramyz instanceof yD)))
/*     */         {
/*  47 */           yA localyA;
/*  42 */           int i = (
/*  42 */             localyA = (yA)(
/*  41 */             localObject = (yD)paramyz)
/*  41 */             .a())
/*  42 */             .indexOf(localObject);
/*  43 */           localyA.e();
/*  44 */           ((yD)localObject).a(true);
/*  45 */           System.err.println("Controller manager call back: index: " + i);
/*  46 */           this.a = ((le)((yD)localObject).a().b());
/*  47 */           localObject = this; setChanged(); if (((bm)localObject).a != null) ((bm)localObject).a.a(-2);
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  56 */     this.d = true;
/*     */   }
/*     */ 
/*     */   public final le a()
/*     */   {
/*  75 */     return this.a;
/*     */   }
/*     */ 
/*     */   public void handleKeyEvent()
/*     */   {
/*  80 */     super.handleKeyEvent();
/*     */ 
/*  82 */     if ((Keyboard.getEventKeyState()) && 
/*  83 */       (Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11)) {
/*  84 */       int i = Keyboard.getEventKey() - 2; bm localbm = this; if (this.a != null) { if ((!e) && (localbm.a().a() == null)) throw new AssertionError(); if (!localbm.a().a().a(localbm.a().a())) localbm.a().a().a().add(new cz(localbm.a().a(), localbm.a().a().getUniqueIdentifier())); try { cz localcz = localbm.a().a().a(localbm.a().a()); int j = -1; q localq = localbm.a.a(new q()); if (localcz.a(localq)) { j = localcz.a(localq); localbm.setChanged(); } System.err.println("PRESSED " + i + ": REMOVE: " + j); if (j != i) { System.err.println("ASSINGING: " + i + " to " + localbm.a); localcz.a(i, localq, true); localbm.setChanged(); } localbm.notifyObservers(localbm.a); return; } catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) { localShipConfigurationNotFoundException.printStackTrace(); } }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/*  90 */     super.a(paramxp);
/*     */   }
/*     */ 
/*     */   public final boolean h()
/*     */   {
/*  97 */     return this.d;
/*     */   }
/*     */ 
/*     */   public final boolean a() {
/* 101 */     return !a().b().isEmpty();
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 138 */     wV.jdField_a_of_type_Boolean = !paramBoolean;
/*     */ 
/* 140 */     if (paramBoolean)
/*     */     {
/* 142 */       xe.b("0022_menu_ui - swoosh scroll large");
/*     */     }
/* 144 */     else xe.b("0022_menu_ui - swoosh scroll small");
/*     */ 
/* 147 */     a().a().a.a.a.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/*     */ 
/* 150 */     a().a().a.a.a.a().a().jdField_a_of_type_Au.e(paramBoolean);
/*     */ 
/* 154 */     setChanged();
/* 155 */     notifyObservers();
/*     */ 
/* 157 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 175 */     this.d = false;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 189 */     wV.jdField_a_of_type_Boolean = false;
/*     */ 
/* 191 */     a().a().a.a.a.e(true);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bm
 * JD-Core Version:    0.6.2
 */