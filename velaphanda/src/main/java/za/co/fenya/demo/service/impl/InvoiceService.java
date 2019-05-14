package za.co.fenya.demo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.fenya.demo.bean.InvoiceBean;
import za.co.fenya.demo.bean.ReadingBean;
import za.co.fenya.demo.dao.InvoiceDaoInt;
import za.co.fenya.demo.dao.ReadingDaoInt;
import za.co.fenya.demo.model.Reading;
import za.co.fenya.demo.service.InvoiceServiceInt;
import za.co.fenya.demo.service.ReadingServiceInt;



//Device
@Service("invoiceService")
public class InvoiceService implements InvoiceServiceInt {
	
	@Autowired
	private InvoiceDaoInt invoiceDAO;
	
	private String retMessage = null;
	
	@Override
	public String createInvoiceHeader(InvoiceBean invoice) {
		// TODO Auto-generated method stub
		retMessage = invoiceDAO.createInvoiceHeader(invoice);
		return retMessage;
	}


}
