/*  1:   */import java.util.Random;
/*  2:   */import org.schema.game.common.controller.SegmentController;
/*  3:   */import org.schema.game.common.data.world.Segment;
/*  4:   */
/* 11:   */public abstract class uj
/* 12:   */  extends tW
/* 13:   */{
/* 14:   */  private long a;
/* 15:   */  protected boolean a;
/* 16:   */  protected uV a;
/* 17:   */  
/* 18:   */  public uj(long paramLong)
/* 19:   */  {
/* 20:20 */    this.jdField_a_of_type_Boolean = false;
/* 21:   */    
/* 31:31 */    this.jdField_a_of_type_Long = paramLong;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public abstract void a(Random paramRandom);
/* 35:   */  
/* 36:   */  public void a(SegmentController paramSegmentController, Segment paramSegment) {
/* 37:37 */    if (!this.jdField_a_of_type_Boolean)
/* 38:   */    {
/* 42:42 */      this.jdField_a_of_type_UV = new uV(((jA)paramSegmentController).getSeed());
/* 43:43 */      this.jdField_a_of_type_UV.a(this);
/* 44:44 */      a();
/* 45:   */      
/* 46:46 */      this.jdField_a_of_type_Boolean = true;
/* 47:   */    }
/* 48:   */    
/* 49:49 */    a(paramSegment);
/* 50:   */  }
/* 51:   */  
/* 52:   */  protected final void a()
/* 53:   */  {
/* 54:   */    Random localRandom;
/* 55:55 */    if ((localRandom = new Random(this.jdField_a_of_type_Long)).nextInt(15) == 0) {
/* 56:56 */      a(localRandom);
/* 57:   */    }
/* 58:   */  }
/* 59:   */  
/* 60:   */  protected final void a(Segment paramSegment) {
/* 61:61 */    this.jdField_a_of_type_UV.a(paramSegment.a(), 64 + paramSegment.a.a / 16, Math.abs(paramSegment.a.b) / 16, 64 + paramSegment.a.c / 16, paramSegment.a.b < 0);
/* 62:   */    
/* 63:63 */    this.jdField_a_of_type_UV.a(paramSegment);
/* 64:   */  }
/* 65:   */  
/* 70:   */  public abstract short a();
/* 71:   */  
/* 75:   */  public abstract uX[] a();
/* 76:   */  
/* 80:   */  public abstract short b();
/* 81:   */  
/* 85:   */  public abstract short c();
/* 86:   */  
/* 90:   */  public short d()
/* 91:   */  {
/* 92:92 */    return 80;
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */