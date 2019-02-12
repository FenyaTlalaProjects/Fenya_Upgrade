package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.model.TicketHistory;
import za.co.fenya.demo.model.Tickets;
import net.sf.jasperreports.engine.JRDataSource;


public interface TicketHistoryInt {
	List<TicketHistory> getHistoryByTicketNumber(Long ticketNumber);

	JRDataSource getDeviceHistoryDataSource(Long recordID);
	Tickets getLoggedTicketsByTicketNumber(Long ticketNumber);
	List<TicketHistory> getAllTicketHistoryByTicketNumber(Long recordID);
}
