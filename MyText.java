import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

public class MyText implements Writable, WritableComparable<MyText> {
    private Text text;

    public MyText(Text _text) {
        this.text = _text;
    }

    public MyText(String textString) {
        this.text = new Text(textString);
    }

    public MyText() {
        this.text = new Text();
    }

    public Text getText() {
        return text;
    }

    public void setText(String textString) {
        this.text = new Text(textString);
    }

    @Override
    public int compareTo(MyText other) { 
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

        MyText textO = (MyText) o;

        if (text != null ? !text.equals(textO.text) : textO.text != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (text != null) ? text.hashCode() : 0;
        result = 163 * result;
        return result;
    }
}