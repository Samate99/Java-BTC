import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.function.Function;
import java.util.logging.Level;
import org.tinylog.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.math.BigInteger;

public class User {
    private String Name;
    private KeyPair keys;


    public User(String name) throws NoSuchAlgorithmException {
        this.Name = name;
        this.keys = KeyPairGenerator.getInstance("RSA").generateKeyPair();

    }



   public byte[] signature(byte[] rawData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

       Cipher cipher = Cipher.getInstance("RSA");
       cipher.init(Cipher.ENCRYPT_MODE, keys.getPublic());
       byte[] encrypted = cipher.doFinal(rawData);

       return encrypted;
   }
}