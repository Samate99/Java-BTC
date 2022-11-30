import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.logging.Level;
import org.tinylog.Logger;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;


public class BlockChain {

    public static Block genesis_block(){
        return new Block(0, null, null,  LocalDate.now(),  100);
    }


    public ArrayList<Block> block_chain = new ArrayList<>();
    public ArrayList<Transaction> pending_transaction  = new ArrayList<>();
    public String nonce = "0000";

    public Block lates_block()
    {
        return  block_chain.get(block_chain.size() - 1);
    }

    public void new_transaction( Transaction transaction) {
            if (transaction.SellerSignature != null){
                pending_transaction.add(transaction);
            }
    }

    public long last_proof() {
        return lates_block().proof;
    }


    public void new_block() throws NoSuchAlgorithmException {
        Node tree = MerkleTree.generateTree(pending_transaction);
        Block block = new Block();
        block.serial = block_chain.size();
        block.data = pending_transaction.toString();
        block.previousHash = lates_block().previousHash;
        block.timeStamp = LocalDate.from(LocalDateTime.now());
        block.merkleTree = tree;
        block.calculateBlockHash();
        block.proof = proof_of_work(block.convertstring2());


    }





    public long proof_of_work(String last_proof) throws NoSuchAlgorithmException {
        int tempproof = 0;

        while (!valid_proof((last_proof),Integer.toString(tempproof))) {
            tempproof += 1;
        }


        return tempproof;
    }

    private boolean valid_proof(String last_proof, String tempproof) throws NoSuchAlgorithmException {
        var dataToHash = last_proof + tempproof;

        var digest = MessageDigest.getInstance("SHA-256");
        var bytes = digest.digest(dataToHash.getBytes(UTF_8));
        var asd = encodeHexString(bytes);

        return asd.substring(0, 4).equals(nonce);
    }

    public String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    public String encodeHexString(byte[] byteArray) {
        StringBuffer hexStringBuffer = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            hexStringBuffer.append(byteToHex(byteArray[i]));
        }
        return hexStringBuffer.toString();
    }
}
