/*   1:    */import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*   2:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   3:    */import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*   4:    */
/* 217:    */final class at
/* 218:    */  implements ah
/* 219:    */{
/* 220:    */  at(ar paramar, EditableSendableSegmentController paramEditableSendableSegmentController, IntOpenHashSet paramIntOpenHashSet) {}
/* 221:    */  
/* 222:    */  public final void a(short paramShort)
/* 223:    */  {
/* 224:224 */    if ((paramShort != 0) && (paramShort != 1))
/* 225:    */    {
/* 226:226 */      mf localmf = ((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getState()).a().getInventory(null);
/* 227:    */      try {
/* 228:228 */        paramShort = localmf.b(paramShort, 1);
/* 229:229 */        this.jdField_a_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet.add(paramShort); return;
/* 230:    */      } catch (NoSlotFreeException localNoSlotFreeException) {
/* 231:231 */        this.jdField_a_of_type_Ar.a().a().b("ERROR\nno free slot in inventory\nthe element is lost");
/* 232:    */      }
/* 233:    */    }
/* 234:    */  }
/* 235:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     at
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */