/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.game.network.objects.NetworkClientChannel;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteShort;
/*    */ 
/*    */ public class lt extends lw
/*    */ {
/*    */   public int a;
/*    */   public Vector3f a;
/* 21 */   public Vector3f b = new Vector3f();
/*    */ 
/*    */   public lt(byte paramByte, short paramShort) {
/* 24 */     super(paramByte, paramShort);
/*    */ 
/* 20 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */ 
/* 25 */     if ((!jdField_a_of_type_Boolean) && (paramByte != 5)) throw new AssertionError(); 
/*    */   }
/*    */ 
/*    */   public lt(short paramShort)
/*    */   {
/* 29 */     this((byte)5, paramShort);
/*    */   }
/*    */ 
/*    */   protected final void a(DataInputStream paramDataInputStream)
/*    */   {
/* 34 */     this.jdField_a_of_type_Int = paramDataInputStream.readInt();
/* 35 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/* 36 */     this.b.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*    */   }
/*    */ 
/*    */   protected final void a(DataOutputStream paramDataOutputStream)
/*    */   {
/* 41 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Int);
/* 42 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.x);
/* 43 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.y);
/* 44 */     paramDataOutputStream.writeFloat(this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 45 */     paramDataOutputStream.writeFloat(this.b.x);
/* 46 */     paramDataOutputStream.writeFloat(this.b.y);
/* 47 */     paramDataOutputStream.writeFloat(this.b.z);
/*    */   }
/*    */ 
/*    */   public final void a(ct paramct, Short2ObjectArrayMap paramShort2ObjectArrayMap, t paramt)
/*    */   {
/* 55 */     if ((
/* 55 */       paramct = (ln)paramShort2ObjectArrayMap.get(this.jdField_a_of_type_Short)) != null)
/*    */     {
/* 56 */       paramct.b(this.jdField_a_of_type_Int);
/* 57 */       paramct.b().origin.set(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 58 */       paramct.a().set(this.b); return;
/*    */     }
/* 60 */     paramt.a().missileMissingRequestBuffer.add(new RemoteShort(this.jdField_a_of_type_Short, false));
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 17 */     jdField_a_of_type_Boolean = !lt.class.desiredAssertionStatus();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lt
 * JD-Core Version:    0.6.2
 */