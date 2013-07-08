package org.hsqldb.auth;

import java.util.regex.Pattern;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.hsqldb.lib.FrameworkLogger;

public class JaasAuthBean
  implements AuthFunctionBean
{
  private static FrameworkLogger logger = FrameworkLogger.getLog(JaasAuthBean.class);
  private boolean initialized;
  private String applicationKey;
  private Pattern roleSchemaValuePattern;
  private boolean roleSchemaViaCredential;
  
  public void setRoleSchemaViaCredential(boolean paramBoolean)
  {
    this.roleSchemaViaCredential = paramBoolean;
  }
  
  public void init()
  {
    if (this.applicationKey == null) {
      throw new IllegalStateException("Required property 'applicationKey' not set");
    }
    if ((this.roleSchemaViaCredential) && (this.roleSchemaValuePattern == null)) {
      throw new IllegalStateException("Properties 'roleSchemaViaCredential' and 'roleSchemaValuePattern' are mutually exclusive.  If you want JaasAuthBean to manage roles or schemas, you must set property 'roleSchemaValuePattern'.");
    }
    this.initialized = true;
  }
  
  public void setApplicationKey(String paramString)
  {
    this.applicationKey = paramString;
  }
  
  public void setRoleSchemaValuePattern(Pattern paramPattern)
  {
    this.roleSchemaValuePattern = paramPattern;
  }
  
  public void setRoleSchemaValuePatternString(String paramString)
  {
    setRoleSchemaValuePattern(Pattern.compile(paramString));
  }
  
  /* Error */
  public String[] authenticate(String paramString1, String paramString2)
    throws DenyException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 9	org/hsqldb/auth/JaasAuthBean:initialized	Z
    //   4: ifne +40 -> 44
    //   7: new 4	java/lang/IllegalStateException
    //   10: dup
    //   11: new 12	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 13	java/lang/StringBuilder:<init>	()V
    //   18: ldc 14
    //   20: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: ldc_w 16
    //   26: invokevirtual 17	java/lang/Class:getName	()Ljava/lang/String;
    //   29: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: ldc 18
    //   34: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 19	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: invokespecial 6	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   43: athrow
    //   44: new 20	javax/security/auth/login/LoginContext
    //   47: dup
    //   48: aload_0
    //   49: getfield 3	org/hsqldb/auth/JaasAuthBean:applicationKey	Ljava/lang/String;
    //   52: new 21	org/hsqldb/auth/JaasAuthBean$UPCallbackHandler
    //   55: dup
    //   56: aload_1
    //   57: aload_2
    //   58: invokespecial 22	org/hsqldb/auth/JaasAuthBean$UPCallbackHandler:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: invokespecial 23	javax/security/auth/login/LoginContext:<init>	(Ljava/lang/String;Ljavax/security/auth/callback/CallbackHandler;)V
    //   64: astore_3
    //   65: aload_3
    //   66: invokevirtual 24	javax/security/auth/login/LoginContext:login	()V
    //   69: goto +39 -> 108
    //   72: astore 4
    //   74: getstatic 26	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   77: new 12	java/lang/StringBuilder
    //   80: dup
    //   81: invokespecial 13	java/lang/StringBuilder:<init>	()V
    //   84: ldc 27
    //   86: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: aload 4
    //   91: invokevirtual 28	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 19	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: invokevirtual 29	org/hsqldb/lib/FrameworkLogger:finer	(Ljava/lang/String;)V
    //   100: new 30	org/hsqldb/auth/DenyException
    //   103: dup
    //   104: invokespecial 31	org/hsqldb/auth/DenyException:<init>	()V
    //   107: athrow
    //   108: aload_0
    //   109: getfield 7	org/hsqldb/auth/JaasAuthBean:roleSchemaValuePattern	Ljava/util/regex/Pattern;
    //   112: ifnonnull +13 -> 125
    //   115: aconst_null
    //   116: astore 4
    //   118: aload_3
    //   119: invokevirtual 32	javax/security/auth/login/LoginContext:logout	()V
    //   122: aload 4
    //   124: areturn
    //   125: iconst_0
    //   126: istore 4
    //   128: aconst_null
    //   129: astore 5
    //   131: new 33	java/util/ArrayList
    //   134: dup
    //   135: invokespecial 34	java/util/ArrayList:<init>	()V
    //   138: astore 6
    //   140: new 33	java/util/ArrayList
    //   143: dup
    //   144: invokespecial 34	java/util/ArrayList:<init>	()V
    //   147: astore 7
    //   149: aload_3
    //   150: invokevirtual 35	javax/security/auth/login/LoginContext:getSubject	()Ljavax/security/auth/Subject;
    //   153: astore 8
    //   155: aload_0
    //   156: getfield 2	org/hsqldb/auth/JaasAuthBean:roleSchemaViaCredential	Z
    //   159: ifeq +58 -> 217
    //   162: new 33	java/util/ArrayList
    //   165: dup
    //   166: aload 8
    //   168: invokevirtual 36	javax/security/auth/Subject:getPublicCredentials	()Ljava/util/Set;
    //   171: invokespecial 37	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   174: invokevirtual 38	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   177: astore 9
    //   179: aload 9
    //   181: invokeinterface 39 1 0
    //   186: ifeq +28 -> 214
    //   189: aload 9
    //   191: invokeinterface 40 1 0
    //   196: astore 10
    //   198: aload 6
    //   200: aload 10
    //   202: invokevirtual 41	java/lang/Object:toString	()Ljava/lang/String;
    //   205: invokeinterface 42 2 0
    //   210: pop
    //   211: goto -32 -> 179
    //   214: goto +60 -> 274
    //   217: new 33	java/util/ArrayList
    //   220: dup
    //   221: aload 8
    //   223: invokevirtual 43	javax/security/auth/Subject:getPrincipals	()Ljava/util/Set;
    //   226: invokespecial 37	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   229: invokevirtual 38	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   232: astore 9
    //   234: aload 9
    //   236: invokeinterface 39 1 0
    //   241: ifeq +33 -> 274
    //   244: aload 9
    //   246: invokeinterface 40 1 0
    //   251: checkcast 44	java/security/Principal
    //   254: astore 10
    //   256: aload 6
    //   258: aload 10
    //   260: invokeinterface 45 1 0
    //   265: invokeinterface 42 2 0
    //   270: pop
    //   271: goto -37 -> 234
    //   274: getstatic 26	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   277: new 12	java/lang/StringBuilder
    //   280: dup
    //   281: invokespecial 13	java/lang/StringBuilder:<init>	()V
    //   284: aload 6
    //   286: invokeinterface 46 1 0
    //   291: invokestatic 47	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   294: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: ldc 48
    //   299: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: aload_0
    //   303: getfield 2	org/hsqldb/auth/JaasAuthBean:roleSchemaViaCredential	Z
    //   306: ifeq +8 -> 314
    //   309: ldc 49
    //   311: goto +5 -> 316
    //   314: ldc 50
    //   316: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: invokevirtual 19	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   322: invokevirtual 29	org/hsqldb/lib/FrameworkLogger:finer	(Ljava/lang/String;)V
    //   325: aload 6
    //   327: invokeinterface 51 1 0
    //   332: astore 9
    //   334: aload 9
    //   336: invokeinterface 39 1 0
    //   341: ifeq +162 -> 503
    //   344: aload 9
    //   346: invokeinterface 40 1 0
    //   351: checkcast 52	java/lang/String
    //   354: astore 10
    //   356: aload_0
    //   357: getfield 7	org/hsqldb/auth/JaasAuthBean:roleSchemaValuePattern	Ljava/util/regex/Pattern;
    //   360: aload 10
    //   362: invokevirtual 53	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   365: astore 5
    //   367: aload 5
    //   369: invokevirtual 54	java/util/regex/Matcher:matches	()Z
    //   372: ifeq +89 -> 461
    //   375: getstatic 26	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   378: new 12	java/lang/StringBuilder
    //   381: dup
    //   382: invokespecial 13	java/lang/StringBuilder:<init>	()V
    //   385: ldc 55
    //   387: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: iinc 4 1
    //   393: iload 4
    //   395: invokevirtual 56	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   398: ldc 57
    //   400: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: aload 5
    //   405: invokevirtual 58	java/util/regex/Matcher:groupCount	()I
    //   408: ifle +12 -> 420
    //   411: aload 5
    //   413: iconst_1
    //   414: invokevirtual 59	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   417: goto +5 -> 422
    //   420: aload 10
    //   422: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   425: invokevirtual 19	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   428: invokevirtual 29	org/hsqldb/lib/FrameworkLogger:finer	(Ljava/lang/String;)V
    //   431: aload 7
    //   433: aload 5
    //   435: invokevirtual 58	java/util/regex/Matcher:groupCount	()I
    //   438: ifle +12 -> 450
    //   441: aload 5
    //   443: iconst_1
    //   444: invokevirtual 59	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   447: goto +5 -> 452
    //   450: aload 10
    //   452: invokeinterface 42 2 0
    //   457: pop
    //   458: goto +42 -> 500
    //   461: getstatic 26	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   464: new 12	java/lang/StringBuilder
    //   467: dup
    //   468: invokespecial 13	java/lang/StringBuilder:<init>	()V
    //   471: ldc 60
    //   473: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   476: iinc 4 1
    //   479: iload 4
    //   481: invokevirtual 56	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   484: ldc 57
    //   486: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   489: aload 10
    //   491: invokevirtual 15	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   494: invokevirtual 19	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   497: invokevirtual 29	org/hsqldb/lib/FrameworkLogger:finer	(Ljava/lang/String;)V
    //   500: goto -166 -> 334
    //   503: aload 7
    //   505: iconst_0
    //   506: anewarray 52	java/lang/String
    //   509: invokeinterface 61 2 0
    //   514: checkcast 62	[Ljava/lang/String;
    //   517: astore 9
    //   519: aload_3
    //   520: invokevirtual 32	javax/security/auth/login/LoginContext:logout	()V
    //   523: aload 9
    //   525: areturn
    //   526: astore 11
    //   528: aload_3
    //   529: invokevirtual 32	javax/security/auth/login/LoginContext:logout	()V
    //   532: aload 11
    //   534: athrow
    //   535: astore_3
    //   536: getstatic 26	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   539: ldc 63
    //   541: aload_3
    //   542: invokevirtual 64	org/hsqldb/lib/FrameworkLogger:severe	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   545: new 65	java/lang/RuntimeException
    //   548: dup
    //   549: aload_3
    //   550: invokespecial 66	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   553: athrow
    //   554: astore_3
    //   555: getstatic 26	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   558: ldc 63
    //   560: aload_3
    //   561: invokevirtual 64	org/hsqldb/lib/FrameworkLogger:severe	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   564: aload_3
    //   565: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	566	0	this	JaasAuthBean
    //   0	566	1	paramString1	String
    //   0	566	2	paramString2	String
    //   64	465	3	localLoginContext	javax.security.auth.login.LoginContext
    //   535	15	3	localLoginException1	javax.security.auth.login.LoginException
    //   554	11	3	localRuntimeException	java.lang.RuntimeException
    //   72	18	4	localLoginException2	javax.security.auth.login.LoginException
    //   116	7	4	arrayOfString	String[]
    //   126	354	4	i	int
    //   129	313	5	localMatcher	java.util.regex.Matcher
    //   138	188	6	localArrayList1	java.util.ArrayList
    //   147	357	7	localArrayList2	java.util.ArrayList
    //   153	69	8	localSubject	javax.security.auth.Subject
    //   177	347	9	localObject1	Object
    //   196	294	10	localObject2	Object
    //   526	7	11	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   65	69	72	javax/security/auth/login/LoginException
    //   108	118	526	finally
    //   125	519	526	finally
    //   526	528	526	finally
    //   44	122	535	javax/security/auth/login/LoginException
    //   125	523	535	javax/security/auth/login/LoginException
    //   526	535	535	javax/security/auth/login/LoginException
    //   44	122	554	java/lang/RuntimeException
    //   125	523	554	java/lang/RuntimeException
    //   526	535	554	java/lang/RuntimeException
  }
  
  public static class UPCallbackHandler
    implements CallbackHandler
  {
    private String u;
    private char[] p;
    
    public UPCallbackHandler(String paramString1, String paramString2)
    {
      this.u = paramString1;
      this.p = paramString2.toCharArray();
    }
    
    public void handle(Callback[] paramArrayOfCallback)
      throws UnsupportedCallbackException
    {
      int i = 0;
      int j = 0;
      for (Callback localCallback : paramArrayOfCallback) {
        if ((localCallback instanceof NameCallback))
        {
          ((NameCallback)localCallback).setName(this.u);
          i = 1;
        }
        else if ((localCallback instanceof PasswordCallback))
        {
          ((PasswordCallback)localCallback).setPassword(this.p);
          j = 1;
        }
        else
        {
          throw new UnsupportedCallbackException(localCallback, "Unsupported Callback type: " + localCallback.getClass().getName());
        }
      }
      if (i == 0) {
        throw new IllegalStateException("Supplied Callbacks does not include a NameCallback");
      }
      if (j == 0) {
        throw new IllegalStateException("Supplied Callbacks does not include a PasswordCallback");
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.auth.JaasAuthBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */