/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import org.schema.schine.network.StateInterface;
/*  4:   */import org.schema.schine.network.objects.NetworkEntity;
/*  5:   */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  6:   */import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteVector3f;
/*  8:   */
/* 12:   */public class NetworkMissile
/* 13:   */  extends NetworkEntity
/* 14:   */{
/* 15:15 */  public RemoteVector3f dir = new RemoteVector3f(this);
/* 16:16 */  public RemoteIntPrimitive targetId = new RemoteIntPrimitive(-1, this);
/* 17:17 */  public RemoteBoolean alive = new RemoteBoolean(true, this);
/* 18:   */  
/* 19:   */  public NetworkMissile(StateInterface paramStateInterface) {
/* 20:20 */    super(paramStateInterface);
/* 21:   */  }
/* 22:   */  
/* 23:   */  public void onDelete(StateInterface paramStateInterface) {}
/* 24:   */  
/* 25:   */  public void onInit(StateInterface paramStateInterface) {}
/* 26:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkMissile
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */