package pers.kaigian.learning.netty.splitpacket;

/**
 * @author BrianHu
 * @create 2021-04-14 14:03
 **/
public class MyProtocol {
    private int length;

    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
