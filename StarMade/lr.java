/*    */ import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class lr extends lw
/*    */ {
/*    */   public Vector3f a;
/*    */ 
/*    */   public lr(byte paramByte, short paramShort)
/*    */   {
/* 22 */     super(paramByte, paramShort);
/*    */ 
/* 19 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */ 
/* 23 */     if ((!jdField_a_of_type_Boolean) && (paramByte != 6)) throw new AssertionError(); 
/*    */   }
/*    */ 
/*    */   public lr(short paramShort)
/*    */   {
/* 27 */     this((byte)6, paramShort);
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
/*    */ 
/*    */   public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/*    */   {
/* 47 */     if ((
/* 47 */       paramShort2ObjectArrayMap = (ln)paramShort2ObjectArrayMap.get(this.jdField_a_of_type_Short)) != null)
/*    */     {
/* 48 */       paramShort2ObjectArrayMap.a().set(this.jdField_a_of_type_JavaxVecmathVector3f); return;
/*    */     }
/* 50 */     paramct.a().a().a(this.jdField_a_of_type_Short, paramt);
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 17 */     jdField_a_of_type_Boolean = !lr.class.desiredAssertionStatus();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lr
 * JD-Core Version:    0.6.2
 */