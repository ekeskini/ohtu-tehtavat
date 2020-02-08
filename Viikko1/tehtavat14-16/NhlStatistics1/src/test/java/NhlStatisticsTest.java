package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NhlStatisticsTest {

	Reader readerStub = new Reader() {
		
		public List<Player> getPlayers() {
			ArrayList<Player> players = new ArrayList<>();
			
			players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            
            return players;
		}
		
	};
	
	Statistics stats;
	
	@Before
	public void setUp() {
		stats = new Statistics(readerStub);
	}
	
	@Test
	public void searchTest() {
		Player player = stats.search("Semenko");
		
		assertEquals(readerStub.getPlayers().get(0).getName(), player.getName());
	}
	
	@Test
	public void searchTestReturnsNull() {
		assertEquals(null, stats.search("LeBron"));
	}
	
	@Test
	public void teamListSizeTest() {
		List<Player> playerList = stats.team("EDM");
		
		assertEquals(playerList.size(), 3);
	}
	@Test
	public void teamListContentTest() {
		List<Player> playerList = stats.team("EDM");
		List<String> listAsStrings = new ArrayList<>();
		for (int i = 0; i < playerList.size(); i++) {
			listAsStrings.add(playerList.get(i).getName());
		}
		List<String> l = new ArrayList<>(Arrays.asList("Semenko", "Kurri", "Gretzky"));
		assertEquals(l, listAsStrings);
	}
	
	@Test
	public void topScorersTest() {
		List<Player> scorerList = stats.topScorers(1);
		
		assertEquals(scorerList.get(0).getName(), "Gretzky");
	}
	
}
