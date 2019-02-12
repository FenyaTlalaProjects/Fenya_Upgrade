package za.co.fenya.demo.service.impl;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.dao.TicketHistoryDaoInt;
import za.co.fenya.demo.model.TicketHistory;
import za.co.fenya.demo.model.Tickets;
import za.co.fenya.demo.service.TicketHistoryInt;



@Service("ticketHistoryService")
public class TicketHistoryService implements TicketHistoryInt{
	
	@Autowired
	private TicketHistoryDaoInt historyDaoInt;

	@Override
	public List<TicketHistory> getHistoryByTicketNumber(Long ticketNumber) {
		
		return historyDaoInt.getHistoryByTicketNumber(ticketNumber);
	}

	@Override
	public JRDataSource getDeviceHistoryDataSource(Long recordID) {
		return historyDaoInt.getDeviceHistoryDataSource(recordID);
	}

	@Override
	public Tickets getLoggedTicketsByTicketNumber(Long ticketNumber) {
		return historyDaoInt.getLoggedTicketsByTicketNumber(ticketNumber);
	}

	@Override
	public List<TicketHistory> getAllTicketHistoryByTicketNumber(Long recordID) {
		return historyDaoInt.getAllTicketHistoryByTicketNumber(recordID);
	}

}
