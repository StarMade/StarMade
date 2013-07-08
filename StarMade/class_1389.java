import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.PNGImageData;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.core.ResourceException;

public class class_1389
{
  private HashMap jdField_field_1587_of_type_JavaUtilHashMap = new HashMap();
  private ColorModel jdField_field_1587_of_type_JavaAwtImageColorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 8 }, true, false, 3, 0);
  private ColorModel field_1588 = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 0 }, false, false, 1, 0);
  
  private static ByteBuffer a(BufferedImage paramBufferedImage)
  {
    byte[] arrayOfByte;
    (paramBufferedImage = GlUtil.a5((arrayOfByte = ((DataBufferByte)paramBufferedImage.getRaster().getDataBuffer()).getData()).length, 0)).order(ByteOrder.nativeOrder());
    paramBufferedImage.put(arrayOfByte, 0, arrayOfByte.length);
    paramBufferedImage.flip();
    return paramBufferedImage;
  }
  
  private BufferedImage a1(BufferedImage paramBufferedImage)
  {
    int i = 2;
    int j = 2;
    while (i < paramBufferedImage.getWidth()) {
      i <<= 1;
    }
    while (j < paramBufferedImage.getHeight()) {
      j <<= 1;
    }
    Object localObject;
    if (paramBufferedImage.getColorModel().hasAlpha())
    {
      localObject = Raster.createInterleavedRaster(0, i, j, 4, null);
      localObject = new BufferedImage(this.jdField_field_1587_of_type_JavaAwtImageColorModel, (WritableRaster)localObject, false, new Hashtable());
    }
    else
    {
      localObject = Raster.createInterleavedRaster(0, i, j, 3, null);
      localObject = new BufferedImage(this.field_1588, (WritableRaster)localObject, false, new Hashtable());
    }
    Graphics localGraphics;
    (localGraphics = ((BufferedImage)localObject).getGraphics()).setColor(new Color(0.0F, 0.0F, 0.0F, 0.0F));
    localGraphics.fillRect(0, 0, i, j);
    localGraphics.drawImage(paramBufferedImage, 0, 0, null);
    return localObject;
  }
  
  private static int a2()
  {
    GL11.glGenTextures(GlUtil.a8());
    return GlUtil.a8().get(0);
  }
  
  private static int a3(int paramInt)
  {
    int i = 2;
    while (i < paramInt) {
      i <<= 1;
    }
    return i;
  }
  
  public final class_1395 a4(String paramString1, String paramString2)
  {
    GL13.glActiveTexture(33984);
    GL11.glGenTextures(GlUtil.a8());
    int i = GlUtil.a8().get(0);
    GL11.glEnable(34067);
    GL11.glBindTexture(34067, i);
    String[] arrayOfString = { "posx", "negx", "posy", "negy", "posz", "negz" };
    int[] arrayOfInt = { 34069, 34070, 34071, 34072, 34073, 34074 };
    class_1395 localclass_1395 = new class_1395(i);
    for (int j = 0; j < 6; j++)
    {
      BufferedImage localBufferedImage;
      (localBufferedImage = a8(paramString1 + "_" + arrayOfString[j] + "." + paramString2)).getWidth();
      localBufferedImage.getHeight();
      ByteBuffer localByteBuffer;
      (localByteBuffer = a(localBufferedImage = a1(localBufferedImage))).rewind();
      localclass_1395.field_1591 = localBufferedImage.getWidth();
      localclass_1395.field_1590 = localBufferedImage.getHeight();
      GL11.glTexImage2D(arrayOfInt[j], 0, 6408, localBufferedImage.getWidth(), localBufferedImage.getHeight(), 0, 6408, 5121, localByteBuffer);
    }
    GL11.glTexParameterf(34067, 10240, 9729.0F);
    GL11.glTexParameterf(34067, 10241, 9729.0F);
    GL11.glTexParameterf(34067, 10242, 33071.0F);
    GL11.glTexParameterf(34067, 10243, 33071.0F);
    GL11.glTexParameterf(34067, 32882, 33071.0F);
    GL11.glBindTexture(34067, 0);
    GL11.glDisable(34067);
    return localclass_1395;
  }
  
  private class_1395 a5(BufferedImage paramBufferedImage, String paramString, boolean paramBoolean)
  {
    int i = a2();
    class_1395 localclass_1395 = new class_1395(i);
    if (FastMath.a7(paramBufferedImage.getWidth())) {
      FastMath.a7(paramBufferedImage.getHeight());
    }
    paramBufferedImage.getWidth();
    paramBufferedImage.getHeight();
    ByteBuffer localByteBuffer;
    (localByteBuffer = a(paramBufferedImage = a1(paramBufferedImage))).rewind();
    GL11.glBindTexture(3553, i);
    if ((!jdField_field_1587_of_type_Boolean) && (paramBufferedImage == null)) {
      throw new AssertionError();
    }
    localclass_1395.field_1591 = paramBufferedImage.getWidth();
    localclass_1395.field_1590 = paramBufferedImage.getHeight();
    if (paramBufferedImage.getColorModel().hasAlpha()) {
      i = 6408;
    } else {
      i = 6407;
    }
    a9(paramBufferedImage.getWidth(), paramBufferedImage.getHeight(), i, localByteBuffer, paramBoolean, paramString);
    GlUtil.f1();
    return localclass_1395;
  }
  
  public final class_1395 a6(String paramString, boolean paramBoolean)
  {
    class_1395 localclass_1395;
    if ((localclass_1395 = (class_1395)this.jdField_field_1587_of_type_JavaUtilHashMap.get(paramString)) != null) {
      return localclass_1395;
    }
    try
    {
      localclass_1395 = b(paramString, paramBoolean);
    }
    catch (ResourceException localResourceException)
    {
      localResourceException;
    }
    return localclass_1395;
  }
  
  public final class_1395 a7(String paramString)
  {
    class_1395 localclass_1395;
    if ((localclass_1395 = (class_1395)this.jdField_field_1587_of_type_JavaUtilHashMap.get(paramString)) != null) {
      return localclass_1395;
    }
    try
    {
      localclass_1395 = b(paramString, true);
    }
    catch (ResourceException localResourceException)
    {
      localResourceException;
    }
    return localclass_1395;
  }
  
  private class_1395 b(String paramString, boolean paramBoolean)
  {
    if (paramString.toLowerCase(Locale.ENGLISH).endsWith(".png"))
    {
      (localObject = new PNGImageData()).loadImage(class_73.field_134.a2(paramString));
      boolean bool = paramBoolean;
      paramString = paramString;
      localObject = localObject;
      paramBoolean = a2();
      class_1395 localclass_1395 = new class_1395(paramBoolean);
      if (((FastMath.a7(((PNGImageData)localObject).getWidth())) && (FastMath.a7(((PNGImageData)localObject).getHeight())) ? 1 : 0) == 0) {
        if (!jdField_field_1587_of_type_Boolean) {
          throw new AssertionError();
        }
      }
      ((PNGImageData)localObject).getWidth();
      ((PNGImageData)localObject).getHeight();
      ByteBuffer localByteBuffer;
      (localByteBuffer = ((PNGImageData)localObject).getImageBufferData()).rewind();
      GL11.glBindTexture(3553, paramBoolean);
      localclass_1395.field_1591 = ((PNGImageData)localObject).getWidth();
      localclass_1395.field_1590 = ((PNGImageData)localObject).getHeight();
      if (((PNGImageData)localObject).getDepth() == 32) {
        paramBoolean = true;
      } else {
        paramBoolean = true;
      }
      a9(((PNGImageData)localObject).getWidth(), ((PNGImageData)localObject).getHeight(), paramBoolean, localByteBuffer, bool, paramString);
      GlUtil.f1();
      return localclass_1395;
    }
    Object localObject = a8(paramString);
    if ((!jdField_field_1587_of_type_Boolean) && (localObject == null)) {
      throw new AssertionError("cannot load " + paramString);
    }
    return a5((BufferedImage)localObject, paramString, paramBoolean);
  }
  
  private static BufferedImage a8(String paramString)
  {
    paramString = class_73.field_134.a2(paramString);
    BufferedInputStream localBufferedInputStream;
    BufferedImage localBufferedImage = ImageIO.read(localBufferedInputStream = new BufferedInputStream(paramString));
    paramString.close();
    localBufferedInputStream.close();
    return localBufferedImage;
  }
  
  private static void a9(int paramInt1, int paramInt2, int paramInt3, ByteBuffer paramByteBuffer, boolean paramBoolean, String paramString)
  {
    
    if (!paramBoolean)
    {
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, class_949.field_1251.b1() ? 9729 : 9728);
      GL11.glTexParameteri(3553, 33085, 0);
      GL11.glTexParameteri(3553, 33169, 0);
    }
    else
    {
      GL11.glTexParameteri(3553, 33085, 3);
      GL11.glTexParameteri(3553, 10241, 9987);
      if (paramString.contains("/textures/block/")) {
        GL11.glTexParameteri(3553, 10240, class_949.field_1250.b1() ? 9729 : 9728);
      } else {
        GL11.glTexParameteri(3553, 10240, 9729);
      }
      GL11.glTexParameteri(3553, 33169, 1);
    }
    GlUtil.f1();
    GL11.glTexImage2D(3553, 0, 6408, a3(paramInt1), a3(paramInt2), 0, paramInt3, 5121, paramByteBuffer);
    GlUtil.f1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1389
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */