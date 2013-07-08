package com.eaio.uuid;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

public abstract class UUIDHelper
{
  private static String _id = "IDL:com/eaio/uuid/UUID:1.0";
  private static TypeCode __typeCode = null;
  private static boolean __active = false;
  
  public static void insert(Any local_a, UUID that)
  {
    OutputStream out = local_a.create_output_stream();
    local_a.type(type());
    write(out, that);
    local_a.read_value(out.create_input_stream(), type());
  }
  
  public static UUID extract(Any local_a)
  {
    return read(local_a.create_input_stream());
  }
  
  public static synchronized TypeCode type()
  {
    if (__typeCode == null) {
      synchronized (TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active) {
            return ORB.init().create_recursive_tc(_id);
          }
          __active = true;
          StructMember[] _members0 = new StructMember[2];
          TypeCode _tcOf_members0 = null;
          _tcOf_members0 = ORB.init().get_primitive_tc(TCKind.tk_longlong);
          _members0[0] = new StructMember("time", _tcOf_members0, null);
          _tcOf_members0 = ORB.init().get_primitive_tc(TCKind.tk_longlong);
          _members0[1] = new StructMember("clockSeqAndNode", _tcOf_members0, null);
          __typeCode = ORB.init().create_struct_tc(id(), "UUID", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }
  
  public static String id()
  {
    return _id;
  }
  
  public static UUID read(InputStream istream)
  {
    UUID value = new UUID();
    value.time = istream.read_longlong();
    value.clockSeqAndNode = istream.read_longlong();
    return value;
  }
  
  public static void write(OutputStream ostream, UUID value)
  {
    ostream.write_longlong(value.time);
    ostream.write_longlong(value.clockSeqAndNode);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.eaio.uuid.UUIDHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */