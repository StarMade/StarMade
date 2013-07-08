/*  1:   */package org.schema.schine.network;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */
/*  5:   */public class NetworkGravity
/*  6:   */{
/*  7: 7 */  public int gravityId = -1;
/*  8: 8 */  public int gravityIdReceive = -1;
/*  9:   */  
/* 10:10 */  public Vector3f gravity = new Vector3f();
/* 11:11 */  public Vector3f gravityReceive = new Vector3f();
/* 12:   */  public boolean gravityReceived;
/* 13:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.NetworkGravity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */