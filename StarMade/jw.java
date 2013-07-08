/*   1:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   2:    */import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*   3:    */import org.schema.game.network.objects.NetworkSegmentController;
/*   4:    */import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/*   5:    */
/* 413:    */public final class jw
/* 414:    */  extends I
/* 415:    */{
/* 416:    */  public jw(EditableSendableSegmentController paramEditableSendableSegmentController, ct paramct, Object paramObject1, Object paramObject2, le paramle, short paramShort, ah paramah, boolean paramBoolean)
/* 417:    */  {
/* 418:418 */    super(paramct, paramObject1, paramObject2);
/* 419:    */  }
/* 420:    */  
/* 421:    */  public final boolean a() {
/* 422:422 */    return false;
/* 423:    */  }
/* 424:    */  
/* 425:    */  public final void b()
/* 426:    */  {
/* 427:    */    short s;
/* 428:428 */    if ((s = EditableSendableSegmentController.access$000(this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController, this.jdField_a_of_type_Le, this.jdField_a_of_type_Short, this.jdField_a_of_type_Ah, this.b)) != 0)
/* 429:    */    {
/* 430:430 */      mf localmf = this.a.a().getInventory(null);
/* 431:    */      try {
/* 432:432 */        int i = localmf.b(s, 1);
/* 433:433 */        xe.b("0022_action - buttons push medium");
/* 434:434 */        this.a.a().sendInventoryModification(i, null);
/* 435:    */      } catch (NoSlotFreeException localNoSlotFreeException) {
/* 436:436 */        this.a.a().b("ERROR\nno free slot in inventory\nthe element is lost");
/* 437:    */      }
/* 438:    */      
/* 441:441 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.forceClientUpdates();
/* 442:442 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.set(this.a.a().getId());
/* 443:443 */      d();
/* 444:    */    }
/* 445:    */  }
/* 446:    */  
/* 448:    */  public final void a()
/* 449:    */  {
/* 450:450 */    this.a.a().a.a.a(400);
/* 451:    */  }
/* 452:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */