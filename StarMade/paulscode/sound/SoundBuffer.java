/*  1:   */package paulscode.sound;
/*  2:   */
/*  3:   */import javax.sound.sampled.AudioFormat;
/*  4:   */
/* 53:   */public class SoundBuffer
/* 54:   */{
/* 55:   */  public byte[] audioData;
/* 56:   */  public AudioFormat audioFormat;
/* 57:   */  
/* 58:   */  public SoundBuffer(byte[] audioData, AudioFormat audioFormat)
/* 59:   */  {
/* 60:60 */    this.audioData = audioData;
/* 61:61 */    this.audioFormat = audioFormat;
/* 62:   */  }
/* 63:   */  
/* 67:   */  public void cleanup()
/* 68:   */  {
/* 69:69 */    this.audioData = null;
/* 70:70 */    this.audioFormat = null;
/* 71:   */  }
/* 72:   */  
/* 79:   */  public void trimData(int maxLength)
/* 80:   */  {
/* 81:81 */    if ((this.audioData == null) || (maxLength == 0)) {
/* 82:82 */      this.audioData = null;
/* 83:83 */    } else if (this.audioData.length > maxLength)
/* 84:   */    {
/* 85:85 */      byte[] trimmedArray = new byte[maxLength];
/* 86:86 */      System.arraycopy(this.audioData, 0, trimmedArray, 0, maxLength);
/* 87:   */      
/* 88:88 */      this.audioData = trimmedArray;
/* 89:   */    }
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SoundBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */