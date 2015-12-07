package cn.yxffcode.easytookit.automaton;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author gaohang on 15/12/7.
 */
public class AutomatonTest {

    @Test
    public void testAutomaton() {
        Automaton automaton = new Automaton.DictionaryBuilder().addWord(new StringIntsRef("hello"))
                                                               .addWord(new StringIntsRef("helle"))
                                                               .addWord(new StringIntsRef("halle"))
                                                               .addWord(new StringIntsRef("halla"))
                                                               .addWord(new StringIntsRef("hallb"))
                                                               .addWord(new StringIntsRef("abc"))
                                                               .addWord(new StringIntsRef("dkk"))
                                                               .addWord(new StringIntsRef("dkkk"))
                                                               .build();
        assertTrue(automaton.run("hello"));
        assertTrue(automaton.run("helle"));
        assertTrue(automaton.run("halle"));
        assertTrue(automaton.run("halla"));
        assertTrue(automaton.run("hallb"));
        assertTrue(automaton.run("abc"));
        assertTrue(automaton.run("dkk"));
        assertTrue(automaton.run("dkkk"));
        assertFalse(automaton.run("abcd"));
        assertFalse(automaton.run("eeee"));
    }
}