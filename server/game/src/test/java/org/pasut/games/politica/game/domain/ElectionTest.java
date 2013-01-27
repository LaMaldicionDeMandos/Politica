package org.pasut.games.politica.game.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ElectionTest {
	private Election election;
	User owner;
	User user1;
	User user2;
	User user3;
	
	@Before
	public void setUp() throws Exception {
		owner = new User("Owner");
		user1 = new User("User1");
		user2 = new User("User2");
		user3 = new User("User3");
		election = new Election(owner, 4);
		election.addUser(user1);
		election.addUser(user2);
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

}
