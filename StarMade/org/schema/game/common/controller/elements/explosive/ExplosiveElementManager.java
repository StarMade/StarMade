/*   1:    */package org.schema.game.common.controller.elements.explosive;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.Collection;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.List;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import kd;
/*  11:    */import lA;
/*  12:    */import lE;
/*  13:    */import le;
/*  14:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*  15:    */import org.schema.game.common.controller.SegmentController;
/*  16:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  17:    */import org.schema.game.common.controller.elements.UpdatableCollectionManager;
/*  18:    */import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
/*  19:    */import org.schema.game.common.data.element.ElementCollection;
/*  20:    */import org.schema.game.network.objects.NetworkPlayer;
/*  21:    */import org.schema.game.network.objects.NetworkSegmentController;
/*  22:    */import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*  23:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  24:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  25:    */import org.schema.schine.network.objects.remote.RemoteVector3f;
/*  26:    */import q;
/*  27:    */import xq;
/*  28:    */
/*  50:    */public class ExplosiveElementManager
/*  51:    */  extends UsableControllableSingleElementManager
/*  52:    */  implements UpdatableCollectionManager
/*  53:    */{
/*  54:    */  private ExplosiveCollectionManager explosiveManager;
/*  55: 55 */  private final ArrayList explosions = new ArrayList();
/*  56:    */  
/*  57: 57 */  public ExplosiveElementManager(SegmentController paramSegmentController) { super(new ExplosiveCollectionManager(paramSegmentController), paramSegmentController);
/*  58: 58 */    this.explosiveManager = ((ExplosiveCollectionManager)getCollection());
/*  59:    */  }
/*  60:    */  
/*  61: 61 */  public void addExplosion(q paramq, Vector3f paramVector3f, EditableSendableSegmentController paramEditableSendableSegmentController, le paramle) { paramle = null;
/*  62: 62 */    for (Iterator localIterator = getCollection().getCollection().iterator(); localIterator.hasNext();)
/*  63: 63 */      if ((localObject1 = (ExplosiveUnit)localIterator.next()).getNeighboringCollection().contains(Long.valueOf(ElementCollection.getIndex(paramq)))) {
/*  64: 64 */        paramle = (le)localObject1;
/*  65: 65 */        break;
/*  66:    */      }
/*  67:    */    Object localObject1;
/*  68: 68 */    if (paramle != null)
/*  69: 69 */      synchronized (this.explosions)
/*  70:    */      {
/*  71: 71 */        localObject1 = new ExplosiveElementManager.Explosion(this, new q(paramq), new Vector3f(paramVector3f), (EditableSendableSegmentController)getSegmentController(), paramEditableSendableSegmentController, (byte)0);
/*  72: 72 */        if (!this.explosions.contains(localObject1)) {
/*  73: 73 */          System.err.println("[EXPLOSION] INITIATING EXPLOSION " + paramVector3f);
/*  74: 74 */          this.explosions.add(localObject1);
/*  75:    */        }
/*  76: 76 */        paramq = new ExplosiveElementManager.Explosion(this, new q(paramq), new Vector3f(paramVector3f), (EditableSendableSegmentController)getSegmentController(), (EditableSendableSegmentController)getSegmentController(), (byte)1);
/*  77: 77 */        if (!this.explosions.contains(paramq)) {
/*  78: 78 */          this.explosions.add(paramq);
/*  79:    */        }
/*  80: 80 */        return;
/*  81:    */      }
/*  82:    */  }
/*  83:    */  
/*  84:    */  public float getActualExplosive() {
/*  85:    */    float f;
/*  86: 86 */    if ((f = this.explosiveManager.getTotalExplosive()) == 0.0F) {
/*  87: 87 */      return 0.0F;
/*  88:    */    }
/*  89: 89 */    return f;
/*  90:    */  }
/*  91:    */  
/*  97:    */  public ElementCollectionManager getNewCollectionManager(le paramle)
/*  98:    */  {
/*  99: 99 */    return new ExplosiveCollectionManager(getSegmentController());
/* 100:    */  }
/* 101:    */  
/* 106:    */  public void handle(lA paramlA)
/* 107:    */  {
/* 108:108 */    if (!((Boolean)paramlA.jdField_a_of_type_LE.a().activeControllerMask.get(1).get()).booleanValue()) {
/* 109:109 */      return;
/* 110:    */    }
/* 111:    */    
/* 112:112 */    if (!kd.a.equals(paramlA.jdField_a_of_type_JavaLangObject))
/* 113:    */    {
/* 114:114 */      return;
/* 115:    */    }
/* 116:    */    
/* 117:117 */    getActualExplosive();
/* 118:    */  }
/* 119:    */  
/* 122:    */  public void onControllerChange() {}
/* 123:    */  
/* 125:    */  public void update(xq arg1)
/* 126:    */  {
/* 127:127 */    synchronized (this.explosions) {
/* 128:128 */      while (!this.explosions.isEmpty()) {
/* 129:129 */        ExplosiveElementManager.Explosion localExplosion = (ExplosiveElementManager.Explosion)this.explosions.remove(0);
/* 130:130 */        System.err.println("Executing explosion for " + ExplosiveElementManager.Explosion.access$000(localExplosion));
/* 131:    */        Transform localTransform;
/* 132:132 */        (localTransform = new Transform()).setIdentity();
/* 133:133 */        localTransform.origin.set(ExplosiveElementManager.Explosion.access$100(localExplosion));
/* 134:    */        
/* 135:135 */        ExplosiveElementManager.Explosion.access$000(localExplosion).handleExplosion(localTransform, 16.0F, 200.0F, ExplosiveElementManager.Explosion.access$200(localExplosion), ExplosiveElementManager.Explosion.access$300(localExplosion));
/* 136:136 */        ExplosiveElementManager.Explosion.access$000(localExplosion).getNetworkObject().explosions.add(new RemoteVector3f(ExplosiveElementManager.Explosion.access$000(localExplosion).getNetworkObject(), ExplosiveElementManager.Explosion.access$100(localExplosion)));
/* 137:    */      }
/* 138:    */      
/* 139:139 */      return;
/* 140:    */    }
/* 141:    */  }
/* 142:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */