package examples.org.optimizationBenchmarking;

import java.util.Random;

import org.optimizationBenchmarking.utils.ErrorUtils;
import org.optimizationBenchmarking.utils.text.textOutput.ITextOutput;

/**
 * A class which can generate random text in the style of <a
 * href="http://en.wikipedia.org/wiki/Lorem_ipsum">Lorem Ipsum</a>.
 */
public final class LoremIpsum {

  /** the internal text */
  private static final String[] TEXT = { "lorem", //$NON-NLS-1$
      "ipsum", //$NON-NLS-1$
      "dolor", //$NON-NLS-1$
      "sit", //$NON-NLS-1$
      "amet", //$NON-NLS-1$
      "consectetur", //$NON-NLS-1$
      "adipisici", //$NON-NLS-1$
      "elit", //$NON-NLS-1$
      "sed", //$NON-NLS-1$
      "eiusmod", //$NON-NLS-1$
      "tempor", //$NON-NLS-1$
      "incidunt", //$NON-NLS-1$
      "ut", //$NON-NLS-1$
      "labore", //$NON-NLS-1$
      "et", //$NON-NLS-1$
      "dolore", //$NON-NLS-1$
      "magna", //$NON-NLS-1$
      "aliqua", //$NON-NLS-1$
      "enim", //$NON-NLS-1$
      "ad", //$NON-NLS-1$
      "minim", //$NON-NLS-1$
      "veniam", //$NON-NLS-1$
      "quis", //$NON-NLS-1$
      "nostrud", //$NON-NLS-1$
      "exercitation", //$NON-NLS-1$
      "ullamco", //$NON-NLS-1$
      "laboris", //$NON-NLS-1$
      "nisi", //$NON-NLS-1$
      "aliquid", //$NON-NLS-1$
      "ex", //$NON-NLS-1$
      "ea", //$NON-NLS-1$
      "commodi", //$NON-NLS-1$
      "consequat", //$NON-NLS-1$
      "aute", //$NON-NLS-1$
      "iure", //$NON-NLS-1$
      "reprehenderit", //$NON-NLS-1$
      "in", //$NON-NLS-1$
      "voluptate", //$NON-NLS-1$
      "velit", //$NON-NLS-1$
      "esse", //$NON-NLS-1$
      "cillum", //$NON-NLS-1$
      "eu", //$NON-NLS-1$
      "fugiat", //$NON-NLS-1$
      "nulla", //$NON-NLS-1$
      "pariatur", //$NON-NLS-1$
      "excepteur", //$NON-NLS-1$
      "sint", //$NON-NLS-1$
      "obcaecat", //$NON-NLS-1$
      "cupiditat", //$NON-NLS-1$
      "non", //$NON-NLS-1$
      "proident", //$NON-NLS-1$
      "sunt", //$NON-NLS-1$
      "culpa", //$NON-NLS-1$
      "qui", //$NON-NLS-1$
      "officia", //$NON-NLS-1$
      "deserunt", //$NON-NLS-1$
      "mollit", //$NON-NLS-1$
      "anim", //$NON-NLS-1$
      "id", //$NON-NLS-1$
      "est", //$NON-NLS-1$
      "laborum", //$NON-NLS-1$
      "duis", //$NON-NLS-1$
      "autem", //$NON-NLS-1$
      "vel", //$NON-NLS-1$
      "eum", //$NON-NLS-1$
      "iriure", //$NON-NLS-1$
      "hendrerit", //$NON-NLS-1$
      "vulputate", //$NON-NLS-1$      
      "molestie", //$NON-NLS-1$
      "illum", //$NON-NLS-1$
      "feugiat", //$NON-NLS-1$
      "facilisis", //$NON-NLS-1$
      "at", //$NON-NLS-1$
      "vero", //$NON-NLS-1$
      "eros", //$NON-NLS-1$
      "accumsan", //$NON-NLS-1$
      "iusto", //$NON-NLS-1$
      "odio", //$NON-NLS-1$
      "dignissim", //$NON-NLS-1$
      "qui", //$NON-NLS-1$
      "blandit", //$NON-NLS-1$
      "praesent", //$NON-NLS-1$
      "luptatum", //$NON-NLS-1$
      "zzril", //$NON-NLS-1$
      "delenit", //$NON-NLS-1$
      "augue", //$NON-NLS-1$
      "te", //$NON-NLS-1$
      "feugait", //$NON-NLS-1$
      "facilisi", //$NON-NLS-1$
      "consectetuer", //$NON-NLS-1$
      "adipiscing", //$NON-NLS-1$
      "elit", //$NON-NLS-1$
      "diam", //$NON-NLS-1$
      "nonummy", //$NON-NLS-1$
      "nibh", //$NON-NLS-1$
      "euismod", //$NON-NLS-1$
      "tincidunt", //$NON-NLS-1$
      "laoreet", //$NON-NLS-1$
      "aliquam", //$NON-NLS-1$
      "erat", //$NON-NLS-1$
      "volutpat", //$NON-NLS-1$
      "wisi", //$NON-NLS-1$
      "enim", //$NON-NLS-1$
      "minim", //$NON-NLS-1$
      "veniam", //$NON-NLS-1$
      "nostrud", //$NON-NLS-1$
      "exerci", //$NON-NLS-1$
      "tation", //$NON-NLS-1$
      "ullamcorper", //$NON-NLS-1$
      "suscipit", //$NON-NLS-1$
      "lobortis", //$NON-NLS-1$
      "nisl", //$NON-NLS-1$
      "aliquip", //$NON-NLS-1$
      "commodo", //$NON-NLS-1$
      "autem", //$NON-NLS-1$
      "eum", //$NON-NLS-1$
      "iriure", //$NON-NLS-1$
      "hendrerit", //$NON-NLS-1$
      "vulputate", //$NON-NLS-1$      
      "molestie", //$NON-NLS-1$
      "illum", //$NON-NLS-1$
      "feugiat", //$NON-NLS-1$
      "facilisis", //$NON-NLS-1$
      "eros", //$NON-NLS-1$
      "accumsan", //$NON-NLS-1$
      "iusto", //$NON-NLS-1$
      "odio", //$NON-NLS-1$
      "dignissim", //$NON-NLS-1$
      "qui", //$NON-NLS-1$
      "blandit", //$NON-NLS-1$
      "praesent", //$NON-NLS-1$
      "luptatum", //$NON-NLS-1$
      "zzril", //$NON-NLS-1$
      "delenit", //$NON-NLS-1$
      "augue", //$NON-NLS-1$
      "feugait", //$NON-NLS-1$
      "facilisi", //$NON-NLS-1$
      "nam", //$NON-NLS-1$
      "liber", //$NON-NLS-1$
      "cum", //$NON-NLS-1$
      "soluta", //$NON-NLS-1$
      "nobis", //$NON-NLS-1$
      "eleifend", //$NON-NLS-1$
      "option", //$NON-NLS-1$
      "congue", //$NON-NLS-1$
      "nihil", //$NON-NLS-1$
      "imperdiet", //$NON-NLS-1$
      "doming", //$NON-NLS-1$
      "quod", //$NON-NLS-1$
      "mazim", //$NON-NLS-1$
      "placerat", //$NON-NLS-1$
      "facer", //$NON-NLS-1$
      "possim", //$NON-NLS-1$
      "assum", //$NON-NLS-1$
      "consectetuer", //$NON-NLS-1$
      "adipiscing", //$NON-NLS-1$
      "elit", //$NON-NLS-1$
      "nonummy", //$NON-NLS-1$
      "nibh", //$NON-NLS-1$
      "euismod", //$NON-NLS-1$
      "tincidunt", //$NON-NLS-1$
      "laoreet", //$NON-NLS-1$
      "aliquam", //$NON-NLS-1$
      "volutpat", //$NON-NLS-1$
      "wisi", //$NON-NLS-1$
      "enim", //$NON-NLS-1$
      "minim", //$NON-NLS-1$
      "veniam", //$NON-NLS-1$
      "nostrud", //$NON-NLS-1$
      "exerci", //$NON-NLS-1$
      "tation", //$NON-NLS-1$
      "ullamcorper", //$NON-NLS-1$
      "suscipit", //$NON-NLS-1$
      "lobortis", //$NON-NLS-1$
      "nisl", //$NON-NLS-1$
      "aliquip", //$NON-NLS-1$
      "commodo", //$NON-NLS-1$
      "autem", //$NON-NLS-1$
      "eum", //$NON-NLS-1$
      "iriure", //$NON-NLS-1$
      "hendrerit", //$NON-NLS-1$
      "vulputate", //$NON-NLS-1$      
      "molestie", //$NON-NLS-1$
      "illum", //$NON-NLS-1$
      "feugiat", //$NON-NLS-1$
      "facilisis", //$NON-NLS-1$
      "eos", //$NON-NLS-1$
      "accusam", //$NON-NLS-1$
      "justo", //$NON-NLS-1$
      "duo", //$NON-NLS-1$
      "dolores", //$NON-NLS-1$
      "rebum", //$NON-NLS-1$
      "stet", //$NON-NLS-1$
      "clita", //$NON-NLS-1$
      "kasd", //$NON-NLS-1$
      "gubergren", //$NON-NLS-1$
      "no", //$NON-NLS-1$
      "sea", //$NON-NLS-1$
      "takimata", //$NON-NLS-1$
      "sanctus", //$NON-NLS-1$
      "consetetur", //$NON-NLS-1$
      "sadipscing", //$NON-NLS-1$
      "elitr", //$NON-NLS-1$
      "nonumy", //$NON-NLS-1$
      "eirmod", //$NON-NLS-1$
      "invidunt", //$NON-NLS-1$
      "aliquyam", //$NON-NLS-1$
      "voluptua", //$NON-NLS-1$
  };

