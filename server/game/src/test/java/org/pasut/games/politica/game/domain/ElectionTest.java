package org.pasut.games.politica.game.domain;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class ElectionTest {
	private Election election;
	User owner;
	User user1;
	User user2;
	User user3;
	
	private Calendar initDate;
	
	@Before
	public void setUp() throws Exception {
		owner = new User("Owner");
		user1 = new User("User1");
		user2 = new User("User2");
		user3 = new User("User3");
		election = new Election(owner, "test", 4, 4);
		election.addUser(user1);
		election.addUser(user2);
		initDate = (Calendar)Calendar.getInstance().clone();
		initDate.set(Calendar.YEAR, 2013);
		initDate.set(Calendar.MONTH, Calendar.JANUARY);
		initDate.set(Calendar.DAY_OF_MONTH, 1);
		initDate.set(Calendar.HOUR_OF_DAY, 12);
	}

	@Test
	public void testRemoveUser() {
		election.removeUser(user1);
		assertEquals(2, election.getLenght());
		assertEquals(owner, election.getUsers()[0]);
		assertEquals(user2, election.getUsers()[1]);
		assertNull(election.getUsers()[2]);
	}

	@Test
	public void testAddUser() {
		election.addUser(user3);
		assertEquals(4, election.getLenght());
		assertEquals(user3, election.getUsers()[3]);
	}
	
	@Test
	public void testIsFull(){
		election.addUser(user3);
		assertTrue(election.isFull());
	}

	@Test
	public void testNotFull(){
		assertFalse(election.isFull());
	}
	
	@Test
	public void testActivateElectionWithoutInitDate(){
		election.activate();
		assertEquals(ElectionState.ACTIVE, election.getState());
	}

	@Test
	public void testNotActivateElectionWithInitDate(){
		election.setInitDate(initDate.getTimeInMillis());
		Calendar date = (Calendar)initDate.clone();
		date.add(Calendar.DAY_OF_MONTH, -1);
		election.activate(date.getTimeInMillis());
		assertFalse(ElectionState.ACTIVE == election.getState());
	}

	@Test
	public void testActivateElectionWithInitDate(){
		election.setInitDate(initDate.getTimeInMillis());
		Calendar date = (Calendar)initDate.clone();
		date.add(Calendar.DAY_OF_MONTH, 1);
		election.activate(date.getTimeInMillis());
		assertEquals(ElectionState.ACTIVE, election.getState());
	}

	@Test
	public void testNotEndElection(){
		election.setInitDate(initDate.getTimeInMillis());
		Calendar date = (Calendar)initDate.clone();
		date.add(Calendar.DAY_OF_MONTH, 1);
		election.activate(date.getTimeInMillis());
		election.end(date.getTimeInMillis());
		assertFalse(ElectionState.ENDED == election.getState());
	}

	@Test
	public void testEndElection(){
		election.setInitDate(initDate.getTimeInMillis());
		Calendar date = (Calendar)initDate.clone();
		date.add(Calendar.DAY_OF_MONTH, 1);
		election.activate(date.getTimeInMillis());
		date.add(Calendar.WEEK_OF_YEAR, 5);
		election.end(date.getTimeInMillis());
		assertEquals(ElectionState.ENDED, election.getState());
	}

}
