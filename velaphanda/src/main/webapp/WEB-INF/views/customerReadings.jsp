<%@include file="templates/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Device Readings | Velaphanda Trading & Projects</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>

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

<c:import url="templates/stylesheetlib.jsp"></c:import>
</head>
<body onload="SelectedItemType()">
	<c:import url="templates/navbar.jsp"></c:import>

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href='<c:url value="/home"/>'><svg
							class="glyph stroked home"> <use xlink:href="#stroked-home"></use></svg></a></li>
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
					<div class="panel-heading" align="center">Readings</div>
					<div class="panel-body">

						<c:if test="${not empty retMessage }">

							<div class="alert alert-info" role="alert">
								<c:out value="${ retMessage}">
								</c:out>
							</div>
						</c:if>
						<c:if test="${not empty errorRetMessage }">

							<div class="alert alert-danger" role="alert">
								<c:out value="${ errorRetMessage}">
								</c:out>
								click <a href="addSpares.html">here </a>to add spare
							</div>
						</c:if>


						<div class="tab-content">

							<c:if test="${empty custName }">


								<form:form action="searchCustomerReading" method="post"
									id="searchCustomerReading" class="searchCustomerReading"
									modelAttribute="searchCustomerReading">

									<div style="margin-bottom: -3px; margin-left: -1px;" align=left>

										<!-- Select customer-->
										<div class="form-group ">
											<div class="col-md-4 selectContainer">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-list"></i></span> <select
														name="customerName" id="customerName"
														class="form-control selectpicker"
														onchange="location = this.value;">
														<c:if test="${not empty selectedName }">
															<option value="${selectedName}">${ selectedName}</option>
														</c:if>
														<option value="<c:out value=""/>">Select Customer</option>
														<c:forEach items="${customers}" var="customer">
															<option
																value="readingsCustomerByDevice?customerName=<c:out value='${customer.customerName}'/>">${customer.customerName}</option>
														</c:forEach>
													</select>

												</div>
											</div>
										</div>

										<!-- Text input serialNumber-->
										<div class="form-group">

											<div class="col-md-3 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-hdd"></i></span> <input
														name="serialNumber" list="deviceList" id="serialNumber"
														class="form-control" type="text"
														onkeydown="upperCaseF(this)"
														placeholder='Enter Serial Number'
														value="${selectedSerialNumber}" />
												</div>
											</div>

											<!-- Iterating over the list sent from Controller -->
											<datalist id="deviceList"> <c:forEach var="list"
												items="${deviceList}">
												<option value="${list.serialNumber}">
													<c:if test="${not empty selectedSerialNumber }">
														<option value="${selectedSerialNumber}">${selectedSerialNumber}</option>
													</c:if>
											</c:forEach> </datalist>
										</div>

										<!-- Text input Contract Start Date-->
										<div class="form-group ">
											<div class="col-md-3 inputGroupContainer">
												<div class="input-group input-append date"
													id="startDatePeriodPicker">
													<input type="text" class="form-control" name="period"
														id="period" placeholder="MM-YYYY"
														value="${selectedPeriod}"> <span
														class="input-group-addon"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
											</div>
										</div>


										<div class="col-md-2">
											<input class="btn btn-success" type='submit' value='Search' />
										</div>


									</div>
								</form:form>

								<!--Search-->
							</c:if>

							<c:if test="${not empty custName }"></c:if>

							<c:if test="${not empty custName}">

							<form:form class="well form-horizontal" action="captureReadings"
									modelAttribute="captureReadings" method="post"
									id="captureReadings">

									<div class="row"></div>
									<fieldset>
										<!-- Customer Device -->
										<legend>
											<b style="font-size: 15px;">Customer Device </b>
										</legend>

										<!-- Select type customers-->
										<div class="form-group">
											<label class="col-md-3 control-label">Selected Customer</label>
											<div class="col-md-6 inputGroupContainer">
												<div class="input-group">

													<span class="input-group-addon"><i
														class="glyphicon glyphicon-list"></i></span> <select
														readonly="readonly" name="customerName" id="customerName"
														class="form-control selectpicker"
														onchange="location = this.value;">
														<c:if test="${not empty selectedName }">
															<option value="${selectedName}">${ selectedName}</option>
														</c:if>

													</select>

												</div>
											</div>
										</div>

										<!-- Text input Search-->
										<div class="form-group">
											<label class="col-md-3 control-label">Selected Serial
												No</label>
											<div class="col-md-6 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-hdd"></i></span> <input
														name="serialNumber" list="deviceList" readonly="readonly" id="serialNumber"
														class="form-control" type="text"
														onkeydown="upperCaseF(this)"
														placeholder='Enter Serial Number'
														value="${selectedSerialNumber}" />
												</div>
											</div>

											<!-- Iterating over the list sent from Controller -->
											<datalist id="deviceList"> <c:forEach var="list"
												items="${deviceList}">
												<option value="${list.serialNumber}">
													<c:if test="${not empty selectedSerialNumber }">
														<option value="${selectedSerialNumber}">${selectedSerialNumber}</option>
													</c:if>
											</c:forEach> </datalist>
										</div>

									</fieldset>



									<fieldset>
										<!-- Period Date -->
										<legend>
											<b style="font-size: 15px;">Period</b>
										</legend>

										<!-- Text input Contract Start Date-->
										<div class="form-group ">
											<label class="col-xs-3 control-label">Selected Period</label>
											<div class="col-md-6 inputGroupContainer">
												<div class="input-group input-append date"
													id="startDatePeriodPicker">
													<input type="text" class="form-control" readonly="readonly" name="period"
														id="period" placeholder="MM-YYYY"
														value="${selectedPeriod}"> <span
														class="input-group-addon"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
											</div>
										</div>

									</fieldset>
									<br>
									
									<fieldset>
										<!-- Device Readings -->
										<br>
										<legend>
											<b style="font-size: 15px;">Device Readings</b>
										</legend>

										
										<c:forEach items="${readingBean}" var="reading">
										
											<div class="row grid-divider">
												<div class="col-sm-6">
													<div class="col-padding">
														<legend>
															<b style="font-size: 15px;"> Mono Reading</b>
														</legend>

														<!-- Text input Mono-->
														<div class="form-group">
															 <label class="col-xs-3 control-label">Previous</label>
														
															<div class="col-md-6 inputGroupContainer">
																<div class="input-group">
																	<span class="input-group-addon"><i
																		class="glyphicon glyphicon-barcode"></i></span> <input
																		id="previousMonoReading" name="previousMonoReading"
																		readonly class="form-control" type="text"
																		value="<c:out value="${reading.previousMonoReading}"/>">
																</div>
															</div>
														</div>

														<!-- Text input Color-->
														<div class="form-group">
														 <label class="col-xs-3 control-label">Current</label>
														
															<div class="col-md-6 inputGroupContainer">
																<div class="input-group">
																	<span class="input-group-addon"><i
																		class="glyphicon glyphicon-barcode"></i></span> <input
																		id="colorReading" name="colorReading"
																		placeholder="Current Mono Reading"
																		class="form-control" type="text">
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="col-sm-6">
													<div class="col-padding">
														<legend>
															<b style="font-size: 15px;"> Color Reading</b>
														</legend>
														<!-- Text input Mono-->
														<div class="form-group">
														<label class="col-xs-3 control-label">Previous </label>
														 <div class="col-md-6 inputGroupContainer">
																	<div class="input-group">
																	<span class="input-group-addon"><i
																		class="glyphicon glyphicon-barcode"></i></span> <input
																		id="previousColorReading" readonly
																		name="previousColorReading"
																		value="<c:out value="${reading.previousColorReading}"/>"
																		class="form-control" type="text">
																</div>
															</div>
														</div>

														<!-- Text input Color-->
														<div class="form-group">
														 <label class="col-xs-3 control-label">Current</label>
														
															<div class="col-md-6 inputGroupContainer">
																<div class="input-group">
																	<span class="input-group-addon"><i
																		class="glyphicon glyphicon-barcode"></i></span> <input
																		id="colourReading" name="colourReading"
																		placeholder="Current Colour Reading"
																		class="form-control" type="text">
																</div>
															</div>
														</div>

													</div>
												</div>
											</div>

										</c:forEach>

										<br />
										<div class="row">
											<div class="col-sm-4"></div>
											<div class="col-sm-4">
												<div class="col text-center">
													<input type="submit" value="Capture Readings"
														class="btn btn-primary btn-block btn-md" tabindex="9"
														id="captureReadings">
												</div>
											</div>
											<div class="col-sm-4"></div>

										</div>
									</fieldset>
									
									
								</form:form>

							</c:if>

							<c:if test="${empty custName }"></c:if>



							<!-- Read Readings -->

							<c:if test="${not empty serialNo}">

								<form:form class="well form-horizontal" action="captureReadings"
									modelAttribute="captureReadings" method="post"
									id="captureReadings">

								</form:form>

							</c:if>

							<c:if test="${empty serialNo }"></c:if>


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
		<c:import url="templates/javascriptslib.jsp"></c:import>
		
		<c:import url="templates/sidebar-collapse.jsp"></c:import>

		<!-- /Scripts -->
</body>
</html>