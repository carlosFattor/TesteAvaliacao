import stream.SequenceStream;
import stream.VogalStream;

/**
 * Created by carlos on 23/07/17.
 */
public class Main {

    public static void main(String[] args) {
        SequenceStream stream = new SequenceStream(args[0]);
        char first = VogalStream.first(stream);
    }
}
