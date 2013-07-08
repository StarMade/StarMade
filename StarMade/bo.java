/*   1:    */import org.schema.game.common.data.element.ElementKeyMap;
/*   2:    */
/*  12:    */public final class bo
/*  13:    */  extends bu
/*  14:    */{
/*  15:    */  public bo(ct paramct, short paramShort, kf paramkf)
/*  16:    */  {
/*  17: 17 */    super(paramct, paramShort, "Buy Quantity", new bn(ElementKeyMap.getInfo(paramShort), paramkf), 1);
/*  18:    */    
/*  20: 20 */    ((bn)this.jdField_a_of_type_JavaLangObject).a = 1;
/*  21:    */    
/*  22: 22 */    a(new bp(this, paramShort));
/*  23:    */    
/*  83: 83 */    this.a.a(new bq(this));
/*  84:    */  }
/*  85:    */  
/*  95:    */  public final boolean a(String paramString)
/*  96:    */  {
/*  97: 97 */    paramString = Integer.parseInt(paramString);
/*  98:    */    
/* 100:100 */    this.a.a().a().a(this.jdField_a_of_type_Short, paramString);
/* 101:101 */    return true;
/* 102:    */  }
/* 103:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */