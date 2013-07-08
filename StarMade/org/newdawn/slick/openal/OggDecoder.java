/*   1:    */package org.newdawn.slick.openal;
/*   2:    */
/*   3:    */import java.io.ByteArrayOutputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStream;
/*   6:    */import java.nio.ByteBuffer;
/*   7:    */
/*  13:    */public class OggDecoder
/*  14:    */{
/*  15: 15 */  private int convsize = 16384;
/*  16:    */  
/*  17: 17 */  private byte[] convbuffer = new byte[this.convsize];
/*  18:    */  
/*  30:    */  public OggData getData(InputStream input)
/*  31:    */    throws IOException
/*  32:    */  {
/*  33: 33 */    if (input == null) {
/*  34: 34 */      throw new IOException("Failed to read OGG, source does not exist?");
/*  35:    */    }
/*  36: 36 */    ByteArrayOutputStream dataout = new ByteArrayOutputStream();
/*  37:    */    
/* 311:311 */    OggInputStream oggInput = new OggInputStream(input);
/* 312:    */    
/* 313:313 */    boolean done = false;
/* 314:314 */    while (!oggInput.atEnd()) {
/* 315:315 */      dataout.write(oggInput.read());
/* 316:    */    }
/* 317:    */    
/* 318:318 */    OggData ogg = new OggData();
/* 319:319 */    ogg.channels = oggInput.getChannels();
/* 320:320 */    ogg.rate = oggInput.getRate();
/* 321:    */    
/* 322:322 */    byte[] data = dataout.toByteArray();
/* 323:323 */    ogg.data = ByteBuffer.allocateDirect(data.length);
/* 324:324 */    ogg.data.put(data);
/* 325:325 */    ogg.data.rewind();
/* 326:    */    
/* 327:327 */    return ogg;
/* 328:    */  }
/* 329:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.OggDecoder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */