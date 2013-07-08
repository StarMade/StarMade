/*  1:   */import com.bulletphysics.linearmath.Transform;
/*  2:   */import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */import org.schema.game.network.objects.NetworkClientChannel;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteShort;
/*  9:   */
/* 10:   */public class lt extends lw
/* 11:   */{
/* 12:   */  public int a;
/* 13:   */  public Vector3f a;
/* 14:   */  
/* 15:   */  static
/* 16:   */  {
/* 17:17 */    jdField_a_of_type_Boolean = !lt.class.desiredAssertionStatus();
/* 18:   */  }
/* 19:   */  
/* 21:21 */  public Vector3f b = new Vector3f();
/* 22:   */  
/* 23:   */  public lt(byte paramByte, short paramShort) {
/* 24:24 */    super(paramByte, paramShort);this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 25:25 */    if ((!jdField_a_of_type_Boolean) && (paramByte != 5)) throw new AssertionError();
/* 26:   */  }
/* 27:   */  
/* 28:   */  public lt(short paramShort) {
/* 29:29 */    this((byte)5, paramShort);
/* 30:   */  }
/* 31:   */  
/* 32:   */  protected final void a(DataInputStream paramDataInputStream)
/* 33:   */  {
/* 34:34 */    this.jdField_a_of_type_Int = paramDataInputStream.readInt();
/* 35:35 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 36:36 */    this.b.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 37:   */  }
/* 38:   */  
/* 39:   */  protected final void a(DataOutputStream paramDataOutputStream)
/* 40:   */  {
/* 41:41 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Int);
/* 42:42 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.x);
/* 43:43 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.y);
/* 44:44 */    paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 45:45 */    paramDataOutputStream.writeFloat(this.b.x);
/* 46:46 */    paramDataOutputStream.writeFloat(this.b.y);
/* 47:47 */    paramDataOutputStream.writeFloat(this.b.z);
/* 48:   */  }
/* 49:   */  
/* 53:   */  public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/* 54:   */  {
/* 55:55 */    if ((paramct = (ln)paramShort2ObjectArrayMap.get(this.jdField_a_of_type_Short)) != null) {
/* 56:56 */      paramct.b(this.jdField_a_of_type_Int);
/* 57:57 */      paramct.b().origin.set(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 58:58 */      paramct.a().set(this.b);return;
/* 59:   */    }
/* 60:60 */    paramt.a().missileMissingRequestBuffer.add(new RemoteShort(this.jdField_a_of_type_Short, false));
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */