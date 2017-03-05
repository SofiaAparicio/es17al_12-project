package pt.ulisboa.tecnico.softeng.hotel.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;
import pt.ulisboa.tecnico.softeng.hotel.domain.Room.Type;

public class RoomConstructorMethodTest {
	private Hotel hotel;

	@Before
	public void setUp() {
		this.hotel = new Hotel("XPTO123", "Lisboa");
	}

	@Test
	public void success() {
		Room room = new Room(this.hotel, "01", Type.DOUBLE);

		Assert.assertEquals(this.hotel, room.getHotel());
		Assert.assertEquals("01", room.getNumber());
		Assert.assertEquals(Type.DOUBLE, room.getType());
		Assert.assertEquals(1, this.hotel.getNumberOfRooms());
	}
	
	@Test (expected = HotelException.class)
	public void roomExists(){
		new Room(this.hotel, "01", Type.DOUBLE);
		new Room(this.hotel,"01",Type.DOUBLE);
	}
	
	@Test (expected = HotelException.class)
	public void hasLetters(){
		new Room(this.hotel, "0A3", Type.DOUBLE);
	}
	
	
	@After
	public void tearDown() {
		Hotel.hotels.clear();
	}

}
