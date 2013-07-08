/*  1:   */import com.bulletphysics.linearmath.Transform;
/*  2:   */import javax.vecmath.Vector3f;
/*  3:   */import org.schema.game.common.data.element.ElementCollection;
/*  4:   */import org.schema.game.common.data.element.PointDistributionCustomOutputUnit;
/*  5:   */
/* 12:   */public final class en
/* 13:   */  implements Comparable
/* 14:   */{
/* 15:15 */  long jdField_a_of_type_Long = -1L;
/* 16:   */  private ElementCollection jdField_a_of_type_OrgSchemaGameCommonDataElementElementCollection;
/* 17:17 */  float jdField_a_of_type_Float = 0.0F;
/* 18:   */  private final eF jdField_a_of_type_EF;
/* 19:   */  
/* 20:   */  public en(kd paramkd, ElementCollection paramElementCollection)
/* 21:   */  {
/* 22:22 */    this.jdField_a_of_type_EF = new eF();
/* 23:23 */    ElementCollection localElementCollection = paramElementCollection;paramElementCollection = paramkd;paramkd = this;this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementCollection = localElementCollection; if ((localElementCollection instanceof PointDistributionCustomOutputUnit)) paramkd.jdField_a_of_type_EF.a(paramElementCollection, ((PointDistributionCustomOutputUnit)localElementCollection).getOutput()); else paramkd.jdField_a_of_type_EF.a(paramElementCollection, localElementCollection.getSignificator()); paramkd.jdField_a_of_type_Float = 0.0F;paramkd.jdField_a_of_type_Long = -1L;
/* 24:   */  }
/* 25:   */  
/* 33:   */  public final boolean equals(Object paramObject)
/* 34:   */  {
/* 35:35 */    return this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementCollection.equals(((en)paramObject).jdField_a_of_type_OrgSchemaGameCommonDataElementElementCollection);
/* 36:   */  }
/* 37:   */  
/* 43:43 */  public final int hashCode() { return this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementCollection.hashCode(); }
/* 44:   */  
/* 45:   */  public final boolean a() {
/* 46:46 */    if ((this.jdField_a_of_type_Long < 0L) || ((float)(System.currentTimeMillis() - this.jdField_a_of_type_Long) > 200.0F)) {
/* 47:47 */      return false;
/* 48:   */    }
/* 49:49 */    return true;
/* 50:   */  }
/* 51:   */  
/* 57:   */  public final void a()
/* 58:   */  {
/* 59:   */    long l;
/* 60:   */    
/* 64:64 */    if (((float)(l = System.currentTimeMillis() - this.jdField_a_of_type_Long) > 160.0F) && ((float)l < 200.0F)) {
/* 65:65 */      this.jdField_a_of_type_Long = (System.currentTimeMillis() + 160L);return; }
/* 66:66 */    if ((float)l >= 200.0F)
/* 67:67 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 68:   */  }
/* 69:   */  
/* 70:   */  public final void a(xq paramxq) {
/* 71:71 */    this.jdField_a_of_type_Float = ((float)(this.jdField_a_of_type_Float + paramxq.a() / 1000.0F * ((Math.random() + 9.999999747378752E-005D) / 0.1000000014901161D)));
/* 72:72 */    if (this.jdField_a_of_type_Float > 1.0F) {
/* 73:73 */      this.jdField_a_of_type_Float = 0.0F;
/* 74:   */    }
/* 75:   */  }
/* 76:   */  
/* 80:   */  public final void a(Transform paramTransform, Vector3f paramVector3f)
/* 81:   */  {
/* 82:82 */    this.jdField_a_of_type_EF.a(paramTransform, paramVector3f);
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     en
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */