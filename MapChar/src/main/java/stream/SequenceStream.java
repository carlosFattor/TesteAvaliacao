package stream;

import com.sun.nio.sctp.InvalidStreamException;

/**
 * Created by carlos on 23/07/17.
 */
public class SequenceStream implements Stream {

    private final String sequence;
    private final char[] chars;
    private int index = 0;

    public SequenceStream(String sequence) {
        if(sequence == null){
            throw new IllegalArgumentException("Sequence can't be null");
        }

        this.sequence = sequence;
        this.chars = this.sequence.toCharArray();
    }

    public char getNext() {
        if (!hasNext()) {
            throw new InvalidStreamException("There's no characters to process");
        }
        return chars[index++];
    }

    public boolean hasNext() {
        return index < chars.length;
    }

    @Override
    public String toString() {
        return sequence;
    }
}
