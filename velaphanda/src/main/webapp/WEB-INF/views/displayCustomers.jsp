<%@include file="templates/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>View Customers | Velaphanda Trading & Projects</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="templates/tableresizefont.jsp"></c:import>
<c:import url="templates/datatablesstyles.jsp"></c:import>
</head>
<body>
	<c:import url="templates/navbar.jsp"></c:import>

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href='<c:url value="/home"/>'><svg class="glyph stroked home">
						<use xlink:href="#stroked-home"></use></svg></a></li>
				<div class="nav navbar-nav navbar-right" style="margin-top: -1%;">
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
					<div class="panel-heading" align="center">Customers</div>
					<div class="panel-body">


						<c:if test="${empty customerName}">

							<a target="_blank" href="customerListDownloadPDF">Download
								PDF </a>
							<br />
							<br />


							<table id="customerHistory" class="display">
								<thead>
									<tr>
										<th></th>
										<th data-field="customername" data-sortable="true">Customer</th>
										<th data-field="email" data-sortable="true">Email</th>
										<th data-field="tellNo" data-sortable="true">Tell No</th>
										<th data-field="updateCustomer" data-sortable="true">Update</th>
										<th data-field="viewDevice" data-sortable="true">View
											Device</th>
										<th data-field="addDevice" data-sortable="true">Add
											Device</th>
									</tr>
								</thead>

								<tbody>
									<!-- Iterating over the list sent from Controller -->
									<c:forEach var="list" items="${displayCustomers}">
										<tr>
											<td class="details-control"
												onclick="window.location='displayCustomerHistory?customerName=<c:out value='${list.customerName}'/>';"></td>
											<td><a
												href="viewCustomer?customerName=<c:out value='${list.customerName}'/>">
													${list.customerName}</a></td>
											<td>${list.contactEmail}</td>
											<td>${list.telephoneNumber}</td>

											<td><a
												href="searchCustomer?customerName=<c:out value='${list.customerName}'/>">
													Update Customer</a></td>
											<td><a
												href="searchCustomerdevices?customerName=<c:out value='${list.customerName}'/>">
													View Devices</a></td>
											<td><a
												href="searchClientforProduct?customerName=<c:out value='${list.customerName}'/>">
													Add Device</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</c:if>

						<!-- Customer History Details -->
						<c:if test="${not empty customerName}">

							<table id="customerHistoryDetails" class="display">
								<thead>
									<tr>
										<th colspan="4" style="text-align: center; font-size: 18px;">History
											of Capturing Data for : ${customerName}</th>
									</tr>
									<tr>
										<th>Name</th>
										<th>Action</th>
										<th>Date</th>
										<th>Comment</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="list" items="${displayCustomerHistory}">
										<tr>
											<td>${list.userName}</td>
											<td>${list.action}</td>
											<td>${list.dateTime}</td>
											<td>${list.description}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>


						</c:if>

					</div>
					<!-- .panel-body -->
				</div>
				<!-- .panel panel-default -->
			</div>
			<!-- /.col-->
		</div>
		<!-- /.row -->
		<!-- footer -->
		<c:import url="templates/footer.jsp"></c:import>
		<!--/ footer -->
	</div>
	<!--/.main-->
	<c:import url="templates/javascriptslib.jsp"></c:import>
	<c:import url="templates/datatablesscripts.jsp"></c:import>
	<c:import url="templates/sidebar-collapse.jsp"></c:import>

	<!-- /Scripts -->
</body>
</html>