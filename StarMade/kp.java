/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.server.controller.GameServerController;
/*     */ import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import org.schema.schine.network.objects.remote.RemoteString;
/*     */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*     */ 
/*     */ public abstract class kp
/*     */   implements Ag, wm
/*     */ {
/*     */   private final ko[] jdField_a_of_type_ArrayOfKo;
/*     */   final SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   private StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   private wt jdField_a_of_type_Wt;
/*     */   wo jdField_a_of_type_Wo;
/*     */   private le jdField_a_of_type_Le;
/*     */   private long jdField_a_of_type_Long;
/*     */ 
/*     */   public final boolean a()
/*     */   {
/*  55 */     return this.jdField_a_of_type_Wo.a();
/*     */   }
/*     */ 
/*     */   public final wo a()
/*     */   {
/*  78 */     return this.jdField_a_of_type_Wo;
/*     */   }
/*     */   protected abstract wo b();
/*     */ 
/*  82 */   public kp(StateInterface paramStateInterface, SegmentController paramSegmentController) { this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  83 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/*     */ 
/*  85 */     this.jdField_a_of_type_Wo = b();
/*     */ 
/*  87 */     this.jdField_a_of_type_ArrayOfKo = new ko[kq.values().length];
/*     */ 
/*  89 */     this.jdField_a_of_type_ArrayOfKo[kq.a.ordinal()] = new ko(kq.a, "Any", new xB(new String[] { "Any", "Selected Target" }), this);
/*     */ 
/*  91 */     this.jdField_a_of_type_ArrayOfKo[kq.b.ordinal()] = new ko(kq.b, "Ship", new xB(new String[] { "Turret", "Ship" }), this);
/*     */ 
/*  95 */     this.jdField_a_of_type_ArrayOfKo[kq.c.ordinal()] = new ko(kq.c, Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }), this); }
/*     */ 
/*     */   public final void a() {
/*  98 */     for (int i = 0; i < this.jdField_a_of_type_ArrayOfKo.length; i++)
/*  99 */       b(this.jdField_a_of_type_ArrayOfKo[i]);
/*     */   }
/*     */ 
/*     */   public final void a(ko paramko, boolean paramBoolean)
/*     */   {
/* 116 */     if (paramBoolean)
/* 117 */       c(paramko);
/*     */   }
/*     */ 
/*     */   public final ko a(kq paramkq)
/*     */   {
/* 123 */     return this.jdField_a_of_type_ArrayOfKo[paramkq.ordinal()];
/*     */   }
/*     */ 
/*     */   public final le a()
/*     */   {
/* 130 */     return this.jdField_a_of_type_Le;
/*     */   }
/*     */ 
/*     */   public final ko[] a()
/*     */   {
/* 137 */     return this.jdField_a_of_type_ArrayOfKo;
/*     */   }
/*     */ 
/*     */   protected void a(ko paramko)
/*     */   {
/* 169 */     if ((paramko.a() == kq.c) && (!paramko.a())) {
/* 170 */       System.err.println("[AI] SENTINEL SET TO FALSE ON " + this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface);
/* 171 */       this.jdField_a_of_type_Wo = b();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void a(lb paramlb)
/*     */   {
/* 179 */     if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/* 180 */       this.jdField_a_of_type_ArrayOfKo[kq.c.ordinal()].a(Boolean.valueOf(false), this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer());
/* 181 */       a();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b(lb paramlb)
/*     */   {
/* 188 */     if ((this.jdField_a_of_type_Wo.a()) && ((this.jdField_a_of_type_Wo instanceof kv)))
/* 189 */       ((kv)this.jdField_a_of_type_Wo).a(paramlb);
/*     */   }
/*     */ 
/*     */   public void a(SegmentController paramSegmentController)
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void b(ko paramko)
/*     */   {
/* 208 */     if (paramko.a() == kq.c) {
/* 209 */       if (paramko.a())
/*     */       {
/* 211 */         b();
/*     */       }
/* 213 */       else ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().a(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*     */ 
/* 216 */       if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Wo == null)) throw new AssertionError();
/* 217 */       if (this.jdField_a_of_type_Wo.a != null)
/* 218 */         this.jdField_a_of_type_Wo.a.b(!paramko.a());
/*     */     }
/*     */   }
/*     */ 
/*     */   protected abstract void b();
/*     */ 
/*     */   private void c(ko paramko)
/*     */   {
/* 227 */     if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject() == null)
/*     */       return;
/*     */     RemoteStringArray localRemoteStringArray;
/* 230 */     (
/* 231 */       localRemoteStringArray = new RemoteStringArray(2, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject()))
/* 231 */       .set(0, paramko.b());
/* 232 */     localRemoteStringArray.set(1, paramko.a().toString());
/*     */ 
/* 234 */     ((kr)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject()).getAiSettingsModification().add(localRemoteStringArray);
/*     */   }
/*     */ 
/*     */   public final void a(le paramle)
/*     */   {
/* 246 */     this.jdField_a_of_type_Le = paramle;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 264 */     ko[] arrayOfko = null; if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/*     */     {
/* 266 */       for (ko localko : this.jdField_a_of_type_ArrayOfKo)
/* 267 */         c(localko);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd)
/*     */   {
/*     */     Ad[] arrayOfAd;
/*     */     int j;
/* 275 */     if (paramAd.a().equals("AIConfig0")) {
/* 276 */       paramAd = (Ad[])paramAd.a();
/* 277 */       for (i = 0; (i < paramAd.length) && (paramAd[i].a() != Af.a); i++) {
/* 278 */         arrayOfAd = (Ad[])paramAd[i].a();
/* 279 */         for (j = 0; j < this.jdField_a_of_type_ArrayOfKo.length; j++)
/*     */         {
/* 281 */           if ((this.jdField_a_of_type_ArrayOfKo[j] != null) && (((String)arrayOfAd[0].a()).equals(this.jdField_a_of_type_ArrayOfKo[j].a().name()))) {
/*     */             try
/*     */             {
/* 284 */               this.jdField_a_of_type_ArrayOfKo[j].a((String)arrayOfAd[1].a(), true);
/* 285 */               b(this.jdField_a_of_type_ArrayOfKo[j]);
/*     */             }
/*     */             catch (StateParameterNotFoundException localStateParameterNotFoundException1) {
/* 288 */               localStateParameterNotFoundException1.printStackTrace();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 292 */       return;
/* 293 */     }paramAd = (Ad[])paramAd.a();
/* 294 */     for (int i = 0; (i < paramAd.length) && (paramAd[i].a() != Af.a); i++) {
/* 295 */       arrayOfAd = (Ad[])paramAd[i].a();
/* 296 */       for (j = 0; j < this.jdField_a_of_type_ArrayOfKo.length; j++)
/* 297 */         if ((this.jdField_a_of_type_ArrayOfKo[j] != null) && (((String)arrayOfAd[0].a()).equals(this.jdField_a_of_type_ArrayOfKo[j].a().name())))
/*     */           try
/*     */           {
/* 300 */             this.jdField_a_of_type_ArrayOfKo[j].a((String)arrayOfAd[1].a(), true);
/* 301 */             b(this.jdField_a_of_type_ArrayOfKo[j]);
/*     */           }
/*     */           catch (StateParameterNotFoundException localStateParameterNotFoundException2) {
/* 304 */             localStateParameterNotFoundException2.printStackTrace();
/*     */           }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure()
/*     */   {
/* 314 */     new ArrayList();
/* 315 */     Ad[] arrayOfAd = new Ad[this.jdField_a_of_type_ArrayOfKo.length + 1];
/* 316 */     for (int i = 0; i < this.jdField_a_of_type_ArrayOfKo.length; i++)
/*     */     {
/* 318 */       ko localko = this.jdField_a_of_type_ArrayOfKo[i];
/* 319 */       if ((!jdField_a_of_type_Boolean) && (localko == null)) throw new AssertionError(i + " / " + Arrays.toString(this.jdField_a_of_type_ArrayOfKo));
/* 320 */       arrayOfAd[i] = localko.toTagStructure();
/*     */     }
/*     */ 
/* 324 */     arrayOfAd[this.jdField_a_of_type_ArrayOfKo.length] = new Ad(Af.a, null, null);
/* 325 */     return new Ad(Af.n, "AIConfig0", arrayOfAd);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 331 */     if (this.jdField_a_of_type_Wo.a()) {
/* 332 */       if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/*     */         try {
/* 334 */           if ((!this.jdField_a_of_type_Wo.a()) && (!(this.jdField_a_of_type_Wt instanceof sN))) {
/* 335 */             this.jdField_a_of_type_Wo.a().a(new tx());
/* 337 */           }this.jdField_a_of_type_Wo.a.a(paramxq);
/*     */           return; } catch (Exception localException1) { (
/* 339 */             paramxq = 
/* 347 */             localException1).printStackTrace();
/* 340 */           System.err.println("Exception: Error occured updating AI " + paramxq.getMessage() + ": Current Program: " + this.jdField_a_of_type_Wo.a + "; state: " + this.jdField_a_of_type_Wo.a() + "; in " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*     */ 
/* 342 */           if (System.currentTimeMillis() - this.jdField_a_of_type_Long > 1000L) {
/* 343 */             ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().broadcastMessage("AI Error occured on Server!\nPlease send in server error report.\n" + paramxq.getClass().getSimpleName(), 3);
/* 344 */             this.jdField_a_of_type_Long = System.currentTimeMillis();
/*     */           }
/*     */ 
/* 347 */           return; }
/*     */       try
/*     */       {
/* 350 */         this.jdField_a_of_type_Wo.a(paramxq); } catch (Exception localException2) {
/* 351 */         (
/* 352 */           paramxq = 
/* 354 */           localException2).printStackTrace();
/* 353 */         throw new ErrorDialogException(paramxq.getMessage());
/*     */       }
/* 355 */       if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()) try { a(kq.c).a();
/*     */ 
/* 359 */           ((ct)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().b("WARNING\nThis vessel was AI controlled\n... switched off AI\nre-enable AI with " + ElementKeyMap.getInfo((short)121).getName());
/*     */           return;
/*     */         }
/*     */         catch (StateParameterNotFoundException localStateParameterNotFoundException) {
/* 363 */           (
/* 364 */             paramxq = 
/* 366 */             localStateParameterNotFoundException).printStackTrace();
/* 365 */           throw new ErrorDialogException(paramxq.toString());
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 376 */     ObjectArrayList localObjectArrayList = ((kr)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject()).getAiSettingsModification().getReceiveBuffer();
/* 377 */     for (int i = 0; i < localObjectArrayList.size(); i++)
/*     */     {
/* 379 */       RemoteStringArray localRemoteStringArray = (RemoteStringArray)localObjectArrayList.get(i);
/*     */       ko[] arrayOfko;
/* 380 */       int j = (arrayOfko = this.jdField_a_of_type_ArrayOfKo).length; for (int k = 0; k < j; k++)
/*     */       {
/*     */         ko localko;
/*     */         boolean bool;
/* 382 */         if (((
/* 382 */           bool = (
/* 381 */           localko = arrayOfko[k])
/* 381 */           .b().equals(localRemoteStringArray.get(0).get()))) && 
/* 382 */           (!localko.a().toString().equals(localRemoteStringArray.get(1).get()))) {
/*     */           try
/*     */           {
/* 385 */             localko.a((String)localRemoteStringArray.get(1).get(), this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer());
/* 386 */             if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/* 387 */               b(localko);
/*     */             else
/* 389 */               a(localko);
/*     */           }
/*     */           catch (StateParameterNotFoundException localStateParameterNotFoundException)
/*     */           {
/* 393 */             localStateParameterNotFoundException.printStackTrace();
/*     */           }
/*     */         }
/* 394 */         else if ((!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (bool))
/*     */         {
/* 398 */           a(localko);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(NetworkObject paramNetworkObject)
/*     */   {
/* 406 */     paramNetworkObject = (kr)paramNetworkObject;
/*     */ 
/* 408 */     if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/* 409 */       if (vo.l.a())
/*     */       {
/* 412 */         String str = "";
/* 413 */         if (this.jdField_a_of_type_Wo.a()) {
/* 414 */           str = "\nTar:" + ((sL)this.jdField_a_of_type_Wo.a).a();
/*     */ 
/* 416 */           this.jdField_a_of_type_Wt = this.jdField_a_of_type_Wo.a();
/* 417 */           ((sL)this.jdField_a_of_type_Wo.a).a();
/*     */         }
/*     */ 
/* 420 */         vR localvR = ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a();
/* 421 */         paramNetworkObject.getDebugState().set("(" + this.jdField_a_of_type_Wo.toString() + "[" + (this.jdField_a_of_type_Wo.a() ? "ON" : "OFF") + "])" + str + ";\n" + localvR.a(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController));
/*     */ 
/* 434 */         return;
/* 435 */       }if (((String)paramNetworkObject.getDebugState().get()).length() > 0)
/* 436 */         paramNetworkObject.getDebugState().set("");
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kp
 * JD-Core Version:    0.6.2
 */