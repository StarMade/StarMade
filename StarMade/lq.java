/*    */ import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class lq extends lw
/*    */ {
/* 19 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */ 
/*    */   public lq(byte paramByte, short paramShort) {
/* 22 */     super(paramByte, paramShort);
/* 23 */     if ((!jdField_a_of_type_Boolean) && (paramByte != 3)) throw new AssertionError(); 
/*    */   }
/*    */ 
/*    */   public lq(short paramShort)
/*    */   {
/* 27 */     this((byte)3, paramShort);
/*    */   }
/*    */ 
/*    */   protected final void a(DataInputStream paramDataInputStream)
/*    */   {
/* 32 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*    */   }
/*    */ 
/*    */   protected final void a(DataOutputStream paramDataOutputStream)
/*    */   {
/* 37 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.x);
/* 38 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.y);
/* 39 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.z);
/*    */   }
/*    */   public final void b(DataOutputStream paramDataOutputStream) {
/* 42 */     super.b(paramDataOutputStream);
/* 43 */     if ((!jdField_a_of_type_Boolean) && (this.b != 3)) throw new AssertionError();
/*    */   }
/*    */ 
/*    */   public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/*    */   {
/* 49 */     if ((
/* 49 */       paramct = (ln)paramShort2ObjectArrayMap.remove(this.jdField_a_of_type_Short)) != null)
/*    */     {
/* 50 */       paramct.b();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lq
 * JD-Core Version:    0.6.2
 */