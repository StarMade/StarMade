/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.ArrayList;
/*   3:    */import org.schema.game.network.objects.NetworkPlayer;
/*   4:    */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*   5:    */import org.schema.schine.network.objects.remote.RemoteStringArray;
/*   6:    */
/* 268:    */final class gi
/* 269:    */  extends K
/* 270:    */{
/* 271:    */  gi(gd paramgd, ct paramct, Object paramObject1, Object paramObject2, String paramString)
/* 272:    */  {
/* 273:273 */    super(paramct, 50, paramObject1, paramObject2, paramString);
/* 274:    */  }
/* 275:    */  
/* 276:276 */  public final String[] getCommandPrefixes() { return null; }
/* 277:    */  
/* 280:    */  public final String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/* 281:    */  {
/* 282:282 */    return paramString1;
/* 283:    */  }
/* 284:    */  
/* 285:    */  public final boolean a()
/* 286:    */  {
/* 287:287 */    return this.a.b().indexOf(this) != this.a.b().size() - 1;
/* 288:    */  }
/* 289:    */  
/* 290:    */  public final void a()
/* 291:    */  {
/* 292:292 */    this.a.a().e(false);
/* 293:    */  }
/* 294:    */  
/* 295:    */  public final void onFailedTextCheck(String paramString)
/* 296:    */  {
/* 297:297 */    a("SHIPNAME INVALID: " + paramString);
/* 298:    */  }
/* 299:    */  
/* 300:    */  public final boolean a(String paramString)
/* 301:    */  {
/* 302:    */    mF localmF;
/* 303:303 */    if (((localmF = this.a.a()) == null) || (!(localmF instanceof kd))) {
/* 304:304 */      System.err.println("[ERROR] Player not int a ship");
/* 305:305 */      return false;
/* 306:    */    }
/* 307:    */    RemoteStringArray localRemoteStringArray;
/* 308:308 */    (localRemoteStringArray = new RemoteStringArray(2, this.a.a().a())).set(0, "#save;" + localmF.getId());
/* 309:309 */    localRemoteStringArray.set(1, paramString);
/* 310:310 */    this.a.a().a().catalogBuyBuffer.add(localRemoteStringArray);
/* 311:    */    
/* 312:312 */    return true;
/* 313:    */  }
/* 314:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gi
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */