/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Kuba
 */

public class Crypto {
    
    /**
     * * Encodes specified using SHA algorithm
     * 
     * @param openPswd Password to be encoded
     * @return string representing encoded password
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException 
     */
    public static String encode(String openPswd) throws NoSuchAlgorithmException, UnsupportedEncodingException  {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(openPswd.getBytes("UTF-8"));        
        byte raw[] = md.digest();
        String hash = (new BASE64Encoder()).encode(raw);
        return hash;        
    }

}
