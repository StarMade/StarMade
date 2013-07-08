/*  1:   */package org.newdawn.slick.util;
/*  2:   */
/*  3:   */import java.lang.reflect.InvocationTargetException;
/*  4:   */import java.lang.reflect.Method;
/*  5:   */import java.nio.ByteBuffer;
/*  6:   */import org.lwjgl.BufferUtils;
/*  7:   */
/* 21:   */public class GlUtil
/* 22:   */{
/* 23:   */  public static void destroyDirectByteBuffer(ByteBuffer toBeDestroyed)
/* 24:   */    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException
/* 25:   */  {
/* 26:26 */    Method cleanerMethod = toBeDestroyed.getClass().getMethod("cleaner", new Class[0]);
/* 27:27 */    cleanerMethod.setAccessible(true);
/* 28:28 */    Object cleaner = cleanerMethod.invoke(toBeDestroyed, new Object[0]);
/* 29:29 */    Method cleanMethod = cleaner.getClass().getMethod("clean", new Class[0]);
/* 30:30 */    cleanMethod.setAccessible(true);
/* 31:31 */    cleanMethod.invoke(cleaner, new Object[0]);
/* 32:   */  }
/* 33:   */  
/* 35:35 */  public static ByteBuffer getDynamicByteBuffer(int size) { return getDynamicByteBuffer(size, 0); }
/* 36:   */  
/* 37:37 */  private static ByteBuffer[] dynamicByteBuffer = new ByteBuffer[1];
/* 38:   */  
/* 39:39 */  public static ByteBuffer getDynamicByteBuffer(int size, int index) { ByteBuffer dynamicByteBuffer = dynamicByteBuffer[index];
/* 40:40 */    if ((dynamicByteBuffer == null) || (dynamicByteBuffer.capacity() < size)) {
/* 41:41 */      if (dynamicByteBuffer != null)
/* 42:   */      {
/* 43:   */        try {
/* 44:44 */          destroyDirectByteBuffer(dynamicByteBuffer);
/* 45:   */        } catch (IllegalArgumentException e) {
/* 46:46 */          e.printStackTrace();
/* 47:   */        } catch (SecurityException e) {
/* 48:48 */          e.printStackTrace();
/* 49:   */        } catch (IllegalAccessException e) {
/* 50:50 */          e.printStackTrace();
/* 51:   */        } catch (InvocationTargetException e) {
/* 52:52 */          e.printStackTrace();
/* 53:   */        } catch (NoSuchMethodException e) {
/* 54:54 */          e.printStackTrace();
/* 55:   */        }
/* 56:56 */        dynamicByteBuffer = null;
/* 57:57 */        System.gc();
/* 58:   */      }
/* 59:   */      
/* 60:60 */      dynamicByteBuffer = BufferUtils.createByteBuffer(size);
/* 61:   */    }
/* 62:62 */    dynamicByteBuffer.limit(size);
/* 63:63 */    dynamicByteBuffer.rewind();
/* 64:64 */    return dynamicByteBuffer;
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.GlUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */