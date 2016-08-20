package xyz.morecraft.dev.mtturing.objects;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {

    private int limit;
    private boolean b = false;
    private boolean b2 = false;

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
        this.b = false;
    }

    public JTextFieldLimit(int limit, boolean b) {
        super();
        this.limit = limit;
        this.b = b;
    }

    public JTextFieldLimit(int limit, boolean b, boolean b2) {
        super();
        this.limit = limit;
        this.b = b;
        this.b2 = b2;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if ((getLength() + str.length()) <= limit) {
            if (b) {
                super.insertString(offset, str.replaceAll("\\D++", ""), attr);
            } else if (b2) {
                super.insertString(offset, str.replaceAll("\\D+\\.\\D+", ""), attr);
            } else {
                super.insertString(offset, str, attr);
            }
        }
    }

    @Override
    public void replace(int off, int len, String str, AttributeSet attr) throws BadLocationException {
        if (b) {
            super.replace(off, len, str.replaceAll("\\D++", ""), attr);
        } else if (b2) {
            super.replace(off, len, str.replaceAll("\\D+\\.\\D+", ""), attr);
        } else {
            super.replace(off, len, str, attr);
        }
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }

}