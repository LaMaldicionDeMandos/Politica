package org.pasut.games.politica.game.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.pasut.games.politica.game.service.DefaultElectionsService;
import org.pasut.games.politica.game.service.ElectionsService;
import org.pasut.persister.PersisterService;
import org.pasut.persister.operators.Operator;

public class ElectionServiceTest {
	private PersisterService db;
	private List<Election> elections = new ArrayList<Election>();
	private ElectionsService service;
	
	@Before
	public void before(){
		Calendar calendar = Calendar.getInstance();
		Calendar tomorroy = (Calendar)calendar.clone();
		tomorroy.add(Calendar.DAY_OF_YEAR, 1);
		Calendar expire = (Calendar)calendar.clone();
		expire.add(Calendar.DAY_OF_YEAR, -1);
		expire.add(Calendar.WEEK_OF_YEAR, -1);
		db = mock(PersisterService.class);
		Election election = new Election(new User("test"), "ready1", 2, 1);
		elections.add(election);
		election.setInitDate(tomorroy.getTimeInMillis());
		election = new Election(new User("test"), "readyToActive1", 2, 1);
		elections.add(election);
		election = new Election(new User("test"), "readyToForDate1", 2, 1);
		elections.add(election);
		election = new Election(new User("test"), "Active1", 2, 1);
		election.activate();
		election = new Election(new User("test"), "ActiveToEnd1", 2, 1);
		election.activate();
		election.setInitDate(expire.getTimeInMillis());
		elections.add(election);
		election = new Election(new User("test"), "End1", 2, 1);
		election.activate();
		election.setInitDate(expire.getTimeInMillis());
		election.end();
		elections.add(election);

		when(db.find(any(Class.class), any(Operator.class))).thenReturn(elections);
		
		service = new DefaultElectionsService(db);
	}
	
	@Test
	public void testReady() {
		List<Election> list = service.searchActiveWithUser(new Date().getTime(), new User());
		assertEquals(3, list.size());
	}

}
