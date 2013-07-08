/*  1:   */import java.util.Random;
/*  2:   */import org.schema.game.common.controller.SegmentController;
/*  3:   */import org.schema.game.common.data.world.Segment;
/*  4:   */
/* 14:   */public final class ug
/* 15:   */  extends uj
/* 16:   */{
/* 17:   */  private uX[] a;
/* 18:   */  
/* 19:   */  public ug(long paramLong)
/* 20:   */  {
/* 21:21 */    super(paramLong);
/* 22:22 */    this.jdField_a_of_type_ArrayOfUX = new uX[9];
/* 23:23 */    this.jdField_a_of_type_ArrayOfUX[0] = new va(128, 3, 91);
/* 24:24 */    this.jdField_a_of_type_ArrayOfUX[1] = new uZ(87, 14, 91);
/* 25:   */    
/* 26:26 */    this.jdField_a_of_type_ArrayOfUX[2] = new va(133, 6, 91);
/* 27:27 */    this.jdField_a_of_type_ArrayOfUX[3] = new va(72, 4, 91);
/* 28:28 */    this.jdField_a_of_type_ArrayOfUX[4] = new va(129, 4, 91);
/* 29:   */    
/* 30:30 */    this.jdField_a_of_type_ArrayOfUX[5] = new uW(97, new short[] { 90, 92 });
/* 31:31 */    this.jdField_a_of_type_ArrayOfUX[6] = new uW(101, new short[] { 90, 92 });
/* 32:32 */    this.jdField_a_of_type_ArrayOfUX[7] = new uW(105, new short[] { 90, 92 });
/* 33:33 */    this.jdField_a_of_type_ArrayOfUX[8] = new uW(109, new short[] { 90, 92 });
/* 34:   */  }
/* 35:   */  
/* 38:   */  public final void a(SegmentController paramSegmentController, Segment paramSegment)
/* 39:   */  {
/* 40:40 */    if (!this.jdField_a_of_type_Boolean) {
/* 41:41 */      this.jdField_a_of_type_UV = new uL(((jA)paramSegmentController).getSeed());
/* 42:42 */      this.jdField_a_of_type_UV.a(this);
/* 43:43 */      a();
/* 44:44 */      this.jdField_a_of_type_Boolean = true;
/* 45:   */    }
/* 46:   */    
/* 47:47 */    a(paramSegment);
/* 48:   */  }
/* 49:   */  
/* 52:   */  public final short a()
/* 53:   */  {
/* 54:54 */    return 92;
/* 55:   */  }
/* 56:   */  
/* 59:   */  public final uX[] a()
/* 60:   */  {
/* 61:61 */    return this.jdField_a_of_type_ArrayOfUX;
/* 62:   */  }
/* 63:   */  
/* 66:   */  public final short b()
/* 67:   */  {
/* 68:68 */    return 91;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public final short c()
/* 72:   */  {
/* 73:73 */    return 90;
/* 74:   */  }
/* 75:   */  
/* 76:   */  public final void a(Random paramRandom) {}
/* 77:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ug
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */