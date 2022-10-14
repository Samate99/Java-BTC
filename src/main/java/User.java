import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;
import java.util.logging.Level;
import org.tinylog.Logger;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.math.BigInteger;

public class User {
    private String Name;
    private BigInteger Privatekey;
    private BigInteger Publickey;

    public User(String name, BigInteger privatekey, BigInteger publickey) {
        this.Name = name;
        this.Privatekey = privatekey;
        this.Publickey = publickey;

    }
    Function signature() {
     return null;
    }
}