package com.eaio.uuid;

import com.eaio.util.lang.Hex;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.omg.CORBA.portable.IDLEntity;

public class UUID
  implements Comparable<UUID>, Externalizable, Cloneable, IDLEntity
{
  static final long serialVersionUID = 7435962790062944603L;
  public long time;
  public long clockSeqAndNode;
  
  public UUID()
  {
    this(UUIDGen.newTime(), UUIDGen.getClockSeqAndNode());
  }
  
  public UUID(long time, long clockSeqAndNode)
  {
    this.time = time;
    this.clockSeqAndNode = clockSeqAndNode;
  }
  
  public UUID(UUID local_u)
  {
    this(local_u.time, local_u.clockSeqAndNode);
  }
  
  public UUID(CharSequence local_s)
  {
    this(Hex.parseLong(local_s.subSequence(0, 18)), Hex.parseLong(local_s.subSequence(19, 36)));
  }
  
  public int compareTo(UUID local_t)
  {
    if (this == local_t) {
      return 0;
    }
    if (this.time > local_t.time) {
      return 1;
    }
    if (this.time < local_t.time) {
      return -1;
    }
    if (this.clockSeqAndNode > local_t.clockSeqAndNode) {
      return 1;
    }
    if (this.clockSeqAndNode < local_t.clockSeqAndNode) {
      return -1;
    }
    return 0;
  }
  
  public void writeExternal(ObjectOutput out)
    throws IOException
  {
    out.writeLong(this.time);
    out.writeLong(this.clockSeqAndNode);
  }
  
  public void readExternal(ObjectInput local_in)
    throws IOException
  {
    this.time = local_in.readLong();
    this.clockSeqAndNode = local_in.readLong();
  }
  
  public final String toString()
  {
    return toAppendable(null).toString();
  }
  
  public StringBuffer toStringBuffer(StringBuffer local_in)
  {
    StringBuffer out = local_in;
    if (out == null) {
      out = new StringBuffer(36);
    } else {
      out.ensureCapacity(out.length() + 36);
    }
    return (StringBuffer)toAppendable(out);
  }
  
  public Appendable toAppendable(Appendable local_a)
  {
    Appendable out = local_a;
    if (out == null) {
      out = new StringBuilder(36);
    }
    try
    {
      Hex.append(out, (int)(this.time >> 32)).append('-');
      Hex.append(out, (short)(int)(this.time >> 16)).append('-');
      Hex.append(out, (short)(int)this.time).append('-');
      Hex.append(out, (short)(int)(this.clockSeqAndNode >> 48)).append('-');
      Hex.append(out, this.clockSeqAndNode, 12);
    }
    catch (IOException local_ex) {}
    return out;
  }
  
  public int hashCode()
  {
    return (int)(this.time >> 32 ^ this.time ^ this.clockSeqAndNode >> 32 ^ this.clockSeqAndNode);
  }
  
  public Object clone()
  {
    try
    {
      return super.clone();
    }
    catch (CloneNotSupportedException local_ex) {}
    return null;
  }
  
  public final long getTime()
  {
    return this.time;
  }
  
  public final long getClockSeqAndNode()
  {
    return this.clockSeqAndNode;
  }
  
  public boolean equals(Object obj)
  {
    if (!(obj instanceof UUID)) {
      return false;
    }
    return compareTo((UUID)obj) == 0;
  }
  
  public static UUID nilUUID()
  {
    return new UUID(0L, 0L);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.eaio.uuid.UUID
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */