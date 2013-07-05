/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*     */ import org.schema.game.network.objects.NetworkSegmentController;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/*     */ 
/*     */ public final class jw extends I
/*     */ {
/*     */   public jw(EditableSendableSegmentController paramEditableSendableSegmentController, ct paramct, Object paramObject1, Object paramObject2, le paramle, short paramShort, ah paramah, boolean paramBoolean)
/*     */   {
/* 418 */     super(paramct, paramObject1, paramObject2);
/*     */   }
/*     */ 
/*     */   public final boolean a() {
/* 422 */     return false;
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*     */     short s;
/* 428 */     if ((
/* 428 */       s = EditableSendableSegmentController.access$000(this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController, this.jdField_a_of_type_Le, this.jdField_a_of_type_Short, this.jdField_a_of_type_Ah, this.b)) != 0)
/*     */     {
/* 430 */       mf localmf = this.a.a().getInventory(null);
/*     */       try {
/* 432 */         int i = localmf.b(s, 1);
/* 433 */         xe.b("0022_action - buttons push medium");
/* 434 */         this.a.a().sendInventoryModification(i, null);
/*     */       } catch (NoSlotFreeException localNoSlotFreeException) {
/* 436 */         this.a.a().b("ERROR\nno free slot in inventory\nthe element is lost");
/*     */       }
/*     */ 
/* 441 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.forceClientUpdates();
/* 442 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.set(this.a.a().getId());
/* 443 */       d();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 450 */     this.a.a().a.a.a(400);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jw
 * JD-Core Version:    0.6.2
 */