  /**
   * Append some random lorem ipsum
   * 
   * @param out
   *          the output
   * @param rand
   *          the randomizer
   */
  public static final void appendLoremIpsum(final ITextOutput out,
      final Random rand) {
    LoremIpsum.appendLoremIpsum(out, rand, Integer.MAX_VALUE);
  }

  /**
   * Append some random lorem ipsum
   * 
   * @param out
   *          the output
   * @param rand
   *          the randomizer
   * @param maxLength
   *          the maximum length of the lorem ipsum
   */
  public static final void appendLoremIpsum(final ITextOutput out,
      final Random rand, final int maxLength) {
    boolean firstText, newSentence;
    int count;
    String s;

    if (maxLength <= 0) {
      return;
    }

    firstText = newSentence = true;
    count = 0;
    do {
      newSentence = true;
      if (firstText) {
        firstText = false;
      } else {
        out.append(' ');
      }

      do {
        s = LoremIpsum.TEXT[rand.nextInt(LoremIpsum.TEXT.length)];
        if (newSentence) {
          out.append(Character.toUpperCase(s.charAt(0)));
          s = s.substring(1);
          newSentence = false;
        } else {
          out.append(' ');
        }
        out.append(s);

      } while (((++count) < maxLength) && (rand.nextInt(10) > 0));
      out.append('.');
    } while (rand.nextBoolean());
  }

  /** the forbidden constructor */
  private LoremIpsum() {
    ErrorUtils.doNotCall();
  }
}
