import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import java.io.PrintStream;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.spacestation.SpaceStationElement;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.game.network.objects.NetworkTeamDeathStar;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.StateInterface;

public class class_782
  extends EditableSendableSegmentController
{
  public class_782(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public boolean allowedType(short paramShort)
  {
    int i;
    if (((i = (paramShort != 121) && (!SpaceStationElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType())) ? 1 : 0) == 0) && (!isOnServer())) {
      ((class_52)getState().getController()).b1("Cannot place\n" + ElementKeyMap.getInfo(paramShort).getName() + "\non a death star");
    }
    return (super.allowedType(paramShort)) && (i != 0);
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if ((!field_136) && (!paramclass_69.a2().equals("DeathStar"))) {
      throw new AssertionError();
    }
    ((Integer)(paramclass_69 = (class_69[])paramclass_69.a4())[0].a4()).intValue();
    super.fromTagStructure(paramclass_69[1]);
  }
  
  protected short getCoreType()
  {
    return 65;
  }
  
  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, class_809 paramclass_809, float paramFloat)
  {
    super.handleHit(paramClosestRayResultCallback, paramclass_809, paramFloat);
    if (!isOnServer())
    {
      if (((paramclass_809 = (CubeRayCastResult)paramClosestRayResultCallback).hasHit()) && (paramclass_809.getSegment() != null) && (!paramclass_809.getSegment().g()))
      {
        paramclass_809.getSegment().a16();
        paramFloat = SegmentData.getInfoIndex(paramclass_809.cubePos);
        if (paramclass_809.getSegment().a16().getType(paramFloat) == 65) {
          new StringBuilder("WARNING!\nYour base's CORE is unter attack!\nHP left: ").append(paramclass_809.getSegment().a16().getHitpoints(paramFloat)).toString();
        }
      }
      ((class_371)getState()).a27().a91().a22(paramClosestRayResultCallback);
    }
  }
  
  public void initialize()
  {
    super.initialize();
    setMass(0.0F);
  }
  
  public final boolean a86(String[] paramArrayOfString, class_48 paramclass_48)
  {
    if (class_747.field_136.equals(paramclass_48))
    {
      paramArrayOfString[0] = "Can't salvage core!";
      return false;
    }
    return false;
  }
  
  public void newNetworkObject()
  {
    setNetworkObject(new NetworkTeamDeathStar(getState(), this));
  }
  
  protected void onCoreDestroyed(class_809 paramclass_809)
  {
    System.err.println("DEATHSTAR HAS BEEN DESTROYED BY !!!! " + paramclass_809);
    if (isOnServer())
    {
      int i = getFactionId();
      paramclass_809 = (paramclass_809 instanceof class_797) ? ((class_797)paramclass_809).getFactionId() : 0;
      ((class_1041)getState()).a59().a10(paramclass_809, i);
    }
  }
  
  public void startCreatorThread()
  {
    if (getCreatorThread() == null) {
      setCreatorThread(new class_906(this));
    }
  }
  
  public String toNiceString()
  {
    return "Death Star";
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_69 = new class_69(class_79.field_551, "team", Integer.valueOf(getFactionId()));
    return new class_69(class_79.field_561, "DeathStar", new class_69[] { localclass_69, super.toTagStructure(), new class_69(class_79.field_548, null, null) });
  }
  
  protected String getSegmentControllerTypeString()
  {
    return "DeathStar";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_782
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */