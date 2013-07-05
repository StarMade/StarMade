/*    */ import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class lu extends lw
/*    */ {
/*    */   public Vector3f a;
/*    */   public Vector3f b;
/*    */   public int a;
/*    */   public byte a;
/*    */   public int b;
/*    */ 
/*    */   public lu(byte paramByte, short paramShort)
/*    */   {
/* 35 */     super(paramByte, paramShort);
/*    */ 
/* 24 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 25 */     this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */ 
/* 28 */     this.jdField_b_of_type_Int = -666;
/*    */ 
/* 36 */     if ((!jdField_a_of_type_Boolean) && (paramByte != 1)) throw new AssertionError();
/*    */   }
/*    */ 
/*    */   public lu(short paramShort, Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt1, byte paramByte, int paramInt2)
/*    */   {
/* 42 */     super((byte)1, paramShort);
/*    */ 
/* 24 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 25 */     this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */ 
/* 28 */     this.jdField_b_of_type_Int = -666;
/*    */ 
/* 43 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(paramVector3f1);
/* 44 */     this.jdField_b_of_type_JavaxVecmathVector3f.set(paramVector3f2);
/* 45 */     this.jdField_a_of_type_Int = paramInt1;
/* 46 */     this.jdField_a_of_type_Byte = paramByte;
/* 47 */     this.jdField_b_of_type_Int = paramInt2;
/*    */   }
/*    */ 
/*    */   protected final void a(DataInputStream paramDataInputStream) {
/* 51 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 52 */     this.jdField_b_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 53 */     this.jdField_a_of_type_Int = paramDataInputStream.readInt();
/* 54 */     this.jdField_b_of_type_Int = paramDataInputStream.readInt();
/* 55 */     this.jdField_a_of_type_Byte = paramDataInputStream.readByte();
/*    */   }
/*    */ 
/*    */   protected final void a(DataOutputStream paramDataOutputStream)
/*    */   {
/* 60 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.x);
/* 61 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.y);
/* 62 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 63 */     paramDataOutputStream.writeFloat(this.jdField_b_of_type_JavaxVecmathVector3f.x);
/* 64 */     paramDataOutputStream.writeFloat(this.jdField_b_of_type_JavaxVecmathVector3f.y);
/* 65 */     paramDataOutputStream.writeFloat(this.jdField_b_of_type_JavaxVecmathVector3f.z);
/* 66 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Int);
/* 67 */     paramDataOutputStream.writeInt(this.jdField_b_of_type_Int);
/* 68 */     paramDataOutputStream.writeByte(this.jdField_a_of_type_Byte);
/*    */   }
/*    */ 
/*    */   public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/*    */   {
/* 87 */     if (!paramShort2ObjectArrayMap.containsKey(this.jdField_a_of_type_Short)) {
/* 88 */       paramt = paramct; paramct = this; switch (this.jdField_a_of_type_Byte) { case 1:
/* 88 */         paramt = new lk(paramt); break;
/*    */       case 2:
/* 88 */         paramt = new lm(paramt); break;
/*    */       case 3:
/* 88 */         paramt = new ll(paramt); break;
/*    */       default:
/* 88 */         throw new IllegalArgumentException("Missile Type unknown " + paramct.jdField_a_of_type_Byte); } paramt.a(paramct); paramct = paramt;
/* 89 */       System.err.println("[CLIENT] SPAWNING NEW MISSILE " + paramct);
/* 90 */       paramShort2ObjectArrayMap.put(paramct.a(), paramct);
/* 91 */       paramct.a();
/* 92 */       return;
/* 93 */     }System.err.println("[CLIENT] not adding missile (already exists) ID " + this.jdField_a_of_type_Short + " -> " + paramShort2ObjectArrayMap.get(this.jdField_a_of_type_Short));
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 21 */     jdField_a_of_type_Boolean = !lu.class.desiredAssertionStatus();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lu
 * JD-Core Version:    0.6.2
 */