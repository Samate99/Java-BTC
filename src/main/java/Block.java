import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import org.tinylog.Logger;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import static java.nio.charset.StandardCharsets.UTF_8;

public class Block {
    private String serial;
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String serial, String data, String previousHash, long timeStamp) {
        this.serial = serial;
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
    }


    public String calculateBlockHash() {
        String dataToHash = previousHash
                + (Integer.parseInt(serial))
                + (Integer.parseInt(previousHash))
                + (timeStamp)
                + (nonce)
                + data;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            Logger.info(ex.getMessage());
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }
    // standard getters and setters
}