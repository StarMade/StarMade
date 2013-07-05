/*     */ package paulscode.sound.libraries;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.LinkedList;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.openal.AL10;
/*     */ import paulscode.sound.Channel;
/*     */ import paulscode.sound.FilenameURL;
/*     */ import paulscode.sound.ICodec;
/*     */ import paulscode.sound.SoundBuffer;
/*     */ import paulscode.sound.SoundSystemConfig;
/*     */ import paulscode.sound.Source;
/*     */ import paulscode.sound.Vector3D;
/*     */ 
/*     */ public class SourceLWJGLOpenAL extends Source
/*     */ {
/*  97 */   private ChannelLWJGLOpenAL channelOpenAL = (ChannelLWJGLOpenAL)this.channel;
/*     */   private IntBuffer myBuffer;
/*     */   private FloatBuffer listenerPosition;
/*     */   private FloatBuffer sourcePosition;
/*     */   private FloatBuffer sourceVelocity;
/*     */ 
/*     */   public SourceLWJGLOpenAL(FloatBuffer listenerPosition, IntBuffer myBuffer, boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
/*     */   {
/* 145 */     super(priority, toStream, toLoop, sourcename, filenameURL, soundBuffer, 
/* 145 */       x, y, z, attModel, distOrRoll, temporary);
/* 146 */     if (this.codec != null)
/* 147 */       this.codec.reverseByteOrder(true);
/* 148 */     this.listenerPosition = listenerPosition;
/* 149 */     this.myBuffer = myBuffer;
/* 150 */     this.libraryType = LibraryLWJGLOpenAL.class;
/* 151 */     this.pitch = 1.0F;
/* 152 */     resetALInformation();
/*     */   }
/*     */ 
/*     */   public SourceLWJGLOpenAL(FloatBuffer listenerPosition, IntBuffer myBuffer, Source old, SoundBuffer soundBuffer)
/*     */   {
/* 165 */     super(old, soundBuffer);
/* 166 */     if (this.codec != null)
/* 167 */       this.codec.reverseByteOrder(true);
/* 168 */     this.listenerPosition = listenerPosition;
/* 169 */     this.myBuffer = myBuffer;
/* 170 */     this.libraryType = LibraryLWJGLOpenAL.class;
/* 171 */     this.pitch = 1.0F;
/* 172 */     resetALInformation();
/*     */   }
/*     */ 
/*     */   public SourceLWJGLOpenAL(FloatBuffer listenerPosition, AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
/*     */   {
/* 194 */     super(audioFormat, priority, sourcename, x, y, z, attModel, 
/* 194 */       distOrRoll);
/* 195 */     this.listenerPosition = listenerPosition;
/* 196 */     this.libraryType = LibraryLWJGLOpenAL.class;
/* 197 */     this.pitch = 1.0F;
/* 198 */     resetALInformation();
/*     */   }
/*     */ 
/*     */   public void cleanup()
/*     */   {
/* 208 */     super.cleanup();
/*     */   }
/*     */ 
/*     */   public void changeSource(FloatBuffer listenerPosition, IntBuffer myBuffer, boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
/*     */   {
/* 236 */     super.changeSource(priority, toStream, toLoop, sourcename, 
/* 237 */       filenameURL, soundBuffer, x, y, z, attModel, 
/* 238 */       distOrRoll, temporary);
/* 239 */     this.listenerPosition = listenerPosition;
/* 240 */     this.myBuffer = myBuffer;
/* 241 */     this.pitch = 1.0F;
/* 242 */     resetALInformation();
/*     */   }
/*     */ 
/*     */   public boolean incrementSoundSequence()
/*     */   {
/* 255 */     if (!this.toStream)
/*     */     {
/* 257 */       errorMessage("Method 'incrementSoundSequence' may only be used for streaming sources.");
/*     */ 
/* 259 */       return false;
/*     */     }
/* 261 */     synchronized (this.soundSequenceLock)
/*     */     {
/* 263 */       if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
/*     */       {
/* 265 */         this.filenameURL = ((FilenameURL)this.soundSequenceQueue.remove(0));
/* 266 */         if (this.codec != null)
/* 267 */           this.codec.cleanup();
/* 268 */         this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
/* 269 */         if (this.codec != null)
/*     */         {
/* 271 */           this.codec.reverseByteOrder(true);
/* 272 */           if (this.codec.getAudioFormat() == null) {
/* 273 */             this.codec.initialize(this.filenameURL.getURL());
/*     */           }
/* 275 */           AudioFormat audioFormat = this.codec.getAudioFormat();
/*     */ 
/* 277 */           if (audioFormat == null)
/*     */           {
/* 279 */             errorMessage("Audio Format null in method 'incrementSoundSequence'");
/*     */ 
/* 281 */             return false;
/*     */           }
/*     */ 
/* 284 */           int soundFormat = 0;
/* 285 */           if (audioFormat.getChannels() == 1)
/*     */           {
/* 287 */             if (audioFormat.getSampleSizeInBits() == 8)
/*     */             {
/* 289 */               soundFormat = 4352;
/*     */             }
/* 291 */             else if (audioFormat.getSampleSizeInBits() == 16)
/*     */             {
/* 293 */               soundFormat = 4353;
/*     */             }
/*     */             else
/*     */             {
/* 297 */               errorMessage("Illegal sample size in method 'incrementSoundSequence'");
/*     */ 
/* 299 */               return false;
/*     */             }
/*     */           }
/* 302 */           else if (audioFormat.getChannels() == 2)
/*     */           {
/* 304 */             if (audioFormat.getSampleSizeInBits() == 8)
/*     */             {
/* 306 */               soundFormat = 4354;
/*     */             }
/* 308 */             else if (audioFormat.getSampleSizeInBits() == 16)
/*     */             {
/* 310 */               soundFormat = 4355;
/*     */             }
/*     */             else
/*     */             {
/* 314 */               errorMessage("Illegal sample size in method 'incrementSoundSequence'");
/*     */ 
/* 316 */               return false;
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 321 */             errorMessage("Audio data neither mono nor stereo in method 'incrementSoundSequence'");
/*     */ 
/* 323 */             return false;
/*     */           }
/*     */ 
/* 327 */           this.channelOpenAL.setFormat(soundFormat, 
/* 328 */             (int)audioFormat.getSampleRate());
/* 329 */           this.preLoad = true;
/*     */         }
/* 331 */         return true;
/*     */       }
/*     */     }
/* 334 */     return false;
/*     */   }
/*     */ 
/*     */   public void listenerMoved()
/*     */   {
/* 343 */     positionChanged();
/*     */   }
/*     */ 
/*     */   public void setPosition(float x, float y, float z)
/*     */   {
/* 355 */     super.setPosition(x, y, z);
/*     */ 
/* 358 */     if (this.sourcePosition == null)
/* 359 */       resetALInformation();
/*     */     else {
/* 361 */       positionChanged();
/*     */     }
/*     */ 
/* 364 */     this.sourcePosition.put(0, x);
/* 365 */     this.sourcePosition.put(1, y);
/* 366 */     this.sourcePosition.put(2, z);
/*     */ 
/* 369 */     if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 370 */       (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/*     */     {
/* 373 */       AL10.alSource(this.channelOpenAL.ALSource.get(0), 4100, 
/* 374 */         this.sourcePosition);
/* 375 */       checkALError();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void positionChanged()
/*     */   {
/* 385 */     calculateDistance();
/* 386 */     calculateGain();
/*     */ 
/* 388 */     if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 389 */       (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/*     */     {
/* 391 */       AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 392 */         4106, this.gain * this.sourceVolume * 
/* 393 */         Math.abs(this.fadeOutGain) * 
/* 394 */         this.fadeInGain);
/* 395 */       checkALError();
/*     */     }
/* 397 */     checkPitch();
/*     */   }
/*     */ 
/*     */   private void checkPitch()
/*     */   {
/* 405 */     if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 406 */       (LibraryLWJGLOpenAL.alPitchSupported()) && (this.channelOpenAL != null) && 
/* 407 */       (this.channelOpenAL.ALSource != null))
/*     */     {
/* 409 */       AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 410 */         4099, this.pitch);
/* 411 */       checkALError();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setLooping(boolean lp)
/*     */   {
/* 422 */     super.setLooping(lp);
/*     */ 
/* 425 */     if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 426 */       (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/*     */     {
/* 428 */       if (lp)
/* 429 */         AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 
/* 430 */           4103, 1);
/*     */       else
/* 432 */         AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 
/* 433 */           4103, 0);
/* 434 */       checkALError();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setAttenuation(int model)
/*     */   {
/* 445 */     super.setAttenuation(model);
/*     */ 
/* 447 */     if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 448 */       (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/*     */     {
/* 451 */       if (model == 1)
/* 452 */         AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 453 */           4129, this.distOrRoll);
/*     */       else
/* 455 */         AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 456 */           4129, 0.0F);
/* 457 */       checkALError();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setDistOrRoll(float dr)
/*     */   {
/* 469 */     super.setDistOrRoll(dr);
/*     */ 
/* 471 */     if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 472 */       (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/*     */     {
/* 475 */       if (this.attModel == 1)
/* 476 */         AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 477 */           4129, dr);
/*     */       else
/* 479 */         AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 480 */           4129, 0.0F);
/* 481 */       checkALError();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setVelocity(float x, float y, float z)
/*     */   {
/* 494 */     super.setVelocity(x, y, z);
/*     */ 
/* 496 */     this.sourceVelocity = BufferUtils.createFloatBuffer(3).put(
/* 497 */       new float[] { x, y, z });
/* 498 */     this.sourceVelocity.flip();
/*     */ 
/* 500 */     if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 501 */       (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/*     */     {
/* 503 */       AL10.alSource(this.channelOpenAL.ALSource.get(0), 
/* 504 */         4102, this.sourceVelocity);
/* 505 */       checkALError();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setPitch(float value)
/*     */   {
/* 516 */     super.setPitch(value);
/* 517 */     checkPitch();
/*     */   }
/*     */ 
/*     */   public void play(Channel c)
/*     */   {
/* 527 */     if (!active())
/*     */     {
/* 529 */       if (this.toLoop)
/* 530 */         this.toPlay = true;
/* 531 */       return;
/*     */     }
/*     */ 
/* 534 */     if (c == null)
/*     */     {
/* 536 */       errorMessage("Unable to play source, because channel was null");
/* 537 */       return;
/*     */     }
/*     */ 
/* 540 */     boolean newChannel = this.channel != c;
/* 541 */     if ((this.channel != null) && (this.channel.attachedSource != this)) {
/* 542 */       newChannel = true;
/*     */     }
/* 544 */     boolean wasPaused = paused();
/*     */ 
/* 546 */     super.play(c);
/*     */ 
/* 548 */     this.channelOpenAL = ((ChannelLWJGLOpenAL)this.channel);
/*     */ 
/* 552 */     if (newChannel)
/*     */     {
/* 554 */       setPosition(this.position.x, this.position.y, this.position.z);
/* 555 */       checkPitch();
/*     */ 
/* 558 */       if ((this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/*     */       {
/* 560 */         if (LibraryLWJGLOpenAL.alPitchSupported())
/*     */         {
/* 562 */           AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 563 */             4099, this.pitch);
/* 564 */           checkALError();
/*     */         }
/* 566 */         AL10.alSource(this.channelOpenAL.ALSource.get(0), 
/* 567 */           4100, this.sourcePosition);
/* 568 */         checkALError();
/*     */ 
/* 570 */         AL10.alSource(this.channelOpenAL.ALSource.get(0), 
/* 571 */           4102, this.sourceVelocity);
/*     */ 
/* 573 */         checkALError();
/*     */ 
/* 575 */         if (this.attModel == 1)
/* 576 */           AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 577 */             4129, this.distOrRoll);
/*     */         else
/* 579 */           AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 580 */             4129, 0.0F);
/* 581 */         checkALError();
/*     */ 
/* 583 */         if ((this.toLoop) && (!this.toStream))
/* 584 */           AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 
/* 585 */             4103, 1);
/*     */         else
/* 587 */           AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 
/* 588 */             4103, 0);
/* 589 */         checkALError();
/*     */       }
/* 591 */       if (!this.toStream)
/*     */       {
/* 595 */         if (this.myBuffer == null)
/*     */         {
/* 597 */           errorMessage("No sound buffer to play");
/* 598 */           return;
/*     */         }
/*     */ 
/* 601 */         this.channelOpenAL.attachBuffer(this.myBuffer);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 606 */     if (!playing())
/*     */     {
/* 608 */       if ((this.toStream) && (!wasPaused))
/*     */       {
/* 610 */         if (this.codec == null)
/*     */         {
/* 612 */           errorMessage("Decoder null in method 'play'");
/* 613 */           return;
/*     */         }
/* 615 */         if (this.codec.getAudioFormat() == null) {
/* 616 */           this.codec.initialize(this.filenameURL.getURL());
/*     */         }
/* 618 */         AudioFormat audioFormat = this.codec.getAudioFormat();
/*     */ 
/* 620 */         if (audioFormat == null)
/*     */         {
/* 622 */           errorMessage("Audio Format null in method 'play'");
/* 623 */           return;
/*     */         }
/*     */ 
/* 626 */         int soundFormat = 0;
/* 627 */         if (audioFormat.getChannels() == 1)
/*     */         {
/* 629 */           if (audioFormat.getSampleSizeInBits() == 8)
/*     */           {
/* 631 */             soundFormat = 4352;
/*     */           }
/* 633 */           else if (audioFormat.getSampleSizeInBits() == 16)
/*     */           {
/* 635 */             soundFormat = 4353;
/*     */           }
/*     */           else
/*     */           {
/* 639 */             errorMessage("Illegal sample size in method 'play'");
/*     */           }
/*     */ 
/*     */         }
/* 643 */         else if (audioFormat.getChannels() == 2)
/*     */         {
/* 645 */           if (audioFormat.getSampleSizeInBits() == 8)
/*     */           {
/* 647 */             soundFormat = 4354;
/*     */           }
/* 649 */           else if (audioFormat.getSampleSizeInBits() == 16)
/*     */           {
/* 651 */             soundFormat = 4355;
/*     */           }
/*     */           else
/*     */           {
/* 655 */             errorMessage("Illegal sample size in method 'play'");
/*     */           }
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 661 */           errorMessage("Audio data neither mono nor stereo in method 'play'");
/*     */ 
/* 663 */           return;
/*     */         }
/*     */ 
/* 667 */         this.channelOpenAL.setFormat(soundFormat, 
/* 668 */           (int)audioFormat.getSampleRate());
/* 669 */         this.preLoad = true;
/*     */       }
/* 671 */       this.channel.play();
/* 672 */       if (this.pitch != 1.0F)
/* 673 */         checkPitch();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean preLoad()
/*     */   {
/* 684 */     if (this.codec == null) {
/* 685 */       return false;
/*     */     }
/* 687 */     this.codec.initialize(this.filenameURL.getURL());
/* 688 */     LinkedList preLoadBuffers = new LinkedList();
/* 689 */     for (int i = 0; i < SoundSystemConfig.getNumberStreamingBuffers(); i++)
/*     */     {
/* 691 */       this.soundBuffer = this.codec.read();
/*     */ 
/* 693 */       if ((this.soundBuffer == null) || (this.soundBuffer.audioData == null)) {
/*     */         break;
/*     */       }
/* 696 */       preLoadBuffers.add(this.soundBuffer.audioData);
/*     */     }
/* 698 */     positionChanged();
/*     */ 
/* 700 */     this.channel.preLoadBuffers(preLoadBuffers);
/*     */ 
/* 702 */     this.preLoad = false;
/* 703 */     return true;
/*     */   }
/*     */ 
/*     */   private void resetALInformation()
/*     */   {
/* 712 */     this.sourcePosition = BufferUtils.createFloatBuffer(3).put(
/* 713 */       new float[] { this.position.x, this.position.y, this.position.z });
/* 714 */     this.sourceVelocity = BufferUtils.createFloatBuffer(3).put(
/* 715 */       new float[] { this.velocity.x, this.velocity.y, this.velocity.z });
/*     */ 
/* 718 */     this.sourcePosition.flip();
/* 719 */     this.sourceVelocity.flip();
/*     */ 
/* 721 */     positionChanged();
/*     */   }
/*     */ 
/*     */   private void calculateDistance()
/*     */   {
/* 729 */     if (this.listenerPosition != null)
/*     */     {
/* 732 */       double dX = this.position.x - this.listenerPosition.get(0);
/* 733 */       double dY = this.position.y - this.listenerPosition.get(1);
/* 734 */       double dZ = this.position.z - this.listenerPosition.get(2);
/* 735 */       this.distanceFromListener = ((float)Math.sqrt(dX * dX + dY * dY + dZ * dZ));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void calculateGain()
/*     */   {
/* 746 */     if (this.attModel == 2)
/*     */     {
/* 748 */       if (this.distanceFromListener <= 0.0F)
/*     */       {
/* 750 */         this.gain = 1.0F;
/*     */       }
/* 752 */       else if (this.distanceFromListener >= this.distOrRoll)
/*     */       {
/* 754 */         this.gain = 0.0F;
/*     */       }
/*     */       else
/*     */       {
/* 758 */         this.gain = (1.0F - this.distanceFromListener / this.distOrRoll);
/*     */       }
/* 760 */       if (this.gain > 1.0F)
/* 761 */         this.gain = 1.0F;
/* 762 */       if (this.gain < 0.0F)
/* 763 */         this.gain = 0.0F;
/*     */     }
/*     */     else
/*     */     {
/* 767 */       this.gain = 1.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean checkALError()
/*     */   {
/* 777 */     switch (AL10.alGetError())
/*     */     {
/*     */     case 0:
/* 780 */       return false;
/*     */     case 40961:
/* 782 */       errorMessage("Invalid name parameter.");
/* 783 */       return true;
/*     */     case 40962:
/* 785 */       errorMessage("Invalid parameter.");
/* 786 */       return true;
/*     */     case 40963:
/* 788 */       errorMessage("Invalid enumerated parameter value.");
/* 789 */       return true;
/*     */     case 40964:
/* 791 */       errorMessage("Illegal call.");
/* 792 */       return true;
/*     */     case 40965:
/* 794 */       errorMessage("Unable to allocate memory.");
/* 795 */       return true;
/*     */     }
/* 797 */     errorMessage("An unrecognized error occurred.");
/* 798 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.libraries.SourceLWJGLOpenAL
 * JD-Core Version:    0.6.2
 */