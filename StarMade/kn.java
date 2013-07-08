/*   1:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   2:    */import com.bulletphysics.dynamics.RigidBody;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   5:    */import org.schema.game.network.objects.NetworkVehicle;
/*   6:    */import org.schema.schine.network.StateInterface;
/*   7:    */import org.schema.schine.network.objects.NetworkObject;
/*   8:    */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*   9:    */
/*  25:    */public class kn
/*  26:    */  extends EditableSendableSegmentController
/*  27:    */  implements wp
/*  28:    */{
/*  29:    */  public kn(StateInterface paramStateInterface)
/*  30:    */  {
/*  31: 31 */    super(paramStateInterface);
/*  32:    */  }
/*  33:    */  
/*  37:    */  public void fromTagStructure(Ah paramAh)
/*  38:    */  {
/*  39: 39 */    if ((!a) && (!paramAh.a().equals(getClass().getSimpleName()))) throw new AssertionError();
/*  40: 40 */    paramAh = (Ah[])paramAh.a();
/*  41:    */    
/*  43: 43 */    super.fromTagStructure(paramAh[0]);
/*  44:    */  }
/*  45:    */  
/*  59:    */  protected short getCoreType()
/*  60:    */  {
/*  61: 61 */    return 65;
/*  62:    */  }
/*  63:    */  
/*  73:    */  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*  74:    */  {
/*  75: 75 */    super.handleHit(paramClosestRayResultCallback, paramlb, paramFloat);
/*  76:    */  }
/*  77:    */  
/*  84:    */  public void initialize()
/*  85:    */  {
/*  86: 86 */    super.initialize();
/*  87:    */    
/*  88: 88 */    setMass(0.01F);
/*  89:    */  }
/*  90:    */  
/*  93:    */  public final boolean a(String[] paramArrayOfString, q paramq)
/*  94:    */  {
/*  95: 95 */    return true;
/*  96:    */  }
/*  97:    */  
/* 105:    */  public void newNetworkObject()
/* 106:    */  {
/* 107:107 */    setNetworkObject(new NetworkVehicle(getState(), this));
/* 108:    */  }
/* 109:    */  
/* 114:    */  protected void onCoreDestroyed(lb paramlb) {}
/* 115:    */  
/* 119:    */  public void onPhysicsAdd()
/* 120:    */  {
/* 121:121 */    super.onPhysicsAdd();
/* 122:    */    
/* 123:123 */    ((RigidBody)getPhysicsDataContainer().getObject())
/* 124:    */    
/* 125:125 */      .setGravity(new Vector3f(0.0F, -1.89F, 0.0F));
/* 126:    */  }
/* 127:    */  
/* 130:    */  public void startCreatorThread()
/* 131:    */  {
/* 132:132 */    if (getCreatorThread() == null) {
/* 133:133 */      setCreatorThread(new kI(this));
/* 134:    */    }
/* 135:    */  }
/* 136:    */  
/* 137:    */  public String toNiceString()
/* 138:    */  {
/* 139:139 */    return "Vehicle";
/* 140:    */  }
/* 141:    */  
/* 150:    */  public Ah toTagStructure()
/* 151:    */  {
/* 152:152 */    return new Ah(Aj.n, getClass().getSimpleName(), new Ah[] { super.toTagStructure(), new Ah(Aj.a, null, null) });
/* 153:    */  }
/* 154:    */  
/* 162:    */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/* 163:    */  {
/* 164:164 */    super.updateFromNetworkObject(paramNetworkObject);
/* 165:    */  }
/* 166:    */  
/* 173:    */  public void updateLocal(xq paramxq)
/* 174:    */  {
/* 175:175 */    super.updateLocal(paramxq);
/* 176:    */  }
/* 177:    */  
/* 181:    */  public void updateToFullNetworkObject()
/* 182:    */  {
/* 183:183 */    super.updateToFullNetworkObject();
/* 184:    */  }
/* 185:    */  
/* 187:    */  protected String getSegmentControllerTypeString()
/* 188:    */  {
/* 189:189 */    return "Vehicle";
/* 190:    */  }
/* 191:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */