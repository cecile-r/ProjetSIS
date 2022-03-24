/**
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * Permet de gerer le cryptage des mots de passe
 * </ul>
 * Description des principales fonctionnalités de la classe
 * </p>
 *
 * @author garcinle
 * @version 1
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.security.Key;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author garci
 */
public class Cryptage {

    private static final SecureRandom RAND = new SecureRandom();

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    /*
    public static void main(String[] args) {

        //String salt = generateSalt(512).get();
        //String salt = "kXnMRShQyt02lLrY5UOQwaRPJEXR4BRquS6V8Jnqkxd/ifIdMWOL/+G3bqhxCYrJadNB4JP9YoGxJTopwN6XU7pWgnxWZ5jvlSnsx4RohM5HTn6QVBB3dewc++B9ZWGL416KkuoqPISCfO9Ihtq2mdPZ3wEMi2ZzLD948QCANXuiNHrmwGC+Oyk9MworVH+g2bFuPW2htFZwnnzwz1L/InuB2smwt9m25hmywPHSzW5kv9zix9NGm0y195E8KsEOfYGSJoMj9WJXmfZtwHNRPC1XgE+Lkgxr0bkdiHQ0BhikbPtYntLy9HAs8xnFZrkIIyOkyZFqfxu4z7YanHOjFSrAjKSNAgVRII2AVoc8nA5M4eZQXXzFrNCkAuJXLfCCKQjd/YrLzn+HtMJ/kX+cbDL+xQ1DDov93aQ1qGa/XWGsI4U3JlHgZwgXyFpMok8I27FgPcFadFiW9H4rxvItOQpoIAW9qbe4wLN1OrJRhGVYgWv7FB33LFZE3pv69ikRxsD3OxdYtQy1PSPpYF+1fKzdEMzZlJM3JzW1f6225tLHSKCIqK24biO5115q6Jc0pozSwKANuX7+0Nn2tFf+m66uKHon4yDsAbqbNXYjg+rA9l78KmzA0PGfe+2Z4vw+nZleTsNaT34pXE8T/DAbN01MSaBBpSN6MecFgL1e6m8=";
        //mot de passe
        //String password = "cigarette";
        //mot de passe crypté
        //String cry = hashPassword(password).get();
        //String passwordHash = key.get();
        //System.out.println(verifyPassword(password, key, SALT));
        //System.out.println(verifyPassword("jeanmariepauline09876", key, salt));
        //password = "p2FmZ+q0suJhllDAIZKFK70A5Z91nF2zwWJLUVO5K6jlUWxlCmmOx1LYgrmKXRiL5F8EbnWvsJKEbLnfNaUZZw==";
        //mot de passe crypté
        //key = hashPassword(password, SALT).get();
        //System.out.println("mdp=\n" + key);
        //String SALT = "kXnMRShQyt02lLrY5UOQwaRPJEXR4BRquS6V8Jnqkxd/ifIdMWOL/+G3bqhxCYrJadNB4JP9YoGxJTopwN6XU7pWgnxWZ5jvlSnsx4RohM5HTn6QVBB3dewc++B9ZWGL416KkuoqPISCfO9Ihtq2mdPZ3wEMi2ZzLD948QCANXuiNHrmwGC+Oyk9MworVH+g2bFuPW2htFZwnnzwz1L/InuB2smwt9m25hmywPHSzW5kv9zix9NGm0y195E8KsEOfYGSJoMj9WJXmfZtwHNRPC1XgE+Lkgxr0bkdiHQ0BhikbPtYntLy9HAs8xnFZrkIIyOkyZFqfxu4z7YanHOjFSrAjKSNAgVRII2AVoc8nA5M4eZQXXzFrNCkAuJXLfCCKQjd/YrLzn+HtMJ/kX+cbDL+xQ1DDov93aQ1qGa/XWGsI4U3JlHgZwgXyFpMok8I27FgPcFadFiW9H4rxvItOQpoIAW9qbe4wLN1OrJRhGVYgWv7FB33LFZE3pv69ikRxsD3OxdYtQy1PSPpYF+1fKzdEMzZlJM3JzW1f6225tLHSKCIqK24biO5115q6Jc0pozSwKANuX7+0Nn2tFf+m66uKHon4yDsAbqbNXYjg+rA9l78KmzA0PGfe+2Z4vw+nZleTsNaT34pXE8T/DAbN01MSaBBpSN6MecFgL1e6m8=";
        String password = "wimbled";
        String cry = hashPassword(password).get();
        System.out.println("mdp = " + password);
        System.out.println("mdp crypté = " + cry);
        
        password = "terrains";
        cry = hashPassword(password).get();
        System.out.println("mdp = " + password);
        System.out.println("mdp crypté = " + cry);
        
        String myDataBasePassword = "Retour";
        System.out.println("mdp = "+myDataBasePassword);
        String cry=cryptage(myDataBasePassword);
        System.out.println("mdp crypte = "+cry);
        System.out.println("mdp decrypte = "+decryptage(cry));
        
        myDataBasePassword = "Osurgences";
        System.out.println("mdp = "+myDataBasePassword);
        cry=cryptage(myDataBasePassword);
        System.out.println("mdp crypte = "+cry);
        System.out.println("mdp decrypte = "+decryptage(cry));
        
        myDataBasePassword = "Garcin";
        System.out.println("mdp = "+myDataBasePassword);
        cry=cryptage(myDataBasePassword);
        System.out.println("mdp crypte = "+cry);
        System.out.println("mdp decrypte = "+decryptage(cry));
    }*/
    
    /** 
     * 
     * @param password : mot de passe à crypter
     * @return mot de passe crypté
     */
    public static String cryptage(String password){
        String salt = "r9ZYeoCR2VSDGVf0M+oYYQ==";
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(salt);
        String myEncryptedPassword = textEncryptor.encrypt(password);
        return myEncryptedPassword;
    }
    
    /** 
     * 
     * @param myEncryptedPassword : mot de passe crypté
     * @return le mot de passe décrypté
     */
    public static String decryptage(String myEncryptedPassword){
        String salt = "r9ZYeoCR2VSDGVf0M+oYYQ==";
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(salt);
        String plainText = textEncryptor.decrypt(myEncryptedPassword);
        return plainText;
    }

    /**
     *
     * @param password
     * @param salt
     * @return mot de passe crypté
     */
    public static Optional<String> hashPassword(String password) {
        String salt = "r9ZYeoCR2VSDGVf0M+oYYQ==";
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }

    /**
     *
     * @param length
     * @return genére une cle de cryptage
     */
    public static Optional<String> generateSalt(final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    /**
     *
     * @param password
     * @param key
     * @return vrai si le mot de passe est bon
     */
    public static boolean verifyPassword(String password, String key) {
        Optional<String> optEncrypted = hashPassword(password);
        if (!optEncrypted.isPresent()) {
            return false;
        }
        return optEncrypted.get().equals(key);
    }

    /**
     *
     * @return un IPP au hasard composé de 10 chiffres
     */
    public static String getIPPRandom() {
        int nb;
        String ch = "";
        for (int i = 0; i < 10; i++) {
            nb = (int) (Math.random() * ((10 - 1) + 1));
            ch = ch + String.valueOf(nb);
        }
        return ch;
    }

}
