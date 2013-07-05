/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.util.Arrays;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.util.ByteUtil;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.Element;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ 
/*     */ public class le
/*     */ {
/*     */   private byte[] jdField_a_of_type_ArrayOfByte;
/*     */   private Segment jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment;
/*  22 */   private int jdField_a_of_type_Int = -1;
/*     */   public byte a;
/*     */   public byte b;
/*     */   public byte c;
/*     */ 
/*     */   public le()
/*     */   {
/*  51 */     this.jdField_a_of_type_ArrayOfByte = new byte[3];
/*     */   }
/*     */ 
/*     */   public final void a(Segment paramSegment, o paramo)
/*     */   {
/*  56 */     this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
/*  57 */     a(paramo);
/*     */ 
/*  60 */     if (paramSegment.g())
/*     */     {
/*  62 */       if (this.jdField_a_of_type_ArrayOfByte == null) {
/*  63 */         this.jdField_a_of_type_ArrayOfByte = new byte[3]; return;
/*     */       }
/*  65 */       Arrays.fill(this.jdField_a_of_type_ArrayOfByte, (byte)0); return;
/*     */     }
/*     */ 
/*  68 */     this.jdField_a_of_type_ArrayOfByte = paramSegment.a().getSegmentPieceData(SegmentData.getInfoIndex(paramo), this.jdField_a_of_type_ArrayOfByte == null ? new byte[3] : this.jdField_a_of_type_ArrayOfByte);
/*     */   }
/*     */ 
/*     */   public le(Segment paramSegment, o paramo)
/*     */   {
/*  74 */     a(paramSegment, paramo);
/*     */   }
/*     */ 
/*     */   public le(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/*  82 */     this.jdField_a_of_type_Byte = paramByte1;
/*  83 */     this.b = paramByte2;
/*  84 */     this.c = paramByte3;
/*  85 */     this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
/*  86 */     if (paramSegment.g())
/*     */     {
/*  88 */       this.jdField_a_of_type_ArrayOfByte = new byte[3]; return;
/*     */     }
/*  90 */     this.jdField_a_of_type_ArrayOfByte = paramSegment.a().getSegmentPieceData(SegmentData.getInfoIndex(paramByte1, paramByte2, paramByte3), new byte[3]);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 101 */     if ((paramObject != null) && ((paramObject instanceof le)))
/*     */     {
/* 103 */       return ((
/* 103 */         paramObject = (le)paramObject).jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment
/* 103 */         .a() == this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a()) && (paramObject.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a.equals(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a)) && (paramObject.jdField_a_of_type_Byte == this.jdField_a_of_type_Byte) && (paramObject.b == this.b) && (paramObject.c == this.c);
/*     */     }
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean a(q paramq) {
/* 109 */     return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a(paramq, this.jdField_a_of_type_Byte, this.b, this.c);
/*     */   }
/*     */ 
/*     */   public final q a(q paramq) {
/* 113 */     return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a(this.jdField_a_of_type_Byte, this.b, this.c, paramq);
/*     */   }
/*     */   public final byte a() {
/* 116 */     byte b1 = 0;
/* 117 */     if (this.jdField_a_of_type_Byte == 0) {
/* 118 */       b1 = 2;
/*     */     }
/* 120 */     if (this.jdField_a_of_type_Byte == 15) {
/* 121 */       b1 = (byte)(b1 + 1);
/*     */     }
/* 123 */     if (this.b == 0) {
/* 124 */       b1 = (byte)(b1 + 8);
/*     */     }
/* 126 */     if (this.b == 15) {
/* 127 */       b1 = (byte)(b1 + 4);
/*     */     }
/* 129 */     if (this.c == 0) {
/* 130 */       b1 = (byte)(b1 + 32);
/*     */     }
/* 132 */     if (this.c == 15) {
/* 133 */       b1 = (byte)(b1 + 16);
/*     */     }
/* 135 */     return b1;
/*     */   }
/*     */ 
/*     */   public final byte[] a() {
/* 139 */     return this.jdField_a_of_type_ArrayOfByte;
/*     */   }
/*     */   public final int a() {
/* 142 */     return (short)ByteUtil.a(ByteUtil.a(this.jdField_a_of_type_ArrayOfByte, 0), 11, 20);
/*     */   }
/*     */ 
/*     */   public final int b()
/*     */   {
/* 147 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final byte b() {
/* 151 */     return (byte)ByteUtil.a(ByteUtil.a(this.jdField_a_of_type_ArrayOfByte, 0), 21, 24);
/*     */   }
/*     */   public final o a(o paramo) {
/* 154 */     paramo.b(this.jdField_a_of_type_Byte, this.b, this.c);
/* 155 */     return paramo;
/*     */   }
/*     */   public final Segment a() {
/* 158 */     return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment;
/*     */   }
/*     */   public final void a(Transform paramTransform) {
/* 161 */     paramTransform.set(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a().getWorldTransform());
/* 162 */     Object localObject = a(new q());
/* 163 */     localObject = new Vector3f(((q)localObject).jdField_a_of_type_Int - 8, ((q)localObject).b - 8, ((q)localObject).c - 8);
/* 164 */     paramTransform.basis.transform((Tuple3f)localObject);
/* 165 */     paramTransform.origin.add((Tuple3f)localObject);
/*     */   }
/*     */   public final short a() {
/* 168 */     return (short)ByteUtil.a(ByteUtil.a(this.jdField_a_of_type_ArrayOfByte, 0), 0, 11);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 175 */     le localle = this; long l = 7L + localle.jdField_a_of_type_Byte; l = 7L * l + localle.b;
/*     */     long tmp45_44 = (7L * l + localle.c); return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a.hashCode() + (byte)(int)(tmp45_44 ^ tmp45_44 >> 8);
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 183 */     return ByteUtil.a(ByteUtil.a(this.jdField_a_of_type_ArrayOfByte, 0), 20, 21) == 0;
/*     */   }
/*     */   public final boolean b() {
/* 186 */     return (this.jdField_a_of_type_Byte == 0) || (this.b == 0) || (this.c == 0) || (this.jdField_a_of_type_Byte == 15) || (this.b == 15) || (this.c == 15);
/*     */   }
/*     */   public final void a(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3) {
/* 189 */     this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
/* 190 */     le localle = this; this.jdField_a_of_type_Byte = paramByte1; localle.b = paramByte2; localle.c = paramByte3;
/* 191 */     if ((paramSegment.g()) || (paramSegment.a() == null))
/*     */     {
/* 193 */       Arrays.fill(this.jdField_a_of_type_ArrayOfByte, (byte)0); return;
/*     */     }
/* 195 */     this.jdField_a_of_type_ArrayOfByte = paramSegment.a().getSegmentPieceData(SegmentData.getInfoIndex(paramByte1, paramByte2, paramByte3), this.jdField_a_of_type_ArrayOfByte);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 211 */     a(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment, this.jdField_a_of_type_Byte, this.b, this.c);
/*     */   }
/*     */ 
/*     */   public final void a(boolean paramBoolean)
/*     */   {
/* 219 */     ByteUtil.a(this.jdField_a_of_type_ArrayOfByte, paramBoolean ? 0 : 1, 20, 21, 0);
/*     */   }
/*     */ 
/*     */   public final void a(byte[] paramArrayOfByte)
/*     */   {
/* 225 */     this.jdField_a_of_type_ArrayOfByte = paramArrayOfByte;
/*     */   }
/*     */   public final void a(int paramInt) {
/* 228 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public final void a(o paramo)
/*     */   {
/* 240 */     this.jdField_a_of_type_Byte = paramo.jdField_a_of_type_Byte;
/* 241 */     this.b = paramo.b;
/* 242 */     this.c = paramo.c;
/*     */   }
/*     */ 
/*     */   public final void a(Segment paramSegment)
/*     */   {
/* 248 */     this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 255 */     return a(new q()).toString() + "[" + (a() != 0 ? ElementKeyMap.getInfo(a()).getName() : "NONE") + "]o[" + Element.getSideString(Element.orientationBackMapping[b()]) + "]";
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  26 */     new q();
/*  27 */     new q();
/*  28 */     new Vector3f();
/*  29 */     new Vector3f();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     le
 * JD-Core Version:    0.6.2
 */