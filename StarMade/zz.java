/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Locale;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.newdawn.slick.opengl.PNGImageData;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.core.ResourceException;
/*     */ 
/*     */ public class zz
/*     */ {
/* 280 */   private HashMap jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*     */   private ColorModel jdField_a_of_type_JavaAwtImageColorModel;
/*     */   private ColorModel b;
/*     */ 
/*     */   private static ByteBuffer a(BufferedImage paramBufferedImage)
/*     */   {
/*     */     byte[] arrayOfByte;
/* 214 */     (
/* 215 */       paramBufferedImage = GlUtil.a((
/* 214 */       arrayOfByte = ((DataBufferByte)paramBufferedImage.getRaster().getDataBuffer()).getData()).length, 
/* 214 */       0))
/* 215 */       .order(ByteOrder.nativeOrder());
/*     */ 
/* 217 */     paramBufferedImage.put(arrayOfByte, 0, arrayOfByte.length);
/* 218 */     paramBufferedImage.flip();
/*     */ 
/* 220 */     return paramBufferedImage;
/*     */   }
/*     */ 
/*     */   public zz()
/*     */   {
/* 294 */     this.jdField_a_of_type_JavaAwtImageColorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 8 }, true, false, 3, 0);
/*     */ 
/* 301 */     this.b = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 0 }, false, false, 1, 0);
/*     */   }
/*     */ 
/*     */   private BufferedImage a(BufferedImage paramBufferedImage)
/*     */   {
/* 310 */     int i = 2;
/* 311 */     int j = 2;
/*     */ 
/* 319 */     while (i < paramBufferedImage.getWidth()) {
/* 320 */       i <<= 1;
/*     */     }
/* 322 */     while (j < paramBufferedImage.getHeight())
/* 323 */       j <<= 1;
/*     */     Object localObject;
/* 332 */     if (paramBufferedImage.getColorModel().hasAlpha()) {
/* 333 */       localObject = Raster.createInterleavedRaster(0, i, j, 4, null);
/* 334 */       localObject = new BufferedImage(this.jdField_a_of_type_JavaAwtImageColorModel, (WritableRaster)localObject, false, new Hashtable());
/*     */     } else {
/* 336 */       localObject = Raster.createInterleavedRaster(0, i, j, 3, null);
/* 337 */       localObject = new BufferedImage(this.b, (WritableRaster)localObject, false, new Hashtable());
/*     */     }
/*     */     Graphics localGraphics;
/* 342 */     (
/* 343 */       localGraphics = ((BufferedImage)localObject).getGraphics())
/* 343 */       .setColor(new Color(0.0F, 0.0F, 0.0F, 0.0F));
/* 344 */     localGraphics.fillRect(0, 0, i, j);
/* 345 */     localGraphics.drawImage(paramBufferedImage, 0, 0, null);
/*     */ 
/* 347 */     return localObject;
/*     */   }
/*     */ 
/*     */   private static int a()
/*     */   {
/* 370 */     GL11.glGenTextures(GlUtil.a());
/* 371 */     return GlUtil.a().get(0);
/*     */   }
/*     */ 
/*     */   private static int a(int paramInt)
/*     */   {
/* 381 */     int i = 2;
/* 382 */     while (i < paramInt) {
/* 383 */       i <<= 1;
/*     */     }
/* 385 */     return i;
/*     */   }
/*     */ 
/*     */   public final zy a(String paramString1, String paramString2) {
/* 389 */     GL13.glActiveTexture(33984);
/* 390 */     GL11.glGenTextures(GlUtil.a());
/* 391 */     int i = GlUtil.a().get(0);
/* 392 */     GL11.glEnable(34067);
/* 393 */     GL11.glBindTexture(34067, i);
/*     */ 
/* 395 */     String[] arrayOfString = { "posx", "negx", "posy", "negy", "posz", "negz" };
/*     */ 
/* 398 */     int[] arrayOfInt = { 34069, 34070, 34071, 34072, 34073, 34074 };
/*     */ 
/* 407 */     zy localzy = new zy(i);
/* 408 */     for (int j = 0; j < 6; j++)
/*     */     {
/*     */       BufferedImage localBufferedImage;
/* 410 */       (
/* 415 */         localBufferedImage = a(paramString1 + "_" + arrayOfString[j] + "." + paramString2))
/* 415 */         .getWidth();
/* 416 */       localBufferedImage.getHeight();
/*     */       ByteBuffer localByteBuffer;
/* 420 */       (
/* 421 */         localByteBuffer = a(localBufferedImage = a(localBufferedImage)))
/* 421 */         .rewind();
/* 422 */       localzy.b = localBufferedImage.getWidth();
/* 423 */       localzy.a = localBufferedImage.getHeight();
/*     */ 
/* 425 */       GL11.glTexImage2D(arrayOfInt[j], 0, 6408, localBufferedImage.getWidth(), localBufferedImage.getHeight(), 0, 6408, 5121, localByteBuffer);
/*     */     }
/*     */ 
/* 430 */     GL11.glTexParameterf(34067, 10240, 9729.0F);
/*     */ 
/* 432 */     GL11.glTexParameterf(34067, 10241, 9729.0F);
/*     */ 
/* 434 */     GL11.glTexParameterf(34067, 10242, 33071.0F);
/*     */ 
/* 436 */     GL11.glTexParameterf(34067, 10243, 33071.0F);
/*     */ 
/* 438 */     GL11.glTexParameterf(34067, 32882, 33071.0F);
/*     */ 
/* 441 */     GL11.glBindTexture(34067, 0);
/* 442 */     GL11.glDisable(34067);
/* 443 */     return localzy;
/*     */   }
/*     */ 
/*     */   private zy a(BufferedImage paramBufferedImage, String paramString, boolean paramBoolean)
/*     */   {
/* 450 */     int i = a();
/*     */ 
/* 452 */     zy localzy = new zy(i);
/*     */ 
/* 454 */     if (FastMath.a(paramBufferedImage.getWidth())) FastMath.a(paramBufferedImage.getHeight());
/*     */ 
/* 456 */     paramBufferedImage.getWidth();
/*     */ 
/* 463 */     paramBufferedImage.getHeight();
/*     */     ByteBuffer localByteBuffer;
/* 468 */     (
/* 469 */       localByteBuffer = a(paramBufferedImage = a(paramBufferedImage)))
/* 469 */       .rewind();
/* 470 */     GL11.glBindTexture(3553, i);
/* 471 */     if ((!jdField_a_of_type_Boolean) && (paramBufferedImage == null)) throw new AssertionError();
/*     */ 
/* 474 */     localzy.b = paramBufferedImage.getWidth();
/* 475 */     localzy.a = paramBufferedImage.getHeight();
/*     */ 
/* 479 */     if (paramBufferedImage.getColorModel().hasAlpha())
/* 480 */       i = 6408;
/*     */     else {
/* 482 */       i = 6407;
/*     */     }
/*     */ 
/* 485 */     a(paramBufferedImage.getWidth(), paramBufferedImage.getHeight(), i, localByteBuffer, paramBoolean, paramString);
/*     */ 
/* 489 */     GlUtil.f();
/* 490 */     return localzy;
/*     */   }
/*     */ 
/*     */   public final zy a(String paramString, boolean paramBoolean)
/*     */   {
/*     */     zy localzy;
/* 550 */     if ((
/* 550 */       localzy = (zy)this.jdField_a_of_type_JavaUtilHashMap.get(paramString)) != null)
/*     */     {
/* 551 */       return localzy;
/*     */     }
/*     */     try
/*     */     {
/* 555 */       localzy = b(paramString, paramBoolean);
/*     */     }
/*     */     catch (ResourceException localResourceException)
/*     */     {
/* 566 */       localResourceException.printStackTrace();
/*     */     }
/*     */ 
/* 569 */     return localzy;
/*     */   }
/*     */ 
/*     */   public final zy a(String paramString)
/*     */   {
/*     */     zy localzy;
/* 575 */     if ((
/* 575 */       localzy = (zy)this.jdField_a_of_type_JavaUtilHashMap.get(paramString)) != null)
/*     */     {
/* 576 */       return localzy;
/*     */     }
/*     */     try
/*     */     {
/* 580 */       localzy = b(paramString, true);
/*     */     }
/*     */     catch (ResourceException localResourceException)
/*     */     {
/* 591 */       localResourceException.printStackTrace();
/*     */     }
/*     */ 
/* 593 */     return localzy;
/*     */   }
/*     */ 
/*     */   private zy b(String paramString, boolean paramBoolean)
/*     */   {
/* 619 */     if (paramString.toLowerCase(Locale.ENGLISH).endsWith(".png")) {
/* 620 */       (
/* 622 */         localObject = new PNGImageData())
/* 622 */         .loadImage(zZ.a.a(paramString));
/*     */ 
/* 625 */       boolean bool = paramBoolean; paramString = paramString; localObject = localObject; paramBoolean = a(); zy localzy = new zy(paramBoolean); if (((FastMath.a(((PNGImageData)localObject).getWidth())) && (FastMath.a(((PNGImageData)localObject).getHeight())) ? 1 : 0) == 0) if (!jdField_a_of_type_Boolean) throw new AssertionError(); 
/* 625 */       ((PNGImageData)localObject).getWidth(); ((PNGImageData)localObject).getHeight();
/*     */       ByteBuffer localByteBuffer;
/* 625 */       (localByteBuffer = ((PNGImageData)localObject).getImageBufferData()).rewind(); GL11.glBindTexture(3553, paramBoolean); localzy.b = ((PNGImageData)localObject).getWidth(); localzy.a = ((PNGImageData)localObject).getHeight(); if (((PNGImageData)localObject).getDepth() == 32) paramBoolean = true; else paramBoolean = true; a(((PNGImageData)localObject).getWidth(), ((PNGImageData)localObject).getHeight(), paramBoolean, localByteBuffer, bool, paramString); GlUtil.f();
/*     */ 
/* 627 */       return localzy;
/*     */     }
/*     */ 
/* 630 */     Object localObject = a(paramString);
/*     */ 
/* 632 */     if ((!jdField_a_of_type_Boolean) && (localObject == null)) throw new AssertionError("cannot load " + paramString);
/*     */ 
/* 639 */     return a((BufferedImage)localObject, paramString, paramBoolean);
/*     */   }
/*     */ 
/*     */   private static BufferedImage a(String paramString)
/*     */   {
/* 653 */     paramString = zZ.a.a(paramString);
/*     */     BufferedInputStream localBufferedInputStream;
/* 658 */     BufferedImage localBufferedImage = ImageIO.read(localBufferedInputStream = new BufferedInputStream(paramString));
/*     */ 
/* 660 */     paramString.close();
/* 661 */     localBufferedInputStream.close();
/* 662 */     return localBufferedImage;
/*     */   }
/*     */ 
/*     */   private static void a(int paramInt1, int paramInt2, int paramInt3, ByteBuffer paramByteBuffer, boolean paramBoolean, String paramString)
/*     */   {
/* 670 */     GlUtil.f();
/*     */ 
/* 672 */     if (!paramBoolean)
/*     */     {
/* 681 */       GL11.glTexParameteri(3553, 10241, 9729);
/*     */ 
/* 683 */       GL11.glTexParameteri(3553, 10240, xu.af.b() ? 9729 : 9728);
/*     */ 
/* 686 */       GL11.glTexParameteri(3553, 33085, 0);
/*     */ 
/* 688 */       GL11.glTexParameteri(3553, 33169, 0);
/*     */     }
/*     */     else
/*     */     {
/* 693 */       GL11.glTexParameteri(3553, 33085, 3);
/*     */ 
/* 696 */       GL11.glTexParameteri(3553, 10241, 9987);
/*     */ 
/* 700 */       if (paramString.contains("/textures/block/")) {
/* 701 */         GL11.glTexParameteri(3553, 10240, xu.ae.b() ? 9729 : 9728);
/*     */       }
/*     */       else {
/* 704 */         GL11.glTexParameteri(3553, 10240, 9729);
/*     */       }
/*     */ 
/* 714 */       GL11.glTexParameteri(3553, 33169, 1);
/*     */     }
/*     */ 
/* 720 */     GlUtil.f();
/*     */ 
/* 723 */     GL11.glTexImage2D(3553, 0, 6408, a(paramInt1), a(paramInt2), 0, paramInt3, 5121, paramByteBuffer);
/*     */ 
/* 729 */     GlUtil.f();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zz
 * JD-Core Version:    0.6.2
 */