/*   1:    */package org.schema.game.common;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.File;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.util.Scanner;
/*   7:    */import jc;
/*   8:    */import k;
/*   9:    */import org.lwjgl.BufferUtils;
/*  10:    */import org.lwjgl.LWJGLException;
/*  11:    */import org.lwjgl.openal.AL;
/*  12:    */import org.schema.schine.graphicsengine.core.ResourceException;
/*  13:    */
/*  14:    */public class OpenALTest
/*  15:    */{
/*  16:    */  public static void main(String[] paramArrayOfString)
/*  17:    */  {
/*  18:    */    try
/*  19:    */    {
/*  20: 20 */      jc.a();
/*  21: 21 */      paramArrayOfString = new OpenALTest(); try { AL.create(); } catch (LWJGLException localLWJGLException) { localLWJGLException.printStackTrace();return; } System.err.println("ERROR? " + org.lwjgl.openal.AL10.alGetError()); if (paramArrayOfString.a() == 0) { System.out.println("Error loading data.");return; } String[] arrayOfString1 = paramArrayOfString;org.lwjgl.openal.AL10.alListener(4100, arrayOfString1.c);org.lwjgl.openal.AL10.alListener(4102, arrayOfString1.d);org.lwjgl.openal.AL10.alListener(4111, arrayOfString1.e);System.out.println("OpenAL Tutorial 1 - Single Static Source");System.out.println("[Menu]");System.out.println("p - Play the sample.");System.out.println("s - Stop the sample.");System.out.println("h - Pause the sample.");System.out.println("q - Quit the program.");int i = 32;org.lwjgl.openal.AL10.alSourcePlay(paramArrayOfString.jdField_b_of_type_JavaNioIntBuffer.get(0)); for (Scanner localScanner = new Scanner(System.in); i != 113;) { try { System.out.print("Input: ");i = localScanner.nextLine().charAt(0); } catch (Exception localException1) { i = 113; } switch (i) {case 112:  System.err.println("PLAY");org.lwjgl.openal.AL10.alSourcePlay(paramArrayOfString.jdField_b_of_type_JavaNioIntBuffer.get(0));break; case 115:  org.lwjgl.openal.AL10.alSourceStop(paramArrayOfString.jdField_b_of_type_JavaNioIntBuffer.get(0));break; case 104:  org.lwjgl.openal.AL10.alSourcePause(paramArrayOfString.jdField_b_of_type_JavaNioIntBuffer.get(0)); } } String[] arrayOfString2 = paramArrayOfString;org.lwjgl.openal.AL10.alDeleteSources(paramArrayOfString.jdField_b_of_type_JavaNioIntBuffer);org.lwjgl.openal.AL10.alDeleteBuffers(arrayOfString2.jdField_a_of_type_JavaNioIntBuffer);AL.destroy(); return;
/*  22: 22 */    } catch (Exception localException2) { 
/*  23:    */      
/*  24: 24 */        localException2;
/*  25:    */    }
/*  26:    */  }
/*  27:    */  
/*  31: 29 */  private java.nio.IntBuffer jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  32:    */  
/*  34: 32 */  private java.nio.IntBuffer jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  35:    */  
/*  37: 35 */  private java.nio.FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = (java.nio.FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
/*  38:    */  
/*  40: 38 */  private java.nio.FloatBuffer jdField_b_of_type_JavaNioFloatBuffer = (java.nio.FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
/*  41:    */  
/*  43: 41 */  private java.nio.FloatBuffer c = (java.nio.FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
/*  44:    */  
/*  46: 44 */  private java.nio.FloatBuffer d = (java.nio.FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
/*  47:    */  
/*  49: 47 */  private java.nio.FloatBuffer e = (java.nio.FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F }).rewind();
/*  50:    */  
/* 120:    */  private int a()
/* 121:    */  {
/* 122:120 */    org.lwjgl.openal.AL10.alGenBuffers(this.jdField_a_of_type_JavaNioIntBuffer);
/* 123:    */    
/* 124:122 */    if (org.lwjgl.openal.AL10.alGetError() != 0) {
/* 125:123 */      return 0;
/* 126:    */    }
/* 127:    */    
/* 144:142 */    Object localObject = new File("./data/audio-resource/Explosions/FancyPants.wav");
/* 145:    */    
/* 146:144 */    k localk = new k();
/* 147:145 */    String str = ((File)localObject).getAbsolutePath();
/* 148:146 */    System.err.println("EXISTS: " + ((File)localObject).exists() + " -> " + str);
/* 149:147 */    localObject = null;
/* 150:    */    try {
/* 151:149 */      localObject = org.lwjgl.util.WaveData.create(new BufferedInputStream(localk.a(str)));
/* 152:150 */    } catch (ResourceException localResourceException) { 
/* 153:    */      
/* 154:152 */        localResourceException;
/* 155:    */    }
/* 156:    */    
/* 157:153 */    System.err.println(localObject + " " + ((org.lwjgl.util.WaveData)localObject).format + ", " + ((org.lwjgl.util.WaveData)localObject).data + ", " + ((org.lwjgl.util.WaveData)localObject).samplerate);
/* 158:    */    
/* 159:155 */    org.lwjgl.openal.AL10.alBufferData(this.jdField_a_of_type_JavaNioIntBuffer.get(0), ((org.lwjgl.util.WaveData)localObject).format, ((org.lwjgl.util.WaveData)localObject).data, ((org.lwjgl.util.WaveData)localObject).samplerate);
/* 160:156 */    ((org.lwjgl.util.WaveData)localObject).dispose();
/* 161:    */    
/* 163:159 */    org.lwjgl.openal.AL10.alGenSources(this.jdField_b_of_type_JavaNioIntBuffer);
/* 164:    */    
/* 165:161 */    if (org.lwjgl.openal.AL10.alGetError() != 0) {
/* 166:162 */      return 0;
/* 167:    */    }
/* 168:164 */    org.lwjgl.openal.AL10.alSourcei(this.jdField_b_of_type_JavaNioIntBuffer.get(0), 4105, this.jdField_a_of_type_JavaNioIntBuffer.get(0));
/* 169:165 */    org.lwjgl.openal.AL10.alSourcef(this.jdField_b_of_type_JavaNioIntBuffer.get(0), 4099, 1.0F);
/* 170:166 */    org.lwjgl.openal.AL10.alSourcef(this.jdField_b_of_type_JavaNioIntBuffer.get(0), 4106, 1.0F);
/* 171:167 */    org.lwjgl.openal.AL10.alSource(this.jdField_b_of_type_JavaNioIntBuffer.get(0), 4100, this.jdField_a_of_type_JavaNioFloatBuffer);
/* 172:168 */    org.lwjgl.openal.AL10.alSource(this.jdField_b_of_type_JavaNioIntBuffer.get(0), 4102, this.jdField_b_of_type_JavaNioFloatBuffer);
/* 173:    */    
/* 175:171 */    if (org.lwjgl.openal.AL10.alGetError() == 0) {
/* 176:172 */      return 1;
/* 177:    */    }
/* 178:174 */    return 0;
/* 179:    */  }
/* 180:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.OpenALTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */