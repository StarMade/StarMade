package org.lwjgl.openal;

public final class Util
{
  public static void checkALCError(ALCdevice device)
  {
    int err = ALC10.alcGetError(device);
    if (err != 0) {
      throw new OpenALException(ALC10.alcGetString(class_1434.getDevice(), err));
    }
  }
  
  public static void checkALError()
  {
    int err = AL10.alGetError();
    if (err != 0) {
      throw new OpenALException(err);
    }
  }
  
  public static void checkALCValidDevice(ALCdevice device)
  {
    if (!device.isValid()) {
      throw new OpenALException("Invalid device: " + device);
    }
  }
  
  public static void checkALCValidContext(ALCcontext context)
  {
    if (!context.isValid()) {
      throw new OpenALException("Invalid context: " + context);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.openal.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */