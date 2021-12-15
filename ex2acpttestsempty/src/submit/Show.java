package submit;

import main.data.OrderInfo;
import main.data.ShowInfo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Show {
    public int id;
    private Show.State[] seats;//0 -free, 1- reserved, 2- VIP
    private ShowInfo relatedShowInfo ;

    public Show() {
    }

    public Show(ShowInfo showInfo){
        this.relatedShowInfo = showInfo;

    }

    public void addOrder(OrderInfo order) {
        for ( OrderInfo cur : relatedShowInfo.userstoinform )
            if(cur.name.equals (order.name))
                return;
        relatedShowInfo.userstoinform.add ( order );
    }

    public ShowInfo getRelatedShowInfo() {
        return relatedShowInfo;
    }

    public enum State {
        reserved, VIP, free, temp
    }


    public List<OrderInfo> getWaitings() {
        return relatedShowInfo.userstoinform;
    }

    @Override
    public String toString() {
        return "ShowInfo [city=" + relatedShowInfo.city + ", hall=" + relatedShowInfo.hall + ", name=" + relatedShowInfo.name + ", description=" + relatedShowInfo.description
                + ", hastime=" + relatedShowInfo.hastime + ", showTime=" + relatedShowInfo.showTime + ", showDate=" + convertTime(relatedShowInfo.showDate)
                + ", lastOrderDate=" + convertTime(relatedShowInfo.lastOrderDate) + ", ticketCost=" + relatedShowInfo.ticketCost + ", userstoinform="
                + relatedShowInfo.userstoinform + "]";
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
        this.seats = new Show.State[numOfSeats];
        for ( int i = 0; i < numOfSeats; i++ )
            seats[i] = Show.State.free;
    }

    public Show.State[] getSeats() {
        return seats;
    }

    public void setSeats(Show.State[] seats) {
        this.seats = seats;
    }

    public void resevrveSeat (int seat){
        seats[seat] = Show.State.reserved;
    }

    public void resevrveTempSeat (int seat){
        seats[seat] = Show.State.temp;
    }


    public List<Integer> getReservedSeats () {
        List<Integer> reserved = new LinkedList<> (  );
        for(int i = 0; i < seats.length; i++){
            if(seats[i] == Show.State.reserved)
                reserved.add ( i );
        }
        return reserved;
    }

    public List<Integer> getReservedSeatsWithVIP() {
        List<Integer> reserved = new LinkedList<> (  );
        for(int i = 0; i < seats.length; i++){
            if(seats[i] == Show.State.reserved || seats[i] == Show.State.VIP)
                reserved.add ( i );
        }
        return reserved;
    }
}
