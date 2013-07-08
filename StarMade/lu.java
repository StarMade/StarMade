/*  1:   */import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*  2:   */import java.io.DataInputStream;
/*  3:   */import java.io.DataOutputStream;
/*  4:   */import java.io.PrintStream;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/* 10:   */public class lu
/* 11:   */  extends lw
/* 12:   */{
/* 13:   */  public Vector3f a;
/* 14:   */  public Vector3f b;
/* 15:   */  public int a;
/* 16:   */  public byte a;
/* 17:   */  public int b;
/* 18:   */  
/* 19:   */  static
/* 20:   */  {
/* 21:21 */    jdField_a_of_type_Boolean = !lu.class.desiredAssertionStatus();
/* 22:   */  }
/* 23:   */  
/* 33:   */  public lu(byte paramByte, short paramShort)
/* 34:   */  {
/* 35:35 */    super(paramByte, paramShort);this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();this.jdField_b_of_type_Int = -666;
/* 36:36 */    if ((!jdField_a_of_type_Boolean) && (paramByte != 1)) { throw new AssertionError();
/* 37:   */    }
/* 38:   */  }
/* 39:   */  
/* 40:   */  public lu(short paramShort, Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt1, byte paramByte, int paramInt2)
/* 41:   */  {
/* 42:42 */    super((byte)1, paramShort);this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();this.jdField_b_of_type_Int = -666;
/* 43:43 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(paramVector3f1);
/* 44:44 */    this.jdField_b_of_type_JavaxVecmathVector3f.set(paramVector3f2);
/* 45:45 */    this.jdField_a_of_type_Int = paramInt1;
/* 46:46 */    this.jdField_a_of_type_Byte = paramByte;
/* 47:47 */    this.jdField_b_of_type_Int = paramInt2;
/* 48:   */  }
/* 49:   */  
/* 50:   */  protected final void a(DataInputStream paramDataInputStream) {
/* 51:51 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 52:52 */    this.jdField_b_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 53:53 */    this.jdField_a_of_type_Int = paramDataInputStream.readInt();
/* 54:54 */    this.jdField_b_of_type_Int = paramDataInputStream.readInt();
/* 55:55 */    this.jdField_a_of_type_Byte = paramDataInputStream.readByte();
/* 56:   */  }
/* 57:   */  
/* 58:   */  protected final void a(DataOutputStream paramDataOutputStream)
/* 59:   */  {
/* 60:60 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.x);
/* 61:61 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.y);
/* 62:62 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 63:63 */    paramDataOutputStream.writeFloat(this.jdField_b_of_type_JavaxVecmathVector3f.x);
/* 64:64 */    paramDataOutputStream.writeFloat(this.jdField_b_of_type_JavaxVecmathVector3f.y);
/* 65:65 */    paramDataOutputStream.writeFloat(this.jdField_b_of_type_JavaxVecmathVector3f.z);
/* 66:66 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Int);
/* 67:67 */    paramDataOutputStream.writeInt(this.jdField_b_of_type_Int);
/* 68:68 */    paramDataOutputStream.writeByte(this.jdField_a_of_type_Byte);
/* 69:   */  }
/* 70:   */  
/* 85:   */  public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/* 86:   */  {
/* 87:87 */    if (!paramShort2ObjectArrayMap.containsKey(this.jdField_a_of_type_Short)) {
/* 88:88 */      paramt = paramct;paramct = this; switch (this.jdField_a_of_type_Byte) {case 1:  paramt = new lk(paramt);break; case 2:  paramt = new lm(paramt);break; case 3:  paramt = new ll(paramt);break; default:  throw new IllegalArgumentException("Missile Type unknown " + paramct.jdField_a_of_type_Byte); } paramt.a(paramct);paramct = paramt;
/* 89:89 */      System.err.println("[CLIENT] SPAWNING NEW MISSILE " + paramct);
/* 90:90 */      paramShort2ObjectArrayMap.put(paramct.a(), paramct);
/* 91:91 */      paramct.a();
/* 92:92 */      return; }
/* 93:93 */    System.err.println("[CLIENT] not adding missile (already exists) ID " + this.jdField_a_of_type_Short + " -> " + paramShort2ObjectArrayMap.get(this.jdField_a_of_type_Short));
/* 94:   */  }
/* 95:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lu
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */