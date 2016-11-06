package ru.stqa.pft;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Primes;

/**
 * Created by Katya on 20.10.2016.
 */
public class PrimeTests {

  @Test
  public void testPrime(){
    Assert.assertTrue(Primes.isPrime(Integer.  MAX_VALUE));
  }

  @Test
  public void testPrimeFast(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.  MAX_VALUE));
  }

  @Test
  public void testPrimeSuperFast(){
    Assert.assertTrue(Primes.isPrimeSuperFast(Integer.  MAX_VALUE));
  }

  @Test(enabled = false)
  public void testPrimeLong(){
    long n=Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test
  public void testNonPrime(){
    Assert.assertFalse(Primes.isPrime(Integer.  MAX_VALUE - 2));
  }
}
