/*     */ package paulscode.sound.utils;
/*     */ 
/*     */ import java.net.URL;
/*     */ import paulscode.sound.CommandObject;
/*     */ import paulscode.sound.FilenameURL;
/*     */ import paulscode.sound.SoundSystem;
/*     */ import paulscode.sound.SoundSystemConfig;
/*     */ import paulscode.sound.SoundSystemException;
/*     */ import paulscode.sound.SoundSystemLogger;
/*     */ 
/*     */ public class SoundSystemLoader
/*     */ {
/*     */   private static SoundSystemLogger logger;
/*  56 */   private static boolean verbose = false;
/*     */ 
/*     */   public static SoundSystem loadXML(URL xmlFile, SoundSystem s)
/*     */   {
/* 113 */     if (xmlFile == null)
/*     */     {
/* 115 */       errorMessage("Parameter 'xmlFile' null in method 'loadXML'");
/* 116 */       return null;
/*     */     }
/*     */ 
/* 120 */     XMLNode commands = XMLParser.parseXML(xmlFile);
/*     */ 
/* 123 */     if (commands == null)
/*     */     {
/* 125 */       warningMessage("No commands found in XML file");
/* 126 */       return null;
/*     */     }
/*     */ 
/* 130 */     Class c = null;
/*     */ 
/* 133 */     while (commands != null)
/*     */     {
/* 136 */       String command = commands.name().toUpperCase();
/*     */ 
/* 139 */       if ((command != null) && (!command.substring(0, 1).equals("/")))
/*     */       {
/* 142 */         if (command.equals("ADDLIBRARY"))
/*     */         {
/* 145 */           if (verbose)
/*     */           {
/* 147 */             message("SoundSystemLoader.loadXML:  addLibrary", 0);
/* 148 */             message("Class name:  " + commands.get("CLASSNAME"), 1);
/*     */           }
/*     */ 
/*     */           try
/*     */           {
/* 154 */             c = Class.forName(commands.get("CLASSNAME"));
/* 155 */             if (verbose) {
/* 156 */               message("Command:  SoundSystemConfig.addLibrary( " + commands.get("CLASSNAME") + ".class );", 1);
/*     */             }
/*     */ 
/* 160 */             SoundSystemConfig.addLibrary(c);
/*     */           }
/*     */           catch (ClassNotFoundException cnfe)
/*     */           {
/* 164 */             errorMessage("Unable to add library plug-in in method 'loadXML':  Class name '" + commands.get("CLASSNAME") + "' not found.");
/*     */           }
/*     */           catch (SoundSystemException sse)
/*     */           {
/* 171 */             printStackTrace(sse);
/*     */           }
/*     */         }
/* 174 */         else if (command.equals("SETCODEC"))
/*     */         {
/* 177 */           if (verbose)
/*     */           {
/* 179 */             message("SoundSystemLoader.loadXML:  setCodec", 0);
/* 180 */             message("Extension: " + commands.get("EXTENSION"), 1);
/*     */ 
/* 182 */             message("Class name: " + commands.get("CLASSNAME"), 1);
/*     */           }
/*     */ 
/*     */           try
/*     */           {
/* 188 */             c = Class.forName(commands.get("CLASSNAME"));
/* 189 */             if (verbose) {
/* 190 */               message("Command:  SoundSystemConfig.setCodec( \"" + commands.get("EXTENSION") + "\", " + commands.get("CLASSNAME") + ".class );", 1);
/*     */             }
/*     */ 
/* 195 */             SoundSystemConfig.setCodec(commands.get("EXTENSION"), c);
/*     */           }
/*     */           catch (ClassNotFoundException cnfe)
/*     */           {
/* 200 */             errorMessage("Unable to set codec plug-in for extension '" + commands.get("EXTENSION") + "' in method 'loadXML':  Class name '" + commands.get("CLASSNAME") + "' not found.");
/*     */           }
/*     */           catch (SoundSystemException sse)
/*     */           {
/* 209 */             printStackTrace(sse);
/*     */           }
/*     */         }
/* 212 */         else if (command.equals("CREATE"))
/*     */         {
/* 215 */           if (verbose)
/* 216 */             message("SoundSystemLoader.loadXML:  create", 0);
/* 217 */           if (s != null)
/* 218 */             s.cleanup();
/* 219 */           s = null;
/* 220 */           String parameter = commands.get("CLASSNAME");
/* 221 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/*     */             try
/*     */             {
/* 225 */               c = Class.forName(parameter);
/* 226 */               if (verbose)
/*     */               {
/* 228 */                 message("Command:  s = (SoundSystem) new " + parameter + "();", 1);
/*     */ 
/* 230 */                 s = (SoundSystem)c.newInstance();
/*     */               }
/*     */             }
/*     */             catch (ClassNotFoundException cnfe)
/*     */             {
/* 235 */               printStackTrace(cnfe);
/*     */             }
/*     */             catch (InstantiationException ie)
/*     */             {
/* 239 */               printStackTrace(ie);
/*     */             }
/*     */             catch (IllegalAccessException iae)
/*     */             {
/* 243 */               printStackTrace(iae);
/*     */             }
/* 245 */             if (verbose)
/*     */             {
/* 247 */               message("Unable to instantiate the Sound System in method 'loadXML'  Returning null.", 1);
/*     */ 
/* 249 */               if (s != null)
/* 250 */                 s.cleanup();
/* 251 */               s = null;
/* 252 */               return null;
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 257 */             if (verbose)
/* 258 */               message("Command:  s = new SoundSystem();", 1);
/* 259 */             s = new SoundSystem();
/*     */           }
/*     */         }
/* 262 */         else if (command.equals("LOADSOUND"))
/*     */         {
/* 265 */           if (verbose)
/*     */           {
/* 267 */             message("SoundSystemLoader.loadXML:  loadSound", 0);
/* 268 */             message("Filename: " + commands.get("FILENAME"), 1);
/*     */           }
/*     */ 
/* 271 */           if (s == null)
/*     */           {
/* 273 */             errorMessage("Encountered 'loadSound' command before SoundSystem was instantiated in method 'loadXML'.  returning null.");
/*     */ 
/* 276 */             return null;
/*     */           }
/* 278 */           if (verbose) {
/* 279 */             message("Command:  s.loadSound( \"" + commands.get("FILENAME") + "\" );", 1);
/*     */           }
/*     */ 
/* 282 */           s.loadSound(commands.get("FILENAME"));
/*     */         }
/* 284 */         else if (command.equals("NEWSOURCE"))
/*     */         {
/* 287 */           if (verbose) {
/* 288 */             message("SoundSystemLoader.loadXML:  newSource", 0);
/*     */           }
/*     */ 
/* 291 */           if (s == null)
/*     */           {
/* 293 */             errorMessage("Encountered 'newSource' command before SoundSystem was instantiated in method 'loadXML'.  returning null.");
/*     */ 
/* 296 */             return null;
/*     */           }
/* 298 */           boolean priority = false;
/* 299 */           boolean toStream = false;
/* 300 */           boolean toLoop = false;
/* 301 */           String sourcename = "";
/* 302 */           String filename = "";
/* 303 */           float x = 0.0F;
/* 304 */           float y = 0.0F;
/* 305 */           float z = 0.0F;
/* 306 */           int attModel = SoundSystemConfig.getDefaultAttenuation();
/* 307 */           float distOrRoll = 0.0F;
/* 308 */           String parameter = commands.get("PRIORITY");
/* 309 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/* 311 */             if (verbose)
/* 312 */               message("PRIORITY: " + parameter, 1);
/* 313 */             if (parameter.toUpperCase().equals("TRUE"))
/* 314 */               priority = true;
/*     */           }
/* 316 */           parameter = commands.get("TOSTREAM");
/* 317 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/* 319 */             if (verbose)
/* 320 */               message("TOSTREAM: " + parameter, 1);
/* 321 */             if (parameter.toUpperCase().equals("TRUE"))
/* 322 */               toStream = true;
/*     */           }
/* 324 */           parameter = commands.get("TOLOOP");
/* 325 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/* 327 */             if (verbose)
/* 328 */               message("TOLOOP: " + parameter, 1);
/* 329 */             if (parameter.toUpperCase().equals("TRUE"))
/* 330 */               toLoop = true;
/*     */           }
/* 332 */           parameter = commands.get("SOURCENAME");
/* 333 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/* 335 */             if (verbose)
/* 336 */               message("SOURCENAME: " + parameter, 1);
/* 337 */             sourcename = parameter;
/*     */           }
/* 339 */           parameter = commands.get("FILENAME");
/* 340 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/* 342 */             if (verbose)
/* 343 */               message("FILENAME: " + parameter, 1);
/* 344 */             filename = parameter;
/*     */           }
/* 346 */           parameter = commands.get("X");
/* 347 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/* 349 */             if (verbose)
/* 350 */               message("X: " + parameter, 1);
/*     */             try
/*     */             {
/* 353 */               x = Float.parseFloat(parameter);
/*     */             }
/*     */             catch (NumberFormatException nfe)
/*     */             {
/* 357 */               errorMessage("Error parsing float 'X' from String '" + parameter + "' in " + "method 'loadXML'.  Using x=0");
/*     */ 
/* 360 */               x = 0.0F;
/*     */             }
/*     */           }
/* 363 */           parameter = commands.get("Y");
/* 364 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/* 366 */             if (verbose)
/* 367 */               message("Y: " + parameter, 1);
/*     */             try
/*     */             {
/* 370 */               y = Float.parseFloat(parameter);
/*     */             }
/*     */             catch (NumberFormatException nfe)
/*     */             {
/* 374 */               errorMessage("Error parsing float 'Y' from String '" + parameter + "' in " + "method 'loadXML'.  Using y=0");
/*     */ 
/* 377 */               y = 0.0F;
/*     */             }
/*     */           }
/* 380 */           parameter = commands.get("Z");
/* 381 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/* 383 */             if (verbose)
/* 384 */               message("Z: " + parameter, 1);
/*     */             try
/*     */             {
/* 387 */               x = Float.parseFloat(parameter);
/*     */             }
/*     */             catch (NumberFormatException nfe)
/*     */             {
/* 391 */               errorMessage("Error parsing float 'Z' from String '" + parameter + "' in " + "method 'loadXML'.  Using z=0");
/*     */ 
/* 394 */               z = 0.0F;
/*     */             }
/*     */           }
/* 397 */           parameter = commands.get("ATTMODEL");
/* 398 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/* 400 */             if (verbose)
/* 401 */               message("ATTMODEL: " + parameter, 1);
/* 402 */             if (parameter.toUpperCase().contains("NONE"))
/* 403 */               attModel = 0;
/* 404 */             else if (parameter.toUpperCase().contains("LINEAR"))
/* 405 */               attModel = 2;
/* 406 */             else if (parameter.toUpperCase().contains("ROLLOFF"))
/* 407 */               attModel = 1;
/*     */           }
/* 409 */           parameter = commands.get("DISTORROLL");
/* 410 */           if ((parameter != null) && (!parameter.equals("")))
/*     */           {
/* 412 */             if (verbose)
/* 413 */               message("DISTORROLL: " + parameter, 1);
/*     */             try
/*     */             {
/* 416 */               distOrRoll = Float.parseFloat(parameter);
/*     */             }
/*     */             catch (NumberFormatException nfe)
/*     */             {
/* 420 */               errorMessage("Error parsing float 'DISTORROLL' from String '" + parameter + "' in method " + "'loadXML'.  Using default value.");
/*     */ 
/* 424 */               distOrRoll = 0.0F;
/* 425 */               if (attModel == 2)
/*     */               {
/* 427 */                 distOrRoll = SoundSystemConfig.getDefaultFadeDistance();
/*     */               }
/* 429 */               else if (attModel == 1)
/*     */               {
/* 431 */                 distOrRoll = SoundSystemConfig.getDefaultAttenuation();
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/*     */           }
/* 437 */           else if (attModel == 2) {
/* 438 */             distOrRoll = SoundSystemConfig.getDefaultFadeDistance();
/*     */           }
/* 440 */           else if (attModel == 1)
/*     */           {
/* 442 */             distOrRoll = SoundSystemConfig.getDefaultAttenuation();
/*     */           }
/*     */ 
/* 445 */           if (sourcename.equals(""))
/*     */           {
/* 447 */             errorMessage("Parameter 'SOURCENAME' not specified for 'NEWSOURCE' tag in method 'loadXML.  Unable to create new source.");
/*     */           }
/* 452 */           else if (filename.equals(""))
/*     */           {
/* 454 */             errorMessage("Parameter 'FILENAME' not specified for 'NEWSOURCE' tag in method 'loadXML.  Unable to create new source.");
/*     */           }
/*     */           else
/*     */           {
/* 461 */             if (verbose) {
/* 462 */               message("Command:  s.CommandQueue( new paulscode.sound.CommandObject( paulscode.sound.CommandObject.NEW_SOURCE, " + priority + ", " + toStream + ", " + toLoop + ", \"" + sourcename + "\", " + "new paulscode.sound.FilenameURL( \"" + filename + "\" ), " + x + ", " + y + ", " + z + ", " + attModel + ", " + distOrRoll + " ) );", 1);
/*     */             }
/*     */ 
/* 472 */             s.CommandQueue(new CommandObject(10, priority, toStream, toLoop, sourcename, new FilenameURL(filename), x, y, z, attModel, distOrRoll));
/*     */ 
/* 477 */             if (verbose) {
/* 478 */               message("Command:  s.interruptCommandThread();", 1);
/*     */             }
/* 480 */             s.interruptCommandThread();
/*     */           }
/*     */         }
/* 483 */         else if ((command.length() >= 3) && (command.substring(0, 3).equals("!--")))
/*     */         {
/* 487 */           if (verbose)
/*     */           {
/* 489 */             message("SoundSystemLoader.loadXML:  comment", 0);
/* 490 */             if (commands.contents().length() > 6) {
/* 491 */               message(commands.contents().substring(3, commands.contents().length() - 2), 1);
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/* 499 */         else if (verbose)
/*     */         {
/* 501 */           message("SoundSystemLoader.loadXML:  " + command, 0);
/* 502 */           message("Unrecognized tag.", 1);
/*     */         }
/*     */         else
/*     */         {
/* 506 */           warningMessage("Command '" + command + "' not " + "recognized in method 'loadXML'");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 512 */       commands = commands.next();
/*     */     }
/*     */ 
/* 516 */     return s;
/*     */   }
/*     */ 
/*     */   public static void setVerbose(boolean val)
/*     */   {
/* 525 */     verbose = val;
/*     */   }
/*     */ 
/*     */   protected static void message(String message, int indent)
/*     */   {
/* 535 */     if (logger == null) {
/* 536 */       logger = SoundSystemConfig.getLogger();
/*     */     }
/* 538 */     if (logger == null)
/*     */     {
/* 540 */       logger = new SoundSystemLogger();
/* 541 */       SoundSystemConfig.setLogger(logger);
/*     */     }
/* 543 */     logger.message(message, indent);
/*     */   }
/*     */ 
/*     */   protected static void errorMessage(String message)
/*     */   {
/* 552 */     if (logger == null) {
/* 553 */       logger = SoundSystemConfig.getLogger();
/*     */     }
/* 555 */     if (logger == null)
/*     */     {
/* 557 */       logger = new SoundSystemLogger();
/* 558 */       SoundSystemConfig.setLogger(logger);
/*     */     }
/* 560 */     logger.errorMessage("SoundSystemLoader", message, 0);
/*     */   }
/*     */ 
/*     */   protected static void warningMessage(String message)
/*     */   {
/* 570 */     if (logger == null) {
/* 571 */       logger = SoundSystemConfig.getLogger();
/*     */     }
/* 573 */     if (logger == null)
/*     */     {
/* 575 */       logger = new SoundSystemLogger();
/* 576 */       SoundSystemConfig.setLogger(logger);
/*     */     }
/* 578 */     logger.importantMessage("Warning in class 'SoundSystemLoader': " + message, 0);
/*     */   }
/*     */ 
/*     */   protected static void printStackTrace(Exception e)
/*     */   {
/* 588 */     if (logger == null) {
/* 589 */       logger = SoundSystemConfig.getLogger();
/*     */     }
/* 591 */     if (logger == null)
/*     */     {
/* 593 */       logger = new SoundSystemLogger();
/* 594 */       SoundSystemConfig.setLogger(logger);
/*     */     }
/* 596 */     logger.printStackTrace(e, 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.utils.SoundSystemLoader
 * JD-Core Version:    0.6.2
 */