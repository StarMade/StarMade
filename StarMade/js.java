/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public final class js extends Thread
/*     */ {
/*     */   private StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   private final boolean jdField_a_of_type_Boolean;
/*  18 */   private final ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*     */ 
/*  20 */   private final ArrayList jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*  21 */   private final ArrayList c = new ArrayList();
/*  22 */   private final ArrayList d = new ArrayList();
/*  23 */   private final Set jdField_a_of_type_JavaUtilSet = new HashSet();
/*  24 */   private boolean jdField_b_of_type_Boolean = true;
/*     */ 
/*     */   public js(StateInterface paramStateInterface)
/*     */   {
/*  28 */     this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  29 */     this.jdField_a_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
/*  30 */     setName((this.jdField_a_of_type_Boolean ? "[SERVER]" : "[CLIENT]") + "_CREATOR_THREAD");
/*     */   }
/*     */ 
/*     */   public final void a(SegmentController paramSegmentController) {
/*  34 */     if (!this.jdField_a_of_type_Boolean)
/*  35 */       synchronized (this.c) {
/*  36 */         this.c.add(paramSegmentController);
/*  37 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   public final void b(SegmentController paramSegmentController) {
/*  42 */     if (!this.jdField_a_of_type_Boolean)
/*  43 */       synchronized (this.d) {
/*  44 */         this.d.add(paramSegmentController);
/*  45 */         return;
/*     */       }
/*     */   }
/*     */ 
/*  49 */   private void a() { System.currentTimeMillis();
/*     */ 
/*  51 */     Object localObject4 = this; if (this.jdField_a_of_type_JavaUtilSet.isEmpty()) synchronized (((js)localObject4).jdField_a_of_type_JavaUtilSet) { while (((js)localObject4).jdField_a_of_type_JavaUtilSet.isEmpty()) ((js)localObject4).jdField_a_of_type_JavaUtilSet.wait(20000L);  } 
/*  51 */     if (((js)localObject4).jdField_b_of_type_Boolean) { ((js)localObject4).jdField_a_of_type_JavaUtilArrayList.clear(); synchronized (((js)localObject4).jdField_a_of_type_JavaUtilSet) { ((js)localObject4).jdField_a_of_type_JavaUtilArrayList.addAll(((js)localObject4).jdField_a_of_type_JavaUtilSet); ((js)localObject4).jdField_b_of_type_Boolean = false;
/*     */       }
/*     */     }
/*  54 */     ??? = this.jdField_a_of_type_JavaUtilArrayList.size();
/*     */     try
/*     */     {
/*  57 */       for (Object localObject3 = 0; localObject3 < ???; localObject3++) {
/*  58 */         localObject4 = (SegmentController)this.jdField_a_of_type_JavaUtilArrayList.get(localObject3);
/*     */ 
/*  60 */         int i = 0;
/*  61 */         if (((ct)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().containsKey(((SegmentController)localObject4).getId())) {
/*  62 */           if (((SegmentController)localObject4).getCreatorThread() != null)
/*     */           {
/*  64 */             i = (localObject4 = ((SegmentController)localObject4).getCreatorThread()).jdField_a_of_type_Boolean ? ((kG)localObject4).jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().c() : 0;
/*     */           }
/*  66 */           if (i != 0)
/*  67 */             Thread.sleep(2L);
/*     */         }
/*     */       }
/*     */       return;
/*     */     } catch (Exception localException) {
/*  72 */       System.err.println("[CreatorThreadController] Exception handled " + localException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(boolean paramBoolean, SegmentController paramSegmentController)
/*     */   {
/* 104 */     if (paramBoolean) {
/* 105 */       if (!this.jdField_a_of_type_JavaUtilSet.contains(paramSegmentController)) {
/* 106 */         long l1 = System.currentTimeMillis();
/* 107 */         synchronized (this.jdField_a_of_type_JavaUtilSet) {
/* 108 */           this.jdField_a_of_type_JavaUtilSet.add(paramSegmentController);
/* 109 */           ct.g = this.jdField_a_of_type_JavaUtilSet.size();
/* 110 */           this.jdField_b_of_type_Boolean = true;
/* 111 */           this.jdField_a_of_type_JavaUtilSet.notify();
/*     */         }
/*     */         long l2;
/* 114 */         if ((
/* 114 */           l2 = System.currentTimeMillis() - l1) > 
/* 114 */           5L) {
/* 115 */           System.err.println("[CREATORTHREAD][CLIENT] WARNING: notify for " + paramSegmentController + " on queue " + this.jdField_a_of_type_JavaUtilSet.size() + " took " + l2 + " ms");
/*     */         }
/*     */       }
/*     */     }
/* 119 */     else if (this.jdField_a_of_type_JavaUtilSet.contains(paramSegmentController))
/* 120 */       synchronized (this.jdField_a_of_type_JavaUtilSet)
/*     */       {
/* 122 */         this.jdField_a_of_type_JavaUtilSet.remove(paramSegmentController);
/* 123 */         ct.g = this.jdField_a_of_type_JavaUtilSet.size();
/* 124 */         this.jdField_b_of_type_Boolean = true;
/*     */ 
/* 126 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/* 137 */       if (!this.jdField_a_of_type_Boolean)
/*     */       {
/* 139 */         while (((ct)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a() == null) {
/* 140 */           Thread.sleep(70L);
/*     */         }
/*     */ 
/* 143 */         localObject = this; localObject = new jt((js)localObject); (localObject = new Thread((Runnable)localObject, "[CLIENT]RequestNewSegments")).setPriority(3); ((Thread)localObject).start();
/*     */         while (true)
/*     */         {
/* 147 */           a();
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */       Object localObject;
/* 150 */       (
/* 151 */         localObject = 
/* 155 */         localException).printStackTrace();
/* 152 */       System.err.println("Creator Thread DIED!!!");
/* 153 */       d.a((Exception)localObject);
/* 154 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     js
 * JD-Core Version:    0.6.2
 */