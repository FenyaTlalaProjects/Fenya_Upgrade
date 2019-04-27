<%@include file="templates/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="templates/tableresizefont.jsp"></c:import>
<c:import url="templates/stylesheetlib.jsp"></c:import>


<title>Enter Readings</title>
<style type="text/css">
/* Tablet and bigger */
@media ( min-width : 768px ) {
	.grid-divider {
		position: relative;
		padding: 0;
	}
	.grid-divider>[class*='col-'] {
		position: static;
	}
	.grid-divider>[class*='col-']:nth-child(n+2):before {
		content: "";
		border-left: 1px solid #DDD;
		position: absolute;
		top: 0;
		bottom: 0;
	}
	.col-padding {
		padding: 0 15px;
	}
}
</style>
</head>
<body>

	<c:import url="templates/navbar.jsp"></c:import>

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href='<c:url value="/home"/>'><svg
							class="glyph stroked home">
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
					<div class="panel-heading" align="center">
						<b>Enter Reading</b>
					</div>
					<div class="panel-body">


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
												class="form-control selectpicker"
												onchange="location = this.value;">
												<c:if test="${not empty selectedName }">
													<option value="${selectedName}">${ selectedName}</option>
												</c:if>
												<option value="<c:out value="All Customers"/>">All
													Customers</option>
												<c:forEach items="${customers}" var="customer">
													<option
														value="readingsCustomerByDevice?customerName=<c:out value='${customer.customerName}'/>">${customer.customerName}</option>
												</c:forEach>
											</select>

										</div>
									</div>
								</div>


								<!-- Text input Search-->
								<div class="form-group">
									<div class="col-md-3 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-hdd"></i></span> <input
												name="serialNumber" list="deviceList" class="form-control"
												type="text" onkeydown="upperCaseF(this)"
												placeholder='Enter Serial Number' />
										</div>
									</div>
									<!-- Iterating over the list sent from Controller -->
									<datalist id="deviceList">
										<c:forEach var="list" items="${deviceList}">
											<option value="${list.serialNumber}">
										</c:forEach>
									</datalist>

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
										<datalist id="serialNumbers">
											<c:forEach var="list" items="${serialNumbers}">
												<option value="${list}">
											</c:forEach>
										</datalist>
									</div>
								</div>
							</div>
						</form:form>

						<form:form class="well form-horizontal" method="post"
							action="createInvoice" id="createInvoice"
							modelAttribute="createInvoice">

							<div class="row"></div>
							<%-- <fieldset>
								<!-- Customer Device -->
								<legend>
									<b style="font-size: 15px;">Customer Device </b>
								</legend>
								
								<!-- Select type customers-->
								<div class="form-group ">
								<label class="col-md-3 control-label">Select Customer</label>
									<div class="col-md-6 selectContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-list"></i></span>  
											<select name="customerName" id="customerName" class="form-control selectpicker" onchange="location = this.value;">
												<c:if test="${not empty selectedName }">
													<option value="${selectedName}">${ selectedName}</option>
												</c:if>
												<option value="<c:out value="All Customers"/>">All Customers</option>
												<c:forEach items="${customers}" var="customer">
													<option value="createInvoiceCustomerByDevice?customerName=<c:out value='${customer.customerName}'/>">${customer.customerName}</option>
												</c:forEach>
											</select>

										</div>
									</div>
								</div>
								
								
								<!-- Text input Search-->
									<div class="form-group">
										<label class="col-md-3 control-label">Select Serial No </label>
										<div class="col-md-6 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-hdd"></i></span> <input
													name="serialNumber" list="deviceList"
													class="form-control" type="text"
													onkeydown="upperCaseF(this)"
													placeholder='Enter Serial Number' />
											</div>
										</div>
										<!-- Iterating over the list sent from Controller -->
										<datalist id="deviceList"> 
											<c:forEach var="list"
												items="${deviceList}">
												<option value="${list.serialNumber}">
											</c:forEach>
										 </datalist>

									</div>							
								
								</fieldset>
								
							
								
							<fieldset>
								<!-- Invoice Date -->
								<legend>
									<b style="font-size: 15px;">Invoice Date</b>
								</legend>
							
								<!-- Text input Contract Start Date-->
									<div class="form-group ">
										<label class="col-xs-3 control-label">Start
											Date</label>
										<div class="col-md-6 inputGroupContainer">
											<div class="input-group input-append date"
												id="startDatePicker">
												<input type="text" class="form-control" name="startDate"
													id="startDate" placeholder="YYYY-MM-DD"> <span
													class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
									</div>

									<!-- Text input Contract End Date-->
									<div class="form-group">
										<label class="col-md-3 control-label">End
											Date</label>
										<div class="col-md-6 inputGroupContainer">
											<div class="input-group input-append date" id="endDatePicker">
												<input type="text" class="form-control" name="endDate"
													id="endDate" placeholder="YYYY-MM-DD"> <span
													class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
									</div>
								
								</fieldset>	 --%>
							<br>
							<fieldset>
								<!-- Device Readings -->
								<br>
								<legend style="text-align:center;">
									<b style="font-size: 15px;">Device Readings</b>
								</legend>

								


								<div class="row grid-divider">
									<div class="col-sm-6">
										<div class="col-padding">
											<legend>
											<b style="font-size: 15px;"> Mono Reading</b>
										</legend>

											<%-- <c:if test="${not empty productObject.monoReading }"> --%>
										<!-- Text input Mono-->
										<div class="form-group">

											<div class="col-md-6 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-barcode"></i></span> <input
														id="previousmonoReading" name="previousmonoReading"
														placeholder="Enter Previous Mono Reading"
														class="form-control" type="text">
												</div>
											</div>
										</div>
										<%--</c:if>
											
											<c:if test="${empty productObject.monoReading }"></c:if>
											<c:if test="${not empty productObject.colourReading}"> --%>

										<!-- Text input Color-->
										<div class="form-group">

											<div class="col-md-6 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-barcode"></i></span> <input
														id="ColourReading" name="ColourReading"
														placeholder="Enter Current Mono Reading"
														class="form-control" type="text">
												</div>
											</div>
										</div>
										<%-- </c:if>
																					
											<c:if test="${empty productObject.colourReading }"></c:if>
											<%-- <c:if test="${not empty productObject.monoReading }"> --%>
											
										</div>
									</div>
									<div class="col-sm-6">
										<div class="col-padding">
											<legend>
											<b style="font-size: 15px;"> Colour Reading</b>
											</legend>
											<!-- Text input Mono-->
										<div class="form-group">

											<div class="col-md-6 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-barcode"></i></span> <input
														id="currentMonoReading" name="currentMonoReading"
														placeholder="Enter Previous Colour Reading"
														class="form-control" type="text">
												</div>
											</div>
										</div>
										<%--</c:if>
								          
											<c:if test="${empty productObject.monoReading }"></c:if>
											<c:if test="${not empty productObject.colourReading}">--%>

										<!-- Text input Color-->
										<div class="form-group">

											<div class="col-md-6 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-barcode"></i></span> <input
														id="currentColourReading" name="currentColourReading"
														placeholder="Enter Current Color Reading"
														class="form-control" type="text">
												</div>
											</div>
										</div>
										<%-- </c:if>											
											<c:if test="${empty productObject.colourReading }"></c:if>
											
											
								
											<c:if test="${not empty productObject.monoReading }"> --%>
										<!-- Text input Mono-->
										<!-- <div class="form-group">
												<label class="col-md-3 control-label">Mono Copy Cost</label>
												<div class="col-md-6 inputGroupContainer">
													<div class="input-group">
														<span class="input-group-addon"><i
															class="glyphicon glyphicon-barcode"></i></span> <input
															id="monoCopyCost" name="monoCopyCost"
															placeholder="Mono Copy Cost" class="form-control"
															type="text">
													</div>
												</div>
											</div> -->
										<%-- </c:if>
											<c:if test="${empty productObject.monoReading }">
											</c:if>
											<c:if test="${not empty productObject.colourReading}"> --%>
										<!-- Text input Color-->
										<!-- <div class="form-group">
												<label class="col-md-3 control-label">Colour Copy Cost</label>
												<div class="col-md-6 inputGroupContainer">
													<div class="input-group">
														<span class="input-group-addon"><i
															class="glyphicon glyphicon-barcode"></i></span> <input
															id="colourCopyCost" name="colourCopyCost"
															placeholder="Colour Copy Cost" class="form-control"
															type="text">
													</div>
												</div>
											</div> -->
										<%-- </c:if>											
											<c:if test="${empty productObject.colourReading }">
											</c:if>--%>

										</div>
									</div>
								</div>
								<br/>
								 <div class="row">
								  <div class="col-sm-4">
								  </div>
								   <div class="col-sm-4">
								    <div class="col text-center">
								     <input type="submit" value="Capture Readings"
											class="btn btn-primary btn-block btn-md" tabindex="9"
											id="captureReadings">
								    </div>
								  </div>
								  <div class="col-sm-4">
								  </div>
								  
								 </div>

							</fieldset>



						</form:form>

						<!-- .panel-body -->
					</div>
					<!-- .panel panel-default -->
				</div>
			</div>
		</div>
		<!-- /.col-->
		<!-- /.row -->
		<!-- Footer -->
		<c:import url="templates/footer.jsp"></c:import>
		<!--/ Footer -->
	</div>
	<!--/.main-->
	<c:import url="templates/javascriptslib.jsp"></c:import>
	<c:import url="templates/ticketmanagementscript.jsp"></c:import>
	<c:import url="templates/sidebar-collapse.jsp"></c:import>
	<!-- /Scripts -->
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
		request
				.open(
						'GET',
						'https://s3-us-west-2.amazonaws.com/s.cdpn.io/4621/html-elements.json',
						true);
		request.send();
	</script>
	<c:import url="templates/sidebar-collapse.jsp"></c:import>
	<!-- /Scripts -->
</body>
</html>
