/*     */ package paulscode.sound.libraries;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.LinkedList;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.openal.AL10;
/*     */ import paulscode.sound.Channel;
/*     */ import paulscode.sound.SoundBuffer;
/*     */ import paulscode.sound.Source;
/*     */ 
/*     */ public class ChannelLWJGLOpenAL extends Channel
/*     */ {
/*     */   public IntBuffer ALSource;
/*     */   public int ALformat;
/*     */   public int sampleRate;
/* 112 */   public float millisPreviouslyPlayed = 0.0F;
/*     */ 
/*     */   public ChannelLWJGLOpenAL(int type, IntBuffer src)
/*     */   {
/* 124 */     super(type);
/* 125 */     this.libraryType = LibraryLWJGLOpenAL.class;
/* 126 */     this.ALSource = src;
/*     */   }
/*     */ 
/*     */   public void cleanup()
/*     */   {
/* 136 */     if (this.ALSource != null)
/*     */     {
/*     */       try
/*     */       {
/* 141 */         AL10.alSourceStop(this.ALSource);
/* 142 */         AL10.alGetError();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */       try
/*     */       {
/* 149 */         AL10.alDeleteSources(this.ALSource);
/* 150 */         AL10.alGetError();
/*     */       }
/*     */       catch (Exception localException1) {
/*     */       }
/* 154 */       this.ALSource.clear();
/*     */     }
/* 156 */     this.ALSource = null;
/*     */ 
/* 158 */     super.cleanup();
/*     */   }
/*     */ 
/*     */   public boolean attachBuffer(IntBuffer buf)
/*     */   {
/* 172 */     if (errorCheck(this.channelType != 0, 
/* 171 */       "Sound buffers may only be attached to normal sources."))
/*     */     {
/* 173 */       return false;
/*     */     }
/*     */ 
/* 176 */     AL10.alSourcei(this.ALSource.get(0), 4105, 
/* 177 */       buf.get(0));
/*     */ 
/* 181 */     if ((this.attachedSource != null) && (this.attachedSource.soundBuffer != null) && 
/* 182 */       (this.attachedSource.soundBuffer.audioFormat != null)) {
/* 183 */       setAudioFormat(this.attachedSource.soundBuffer.audioFormat);
/*     */     }
/*     */ 
/* 186 */     return checkALError();
/*     */   }
/*     */ 
/*     */   public void setAudioFormat(AudioFormat audioFormat)
/*     */   {
/* 195 */     int soundFormat = 0;
/* 196 */     if (audioFormat.getChannels() == 1)
/*     */     {
/* 198 */       if (audioFormat.getSampleSizeInBits() == 8)
/*     */       {
/* 200 */         soundFormat = 4352;
/*     */       }
/* 202 */       else if (audioFormat.getSampleSizeInBits() == 16)
/*     */       {
/* 204 */         soundFormat = 4353;
/*     */       }
/*     */       else
/*     */       {
/* 208 */         errorMessage("Illegal sample size in method 'setAudioFormat'");
/*     */       }
/*     */ 
/*     */     }
/* 213 */     else if (audioFormat.getChannels() == 2)
/*     */     {
/* 215 */       if (audioFormat.getSampleSizeInBits() == 8)
/*     */       {
/* 217 */         soundFormat = 4354;
/*     */       }
/* 219 */       else if (audioFormat.getSampleSizeInBits() == 16)
/*     */       {
/* 221 */         soundFormat = 4355;
/*     */       }
/*     */       else
/*     */       {
/* 225 */         errorMessage("Illegal sample size in method 'setAudioFormat'");
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 232 */       errorMessage("Audio data neither mono nor stereo in method 'setAudioFormat'");
/*     */ 
/* 234 */       return;
/*     */     }
/* 236 */     this.ALformat = soundFormat;
/* 237 */     this.sampleRate = ((int)audioFormat.getSampleRate());
/*     */   }
/*     */ 
/*     */   public void setFormat(int format, int rate)
/*     */   {
/* 247 */     this.ALformat = format;
/* 248 */     this.sampleRate = rate;
/*     */   }
/*     */ 
/*     */   public boolean preLoadBuffers(LinkedList<byte[]> bufferList)
/*     */   {
/* 261 */     if (errorCheck(this.channelType != 1, 
/* 261 */       "Buffers may only be queued for streaming sources.")) {
/* 262 */       return false;
/*     */     }
/*     */ 
/* 265 */     if (errorCheck(bufferList == null, 
/* 265 */       "Buffer List null in method 'preLoadBuffers'")) {
/* 266 */       return false;
/*     */     }
/*     */ 
/* 271 */     boolean playing = playing();
/*     */ 
/* 273 */     if (playing)
/*     */     {
/* 275 */       AL10.alSourceStop(this.ALSource.get(0));
/* 276 */       checkALError();
/*     */     }
/*     */ 
/* 279 */     int processed = AL10.alGetSourcei(this.ALSource.get(0), 
/* 280 */       4118);
/* 281 */     if (processed > 0)
/*     */     {
/* 283 */       IntBuffer streamBuffers = BufferUtils.createIntBuffer(processed);
/* 284 */       AL10.alGenBuffers(streamBuffers);
/*     */ 
/* 286 */       if (errorCheck(checkALError(), 
/* 286 */         "Error clearing stream buffers in method 'preLoadBuffers'"))
/* 287 */         return false;
/* 288 */       AL10.alSourceUnqueueBuffers(this.ALSource.get(0), streamBuffers);
/*     */ 
/* 290 */       if (errorCheck(checkALError(), 
/* 290 */         "Error unqueuing stream buffers in method 'preLoadBuffers'")) {
/* 291 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 295 */     if (playing)
/*     */     {
/* 297 */       AL10.alSourcePlay(this.ALSource.get(0));
/* 298 */       checkALError();
/*     */     }
/*     */ 
/* 301 */     IntBuffer streamBuffers = BufferUtils.createIntBuffer(bufferList.size());
/* 302 */     AL10.alGenBuffers(streamBuffers);
/*     */ 
/* 304 */     if (errorCheck(checkALError(), 
/* 304 */       "Error generating stream buffers in method 'preLoadBuffers'")) {
/* 305 */       return false;
/*     */     }
/* 307 */     ByteBuffer byteBuffer = null;
/* 308 */     for (int i = 0; i < bufferList.size(); i++)
/*     */     {
/* 312 */       byteBuffer = (ByteBuffer)BufferUtils.createByteBuffer(
/* 313 */         ((byte[])bufferList.get(i)).length).put((byte[])bufferList.get(i)).flip();
/*     */       try
/*     */       {
/* 317 */         AL10.alBufferData(streamBuffers.get(i), this.ALformat, byteBuffer, 
/* 318 */           this.sampleRate);
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 322 */         errorMessage("Error creating buffers in method 'preLoadBuffers'");
/*     */ 
/* 324 */         printStackTrace(e);
/* 325 */         return false;
/*     */       }
/*     */ 
/* 328 */       if (errorCheck(checkALError(), 
/* 328 */         "Error creating buffers in method 'preLoadBuffers'")) {
/* 329 */         return false;
/*     */       }
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 335 */       AL10.alSourceQueueBuffers(this.ALSource.get(0), streamBuffers);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 339 */       errorMessage("Error queuing buffers in method 'preLoadBuffers'");
/* 340 */       printStackTrace(e);
/* 341 */       return false;
/*     */     }
/*     */ 
/* 344 */     if (errorCheck(checkALError(), 
/* 344 */       "Error queuing buffers in method 'preLoadBuffers'")) {
/* 345 */       return false;
/*     */     }
/* 347 */     AL10.alSourcePlay(this.ALSource.get(0));
/*     */ 
/* 349 */     if (errorCheck(checkALError(), 
/* 349 */       "Error playing source in method 'preLoadBuffers'")) {
/* 350 */       return false;
/*     */     }
/*     */ 
/* 353 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean queueBuffer(byte[] buffer)
/*     */   {
/* 366 */     if (errorCheck(this.channelType != 1, 
/* 366 */       "Buffers may only be queued for streaming sources.")) {
/* 367 */       return false;
/*     */     }
/*     */ 
/* 370 */     ByteBuffer byteBuffer = (ByteBuffer)BufferUtils.createByteBuffer(
/* 371 */       buffer.length).put(buffer).flip();
/*     */ 
/* 373 */     IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
/*     */ 
/* 375 */     AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer);
/* 376 */     if (checkALError()) {
/* 377 */       return false;
/*     */     }
/* 379 */     if (AL10.alIsBuffer(intBuffer.get(0)))
/* 380 */       this.millisPreviouslyPlayed += millisInBuffer(intBuffer.get(0));
/* 381 */     checkALError();
/*     */ 
/* 383 */     AL10.alBufferData(intBuffer.get(0), this.ALformat, byteBuffer, this.sampleRate);
/* 384 */     if (checkALError()) {
/* 385 */       return false;
/*     */     }
/* 387 */     AL10.alSourceQueueBuffers(this.ALSource.get(0), intBuffer);
/* 388 */     if (checkALError()) {
/* 389 */       return false;
/*     */     }
/* 391 */     return true;
/*     */   }
/*     */ 
/*     */   public int feedRawAudioData(byte[] buffer)
/*     */   {
/* 404 */     if (errorCheck(this.channelType != 1, 
/* 404 */       "Raw audio data can only be fed to streaming sources.")) {
/* 405 */       return -1;
/*     */     }
/*     */ 
/* 408 */     ByteBuffer byteBuffer = (ByteBuffer)BufferUtils.createByteBuffer(
/* 409 */       buffer.length).put(buffer).flip();
/*     */ 
/* 414 */     int processed = AL10.alGetSourcei(this.ALSource.get(0), 
/* 415 */       4118);
/* 416 */     if (processed > 0)
/*     */     {
/* 418 */       IntBuffer intBuffer = BufferUtils.createIntBuffer(processed);
/* 419 */       AL10.alGenBuffers(intBuffer);
/*     */ 
/* 421 */       if (errorCheck(checkALError(), 
/* 421 */         "Error clearing stream buffers in method 'feedRawAudioData'"))
/* 422 */         return -1;
/* 423 */       AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer);
/*     */ 
/* 425 */       if (errorCheck(checkALError(), 
/* 425 */         "Error unqueuing stream buffers in method 'feedRawAudioData'")) {
/* 426 */         return -1;
/*     */       }
/* 428 */       intBuffer.rewind();
/* 429 */       while (intBuffer.hasRemaining())
/*     */       {
/* 431 */         int i = intBuffer.get();
/* 432 */         if (AL10.alIsBuffer(i))
/*     */         {
/* 434 */           this.millisPreviouslyPlayed += millisInBuffer(i);
/*     */         }
/* 436 */         checkALError();
/*     */       }
/* 438 */       AL10.alDeleteBuffers(intBuffer);
/* 439 */       checkALError();
/*     */     }
/* 441 */     IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
/* 442 */     AL10.alGenBuffers(intBuffer);
/*     */ 
/* 444 */     if (errorCheck(checkALError(), 
/* 444 */       "Error generating stream buffers in method 'preLoadBuffers'")) {
/* 445 */       return -1;
/*     */     }
/* 447 */     AL10.alBufferData(intBuffer.get(0), this.ALformat, byteBuffer, this.sampleRate);
/* 448 */     if (checkALError()) {
/* 449 */       return -1;
/*     */     }
/* 451 */     AL10.alSourceQueueBuffers(this.ALSource.get(0), intBuffer);
/* 452 */     if (checkALError()) {
/* 453 */       return -1;
/*     */     }
/* 455 */     if ((this.attachedSource != null) && (this.attachedSource.channel == this) && 
/* 456 */       (this.attachedSource.active()))
/*     */     {
/* 459 */       if (!playing())
/*     */       {
/* 461 */         AL10.alSourcePlay(this.ALSource.get(0));
/* 462 */         checkALError();
/*     */       }
/*     */     }
/*     */ 
/* 466 */     return processed;
/*     */   }
/*     */ 
/*     */   public float millisInBuffer(int alBufferi)
/*     */   {
/* 475 */     return AL10.alGetBufferi(alBufferi, 8196) / 
/* 476 */       AL10.alGetBufferi(alBufferi, 8195) / (
/* 477 */       AL10.alGetBufferi(alBufferi, 8194) / 8.0F) / 
/* 478 */       this.sampleRate * 1000.0F;
/*     */   }
/*     */ 
/*     */   public float millisecondsPlayed()
/*     */   {
/* 489 */     float offset = AL10.alGetSourcei(this.ALSource.get(0), 
/* 490 */       4134);
/*     */ 
/* 492 */     float bytesPerFrame = 1.0F;
/* 493 */     switch (this.ALformat)
/*     */     {
/*     */     case 4352:
/* 496 */       bytesPerFrame = 1.0F;
/* 497 */       break;
/*     */     case 4353:
/* 499 */       bytesPerFrame = 2.0F;
/* 500 */       break;
/*     */     case 4354:
/* 502 */       bytesPerFrame = 2.0F;
/* 503 */       break;
/*     */     case 4355:
/* 505 */       bytesPerFrame = 4.0F;
/* 506 */       break;
/*     */     }
/*     */ 
/* 511 */     offset = offset / bytesPerFrame / this.sampleRate * 
/* 512 */       1000.0F;
/*     */ 
/* 515 */     if (this.channelType == 1) {
/* 516 */       offset += this.millisPreviouslyPlayed;
/*     */     }
/*     */ 
/* 519 */     return offset;
/*     */   }
/*     */ 
/*     */   public int buffersProcessed()
/*     */   {
/* 530 */     if (this.channelType != 1) {
/* 531 */       return 0;
/*     */     }
/*     */ 
/* 534 */     int processed = AL10.alGetSourcei(this.ALSource.get(0), 
/* 535 */       4118);
/*     */ 
/* 538 */     if (checkALError()) {
/* 539 */       return 0;
/*     */     }
/*     */ 
/* 542 */     return processed;
/*     */   }
/*     */ 
/*     */   public void flush()
/*     */   {
/* 553 */     if (this.channelType != 1) {
/* 554 */       return;
/*     */     }
/*     */ 
/* 557 */     int queued = AL10.alGetSourcei(this.ALSource.get(0), 
/* 558 */       4117);
/*     */ 
/* 560 */     if (checkALError()) {
/* 561 */       return;
/*     */     }
/* 563 */     IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
/* 564 */     while (queued > 0)
/*     */     {
/*     */       try
/*     */       {
/* 568 */         AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer);
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 572 */         return;
/*     */       }
/* 574 */       if (checkALError())
/* 575 */         return;
/* 576 */       queued--;
/*     */     }
/* 578 */     this.millisPreviouslyPlayed = 0.0F;
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/*     */     try
/*     */     {
/* 589 */       AL10.alSourceStop(this.ALSource.get(0));
/* 590 */       AL10.alGetError();
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/* 595 */     if (this.channelType == 1)
/* 596 */       flush();
/*     */   }
/*     */ 
/*     */   public void play()
/*     */   {
/* 606 */     AL10.alSourcePlay(this.ALSource.get(0));
/* 607 */     checkALError();
/*     */   }
/*     */ 
/*     */   public void pause()
/*     */   {
/* 616 */     AL10.alSourcePause(this.ALSource.get(0));
/* 617 */     checkALError();
/*     */   }
/*     */ 
/*     */   public void stop()
/*     */   {
/* 627 */     AL10.alSourceStop(this.ALSource.get(0));
/* 628 */     if (!checkALError())
/* 629 */       this.millisPreviouslyPlayed = 0.0F;
/*     */   }
/*     */ 
/*     */   public void rewind()
/*     */   {
/* 640 */     if (this.channelType == 1) {
/* 641 */       return;
/*     */     }
/* 643 */     AL10.alSourceRewind(this.ALSource.get(0));
/* 644 */     if (!checkALError())
/* 645 */       this.millisPreviouslyPlayed = 0.0F;
/*     */   }
/*     */ 
/*     */   public boolean playing()
/*     */   {
/* 658 */     int state = AL10.alGetSourcei(this.ALSource.get(0), 
/* 659 */       4112);
/* 660 */     if (checkALError()) {
/* 661 */       return false;
/*     */     }
/* 663 */     return state == 4114;
/*     */   }
/*     */ 
/*     */   private boolean checkALError()
/*     */   {
/* 672 */     switch (AL10.alGetError())
/*     */     {
/*     */     case 0:
/* 675 */       return false;
/*     */     case 40961:
/* 677 */       errorMessage("Invalid name parameter.");
/* 678 */       return true;
/*     */     case 40962:
/* 680 */       errorMessage("Invalid parameter.");
/* 681 */       return true;
/*     */     case 40963:
/* 683 */       errorMessage("Invalid enumerated parameter value.");
/* 684 */       return true;
/*     */     case 40964:
/* 686 */       errorMessage("Illegal call.");
/* 687 */       return true;
/*     */     case 40965:
/* 689 */       errorMessage("Unable to allocate memory.");
/* 690 */       return true;
/*     */     }
/* 692 */     errorMessage("An unrecognized error occurred.");
/* 693 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.libraries.ChannelLWJGLOpenAL
 * JD-Core Version:    0.6.2
 */