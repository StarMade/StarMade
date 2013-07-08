/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import org.schema.schine.network.StateInterface;
/*  4:   */import org.schema.schine.network.objects.NetworkEntity;
/*  5:   */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  6:   */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteString;
/*  8:   */
/*  9:   */public class NetworkPlayerCharacter
/* 10:   */  extends NetworkEntity
/* 11:   */{
/* 12:12 */  public RemoteInteger clientOwnerId = new RemoteInteger(Integer.valueOf(-1), this);
/* 13:   */  
/* 14:14 */  public RemoteString uniqueId = new RemoteString(this);
/* 15:15 */  public RemoteBoolean spawnedOnServer = new RemoteBoolean(this);
/* 16:   */  
/* 17:17 */  public NetworkPlayerCharacter(StateInterface paramStateInterface) { super(paramStateInterface); }
/* 18:   */  
/* 19:   */  public void onDelete(StateInterface paramStateInterface) {}
/* 20:   */  
/* 21:   */  public void onInit(StateInterface paramStateInterface) {}
/* 22:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkPlayerCharacter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */