/*     */ package com.eaio.uuid;
/*     */ 
/*     */ import com.eaio.util.lang.Hex;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import org.omg.CORBA.portable.IDLEntity;
/*     */ 
/*     */ public class UUID
/*     */   implements Comparable<UUID>, Externalizable, Cloneable, IDLEntity
/*     */ {
/*     */   static final long serialVersionUID = 7435962790062944603L;
/*     */   public long time;
/*     */   public long clockSeqAndNode;
/*     */ 
/*     */   public UUID()
/*     */   {
/*  83 */     this(UUIDGen.newTime(), UUIDGen.getClockSeqAndNode());
/*     */   }
/*     */ 
/*     */   public UUID(long time, long clockSeqAndNode)
/*     */   {
/*  93 */     this.time = time;
/*  94 */     this.clockSeqAndNode = clockSeqAndNode;
/*     */   }
/*     */ 
/*     */   public UUID(UUID u)
/*     */   {
/* 103 */     this(u.time, u.clockSeqAndNode);
/*     */   }
/*     */ 
/*     */   public UUID(CharSequence s)
/*     */   {
/* 115 */     this(Hex.parseLong(s.subSequence(0, 18)), Hex.parseLong(s.subSequence(19, 36)));
/*     */   }
/*     */ 
/*     */   public int compareTo(UUID t)
/*     */   {
/* 131 */     if (this == t) {
/* 132 */       return 0;
/*     */     }
/* 134 */     if (this.time > t.time) {
/* 135 */       return 1;
/*     */     }
/* 137 */     if (this.time < t.time) {
/* 138 */       return -1;
/*     */     }
/* 140 */     if (this.clockSeqAndNode > t.clockSeqAndNode) {
/* 141 */       return 1;
/*     */     }
/* 143 */     if (this.clockSeqAndNode < t.clockSeqAndNode) {
/* 144 */       return -1;
/*     */     }
/* 146 */     return 0;
/*     */   }
/*     */ 
/*     */   public void writeExternal(ObjectOutput out)
/*     */     throws IOException
/*     */   {
/* 153 */     out.writeLong(this.time);
/* 154 */     out.writeLong(this.clockSeqAndNode);
/*     */   }
/*     */ 
/*     */   public void readExternal(ObjectInput in)
/*     */     throws IOException
/*     */   {
/* 161 */     this.time = in.readLong();
/* 162 */     this.clockSeqAndNode = in.readLong();
/*     */   }
/*     */ 
/*     */   public final String toString()
/*     */   {
/* 174 */     return toAppendable(null).toString();
/*     */   }
/*     */ 
/*     */   public StringBuffer toStringBuffer(StringBuffer in)
/*     */   {
/* 186 */     StringBuffer out = in;
/* 187 */     if (out == null) {
/* 188 */       out = new StringBuffer(36);
/*     */     }
/*     */     else {
/* 191 */       out.ensureCapacity(out.length() + 36);
/*     */     }
/* 193 */     return (StringBuffer)toAppendable(out);
/*     */   }
/*     */ 
/*     */   public Appendable toAppendable(Appendable a)
/*     */   {
/* 208 */     Appendable out = a;
/* 209 */     if (out == null)
/* 210 */       out = new StringBuilder(36);
/*     */     try
/*     */     {
/* 213 */       Hex.append(out, (int)(this.time >> 32)).append('-');
/* 214 */       Hex.append(out, (short)(int)(this.time >> 16)).append('-');
/* 215 */       Hex.append(out, (short)(int)this.time).append('-');
/* 216 */       Hex.append(out, (short)(int)(this.clockSeqAndNode >> 48)).append('-');
/* 217 */       Hex.append(out, this.clockSeqAndNode, 12);
/*     */     }
/*     */     catch (IOException ex)
/*     */     {
/*     */     }
/* 222 */     return out;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 235 */     return (int)(this.time >> 32 ^ this.time ^ this.clockSeqAndNode >> 32 ^ this.clockSeqAndNode);
/*     */   }
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 246 */       return super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException ex) {
/*     */     }
/* 250 */     return null;
/*     */   }
/*     */ 
/*     */   public final long getTime()
/*     */   {
/* 260 */     return this.time;
/*     */   }
/*     */ 
/*     */   public final long getClockSeqAndNode()
/*     */   {
/* 269 */     return this.clockSeqAndNode;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 282 */     if (!(obj instanceof UUID)) {
/* 283 */       return false;
/*     */     }
/* 285 */     return compareTo((UUID)obj) == 0;
/*     */   }
/*     */ 
/*     */   public static UUID nilUUID()
/*     */   {
/* 299 */     return new UUID(0L, 0L);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.uuid.UUID
 * JD-Core Version:    0.6.2
 */