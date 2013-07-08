/*  1:   */package de.jarnbjo.util.audio;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import javax.sound.sampled.AudioInputStream;
/*  5:   */
/*  6:   */public class FadeableAudioInputStream extends AudioInputStream
/*  7:   */{
/*  8:   */  private AudioInputStream stream;
/*  9: 9 */  private boolean fading = false;
/* 10:10 */  private double phi = 0.0D;
/* 11:   */  
/* 12:   */  public FadeableAudioInputStream(AudioInputStream stream) throws IOException {
/* 13:13 */    super(stream, stream.getFormat(), -1L);
/* 14:   */  }
/* 15:   */  
/* 16:   */  public void fadeOut() {
/* 17:17 */    this.fading = true;
/* 18:18 */    this.phi = 0.0D;
/* 19:   */  }
/* 20:   */  
/* 21:   */  public int read(byte[] b) throws IOException {
/* 22:22 */    return read(b, 0, b.length);
/* 23:   */  }
/* 24:   */  
/* 25:   */  public int read(byte[] b, int offset, int length) throws IOException {
/* 26:26 */    int read = super.read(b, offset, length);
/* 27:   */    
/* 30:30 */    if (this.fading) {
/* 31:31 */      int j = 0;int l = 0;int r = 0;
/* 32:32 */      double gain = 0.0D;
/* 33:   */      
/* 34:34 */      for (int i = offset; i < offset + read; i += 4) {
/* 35:35 */        j = i;
/* 36:36 */        l = b[(j++)] & 0xFF;
/* 37:37 */        l |= b[(j++)] << 8;
/* 38:38 */        r = b[(j++)] & 0xFF;
/* 39:39 */        r |= b[j] << 8;
/* 40:   */        
/* 41:41 */        if (this.phi < 1.570796326794897D) {
/* 42:42 */          this.phi += 1.5E-005D;
/* 43:   */        }
/* 44:   */        
/* 45:45 */        gain = Math.cos(this.phi);
/* 46:   */        
/* 48:48 */        l = (int)(l * gain);
/* 49:49 */        r = (int)(r * gain);
/* 50:   */        
/* 51:51 */        j = i;
/* 52:52 */        b[(j++)] = ((byte)(l & 0xFF));
/* 53:53 */        b[(j++)] = ((byte)(l >> 8 & 0xFF));
/* 54:54 */        b[(j++)] = ((byte)(r & 0xFF));
/* 55:55 */        b[(j++)] = ((byte)(r >> 8 & 0xFF));
/* 56:   */      }
/* 57:   */    }
/* 58:   */    
/* 59:59 */    return read;
/* 60:   */  }
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.util.audio.FadeableAudioInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */