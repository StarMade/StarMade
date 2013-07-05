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
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.ControlElementMapper;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ 
/*     */ public class ax extends U
/*     */   implements al
/*     */ {
/*  25 */   private Object jdField_a_of_type_JavaLangObject = new Object();
/*     */   private le jdField_a_of_type_Le;
/*     */   private ay jdField_a_of_type_Ay;
/*     */   private au jdField_a_of_type_Au;
/*     */ 
/*     */   public ax(ct paramct)
/*     */   {
/*  36 */     super(paramct);
/*  37 */     paramct = this; this.jdField_a_of_type_Ay = new ay(paramct.a()); paramct.jdField_a_of_type_Au = new au(paramct.a(), paramct); paramct.a.add(paramct.jdField_a_of_type_Ay); paramct.a.add(paramct.jdField_a_of_type_Au);
/*     */   }
/*     */ 
/*     */   private void b()
/*     */   {
/*  42 */     synchronized (this.jdField_a_of_type_JavaLangObject) {
/*  43 */       q localq = null; if (this.jdField_a_of_type_Le != null)
/*     */       {
/*  45 */         SegmentController localSegmentController = this.jdField_a_of_type_Le.a().a();
/*  46 */         localq = this.jdField_a_of_type_Le.a(new q());
/*  47 */         System.err.println("EXIT SHIP FROM EXTRYPOINT " + localq);
/*  48 */         a().a().a((cw)localSegmentController, a().a(), localq, new q(), true);
/*     */       }
/*     */ 
/*  57 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final q a()
/*     */   {
/*  67 */     return this.jdField_a_of_type_Le.a(new q());
/*     */   }
/*     */ 
/*     */   public final le a()
/*     */   {
/*  75 */     return this.jdField_a_of_type_Le;
/*     */   }
/*     */ 
/*     */   public final au a()
/*     */   {
/*  85 */     return this.jdField_a_of_type_Au;
/*     */   }
/*     */ 
/*     */   public final EditableSendableSegmentController a()
/*     */   {
/*  90 */     return (EditableSendableSegmentController)this.jdField_a_of_type_Le.a().a();
/*     */   }
/*     */ 
/*     */   public final ay a()
/*     */   {
/* 103 */     return this.jdField_a_of_type_Ay;
/*     */   }
/*     */ 
/*     */   public void handleKeyEvent()
/*     */   {
/* 108 */     super.handleKeyEvent();
/*     */ 
/* 110 */     if ((Keyboard.getEventKeyState()) && 
/* 111 */       (Keyboard.getEventKey() == cv.v.a()))
/* 112 */       b();
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 148 */     if (paramBoolean)
/*     */     {
/* 150 */       if (this.jdField_a_of_type_Le.a() == null) {
/* 151 */         System.err.println("Exception: entered has been null");
/* 152 */         if ((!d) && (this.jdField_a_of_type_Le == null)) throw new AssertionError();
/*     */         return;
/*     */       }
/*     */       Object localObject;
/* 163 */       jD localjD = (
/* 163 */         localObject = this.jdField_a_of_type_Le.a().a())
/* 163 */         .getControlElementMap().getControlledElements((short)32767, this.jdField_a_of_type_Le.a(new q()));
/*     */ 
/* 165 */       if (!a().a().a((SegmentController)localObject)) {
/* 166 */         cz localcz = new cz(a().a(), ((SegmentController)localObject).getUniqueIdentifier());
/* 167 */         int i = 0;
/*     */         try {
/* 169 */           localq = new q();
/* 170 */           for (localObject = ((SegmentController)localObject).getControlElementMap().getControllingMap().keySet().iterator(); ((Iterator)localObject).hasNext(); ) { long l = ((Long)((Iterator)localObject).next()).longValue();
/* 171 */             if (localjD.a.contains(ElementCollection.getPosFromIndex(l, localq))) {
/* 172 */               localcz.a(i, new q(localq), true);
/*     */             }
/* 174 */             if (i > 10) break;
/* 175 */             i++; }
/*     */         }
/*     */         catch (ConcurrentModificationException localConcurrentModificationException)
/*     */         {
/* 179 */           q localq = null;
/*     */ 
/* 181 */           localConcurrentModificationException.printStackTrace();
/*     */         }
/*     */ 
/* 182 */         a().a().a().add(localcz);
/*     */       }
/*     */ 
/* 185 */       if (this.jdField_a_of_type_Le.a() == 123)
/* 186 */         this.jdField_a_of_type_Au.c(true);
/*     */       else {
/* 188 */         this.jdField_a_of_type_Ay.c(true);
/*     */       }
/*     */ 
/* 191 */       this.jdField_a_of_type_Le.a().a();
/* 192 */       this.jdField_a_of_type_Le.a(new q());
/*     */     }
/*     */     else
/*     */     {
/* 197 */       if ((this.jdField_a_of_type_Le != null) && (this.jdField_a_of_type_Le.a().a() == a().a())) {
/* 198 */         a().a(null);
/*     */       }
/* 200 */       this.jdField_a_of_type_Ay.c(false);
/* 201 */       this.jdField_a_of_type_Au.c(false);
/*     */     }
/* 203 */     if ((!d) && (paramBoolean) && (this.jdField_a_of_type_Le == null)) throw new AssertionError(": Entered: " + this.jdField_a_of_type_Le.a().a() + " -> " + this.jdField_a_of_type_Le.a(new q()));
/* 204 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(le paramle)
/*     */   {
/* 213 */     synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 214 */       this.jdField_a_of_type_Le = paramle;
/* 215 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xq arg1)
/*     */   {
/* 225 */     super.a(???);
/* 226 */     if (!a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_a_of_type_Le.a().a().getId())) {
/* 227 */       b();
/*     */     }
/* 229 */     synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 230 */       if (this.jdField_a_of_type_Le != null) {
/* 231 */         this.jdField_a_of_type_Le.a();
/* 232 */         if (this.jdField_a_of_type_Le.a() == 0) {
/* 233 */           Object localObject1 = this.jdField_a_of_type_Le.a(new q());
/*     */           try
/*     */           {
/* 236 */             if ((
/* 236 */               localObject1 = this.jdField_a_of_type_Le.a().a().getSegmentBuffer().a((q)localObject1, true))
/* 236 */               .a() == 0)
/* 237 */               b();
/*     */             else
/* 239 */               this.jdField_a_of_type_Le = ((le)localObject1);
/*     */           }
/*     */           catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {
/*     */           }
/*     */         }
/* 244 */         if (!a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_a_of_type_Le.a().a().getId()))
/* 245 */           b();
/*     */       }
/*     */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ax
 * JD-Core Version:    0.6.2
 */