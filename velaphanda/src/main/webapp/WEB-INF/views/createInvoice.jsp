<%@include file="templates/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="templates/tableresizefont.jsp"></c:import>
<c:import url="templates/stylesheetlib.jsp"></c:import>
<title>Create Invoice </title>
<style type="text/css">
.declineButton {
	margin-left: 25%;
	margin-right: 10%;
}
</style>
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
					<div class="panel-heading" align="center">
						<b>Create Invoice</b>
					</div>
					<div class="panel-body">

						<form:form class="well form-horizontal" method="post"
							action="createInvoice" id="createInvoice"
							modelAttribute="createInvoice">

							
								<!-- Select type customers-->
								<div class="form-group ">
								<label class="col-md-3 control-label">Select Customer</label>
									<div class="col-md-6 selectContainer">
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
								
								<!-- Text input Search-->
									<div class="form-group">
										<label class="col-md-3 control-label">Select Serial No </label>
										<div class="col-md-6 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-hdd"></i></span> <input
													name="serialNumber" list="serialNumbers"
													class="form-control" type="text"
													onkeydown="upperCaseF(this)"
													placeholder='Enter Serial Number' />
											</div>
										</div>
										<!-- Iterating over the list sent from Controller -->
										<datalist id="serialNumbers"> <c:forEach var="list"
											items="${serialNumbers}">
											<option value="${list}">
										</c:forEach> </datalist>

									</div>
								
								
								<!-- Select type selectDateRange-->
								<div class="form-group">
								<label class="col-md-3 control-label">Select Date</label>
									<div class="col-md-6 selectContainer">
										<div class="input-group">
											<input type="text" class="form-control"
												name="selectDateRange" id="selectDateRange"
												value="${newDate}"> <span class="input-group-addon"><i
												class="glyphicon glyphicon-calendar"></i></span>
										</div>
									</div>
								</div>
								
								
								<%-- <c:if test="${not empty productObject.monoReading }"> --%>
											<!-- Text input Mono-->
											<div class="form-group">
												<label class="col-md-3 control-label">Mono Reading</label>
												<div class="col-md-6 inputGroupContainer">
													<div class="input-group">
														<span class="input-group-addon"><i
															class="glyphicon glyphicon-barcode"></i></span> <input
															id="monoReading" name="monoReading"
															placeholder="Mono Reading" class="form-control"
															type="text">
													</div>
												</div>
											</div>
										<%-- 	</c:if>
											<c:if test="${empty productObject.monoReading }">
											</c:if>
											<c:if test="${not empty productObject.colourReading}">
											 --%><!-- Text input Color-->
											<div class="form-group">
												<label class="col-md-3 control-label">Colour Reading</label>
												<div class="col-md-6 inputGroupContainer">
													<div class="input-group">
														<span class="input-group-addon"><i
															class="glyphicon glyphicon-barcode"></i></span> <input
															id="ColourReading" name="ColourReading"
															placeholder="Color Reading" class="form-control"
															type="text">
													</div>
												</div>
											</div>
											<%-- </c:if>											
											<c:if test="${empty productObject.colourReading }">
											</c:if>
								
								<c:if test="${not empty productObject.monoReading }"> --%>
											<!-- Text input Mono-->
											<div class="form-group">
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
											</div>
											<%-- </c:if>
											<c:if test="${empty productObject.monoReading }">
											</c:if>
											<c:if test="${not empty productObject.colourReading}"> --%>
											<!-- Text input Color-->
											<div class="form-group">
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
											</div>
											<%-- </c:if>											
											<c:if test="${empty productObject.colourReading }">
											</c:if>
								 --%>
															
							
							<br>
							<div class="declineButton">
								<div class="form-group row">
									<div class="col-sm-offset-2 col-md-5">
										<input type="submit" value="Decline Leave"
											class="btn btn-primary btn-block btn-lg" tabindex="9"
											id="declineLeave" name="declineLeave">
									</div>
								</div>
							</div>



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
		request.open('GET',
				'https://s3-us-west-2.amazonaws.com/s.cdpn.io/4621/html-elements.json',
				true);
		request.send();
		
	</script>
	<c:import url="templates/sidebar-collapse.jsp"></c:import>
	<!-- /Scripts -->
</body>
</html>
