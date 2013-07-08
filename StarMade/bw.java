/*  1:   */import org.schema.game.common.data.element.ElementKeyMap;
/*  2:   */
/* 13:   */public final class bw
/* 14:   */  extends bu
/* 15:   */{
/* 16:   */  public bw(ct paramct, short paramShort, int paramInt, kf paramkf)
/* 17:   */  {
/* 18:18 */    super(paramct, paramShort, "Sell Quantity", new bv(ElementKeyMap.getInfo(paramShort), paramkf), paramInt);
/* 19:   */    
/* 21:21 */    ((bv)this.jdField_a_of_type_JavaLangObject).a = (-paramInt);
/* 22:   */    
/* 23:23 */    a(new bx(this, paramShort));
/* 24:   */    
/* 74:74 */    this.a.a(new by(this));
/* 75:   */  }
/* 76:   */  
/* 91:   */  public final boolean a(String paramString)
/* 92:   */  {
/* 93:93 */    paramString = Integer.parseInt(paramString);
/* 94:   */    
/* 96:96 */    this.a.a().a().b(this.jdField_a_of_type_Short, paramString);
/* 97:97 */    return true;
/* 98:   */  }
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */