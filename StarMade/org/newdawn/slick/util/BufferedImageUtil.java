/*   1:    */package org.newdawn.slick.util;
/*   2:    */
/*   3:    */import java.awt.Graphics2D;
/*   4:    */import java.awt.image.BufferedImage;
/*   5:    */import java.awt.image.ColorModel;
/*   6:    */import java.io.IOException;
/*   7:    */import java.nio.ByteBuffer;
/*   8:    */import org.newdawn.slick.opengl.ImageIOImageData;
/*   9:    */import org.newdawn.slick.opengl.InternalTextureLoader;
/*  10:    */import org.newdawn.slick.opengl.Texture;
/*  11:    */import org.newdawn.slick.opengl.TextureImpl;
/*  12:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  13:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  14:    */
/*  34:    */public class BufferedImageUtil
/*  35:    */{
/*  36:    */  public static Texture getTexture(String resourceName, BufferedImage resourceImage)
/*  37:    */    throws IOException
/*  38:    */  {
/*  39: 39 */    Texture tex = getTexture(resourceName, resourceImage, 3553, 6408, 9729, 9729);
/*  40:    */    
/*  45: 45 */    return tex;
/*  46:    */  }
/*  47:    */  
/*  58:    */  public static Texture getTexture(String resourceName, BufferedImage resourceImage, int filter)
/*  59:    */    throws IOException
/*  60:    */  {
/*  61: 61 */    Texture tex = getTexture(resourceName, resourceImage, 3553, 6408, filter, filter);
/*  62:    */    
/*  67: 67 */    return tex;
/*  68:    */  }
/*  69:    */  
/*  89:    */  public static Texture getTexture(String resourceName, BufferedImage resourceimage, int target, int dstPixelFormat, int minFilter, int magFilter)
/*  90:    */    throws IOException
/*  91:    */  {
/*  92: 92 */    ImageIOImageData data = new ImageIOImageData();int srcPixelFormat = 0;
/*  93:    */    
/*  95: 95 */    int textureID = InternalTextureLoader.createTextureID();
/*  96: 96 */    TextureImpl texture = new TextureImpl(resourceName, target, textureID);
/*  97:    */    
/*  99: 99 */    Renderer.get().glEnable(3553);
/* 100:    */    
/* 102:102 */    Renderer.get().glBindTexture(target, textureID);
/* 103:    */    
/* 104:104 */    BufferedImage bufferedImage = resourceimage;
/* 105:105 */    texture.setWidth(bufferedImage.getWidth());
/* 106:106 */    texture.setHeight(bufferedImage.getHeight());
/* 107:    */    
/* 108:108 */    if (bufferedImage.getColorModel().hasAlpha()) {
/* 109:109 */      srcPixelFormat = 6408;
/* 110:    */    } else {
/* 111:111 */      srcPixelFormat = 6407;
/* 112:    */    }
/* 113:    */    
/* 115:115 */    ByteBuffer textureBuffer = data.imageToByteBuffer(bufferedImage, false, false, null);
/* 116:116 */    texture.setTextureHeight(data.getTexHeight());
/* 117:117 */    texture.setTextureWidth(data.getTexWidth());
/* 118:118 */    texture.setAlpha(data.getDepth() == 32);
/* 119:    */    
/* 120:120 */    if (target == 3553) {
/* 121:121 */      Renderer.get().glTexParameteri(target, 10241, minFilter);
/* 122:122 */      Renderer.get().glTexParameteri(target, 10240, magFilter);
/* 123:    */      
/* 124:124 */      if (Renderer.get().canTextureMirrorClamp()) {
/* 125:125 */        Renderer.get().glTexParameteri(3553, 10242, 34627);
/* 126:126 */        Renderer.get().glTexParameteri(3553, 10243, 34627);
/* 127:    */      } else {
/* 128:128 */        Renderer.get().glTexParameteri(3553, 10242, 10496);
/* 129:129 */        Renderer.get().glTexParameteri(3553, 10243, 10496);
/* 130:    */      }
/* 131:    */    }
/* 132:    */    
/* 133:133 */    Renderer.get().glTexImage2D(target, 0, dstPixelFormat, texture.getTextureWidth(), texture.getTextureHeight(), 0, srcPixelFormat, 5121, textureBuffer);
/* 134:    */    
/* 143:143 */    return texture;
/* 144:    */  }
/* 145:    */  
/* 156:    */  private static void copyArea(BufferedImage image, int x, int y, int width, int height, int dx, int dy)
/* 157:    */  {
/* 158:158 */    Graphics2D g = (Graphics2D)image.getGraphics();
/* 159:    */    
/* 160:160 */    g.drawImage(image.getSubimage(x, y, width, height), x + dx, y + dy, null);
/* 161:    */  }
/* 162:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.BufferedImageUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */