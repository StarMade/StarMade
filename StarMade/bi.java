/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.ControlElementMapper;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ 
/*     */ public class bi extends U
/*     */ {
/*  25 */   private Object jdField_a_of_type_JavaLangObject = new Object();
/*     */   private bj jdField_a_of_type_Bj;
/*     */   private le jdField_a_of_type_Le;
/*     */ 
/*     */   public bi(ct paramct)
/*     */   {
/*  33 */     super(paramct);
/*  34 */     paramct = this; this.jdField_a_of_type_Bj = new bj(paramct.a()); paramct.a.add(paramct.jdField_a_of_type_Bj);
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  42 */     synchronized (this.jdField_a_of_type_JavaLangObject) {
/*  43 */       q localq = null; if (this.jdField_a_of_type_Le != null)
/*     */       {
/*  45 */         SegmentController localSegmentController = this.jdField_a_of_type_Le.a().a();
/*  46 */         localq = this.jdField_a_of_type_Le.a(new q());
/*  47 */         System.err.println("[CLIENT] EXIT SHIP FROM EXTRYPOINT " + localq + " OF " + localSegmentController);
/*  48 */         a().a().a((cw)localSegmentController, a().a(), localq, new q(), true);
/*     */       }
/*     */ 
/*  57 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final le a()
/*     */   {
/*  69 */     return this.jdField_a_of_type_Le;
/*     */   }
/*     */ 
/*     */   public final bj a()
/*     */   {
/*  77 */     return this.jdField_a_of_type_Bj;
/*     */   }
/*     */ 
/*     */   public void handleKeyEvent()
/*     */   {
/*  85 */     super.handleKeyEvent();
/*     */ 
/*  87 */     if ((Keyboard.getEventKeyState()) && 
/*  88 */       (Keyboard.getEventKey() == cv.v.a()))
/*  89 */       b();
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 117 */     if (paramBoolean)
/*     */     {
/* 120 */       Object localObject = (kd)this.jdField_a_of_type_Le.a().a();
/*     */ 
/* 122 */       a().a((kd)localObject);
/*     */ 
/* 126 */       if ((!d) && (this.jdField_a_of_type_Le == null)) throw new AssertionError();
/*     */ 
/* 128 */       jD localjD = ((kd)localObject).getControlElementMap().getControlledElements((short)32767, this.jdField_a_of_type_Le.a(new q()));
/*     */ 
/* 130 */       if (!a().a().a((SegmentController)localObject)) {
/* 131 */         cz localcz = new cz(a().a(), ((kd)localObject).getUniqueIdentifier());
/* 132 */         int i = 0;
/*     */         try {
/* 134 */           localq = new q();
/* 135 */           for (localObject = ((kd)localObject).getControlElementMap().getControllingMap().keySet().iterator(); ((Iterator)localObject).hasNext(); ) { long l = ((Long)((Iterator)localObject).next()).longValue();
/* 136 */             if (localjD.a.contains(ElementCollection.getPosFromIndex(l, localq))) {
/* 137 */               localcz.a(i, new q(localq), true);
/*     */             }
/* 139 */             if (i > 10) break;
/* 140 */             i++; }
/*     */         }
/*     */         catch (ConcurrentModificationException localConcurrentModificationException)
/*     */         {
/* 144 */           q localq = null;
/*     */ 
/* 146 */           localConcurrentModificationException.printStackTrace();
/*     */         }
/*     */ 
/* 147 */         a().a().a().add(localcz);
/*     */       }
/*     */ 
/* 151 */       this.jdField_a_of_type_Bj.c(true);
/*     */ 
/* 153 */       this.jdField_a_of_type_Le.a().a();
/* 154 */       this.jdField_a_of_type_Le.a(new q());
/*     */     }
/*     */     else
/*     */     {
/* 159 */       if (a().a() == a().a()) {
/* 160 */         a().a(null);
/*     */       }
/*     */ 
/* 166 */       a().a(null);
/*     */     }
/*     */ 
/* 169 */     if ((!d) && (paramBoolean) && (a().a() == null)) throw new AssertionError(": Entered: " + this.jdField_a_of_type_Le.a().a() + " -> " + this.jdField_a_of_type_Le.a(new q()));
/* 170 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(le paramle)
/*     */   {
/* 177 */     synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 178 */       this.jdField_a_of_type_Le = paramle;
/* 179 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xq arg1)
/*     */   {
/* 185 */     super.a(???);
/* 186 */     if (!a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_a_of_type_Le.a().a().getId())) {
/* 187 */       b();
/*     */     }
/* 189 */     synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 190 */       if (this.jdField_a_of_type_Le != null) {
/* 191 */         this.jdField_a_of_type_Le.a();
/* 192 */         if ((!this.jdField_a_of_type_Le.a().c) && (this.jdField_a_of_type_Le.a().a().getSegmentBuffer().a(this.jdField_a_of_type_Le.a().a)) && 
/* 193 */           (((mw)this.jdField_a_of_type_Le.a()).a() > 0L) && (this.jdField_a_of_type_Le.a() == 0)) {
/* 194 */           Object localObject1 = this.jdField_a_of_type_Le.a(new q());
/*     */           try
/*     */           {
/* 197 */             if ((
/* 197 */               localObject1 = this.jdField_a_of_type_Le.a().a().getSegmentBuffer().a((q)localObject1, true))
/* 197 */               .a() == 0) {
/* 198 */               System.err.println("POINT BECAME AIR -> exiting ship LC: " + ((mw)this.jdField_a_of_type_Le.a()).a());
/* 199 */               b();
/*     */             } else {
/* 201 */               a((le)localObject1);
/*     */             }
/*     */           }
/*     */           catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {
/*     */           }
/*     */         }
/* 207 */         if (!a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_a_of_type_Le.a().a().getId()))
/* 208 */           b();
/*     */       }
/*     */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bi
 * JD-Core Version:    0.6.2
 */