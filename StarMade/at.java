/*     */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*     */ 
/*     */ final class at
/*     */   implements ah
/*     */ {
/*     */   at(ar paramar, EditableSendableSegmentController paramEditableSendableSegmentController, IntOpenHashSet paramIntOpenHashSet)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(short paramShort)
/*     */   {
/* 224 */     if ((paramShort != 0) && (paramShort != 1))
/*     */     {
/* 226 */       mf localmf = ((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getState()).a().getInventory(null);
/*     */       try { paramShort = localmf.b(paramShort, 1);
/* 229 */         this.jdField_a_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet.add(paramShort);
/*     */         return; } catch (NoSlotFreeException localNoSlotFreeException) { this.jdField_a_of_type_Ar.a().a().b("ERROR\nno free slot in inventory\nthe element is lost"); }
/*     */ 
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     at
 * JD-Core Version:    0.6.2
 */