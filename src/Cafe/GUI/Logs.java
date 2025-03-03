package Cafe.GUI;

import javax.swing.*;

/**
 * Returns text to the JTextArea specified.
 * Extends OutputStream to allow for text from system.out to be displayed elsewhere.
 */
public class Logs extends java.io.OutputStream {
    private final JTextArea textArea;

    /**
     * Constructor for Logs Class.
     * @param textArea the text area modifications should be made to.
     */
    public Logs(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * Writes a single byte to the text area by converting it into a character
     * and appending it. Also ensures that the text area automatically
     * scrolls to the bottom after each write operation.
     *
     * @param b The byte to be written, interpreted as a character.
     */
    @Override
    public void write(int b) {
        textArea.append(String.valueOf((char) b));
        textArea.setCaretPosition(textArea.getDocument().getLength()); // Auto-scroll to bottom
    }

}