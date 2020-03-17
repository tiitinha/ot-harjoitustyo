/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tiitinha
 */
public class KassapaateTest {

    private Kassapaate kassapaate;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }

    @Test
    public void luodunKassapaatteenSaldoOikein() {
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }

    @Test
    public void luodunKassapaatteenMyydytEdullisetLounaatNolla() {
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void luodunKassapaatteenMyydytMaukkaatLounaatNolla() {
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void riittavallaMaksullaVaihtorahatOikein() {
        assertTrue(kassapaate.syoEdullisesti(300) == 60);
    }

    @Test
    public void riittavallaMaksullaKassaKasvaaOikein() {
        kassapaate.syoEdullisesti(300);
        assertTrue(kassapaate.kassassaRahaa() == 100240);
    }

    @Test
    public void riittavallaMaksullaMyytyjenEdullistenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(300);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 1);
    }

    @Test
    public void riittavallaMaksullaMyytyjenMaukkaidenLounaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(1000);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void eiRiittavallaMaksullaKaikkiRahatPalautetaan() {
        assertTrue(kassapaate.syoEdullisesti(100) == 100);
    }

    @Test
    public void eiRiittavallaMaksullaKassaEiKasva() {
        kassapaate.syoEdullisesti(100);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }

    @Test
    public void eiRiittavallaMaksullaMyytyjenEdullistenMaaraEiKasva() {
        kassapaate.syoEdullisesti(100);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void eiRiittavallaMaksullaMyytyjenMaukkaidenMaaraEiKasva() {
        kassapaate.syoMaukkaasti(100);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void josKortillaTarpeeksiRahaaPalautetaanTrue() {
        Maksukortti kortti = new Maksukortti(1000);
        assertTrue(kassapaate.syoEdullisesti(kortti));
    }

    @Test
    public void josKortillaTarpeeksiRahaaVeloitetaanKortilta() {
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 760);
    }

    @Test
    public void josKortillaTarpeeksiRahaaEdullistenMaaraKasvaa() {
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 1);
    }

    @Test
    public void josKortillaTarpeeksiRahaaMaukkaidenMaaraKasvaa() {
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void josKortillaEiTarpeeksiRahaaPalautetaanFalse() {
        Maksukortti kortti = new Maksukortti(0);
        assertFalse(kassapaate.syoEdullisesti(kortti));
    }

    @Test
    public void josKortillaEiTarpeeksiRahaaKortinRahamaaraEiMuutu() {
        Maksukortti kortti = new Maksukortti(10);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 10);
    }

    @Test
    public void josKortillaEiTarpeeksiRahaaMaukkaidenMaaraEiKasva() {
        Maksukortti kortti = new Maksukortti(0);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void josKortillaEiTarpeeksiRahaaEdullistenMaaraEiKasva() {
        Maksukortti kortti = new Maksukortti(0);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void kassassaOlevaRahamaaraEiMuutuKortillaOstettaessa() {
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }

    @Test
    public void kortilleLadattaessaKortinSaldoMuuttuu() {
        Maksukortti kortti = new Maksukortti(0);
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertTrue(kortti.saldo() == 100);
    }

    @Test
    public void kortilleLadattaessaKassaKasvaa() {
        Maksukortti kortti = new Maksukortti(0);
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertTrue(kassapaate.kassassaRahaa() == 100100);
    }

    @Test
    public void negatiivinenLatausEiMuutaKassaa() {
        Maksukortti kortti = new Maksukortti(0);
        kassapaate.lataaRahaaKortille(kortti, -100);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }

    @Test
    public void negatiivinneLatausEiMuutaKortinSaldoa() {
        Maksukortti kortti = new Maksukortti(0);
        kassapaate.lataaRahaaKortille(kortti, -100);
        assertTrue(kortti.saldo() == 0);
    }
}
