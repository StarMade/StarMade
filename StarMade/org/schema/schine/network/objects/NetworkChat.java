/*  1:   */package org.schema.schine.network.objects;
/*  2:   */
/*  3:   */import org.schema.schine.network.StateInterface;
/*  4:   */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*  5:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  6:   */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteString;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteStringArray;
/*  9:   */
/* 10:   */public class NetworkChat extends NetworkObject
/* 11:   */{
/* 12:12 */  public RemoteBuffer chatLogBuffer = new RemoteBuffer(RemoteString.class, this);
/* 13:13 */  public RemoteBuffer chatServerLogBuffer = new RemoteBuffer(RemoteString.class, this);
/* 14:14 */  public RemoteInteger owner = new RemoteInteger(Integer.valueOf(-1), this);
/* 15:15 */  public RemoteArrayBuffer chatWisperBuffer = new RemoteArrayBuffer(3, RemoteStringArray.class, this);
/* 16:   */  
/* 17:17 */  public NetworkChat(StateInterface paramStateInterface) { super(paramStateInterface); }
/* 18:   */  
/* 19:   */  public void onDelete(StateInterface paramStateInterface) {}
/* 20:   */  
/* 21:   */  public void onInit(StateInterface paramStateInterface) {}
/* 22:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.NetworkChat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */