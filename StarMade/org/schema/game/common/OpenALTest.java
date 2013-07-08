package org.schema.game.common;

import class_39;
import class_897;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Scanner;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.class_1434;
import org.lwjgl.util.WaveData;
import org.schema.schine.graphicsengine.core.ResourceException;

public class OpenALTest
{
  private IntBuffer jdField_field_492_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private IntBuffer jdField_field_493_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private FloatBuffer jdField_field_492_of_type_JavaNioFloatBuffer = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
  private FloatBuffer jdField_field_493_of_type_JavaNioFloatBuffer = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
  private FloatBuffer field_494 = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
  private FloatBuffer field_495 = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F }).rewind();
  private FloatBuffer field_496 = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F }).rewind();
  
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      class_897.a1();
      paramArrayOfString = new OpenALTest();
      try
      {
        class_1434.create();
      }
      catch (LWJGLException localLWJGLException)
      {
        localLWJGLException.printStackTrace();
        return;
      }
      System.err.println("ERROR? " + AL10.alGetError());
      if (paramArrayOfString.a() == 0)
      {
        System.out.println("Error loading data.");
        return;
      }
      String[] arrayOfString1 = paramArrayOfString;
      AL10.alListener(4100, arrayOfString1.field_494);
      AL10.alListener(4102, arrayOfString1.field_495);
      AL10.alListener(4111, arrayOfString1.field_496);
      System.out.println("OpenAL Tutorial 1 - Single Static Source");
      System.out.println("[Menu]");
      System.out.println("p - Play the sample.");
      System.out.println("s - Stop the sample.");
      System.out.println("h - Pause the sample.");
      System.out.println("q - Quit the program.");
      int i = 32;
      AL10.alSourcePlay(paramArrayOfString.jdField_field_493_of_type_JavaNioIntBuffer.get(0));
      Scanner localScanner = new Scanner(System.in);
      while (i != 113)
      {
        try
        {
          System.out.print("Input: ");
          i = localScanner.nextLine().charAt(0);
        }
        catch (Exception localException1)
        {
          i = 113;
        }
        switch (i)
        {
        case 112: 
          System.err.println("PLAY");
          AL10.alSourcePlay(paramArrayOfString.jdField_field_493_of_type_JavaNioIntBuffer.get(0));
          break;
        case 115: 
          AL10.alSourceStop(paramArrayOfString.jdField_field_493_of_type_JavaNioIntBuffer.get(0));
          break;
        case 104: 
          AL10.alSourcePause(paramArrayOfString.jdField_field_493_of_type_JavaNioIntBuffer.get(0));
        }
      }
      String[] arrayOfString2 = paramArrayOfString;
      AL10.alDeleteSources(paramArrayOfString.jdField_field_493_of_type_JavaNioIntBuffer);
      AL10.alDeleteBuffers(arrayOfString2.jdField_field_492_of_type_JavaNioIntBuffer);
      class_1434.destroy();
      return;
    }
    catch (Exception localException2)
    {
      localException2;
    }
  }
  
  private int a()
  {
    AL10.alGenBuffers(this.jdField_field_492_of_type_JavaNioIntBuffer);
    if (AL10.alGetError() != 0) {
      return 0;
    }
    Object localObject = new File("./data/audio-resource/Explosions/FancyPants.wav");
    class_39 localclass_39 = new class_39();
    String str = ((File)localObject).getAbsolutePath();
    System.err.println("EXISTS: " + ((File)localObject).exists() + " -> " + str);
    localObject = null;
    try
    {
      localObject = WaveData.create(new BufferedInputStream(localclass_39.a2(str)));
    }
    catch (ResourceException localResourceException)
    {
      localResourceException;
    }
    System.err.println(localObject + " " + ((WaveData)localObject).format + ", " + ((WaveData)localObject).data + ", " + ((WaveData)localObject).samplerate);
    AL10.alBufferData(this.jdField_field_492_of_type_JavaNioIntBuffer.get(0), ((WaveData)localObject).format, ((WaveData)localObject).data, ((WaveData)localObject).samplerate);
    ((WaveData)localObject).dispose();
    AL10.alGenSources(this.jdField_field_493_of_type_JavaNioIntBuffer);
    if (AL10.alGetError() != 0) {
      return 0;
    }
    AL10.alSourcei(this.jdField_field_493_of_type_JavaNioIntBuffer.get(0), 4105, this.jdField_field_492_of_type_JavaNioIntBuffer.get(0));
    AL10.alSourcef(this.jdField_field_493_of_type_JavaNioIntBuffer.get(0), 4099, 1.0F);
    AL10.alSourcef(this.jdField_field_493_of_type_JavaNioIntBuffer.get(0), 4106, 1.0F);
    AL10.alSource(this.jdField_field_493_of_type_JavaNioIntBuffer.get(0), 4100, this.jdField_field_492_of_type_JavaNioFloatBuffer);
    AL10.alSource(this.jdField_field_493_of_type_JavaNioIntBuffer.get(0), 4102, this.jdField_field_493_of_type_JavaNioFloatBuffer);
    if (AL10.alGetError() == 0) {
      return 1;
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.OpenALTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */