<%@ page import="java.util.*"%>
<%@include file="templates/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Invoice Management</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="templates/tableresizefont.jsp"></c:import>
<c:import url="templates/stylesheetlib.jsp"></c:import>
<c:import url="templates/topviewtickets.jsp"></c:import>
</head>
<body onload="CovertDateToString()">

	<c:import url="templates/navbar.jsp"></c:import>

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href='<c:url value="/home"/>'><svg class="glyph stroked home">
						<use xlink:href="#stroked-home"></use></svg></a></li>
				<div class="nav navbar-nav navbar-right" style="margin-top: -1%">
					<a href="#" onclick="history.go(-1);"><span
						class="glyphicon glyphicon-circle-arrow-left btn-lg"
						title="Previous Page"></span></a> <a href="#" onclick="history.go(1);"><span
						class="glyphicon glyphicon-circle-arrow-right btn-lg"
						title="Go Forward"></span></a>
				</div>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading" align="center">Invoice</div>
					<div class="panel-body">
						
					
						<a href='<c:url value="/invoice"/>' class="btn btn-success"> Create Invoice </a>
										
						<br>
						
						<form:form action="searchTicket" method="post" id="searchTicket"
							class="searchTicket" modelAttribute="searchTicket">

							<div style="margin-bottom: -3px; margin-left: -1px;" align=left>

								<!-- Select type customers-->
								<div class="form-group ">
									<div class="col-md-3 selectContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-list"></i></span> <select
												name="customerName" id="customerName"
												class="form-control selectpicker">
												<c:if test="${not empty selectedName }">
													<option value="${selectedName}">${ selectedName}</option>
												</c:if>
												<option value="<c:out value="All Customers"/>">All
													Customers</option>
												<c:forEach items="${customers}" var="customer">
													<option value="<c:out value='${customer.customerName}'/>">${customer.customerName}</option>
												</c:forEach>
											</select>

										</div>
									</div>
								</div>
								
								
								<!-- Select type customers-->
								<div class="form-group ">
									<div class="col-md-3 selectContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-list"></i></span> <select
												name="deviceName" id="deviceName"
												class="form-control selectpicker">
												<c:if test="${not empty selectedName }">
													<option value="${selectedName}">${ selectedName}</option>
												</c:if>
												<option value="<c:out value="All Customers"/>">All
													Devices</option>
												<c:forEach items="${deviceName}" var="deviceName">
													<option value="<c:out value='${customer.customerName}'/>">${customer.customerName}</option>
												</c:forEach>
											</select>

										</div>
									</div>
								</div>

							
								<!-- Select type selectTehnnician-->
								<div class="form-group ">
									<div class="col-md-3 selectContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-list"></i></span> <select
												name="technicianName" id="technicianName"
												class="form-control selectpicker">
												<c:if test="${not empty selectedTechnician }">
													<option>${ selectedTechnician.firstName}${ selectedTechnician.lastName}</option>
												</c:if>
												<option value="All Technicians" />All Users
												</option>
												<c:forEach items="${technicians}" var="technician">
													<option value="<c:out value='${technician.email}'/>">${technician.firstName}
														${technician.lastName}</option>
												</c:forEach>

											</select>
										</div>
									</div>
								</div>
								<div align=right>
									<!-- Text input Search-->
									<div class="form-group">
										<div class="col-md-3 inputGroupContainer">
											<div class="input-group">
												<input name="serialNumber" list="serialNumbers"
													class="form-control" type="text"
													onkeydown="upperCaseF(this)"
													placeholder='Enter Serial Number' /> <span
													class="input-group-btn">
													<button class="btn btn-success" type="submit">
														<div class="up" style="margin-top: -8%; color: white;">Search</div>
													</button>
												</span>
											</div>
											<!-- /input-group -->
										</div>

										<!-- Iterating over the list sent from Controller -->
										<datalist id="serialNumbers"> <c:forEach var="list"
											items="${serialNumbers}">
											<option value="${list}">
										</c:forEach> </datalist>
									</div>
								</div>
							</div>
						</form:form>

						<div class="row">
							<ul class="nav nav-tabs">
								<div class="col-sm-12">
									<div class="row">
										<br />
										
										<div class="col-xs-6 col-md-3">
											<a href='<c:url value="/partialinvoice"/>'>
												<div class="well" style="background-color: #ffffff;">
													<h5 class="text-success">
														<span class="label label-success pull-right">${countCapturedReadings}</span>
														Partial Invoice 
													</h5>
												</div>
											</a>
										</div>
										
										<div class="col-xs-6 col-md-3">
											<a href='<c:url value="/draftinvoice"/>'>
												<div class="well" style="background-color: #ffffff;">
													<h5 class="text-success">
														<span class="label label-success pull-right">${countCapturedReadings}</span>
														Draft Invoice 
													</h5>
												</div>
											</a>
										</div>
										
										<div class="col-xs-6 col-md-3">
											<a href='<c:url value="/sentinvoice"/>'>
												<div class="well" style="background-color: #ffffff;">
													<h5 class="text-success">
														<span class="label label-success pull-right">${countCapturedReadings}</span>
														Sent Invoice 
													</h5>
												</div>
											</a>
										</div>
										
										<div class="col-xs-6 col-md-3">
											<a href='<c:url value="/approvedinvoice"/>'>
												<div class="well" style="background-color: #ffffff;">
													<h5 class="text-success">
														<span class="label label-success pull-right">${countCapturedReadings}</span>
														Approved Invoice 
													</h5>
												</div>
											</a>
										</div>
										
										<div class="col-xs-6 col-md-3">
											<a href='<c:url value="/capturedinvoice"/>'>
												<div class="well" style="background-color: #ffffff;">
													<h5 class="text-success">
														<span class="label label-success pull-right">${countCapturedReadings}</span>
														Captured Invoice 
													</h5>
												</div>
											</a>
										</div>
										
										<div class="col-xs-6 col-md-3">
											<a href='<c:url value="/deletedinvoice"/>'>
												<div class="well" style="background-color: #ffffff;">
													<h5 class="text-success">
														<span class="label label-success pull-right">${countCapturedReadings}</span>
														Deleted Invoice 
													</h5>
												</div>
											</a>
										</div>
										<div class="col-xs-6 col-md-3">
											<a href='<c:url value="/pendinginvoice"/>'>
												<div class="well" style="background-color: #ffffff;">
													<h5 class="text-primary">
														<span class="label label-primary pull-right">${countPendingReadings}</span>
														 Pending Invoice
													</h5>
												</div>
											</a>
										</div>
										
										<div class="col-xs-6 col-md-3">
											<a href='<c:url value="/paidinvoice"/>'>
												<div class="well" style="background-color: #ffffff;">
													<h5 class="text-primary">
														<span class="label label-primary pull-right">${countPendingReadings}</span>
														Paid Invoice
													</h5>
												</div>
											</a>
										</div>
										
									</div>
									<!--/row-->
									
									
								</div>
								<!--/col-12-->
							</ul>
							<!--/tab nav-->
						</div>
						<!--/row-->

						<!-- tab nav -->
						<div class="tab-content">

							<c:choose>
								
								<c:when test="${heading=='All Invoices' }">
									<div class="panel-heading" align="center">All Invoices</div>
								</c:when>
								<c:when test="${heading=='Approved' }">
									<div class="panel-heading" align="center">Approved
										Invoice</div>
								</c:when>	
								<c:when test="${heading=='Captured' }">
									<div class="panel-heading" align="center">Captured Invoice</div>
								</c:when>
								<c:when test="${heading=='Pending' }">
									<div class="panel-heading" align="center">Pending
										Invoice</div>
								</c:when>
								<c:when test="${heading=='Partial' }">
									<div class="panel-heading" align="center">Partial
										Invoice</div>
								</c:when>
								<c:when test="${heading=='Draft' }">
									<div class="panel-heading" align="center">Draft
										Invoice</div>
								</c:when>
								<c:when test="${heading=='Sent' }">
									<div class="panel-heading" align="center">Sent
										Invoice</div>
								</c:when>
								<c:when test="${heading=='Paid' }">
									<div class="panel-heading" align="center">Paid
										Invoice</div>
								</c:when>
								<c:when test="${heading=='Deleted' }">
									<div class="panel-heading" align="center">Deleted
										Invoice</div>
								</c:when>
												
								
							</c:choose>

							<table data-toggle="table" data-url="${deviceReadingList}"
								data-show-refresh="true" data-show-toggle="true"
								data-search="true" data-select-item-name="toolbar1"
								data-pagination="true" data-sort-name="ticketdate"
								data-sort-order="desc">
								<thead>
									<tr>
										<th data-field="invoiceNo" data-sortable="true">Invoice No</th>
										<th data-field="customername" data-sortable="true">Customer Name</th>
										<th data-field="serialnumber" data-sortable="true">Serial Number</th>										
										<th data-field="capturedBy" data-sortable="true">Captured By</th>										
										<th data-field="dateInserted" data-sortable="true">Date Issued</th>
										<th data-field="readingStatus" data-sortable="true">Due Date</th>
										<th data-field="invoiceStatus" data-sortable="true">Invoice Status</th>
										<th data-field="type" data-sortable="true">Amount Paid</th>
										<th data-field="type" data-sortable="true">Amount Due</th>										
										<th data-field="monoReading" data-sortable="true">Action</th>
										
									</tr>
								</thead>

								<tbody>
									<!-- Iterating over the list sent from Controller -->
									<c:forEach var="list" items="${deviceReadingList}">
										<tr>
											<td>INV011</td>
											<td>${list.customerName.customerName}</td>
											<td>${list.serialNumber.serialNumber}</td>
											<td>${list.employee.firstName} ${list.employee.lastName}</td>
											<td>${list.insertDate}</td>
											<td>${list.insertDate}</td>
											<td>${list.readingStatus}</td>
											<td>R400</td>
											<td>R000</td>
											<td><a href="" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a> <a href="#" data-invoice-id="1" data-email="" data-invoice-type="invoice" data-custom-email="" class="btn btn-success btn-xs email-invoice"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a> <a href="" class="btn btn-info btn-xs" target="_blank"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a> <a data-invoice-id="1" class="btn btn-danger btn-xs delete-invoice"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>										
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- table ticket --> 
							
							
						</div>
						<!-- /tab-content -->

					</div>
					<!-- .panel-body -->
				</div>
				<!-- .panel panel-default -->
			</div>
			<!-- /.col-->
		</div>
		<!-- /.row -->
		<!-- Footer -->
		<c:import url="templates/footer.jsp"></c:import>
		<!--/ Footer -->
	</div>
	<!--/.main-->
	<!-- scripts -->
	<c:import url="templates/ticketandorderselectdate.jsp"></c:import>
	<script type="text/javascript">
		//covert a date and store a variable from server
		function CovertDateToString() {

			var date = "${newDate}";
			var myDate = new Date();
			var selectDate = myDate.toDateString();
			document.getElementsByName('selectDateRange')[0].value = selectDate;
			selectDate = date;
			document.getElementById('selectDateRange').value = date;
			console.log("Set Date to Select Date: ", date);
			console.log("Set Date to Selected Date: ", date);
		}
		// selectDateRange
		$(function() {

			$('input[name="selectDateRange"]').daterangepicker({
				//startDate: moment().subtract(6, 'days')		       
				locale : {
					format : 'YYYY-MM-DD'
				}
			});
		});
		

		/*---Create datalist to populate search---*/

		//Get the <datalist> and <input> elements.
		var dataList = document.getElementById('json-datalist');
		var input = document.getElementById('ajax');

		//Create a new XMLHttpRequest.
		var request = new XMLHttpRequest();

		//Handle state changes for the request.
		request.onreadystatechange = function(response) {
			if (request.readyState === 4) {
				if (request.status === 200) {
					// Parse the JSON
					var jsonOptions = JSON.parse(request.responseText);

					// Loop over the JSON array.
					jsonOptions.forEach(function(item) {
						// Create a new <option> element.
						var option = document.createElement('option');
						// Set the value using the item in the JSON array.
						option.value = item;
						// Add the <option> element to the <datalist>.
						var appendChild = "Lets See";
						dataList.appendChild(option);
					});

					// Update the placeholder text.
					input.placeholder = "e.g. datalist";
				} else {
					// An error occured :(
					input.placeholder = "Couldn't load datalist options :(";
				}
			}
		};

		//Update the placeholder text.
		var input = "Loading options";
		input.placeholder = "Loading options...";
		console.log("What do we have here : ", input.placeholder);
		console.log("Mine : ", input);

		//Set up and make the request.
		request.open('GET',
				'https://s3-us-west-2.amazonaws.com/s.cdpn.io/4621/html-elements.json',
				true);
		request.send();
		
	</script>
	<c:import url="templates/sidebar-collapse.jsp"></c:import>
	<!-- /Scripts -->
</body>
</html>

