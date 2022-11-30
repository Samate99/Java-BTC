import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.logging.Level;
import org.tinylog.Logger;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import static java.nio.charset.StandardCharsets.UTF_8;

public class Block {
    public int serial;
    public String hash;
    public String previousHash;
    public String data;
    public LocalDate timeStamp;
    public int nonce;

    public Node merkleTree;

    public long proof;

    public Block(int serial, String data, String previousHash, LocalDate timeStamp) {
        this.serial = serial;
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
    }

    public Block(int serial, String data, String previousHash, LocalDate timeStamp,long proof) {
        this.serial = serial;
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
        this.proof = proof;

    }

    public Block() {

    }

    public String convertstring2(){
     return "serial: '" + this.serial + "', data: '" + this.data + "', previousHash: '" + this.previousHash + "'" + "', timeStamp: '" + this.timeStamp + "'" + "', hash: '" + this.hash + "'"+ "', Node: '" + this.merkleTree + "'";
    }


    public String calculateBlockHash() {
        String dataToHash = previousHash
                + serial
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
    public void setProof(int proof){
        this.proof = proof;
    }
}
