package gg.projects.urlshortener;

import gg.projects.urlshortener.util.Base62;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author gohar.gasparyan
 */
public class Base62Tests {

    @Test
    public void testCharList() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            sb.append(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            sb.append(c);
        }
        for (int i = 0; i <= 9; i++) {
            sb.append(i);
        }
        assertEquals(sb.toString(), Base62.BASE_62_CHARS);
    }

    @Test
    public void test_encodeBase10() throws Exception {
        int n = 0;
        String str = "6JaY2";
        char[] chars = str.toCharArray();
        n += Base62.BASE_62_CHARS.indexOf(chars[0]) * (int) Math.pow(62, 4);
        n += Base62.BASE_62_CHARS.indexOf(chars[1]) * (int) Math.pow(62, 3);
        n += Base62.BASE_62_CHARS.indexOf(chars[2]) * (int) Math.pow(62, 2);
        n += Base62.BASE_62_CHARS.indexOf(chars[3]) * (int) Math.pow(62, 1);
        n += Base62.BASE_62_CHARS.indexOf(chars[4]) * (int) Math.pow(62, 0);

        assertEquals(str, Base62.encodeBase10(n));
    }

    @Test
    public void test_decodeBase62() throws Exception {
        assertEquals(125, Base62.decodeBase62("cb"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_encodeBase10_invalidString() throws Exception {
        assertEquals("ss", Base62.encodeBase10(-3));
    }

    @Test(expected = IllegalStateException.class)
    public void test_decodeBase62_invalidString() throws Exception {
        assertEquals(125, Base62.decodeBase62("("));
    }

}