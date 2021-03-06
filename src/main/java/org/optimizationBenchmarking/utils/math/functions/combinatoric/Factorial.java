package org.optimizationBenchmarking.utils.math.functions.combinatoric;

import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;

/** The factorial function */
public final class Factorial extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Factorial INSTANCE = new Factorial();

  /**
   * the factorials
   */
  static final long[] FACTORIALS = new long[] {//
  1L,// 0
      1L,// 1
      2L,// 2
      6L,// 3
      24L,// 4
      120L,// 5
      720L,// 6
      5040L,// 7
      40320L,// 8
      362880L,// 9
      3628800L,// 10
      39916800L,// 11
      479001600L,// 12
      6227020800L,// 13
      87178291200L,// 14
      1307674368000L,// 15
      20922789888000L,// 16
      355687428096000L,// 17
      6402373705728000L,// 18
      121645100408832000L,// 19
      2432902008176640000L,// 20
  };

  /**
   * the factorials
   */
  static final double[] FACTORIALS2 = new double[] {//
  1.0d,//
      1.0d, // 1
      2.0d, // 2
      6.0d, // 3
      24.0d, // 4
      120.0d, // 5
      720.0d, // 6
      5040.0d, // 7
      40320.0d, // 8
      362880.0d, // 9
      3628800.0d, // 10
      3.99168E7d, // 11
      4.790016E8d, // 12
      6.2270208E9d, // 13
      8.71782912E10d, // 14
      1.307674368E12d, // 15
      2.0922789888E13d, // 16
      3.55687428096E14d, // 17
      6.402373705728E15d, // 18
      1.21645100408832E17d, // 19
      2.43290200817664E18d, // 20
      5.109094217170944E19d, // 21
      1.1240007277776077E21d, // 22
      2.585201673888498E22d, // 23
      6.204484017332394E23d, // 24
      1.5511210043330986E25d, // 25
      4.0329146112660565E26d, // 26
      1.0888869450418352E28d, // 27
      3.0488834461171384E29d, // 28
      8.841761993739701E30d, // 29
      2.6525285981219103E32d, // 30
      8.222838654177922E33d, // 31
      2.631308369336935E35d, // 32
      8.683317618811886E36d, // 33
      2.9523279903960412E38d, // 34
      1.0333147966386144E40d, // 35
      3.719933267899012E41d, // 36
      1.3763753091226343E43d, // 37
      5.23022617466601E44d, // 38
      2.0397882081197442E46d, // 39
      8.159152832478977E47d, // 40
      3.3452526613163803E49d, // 41
      1.4050061177528798E51d, // 42
      6.041526306337383E52d, // 43
      2.6582715747884485E54d, // 44
      1.1962222086548019E56d, // 45
      5.5026221598120885E57d, // 46
      2.5862324151116818E59d, // 47
      1.2413915592536073E61d, // 48
      6.082818640342675E62d, // 49
      3.0414093201713376E64d, // 50
      1.5511187532873822E66d, // 51
      8.065817517094388E67d, // 52
      4.2748832840600255E69d, // 53
      2.308436973392414E71d, // 54
      1.2696403353658276E73d, // 55
      7.109985878048635E74d, // 56
      4.052691950487722E76d, // 57
      2.350561331282879E78d, // 58
      1.3868311854568986E80d, // 59
      8.320987112741392E81d, // 60
      5.075802138772248E83d, // 61
      3.146997326038794E85d, // 62
      1.98260831540444E87d, // 63
      1.2688693218588417E89d, // 64
      8.247650592082472E90d, // 65
      5.443449390774431E92d, // 66
      3.647111091818868E94d, // 67
      2.4800355424368305E96d, // 68
      1.711224524281413E98d, // 69
      1.197857166996989E100d, // 70
      8.504785885678622E101d, // 71
      6.123445837688608E103d, // 72
      4.4701154615126834E105d, // 73
      3.3078854415193856E107d, // 74
      2.480914081139539E109d, // 75
      1.8854947016660498E111d, // 76
      1.4518309202828584E113d, // 77
      1.1324281178206295E115d, // 78
      8.946182130782973E116d, // 79
      7.156945704626378E118d, // 80
      5.797126020747366E120d, // 81
      4.75364333701284E122d, // 82
      3.945523969720657E124d, // 83
      3.314240134565352E126d, // 84
      2.8171041143805494E128d, // 85
      2.4227095383672724E130d, // 86
      2.107757298379527E132d, // 87
      1.8548264225739836E134d, // 88
      1.6507955160908452E136d, // 89
      1.4857159644817607E138d, // 90
      1.3520015276784023E140d, // 91
      1.24384140546413E142d, // 92
      1.1567725070816409E144d, // 93
      1.0873661566567424E146d, // 94
      1.0329978488239052E148d, // 95
      9.916779348709491E149d, // 96
      9.619275968248206E151d, // 97
      9.426890448883242E153d, // 98
      9.33262154439441E155d, // 99
      9.33262154439441E157d, // 100
      9.425947759838354E159d, // 101
      9.614466715035121E161d, // 102
      9.902900716486175E163d, // 103
      1.0299016745145622E166d, // 104
      1.0813967582402903E168d, // 105
      1.1462805637347078E170d, // 106
      1.2265202031961373E172d, // 107
      1.3246418194518284E174d, // 108
      1.4438595832024928E176d, // 109
      1.5882455415227421E178d, // 110
      1.7629525510902437E180d, // 111
      1.9745068572210728E182d, // 112
      2.2311927486598123E184d, // 113
      2.543559733472186E186d, // 114
      2.925093693493014E188d, // 115
      3.3931086844518965E190d, // 116
      3.969937160808719E192d, // 117
      4.6845258497542883E194d, // 118
      5.574585761207603E196d, // 119
      6.689502913449124E198d, // 120
      8.09429852527344E200d, // 121
      9.875044200833598E202d, // 122
      1.2146304367025325E205d, // 123
      1.5061417415111404E207d, // 124
      1.8826771768889254E209d, // 125
      2.372173242880046E211d, // 126
      3.012660018457658E213d, // 127
      3.8562048236258025E215d, // 128
      4.9745042224772855E217d, // 129
      6.466855489220472E219d, // 130
      8.471580690878817E221d, // 131
      1.118248651196004E224d, // 132
      1.4872707060906852E226d, // 133
      1.992942746161518E228d, // 134
      2.6904727073180495E230d, // 135
      3.659042881952547E232d, // 136
      5.01288874827499E234d, // 137
      6.917786472619486E236d, // 138
      9.615723196941086E238d, // 139
      1.346201247571752E241d, // 140
      1.89814375907617E243d, // 141
      2.6953641378881614E245d, // 142
      3.8543707171800706E247d, // 143
      5.550293832739301E249d, // 144
      8.047926057471987E251d, // 145
      1.17499720439091E254d, // 146
      1.7272458904546376E256d, // 147
      2.5563239178728637E258d, // 148
      3.808922637630567E260d, // 149
      5.7133839564458505E262d, // 150
      8.627209774233235E264d, // 151
      1.3113358856834518E267d, // 152
      2.006343905095681E269d, // 153
      3.089769613847349E271d, // 154
      4.789142901463391E273d, // 155
      7.47106292628289E275d, // 156
      1.1729568794264138E278d, // 157
      1.8532718694937338E280d, // 158
      2.946702272495037E282d, // 159
      4.714723635992059E284d, // 160
      7.590705053947215E286d, // 161
      1.2296942187394488E289d, // 162
      2.0044015765453015E291d, // 163
      3.2872185855342945E293d, // 164
      5.423910666131586E295d, // 165
      9.003691705778433E297d, // 166
      1.5036165148649983E300d, // 167
      2.526075744973197E302d, // 168
      4.2690680090047027E304d, // 169
      7.257415615307994E306d, // 170
  };

  /** instantiate */
  private Factorial() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x1) {
    final int i;
    i = ((int) (Math.round(x1)));
    if (i <= 1) {
      return 1;
    }
    if (i <= 170) {
      return Factorial.FACTORIALS2[i];
    }
    return Double.POSITIVE_INFINITY;
  }

  /** {@inheritDoc} */
  @Override
  public final long computeAsLong(final long x1) {
    if (x1 <= 1L) {
      return 1L;
    }
    if (x1 <= 20L) {
      return Factorial.FACTORIALS[(int) x1];
    }
    return Long.MAX_VALUE;
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final long x1) {
    if (x1 <= 1L) {
      return 1L;
    }
    if (x1 <= 170L) {
      return Factorial.FACTORIALS2[(int) x1];
    }
    return Double.POSITIVE_INFINITY;
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final int x1) {
    if (x1 <= 1L) {
      return 1L;
    }
    if (x1 <= 170) {
      return Factorial.FACTORIALS2[x1];
    }
    return Double.POSITIVE_INFINITY;
  }

  /** {@inheritDoc} */
  @Override
  public final int computeAsInt(final int x1) {
    if (x1 <= 1) {
      return 1;
    }
    if (x1 <= 12) {
      return ((int) (Factorial.FACTORIALS[x1]));
    }
    return Integer.MAX_VALUE;
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link #INSTANCE} for serialization, i.e.,
   * when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link #INSTANCE})
   */
  private final Object writeReplace() {
    return Factorial.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link #INSTANCE} after serialization,
   * i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link #INSTANCE})
   */
  private final Object readResolve() {
    return Factorial.INSTANCE;
  }
}
