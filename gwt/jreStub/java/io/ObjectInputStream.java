/** Stub needed so code can have readObject and writeObject methods */
public class ObjectInputStream {
    public final void defaultReadObject() {
    }
    public final int readInt() {
       return 0;
    }
    public final Object readObject() {
       return null;
    }
}
