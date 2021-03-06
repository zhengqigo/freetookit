package cn.yxffcode.freetookit.algorithm;

import cn.yxffcode.freetookit.collection.ImmutableIterator;
import cn.yxffcode.freetookit.dic.Dictionary;
import cn.yxffcode.freetookit.lang.IntArrayStringBuilder;
import cn.yxffcode.freetookit.lang.IntSequence;
import cn.yxffcode.freetookit.lang.StringIntSequence;

import java.util.Iterator;

/**
 * 基于词典的最短匹配过虑出关键词
 *
 * @author gaohang on 15/12/12.
 */
public class DictionaryTokenFilter implements WordTokenFilter {

  private static final int NO_SUCH_STATE = -1;
  private final Dictionary dictionary;

  public DictionaryTokenFilter(final Dictionary dictionary) {
    this.dictionary = dictionary;
  }

  @Override public Iterator<String> getMatched(final String sentence) {
    return new ImmutableIterator<String>() {

      private IntArrayStringBuilder appender;
      private IntSequence intsRef;
      private int cur;

      /**
       * 用于标识上一次匹配成功的位置
       * TODO:使用了最简单的回溯方式,可以使用AC自动机优化时间复杂度
       */
      private int lastMatched = -1;

      @Override public boolean hasNext() {
        if (appender == null) {
          appender = new IntArrayStringBuilder();
          intsRef = new StringIntSequence(sentence);
        } else {
          appender.clear();
        }

        int len = intsRef.length();
        int state = dictionary.startState();
        while (cur < len) {
          int c = intsRef.element(cur);
          int next = dictionary.nextState(state, c);
          if (next == NO_SUCH_STATE) {
            if (cur == lastMatched + 1) {
              lastMatched++;
            }
            appender.clear();
            state = dictionary.startState();
            cur = lastMatched == -1 ? cur + 1 : lastMatched + 1;
            continue;
          }
          appender.append(c);
          cur++;
          if (dictionary.isWordEnded(next)) {
            lastMatched = cur;
            return true;
          }
          state = next;
        }
        return false;
      }

      @Override public String next() {
        return appender.toString();
      }
    };
  }

  @Override
  public boolean match(String source) {
    return getMatched(source).hasNext();
  }
}
