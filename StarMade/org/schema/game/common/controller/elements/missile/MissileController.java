package org.schema.game.common.controller.elements.missile;

import class_1041;
import class_595;
import class_597;
import class_599;
import class_601;
import class_792;
import class_797;
import class_809;
import class_941;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Vector3f;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.schine.network.server.ServerState;

public class MissileController
{
  private class_1041 state;
  private final boolean onServer;
  private static short missileIdCreator;
  private final class_595 missileManager;
  
  public MissileController(class_1041 paramclass_1041)
  {
    this.state = paramclass_1041;
    this.onServer = (paramclass_1041 instanceof ServerState);
    this.missileManager = new class_595(paramclass_1041);
  }
  
  public void addDumbMissile(class_809 paramclass_809, Transform paramTransform, Vector3f paramVector3f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    class_792 localclass_792;
    (localclass_792 = new class_792(this.state)).a10(paramFloat2);
    localclass_792.c2(paramFloat1);
    localclass_792.b2(paramFloat4);
    localclass_792.a11((int)paramFloat3);
    addMissile(localclass_792, paramTransform, paramVector3f, paramclass_809);
  }
  
  public void addFafoMissile(class_809 paramclass_809, Transform paramTransform, Vector3f paramVector3f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, class_797 paramclass_797)
  {
    System.err.println("ADDING FAFO MISSILE!!!!!!!!!!!!!!! Dir: " + paramVector3f + " onServer: " + isOnServer());
    class_601 localclass_601;
    (localclass_601 = new class_601(this.state)).a10(paramFloat2);
    localclass_601.c2(paramFloat1);
    localclass_601.b2(paramFloat4);
    localclass_601.a11((int)paramFloat3);
    paramVector3f.normalize();
    System.err.println("MISSILE TARGET " + paramclass_797);
    localclass_601.a22(paramclass_797);
    addMissile(localclass_601, paramTransform, paramVector3f, paramclass_809);
  }
  
  public void addHeatMissile(class_809 paramclass_809, Transform paramTransform, Vector3f paramVector3f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    System.err.println("ADDING HEAT MISSILE!!!!!!!!!!!!!!!");
    class_599 localclass_599;
    (localclass_599 = new class_599(this.state)).a10(paramFloat2);
    localclass_599.c2(paramFloat1);
    localclass_599.b2(paramFloat4);
    localclass_599.a11((int)paramFloat3);
    addMissile(localclass_599, paramTransform, paramVector3f, paramclass_809);
  }
  
  public void addMissile(class_597 paramclass_597, Transform paramTransform, Vector3f paramVector3f, class_809 paramclass_809)
  {
    assert (isOnServer());
    if (paramclass_809 == null) {
      throw new NullPointerException("OWNER NULL");
    }
    System.err.println("[MISSILE] NEW MISSILE: " + paramTransform.origin + "; " + paramclass_597 + ": Speed: " + paramclass_597.c() + ", Damage: " + paramclass_597.a1() + ", Radius: " + paramclass_597.a() + ", Lifetime: " + paramclass_597.b());
    paramclass_597.a13(paramclass_809);
    paramclass_597.b7(((class_797)paramclass_809).getSectorId());
    paramclass_597.a3().set(paramTransform);
    paramclass_597.b8().set(paramTransform);
    paramclass_597.a12(paramVector3f);
    paramclass_597.a19(missileIdCreator++);
    this.missileManager.a(paramclass_597);
  }
  
  public boolean isOnServer()
  {
    return this.onServer;
  }
  
  public void fromNetwork(NetworkClientChannel paramNetworkClientChannel)
  {
    this.missileManager.a3(paramNetworkClientChannel);
  }
  
  public void updateServer(class_941 paramclass_941)
  {
    this.missileManager.a1(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.missile.MissileController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */