/*   1:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   2:    */import com.bulletphysics.linearmath.Transform;
/*   3:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.PrintStream;
/*   6:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   7:    */import org.schema.game.common.data.element.ElementInformation;
/*   8:    */import org.schema.game.common.data.element.ElementKeyMap;
/*   9:    */import org.schema.game.common.data.element.factory.FactoryElement;
/*  10:    */import org.schema.game.common.data.element.factory.ManufacturingElement;
/*  11:    */import org.schema.schine.network.NetworkStateContainer;
/*  12:    */import org.schema.schine.network.StateInterface;
/*  13:    */import org.schema.schine.network.objects.Sendable;
/*  14:    */
/*  23:    */public class jy
/*  24:    */  extends EditableSendableSegmentController
/*  25:    */  implements km
/*  26:    */{
/*  27:    */  private boolean a;
/*  28:    */  
/*  29:    */  public jy(StateInterface paramStateInterface)
/*  30:    */  {
/*  31: 31 */    super(paramStateInterface);
/*  32:    */  }
/*  33:    */  
/*  44:    */  public boolean allowedType(short paramShort)
/*  45:    */  {
/*  46: 46 */    if ((!FactoryElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0) {}
/*  47: 47 */    if ((!ManufacturingElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0) {}
/*  48: 48 */    if ((paramShort != 121 ? 1 : 0) != 0) if (super.allowedType(paramShort)) return true; return false;
/*  49:    */  }
/*  50:    */  
/*  62:    */  public boolean isEmptyOnServer()
/*  63:    */  {
/*  64: 64 */    if (!this.a)
/*  65:    */    {
/*  70: 70 */      return false;
/*  71:    */    }
/*  72:    */    
/*  73: 73 */    return super.isEmptyOnServer();
/*  74:    */  }
/*  75:    */  
/*  85:    */  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*  86:    */  {
/*  87: 87 */    super.handleHit(paramClosestRayResultCallback, paramlb, paramFloat);
/*  88: 88 */    if ((isOnServer()) && (getTotalElements() <= 0)) {
/*  89: 89 */      System.err.println("[FLOATINGROCK] DESTROYING " + this + " -> TOTAL ELEMENTS: " + getTotalElements());
/*  90: 90 */      destroy();
/*  91:    */    }
/*  92:    */  }
/*  93:    */  
/*  99:    */  public void handleHitMissile(ln paramln, Transform paramTransform)
/* 100:    */  {
/* 101:101 */    super.handleHitMissile(paramln, paramTransform);
/* 102:102 */    if ((isOnServer()) && (getTotalElements() <= 0)) {
/* 103:103 */      destroy();
/* 104:    */    }
/* 105:    */  }
/* 106:    */  
/* 114:    */  public void initialize()
/* 115:    */  {
/* 116:116 */    super.initialize();
/* 117:117 */    setMass(0.0F);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public final boolean a(String[] paramArrayOfString, q paramq) {
/* 121:121 */    return true;
/* 122:    */  }
/* 123:    */  
/* 127:    */  protected void onCoreDestroyed(lb paramlb) {}
/* 128:    */  
/* 132:    */  public void startCreatorThread()
/* 133:    */  {
/* 134:134 */    if (getCreatorThread() == null)
/* 135:    */    {
/* 136:136 */      setCreatorThread(new kD(this, kF.values()[getCreatorId()]));
/* 137:    */    }
/* 138:    */  }
/* 139:    */  
/* 141:    */  public String toNiceString()
/* 142:    */  {
/* 143:143 */    if (!isOnServer()) {
/* 144:    */      Sendable localSendable;
/* 145:145 */      if ((localSendable = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSectorId())) != null) {
/* 146:146 */        return "Rock " + ((mv)localSendable).a();
/* 147:    */      }
/* 148:    */    }
/* 149:    */    
/* 150:150 */    return "Floating Rock <can be harvested>";
/* 151:    */  }
/* 152:    */  
/* 156:    */  public void updateLocal(xq paramxq)
/* 157:    */  {
/* 158:158 */    super.updateLocal(paramxq);
/* 159:    */    try {
/* 160:160 */      if ((isOnServer()) && (getTotalElements() <= 0) && (System.currentTimeMillis() - getTimeCreated() > 3000L) && (isEmptyOnServer()))
/* 161:    */      {
/* 164:164 */        System.err.println("[SERVER][FloatingRock] Empty rock: deleting!!!!!!!!!!!!!!!!!!! " + this);
/* 165:165 */        setMarkedForDeleteVolatile(true);
/* 166:    */      }
/* 167:    */      return; } catch (IOException localIOException) { 
/* 168:    */      
/* 171:171 */        localIOException.printStackTrace(); return;
/* 172:    */    } catch (InterruptedException localInterruptedException) {
/* 173:169 */      
/* 174:    */      
/* 175:171 */        localInterruptedException;
/* 176:    */    }
/* 177:    */  }
/* 178:    */  
/* 185:    */  public String toString()
/* 186:    */  {
/* 187:181 */    return "Asteroid(" + getId() + ")sec[" + getSectorId() + "]" + (this.a ? "(!)" : "");
/* 188:    */  }
/* 189:    */  
/* 195:    */  public final void a(boolean paramBoolean)
/* 196:    */  {
/* 197:191 */    this.a = paramBoolean;
/* 198:    */  }
/* 199:    */  
/* 205:    */  public final boolean a()
/* 206:    */  {
/* 207:201 */    return this.a;
/* 208:    */  }
/* 209:    */  
/* 215:    */  protected String getSegmentControllerTypeString()
/* 216:    */  {
/* 217:211 */    return "Asteroid";
/* 218:    */  }
/* 219:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */