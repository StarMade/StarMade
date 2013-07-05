/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class ls extends lw
/*    */ {
/*    */   public Vector3f a;
/*    */ 
/*    */   public ls(byte paramByte, short paramShort)
/*    */   {
/* 22 */     super(paramByte, paramShort);
/*    */ 
/* 19 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */ 
/* 23 */     if ((!jdField_a_of_type_Boolean) && (paramByte != 2)) throw new AssertionError();
/*    */   }
/*    */ 
/*    */   public ls(short paramShort)
/*    */   {
/* 28 */     this((byte)2, paramShort);
/*    */   }
/*    */ 
/*    */   protected final void a(DataInputStream paramDataInputStream)
/*    */   {
/* 33 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*    */   }
/*    */ 
/*    */   protected final void a(DataOutputStream paramDataOutputStream)
/*    */   {
/* 38 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.x);
/* 39 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.y);
/* 40 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.z);
/*    */   }
/*    */ 
/*    */   public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/*    */   {
/* 48 */     if ((
/* 48 */       paramShort2ObjectArrayMap = (ln)paramShort2ObjectArrayMap.get(this.jdField_a_of_type_Short)) != null)
/*    */     {
/* 49 */       paramShort2ObjectArrayMap.b().origin.set(this.jdField_a_of_type_JavaxVecmathVector3f); return;
/*    */     }
/* 51 */     paramct.a().a().a(this.jdField_a_of_type_Short, paramt);
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 17 */     jdField_a_of_type_Boolean = !ls.class.desiredAssertionStatus();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ls
 * JD-Core Version:    0.6.2
 */