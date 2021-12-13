package main.data;

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
	private State[] seats;//0 -free, 1- reserved, 2- VIP

	public ShowInfo() {
		showTime = null;
	}

	public enum State {
		reserved, VIP, free, temp
	}


	public List<OrderInfo> getWaitings() {
		return userstoinform;
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
		this.seats = new State[numOfSeats];
		for ( int i = 0; i < numOfSeats; i++ )
			seats[i] = State.free;
	}

	public State[] getSeats() {
		return seats;
	}

	public void setSeats(State[] seats) {
		this.seats = seats;
	}

	public void resevrveSeat (int seat){
		seats[seat] = State.reserved;
	}

	public void resevrveTempSeat (int seat){
		seats[seat] = State.temp;
	}


	public List<Integer> getReservedSeats () {
		List<Integer> reserved = new LinkedList<> (  );
		for(int i = 0; i < seats.length; i++){
			if(seats[i] == State.reserved)
				reserved.add ( i );
		}
		return reserved;
	}

	public List<Integer> getReservedSeatsWithVIP() {
		List<Integer> reserved = new LinkedList<> (  );
		for(int i = 0; i < seats.length; i++){
			if(seats[i] == State.reserved || seats[i] == State.VIP)
				reserved.add ( i );
		}
		return reserved;
	}
}