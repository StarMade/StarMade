package jo.sm.logic;

public class ByteUtils
{
    public static byte flipByte(byte b)
    {
        return (byte)(((b&0x01)<<7)
            |((b&0x02)<<5)
            |((b&0x04)<<3)
            |((b&0x08)<<1)
            |((b&0x10)>>1)
            |((b&0x20)>>3)
            |((b&0x40)>>5)
            |((b&0x80)>>7));
    }
    
    public static boolean getBit(byte[] bytes, int off)
    {
        byte b = bytes[off/8];
        return (b&(0x01<<(off%8))) != 0;
    }
    
    public static void setBit(byte[] bytes, int off, boolean v)
    {
        if (v)
            bytes[off/8] |= (0x01<<(off%8));
        else
            bytes[off/8] &= ~(0x01<<(off%8));
    }
    
    public static void flipBits(byte[] bytes, int start, int bitLength)
    {
        for (int i = 0; i < bitLength/2; i++)
        {
            boolean leftBit = getBit(bytes, start*8 + i);
            boolean rightBit = getBit(bytes, start*8 + bitLength - i - 1);
            setBit(bytes, start*8 + i, rightBit);
            setBit(bytes, start*8 + bitLength - i - 1, leftBit);
        }
    }

    public static void copyBits(byte[] from, int fromByteOffset, int fromBitOffset, byte[] to, int toByteOffset, int toBitOffset, int bits)
    {
        for (int i = 0; i < bits; i++)
        {
            boolean bit = getBit(from, fromByteOffset*8 + fromBitOffset + i);
            setBit(to, toByteOffset*8 + toBitOffset + i, bit);
        }
    }

    public static Object[] toArray(byte[] byteArray)
    {
        if (byteArray == null)
            return null;
        Byte[] objArray = new Byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++)
            objArray[i] = new Byte(byteArray[i]);
        return objArray;
    }

    // assumes MSB first
    public static int toUnsignedInt(byte[] bytes)
    {
        return toUnsignedInt(bytes, 0, bytes.length);
    }

    public static int toUnsignedInt(byte[] bytes, int o, int l)
    {
        long v = 0;
        for (int i = 0; i < l; i++)
            v = (v<<8) | (bytes[o + i]&0xff);
        return (int)v;
    }

    public static int toUnsignedInt(byte b1, byte b2)
    {
        return ((((int)b1)&0xff)<<8 | ((int)b2&0xff));
    }

    // assumes MSB first
    public static int toSignedInt(byte[] bytes)
    {
        return toSignedInt(bytes, 0, bytes.length);
    }
    public static int toSignedInt(byte[] bytes, int o, int l)
    {
        int v = 0;
        int max = 1;
        for (int i = 0; i < l; i++)
        {
            v = (v<<8) | (bytes[o + i]&0xff);
            max = max<<8;
        }
        if ((bytes[0]&0x80) == 0x80)
            return v - max;
        else
            return v;
    }

    public static String toString(byte[] bytes)
    {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes)
        {
            if (sb.length() > 0)
                sb.append('.');
            String s = Integer.toHexString(b);
            if (s.length() > 2)
                s = s.substring(s.length() - 2);
            else if (s.length() == 1)
                s = "0"+s;
            sb.append(s);
        }
        return sb.toString();
    }
    
    public static String toStringDump(byte[] b)
    {
        StringBuffer sb = new StringBuffer();
        if (b != null)
        {
            for (int i = 0; i < b.length; i += 16)
            {
                sb.append(StringUtils.zeroPrefix(Integer.toHexString(i), 4));
                for (int j = 0; j < 16; j++)
                {
                    if (j%4 == 0)
                        sb.append("  ");
                    sb.append(" ");
                    if (i + j < b.length)
                        sb.append(StringUtils.zeroPrefix(Integer.toHexString(b[i+j]&0xff), 2));
                    else
                        sb.append("  ");
                }
                sb.append("    ");
                for (int j = 0; j < 16; j++)
                {
                    if (i + j < b.length)
                    {
                        char ch = (char)(b[i+j]&0xff);
                        if ((ch >= ' ') && (ch <= '~'))
                            sb.append(ch);
                        else
                            sb.append('?');
                    }
                    else
                        sb.append(" ");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
