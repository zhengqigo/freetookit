package cn.yxffcode.easytookit.lang;

/**
 * 对整型数串的表示
 *
 * @author gaohang on 15/12/7.
 */
public interface IntsRef extends Comparable<IntsRef> {
  int element(int index);

  int length();
}
