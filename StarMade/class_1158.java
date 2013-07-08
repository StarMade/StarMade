import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Random;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.data.element.BlockLevel;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;

public final class class_1158
  extends class_1156
{
  public final void a()
  {
    float f1 = ((Float)class_1057.field_1346.a3()).floatValue();
    float f2 = ((Float)class_1057.field_1347.a3()).floatValue();
    if ((f1 <= 0.0F) || (f2 <= 0.0F)) {
      return;
    }
    System.err.println("[SERVER] EXECUTING REGION HOOK: " + this);
    Object localObject;
    (localObject = this.jdField_field_1383_of_type_OrgSchemaGameCommonDataWorldSegment.a15()).getState();
    if ((localObject = ((class_798)localObject).a().getInventory(((class_1059)this.jdField_field_1383_of_type_Class_1168).field_241)) != null) {
      try
      {
        int i = Math.min((int)(Universe.getRandom().nextInt(((class_639)localObject).d1()) * f1), ((class_639)localObject).d1());
        IntOpenHashSet localIntOpenHashSet = new IntOpenHashSet();
        for (int j = 0; j < i; j++)
        {
          ElementKeyMap.keySet.iterator();
          int k = Universe.getRandom().nextInt(ElementKeyMap.typeList().length);
          ElementInformation localElementInformation;
          if ((localElementInformation = ElementKeyMap.getInfo(k = ElementKeyMap.typeList()[k])).isShoppable())
          {
            if (localElementInformation.isLeveled())
            {
              m = 1;
              for (int n = 0; n < localElementInformation.getLevel().getLevel(); n++) {
                if (Math.random() < 0.0500000007450581D)
                {
                  m = 0;
                  break;
                }
              }
              if (m == 0) {
                k = localElementInformation.getLevel().getIdBase();
              }
            }
            int m = (int)((Universe.getRandom().nextInt(1000) + 100) * f2);
            System.err.println("putting into CHEST: " + ElementKeyMap.getInfo(k).getName() + " #" + m);
            localIntOpenHashSet.add(((class_639)localObject).c4(k, m));
          }
        }
        ((class_639)localObject).a56(localIntOpenHashSet);
        return;
      }
      catch (NoSlotFreeException localNoSlotFreeException)
      {
        return;
      }
    }
    System.err.println("[REGIONHOOK] Exception: Inventory not found at: " + ((class_1059)this.jdField_field_1383_of_type_Class_1168).field_241);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1158
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */