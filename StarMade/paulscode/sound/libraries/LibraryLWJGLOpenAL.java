/*      */ package paulscode.sound.libraries;
/*      */ 
/*      */ import java.net.URL;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import javax.sound.sampled.AudioFormat;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.LWJGLException;
/*      */ import org.lwjgl.openal.AL;
/*      */ import org.lwjgl.openal.AL10;
/*      */ import paulscode.sound.Channel;
/*      */ import paulscode.sound.FilenameURL;
/*      */ import paulscode.sound.ICodec;
/*      */ import paulscode.sound.Library;
/*      */ import paulscode.sound.ListenerData;
/*      */ import paulscode.sound.SoundBuffer;
/*      */ import paulscode.sound.SoundSystemConfig;
/*      */ import paulscode.sound.SoundSystemException;
/*      */ import paulscode.sound.Source;
/*      */ import paulscode.sound.Vector3D;
/*      */ 
/*      */ public class LibraryLWJGLOpenAL extends Library
/*      */ {
/*      */   private static final boolean GET = false;
/*      */   private static final boolean SET = true;
/*      */   private static final boolean XXX = false;
/*  122 */   private FloatBuffer listenerPositionAL = null;
/*      */ 
/*  126 */   private FloatBuffer listenerOrientation = null;
/*      */ 
/*  130 */   private FloatBuffer listenerVelocity = null;
/*      */ 
/*  134 */   private HashMap<String, IntBuffer> ALBufferMap = null;
/*      */ 
/*  139 */   private static boolean alPitchSupported = true;
/*      */ 
/*      */   public LibraryLWJGLOpenAL()
/*      */     throws SoundSystemException
/*      */   {
/*  149 */     this.ALBufferMap = new HashMap();
/*  150 */     this.reverseByteOrder = true;
/*      */   }
/*      */ 
/*      */   public void init()
/*      */     throws SoundSystemException
/*      */   {
/*  159 */     boolean errors = false;
/*      */     try
/*      */     {
/*  164 */       AL.create();
/*  165 */       errors = checkALError();
/*      */     }
/*      */     catch (LWJGLException e)
/*      */     {
/*  170 */       errorMessage("Unable to initialize OpenAL.  Probable cause: OpenAL not supported.");
/*      */ 
/*  172 */       printStackTrace(e);
/*  173 */       throw new Exception(e.getMessage(), 
/*  174 */         101);
/*      */     }
/*      */ 
/*  178 */     if (errors)
/*  179 */       importantMessage("OpenAL did not initialize properly!");
/*      */     else {
/*  181 */       message("OpenAL initialized.");
/*      */     }
/*      */ 
/*  184 */     this.listenerPositionAL = BufferUtils.createFloatBuffer(3).put(
/*  185 */       new float[] { this.listener.position.x, 
/*  186 */       this.listener.position.y, 
/*  187 */       this.listener.position.z });
/*  188 */     this.listenerOrientation = BufferUtils.createFloatBuffer(6).put(
/*  189 */       new float[] { this.listener.lookAt.x, this.listener.lookAt.y, 
/*  190 */       this.listener.lookAt.z, this.listener.up.x, this.listener.up.y, 
/*  191 */       this.listener.up.z });
/*  192 */     this.listenerVelocity = BufferUtils.createFloatBuffer(3).put(
/*  193 */       new float[] { 0.0F, 0.0F, 0.0F });
/*      */ 
/*  196 */     this.listenerPositionAL.flip();
/*  197 */     this.listenerOrientation.flip();
/*  198 */     this.listenerVelocity.flip();
/*      */ 
/*  201 */     AL10.alListener(4100, this.listenerPositionAL);
/*  202 */     errors = (checkALError()) || (errors);
/*  203 */     AL10.alListener(4111, this.listenerOrientation);
/*  204 */     errors = (checkALError()) || (errors);
/*  205 */     AL10.alListener(4102, this.listenerVelocity);
/*  206 */     errors = (checkALError()) || (errors);
/*      */ 
/*  208 */     AL10.alDopplerFactor(SoundSystemConfig.getDopplerFactor());
/*  209 */     errors = (checkALError()) || (errors);
/*      */ 
/*  211 */     AL10.alDopplerVelocity(SoundSystemConfig.getDopplerVelocity());
/*  212 */     errors = (checkALError()) || (errors);
/*      */ 
/*  215 */     if (errors)
/*      */     {
/*  217 */       importantMessage("OpenAL did not initialize properly!");
/*  218 */       throw new Exception("Problem encountered while loading OpenAL or creating the listener.  Probable cause:  OpenAL not supported", 
/*  223 */         101);
/*      */     }
/*      */ 
/*  226 */     super.init();
/*      */ 
/*  229 */     ChannelLWJGLOpenAL channel = 
/*  230 */       (ChannelLWJGLOpenAL)this.normalChannels.get(1);
/*      */     try
/*      */     {
/*  233 */       AL10.alSourcef(channel.ALSource.get(0), 
/*  234 */         4099, 1.0F);
/*  235 */       if (checkALError())
/*      */       {
/*  237 */         alPitchSupported(true, false);
/*  238 */         throw new Exception("OpenAL: AL_PITCH not supported.", 
/*  239 */           108);
/*      */       }
/*      */ 
/*  243 */       alPitchSupported(true, true);
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  248 */       alPitchSupported(true, false);
/*  249 */       throw new Exception("OpenAL: AL_PITCH not supported.", 
/*  250 */         108);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean libraryCompatible()
/*      */   {
/*  260 */     if (AL.isCreated()) {
/*  261 */       return true;
/*      */     }
/*      */     try
/*      */     {
/*  265 */       AL.create();
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  269 */       return false;
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/*  274 */       AL.destroy();
/*      */     }
/*      */     catch (Exception localException1)
/*      */     {
/*      */     }
/*  279 */     return true;
/*      */   }
/*      */ 
/*      */   protected Channel createChannel(int type)
/*      */   {
/*  294 */     IntBuffer ALSource = BufferUtils.createIntBuffer(1);
/*      */     try
/*      */     {
/*  297 */       AL10.alGenSources(ALSource);
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  301 */       AL10.alGetError();
/*  302 */       return null;
/*      */     }
/*      */ 
/*  305 */     if (AL10.alGetError() != 0) {
/*  306 */       return null;
/*      */     }
/*  308 */     ChannelLWJGLOpenAL channel = new ChannelLWJGLOpenAL(type, ALSource);
/*  309 */     return channel;
/*      */   }
/*      */ 
/*      */   public void cleanup()
/*      */   {
/*  319 */     super.cleanup();
/*      */ 
/*  321 */     Set keys = this.bufferMap.keySet();
/*  322 */     Iterator iter = keys.iterator();
/*      */ 
/*  327 */     while (iter.hasNext())
/*      */     {
/*  329 */       String filename = (String)iter.next();
/*  330 */       IntBuffer buffer = (IntBuffer)this.ALBufferMap.get(filename);
/*  331 */       if (buffer != null)
/*      */       {
/*  333 */         AL10.alDeleteBuffers(buffer);
/*  334 */         checkALError();
/*  335 */         buffer.clear();
/*      */       }
/*      */     }
/*      */ 
/*  339 */     this.bufferMap.clear();
/*  340 */     AL.destroy();
/*      */ 
/*  342 */     this.bufferMap = null;
/*  343 */     this.listenerPositionAL = null;
/*  344 */     this.listenerOrientation = null;
/*  345 */     this.listenerVelocity = null;
/*      */   }
/*      */ 
/*      */   public boolean loadSound(FilenameURL filenameURL)
/*      */   {
/*  357 */     if (this.bufferMap == null)
/*      */     {
/*  359 */       this.bufferMap = new HashMap();
/*  360 */       importantMessage("Buffer Map was null in method 'loadSound'");
/*      */     }
/*      */ 
/*  363 */     if (this.ALBufferMap == null)
/*      */     {
/*  365 */       this.ALBufferMap = new HashMap();
/*  366 */       importantMessage("Open AL Buffer Map was null in method'loadSound'");
/*      */     }
/*      */ 
/*  372 */     if (errorCheck(filenameURL == null, 
/*  372 */       "Filename/URL not specified in method 'loadSound'")) {
/*  373 */       return false;
/*      */     }
/*      */ 
/*  376 */     if (this.bufferMap.get(filenameURL.getFilename()) != null) {
/*  377 */       return true;
/*      */     }
/*  379 */     ICodec codec = SoundSystemConfig.getCodec(filenameURL.getFilename());
/*      */ 
/*  382 */     if (errorCheck(codec == null, "No codec found for file '" + 
/*  381 */       filenameURL.getFilename() + 
/*  382 */       "' in method 'loadSound'"))
/*  383 */       return false;
/*  384 */     codec.reverseByteOrder(true);
/*      */ 
/*  386 */     URL url = filenameURL.getURL();
/*      */ 
/*  389 */     if (errorCheck(url == null, "Unable to open file '" + 
/*  388 */       filenameURL.getFilename() + 
/*  389 */       "' in method 'loadSound'")) {
/*  390 */       return false;
/*      */     }
/*  392 */     codec.initialize(url);
/*  393 */     SoundBuffer buffer = codec.readAll();
/*  394 */     codec.cleanup();
/*  395 */     codec = null;
/*      */ 
/*  397 */     if (errorCheck(buffer == null, 
/*  397 */       "Sound buffer null in method 'loadSound'")) {
/*  398 */       return false;
/*      */     }
/*  400 */     this.bufferMap.put(filenameURL.getFilename(), buffer);
/*      */ 
/*  402 */     AudioFormat audioFormat = buffer.audioFormat;
/*  403 */     int soundFormat = 0;
/*  404 */     if (audioFormat.getChannels() == 1)
/*      */     {
/*  406 */       if (audioFormat.getSampleSizeInBits() == 8)
/*      */       {
/*  408 */         soundFormat = 4352;
/*      */       }
/*  410 */       else if (audioFormat.getSampleSizeInBits() == 16)
/*      */       {
/*  412 */         soundFormat = 4353;
/*      */       }
/*      */       else
/*      */       {
/*  416 */         errorMessage("Illegal sample size in method 'loadSound'");
/*  417 */         return false;
/*      */       }
/*      */     }
/*  420 */     else if (audioFormat.getChannels() == 2)
/*      */     {
/*  422 */       if (audioFormat.getSampleSizeInBits() == 8)
/*      */       {
/*  424 */         soundFormat = 4354;
/*      */       }
/*  426 */       else if (audioFormat.getSampleSizeInBits() == 16)
/*      */       {
/*  428 */         soundFormat = 4355;
/*      */       }
/*      */       else
/*      */       {
/*  432 */         errorMessage("Illegal sample size in method 'loadSound'");
/*  433 */         return false;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  438 */       errorMessage("File neither mono nor stereo in method 'loadSound'");
/*      */ 
/*  440 */       return false;
/*      */     }
/*      */ 
/*  443 */     IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
/*  444 */     AL10.alGenBuffers(intBuffer);
/*      */ 
/*  447 */     if (errorCheck(AL10.alGetError() != 0, 
/*  446 */       "alGenBuffers error when loading " + 
/*  447 */       filenameURL.getFilename())) {
/*  448 */       return false;
/*      */     }
/*      */ 
/*  453 */     AL10.alBufferData(intBuffer.get(0), soundFormat, 
/*  454 */       (ByteBuffer)BufferUtils.createByteBuffer(
/*  455 */       buffer.audioData.length).put(
/*  456 */       buffer.audioData).flip(), 
/*  457 */       (int)audioFormat.getSampleRate());
/*      */ 
/*  461 */     if (errorCheck(AL10.alGetError() != 0, 
/*  460 */       "alBufferData error when loading " + 
/*  461 */       filenameURL.getFilename()))
/*      */     {
/*  466 */       if (errorCheck(intBuffer == null, 
/*  465 */         "Sound buffer was not created for " + 
/*  466 */         filenameURL.getFilename()))
/*  467 */         return false;
/*      */     }
/*  469 */     this.ALBufferMap.put(filenameURL.getFilename(), intBuffer);
/*      */ 
/*  471 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean loadSound(SoundBuffer buffer, String identifier)
/*      */   {
/*  486 */     if (this.bufferMap == null)
/*      */     {
/*  488 */       this.bufferMap = new HashMap();
/*  489 */       importantMessage("Buffer Map was null in method 'loadSound'");
/*      */     }
/*      */ 
/*  492 */     if (this.ALBufferMap == null)
/*      */     {
/*  494 */       this.ALBufferMap = new HashMap();
/*  495 */       importantMessage("Open AL Buffer Map was null in method'loadSound'");
/*      */     }
/*      */ 
/*  501 */     if (errorCheck(identifier == null, 
/*  501 */       "Identifier not specified in method 'loadSound'")) {
/*  502 */       return false;
/*      */     }
/*      */ 
/*  505 */     if (this.bufferMap.get(identifier) != null) {
/*  506 */       return true;
/*      */     }
/*      */ 
/*  509 */     if (errorCheck(buffer == null, 
/*  509 */       "Sound buffer null in method 'loadSound'")) {
/*  510 */       return false;
/*      */     }
/*  512 */     this.bufferMap.put(identifier, buffer);
/*      */ 
/*  514 */     AudioFormat audioFormat = buffer.audioFormat;
/*  515 */     int soundFormat = 0;
/*  516 */     if (audioFormat.getChannels() == 1)
/*      */     {
/*  518 */       if (audioFormat.getSampleSizeInBits() == 8)
/*      */       {
/*  520 */         soundFormat = 4352;
/*      */       }
/*  522 */       else if (audioFormat.getSampleSizeInBits() == 16)
/*      */       {
/*  524 */         soundFormat = 4353;
/*      */       }
/*      */       else
/*      */       {
/*  528 */         errorMessage("Illegal sample size in method 'loadSound'");
/*  529 */         return false;
/*      */       }
/*      */     }
/*  532 */     else if (audioFormat.getChannels() == 2)
/*      */     {
/*  534 */       if (audioFormat.getSampleSizeInBits() == 8)
/*      */       {
/*  536 */         soundFormat = 4354;
/*      */       }
/*  538 */       else if (audioFormat.getSampleSizeInBits() == 16)
/*      */       {
/*  540 */         soundFormat = 4355;
/*      */       }
/*      */       else
/*      */       {
/*  544 */         errorMessage("Illegal sample size in method 'loadSound'");
/*  545 */         return false;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  550 */       errorMessage("File neither mono nor stereo in method 'loadSound'");
/*      */ 
/*  552 */       return false;
/*      */     }
/*      */ 
/*  555 */     IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
/*  556 */     AL10.alGenBuffers(intBuffer);
/*      */ 
/*  559 */     if (errorCheck(AL10.alGetError() != 0, 
/*  558 */       "alGenBuffers error when saving " + 
/*  559 */       identifier)) {
/*  560 */       return false;
/*      */     }
/*      */ 
/*  565 */     AL10.alBufferData(intBuffer.get(0), soundFormat, 
/*  566 */       (ByteBuffer)BufferUtils.createByteBuffer(
/*  567 */       buffer.audioData.length).put(
/*  568 */       buffer.audioData).flip(), 
/*  569 */       (int)audioFormat.getSampleRate());
/*      */ 
/*  573 */     if (errorCheck(AL10.alGetError() != 0, 
/*  572 */       "alBufferData error when saving " + 
/*  573 */       identifier))
/*      */     {
/*  578 */       if (errorCheck(intBuffer == null, 
/*  577 */         "Sound buffer was not created for " + 
/*  578 */         identifier))
/*  579 */         return false;
/*      */     }
/*  581 */     this.ALBufferMap.put(identifier, intBuffer);
/*      */ 
/*  583 */     return true;
/*      */   }
/*      */ 
/*      */   public void unloadSound(String filename)
/*      */   {
/*  596 */     this.ALBufferMap.remove(filename);
/*  597 */     super.unloadSound(filename);
/*      */   }
/*      */ 
/*      */   public void setMasterVolume(float value)
/*      */   {
/*  607 */     super.setMasterVolume(value);
/*      */ 
/*  609 */     AL10.alListenerf(4106, value);
/*  610 */     checkALError();
/*      */   }
/*      */ 
/*      */   public void newSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x, float y, float z, int attModel, float distOrRoll)
/*      */   {
/*  631 */     IntBuffer myBuffer = null;
/*  632 */     if (!toStream)
/*      */     {
/*  635 */       myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
/*      */ 
/*  638 */       if (myBuffer == null)
/*      */       {
/*  640 */         if (!loadSound(filenameURL))
/*      */         {
/*  642 */           errorMessage("Source '" + sourcename + "' was not created " + 
/*  643 */             "because an error occurred while loading " + 
/*  644 */             filenameURL.getFilename());
/*  645 */           return;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  650 */       myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
/*      */ 
/*  652 */       if (myBuffer == null)
/*      */       {
/*  654 */         errorMessage("Source '" + sourcename + "' was not created " + 
/*  655 */           "because a sound buffer was not found for " + 
/*  656 */           filenameURL.getFilename());
/*  657 */         return;
/*      */       }
/*      */     }
/*  660 */     SoundBuffer buffer = null;
/*      */ 
/*  662 */     if (!toStream)
/*      */     {
/*  665 */       buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
/*      */ 
/*  667 */       if (buffer == null)
/*      */       {
/*  669 */         if (!loadSound(filenameURL))
/*      */         {
/*  671 */           errorMessage("Source '" + sourcename + "' was not created " + 
/*  672 */             "because an error occurred while loading " + 
/*  673 */             filenameURL.getFilename());
/*  674 */           return;
/*      */         }
/*      */       }
/*      */ 
/*  678 */       buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
/*      */ 
/*  680 */       if (buffer == null)
/*      */       {
/*  682 */         errorMessage("Source '" + sourcename + "' was not created " + 
/*  683 */           "because audio data was not found for " + 
/*  684 */           filenameURL.getFilename());
/*  685 */         return;
/*      */       }
/*      */     }
/*      */ 
/*  689 */     this.sourceMap.put(sourcename, 
/*  690 */       new SourceLWJGLOpenAL(this.listenerPositionAL, myBuffer, 
/*  691 */       priority, toStream, toLoop, 
/*  692 */       sourcename, filenameURL, buffer, x, 
/*  693 */       y, z, attModel, distOrRoll, 
/*  694 */       false));
/*      */   }
/*      */ 
/*      */   public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
/*      */   {
/*  713 */     this.sourceMap.put(sourcename, 
/*  714 */       new SourceLWJGLOpenAL(this.listenerPositionAL, audioFormat, 
/*  715 */       priority, sourcename, x, y, z, 
/*  716 */       attModel, distOrRoll));
/*      */   }
/*      */ 
/*      */   public void quickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
/*      */   {
/*  739 */     IntBuffer myBuffer = null;
/*  740 */     if (!toStream)
/*      */     {
/*  743 */       myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
/*      */ 
/*  745 */       if (myBuffer == null) {
/*  746 */         loadSound(filenameURL);
/*      */       }
/*  748 */       myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
/*      */ 
/*  750 */       if (myBuffer == null)
/*      */       {
/*  752 */         errorMessage("Sound buffer was not created for " + 
/*  753 */           filenameURL.getFilename());
/*  754 */         return;
/*      */       }
/*      */     }
/*      */ 
/*  758 */     SoundBuffer buffer = null;
/*      */ 
/*  760 */     if (!toStream)
/*      */     {
/*  763 */       buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
/*      */ 
/*  765 */       if (buffer == null)
/*      */       {
/*  767 */         if (!loadSound(filenameURL))
/*      */         {
/*  769 */           errorMessage("Source '" + sourcename + "' was not created " + 
/*  770 */             "because an error occurred while loading " + 
/*  771 */             filenameURL.getFilename());
/*  772 */           return;
/*      */         }
/*      */       }
/*      */ 
/*  776 */       buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
/*      */ 
/*  778 */       if (buffer == null)
/*      */       {
/*  780 */         errorMessage("Source '" + sourcename + "' was not created " + 
/*  781 */           "because audio data was not found for " + 
/*  782 */           filenameURL.getFilename());
/*  783 */         return;
/*      */       }
/*      */     }
/*  786 */     SourceLWJGLOpenAL s = new SourceLWJGLOpenAL(this.listenerPositionAL, 
/*  787 */       myBuffer, priority, 
/*  788 */       toStream, toLoop, 
/*  789 */       sourcename, filenameURL, 
/*  790 */       buffer, x, y, z, 
/*  791 */       attModel, distOrRoll, 
/*  792 */       false);
/*      */ 
/*  794 */     this.sourceMap.put(sourcename, s);
/*  795 */     play(s);
/*  796 */     if (temporary)
/*  797 */       s.setTemporary(true);
/*      */   }
/*      */ 
/*      */   public void copySources(HashMap<String, Source> srcMap)
/*      */   {
/*  807 */     if (srcMap == null)
/*  808 */       return;
/*  809 */     Set keys = srcMap.keySet();
/*  810 */     Iterator iter = keys.iterator();
/*      */ 
/*  815 */     if (this.bufferMap == null)
/*      */     {
/*  817 */       this.bufferMap = new HashMap();
/*  818 */       importantMessage("Buffer Map was null in method 'copySources'");
/*      */     }
/*      */ 
/*  821 */     if (this.ALBufferMap == null)
/*      */     {
/*  823 */       this.ALBufferMap = new HashMap();
/*  824 */       importantMessage("Open AL Buffer Map was null in method'copySources'");
/*      */     }
/*      */ 
/*  829 */     this.sourceMap.clear();
/*      */ 
/*  833 */     while (iter.hasNext())
/*      */     {
/*  835 */       String sourcename = (String)iter.next();
/*  836 */       Source source = (Source)srcMap.get(sourcename);
/*  837 */       if (source != null)
/*      */       {
/*  839 */         SoundBuffer buffer = null;
/*  840 */         if (!source.toStream)
/*      */         {
/*  842 */           loadSound(source.filenameURL);
/*  843 */           buffer = (SoundBuffer)this.bufferMap.get(source.filenameURL.getFilename());
/*      */         }
/*  845 */         if ((source.toStream) || (buffer != null))
/*  846 */           this.sourceMap.put(sourcename, new SourceLWJGLOpenAL(
/*  847 */             this.listenerPositionAL, 
/*  848 */             (IntBuffer)this.ALBufferMap.get(
/*  849 */             source.filenameURL.getFilename()), 
/*  850 */             source, buffer));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setListenerPosition(float x, float y, float z)
/*      */   {
/*  864 */     super.setListenerPosition(x, y, z);
/*      */ 
/*  866 */     this.listenerPositionAL.put(0, x);
/*  867 */     this.listenerPositionAL.put(1, y);
/*  868 */     this.listenerPositionAL.put(2, z);
/*      */ 
/*  871 */     AL10.alListener(4100, this.listenerPositionAL);
/*      */ 
/*  873 */     checkALError();
/*      */   }
/*      */ 
/*      */   public void setListenerAngle(float angle)
/*      */   {
/*  884 */     super.setListenerAngle(angle);
/*      */ 
/*  886 */     this.listenerOrientation.put(0, this.listener.lookAt.x);
/*  887 */     this.listenerOrientation.put(2, this.listener.lookAt.z);
/*      */ 
/*  890 */     AL10.alListener(4111, this.listenerOrientation);
/*      */ 
/*  892 */     checkALError();
/*      */   }
/*      */ 
/*      */   public void setListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
/*      */   {
/*  908 */     super.setListenerOrientation(lookX, lookY, lookZ, upX, upY, upZ);
/*  909 */     this.listenerOrientation.put(0, lookX);
/*  910 */     this.listenerOrientation.put(1, lookY);
/*  911 */     this.listenerOrientation.put(2, lookZ);
/*  912 */     this.listenerOrientation.put(3, upX);
/*  913 */     this.listenerOrientation.put(4, upY);
/*  914 */     this.listenerOrientation.put(5, upZ);
/*  915 */     AL10.alListener(4111, this.listenerOrientation);
/*  916 */     checkALError();
/*      */   }
/*      */ 
/*      */   public void setListenerData(ListenerData l)
/*      */   {
/*  927 */     super.setListenerData(l);
/*      */ 
/*  929 */     this.listenerPositionAL.put(0, l.position.x);
/*  930 */     this.listenerPositionAL.put(1, l.position.y);
/*  931 */     this.listenerPositionAL.put(2, l.position.z);
/*  932 */     AL10.alListener(4100, this.listenerPositionAL);
/*  933 */     checkALError();
/*      */ 
/*  935 */     this.listenerOrientation.put(0, l.lookAt.x);
/*  936 */     this.listenerOrientation.put(1, l.lookAt.y);
/*  937 */     this.listenerOrientation.put(2, l.lookAt.z);
/*  938 */     this.listenerOrientation.put(3, l.up.x);
/*  939 */     this.listenerOrientation.put(4, l.up.y);
/*  940 */     this.listenerOrientation.put(5, l.up.z);
/*  941 */     AL10.alListener(4111, this.listenerOrientation);
/*  942 */     checkALError();
/*      */ 
/*  944 */     this.listenerVelocity.put(0, l.velocity.x);
/*  945 */     this.listenerVelocity.put(1, l.velocity.y);
/*  946 */     this.listenerVelocity.put(2, l.velocity.z);
/*  947 */     AL10.alListener(4102, this.listenerVelocity);
/*  948 */     checkALError();
/*      */   }
/*      */ 
/*      */   public void setListenerVelocity(float x, float y, float z)
/*      */   {
/*  960 */     super.setListenerVelocity(x, y, z);
/*      */ 
/*  962 */     this.listenerVelocity.put(0, this.listener.velocity.x);
/*  963 */     this.listenerVelocity.put(1, this.listener.velocity.y);
/*  964 */     this.listenerVelocity.put(2, this.listener.velocity.z);
/*  965 */     AL10.alListener(4102, this.listenerVelocity);
/*      */   }
/*      */ 
/*      */   public void dopplerChanged()
/*      */   {
/*  974 */     super.dopplerChanged();
/*      */ 
/*  976 */     AL10.alDopplerFactor(SoundSystemConfig.getDopplerFactor());
/*  977 */     checkALError();
/*  978 */     AL10.alDopplerVelocity(SoundSystemConfig.getDopplerVelocity());
/*  979 */     checkALError();
/*      */   }
/*      */ 
/*      */   private boolean checkALError()
/*      */   {
/*  988 */     switch (AL10.alGetError())
/*      */     {
/*      */     case 0:
/*  991 */       return false;
/*      */     case 40961:
/*  993 */       errorMessage("Invalid name parameter.");
/*  994 */       return true;
/*      */     case 40962:
/*  996 */       errorMessage("Invalid parameter.");
/*  997 */       return true;
/*      */     case 40963:
/*  999 */       errorMessage("Invalid enumerated parameter value.");
/* 1000 */       return true;
/*      */     case 40964:
/* 1002 */       errorMessage("Illegal call.");
/* 1003 */       return true;
/*      */     case 40965:
/* 1005 */       errorMessage("Unable to allocate memory.");
/* 1006 */       return true;
/*      */     }
/* 1008 */     errorMessage("An unrecognized error occurred.");
/* 1009 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean alPitchSupported()
/*      */   {
/* 1019 */     return alPitchSupported(false, false);
/*      */   }
/*      */ 
/*      */   private static synchronized boolean alPitchSupported(boolean action, boolean value)
/*      */   {
/* 1030 */     if (action)
/* 1031 */       alPitchSupported = value;
/* 1032 */     return alPitchSupported;
/*      */   }
/*      */ 
/*      */   public static String getTitle()
/*      */   {
/* 1041 */     return "LWJGL OpenAL";
/*      */   }
/*      */ 
/*      */   public static String getDescription()
/*      */   {
/* 1050 */     return "The LWJGL binding of OpenAL.  For more information, see http://www.lwjgl.org";
/*      */   }
/*      */ 
/*      */   public String getClassName()
/*      */   {
/* 1061 */     return "LibraryLWJGLOpenAL";
/*      */   }
/*      */ 
/*      */   public static class Exception extends SoundSystemException
/*      */   {
/*      */     private static final long serialVersionUID = -7502452059037798035L;
/*      */     public static final int CREATE = 101;
/*      */     public static final int INVALID_NAME = 102;
/*      */     public static final int INVALID_ENUM = 103;
/*      */     public static final int INVALID_VALUE = 104;
/*      */     public static final int INVALID_OPERATION = 105;
/*      */     public static final int OUT_OF_MEMORY = 106;
/*      */     public static final int LISTENER = 107;
/*      */     public static final int NO_AL_PITCH = 108;
/*      */ 
/*      */     public Exception(String message)
/*      */     {
/* 1112 */       super();
/*      */     }
/*      */ 
/*      */     public Exception(String message, int type)
/*      */     {
/* 1123 */       super(type);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.libraries.LibraryLWJGLOpenAL
 * JD-Core Version:    0.6.2
 */