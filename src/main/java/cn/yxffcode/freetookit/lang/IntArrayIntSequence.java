package cn.yxffcode.freetookit.lang;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author gaohang on 15/12/7.
 */
public class IntArrayIntSequence extends AbstractIntSequence {
  private final int[] source;

  public IntArrayIntSequence(final int[] word) {
    this(word, 0, word.length);
  }

  public IntArrayIntSequence(final int[] source, final int offset, final int length) {
    super(offset, length);
    this.source = source;
  }

  @Override public String toString() {
    IntArrayStringBuilder appender = new IntArrayStringBuilder();
    for (int i = 0, j = length(); i < j; i++) {
      appender.append(element(i));
    }
    return appender.toString();
  }

  @Override public int element(final int index) {
    return source[index + offset];
  }

  @Override public IntSequence slice(int offset, int length) {
    checkState(offset >= 0 && offset + length <= this.length);
    return new IntArrayIntSequence(source, offset + this.offset, length);
  }
}
