/*  1:   */import com.bulletphysics.linearmath.Transform;
/*  2:   */import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/* 10:   */public class ls
/* 11:   */  extends lw
/* 12:   */{
/* 13:   */  public Vector3f a;
/* 14:   */  
/* 15:   */  static
/* 16:   */  {
/* 17:17 */    jdField_a_of_type_Boolean = !ls.class.desiredAssertionStatus();
/* 18:   */  }
/* 19:   */  
/* 20:   */  public ls(byte paramByte, short paramShort)
/* 21:   */  {
/* 22:22 */    super(paramByte, paramShort);this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 23:23 */    if ((!jdField_a_of_type_Boolean) && (paramByte != 2)) throw new AssertionError();
/* 24:   */  }
/* 25:   */  
/* 26:   */  public ls(short paramShort)
/* 27:   */  {
/* 28:28 */    this((byte)2, paramShort);
/* 29:   */  }
/* 30:   */  
/* 31:   */  protected final void a(DataInputStream paramDataInputStream)
/* 32:   */  {
/* 33:33 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 34:   */  }
/* 35:   */  
/* 36:   */  protected final void a(DataOutputStream paramDataOutputStream)
/* 37:   */  {
/* 38:38 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.x);
/* 39:39 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.y);
/* 40:40 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 41:   */  }
/* 42:   */  
/* 46:   */  public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/* 47:   */  {
/* 48:48 */    if ((paramShort2ObjectArrayMap = (ln)paramShort2ObjectArrayMap.get(this.jdField_a_of_type_Short)) != null) {
/* 49:49 */      paramShort2ObjectArrayMap.b().origin.set(this.jdField_a_of_type_JavaxVecmathVector3f);return;
/* 50:   */    }
/* 51:51 */    paramct.a().a().a(this.jdField_a_of_type_Short, paramt);
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ls
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */