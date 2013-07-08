package org.schema.game.common.controller.elements.explosive;

import class_48;
import class_747;
import class_748;
import class_755;
import class_796;
import class_941;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.UpdatableCollectionManager;
import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.schine.network.objects.remote.RemoteBooleanArray;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteVector3f;

public class ExplosiveElementManager
  extends UsableControllableSingleElementManager
  implements UpdatableCollectionManager
{
  private ExplosiveCollectionManager explosiveManager = (ExplosiveCollectionManager)getCollection();
  private final ArrayList explosions = new ArrayList();
  
  public ExplosiveElementManager(SegmentController paramSegmentController)
  {
    super(new ExplosiveCollectionManager(paramSegmentController), paramSegmentController);
  }
  
  public void addExplosion(class_48 paramclass_48, Vector3f paramVector3f, EditableSendableSegmentController paramEditableSendableSegmentController, class_796 paramclass_796)
  {
    paramclass_796 = null;
    Iterator localIterator = getCollection().getCollection().iterator();
    Object localObject1;
    while (localIterator.hasNext()) {
      if ((localObject1 = (ExplosiveUnit)localIterator.next()).getNeighboringCollection().contains(Long.valueOf(ElementCollection.getIndex(paramclass_48))))
      {
        paramclass_796 = (class_796)localObject1;
        break;
      }
    }
    if (paramclass_796 != null) {
      synchronized (this.explosions)
      {
        localObject1 = new ExplosiveElementManager.Explosion(this, new class_48(paramclass_48), new Vector3f(paramVector3f), (EditableSendableSegmentController)getSegmentController(), paramEditableSendableSegmentController, (byte)0);
        if (!this.explosions.contains(localObject1))
        {
          System.err.println("[EXPLOSION] INITIATING EXPLOSION " + paramVector3f);
          this.explosions.add(localObject1);
        }
        paramclass_48 = new ExplosiveElementManager.Explosion(this, new class_48(paramclass_48), new Vector3f(paramVector3f), (EditableSendableSegmentController)getSegmentController(), (EditableSendableSegmentController)getSegmentController(), (byte)1);
        if (!this.explosions.contains(paramclass_48)) {
          this.explosions.add(paramclass_48);
        }
        return;
      }
    }
  }
  
  public float getActualExplosive()
  {
    float f;
    if ((f = this.explosiveManager.getTotalExplosive()) == 0.0F) {
      return 0.0F;
    }
    return f;
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new ExplosiveCollectionManager(getSegmentController());
  }
  
  public void handle(class_755 paramclass_755)
  {
    if (!((Boolean)paramclass_755.jdField_field_1015_of_type_Class_748.a127().activeControllerMask.get(1).get()).booleanValue()) {
      return;
    }
    if (!class_747.field_136.equals(paramclass_755.jdField_field_1015_of_type_JavaLangObject)) {
      return;
    }
    getActualExplosive();
  }
  
  public void onControllerChange() {}
  
  public void update(class_941 arg1)
  {
    synchronized (this.explosions)
    {
      while (!this.explosions.isEmpty())
      {
        ExplosiveElementManager.Explosion localExplosion = (ExplosiveElementManager.Explosion)this.explosions.remove(0);
        System.err.println("Executing explosion for " + ExplosiveElementManager.Explosion.access$000(localExplosion));
        Transform localTransform;
        (localTransform = new Transform()).setIdentity();
        localTransform.origin.set(ExplosiveElementManager.Explosion.access$100(localExplosion));
        ExplosiveElementManager.Explosion.access$000(localExplosion).handleExplosion(localTransform, 16.0F, 200.0F, ExplosiveElementManager.Explosion.access$200(localExplosion), ExplosiveElementManager.Explosion.access$300(localExplosion));
        ExplosiveElementManager.Explosion.access$000(localExplosion).getNetworkObject().explosions.add(new RemoteVector3f(ExplosiveElementManager.Explosion.access$000(localExplosion).getNetworkObject(), ExplosiveElementManager.Explosion.access$100(localExplosion)));
      }
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */