/*   1:    */import java.io.IOException;
/*   2:    */import java.util.ArrayList;
/*   3:    */import org.schema.game.common.data.UploadInProgressException;
/*   4:    */
/* 206:    */final class gg
/* 207:    */  extends K
/* 208:    */{
/* 209:    */  gg(gd paramgd, ct paramct, Object paramObject1, Object paramObject2, String paramString)
/* 210:    */  {
/* 211:211 */    super(paramct, 50, paramObject1, paramObject2, paramString);
/* 212:    */  }
/* 213:    */  
/* 214:214 */  public final String[] getCommandPrefixes() { return null; }
/* 215:    */  
/* 218:    */  public final String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/* 219:    */  {
/* 220:220 */    return paramString1;
/* 221:    */  }
/* 222:    */  
/* 223:    */  public final boolean a()
/* 224:    */  {
/* 225:225 */    return this.a.b().indexOf(this) != this.a.b().size() - 1;
/* 226:    */  }
/* 227:    */  
/* 228:    */  public final void a()
/* 229:    */  {
/* 230:230 */    this.a.a().e(false);
/* 231:    */  }
/* 232:    */  
/* 233:    */  public final void onFailedTextCheck(String paramString)
/* 234:    */  {
/* 235:235 */    a("SHIPNAME INVALID: " + paramString);
/* 236:    */  }
/* 237:    */  
/* 238:    */  public final boolean a(String paramString)
/* 239:    */  {
/* 240:    */    try {
/* 241:241 */      this.a.a().a().a(paramString);
/* 242:    */      
/* 243:243 */      return true;
/* 244:    */    } catch (IOException localIOException) {
/* 245:245 */      (paramString = localIOException).printStackTrace();
/* 246:246 */      xm.a(paramString);
/* 247:    */    } catch (UploadInProgressException paramString) {
/* 248:248 */      this.a.a().b("Cannot Upload!\nThere is already\nan Upload in progress");
/* 249:    */    }
/* 250:    */    
/* 251:251 */    return false;
/* 252:    */  }
/* 253:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */