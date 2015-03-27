/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 *
 * @author Doreen
 */
public class Rowo {
    
    BigDecimal[] big;
    BigDecimal abso;
    BigDecimal threshold = new BigDecimal("0.000001");

    Rowo(BigDecimal[] biggie)
    {
        big = biggie; 
    }
    public BigDecimal[] getBD()
    {
        return big;   
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rowo other = (Rowo) obj;
        int count = 0;
        for (int j = 0; j < this.big.length; ++j) {
            abso = this.big[j].subtract(other.big[j]);
            if (abso.compareTo(threshold) == -1) {
                // System.out.println("abso: " + abso);
                // System.out.println("count: " + count);
                count += 1;
            }
            }
        if (count == this.big.length) {
            return true;
        }
        return false;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Arrays.deepHashCode(this.big);
        return hash;
    }
    
}
