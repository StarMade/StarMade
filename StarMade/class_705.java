import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.IOException;
import java.io.PrintStream;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.factory.FactoryElement;
import org.schema.game.common.data.element.factory.ManufacturingElement;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;

public class class_705
  extends EditableSendableSegmentController
  implements class_784
{
  private boolean field_136;
  
  public class_705(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public boolean allowedType(short paramShort)
  {
    if ((!FactoryElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0) {}
    if ((!ManufacturingElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0) {}
    if ((paramShort != 121 ? 1 : 0) != 0) {
      if (super.allowedType(paramShort)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isEmptyOnServer()
  {
    if (!this.field_136) {
      return false;
    }
    return super.isEmptyOnServer();
  }
  
  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, class_809 paramclass_809, float paramFloat)
  {
    super.handleHit(paramClosestRayResultCallback, paramclass_809, paramFloat);
    if ((isOnServer()) && (getTotalElements() <= 0))
    {
      System.err.println("[FLOATINGROCK] DESTROYING " + this + " -> TOTAL ELEMENTS: " + getTotalElements());
      destroy();
    }
  }
  
  public void handleHitMissile(class_597 paramclass_597, Transform paramTransform)
  {
    super.handleHitMissile(paramclass_597, paramTransform);
    if ((isOnServer()) && (getTotalElements() <= 0)) {
      destroy();
    }
  }
  
  public void initialize()
  {
    super.initialize();
    setMass(0.0F);
  }
  
  public final boolean a86(String[] paramArrayOfString, class_48 paramclass_48)
  {
    return true;
  }
  
  protected void onCoreDestroyed(class_809 paramclass_809) {}
  
  public void startCreatorThread()
  {
    if (getCreatorThread() == null) {
      setCreatorThread(new class_898(this, class_902.values()[getCreatorId()]));
    }
  }
  
  public String toNiceString()
  {
    Sendable localSendable;
    if ((!isOnServer()) && ((localSendable = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSectorId())) != null)) {
      return "Rock " + ((class_665)localSendable).a34();
    }
    return "Floating Rock <can be harvested>";
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    super.updateLocal(paramclass_941);
    try
    {
      if ((isOnServer()) && (getTotalElements() <= 0) && (System.currentTimeMillis() - getTimeCreated() > 3000L) && (isEmptyOnServer()))
      {
        System.err.println("[SERVER][FloatingRock] Empty rock: deleting!!!!!!!!!!!!!!!!!!! " + this);
        setMarkedForDeleteVolatile(true);
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
  }
  
  public String toString()
  {
    return "Asteroid(" + getId() + ")sec[" + getSectorId() + "]" + (this.field_136 ? "(!)" : "");
  }
  
  public final void a72(boolean paramBoolean)
  {
    this.field_136 = paramBoolean;
  }
  
  public final boolean a7()
  {
    return this.field_136;
  }
  
  protected String getSegmentControllerTypeString()
  {
    return "Asteroid";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_705
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */