package za.co.fenya.demo.dao;

import za.co.fenya.demo.model.Tickets;


public interface ScheduledTickets {

	void calculateSLAHours();
	void resolveToClosedTicketUpdate();
	void updateSLA(Tickets tickets);
}
