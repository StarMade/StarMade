/*  1:   */package org.lwjgl.openal;
/*  2:   */
/* 49:   */public final class Util
/* 50:   */{
/* 51:   */  public static void checkALCError(ALCdevice device)
/* 52:   */  {
/* 53:53 */    int err = ALC10.alcGetError(device);
/* 54:54 */    if (err != 0) {
/* 55:55 */      throw new OpenALException(ALC10.alcGetString(AL.getDevice(), err));
/* 56:   */    }
/* 57:   */  }
/* 58:   */  
/* 60:   */  public static void checkALError()
/* 61:   */  {
/* 62:62 */    int err = AL10.alGetError();
/* 63:63 */    if (err != 0) {
/* 64:64 */      throw new OpenALException(err);
/* 65:   */    }
/* 66:   */  }
/* 67:   */  
/* 70:   */  public static void checkALCValidDevice(ALCdevice device)
/* 71:   */  {
/* 72:72 */    if (!device.isValid()) {
/* 73:73 */      throw new OpenALException("Invalid device: " + device);
/* 74:   */    }
/* 75:   */  }
/* 76:   */  
/* 80:   */  public static void checkALCValidContext(ALCcontext context)
/* 81:   */  {
/* 82:82 */    if (!context.isValid()) {
/* 83:83 */      throw new OpenALException("Invalid context: " + context);
/* 84:   */    }
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */