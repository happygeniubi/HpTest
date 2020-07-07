package com.happyge.empl.utils;


import com.happyge.empl.model.HllcUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
 
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;
 
    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }


    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
 
    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }
    
	/**
	 * 加密密码
	 */
    public  HllcUser encryptPassword(HllcUser hllcUser) {
    	//设置随机salt
        hllcUser.setUsalt(randomNumberGenerator.nextBytes().toHex());
        //密码明文+随机salt=密码密文         
        String newPassword = new SimpleHash(
            algorithmName,
            hllcUser.getUpassword(),
            //credentialsSalt=username+salt
            ByteSource.Util.bytes(hllcUser.getUsalt()),
            hashIterations).toHex();
        //设置密码密文
        hllcUser.setUpassword(newPassword);
        return hllcUser;
    }
    
   
}
