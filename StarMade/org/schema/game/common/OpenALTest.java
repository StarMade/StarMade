/*     */ package org.schema.game.common;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.Scanner;
/*     */ import jc;
/*     */ import k;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.openal.AL;
/*     */ import org.lwjgl.openal.AL10;
/*     */ import org.lwjgl.util.WaveData;
/*     */ import org.schema.schine.graphicsengine.core.ResourceException;
/*     */ 
/*     */ public class OpenALTest
/*     */ {
/*  29 */   private IntBuffer jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*     */ 
/*  32 */   private IntBuffer jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*     */ 
/*  35 */   private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
/*     */ 
/*  38 */   private FloatBuffer jdField_b_of_type_JavaNioFloatBuffer = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
/*     */ 
/*  41 */   private FloatBuffer c = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
/*     */ 
/*  44 */   private FloatBuffer d = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
/*     */ 
/*  47 */   private FloatBuffer e = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F }).rewind();
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*     */     try
/*     */     {
/*  20 */       jc.a();
/*  21 */       paramArrayOfString = new OpenALTest();
/*     */       try { AL.create(); } catch (LWJGLException localLWJGLException) { localLWJGLException.printStackTrace(); return; } System.err.println("ERROR? " + AL10.alGetError()); if (paramArrayOfString.a() == 0) { System.out.println("Error loading data."); return; } String[] arrayOfString1 = paramArrayOfString; AL10.alListener(4100, arrayOfString1.c); AL10.alListener(4102, arrayOfString1.d); AL10.alListener(4111, arrayOfString1.e); System.out.println("OpenAL Tutorial 1 - Single Static Source"); System.out.println("[Menu]"); System.out.println("p - Play the sample."); System.out.println("s - Stop the sample."); System.out.println("h - Pause the sample."); System.out.println("q - Quit the program."); int i = 32; AL10.alSourcePlay(paramArrayOfString.jdField_b_of_type_JavaNioIntBuffer.get(0)); for (Scanner localScanner = new Scanner(System.in); i != 113; ) { try { System.out.print("Input: "); i = localScanner.nextLine().charAt(0); } catch (Exception localException1) { i = 113; } switch (i) { case 112:
/*  21 */           System.err.println("PLAY"); AL10.alSourcePlay(paramArrayOfString.jdField_b_of_type_JavaNioIntBuffer.get(0)); break;
/*     */         case 115:
/*  21 */           AL10.alSourceStop(paramArrayOfString.jdField_b_of_type_JavaNioIntBuffer.get(0)); break;
/*     */         case 104:
/*  21 */           AL10.alSourcePause(paramArrayOfString.jdField_b_of_type_JavaNioIntBuffer.get(0)); }  } String[] arrayOfString2 = paramArrayOfString; AL10.alDeleteSources(paramArrayOfString.jdField_b_of_type_JavaNioIntBuffer); AL10.alDeleteBuffers(arrayOfString2.jdField_a_of_type_JavaNioIntBuffer); AL.destroy();
/*     */       return; } catch (Exception localException2) { localException2.printStackTrace(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private int a()
/*     */   {
/* 120 */     AL10.alGenBuffers(this.jdField_a_of_type_JavaNioIntBuffer);
/*     */ 
/* 122 */     if (AL10.alGetError() != 0) {
/* 123 */       return 0;
/*     */     }
/*     */ 
/* 142 */     Object localObject = new File("./data/audio-resource/Explosions/FancyPants.wav");
/*     */ 
/* 144 */     k localk = new k();
/* 145 */     String str = ((File)localObject).getAbsolutePath();
/* 146 */     System.err.println("EXISTS: " + ((File)localObject).exists() + " -> " + str);
/* 147 */     localObject = null;
/*     */     try {
/* 149 */       localObject = WaveData.create(new BufferedInputStream(localk.a(str)));
/*     */     }
/*     */     catch (ResourceException localResourceException) {
/* 152 */       localResourceException.printStackTrace();
/*     */     }
/*     */ 
/* 153 */     System.err.println(localObject + " " + ((WaveData)localObject).format + ", " + ((WaveData)localObject).data + ", " + ((WaveData)localObject).samplerate);
/*     */ 
/* 155 */     AL10.alBufferData(this.jdField_a_of_type_JavaNioIntBuffer.get(0), ((WaveData)localObject).format, ((WaveData)localObject).data, ((WaveData)localObject).samplerate);
/* 156 */     ((WaveData)localObject).dispose();
/*     */ 
/* 159 */     AL10.alGenSources(this.jdField_b_of_type_JavaNioIntBuffer);
/*     */ 
/* 161 */     if (AL10.alGetError() != 0) {
/* 162 */       return 0;
/*     */     }
/* 164 */     AL10.alSourcei(this.jdField_b_of_type_JavaNioIntBuffer.get(0), 4105, this.jdField_a_of_type_JavaNioIntBuffer.get(0));
/* 165 */     AL10.alSourcef(this.jdField_b_of_type_JavaNioIntBuffer.get(0), 4099, 1.0F);
/* 166 */     AL10.alSourcef(this.jdField_b_of_type_JavaNioIntBuffer.get(0), 4106, 1.0F);
/* 167 */     AL10.alSource(this.jdField_b_of_type_JavaNioIntBuffer.get(0), 4100, this.jdField_a_of_type_JavaNioFloatBuffer);
/* 168 */     AL10.alSource(this.jdField_b_of_type_JavaNioIntBuffer.get(0), 4102, this.jdField_b_of_type_JavaNioFloatBuffer);
/*     */ 
/* 171 */     if (AL10.alGetError() == 0) {
/* 172 */       return 1;
/*     */     }
/* 174 */     return 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.OpenALTest
 * JD-Core Version:    0.6.2
 */