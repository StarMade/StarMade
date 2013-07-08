/*  1:   */package com.eaio.uuid;
/*  2:   */
/*  3:   */import org.omg.CORBA.Any;
/*  4:   */import org.omg.CORBA.ORB;
/*  5:   */import org.omg.CORBA.StructMember;
/*  6:   */import org.omg.CORBA.TCKind;
/*  7:   */import org.omg.CORBA.TypeCode;
/*  8:   */import org.omg.CORBA.portable.InputStream;
/*  9:   */import org.omg.CORBA.portable.OutputStream;
/* 10:   */
/* 15:   */public abstract class UUIDHelper
/* 16:   */{
/* 17:17 */  private static String _id = "IDL:com/eaio/uuid/UUID:1.0";
/* 18:   */  
/* 19:   */  public static void insert(Any a, UUID that)
/* 20:   */  {
/* 21:21 */    OutputStream out = a.create_output_stream();
/* 22:22 */    a.type(type());
/* 23:23 */    write(out, that);
/* 24:24 */    a.read_value(out.create_input_stream(), type());
/* 25:   */  }
/* 26:   */  
/* 27:   */  public static UUID extract(Any a)
/* 28:   */  {
/* 29:29 */    return read(a.create_input_stream());
/* 30:   */  }
/* 31:   */  
/* 32:32 */  private static TypeCode __typeCode = null;
/* 33:33 */  private static boolean __active = false;
/* 34:   */  
/* 35:   */  public static synchronized TypeCode type() {
/* 36:36 */    if (__typeCode == null)
/* 37:   */    {
/* 38:38 */      synchronized (TypeCode.class)
/* 39:   */      {
/* 40:40 */        if (__typeCode == null)
/* 41:   */        {
/* 42:42 */          if (__active)
/* 43:   */          {
/* 44:44 */            return ORB.init().create_recursive_tc(_id);
/* 45:   */          }
/* 46:46 */          __active = true;
/* 47:47 */          StructMember[] _members0 = new StructMember[2];
/* 48:48 */          TypeCode _tcOf_members0 = null;
/* 49:49 */          _tcOf_members0 = ORB.init().get_primitive_tc(TCKind.tk_longlong);
/* 50:50 */          _members0[0] = new StructMember("time", _tcOf_members0, null);
/* 51:   */          
/* 54:54 */          _tcOf_members0 = ORB.init().get_primitive_tc(TCKind.tk_longlong);
/* 55:55 */          _members0[1] = new StructMember("clockSeqAndNode", _tcOf_members0, null);
/* 56:   */          
/* 59:59 */          __typeCode = ORB.init().create_struct_tc(id(), "UUID", _members0);
/* 60:60 */          __active = false;
/* 61:   */        }
/* 62:   */      }
/* 63:   */    }
/* 64:64 */    return __typeCode;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public static String id()
/* 68:   */  {
/* 69:69 */    return _id;
/* 70:   */  }
/* 71:   */  
/* 72:   */  public static UUID read(InputStream istream)
/* 73:   */  {
/* 74:74 */    UUID value = new UUID();
/* 75:75 */    value.time = istream.read_longlong();
/* 76:76 */    value.clockSeqAndNode = istream.read_longlong();
/* 77:77 */    return value;
/* 78:   */  }
/* 79:   */  
/* 80:   */  public static void write(OutputStream ostream, UUID value)
/* 81:   */  {
/* 82:82 */    ostream.write_longlong(value.time);
/* 83:83 */    ostream.write_longlong(value.clockSeqAndNode);
/* 84:   */  }
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.uuid.UUIDHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */