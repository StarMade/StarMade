/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ abstract class LinuxPeerInfo extends PeerInfo
/*    */ {
/*    */   LinuxPeerInfo()
/*    */   {
/* 44 */     super(createHandle());
/*    */   }
/*    */   private static native ByteBuffer createHandle();
/*    */ 
/*    */   public final long getDisplay() {
/* 49 */     return nGetDisplay(getHandle());
/*    */   }
/*    */   private static native long nGetDisplay(ByteBuffer paramByteBuffer);
/*    */ 
/*    */   public final long getDrawable() {
/* 54 */     return nGetDrawable(getHandle());
/*    */   }
/*    */ 
/*    */   private static native long nGetDrawable(ByteBuffer paramByteBuffer);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxPeerInfo
 * JD-Core Version:    0.6.2
 */