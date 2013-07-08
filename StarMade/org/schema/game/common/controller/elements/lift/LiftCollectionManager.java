/*   1:    */package org.schema.game.common.controller.elements.lift;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.Collection;
/*   7:    */import java.util.List;
/*   8:    */import org.schema.game.common.controller.SegmentController;
/*   9:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  10:    */import org.schema.game.common.controller.elements.NTReceiveInterface;
/*  11:    */import org.schema.game.common.data.element.ElementCollection;
/*  12:    */import org.schema.game.network.objects.NetworkLiftInterface;
/*  13:    */import org.schema.schine.network.objects.NetworkObject;
/*  14:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  15:    */import org.schema.schine.network.objects.remote.RemoteVector4i;
/*  16:    */import q;
/*  17:    */import s;
/*  18:    */import xq;
/*  19:    */
/*  20:    */public class LiftCollectionManager extends ElementCollectionManager implements NTReceiveInterface
/*  21:    */{
/*  22: 22 */  private final ArrayList toActivate = new ArrayList();
/*  23:    */  
/*  27:    */  public LiftCollectionManager(SegmentController paramSegmentController)
/*  28:    */  {
/*  29: 29 */    super((short)113, paramSegmentController);
/*  30:    */  }
/*  31:    */  
/*  39:    */  public void activate(q paramq, boolean paramBoolean)
/*  40:    */  {
/*  41: 41 */    System.err.println("CHECKING FOR LIFT UNIT IN " + getCollection().size() + " COLLECTIONS");
/*  42: 42 */    long l = ElementCollection.getIndex(paramq);
/*  43: 43 */    for (java.util.Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/*  44:    */      LiftUnit localLiftUnit;
/*  45: 45 */      if ((localLiftUnit = (LiftUnit)localIterator.next()).getNeighboringCollection().contains(Long.valueOf(l))) {
/*  46: 46 */        if (paramBoolean) {
/*  47: 47 */          System.err.println("ACTIVATING LIFT " + paramq + " " + getSegmentController().getState() + " " + getSegmentController());
/*  48: 48 */          System.err.println("ACTIVATING " + localLiftUnit + "; this unit size " + localLiftUnit.getNeighboringCollection().size() + " / units " + getCollection().size());
/*  49: 49 */          localLiftUnit.activate();
/*  50:    */        } else {
/*  51: 51 */          localLiftUnit.deactivate();
/*  52:    */        }
/*  53:    */      }
/*  54:    */    }
/*  55:    */  }
/*  56:    */  
/*  60:    */  public int getMargin()
/*  61:    */  {
/*  62: 62 */    return 0;
/*  63:    */  }
/*  64:    */  
/*  68:    */  protected Class getType()
/*  69:    */  {
/*  70: 70 */    return LiftUnit.class;
/*  71:    */  }
/*  72:    */  
/*  78:    */  protected void onChangedCollection() {}
/*  79:    */  
/*  84:    */  public void update(xq paramxq)
/*  85:    */  {
/*  86: 86 */    for (java.util.Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/*  87: 87 */      ((LiftUnit)localIterator.next()).update(paramxq);
/*  88:    */    }
/*  89: 89 */    if (!this.toActivate.isEmpty()) {
/*  90: 90 */      synchronized (this.toActivate) {
/*  91: 91 */        paramxq = (s)this.toActivate.remove(0);
/*  92: 92 */        activate(new q(paramxq.a, paramxq.b, paramxq.c), paramxq.d != 0);
/*  93: 93 */        return;
/*  94:    */      }
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/*  99:    */  public void updateFromNT(NetworkObject paramNetworkObject)
/* 100:    */  {
/* 101:    */    RemoteBuffer localRemoteBuffer;
/* 102:    */    
/* 103:103 */    for (java.util.Iterator localIterator = (localRemoteBuffer = ((NetworkLiftInterface)paramNetworkObject).getLiftActivate()).getReceiveBuffer().iterator(); localIterator.hasNext();) {
/* 104:104 */      s locals = ((RemoteVector4i)localIterator.next()).getVector();
/* 105:105 */      synchronized (this.toActivate) {
/* 106:106 */        this.toActivate.add(locals);
/* 107:    */      }
/* 108:    */      
/* 110:110 */      if (getSegmentController().isOnServer()) {
/* 111:111 */        localRemoteBuffer.add(new RemoteVector4i(locals, paramNetworkObject));
/* 112:    */      }
/* 113:    */    }
/* 114:    */  }
/* 115:    */  
/* 120:    */  public boolean needsUpdate()
/* 121:    */  {
/* 122:122 */    return true;
/* 123:    */  }
/* 124:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.lift.LiftCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */