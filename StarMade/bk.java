/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*     */ 
/*     */ public class bk extends U
/*     */   implements ys
/*     */ {
/*     */   private le a;
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  33 */     paramxp = null; if ((this.c) && (!this.jdField_a_of_type_Boolean))
/*  34 */       for (paramxp = a().getMouseEvents().iterator(); paramxp.hasNext(); )
/*     */       {
/*     */         Object localObject;
/*  35 */         if (((
/*  35 */           localObject = (xp)paramxp.next()).jdField_a_of_type_Int == 0) && 
/*  35 */           (!((xp)localObject).jdField_a_of_type_Boolean) && 
/*  36 */           ((paramyz instanceof yD)))
/*     */         {
/*     */           yA localyA;
/*  39 */           int i = (
/*  39 */             localyA = (yA)(
/*  38 */             localObject = (yD)paramyz)
/*  38 */             .a())
/*  39 */             .indexOf(localObject);
/*  40 */           localyA.e();
/*  41 */           ((yD)localObject).a(true);
/*  42 */           System.err.println("Controller manager call back: index: " + i);
/*  43 */           this.a = ((le)((yD)localObject).a().b());
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public void handleKeyEvent()
/*     */   {
/*  58 */     super.handleKeyEvent();
/*     */ 
/*  60 */     if ((Keyboard.getEventKeyState()) && 
/*  61 */       (Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11)) {
/*  62 */       int i = Keyboard.getEventKey() - 2; bk localbk = this; if (this.a != null) { if ((!d) && (localbk.a().a() == null)) throw new AssertionError(); if (!localbk.a().a().a(localbk.a().a())) localbk.a().a().a().add(new cz(localbk.a().a(), localbk.a().a().getUniqueIdentifier())); try { cz localcz = localbk.a().a().a(localbk.a().a()); int j = -1; q localq = localbk.a.a(new q()); if (localcz.a(localq)) { j = localcz.a(localq); localbk.setChanged(); } if (j != i) { System.err.println("ASSINGING: " + i + " to " + localbk.a); localcz.a(i, localq, true); localbk.setChanged(); } localbk.notifyObservers(localbk.a); return; } catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) { localShipConfigurationNotFoundException.printStackTrace(); } }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/*  68 */     return !a().b().isEmpty();
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 104 */     wV.jdField_a_of_type_Boolean = !paramBoolean;
/* 105 */     super.b(paramBoolean);
/*     */ 
/* 107 */     a().a().a.a.a.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/*     */ 
/* 111 */     a().a().a.a.a.a().a().jdField_a_of_type_Au.e(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 118 */     wV.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bk
 * JD-Core Version:    0.6.2
 */