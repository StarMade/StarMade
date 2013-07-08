/*   1:    */import org.lwjgl.opengl.GL20;
/*   2:    */
/* 413:    */final class zq
/* 414:    */  extends zj
/* 415:    */{
/* 416:416 */  zq(String paramString1, String paramString2) { super(paramString1, paramString2); }
/* 417:    */  
/* 418:    */  public final void a(int paramInt) {
/* 419:419 */    GL20.glBindAttribLocation(paramInt, 3, "indices");
/* 420:420 */    GL20.glBindAttribLocation(paramInt, 4, "weights");
/* 421:    */  }
/* 422:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */