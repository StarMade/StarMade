/*  1:   */import java.util.Random;
/*  2:   */import org.schema.game.common.controller.SegmentController;
/*  3:   */import org.schema.game.common.data.world.Segment;
/*  4:   */
/* 14:   */public final class uk
/* 15:   */  extends uj
/* 16:   */{
/* 17:   */  private uX[] a;
/* 18:   */  
/* 19:   */  public final void a(SegmentController paramSegmentController, Segment paramSegment)
/* 20:   */  {
/* 21:21 */    if (!this.jdField_a_of_type_Boolean) {
/* 22:22 */      this.jdField_a_of_type_UV = new uN(((jA)paramSegmentController).getSeed());
/* 23:23 */      this.jdField_a_of_type_UV.a(this);
/* 24:24 */      a();
/* 25:25 */      this.jdField_a_of_type_Boolean = true;
/* 26:   */    }
/* 27:   */    
/* 28:28 */    a(paramSegment);
/* 29:   */  }
/* 30:   */  
/* 31:   */  public uk(long paramLong) {
/* 32:32 */    super(paramLong);
/* 33:33 */    this.jdField_a_of_type_ArrayOfUX = new uX[8];
/* 34:34 */    this.jdField_a_of_type_ArrayOfUX[0] = new uZ(137, 4, 275);
/* 35:35 */    this.jdField_a_of_type_ArrayOfUX[1] = new uZ(132, 4, 275);
/* 36:36 */    this.jdField_a_of_type_ArrayOfUX[2] = new uZ(130, 4, 275);
/* 37:37 */    this.jdField_a_of_type_ArrayOfUX[3] = new uZ(129, 4, 275);
/* 38:   */    
/* 39:39 */    this.jdField_a_of_type_ArrayOfUX[4] = new uW(Integer.valueOf(1), 280, new short[] { 274, 64 });
/* 40:40 */    this.jdField_a_of_type_ArrayOfUX[5] = new uW(Integer.valueOf(1), 279, new short[] { 274, 64 });
/* 41:41 */    this.jdField_a_of_type_ArrayOfUX[6] = new uW(Integer.valueOf(1), 281, new short[] { 274, 64 });
/* 42:42 */    this.jdField_a_of_type_ArrayOfUX[7] = new uW(Integer.valueOf(1), 278, new short[] { 274, 64 });
/* 43:   */  }
/* 44:   */  
/* 48:   */  public final short a()
/* 49:   */  {
/* 50:50 */    return 64;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public final short d()
/* 54:   */  {
/* 55:55 */    return 286;
/* 56:   */  }
/* 57:   */  
/* 58:   */  public final uX[] a() {
/* 59:59 */    return this.jdField_a_of_type_ArrayOfUX;
/* 60:   */  }
/* 61:   */  
/* 64:   */  public final short b()
/* 65:   */  {
/* 66:66 */    return 275;
/* 67:   */  }
/* 68:   */  
/* 71:   */  public final short c()
/* 72:   */  {
/* 73:73 */    return 274;
/* 74:   */  }
/* 75:   */  
/* 76:   */  public final void a(Random paramRandom) {}
/* 77:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */