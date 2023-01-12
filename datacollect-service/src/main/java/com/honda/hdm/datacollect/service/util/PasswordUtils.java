/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.util;

import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cryptacular.bean.EncodingHashBean;
import org.cryptacular.spec.CodecSpec;
import org.cryptacular.spec.DigestSpec;
import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordGenerator;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

/**
 *
 * @author VJC80439
 */
public class PasswordUtils {
    
    private static final Logger LOGGER = LogManager.getLogger(PasswordUtils.class);
    
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 16;
    
    private static final List<CharacterRule> CHARACTER_RULES = Arrays.asList(
        // at least one upper-case character
        new CharacterRule(EnglishCharacterData.UpperCase, 1),
        // at least one lower-case character
        new CharacterRule(EnglishCharacterData.LowerCase, 1),
        // at least one digit character
        new CharacterRule(EnglishCharacterData.Digit, 1),
        // at least one symbol (special character)
        new CharacterRule(EnglishCharacterData.Special, 1)
    );
    
    private static final List<Rule> PASSWORD_POLICY = Arrays.asList(
        // Rule: Length of 8 to 16 characters
        new LengthRule(MIN_LENGTH, MAX_LENGTH),
        // Rule: Must contain characters from at least 3 of the following: upper, lower, digit, symbol
        new CharacterCharacteristicsRule(3, CHARACTER_RULES),
        // Rule:  No whitespace characters
        new WhitespaceRule()
    );
    
    public static PasswordValidator VALIDATOR = new PasswordValidator(PASSWORD_POLICY);
    
    public static final EncodingHashBean HASHER = new EncodingHashBean(
            new CodecSpec("Base64"),    // Handles base64 encoding
            new DigestSpec("SHA256"),   // Digest algorithm
            1,      // Number of hash rounds
            false); // Salted hash == false
    
    private PasswordUtils() {
    }
    
    public static PasswordUtils getInstance() {
        return PasswordUtilsHolder.INSTANCE;
    }
    
    private static class PasswordUtilsHolder {
        private static final PasswordUtils INSTANCE = new PasswordUtils();
    }
    
    /**
     * Validates if a password follows the security policy
     * @param username Required in case policy includes a validation for avoid using username or part of it as password
     * @param clearTextPassword
     * @return 
     */
    public static RuleResult validate(String username, String clearTextPassword){
        PasswordData passwordData = new PasswordData(username,  clearTextPassword);
        return VALIDATOR.validate(passwordData);
    }
    
    /**
     * Validates if a password follows the security policy
     * @param username Required in case policy includes a validation for avoid using username or part of it as password
     * @param clearTextPassword
     * @return 
     */
    public static RuleResult validate(String username, char[] clearTextPassword){
        return validate(username, new String(clearTextPassword));
    }
    
    /**
     * Applies a hash (SHA256 + Base64) over the given password
     * @param clearTextPassword
     * @return 
     */
    public static String hash(String clearTextPassword){
        return HASHER.hash(clearTextPassword);
    }
    
    /**
     * Applies a hash (SHA256 + Base64) over the given password
     * @param clearTextPassword
     * @return 
     */
    public static String hash(char[] clearTextPassword){
        return hash(new String(clearTextPassword));
    }
    
    /**
     * Generates a password with specified characters long, which complies with policy
     * @param length characters long
     * @return A random password that complies with policy
     */
    public static String generatePassword(int length){
        PasswordGenerator generator = new PasswordGenerator();
        return generator.generatePassword(length, CHARACTER_RULES);
    }
    
    /**
     * Generates a password with the MAX_LENGTH characters long, which complies with policy
     * @return A random password that complies with policy
     */
    public static String generatePassword(){
        return generatePassword(MAX_LENGTH);
    }
}
