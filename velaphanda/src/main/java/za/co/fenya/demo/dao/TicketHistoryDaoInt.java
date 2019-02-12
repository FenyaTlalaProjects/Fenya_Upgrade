package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.model.TicketHistory;
import za.co.fenya.demo.model.Tickets;
import net.sf.jasperreports.engine.JRDataSource;


public interface TicketHistoryDaoInt {
	void insertTicketHistory(Tickets ticket);
	List<TicketHistory> getHistoryByTicketNumber(Long ticketNumber);	
	List<TicketHistory> getAllTicketHistoryByTicketNumber();
	JRDataSource getDeviceHistoryDataSource(Long recordID);
	Tickets getLoggedTicketsByTicketNumber(Long recordID);
	List<TicketHistory> getAllTicketHistoryByTicketNumber(Long recordID);
}
