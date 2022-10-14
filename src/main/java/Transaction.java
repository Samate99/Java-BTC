import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import org.tinylog.Logger;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Transaction {

    String SellerSignature;
    String BuyerSignature;
    BigInteger TransactionAmount;

    public Transaction(String sellersignature,String buyersignature, BigInteger transactionAmount) {
        SellerSignature = sellersignature;
        BuyerSignature = buyersignature;
        TransactionAmount=transactionAmount;
    }

    public String  convertstring() {
        return "Transaction{" + "SellerSignature='" + SellerSignature + '\'' + ", BuyerSignature='" + BuyerSignature + '\'' + ", TransactionAmount=" + TransactionAmount + '}';
    }

}




