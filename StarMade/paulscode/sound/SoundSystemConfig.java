/*      */ package paulscode.sound;
/*      */ 
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.LinkedList;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Locale;
/*      */ 
/*      */ public class SoundSystemConfig
/*      */ {
/*   55 */   public static final Object THREAD_SYNC = new Object();
/*      */   public static final int TYPE_NORMAL = 0;
/*      */   public static final int TYPE_STREAMING = 1;
/*      */   public static final int ATTENUATION_NONE = 0;
/*      */   public static final int ATTENUATION_ROLLOFF = 1;
/*      */   public static final int ATTENUATION_LINEAR = 2;
/*   94 */   public static String EXTENSION_MIDI = ".*[mM][iI][dD][iI]?$";
/*      */ 
/*   99 */   public static String PREFIX_URL = "^[hH][tT][tT][pP]://.*";
/*      */ 
/*  112 */   private static SoundSystemLogger logger = null;
/*      */   private static LinkedList<Class> libraries;
/*  122 */   private static LinkedList<Codec> codecs = null;
/*      */ 
/*  127 */   private static LinkedList<IStreamListener> streamListeners = null;
/*      */ 
/*  131 */   private static final Object streamListenersLock = new Object();
/*      */ 
/*  138 */   private static int numberNormalChannels = 28;
/*      */ 
/*  144 */   private static int numberStreamingChannels = 4;
/*      */ 
/*  148 */   private static float masterGain = 1.0F;
/*      */ 
/*  153 */   private static int defaultAttenuationModel = 1;
/*      */ 
/*  157 */   private static float defaultRolloffFactor = 0.03F;
/*      */ 
/*  161 */   private static float dopplerFactor = 0.0F;
/*      */ 
/*  165 */   private static float dopplerVelocity = 1.0F;
/*      */ 
/*  169 */   private static float defaultFadeDistance = 1000.0F;
/*      */ 
/*  173 */   private static String soundFilesPackage = "Sounds/";
/*      */ 
/*  178 */   private static int streamingBufferSize = 131072;
/*      */ 
/*  183 */   private static int numberStreamingBuffers = 3;
/*      */ 
/*  190 */   private static boolean streamQueueFormatsMatch = false;
/*      */ 
/*  196 */   private static int maxFileSize = 268435456;
/*      */ 
/*  201 */   private static int fileChunkSize = 1048576;
/*      */ 
/*  207 */   private static boolean midiCodec = false;
/*      */ 
/*  215 */   private static String overrideMIDISynthesizer = "";
/*      */ 
/*      */   public static void addLibrary(Class libraryClass)
/*      */     throws SoundSystemException
/*      */   {
/*  231 */     if (libraryClass == null) {
/*  232 */       throw new SoundSystemException("Parameter null in method 'addLibrary'", 2);
/*      */     }
/*      */ 
/*  235 */     if (!Library.class.isAssignableFrom(libraryClass)) {
/*  236 */       throw new SoundSystemException("The specified class does not extend class 'Library' in method 'addLibrary'");
/*      */     }
/*      */ 
/*  239 */     if (libraries == null) {
/*  240 */       libraries = new LinkedList();
/*      */     }
/*  242 */     if (!libraries.contains(libraryClass))
/*  243 */       libraries.add(libraryClass);
/*      */   }
/*      */ 
/*      */   public static void removeLibrary(Class libraryClass)
/*      */     throws SoundSystemException
/*      */   {
/*  253 */     if ((libraries == null) || (libraryClass == null)) {
/*  254 */       return;
/*      */     }
/*  256 */     libraries.remove(libraryClass);
/*      */   }
/*      */ 
/*      */   public static LinkedList<Class> getLibraries()
/*      */   {
/*  265 */     return libraries;
/*      */   }
/*      */ 
/*      */   public static boolean libraryCompatible(Class libraryClass)
/*      */   {
/*  275 */     if (libraryClass == null)
/*      */     {
/*  277 */       errorMessage("Parameter 'libraryClass' null in method'librayCompatible'");
/*      */ 
/*  279 */       return false;
/*      */     }
/*  281 */     if (!Library.class.isAssignableFrom(libraryClass))
/*      */     {
/*  283 */       errorMessage("The specified class does not extend class 'Library' in method 'libraryCompatible'");
/*      */ 
/*  285 */       return false;
/*      */     }
/*      */ 
/*  288 */     Object o = runMethod(libraryClass, "libraryCompatible", new Class[0], new Object[0]);
/*      */ 
/*  291 */     if (o == null)
/*      */     {
/*  293 */       errorMessage("Method 'Library.libraryCompatible' returned 'null' in method 'libraryCompatible'");
/*      */ 
/*  295 */       return false;
/*      */     }
/*      */ 
/*  298 */     return ((Boolean)o).booleanValue();
/*      */   }
/*      */ 
/*      */   public static String getLibraryTitle(Class libraryClass)
/*      */   {
/*  308 */     if (libraryClass == null)
/*      */     {
/*  310 */       errorMessage("Parameter 'libraryClass' null in method'getLibrayTitle'");
/*      */ 
/*  312 */       return null;
/*      */     }
/*  314 */     if (!Library.class.isAssignableFrom(libraryClass))
/*      */     {
/*  316 */       errorMessage("The specified class does not extend class 'Library' in method 'getLibraryTitle'");
/*      */ 
/*  318 */       return null;
/*      */     }
/*      */ 
/*  321 */     Object o = runMethod(libraryClass, "getTitle", new Class[0], new Object[0]);
/*      */ 
/*  323 */     if (o == null)
/*      */     {
/*  325 */       errorMessage("Method 'Library.getTitle' returned 'null' in method 'getLibraryTitle'");
/*      */ 
/*  327 */       return null;
/*      */     }
/*      */ 
/*  330 */     return (String)o;
/*      */   }
/*      */ 
/*      */   public static String getLibraryDescription(Class libraryClass)
/*      */   {
/*  340 */     if (libraryClass == null)
/*      */     {
/*  342 */       errorMessage("Parameter 'libraryClass' null in method'getLibrayDescription'");
/*      */ 
/*  344 */       return null;
/*      */     }
/*  346 */     if (!Library.class.isAssignableFrom(libraryClass))
/*      */     {
/*  348 */       errorMessage("The specified class does not extend class 'Library' in method 'getLibraryDescription'");
/*      */ 
/*  350 */       return null;
/*      */     }
/*      */ 
/*  353 */     Object o = runMethod(libraryClass, "getDescription", new Class[0], new Object[0]);
/*      */ 
/*  355 */     if (o == null)
/*      */     {
/*  357 */       errorMessage("Method 'Library.getDescription' returned 'null' in method 'getLibraryDescription'");
/*      */ 
/*  359 */       return null;
/*      */     }
/*      */ 
/*  362 */     return (String)o;
/*      */   }
/*      */ 
/*      */   public static boolean reverseByteOrder(Class libraryClass)
/*      */   {
/*  372 */     if (libraryClass == null)
/*      */     {
/*  374 */       errorMessage("Parameter 'libraryClass' null in method'reverseByteOrder'");
/*      */ 
/*  376 */       return false;
/*      */     }
/*  378 */     if (!Library.class.isAssignableFrom(libraryClass))
/*      */     {
/*  380 */       errorMessage("The specified class does not extend class 'Library' in method 'reverseByteOrder'");
/*      */ 
/*  382 */       return false;
/*      */     }
/*      */ 
/*  385 */     Object o = runMethod(libraryClass, "reversByteOrder", new Class[0], new Object[0]);
/*      */ 
/*  387 */     if (o == null)
/*      */     {
/*  389 */       errorMessage("Method 'Library.reverseByteOrder' returned 'null' in method 'getLibraryDescription'");
/*      */ 
/*  391 */       return false;
/*      */     }
/*      */ 
/*  394 */     return ((Boolean)o).booleanValue();
/*      */   }
/*      */ 
/*      */   public static void setLogger(SoundSystemLogger l)
/*      */   {
/*  420 */     logger = l;
/*      */   }
/*      */ 
/*      */   public static SoundSystemLogger getLogger()
/*      */   {
/*  428 */     return logger;
/*      */   }
/*      */ 
/*      */   public static synchronized void setNumberNormalChannels(int number)
/*      */   {
/*  445 */     numberNormalChannels = number;
/*      */   }
/*      */ 
/*      */   public static synchronized int getNumberNormalChannels()
/*      */   {
/*  455 */     return numberNormalChannels;
/*      */   }
/*      */ 
/*      */   public static synchronized void setNumberStreamingChannels(int number)
/*      */   {
/*  470 */     numberStreamingChannels = number;
/*      */   }
/*      */ 
/*      */   public static synchronized int getNumberStreamingChannels()
/*      */   {
/*  479 */     return numberStreamingChannels;
/*      */   }
/*      */ 
/*      */   public static synchronized void setMasterGain(float value)
/*      */   {
/*  488 */     masterGain = value;
/*      */   }
/*      */ 
/*      */   public static synchronized float getMasterGain()
/*      */   {
/*  497 */     return masterGain;
/*      */   }
/*      */ 
/*      */   public static synchronized void setDefaultAttenuation(int model)
/*      */   {
/*  507 */     defaultAttenuationModel = model;
/*      */   }
/*      */ 
/*      */   public static synchronized int getDefaultAttenuation()
/*      */   {
/*  515 */     return defaultAttenuationModel;
/*      */   }
/*      */ 
/*      */   public static synchronized void setDefaultRolloff(float rolloff)
/*      */   {
/*  523 */     defaultRolloffFactor = rolloff;
/*      */   }
/*      */ 
/*      */   public static synchronized float getDopplerFactor()
/*      */   {
/*  531 */     return dopplerFactor;
/*      */   }
/*      */ 
/*      */   public static synchronized void setDopplerFactor(float factor)
/*      */   {
/*  542 */     dopplerFactor = factor;
/*      */   }
/*      */ 
/*      */   public static synchronized float getDopplerVelocity()
/*      */   {
/*  550 */     return dopplerVelocity;
/*      */   }
/*      */ 
/*      */   public static synchronized void setDopplerVelocity(float velocity)
/*      */   {
/*  561 */     dopplerVelocity = velocity;
/*      */   }
/*      */ 
/*      */   public static synchronized float getDefaultRolloff()
/*      */   {
/*  569 */     return defaultRolloffFactor;
/*      */   }
/*      */ 
/*      */   public static synchronized void setDefaultFadeDistance(float distance)
/*      */   {
/*  577 */     defaultFadeDistance = distance;
/*      */   }
/*      */ 
/*      */   public static synchronized float getDefaultFadeDistance()
/*      */   {
/*  585 */     return defaultFadeDistance;
/*      */   }
/*      */ 
/*      */   public static synchronized void setSoundFilesPackage(String location)
/*      */   {
/*  593 */     soundFilesPackage = location;
/*      */   }
/*      */ 
/*      */   public static synchronized String getSoundFilesPackage()
/*      */   {
/*  601 */     return soundFilesPackage;
/*      */   }
/*      */ 
/*      */   public static synchronized void setStreamingBufferSize(int size)
/*      */   {
/*  609 */     streamingBufferSize = size;
/*      */   }
/*      */ 
/*      */   public static synchronized int getStreamingBufferSize()
/*      */   {
/*  617 */     return streamingBufferSize;
/*      */   }
/*      */ 
/*      */   public static synchronized void setNumberStreamingBuffers(int num)
/*      */   {
/*  627 */     numberStreamingBuffers = num;
/*      */   }
/*      */ 
/*      */   public static synchronized int getNumberStreamingBuffers()
/*      */   {
/*  635 */     return numberStreamingBuffers;
/*      */   }
/*      */ 
/*      */   public static synchronized void setStreamQueueFormatsMatch(boolean val)
/*      */   {
/*  647 */     streamQueueFormatsMatch = val;
/*      */   }
/*      */ 
/*      */   public static synchronized boolean getStreamQueueFormatsMatch()
/*      */   {
/*  659 */     return streamQueueFormatsMatch;
/*      */   }
/*      */ 
/*      */   public static synchronized void setMaxFileSize(int size)
/*      */   {
/*  670 */     maxFileSize = size;
/*      */   }
/*      */ 
/*      */   public static synchronized int getMaxFileSize()
/*      */   {
/*  678 */     return maxFileSize;
/*      */   }
/*      */ 
/*      */   public static synchronized void setFileChunkSize(int size)
/*      */   {
/*  687 */     fileChunkSize = size;
/*      */   }
/*      */ 
/*      */   public static synchronized int getFileChunkSize()
/*      */   {
/*  696 */     return fileChunkSize;
/*      */   }
/*      */ 
/*      */   public static synchronized String getOverrideMIDISynthesizer()
/*      */   {
/*  705 */     return overrideMIDISynthesizer;
/*      */   }
/*      */ 
/*      */   public static synchronized void setOverrideMIDISynthesizer(String name)
/*      */   {
/*  715 */     overrideMIDISynthesizer = name;
/*      */   }
/*      */ 
/*      */   public static synchronized void setCodec(String extension, Class iCodecClass)
/*      */     throws SoundSystemException
/*      */   {
/*  727 */     if (extension == null) {
/*  728 */       throw new SoundSystemException("Parameter 'extension' null in method 'setCodec'.", 2);
/*      */     }
/*      */ 
/*  731 */     if (iCodecClass == null) {
/*  732 */       throw new SoundSystemException("Parameter 'iCodecClass' null in method 'setCodec'.", 2);
/*      */     }
/*      */ 
/*  735 */     if (!ICodec.class.isAssignableFrom(iCodecClass)) {
/*  736 */       throw new SoundSystemException("The specified class does not implement interface 'ICodec' in method 'setCodec'", 3);
/*      */     }
/*      */ 
/*  740 */     if (codecs == null) {
/*  741 */       codecs = new LinkedList();
/*      */     }
/*  743 */     ListIterator i = codecs.listIterator();
/*      */ 
/*  746 */     while (i.hasNext())
/*      */     {
/*  748 */       Codec codec = (Codec)i.next();
/*  749 */       if (extension.matches(codec.extensionRegX))
/*  750 */         i.remove();
/*      */     }
/*  752 */     codecs.add(new Codec(extension, iCodecClass));
/*      */ 
/*  756 */     if (extension.matches(EXTENSION_MIDI))
/*  757 */       midiCodec = true;
/*      */   }
/*      */ 
/*      */   public static synchronized ICodec getCodec(String filename)
/*      */   {
/*  767 */     if (codecs == null) {
/*  768 */       return null;
/*      */     }
/*  770 */     ListIterator i = codecs.listIterator();
/*      */ 
/*  773 */     while (i.hasNext())
/*      */     {
/*  775 */       Codec codec = (Codec)i.next();
/*  776 */       if (filename.matches(codec.extensionRegX)) {
/*  777 */         return codec.getInstance();
/*      */       }
/*      */     }
/*  780 */     return null;
/*      */   }
/*      */ 
/*      */   public static boolean midiCodec()
/*      */   {
/*  790 */     return midiCodec;
/*      */   }
/*      */ 
/*      */   public static void addStreamListener(IStreamListener streamListener)
/*      */   {
/*  800 */     synchronized (streamListenersLock)
/*      */     {
/*  802 */       if (streamListeners == null) {
/*  803 */         streamListeners = new LinkedList();
/*      */       }
/*  805 */       if (!streamListeners.contains(streamListener))
/*  806 */         streamListeners.add(streamListener);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void removeStreamListener(IStreamListener streamListener)
/*      */   {
/*  817 */     synchronized (streamListenersLock)
/*      */     {
/*  819 */       if (streamListeners == null) {
/*  820 */         streamListeners = new LinkedList();
/*      */       }
/*  822 */       if (streamListeners.contains(streamListener))
/*  823 */         streamListeners.remove(streamListener);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void notifyEOS(String sourcename, int queueSize)
/*      */   {
/*  835 */     synchronized (streamListenersLock)
/*      */     {
/*  837 */       if (streamListeners == null)
/*  838 */         return;
/*      */     }
/*  840 */     String srcName = sourcename;
/*  841 */     final int qSize = queueSize;
/*      */ 
/*  843 */     new Thread()
/*      */     {
/*      */       public void run()
/*      */       {
/*  848 */         synchronized (SoundSystemConfig.streamListenersLock)
/*      */         {
/*  850 */           if (SoundSystemConfig.streamListeners == null)
/*  851 */             return;
/*  852 */           ListIterator i = SoundSystemConfig.streamListeners.listIterator();
/*      */ 
/*  854 */           while (i.hasNext())
/*      */           {
/*  856 */             IStreamListener streamListener = (IStreamListener)i.next();
/*  857 */             if (streamListener == null)
/*  858 */               i.remove();
/*      */             else
/*  860 */               streamListener.endOfStream(this.val$srcName, qSize);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  843 */     .start();
/*      */   }
/*      */ 
/*      */   private static void errorMessage(String message)
/*      */   {
/*  878 */     if (logger != null)
/*  879 */       logger.errorMessage("SoundSystemConfig", message, 0);
/*      */   }
/*      */ 
/*      */   private static Object runMethod(Class c, String method, Class[] paramTypes, Object[] params)
/*      */   {
/*  897 */     Method m = null;
/*      */     try
/*      */     {
/*  900 */       m = c.getMethod(method, paramTypes);
/*      */     }
/*      */     catch (NoSuchMethodException nsme)
/*      */     {
/*  904 */       errorMessage("NoSuchMethodException thrown when attempting to call method '" + method + "' in " + "method 'runMethod'");
/*      */ 
/*  907 */       return null;
/*      */     }
/*      */     catch (SecurityException se)
/*      */     {
/*  911 */       errorMessage("Access denied when attempting to call method '" + method + "' in method 'runMethod'");
/*      */ 
/*  913 */       return null;
/*      */     }
/*      */     catch (NullPointerException npe)
/*      */     {
/*  917 */       errorMessage("NullPointerException thrown when attempting to call method '" + method + "' in " + "method 'runMethod'");
/*      */ 
/*  920 */       return null;
/*      */     }
/*  922 */     if (m == null)
/*      */     {
/*  924 */       errorMessage("Method '" + method + "' not found for the class " + "specified in method 'runMethod'");
/*      */ 
/*  926 */       return null;
/*      */     }
/*      */ 
/*  929 */     Object o = null;
/*      */     try
/*      */     {
/*  932 */       o = m.invoke(null, params);
/*      */     }
/*      */     catch (IllegalAccessException iae)
/*      */     {
/*  936 */       errorMessage("IllegalAccessException thrown when attempting to invoke method '" + method + "' in " + "method 'runMethod'");
/*      */ 
/*  939 */       return null;
/*      */     }
/*      */     catch (IllegalArgumentException iae)
/*      */     {
/*  943 */       errorMessage("IllegalArgumentException thrown when attempting to invoke method '" + method + "' in " + "method 'runMethod'");
/*      */ 
/*  946 */       return null;
/*      */     }
/*      */     catch (InvocationTargetException ite)
/*      */     {
/*  950 */       errorMessage("InvocationTargetException thrown while attempting to invoke method 'Library.getTitle' in method 'getLibraryTitle'");
/*      */ 
/*  953 */       return null;
/*      */     }
/*      */     catch (NullPointerException npe)
/*      */     {
/*  957 */       errorMessage("NullPointerException thrown when attempting to invoke method '" + method + "' in " + "method 'runMethod'");
/*      */ 
/*  960 */       return null;
/*      */     }
/*      */     catch (ExceptionInInitializerError eiie)
/*      */     {
/*  964 */       errorMessage("ExceptionInInitializerError thrown when attempting to invoke method '" + method + "' in " + "method 'runMethod'");
/*      */ 
/*  967 */       return null;
/*      */     }
/*      */ 
/*  970 */     return o;
/*      */   }
/*      */ 
/*      */   private static class Codec
/*      */   {
/*      */     public String extensionRegX;
/*      */     public Class iCodecClass;
/*      */ 
/*      */     public Codec(String extension, Class iCodecClass)
/*      */     {
/* 1003 */       this.extensionRegX = "";
/*      */ 
/* 1005 */       if ((extension != null) && (extension.length() > 0))
/*      */       {
/* 1009 */         this.extensionRegX = ".*";
/*      */ 
/* 1011 */         for (int x = 0; x < extension.length(); x++)
/*      */         {
/* 1014 */           String c = extension.substring(x, x + 1);
/* 1015 */           this.extensionRegX = (this.extensionRegX + "[" + c.toLowerCase(Locale.ENGLISH) + c.toUpperCase(Locale.ENGLISH) + "]");
/*      */         }
/*      */ 
/* 1019 */         this.extensionRegX += "$";
/*      */       }
/*      */ 
/* 1022 */       this.iCodecClass = iCodecClass;
/*      */     }
/*      */ 
/*      */     public ICodec getInstance()
/*      */     {
/* 1027 */       if (this.iCodecClass == null) {
/* 1028 */         return null;
/*      */       }
/* 1030 */       Object o = null;
/*      */       try
/*      */       {
/* 1033 */         o = this.iCodecClass.newInstance();
/*      */       }
/*      */       catch (InstantiationException ie)
/*      */       {
/* 1037 */         instantiationErrorMessage();
/* 1038 */         return null;
/*      */       }
/*      */       catch (IllegalAccessException iae)
/*      */       {
/* 1042 */         instantiationErrorMessage();
/* 1043 */         return null;
/*      */       }
/*      */       catch (ExceptionInInitializerError eiie)
/*      */       {
/* 1047 */         instantiationErrorMessage();
/* 1048 */         return null;
/*      */       }
/*      */       catch (SecurityException se)
/*      */       {
/* 1052 */         instantiationErrorMessage();
/* 1053 */         return null;
/*      */       }
/*      */ 
/* 1057 */       if (o == null)
/*      */       {
/* 1059 */         instantiationErrorMessage();
/* 1060 */         return null;
/*      */       }
/*      */ 
/* 1063 */       return (ICodec)o;
/*      */     }
/*      */ 
/*      */     private void instantiationErrorMessage()
/*      */     {
/* 1068 */       SoundSystemConfig.errorMessage("Unrecognized ICodec implementation in method 'getInstance'.  Ensure that the implementing class has one public, parameterless constructor.");
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SoundSystemConfig
 * JD-Core Version:    0.6.2
 */