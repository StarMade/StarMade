/*   1:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   2:    */import java.io.PrintStream;
/*   3:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   4:    */import org.schema.game.common.data.element.ElementInformation;
/*   5:    */import org.schema.game.common.data.element.ElementKeyMap;
/*   6:    */import org.schema.game.common.data.element.spacestation.SpaceStationElement;
/*   7:    */import org.schema.game.common.data.physics.CubeRayCastResult;
/*   8:    */import org.schema.game.common.data.world.Segment;
/*   9:    */import org.schema.game.common.data.world.SegmentData;
/*  10:    */import org.schema.game.network.objects.NetworkTeamDeathStar;
/*  11:    */import org.schema.game.server.controller.GameServerController;
/*  12:    */import org.schema.schine.network.StateInterface;
/*  13:    */
/*  26:    */public class kl
/*  27:    */  extends EditableSendableSegmentController
/*  28:    */{
/*  29:    */  public kl(StateInterface paramStateInterface)
/*  30:    */  {
/*  31: 31 */    super(paramStateInterface);
/*  32:    */  }
/*  33:    */  
/*  36:    */  public boolean allowedType(short paramShort)
/*  37:    */  {
/*  38:    */    int i;
/*  39:    */    
/*  41: 41 */    if (((i = (paramShort != 121) && (!SpaceStationElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType())) ? 1 : 0) == 0) && (!isOnServer())) {
/*  42: 42 */      ((x)getState().getController()).b("Cannot place\n" + ElementKeyMap.getInfo(paramShort).getName() + "\non a death star");
/*  43:    */    }
/*  44:    */    
/*  47: 47 */    return (super.allowedType(paramShort)) && (i != 0);
/*  48:    */  }
/*  49:    */  
/*  57:    */  public void fromTagStructure(Ah paramAh)
/*  58:    */  {
/*  59: 59 */    if ((!a) && (!paramAh.a().equals("DeathStar"))) { throw new AssertionError();
/*  60:    */    }
/*  61:    */    
/*  62: 62 */    ((Integer)(paramAh = (Ah[])paramAh.a())[0].a()).intValue();
/*  63:    */    
/*  72: 72 */    super.fromTagStructure(paramAh[1]);
/*  73:    */  }
/*  74:    */  
/*  75:    */  protected short getCoreType()
/*  76:    */  {
/*  77: 77 */    return 65;
/*  78:    */  }
/*  79:    */  
/*  91:    */  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*  92:    */  {
/*  93: 93 */    super.handleHit(paramClosestRayResultCallback, paramlb, paramFloat);
/*  94:    */    
/*  97: 97 */    if (!isOnServer())
/*  98:    */    {
/*  99: 99 */      if (((paramlb = (CubeRayCastResult)paramClosestRayResultCallback).hasHit()) && (paramlb.getSegment() != null) && (!paramlb.getSegment().g())) {
/* 100:100 */        paramlb.getSegment().a();
/* 101:101 */        paramFloat = SegmentData.getInfoIndex(paramlb.cubePos);
/* 102:    */        
/* 103:103 */        if (paramlb.getSegment().a().getType(paramFloat) == 65) {
/* 104:104 */          new StringBuilder("WARNING!\nYour base's CORE is unter attack!\nHP left: ").append(paramlb.getSegment().a().getHitpoints(paramFloat)).toString();
/* 105:    */        }
/* 106:    */      }
/* 107:    */      
/* 113:113 */      ((ct)getState()).a().a().a(paramClosestRayResultCallback);
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 135:    */  public void initialize()
/* 136:    */  {
/* 137:137 */    super.initialize();
/* 138:    */    
/* 139:139 */    setMass(0.0F);
/* 140:    */  }
/* 141:    */  
/* 149:    */  public final boolean a(String[] paramArrayOfString, q paramq)
/* 150:    */  {
/* 151:151 */    if (kd.a.equals(paramq)) {
/* 152:152 */      paramArrayOfString[0] = "Can't salvage core!";
/* 153:153 */      return false;
/* 154:    */    }
/* 155:155 */    return false;
/* 156:    */  }
/* 157:    */  
/* 165:    */  public void newNetworkObject()
/* 166:    */  {
/* 167:167 */    setNetworkObject(new NetworkTeamDeathStar(getState(), this));
/* 168:    */  }
/* 169:    */  
/* 173:    */  protected void onCoreDestroyed(lb paramlb)
/* 174:    */  {
/* 175:175 */    System.err.println("DEATHSTAR HAS BEEN DESTROYED BY !!!! " + paramlb);
/* 176:176 */    if (isOnServer()) {
/* 177:177 */      int i = getFactionId();
/* 178:178 */      paramlb = (paramlb instanceof mF) ? ((mF)paramlb).getFactionId() : 0;
/* 179:179 */      ((vg)getState()).a().a(paramlb, i);
/* 180:    */    }
/* 181:    */  }
/* 182:    */  
/* 185:    */  public void startCreatorThread()
/* 186:    */  {
/* 187:187 */    if (getCreatorThread() == null) {
/* 188:188 */      setCreatorThread(new kH(this));
/* 189:    */    }
/* 190:    */  }
/* 191:    */  
/* 192:    */  public String toNiceString()
/* 193:    */  {
/* 194:194 */    return "Death Star";
/* 195:    */  }
/* 196:    */  
/* 201:    */  public Ah toTagStructure()
/* 202:    */  {
/* 203:203 */    Ah localAh = new Ah(Aj.d, "team", Integer.valueOf(getFactionId()));
/* 204:    */    
/* 205:205 */    return new Ah(Aj.n, "DeathStar", new Ah[] { localAh, super.toTagStructure(), new Ah(Aj.a, null, null) });
/* 206:    */  }
/* 207:    */  
/* 211:    */  protected String getSegmentControllerTypeString()
/* 212:    */  {
/* 213:213 */    return "DeathStar";
/* 214:    */  }
/* 215:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */