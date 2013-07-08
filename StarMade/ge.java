/*   1:    */import java.io.IOException;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.schema.game.common.controller.SegmentController;
/*   5:    */
/* 140:    */final class ge
/* 141:    */  extends K
/* 142:    */{
/* 143:    */  ge(gd paramgd, ct paramct, Object paramObject1, Object paramObject2, String paramString)
/* 144:    */  {
/* 145:145 */    super(paramct, 50, paramObject1, paramObject2, paramString);
/* 146:    */  }
/* 147:    */  
/* 148:148 */  public final String[] getCommandPrefixes() { return null; }
/* 149:    */  
/* 152:    */  public final String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/* 153:    */  {
/* 154:154 */    return paramString1;
/* 155:    */  }
/* 156:    */  
/* 157:    */  public final boolean a()
/* 158:    */  {
/* 159:159 */    return this.a.b().indexOf(this) != this.a.b().size() - 1;
/* 160:    */  }
/* 161:    */  
/* 162:    */  public final void a()
/* 163:    */  {
/* 164:164 */    this.a.a().e(false);
/* 165:    */  }
/* 166:    */  
/* 167:    */  public final void onFailedTextCheck(String paramString)
/* 168:    */  {
/* 169:169 */    a("SHIPNAME INVALID: " + paramString);
/* 170:    */  }
/* 171:    */  
/* 172:    */  public final boolean a(String paramString)
/* 173:    */  {
/* 174:    */    mF localmF;
/* 175:175 */    if (((localmF = this.a.a()) == null) || (!(localmF instanceof kd))) {
/* 176:176 */      System.err.println("[ERROR] Player not int a ship");
/* 177:177 */      return false;
/* 178:    */    }
/* 179:    */    try {
/* 180:180 */      tH.a.a((SegmentController)localmF, paramString, true);
/* 181:    */    } catch (IOException localIOException) {
/* 182:182 */      (paramString = 
/* 183:    */      
/* 184:184 */        localIOException).printStackTrace();xm.a(paramString);
/* 185:    */    }
/* 186:    */    
/* 187:186 */    return true;
/* 188:    */  }
/* 189:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ge
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */