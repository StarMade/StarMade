/*     */ public class o
/*     */ {
/*     */   public byte a;
/*     */   public byte b;
/*     */   public byte c;
/*     */ 
/*     */   public o()
/*     */   {
/*     */   }
/*     */ 
/*     */   public o(byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/*   9 */     this.a = paramByte1; this.b = paramByte2; this.c = paramByte3;
/*     */   }
/*     */   public o(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  12 */     this.a = ((byte)(int)paramFloat1); this.b = ((byte)(int)paramFloat2); this.c = ((byte)(int)paramFloat3);
/*     */   }
/*     */   public o(o paramo) {
/*  15 */     this.a = paramo.a;
/*  16 */     this.b = paramo.b;
/*  17 */     this.c = paramo.c;
/*     */   }
/*     */   public final void a(byte paramByte1, byte paramByte2, byte paramByte3) {
/*  20 */     this.a = ((byte)(this.a + paramByte1));
/*  21 */     this.b = ((byte)(this.b + paramByte2));
/*  22 */     this.c = ((byte)(this.c + paramByte3));
/*     */   }
/*     */ 
/*     */   public final void a(o paramo) {
/*  26 */     this.a = ((byte)(this.a + paramo.a));
/*  27 */     this.b = ((byte)(this.b + paramo.b));
/*  28 */     this.c = ((byte)(this.c + paramo.c));
/*     */   }
/*     */   public final void a() {
/*  31 */     this.a = ((byte)(this.a / 2));
/*  32 */     this.b = ((byte)(this.b / 2));
/*  33 */     this.c = ((byte)(this.c / 2));
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/*     */     try
/*     */     {
/*  47 */       paramObject = (o)paramObject;
/*  48 */       return (this.a == paramObject.a) && (this.b == paramObject.b) && (this.c == paramObject.c);
/*     */     } catch (NullPointerException localNullPointerException) {
/*  50 */       return false; } catch (ClassCastException localClassCastException) {
/*  51 */     }return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  63 */     long l = 7L + this.a;
/*     */ 
/*  65 */     l = 7L * l + this.b;
/*     */     long tmp33_32 = (7L * l + this.c);
/*  67 */     return (byte)(int)(tmp33_32 ^ tmp33_32 >> 
/*  67 */       8);
/*     */   }
/*     */ 
/*     */   public final void b(byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/*  84 */     this.a = paramByte1; this.b = paramByte2; this.c = paramByte3;
/*     */   }
/*     */   public final void b(o paramo) {
/*  87 */     b(paramo.a, paramo.b, paramo.c);
/*     */   }
/*     */ 
/*     */   public final void c(o paramo)
/*     */   {
/*  96 */     this.a = ((byte)(this.a - paramo.a));
/*  97 */     this.b = ((byte)(this.b - paramo.b));
/*  98 */     this.c = ((byte)(this.c - paramo.c));
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 103 */     return "(" + this.a + ", " + this.b + ", " + this.c + ")";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     o
 * JD-Core Version:    0.6.2
 */