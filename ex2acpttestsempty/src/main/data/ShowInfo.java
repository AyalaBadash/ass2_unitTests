package main.data;

import javax.sql.rowset.serial.SerialArray;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ShowInfo {
	public int id;
	public String city;
	public String hall;
	public String name;
	public String description;
	public boolean hastime;
	public LocalTime showTime;
	public long showDate;
	public long lastOrderDate;
	public double ticketCost;
	public List<OrderInfo> userstoinform = new LinkedList<>();
	private int[] seats;//0 -free, 1- reserved, 2- VIP

	public ShowInfo() {
		showTime = null;
	}

	@Override
	public String toString() {
		return "ShowInfo [city=" + city + ", hall=" + hall + ", name=" + name + ", description=" + description
				+ ", hastime=" + hastime + ", showTime=" + showTime + ", showDate=" + convertTime(showDate)
				+ ", lastOrderDate=" + convertTime(lastOrderDate) + ", ticketCost=" + ticketCost + ", userstoinform="
				+ userstoinform + "]";
	}

	public String convertTime(long time) {
		Date date = new Date(time);
		Format format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(date);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void initialSeats(int numOfSeats) {
		this.seats = new int[numOfSeats];
		for ( int i = 0; i < numOfSeats; i++ )
			seats[i] = 0;
	}

	public int[] getSeats() {
		return seats;
	}

	public void setSeats(int[] seats) {
		this.seats = seats;
	}

	public void resevrveSeat (int seat){
		seats[seat] = 1;
	}

	public List<Integer> getReservedSeats () {
		List<Integer> reserved = new LinkedList<> (  );
		for(int i = 0; i < seats.length; i++){
			if(seats[i] == 1)
				reserved.add ( i );
		}
		return reserved;
	}

	public List<Integer> getReservedSeatsWithVIP() {
		List<Integer> reserved = new LinkedList<> (  );
		for(int i = 0; i < seats.length; i++){
			if(seats[i] == 1 || seats[i] == 2)
				reserved.add ( i );
		}
		return reserved;
	}
}