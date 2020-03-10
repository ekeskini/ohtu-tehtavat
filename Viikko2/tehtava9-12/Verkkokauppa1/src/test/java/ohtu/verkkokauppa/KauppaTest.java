package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
	private PankkiInterface pankki;
	private ViitegeneraattoriInterface viite;
	private VarastoInterface varasto;
	private Kauppa k;
	
	@Before
	public void setUp() {
		pankki = mock(Pankki.class);
		viite = mock(Viitegeneraattori.class);
		varasto = mock(Varasto.class);
		k = new Kauppa(varasto, pankki, viite);
	}
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {   
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
 
    }
    @Test
    public void tilisiirrollaOikeatParametrit() {    
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);   
 
    }
    @Test
    public void useampiSamaaTuotetta() {    
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);   
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);   
    }
    
    @Test
    public void kahdenTuotteenOstos() {	
    	when(viite.uusi()).thenReturn(42);
    	
    	when(varasto.saldo(1)).thenReturn(10);
    	when(varasto.saldo(2)).thenReturn(10);
    	
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 4));
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(1);
    	k.lisaaKoriin(2);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 9);
    }
    
    @Test
    public void yhtaTuotettaTarpeeksiToistaEi() {	
    	when(viite.uusi()).thenReturn(42);
    	
    	when(varasto.saldo(1)).thenReturn(10);
    	when(varasto.saldo(2)).thenReturn(0);
    	
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 4));
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(1);
    	k.lisaaKoriin(2);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }
    
    @Test
    public void aloitaAsiointiNollaEdelliset() {
    	when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);   
        k.lisaaKoriin(1);
        
        //aloitetaan uudet ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }
    
    @Test
    public void uusiViiteUudelleOstokselle() {
    	when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);   
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        //aloitetaan uudet ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
        
        verify(viite, times(2)).uusi();
    }
    @Test
    public void koristaPoistoTest() {
    	when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);   
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }
}
