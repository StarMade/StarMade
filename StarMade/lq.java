/*  1:   */import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*  2:   */import java.io.DataInputStream;
/*  3:   */import java.io.DataOutputStream;
/*  4:   */import javax.vecmath.Vector3f;
/*  5:   */
/* 16:   */public class lq
/* 17:   */  extends lw
/* 18:   */{
/* 19:19 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 20:   */  
/* 21:   */  public lq(byte paramByte, short paramShort) {
/* 22:22 */    super(paramByte, paramShort);
/* 23:23 */    if ((!jdField_a_of_type_Boolean) && (paramByte != 3)) throw new AssertionError();
/* 24:   */  }
/* 25:   */  
/* 26:   */  public lq(short paramShort) {
/* 27:27 */    this((byte)3, paramShort);
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
/* 42:42 */  public final void b(DataOutputStream paramDataOutputStream) { super.b(paramDataOutputStream);
/* 43:43 */    if ((!jdField_a_of_type_Boolean) && (this.b != 3)) { throw new AssertionError();
/* 44:   */    }
/* 45:   */  }
/* 46:   */  
/* 47:   */  public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/* 48:   */  {
/* 49:49 */    if ((paramct = (ln)paramShort2ObjectArrayMap.remove(this.jdField_a_of_type_Short)) != null) {
/* 50:50 */      paramct.b();
/* 51:   */    }
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */