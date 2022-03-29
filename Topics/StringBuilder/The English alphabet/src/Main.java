
class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        StringBuilder englishAlphabet = new StringBuilder();
        final int asciiStart = 65;
        final int asciiEnd = 90;

        for (int i = asciiStart; i <= asciiEnd; i++) {
            englishAlphabet.append((char) i).append(" ");
        }
        englishAlphabet.deleteCharAt(englishAlphabet.length() - 1);
        return englishAlphabet;
    }
}