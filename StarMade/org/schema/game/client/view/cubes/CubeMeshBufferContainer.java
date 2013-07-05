/*     */ package org.schema.game.client.view.cubes;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ 
/*     */ public class CubeMeshBufferContainer
/*     */ {
/*     */   public ShortBuffer a;
/*     */   public ByteBuffer a;
/*     */   public FloatBuffer a;
/*     */   public IntBuffer a;
/*     */   public ShortBuffer b;
/*     */   private ByteBuffer b;
/*     */   private ByteBuffer c;
/*     */   public static int a;
/*     */ 
/*     */   public CubeMeshBufferContainer()
/*     */   {
/*  25 */     this.jdField_a_of_type_JavaNioByteBuffer = null;
/*     */   }
/*     */ 
/*     */   public static int a(byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/*  20 */     return ((paramByte3 << 8) + (paramByte2 << 4) + paramByte1) * 3 * 24;
/*     */   }
/*     */ 
/*     */   public static CubeMeshBufferContainer a()
/*     */   {
/*     */     CubeMeshBufferContainer localCubeMeshBufferContainer1;
/*     */     CubeMeshBufferContainer localCubeMeshBufferContainer2;
/*  44 */     (localCubeMeshBufferContainer2 = localCubeMeshBufferContainer1 = new CubeMeshBufferContainer()).jdField_a_of_type_JavaNioIntBuffer = 
/*  44 */       BufferUtils.createIntBuffer(4096); localCubeMeshBufferContainer2.jdField_b_of_type_JavaNioShortBuffer = BufferUtils.createShortBuffer(4096); localCubeMeshBufferContainer2.jdField_b_of_type_JavaNioByteBuffer = BufferUtils.createByteBuffer(294912); localCubeMeshBufferContainer2.c = BufferUtils.createByteBuffer(4096); localCubeMeshBufferContainer2.jdField_a_of_type_JavaNioShortBuffer = BufferUtils.createShortBuffer(4096); localCubeMeshBufferContainer2.jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(49152 * jdField_a_of_type_Int); localCubeMeshBufferContainer2.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  45 */     return localCubeMeshBufferContainer1;
/*     */   }
/*     */   public static void main(String[] paramArrayOfString) {
/*  48 */     System.err.println("index 96 kb");
/*     */ 
/*  50 */     System.err.println("attrib 384 kb");
/*     */ 
/*  52 */     System.err.println("other " + 196608 * jdField_a_of_type_Int / 1024 + " kb");
/*     */ 
/*  57 */     paramArrayOfString = (float)Math.floor(8.53125D);
/*     */ 
/*  59 */     float f1 = (float)Math.floor((2184.0D - paramArrayOfString * 256.0D) / 16.0D);
/*  60 */     float f2 = 2184.0F - (f1 * 16.0F + paramArrayOfString * 256.0F);
/*     */ 
/*  62 */     System.err.println("2184.0: " + f2 + ", " + f1 + ", " + paramArrayOfString);
/*     */   }
/*     */ 
/*     */   public final byte a(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  82 */     return this.jdField_b_of_type_JavaNioByteBuffer.get(paramInt1 + paramInt2 * 3 + paramInt3);
/*     */   }
/*     */   public final byte a(byte paramByte1, byte paramByte2, byte paramByte3) {
/*  85 */     paramByte1 = paramByte1 + (paramByte2 << 4) + (paramByte3 << 8);
/*  86 */     return this.c.get(paramByte1);
/*     */   }
/*     */ 
/*     */   public final byte a(int paramInt)
/*     */   {
/*  96 */     return this.c.get(paramInt / 3);
/*     */   }
/*     */ 
/*     */   public final void a() {
/* 100 */     for (int i = 0; i < 4096; i++)
/* 101 */       this.c.put(i, (byte)0);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, byte paramByte, int paramInt2, int paramInt3) {
/* 105 */     this.jdField_b_of_type_JavaNioByteBuffer.put(paramInt1 + paramInt2 * 3 + paramInt3, paramByte);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, byte paramByte)
/*     */   {
/* 114 */     this.c.put(paramInt, paramByte);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  12 */     CubeMeshBufferContainer.class.desiredAssertionStatus();
/*     */ 
/*  41 */     jdField_a_of_type_Int = 2;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.client.view.cubes.CubeMeshBufferContainer
 * JD-Core Version:    0.6.2
 */