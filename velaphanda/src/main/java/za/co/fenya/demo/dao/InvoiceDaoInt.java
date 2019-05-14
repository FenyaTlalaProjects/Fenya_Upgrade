package za.co.fenya.demo.dao;

import java.util.List;

import za.co.fenya.demo.bean.InvoiceBean;
import za.co.fenya.demo.model.InvoiceHeader;

public interface InvoiceDaoInt {

	String createInvoiceHeader(InvoiceBean invoice);

	List<InvoiceHeader> getAllInvoices();

	List<InvoiceHeader> getInvoiceByInvoiceNumber(String invoiceNumber);

}
