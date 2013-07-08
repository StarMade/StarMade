/*  1:   */import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*  2:   */import java.io.DataInputStream;
/*  3:   */import java.io.DataOutputStream;
/*  4:   */import javax.vecmath.Vector3f;
/*  5:   */
/* 10:   */public class lr
/* 11:   */  extends lw
/* 12:   */{
/* 13:   */  public Vector3f a;
/* 14:   */  
/* 15:   */  static
/* 16:   */  {
/* 17:17 */    jdField_a_of_type_Boolean = !lr.class.desiredAssertionStatus();
/* 18:   */  }
/* 19:   */  
/* 20:   */  public lr(byte paramByte, short paramShort)
/* 21:   */  {
/* 22:22 */    super(paramByte, paramShort);this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 23:23 */    if ((!jdField_a_of_type_Boolean) && (paramByte != 6)) throw new AssertionError();
/* 24:   */  }
/* 25:   */  
/* 26:   */  public lr(short paramShort) {
/* 27:27 */    this((byte)6, paramShort);
/* 28:   */  }
/* 29:   */  
/* 30:   */  protected final void a(DataInputStream paramDataInputStream)
/* 31:   */  {
/* 32:32 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 33:   */  }
/* 34:   */  
/* 35:   */  protected final void a(DataOutputStream paramDataOutputStream)
/* 36:   */  {
/* 37:37 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.x);
/* 38:38 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.y);
/* 39:39 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 40:   */  }
/* 41:   */  
/* 45:   */  public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/* 46:   */  {
/* 47:47 */    if ((paramShort2ObjectArrayMap = (ln)paramShort2ObjectArrayMap.get(this.jdField_a_of_type_Short)) != null) {
/* 48:48 */      paramShort2ObjectArrayMap.a().set(this.jdField_a_of_type_JavaxVecmathVector3f);return;
/* 49:   */    }
/* 50:50 */    paramct.a().a().a(this.jdField_a_of_type_Short, paramt);
/* 51:   */  }
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */