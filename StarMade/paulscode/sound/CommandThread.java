/*   1:    */package paulscode.sound;
/*   2:    */
/*  22:    */public class CommandThread
/*  23:    */  extends SimpleThread
/*  24:    */{
/*  25:    */  protected SoundSystemLogger logger;
/*  26:    */  
/*  45:    */  private SoundSystem soundSystem;
/*  46:    */  
/*  65: 65 */  protected String className = "CommandThread";
/*  66:    */  
/*  72:    */  public CommandThread(SoundSystem s)
/*  73:    */  {
/*  74: 74 */    this.logger = SoundSystemConfig.getLogger();
/*  75:    */    
/*  76: 76 */    this.soundSystem = s;
/*  77:    */  }
/*  78:    */  
/*  84:    */  protected void cleanup()
/*  85:    */  {
/*  86: 86 */    kill();
/*  87:    */    
/*  88: 88 */    this.logger = null;
/*  89: 89 */    this.soundSystem = null;
/*  90:    */    
/*  91: 91 */    super.cleanup();
/*  92:    */  }
/*  93:    */  
/* 100:    */  public void run()
/* 101:    */  {
/* 102:102 */    long previousTime = System.currentTimeMillis();
/* 103:103 */    long currentTime = previousTime;
/* 104:    */    
/* 105:105 */    if (this.soundSystem == null)
/* 106:    */    {
/* 107:107 */      errorMessage("SoundSystem was null in method run().", 0);
/* 108:108 */      cleanup();
/* 109:109 */      return;
/* 110:    */    }
/* 111:    */    
/* 113:113 */    snooze(3600000L);
/* 114:    */    
/* 115:115 */    while (!dying())
/* 116:    */    {
/* 118:118 */      this.soundSystem.ManageSources();
/* 119:    */      
/* 121:121 */      this.soundSystem.CommandQueue(null);
/* 122:    */      
/* 124:124 */      currentTime = System.currentTimeMillis();
/* 125:125 */      if ((!dying()) && (currentTime - previousTime > 10000L))
/* 126:    */      {
/* 127:127 */        previousTime = currentTime;
/* 128:128 */        this.soundSystem.removeTemporarySources();
/* 129:    */      }
/* 130:    */      
/* 132:132 */      if (!dying()) {
/* 133:133 */        snooze(3600000L);
/* 134:    */      }
/* 135:    */    }
/* 136:136 */    cleanup();
/* 137:    */  }
/* 138:    */  
/* 143:    */  protected void message(String message, int indent)
/* 144:    */  {
/* 145:145 */    this.logger.message(message, indent);
/* 146:    */  }
/* 147:    */  
/* 152:    */  protected void importantMessage(String message, int indent)
/* 153:    */  {
/* 154:154 */    this.logger.importantMessage(message, indent);
/* 155:    */  }
/* 156:    */  
/* 163:    */  protected boolean errorCheck(boolean error, String message)
/* 164:    */  {
/* 165:165 */    return this.logger.errorCheck(error, this.className, message, 0);
/* 166:    */  }
/* 167:    */  
/* 172:    */  protected void errorMessage(String message, int indent)
/* 173:    */  {
/* 174:174 */    this.logger.errorMessage(this.className, message, indent);
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.CommandThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */