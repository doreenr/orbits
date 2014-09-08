/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Doreen
 */
public final class Big {
    BigDecimal B, D;
    Random r;
    
    public Big() {
        r = new Random();
        B = newRandomBigDecimal(r, 3);
    }
    
    public BigDecimal getB() {
        return B;
    }
        
    public BigDecimal newRandomBigDecimal(Random r, int precision) {
    BigInteger n = BigInteger.TEN.pow(precision);
    return new BigDecimal(newRandomBigInteger(n, r), precision);
    }

    public BigInteger newRandomBigInteger(BigInteger n, Random rnd) {
    BigInteger i;
    do {
        i = new BigInteger(n.bitLength(), rnd);
    }   while (i.compareTo(n) >= 0);

    return i;
}
}
