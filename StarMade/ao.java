/*   1:    */import java.util.ArrayList;
/*   2:    */import javax.vecmath.Vector3f;
/*   3:    */
/*  15:    */public final class ao
/*  16:    */  extends K
/*  17:    */{
/*  18:    */  private yp a;
/*  19:    */  
/*  20:    */  public ao(ct paramct, short paramShort, String paramString, Object paramObject, yp paramyp)
/*  21:    */  {
/*  22: 22 */    super(paramct, 5, paramString, paramObject, "1");
/*  23: 23 */    this.a = paramyp;
/*  24:    */    
/*  28: 28 */    a(new ap(paramShort));
/*  29:    */  }
/*  30:    */  
/*  48:    */  public final String[] getCommandPrefixes()
/*  49:    */  {
/*  50: 50 */    return null;
/*  51:    */  }
/*  52:    */  
/*  61:    */  public final String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/*  62:    */  {
/*  63: 63 */    return paramString1;
/*  64:    */  }
/*  65:    */  
/*  66:    */  public final boolean a()
/*  67:    */  {
/*  68: 68 */    return this.a.b().indexOf(this) != this.a.b().size() - 1;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public final void a() {
/*  72: 72 */    this.a.a().a.a.a.e(false);
/*  73:    */  }
/*  74:    */  
/*  77:    */  public final void onFailedTextCheck(String paramString)
/*  78:    */  {
/*  79: 79 */    a(paramString);
/*  80:    */  }
/*  81:    */  
/*  85:    */  public final boolean a(String paramString)
/*  86:    */  {
/*  87: 87 */    paramString = Integer.parseInt(paramString);
/*  88: 88 */    paramString = Math.min(((hR)this.a).a(false), paramString);
/*  89:    */    
/*  91: 91 */    this.a.a(true);
/*  92:    */    
/*  93: 93 */    this.a.setDragging(this.a);
/*  94:    */    
/*  95: 95 */    this.a.c((int)this.a.f().x + 32);
/*  96: 96 */    this.a.d((int)this.a.f().y + 32);
/*  97: 97 */    this.a.a(System.currentTimeMillis());
/*  98:    */    
/*  99: 99 */    ((hR)this.a).b(paramString);
/* 100:    */    
/* 102:102 */    return true;
/* 103:    */  }
/* 104:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */