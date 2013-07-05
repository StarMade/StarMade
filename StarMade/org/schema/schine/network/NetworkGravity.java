/*    */ package org.schema.schine.network;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class NetworkGravity
/*    */ {
/*  7 */   public int gravityId = -1;
/*  8 */   public int gravityIdReceive = -1;
/*    */ 
/* 10 */   public Vector3f gravity = new Vector3f();
/* 11 */   public Vector3f gravityReceive = new Vector3f();
/*    */   public boolean gravityReceived;
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.NetworkGravity
 * JD-Core Version:    0.6.2
 */