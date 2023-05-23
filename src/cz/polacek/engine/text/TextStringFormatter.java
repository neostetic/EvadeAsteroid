package cz.polacek.engine.text;

public class TextStringFormatter {
    String text, replacement;

    public TextStringFormatter(String text, String replacement) {
        this.text = text;
        this.replacement = replacement;
    }

    public TextStringFormatter(String text, char[] replacements) {
        this.text = text;
        StringBuilder sb = new StringBuilder("");
        for (char c : replacements) {
            sb.append(c);
        }
        this.replacement = sb.toString();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }
}
