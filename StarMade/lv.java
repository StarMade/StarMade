/*    */ import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.game.network.objects.NetworkClientChannel;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteShort;
/*    */ 
/*    */ public class lv extends lw
/*    */ {
/*    */   public int a;
/*    */ 
/*    */   public lv(byte paramByte, short paramShort)
/*    */   {
/* 23 */     super(paramByte, paramShort);
/*    */   }
/*    */ 
/*    */   public lv(short paramShort) {
/* 27 */     this((byte)4, paramShort);
/*    */   }
/*    */ 
/*    */   protected final void a(DataInputStream paramDataInputStream)
/*    */   {
/* 32 */     this.jdField_a_of_type_Int = paramDataInputStream.readInt();
/* 33 */     if ((!jdField_a_of_type_Boolean) && (this.b != 4)) throw new AssertionError();
/*    */   }
/*    */ 
/*    */   protected final void a(DataOutputStream paramDataOutputStream)
/*    */   {
/* 38 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Int);
/*    */   }
/*    */ 
/*    */   public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/*    */   {
/* 46 */     if (((
/* 46 */       paramct = (ln)paramShort2ObjectArrayMap.get(this.jdField_a_of_type_Short)) != null) && 
/* 46 */       ((paramct instanceof lp))) {
/* 47 */       ((lp)paramct).c(this.jdField_a_of_type_Int); return;
/*    */     }
/* 49 */     paramt.a().missileMissingRequestBuffer.add(new RemoteShort(this.jdField_a_of_type_Short, false));
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 18 */     jdField_a_of_type_Boolean = !lv.class.desiredAssertionStatus();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lv
 * JD-Core Version:    0.6.2
 */