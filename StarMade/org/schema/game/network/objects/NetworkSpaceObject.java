/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import org.schema.schine.network.StateInterface;
/*  4:   */import org.schema.schine.network.objects.NetworkEntity;
/*  5:   */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  6:   */
/*  8:   */public class NetworkSpaceObject
/*  9:   */  extends NetworkEntity
/* 10:   */{
/* 11:   */  public static final int TYPE_ASTEROID = 0;
/* 12:   */  public static final int TYPE_ASTEROID_STYLE_1 = 0;
/* 13:13 */  public RemoteInteger starSystemId = new RemoteInteger(Integer.valueOf(-1), this);
/* 14:   */  
/* 15:15 */  public RemoteInteger objectType = new RemoteInteger(Integer.valueOf(-1), this);
/* 16:16 */  public RemoteInteger objectSubtype = new RemoteInteger(Integer.valueOf(-1), this);
/* 17:   */  
/* 18:18 */  public NetworkSpaceObject(StateInterface paramStateInterface) { super(paramStateInterface); }
/* 19:   */  
/* 20:   */  public void onDelete(StateInterface paramStateInterface) {}
/* 21:   */  
/* 22:   */  public void onInit(StateInterface paramStateInterface) {}
/* 23:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkSpaceObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */