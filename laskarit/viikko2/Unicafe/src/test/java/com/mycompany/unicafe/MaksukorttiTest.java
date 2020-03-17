package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoKortissaAluksiOikein() {
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(15);
        assertTrue(kortti.saldo() == 25);
    }
    
    @Test
    public void saldoVaheneeOikeinJosSaldoaTarpeeksi() {
        kortti.otaRahaa(5);
        assertTrue(kortti.saldo() == 5);
    }
    
    @Test
    public void saldoEiMuutuJosEiSaldoaTarpeeksi() {
        kortti.otaRahaa(15);
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosRahatRiittavat() {
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosRahatEiRiita() {
        assertFalse(kortti.otaRahaa(15));
    }
    
    @Test
    public void toStringOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
}
