import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.InternalTickCallback;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import javax.vecmath.Vector3f;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.Sendable;

final class class_6
  extends InternalTickCallback
{
  private class_6(class_52 paramclass_52) {}
  
  public final void internalTick(DynamicsWorld paramDynamicsWorld, float paramFloat)
  {
    paramDynamicsWorld = class_52.a37(this.field_8).a19().getDynamicsWorld().getDispatcher().getNumManifolds();
    for (paramFloat = 0; paramFloat < paramDynamicsWorld; paramFloat++)
    {
      PersistentManifold localPersistentManifold;
      CollisionObject localCollisionObject1 = (CollisionObject)(localPersistentManifold = class_52.a37(this.field_8).a19().getDynamicsWorld().getDispatcher().getManifoldByIndexInternal(paramFloat)).getBody0();
      CollisionObject localCollisionObject2 = (CollisionObject)localPersistentManifold.getBody1();
      int i = localPersistentManifold.getNumContacts();
      if ((localCollisionObject1 != null) && (localCollisionObject2 != null) && (localCollisionObject1.getUserPointer() != null) && (localCollisionObject2.getUserPointer() != null))
      {
        int j = ((Integer)localCollisionObject1.getUserPointer()).intValue();
        int k = ((Integer)localCollisionObject2.getUserPointer()).intValue();
        Sendable localSendable1 = (Sendable)class_52.a37(this.field_8).getLocalAndRemoteObjectContainer().getLocalObjects().get(j);
        Sendable localSendable2 = (Sendable)class_52.a37(this.field_8).getLocalAndRemoteObjectContainer().getLocalObjects().get(k);
        Vector3f localVector3f1 = new Vector3f();
        Vector3f localVector3f2 = new Vector3f();
        if (((localSendable1 instanceof class_1277)) && ((localSendable2 instanceof class_1277)))
        {
          class_1277 localclass_12771 = (class_1277)localSendable1;
          class_1277 localclass_12772 = (class_1277)localSendable2;
          boolean bool1 = localclass_12771.needsManifoldCollision();
          boolean bool2 = localclass_12772.needsManifoldCollision();
          if ((bool1) || (bool2)) {
            for (int m = 0; m < i; m++)
            {
              ManifoldPoint localManifoldPoint;
              if (((localManifoldPoint = localPersistentManifold.getContactPoint(m)).getDistance() < 0.0F) && (localCollisionObject1.getUserPointer() != null) && (localCollisionObject2.getUserPointer() != null))
              {
                localManifoldPoint.getPositionWorldOnA(localVector3f1);
                localManifoldPoint.getPositionWorldOnB(localVector3f2);
                if (bool1) {
                  localclass_12771.onCollision(localManifoldPoint, localSendable2);
                }
                if (bool2) {
                  localclass_12772.onCollision(localManifoldPoint, localSendable1);
                }
              }
            }
          }
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_6
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */