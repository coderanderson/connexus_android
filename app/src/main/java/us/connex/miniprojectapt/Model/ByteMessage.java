package us.connex.miniprojectapt.Model;

/**
 * Created by Moez on 10/27/17.
 */

public class ByteMessage {
    public ByteMessage(byte[] bytes)
    {
        this.byteArray = bytes;
    }
    private byte[] byteArray;

    byte[] getByteArray() {
        return byteArray;
    }
}
