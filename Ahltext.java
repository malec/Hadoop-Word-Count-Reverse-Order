import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

public class Ahltext implements Writable, WritableComparable<Ahltext> {
    private Text text;

    public Ahltext(Text _text) {
        this.text = _text;
    }

    public Ahltext(String textString) {
        this.text = new Text(textString);
    }

    public Ahltext() {
        this.text = new Text();
    }

    public Text getText() {
        return text;
    }

    public void setText(String textString) {
        this.text = new Text(textString);
    }

    @Override
    public int compareTo(Ahltext other) { 
        // A compareTo B
        int returnVal = this.text.compareTo(other.getText()); 
        // return 1: A < B
        // return 0: A = B
        // return -1: A > B
        if (returnVal != 0) { 
            return -1*returnVal;
        }
        if (this.text.toString().equals("*")) {
            return -1;
        } else if (other.getText().toString().equals("*")) {
            return 1;
        }
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        this.text.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.text.readFields(in);
    }

    @Override
    public String toString() {
        return text.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Ahltext textO = (Ahltext) o;

        if (text != null ? !text.equals(textO.text) : textO.text != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}