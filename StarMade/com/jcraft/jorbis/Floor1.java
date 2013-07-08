package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

class Floor1
  extends FuncFloor
{
  static final int floor1_rangedb = 140;
  static final int VIF_POSIT = 63;
  private static float[] FLOOR_fromdB_LOOKUP = { 1.064986E-007F, 1.134195E-007F, 1.207902E-007F, 1.286398E-007F, 1.369995E-007F, 1.459025E-007F, 1.553841E-007F, 1.654818E-007F, 1.762357E-007F, 1.876886E-007F, 1.998856E-007F, 2.128753E-007F, 2.267091E-007F, 2.41442E-007F, 2.571322E-007F, 2.738421E-007F, 2.916379E-007F, 3.105902E-007F, 3.307741E-007F, 3.522697E-007F, 3.751621E-007F, 3.995423E-007F, 4.255068E-007F, 4.531586E-007F, 4.826075E-007F, 5.1397E-007F, 5.473706E-007F, 5.829419E-007F, 6.208247E-007F, 6.611694E-007F, 7.041359E-007F, 7.498946E-007F, 7.98627E-007F, 8.505263E-007F, 9.057983E-007F, 9.646622E-007F, 1.027351E-006F, 1.094114E-006F, 1.165216E-006F, 1.240938E-006F, 1.321582E-006F, 1.407465E-006F, 1.498931E-006F, 1.596339E-006F, 1.700079E-006F, 1.810559E-006F, 1.92822E-006F, 2.053526E-006F, 2.186976E-006F, 2.329098E-006F, 2.480456E-006F, 2.64165E-006F, 2.813319E-006F, 2.996144E-006F, 3.190851E-006F, 3.39821E-006F, 3.619045E-006F, 3.854231E-006F, 4.104701E-006F, 4.371447E-006F, 4.655528E-006F, 4.958071E-006F, 5.280274E-006F, 5.623416E-006F, 5.988857E-006F, 6.378047E-006F, 6.792528E-006F, 7.233945E-006F, 7.704048E-006F, 8.2047E-006F, 8.737888E-006F, 9.305725E-006F, 9.910464E-006F, 1.05545E-005F, 1.124039E-005F, 1.197086E-005F, 1.274879E-005F, 1.357728E-005F, 1.445961E-005F, 1.539927E-005F, 1.640001E-005F, 1.746577E-005F, 1.860079E-005F, 1.980958E-005F, 2.109691E-005F, 2.246791E-005F, 2.3928E-005F, 2.548298E-005F, 2.713901E-005F, 2.890265E-005F, 3.078091E-005F, 3.278123E-005F, 3.491153E-005F, 3.718028E-005F, 3.959647E-005F, 4.216967E-005F, 4.491009E-005F, 4.78286E-005F, 5.093678E-005F, 5.424693E-005F, 5.77722E-005F, 6.152657E-005F, 6.552491E-005F, 6.978308E-005F, 7.431798E-005F, 7.914758E-005F, 8.429104E-005F, 8.976875E-005F, 9.560242E-005F, 0.0001018152F, 0.0001084317F, 0.0001154782F, 0.0001229827F, 0.0001309748F, 0.0001394863F, 0.0001485509F, 0.0001582045F, 0.0001684856F, 0.0001794347F, 0.0001910954F, 0.0002035138F, 0.000216739F, 0.0002308242F, 0.0002458245F, 0.0002617996F, 0.0002788128F, 0.0002969316F, 0.0003162279F, 0.0003367782F, 0.0003586639F, 0.0003819719F, 0.0004067946F, 0.0004332304F, 0.000461384F, 0.0004913675F, 0.0005232993F, 0.0005573062F, 0.0005935231F, 0.0006320936F, 0.0006731706F, 0.000716917F, 0.0007635063F, 0.0008131233F, 0.0008659646F, 0.0009222399F, 0.0009821722F, 0.001045999F, 0.001113974F, 0.001186367F, 0.001263463F, 0.00134557F, 0.001433013F, 0.001526138F, 0.001625315F, 0.001730937F, 0.001843424F, 0.00196322F, 0.002090801F, 0.002226673F, 0.002371374F, 0.00252548F, 0.002689599F, 0.002864385F, 0.003050529F, 0.003248769F, 0.003459893F, 0.003684736F, 0.003924191F, 0.004179207F, 0.004450795F, 0.004740033F, 0.005048067F, 0.005376119F, 0.00572549F, 0.006097564F, 0.006493818F, 0.006915823F, 0.007365251F, 0.007843887F, 0.008353627F, 0.008896492F, 0.00947464F, 0.01009035F, 0.0107461F, 0.01144442F, 0.01218814F, 0.0129802F, 0.01382373F, 0.01472207F, 0.01567879F, 0.01669769F, 0.0177828F, 0.01893842F, 0.02016915F, 0.02147985F, 0.02287574F, 0.02436233F, 0.02594553F, 0.02763162F, 0.02942728F, 0.03133963F, 0.0333763F, 0.03554523F, 0.03785516F, 0.040315F, 0.04293511F, 0.04572528F, 0.04869676F, 0.05186135F, 0.0552316F, 0.05882085F, 0.06264336F, 0.06671428F, 0.0710498F, 0.07566696F, 0.08058423F, 0.08582105F, 0.0913982F, 0.09733775F, 0.103663F, 0.1103999F, 0.1175743F, 0.125215F, 0.1333522F, 0.1420181F, 0.1512473F, 0.1610762F, 0.1715438F, 0.1826917F, 0.194564F, 0.2072079F, 0.2206734F, 0.235014F, 0.2502866F, 0.2665516F, 0.2838736F, 0.3023213F, 0.3219679F, 0.3428911F, 0.3651741F, 0.388905F, 0.4141785F, 0.4410941F, 0.469759F, 0.5002865F, 0.5327979F, 0.5674221F, 0.6042964F, 0.643567F, 0.6853896F, 0.72993F, 0.777365F, 0.8278826F, 0.8816831F, 0.93898F, 1.0F };
  
  void pack(Object paramObject, Buffer paramBuffer)
  {
    InfoFloor1 localInfoFloor1 = (InfoFloor1)paramObject;
    int i = 0;
    int k = localInfoFloor1.postlist[1];
    int m = -1;
    paramBuffer.write(localInfoFloor1.partitions, 5);
    for (int n = 0; n < localInfoFloor1.partitions; n++)
    {
      paramBuffer.write(localInfoFloor1.partitionclass[n], 4);
      if (m < localInfoFloor1.partitionclass[n]) {
        m = localInfoFloor1.partitionclass[n];
      }
    }
    for (int i1 = 0; i1 < m + 1; i1++)
    {
      paramBuffer.write(localInfoFloor1.class_dim[i1] - 1, 3);
      paramBuffer.write(localInfoFloor1.class_subs[i1], 2);
      if (localInfoFloor1.class_subs[i1] != 0) {
        paramBuffer.write(localInfoFloor1.class_book[i1], 8);
      }
      for (i2 = 0; i2 < 1 << localInfoFloor1.class_subs[i1]; i2++) {
        paramBuffer.write(localInfoFloor1.class_subbook[i1][i2] + 1, 8);
      }
    }
    paramBuffer.write(localInfoFloor1.mult - 1, 2);
    paramBuffer.write(ilog2(k), 4);
    int j = ilog2(k);
    int i2 = 0;
    int i3 = 0;
    while (i2 < localInfoFloor1.partitions)
    {
      i += localInfoFloor1.class_dim[localInfoFloor1.partitionclass[i2]];
      while (i3 < i)
      {
        paramBuffer.write(localInfoFloor1.postlist[(i3 + 2)], j);
        i3++;
      }
      i2++;
    }
  }
  
  Object unpack(Info paramInfo, Buffer paramBuffer)
  {
    int i = 0;
    int j = -1;
    InfoFloor1 localInfoFloor1 = new InfoFloor1();
    localInfoFloor1.partitions = paramBuffer.read(5);
    for (int m = 0; m < localInfoFloor1.partitions; m++)
    {
      localInfoFloor1.partitionclass[m] = paramBuffer.read(4);
      if (j < localInfoFloor1.partitionclass[m]) {
        j = localInfoFloor1.partitionclass[m];
      }
    }
    for (int n = 0; n < j + 1; n++)
    {
      localInfoFloor1.class_dim[n] = (paramBuffer.read(3) + 1);
      localInfoFloor1.class_subs[n] = paramBuffer.read(2);
      if (localInfoFloor1.class_subs[n] < 0)
      {
        localInfoFloor1.free();
        return null;
      }
      if (localInfoFloor1.class_subs[n] != 0) {
        localInfoFloor1.class_book[n] = paramBuffer.read(8);
      }
      if ((localInfoFloor1.class_book[n] < 0) || (localInfoFloor1.class_book[n] >= paramInfo.books))
      {
        localInfoFloor1.free();
        return null;
      }
      for (i1 = 0; i1 < 1 << localInfoFloor1.class_subs[n]; i1++)
      {
        localInfoFloor1.class_subbook[n][i1] = (paramBuffer.read(8) - 1);
        if ((localInfoFloor1.class_subbook[n][i1] < -1) || (localInfoFloor1.class_subbook[n][i1] >= paramInfo.books))
        {
          localInfoFloor1.free();
          return null;
        }
      }
    }
    localInfoFloor1.mult = (paramBuffer.read(2) + 1);
    int k = paramBuffer.read(4);
    int i1 = 0;
    int i2 = 0;
    while (i1 < localInfoFloor1.partitions)
    {
      i += localInfoFloor1.class_dim[localInfoFloor1.partitionclass[i1]];
      while (i2 < i)
      {
        int i3 = localInfoFloor1.postlist[(i2 + 2)] = paramBuffer.read(k);
        if ((i3 < 0) || (i3 >= 1 << k))
        {
          localInfoFloor1.free();
          return null;
        }
        i2++;
      }
      i1++;
    }
    localInfoFloor1.postlist[0] = 0;
    localInfoFloor1.postlist[1] = (1 << k);
    return localInfoFloor1;
  }
  
  Object look(DspState paramDspState, InfoMode paramInfoMode, Object paramObject)
  {
    int i = 0;
    int[] arrayOfInt = new int[65];
    InfoFloor1 localInfoFloor1 = (InfoFloor1)paramObject;
    LookFloor1 localLookFloor1 = new LookFloor1();
    localLookFloor1.field_1899 = localInfoFloor1;
    localLookFloor1.field_1898 = localInfoFloor1.postlist[1];
    for (int j = 0; j < localInfoFloor1.partitions; j++) {
      i += localInfoFloor1.class_dim[localInfoFloor1.partitionclass[j]];
    }
    i += 2;
    localLookFloor1.posts = i;
    for (int k = 0; k < i; k++) {
      arrayOfInt[k] = k;
    }
    for (int n = 0; n < i - 1; n++) {
      for (i1 = n; i1 < i; i1++) {
        if (localInfoFloor1.postlist[arrayOfInt[n]] > localInfoFloor1.postlist[arrayOfInt[i1]])
        {
          int m = arrayOfInt[i1];
          arrayOfInt[i1] = arrayOfInt[n];
          arrayOfInt[n] = m;
        }
      }
    }
    for (int i1 = 0; i1 < i; i1++) {
      localLookFloor1.forward_index[i1] = arrayOfInt[i1];
    }
    for (int i2 = 0; i2 < i; i2++) {
      localLookFloor1.reverse_index[localLookFloor1.forward_index[i2]] = i2;
    }
    for (int i3 = 0; i3 < i; i3++) {
      localLookFloor1.sorted_index[i3] = localInfoFloor1.postlist[localLookFloor1.forward_index[i3]];
    }
    switch (localInfoFloor1.mult)
    {
    case 1: 
      localLookFloor1.quant_q = 256;
      break;
    case 2: 
      localLookFloor1.quant_q = 128;
      break;
    case 3: 
      localLookFloor1.quant_q = 86;
      break;
    case 4: 
      localLookFloor1.quant_q = 64;
      break;
    default: 
      localLookFloor1.quant_q = -1;
    }
    for (int i4 = 0; i4 < i - 2; i4++)
    {
      int i5 = 0;
      int i6 = 1;
      int i7 = 0;
      int i8 = localLookFloor1.field_1898;
      int i9 = localInfoFloor1.postlist[(i4 + 2)];
      for (int i10 = 0; i10 < i4 + 2; i10++)
      {
        int i11 = localInfoFloor1.postlist[i10];
        if ((i11 > i7) && (i11 < i9))
        {
          i5 = i10;
          i7 = i11;
        }
        if ((i11 < i8) && (i11 > i9))
        {
          i6 = i10;
          i8 = i11;
        }
      }
      localLookFloor1.loneighbor[i4] = i5;
      localLookFloor1.hineighbor[i4] = i6;
    }
    return localLookFloor1;
  }
  
  void free_info(Object paramObject) {}
  
  void free_look(Object paramObject) {}
  
  void free_state(Object paramObject) {}
  
  int forward(Block paramBlock, Object paramObject1, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, Object paramObject2)
  {
    return 0;
  }
  
  Object inverse1(Block paramBlock, Object paramObject1, Object paramObject2)
  {
    LookFloor1 localLookFloor1 = (LookFloor1)paramObject1;
    InfoFloor1 localInfoFloor1 = localLookFloor1.field_1899;
    CodeBook[] arrayOfCodeBook = paramBlock.field_2098.fullbooks;
    if (paramBlock.opb.read(1) == 1)
    {
      int[] arrayOfInt = null;
      if ((paramObject2 instanceof int[])) {
        arrayOfInt = (int[])paramObject2;
      }
      if ((arrayOfInt == null) || (arrayOfInt.length < localLookFloor1.posts)) {
        arrayOfInt = new int[localLookFloor1.posts];
      } else {
        for (i = 0; i < arrayOfInt.length; i++) {
          arrayOfInt[i] = 0;
        }
      }
      arrayOfInt[0] = paramBlock.opb.read(ilog(localLookFloor1.quant_q - 1));
      arrayOfInt[1] = paramBlock.opb.read(ilog(localLookFloor1.quant_q - 1));
      int i = 0;
      int j = 2;
      int m;
      int n;
      int i1;
      int i2;
      int i3;
      while (i < localInfoFloor1.partitions)
      {
        k = localInfoFloor1.partitionclass[i];
        m = localInfoFloor1.class_dim[k];
        n = localInfoFloor1.class_subs[k];
        i1 = 1 << n;
        i2 = 0;
        if (n != 0)
        {
          i2 = arrayOfCodeBook[localInfoFloor1.class_book[k]].decode(paramBlock.opb);
          if (i2 == -1) {
            return null;
          }
        }
        for (i3 = 0; i3 < m; i3++)
        {
          int i4 = localInfoFloor1.class_subbook[k][(i2 & i1 - 1)];
          i2 >>>= n;
          if (i4 >= 0)
          {
            if ((arrayOfInt[(j + i3)] = arrayOfCodeBook[i4].decode(paramBlock.opb)) == -1) {
              return null;
            }
          }
          else {
            arrayOfInt[(j + i3)] = 0;
          }
        }
        j += m;
        i++;
      }
      for (int k = 2; k < localLookFloor1.posts; k++)
      {
        m = render_point(localInfoFloor1.postlist[localLookFloor1.loneighbor[(k - 2)]], localInfoFloor1.postlist[localLookFloor1.hineighbor[(k - 2)]], arrayOfInt[localLookFloor1.loneighbor[(k - 2)]], arrayOfInt[localLookFloor1.hineighbor[(k - 2)]], localInfoFloor1.postlist[k]);
        n = localLookFloor1.quant_q - m;
        i1 = m;
        i2 = (n < i1 ? n : i1) << 1;
        i3 = arrayOfInt[k];
        if (i3 != 0)
        {
          if (i3 >= i2)
          {
            if (n > i1) {
              i3 -= i1;
            } else {
              i3 = -1 - (i3 - n);
            }
          }
          else if ((i3 & 0x1) != 0) {
            i3 = -(i3 + 1 >>> 1);
          } else {
            i3 >>= 1;
          }
          arrayOfInt[k] = (i3 + m);
          arrayOfInt[localLookFloor1.loneighbor[(k - 2)]] &= 32767;
          arrayOfInt[localLookFloor1.hineighbor[(k - 2)]] &= 32767;
        }
        else
        {
          arrayOfInt[k] = (m | 0x8000);
        }
      }
      return arrayOfInt;
    }
    return null;
  }
  
  private static int render_point(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    paramInt3 &= 32767;
    paramInt4 &= 32767;
    int i = paramInt4 - paramInt3;
    int j = paramInt2 - paramInt1;
    int k = Math.abs(i);
    int m = k * (paramInt5 - paramInt1);
    int n = m / j;
    if (i < 0) {
      return paramInt3 - n;
    }
    return paramInt3 + n;
  }
  
  int inverse2(Block paramBlock, Object paramObject1, Object paramObject2, float[] paramArrayOfFloat)
  {
    LookFloor1 localLookFloor1 = (LookFloor1)paramObject1;
    InfoFloor1 localInfoFloor1 = localLookFloor1.field_1899;
    int i = paramBlock.field_2098.field_2242.blocksizes[paramBlock.mode] / 2;
    if (paramObject2 != null)
    {
      int[] arrayOfInt = (int[])paramObject2;
      int k = 0;
      int m = 0;
      int n = arrayOfInt[0] * localInfoFloor1.mult;
      for (int i1 = 1; i1 < localLookFloor1.posts; i1++)
      {
        i2 = localLookFloor1.forward_index[i1];
        int i3 = arrayOfInt[i2] & 0x7FFF;
        if (i3 == arrayOfInt[i2])
        {
          i3 *= localInfoFloor1.mult;
          k = localInfoFloor1.postlist[i2];
          render_line(m, k, n, i3, paramArrayOfFloat);
          m = k;
          n = i3;
        }
      }
      for (int i2 = k; i2 < i; i2++) {
        paramArrayOfFloat[i2] *= paramArrayOfFloat[(i2 - 1)];
      }
      return 1;
    }
    for (int j = 0; j < i; j++) {
      paramArrayOfFloat[j] = 0.0F;
    }
    return 0;
  }
  
  private static void render_line(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float[] paramArrayOfFloat)
  {
    int i = paramInt4 - paramInt3;
    int j = paramInt2 - paramInt1;
    int k = Math.abs(i);
    int m = i / j;
    int n = i < 0 ? m - 1 : m + 1;
    int i1 = paramInt1;
    int i2 = paramInt3;
    int i3 = 0;
    k -= Math.abs(m * j);
    paramArrayOfFloat[i1] *= FLOOR_fromdB_LOOKUP[i2];
    do
    {
      i3 += k;
      if (i3 >= j)
      {
        i3 -= j;
        i2 += n;
      }
      else
      {
        i2 += m;
      }
      paramArrayOfFloat[i1] *= FLOOR_fromdB_LOOKUP[i2];
      i1++;
    } while (i1 < paramInt2);
  }
  
  static int ilog(int paramInt)
  {
    int i = 0;
    while (paramInt != 0)
    {
      i++;
      paramInt >>>= 1;
    }
    return i;
  }
  
  private static int ilog2(int paramInt)
  {
    int i = 0;
    while (paramInt > 1)
    {
      i++;
      paramInt >>>= 1;
    }
    return i;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Floor1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */