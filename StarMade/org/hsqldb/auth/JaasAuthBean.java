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
    //   1: getfield 42	org/hsqldb/auth/JaasAuthBean:initialized	Z
    //   4: ifne +39 -> 43
    //   7: new 31	java/lang/IllegalStateException
    //   10: dup
    //   11: new 64	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   18: ldc 67
    //   20: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: ldc 2
    //   25: invokevirtual 77	java/lang/Class:getName	()Ljava/lang/String;
    //   28: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: ldc 79
    //   33: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: invokespecial 36	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   42: athrow
    //   43: new 84	javax/security/auth/login/LoginContext
    //   46: dup
    //   47: aload_0
    //   48: getfield 29	org/hsqldb/auth/JaasAuthBean:applicationKey	Ljava/lang/String;
    //   51: new 8	org/hsqldb/auth/JaasAuthBean$UPCallbackHandler
    //   54: dup
    //   55: aload_1
    //   56: aload_2
    //   57: invokespecial 87	org/hsqldb/auth/JaasAuthBean$UPCallbackHandler:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   60: invokespecial 90	javax/security/auth/login/LoginContext:<init>	(Ljava/lang/String;Ljavax/security/auth/callback/CallbackHandler;)V
    //   63: astore_3
    //   64: aload_3
    //   65: invokevirtual 93	javax/security/auth/login/LoginContext:login	()V
    //   68: goto +39 -> 107
    //   71: astore 4
    //   73: getstatic 97	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   76: new 64	java/lang/StringBuilder
    //   79: dup
    //   80: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   83: ldc 99
    //   85: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload 4
    //   90: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   93: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: invokevirtual 107	org/hsqldb/lib/FrameworkLogger:finer	(Ljava/lang/String;)V
    //   99: new 58	org/hsqldb/auth/DenyException
    //   102: dup
    //   103: invokespecial 108	org/hsqldb/auth/DenyException:<init>	()V
    //   106: athrow
    //   107: aload_0
    //   108: getfield 38	org/hsqldb/auth/JaasAuthBean:roleSchemaValuePattern	Ljava/util/regex/Pattern;
    //   111: ifnonnull +13 -> 124
    //   114: aconst_null
    //   115: astore 4
    //   117: aload_3
    //   118: invokevirtual 111	javax/security/auth/login/LoginContext:logout	()V
    //   121: aload 4
    //   123: areturn
    //   124: iconst_0
    //   125: istore 4
    //   127: aconst_null
    //   128: astore 5
    //   130: new 113	java/util/ArrayList
    //   133: dup
    //   134: invokespecial 114	java/util/ArrayList:<init>	()V
    //   137: astore 6
    //   139: new 113	java/util/ArrayList
    //   142: dup
    //   143: invokespecial 114	java/util/ArrayList:<init>	()V
    //   146: astore 7
    //   148: aload_3
    //   149: invokevirtual 118	javax/security/auth/login/LoginContext:getSubject	()Ljavax/security/auth/Subject;
    //   152: astore 8
    //   154: aload_0
    //   155: getfield 26	org/hsqldb/auth/JaasAuthBean:roleSchemaViaCredential	Z
    //   158: ifeq +58 -> 216
    //   161: new 113	java/util/ArrayList
    //   164: dup
    //   165: aload 8
    //   167: invokevirtual 124	javax/security/auth/Subject:getPublicCredentials	()Ljava/util/Set;
    //   170: invokespecial 127	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   173: invokevirtual 131	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   176: astore 9
    //   178: aload 9
    //   180: invokeinterface 141 1 0
    //   185: ifeq +28 -> 213
    //   188: aload 9
    //   190: invokeinterface 145 1 0
    //   195: astore 10
    //   197: aload 6
    //   199: aload 10
    //   201: invokevirtual 146	java/lang/Object:toString	()Ljava/lang/String;
    //   204: invokeinterface 150 2 0
    //   209: pop
    //   210: goto -32 -> 178
    //   213: goto +60 -> 273
    //   216: new 113	java/util/ArrayList
    //   219: dup
    //   220: aload 8
    //   222: invokevirtual 153	javax/security/auth/Subject:getPrincipals	()Ljava/util/Set;
    //   225: invokespecial 127	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   228: invokevirtual 131	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   231: astore 9
    //   233: aload 9
    //   235: invokeinterface 141 1 0
    //   240: ifeq +33 -> 273
    //   243: aload 9
    //   245: invokeinterface 145 1 0
    //   250: checkcast 155	java/security/Principal
    //   253: astore 10
    //   255: aload 6
    //   257: aload 10
    //   259: invokeinterface 156 1 0
    //   264: invokeinterface 150 2 0
    //   269: pop
    //   270: goto -37 -> 233
    //   273: getstatic 97	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   276: new 64	java/lang/StringBuilder
    //   279: dup
    //   280: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   283: aload 6
    //   285: invokeinterface 160 1 0
    //   290: invokestatic 165	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   293: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   296: ldc 167
    //   298: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: aload_0
    //   302: getfield 26	org/hsqldb/auth/JaasAuthBean:roleSchemaViaCredential	Z
    //   305: ifeq +8 -> 313
    //   308: ldc 169
    //   310: goto +5 -> 315
    //   313: ldc 171
    //   315: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   321: invokevirtual 107	org/hsqldb/lib/FrameworkLogger:finer	(Ljava/lang/String;)V
    //   324: aload 6
    //   326: invokeinterface 172 1 0
    //   331: astore 9
    //   333: aload 9
    //   335: invokeinterface 141 1 0
    //   340: ifeq +162 -> 502
    //   343: aload 9
    //   345: invokeinterface 145 1 0
    //   350: checkcast 95	java/lang/String
    //   353: astore 10
    //   355: aload_0
    //   356: getfield 38	org/hsqldb/auth/JaasAuthBean:roleSchemaValuePattern	Ljava/util/regex/Pattern;
    //   359: aload 10
    //   361: invokevirtual 176	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   364: astore 5
    //   366: aload 5
    //   368: invokevirtual 179	java/util/regex/Matcher:matches	()Z
    //   371: ifeq +89 -> 460
    //   374: getstatic 97	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   377: new 64	java/lang/StringBuilder
    //   380: dup
    //   381: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   384: ldc 181
    //   386: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: iinc 4 1
    //   392: iload 4
    //   394: invokevirtual 184	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   397: ldc 186
    //   399: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: aload 5
    //   404: invokevirtual 189	java/util/regex/Matcher:groupCount	()I
    //   407: ifle +12 -> 419
    //   410: aload 5
    //   412: iconst_1
    //   413: invokevirtual 192	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   416: goto +5 -> 421
    //   419: aload 10
    //   421: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   427: invokevirtual 107	org/hsqldb/lib/FrameworkLogger:finer	(Ljava/lang/String;)V
    //   430: aload 7
    //   432: aload 5
    //   434: invokevirtual 189	java/util/regex/Matcher:groupCount	()I
    //   437: ifle +12 -> 449
    //   440: aload 5
    //   442: iconst_1
    //   443: invokevirtual 192	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   446: goto +5 -> 451
    //   449: aload 10
    //   451: invokeinterface 150 2 0
    //   456: pop
    //   457: goto +42 -> 499
    //   460: getstatic 97	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   463: new 64	java/lang/StringBuilder
    //   466: dup
    //   467: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   470: ldc 194
    //   472: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   475: iinc 4 1
    //   478: iload 4
    //   480: invokevirtual 184	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   483: ldc 186
    //   485: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   488: aload 10
    //   490: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   493: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   496: invokevirtual 107	org/hsqldb/lib/FrameworkLogger:finer	(Ljava/lang/String;)V
    //   499: goto -166 -> 333
    //   502: aload 7
    //   504: iconst_0
    //   505: anewarray 95	java/lang/String
    //   508: invokeinterface 198 2 0
    //   513: checkcast 200	[Ljava/lang/String;
    //   516: astore 9
    //   518: aload_3
    //   519: invokevirtual 111	javax/security/auth/login/LoginContext:logout	()V
    //   522: aload 9
    //   524: areturn
    //   525: astore 11
    //   527: aload_3
    //   528: invokevirtual 111	javax/security/auth/login/LoginContext:logout	()V
    //   531: aload 11
    //   533: athrow
    //   534: astore_3
    //   535: getstatic 97	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   538: ldc 204
    //   540: aload_3
    //   541: invokevirtual 208	org/hsqldb/lib/FrameworkLogger:severe	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   544: new 62	java/lang/RuntimeException
    //   547: dup
    //   548: aload_3
    //   549: invokespecial 211	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   552: athrow
    //   553: astore_3
    //   554: getstatic 97	org/hsqldb/auth/JaasAuthBean:logger	Lorg/hsqldb/lib/FrameworkLogger;
    //   557: ldc 204
    //   559: aload_3
    //   560: invokevirtual 208	org/hsqldb/lib/FrameworkLogger:severe	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   563: aload_3
    //   564: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	565	0	this	JaasAuthBean
    //   0	565	1	paramString1	String
    //   0	565	2	paramString2	String
    //   63	465	3	localLoginContext	javax.security.auth.login.LoginContext
    //   534	15	3	localLoginException1	javax.security.auth.login.LoginException
    //   553	11	3	localRuntimeException	java.lang.RuntimeException
    //   71	18	4	localLoginException2	javax.security.auth.login.LoginException
    //   115	7	4	arrayOfString	String[]
    //   125	354	4	i	int
    //   128	313	5	localMatcher	java.util.regex.Matcher
    //   137	188	6	localArrayList1	java.util.ArrayList
    //   146	357	7	localArrayList2	java.util.ArrayList
    //   152	69	8	localSubject	javax.security.auth.Subject
    //   176	347	9	localObject1	Object
    //   195	294	10	localObject2	Object
    //   525	7	11	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   64	68	71	javax/security/auth/login/LoginException
    //   107	117	525	finally
    //   124	518	525	finally
    //   525	527	525	finally
    //   43	121	534	javax/security/auth/login/LoginException
    //   124	522	534	javax/security/auth/login/LoginException
    //   525	534	534	javax/security/auth/login/LoginException
    //   43	121	553	java/lang/RuntimeException
    //   124	522	553	java/lang/RuntimeException
    //   525	534	553	java/lang/RuntimeException
  }
  
  public static class UPCallbackHandler
    implements CallbackHandler
  {
    private String field_523;
    private char[] field_524;
    
    public UPCallbackHandler(String paramString1, String paramString2)
    {
      this.field_523 = paramString1;
      this.field_524 = paramString2.toCharArray();
    }
    
    public void handle(Callback[] paramArrayOfCallback)
      throws UnsupportedCallbackException
    {
      int i = 0;
      int j = 0;
      for (Callback localCallback : paramArrayOfCallback) {
        if ((localCallback instanceof NameCallback))
        {
          ((NameCallback)localCallback).setName(this.field_523);
          i = 1;
        }
        else if ((localCallback instanceof PasswordCallback))
        {
          ((PasswordCallback)localCallback).setPassword(this.field_524);
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


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.auth.JaasAuthBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */