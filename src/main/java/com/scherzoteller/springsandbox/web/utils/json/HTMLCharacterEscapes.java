package com.scherzoteller.springsandbox.web.utils.json;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

public class HTMLCharacterEscapes extends CharacterEscapes {

    private static final long serialVersionUID = 1L;
    private static final String NEWLINE_HTML = "<br/>";
    private static final char NEWLINE_CHAR = '\n';
    private char[] encodedCharacters = {'<', '>', NEWLINE_CHAR};

    private final int[] asciiEscapes;
    
    public HTMLCharacterEscapes() {
        // set of characters known to require escaping (double-quote, backslash etc)
        int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
        // force escaping
        for (char c : encodedCharacters) {
            esc[c] = CharacterEscapes.ESCAPE_CUSTOM;
        }
        asciiEscapes = esc;
    }
    // this method gets called for character codes 0 - 127
    @Override public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }
    // and this for others; we don't need anything special here
    @Override public SerializableString getEscapeSequence(int ch) {
        if (ch == NEWLINE_CHAR) {
            return new SerializedString(NEWLINE_HTML);      
        } else {
            return new SerializedString("&#"+ch+";");      
        }
        
    }    
}
