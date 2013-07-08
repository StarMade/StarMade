/*  1:   */import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*  2:   */import java.io.DataInputStream;
/*  3:   */import java.io.DataOutputStream;
/*  4:   */import org.schema.game.network.objects.NetworkClientChannel;
/*  5:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  6:   */import org.schema.schine.network.objects.remote.RemoteShort;
/*  7:   */
/* 11:   */public class lv
/* 12:   */  extends lw
/* 13:   */{
/* 14:   */  public int a;
/* 15:   */  
/* 16:   */  static
/* 17:   */  {
/* 18:18 */    jdField_a_of_type_Boolean = !lv.class.desiredAssertionStatus();
/* 19:   */  }
/* 20:   */  
/* 21:   */  public lv(byte paramByte, short paramShort)
/* 22:   */  {
/* 23:23 */    super(paramByte, paramShort);
/* 24:   */  }
/* 25:   */  
/* 26:   */  public lv(short paramShort) {
/* 27:27 */    this((byte)4, paramShort);
/* 28:   */  }
/* 29:   */  
/* 30:   */  protected final void a(DataInputStream paramDataInputStream)
/* 31:   */  {
/* 32:32 */    this.jdField_a_of_type_Int = paramDataInputStream.readInt();
/* 33:33 */    if ((!jdField_a_of_type_Boolean) && (this.b != 4)) throw new AssertionError();
/* 34:   */  }
/* 35:   */  
/* 36:   */  protected final void a(DataOutputStream paramDataOutputStream)
/* 37:   */  {
/* 38:38 */    paramDataOutputStream.writeInt(this.jdField_a_of_type_Int);
/* 39:   */  }
/* 40:   */  
/* 44:   */  public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/* 45:   */  {
/* 46:46 */    if (((paramct = (ln)paramShort2ObjectArrayMap.get(this.jdField_a_of_type_Short)) != null) && ((paramct instanceof lp))) {
/* 47:47 */      ((lp)paramct).c(this.jdField_a_of_type_Int);return;
/* 48:   */    }
/* 49:49 */    paramt.a().missileMissingRequestBuffer.add(new RemoteShort(this.jdField_a_of_type_Short, false));
/* 50:   */  }
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */