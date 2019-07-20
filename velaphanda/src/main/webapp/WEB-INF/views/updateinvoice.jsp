<%@ include file="templates/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Update Invoice Management | Velaphanda Trading & Projects</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="templates/tableresizefont.jsp"></c:import>
<c:import url="templates/stylesheetlib.jsp"></c:import>
<c:import url="templates/orderstyle.jsp"></c:import>
<c:import url="templates/invoicestyles.jsp"></c:import>

</head>
<body>

	<c:import url="templates/navbar.jsp"></c:import>

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href='<c:url value="/technicianHome"/>'><svg
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
					<!-- <div class="panel-heading" align="center">
						<b>New Order</b>
					</div> -->
					<div class="panel-body">

						<c:if test="${not empty retMessage }">
							<div class="alert alert-info" role="alert">

								<c:out value="${ retMessage}">
								</c:out>
							</div>
						</c:if>
						<c:if test="${not empty retErrorMessage }">
							<div class="alert alert-info" role="alert">

								<c:out value="${ retErrorMessage}">
								</c:out>
							</div>
						</c:if>

						<section class="form-box">

						<div class="row">
							<div class="col-sm-12 form-wizard">

								<!-- Form Wizard -->

								<%-- <form:form role="form" name="placeOrder"
									class="well form-horizontal" modelAttribute="makeOrder"
									method="post" action="makeOrder" id="putInOrder">
 --%>
								<h3>Create Invoice</h3>
								<!-- Form progress -->
								<div class="form-wizard-steps form-wizard-tolal-steps-4">
									<div class="form-wizard-progress">
										<div class="form-wizard-progress-line" data-now-value="12.25"
											data-number-of-steps="4" style="width: 12.25%;"></div>
									</div>
									<!-- Step 1 -->
									<div class="form-wizard-step active">
										<div class="form-wizard-step-icon">
											<i class="fa fa-user" aria-hidden="true"></i>
										</div>
										<p>Customer and Device Readings</p>
									</div>
									<!-- Step 1 -->

									<!-- Step 2 -->
									<div class="form-wizard-step">
										<div class="form-wizard-step-icon">
											<i class="fa fa-money" aria-hidden="true"></i>
										</div>
										<p>Create Invoice</p>
									</div>
									<!-- Step 2-->
								</div>
								<!-- Form progress -->


								<!-- Form Step 1 -->
								<fieldset>

									<h4>
										Provide Details: <span>Step 1 - 4</span>
									</h4>

									<p>
										Fields marked with <span style="color: red;">*</span> are
										required

										<c:if test="${empty custName }">


											<form:form action="invoiceSearchCustomerReading"
												method="post" id="searchCustomerReading"
												class="searchCustomerReading"
												modelAttribute="searchCustomerReading">

												<div>

													<!-- Select customer-->
													<div class="form-group ">
														<label>Customer Name <span>*</span></label> <select
															name="customerName" id="customerName"
															class="form-control required"
															onchange="location = this.value;">
															<c:if test="${not empty selectedName }">
																<option value="${selectedName}">${ selectedName}</option>
															</c:if>
															<option value="<c:out value=""/>">Select
																Customer</option>
															<c:forEach items="${customers}" var="customer">
																<option
																	value="invoiceCustomerByDevice?customerName=<c:out value='${customer.customerName}'/>">${customer.customerName}</option>
															</c:forEach>
														</select>
													</div>


													<!-- Text input serialNumber-->
													<div class="form-group">
														<label>Serial Number <span>*</span></label> <input
															name="serialNumber" list="deviceList" id="serialNumber"
															class="form-control required" type="text"
															onkeydown="upperCaseF(this)"
															placeholder='Enter Serial Number'
															value="${selectedSerialNumber}" />
													</div>


													<!-- Iterating over the list sent from Controller -->
													<datalist id="deviceList"> <c:forEach var="list"
														items="${deviceList}">
														<option value="${list.serialNumber}">
															<c:if test="${not empty selectedSerialNumber }">
																<option value="${selectedSerialNumber}">${selectedSerialNumber}</option>
															</c:if>
													</c:forEach> </datalist>

													<!-- Text input selected Period-->
													<div class="form-group ">
														<label>Period<span>*</span></label>
														<div class="input-group input-append date"
															id="startDatePeriodPicker">
															<input type="text" class="form-control required"
																name="period" id="period" placeholder="MM-YYYY"
																value="${selectedPeriod}"> <span
																class="input-group-btn">
																<button class="btn btn-success" type="submit">
																	<div class="up" style="margin-top: -8%; color: white;">Search</div>
																</button>
															</span> </span>
														</div>

													</div>
													<!-- /input-group -->

													<br />


												</div>

											</form:form>

											<!--Search-->
										</c:if>

										<c:if test="${not empty custName }"></c:if>

										<c:if test="${not empty custName}">

											<form:form class="well form-horizontal"
												action="captureReadings" modelAttribute="captureReadings"
												method="post" id="captureReadings">

												<div class="row"></div>
												<fieldset>
													<!-- Customer Device -->
													<legend>
														<b style="font-size: 15px;">Customer Device </b>
													</legend>
													<%-- 
													<!-- Text input recordID-->
													<div class="form-group ">
														<label class="col-xs-3 control-label">Record ID</label>
														<div class="col-md-6 inputGroupContainer">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="glyphicon glyphicon-list"></i></span> <input
																	type="text" class="form-control" name="recordID"
																	id="recordID" value="${readingBean.recordID}"
																	readonly="readonly">
															</div>
														</div>
													</div> --%>

													<%-- <!-- Text input Reading Number-->
										<div class="form-group ">
											<label class="col-xs-3 control-label">Reading Number</label>
											<div class="col-md-6 inputGroupContainer">
											<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-list"></i></span>  
													<input type="text" class="form-control" name="readingNumber"
														id="readingNumber" value="${readingNumber}" readonly="readonly"> 
											</div>
											</div>
										</div> --%>

													<!-- Select type customers-->
													<div class="form-group">
														<label class="col-md-3 control-label">Selected
															Customer</label>
														<div class="col-md-6 inputGroupContainer">
															<div class="input-group">

																<span class="input-group-addon"><i
																	class="glyphicon glyphicon-list"></i></span> <select
																	readonly="readonly" name="customerName"
																	id="customerName" class="form-control selectpicker"
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
														<label class="col-md-3 control-label">Selected
															Serial No</label>
														<div class="col-md-6 inputGroupContainer">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="glyphicon glyphicon-hdd"></i></span> <input
																	name="serialNumber" list="deviceList"
																	readonly="readonly" id="serialNumber"
																	class="form-control" type="text"
																	onkeydown="upperCaseF(this)"
																	placeholder='Enter Serial Number'
																	value="${selectedSerialNumber}" />
															</div>
														</div>

														<!-- Iterating over the list sent from Controller -->
														<datalist id="deviceList"> <c:forEach
															var="list" items="${deviceList}">
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
														<label class="col-xs-3 control-label">Selected
															Period</label>
														<div class="col-md-6 inputGroupContainer">
															<div class="input-group input-append date"
																id="startDatePeriodPicker">
																<input type="text" class="form-control"
																	readonly="readonly" name="period" id="period"
																	placeholder="MM-YYYY" value="${selectedPeriod}">
																<span class="input-group-addon"> <span
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


													<%-- <c:forEach items="${readingBean}" var="reading"> --%>

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
																				value="${readingBean.previousMonoReading}">
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
																				id="monoReading" name="monoReading"
																				placeholder="Current Mono Reading"
																				class="form-control" type="text"
																				value="${readingBean.monoReading}">
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
																	<label class="col-xs-3 control-label">Previous
																	</label>
																	<div class="col-md-6 inputGroupContainer">
																		<div class="input-group">
																			<span class="input-group-addon"><i
																				class="glyphicon glyphicon-barcode"></i></span> <input
																				id="previousColorReading" readonly
																				name="previousColorReading" class="form-control"
																				type="text"
																				value="${readingBean.previousColorReading}">
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
																				class="form-control" type="text"
																				value="${readingBean.colorReading}">
																		</div>
																	</div>
																</div>

															</div>
														</div>
													</div>
											</form:form>

										</c:if>

										<c:if test="${empty custName }"></c:if>
									<div class="form-wizard-buttons">
										<button type="button" class="btn btn-next">Next</button>
									</div>

									</p>
								</fieldset>
								<!-- Form Step 1 -->



								<!-- Form Step 2 -->
								<fieldset>

									<h4>
										Create Invoice: <span>Step 2 - 2</span>
									</h4>
									<div style="clear: both;"></div>
									<p>
										<br />
									<div class="row">
										<div class="col-xs-5">
											<h1>
												<img src="resources/images/mainlogo_small.jpg" class="img-responsive">
											</h1>
										</div>
										<div class="col-xs-6 text-right">
											<div class="row">
												<div class="col-xs-6">
													<h1 class="invoice_type">INVOICE</h1>
												</div>
												<div class="col-xs-3">
													<select name="invoice_type" id="invoice_type"
														class="form-control">
														<option value="invoice" selected>Invoice</option>
														<!-- 	<option value="quote">Quote</option>
														<option value="receipt">Receipt</option> -->
													</select>
												</div>
												<div class="col-xs-3">
													<select name="invoice_status" id="invoice_status"
														class="form-control">
														<option value="open" selected>Open</option>
														<option value="paid">Partial</option>
														<option value="draft">Draft</option>
														<option value="paid">Paid</option>
													</select>
												</div>
											</div>
											<div class="col-xs-4 no-padding-right">
												<div class="form-group">
													<div class="input-group date" id="invoice_date">
														<input type="text" class="form-control required"
															name="invoice_date" placeholder="Select invoice date"
															data-date-format="DD/MM/YYYY" /> <span
															class="input-group-addon"> <span
															class="glyphicon glyphicon-calendar"></span>
														</span>
													</div>
												</div>
											</div>
											<div class="col-xs-4">
												<div class="form-group">
													<div class="input-group date" id="invoice_due_date">
														<input type="text" class="form-control required"
															name="invoice_due_date" placeholder="Select due date"
															data-date-format="DD/MM/YYYY" /> <span
															class="input-group-addon"> <span
															class="glyphicon glyphicon-calendar"></span>
														</span>
													</div>
												</div>
											</div>
											<div class="input-group col-xs-4 float-right">
												<span class="input-group-addon">INV</span> <input
													type="text" name="invoice_id" id="invoice_id"
													class="form-control required" placeholder="Invoice Number"
													aria-describedby="sizing-addon1" value="1">
											</div>
										</div>
									</div>
									<div class="row">


										<div class="col-xs-6">
											<div class="panel panel-default">
												<div class="panel-heading">INOVICE FROM
												<h4 class="float-left">Vendor Information</h4>
													
													<div class="clear"></div>
												
												</div>
												<div class="panel-body form-group form-group-sm">
													<div class="row">
														<div class="col-xs-6">
															Company Address
															<div class="form-group">
																<input type="text"
																	class="form-control margin-bottom copy-input required"
																	name="customer_name" id="customer_name"
																	placeholder="Enter name" tabindex="1"
																	value="Velaphanda Trading & Projects ">
															</div>
															<div class="form-group">
																<input type="text"
																	class="form-control margin-bottom copy-input required"
																	name="customer_address_1" id="customer_address_1"
																	placeholder="Address 1" tabindex="3"
																	value="296 Galway Avenue, Bronberrick">
															</div>
															<div class="form-group">
																<input type="text"
																	class="form-control margin-bottom copy-input required"
																	name="customer_town" id="customer_town"
																	placeholder="Town" tabindex="5" value="Centurion">
															</div>
															<div class="form-group no-margin-bottom">
																<input type="text"
																	class="form-control copy-input required"
																	name="customer_postcode" id="customer_postcode"
																	placeholder="Postcode" tabindex="7" value="0158">
															</div>

														</div>

														<div class="col-xs-6">
															Contact Details
															<div class="form-group no-margin-bottom">
																<input type="text"
																	class="form-control copy-input required"
																	name="customer_postcode" id="customer_postcode"
																	placeholder="Postcode" tabindex="7"
																	value="Switchboard: 012 765 0200/087 701 1689">
															</div>
															<div class="form-group no-margin-bottom">
																<input type="text"
																	class="form-control copy-input required"
																	name="customer_postcode" id="customer_postcode"
																	placeholder="Postcode" tabindex="7"
																	value="Fax : 086 403 7955">
															</div>

															<div class="form-group no-margin-bottom">
																<input type="text"
																	class="form-control copy-input required"
																	name="customer_postcode" id="customer_postcode"
																	placeholder="Postcode" tabindex="7"
																	value="Email : admin@velaphanda.co.za">
															</div>

														</div>

													</div>
												</div>
											</div>
										</div>



										<div class="col-xs-6 text-left">
											<div class="panel panel-default">
												<div class="panel-heading">
													INOVICE TO
													<div class="row">
													<div class=col-sm-5><h4 class="">Customer Information</h4>
													</div>
													
													<div class=col-sm-5><a href="#" class="select-customer">Select Existing customer</a></div>
													<div class="clear"></div>
													</div>
												</div>
												<div class="panel-body form-group form-group-sm">
													<div class="row">
														<div class="col-xs-6">
															Contact Person
															<div class="form-group">
																<input type="text"
																	class="form-control margin-bottom copy-input required"
																	name="customer_name" id="customer_name"
																	placeholder="Enter firstname" tabindex="1">
															</div>
															<div class="form-group">
																<input type="text"
																	class="form-control margin-bottom copy-input required"
																	name="customer_address_1" id="customer_address_1"
																	placeholder="Enter lastname" tabindex="3">
															</div>
															<div class="form-group">
																<input type="text"
																	class="form-control margin-bottom copy-input required"
																	name="customer_town" id="customer_town"
																	placeholder="Cellphone no" tabindex="5">
															</div>
															<div class="form-group no-margin-bottom">
																<input type="text"
																	class="form-control copy-input required"
																	name="customer_postcode" id="customer_postcode"
																	placeholder="email" tabindex="7">
															</div>
														</div>
														<div class="col-xs-6">
															Customer Address
															<div class="input-group float-right margin-bottom">
																<span class="input-group-addon">@</span> <input
																	type="email" class="form-control copy-input required"
																	name="customer_email" id="customer_email"
																	placeholder="E-mail address"
																	aria-describedby="sizing-addon1" tabindex="2">
															</div>
															<div class="form-group">
																<input type="text"
																	class="form-control margin-bottom copy-input"
																	name="customer_address_2" id="customer_address_2"
																	placeholder="Address 2" tabindex="4">
															</div>
															<div class="form-group">
																<input type="text"
																	class="form-control margin-bottom copy-input required"
																	name="customer_county" id="customer_county"
																	placeholder="County" tabindex="6">
															</div>
															<div class="form-group no-margin-bottom">
																<input type="text" class="form-control required"
																	name="customer_phone" id="customer_phone"
																	placeholder="Phone number" tabindex="8">
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- / end client details section -->
									<table class="table table-bordered" id="invoice_table">
										<thead>
											<tr>
												<th width="500">
													<h4>
														<a href="#" class="btn btn-success btn-xs add-row"><span
															class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
														Item
													</h4>
												</th>
												<th>
													<h4>Qty</h4>
												</th>
												<th>
													<h4>Price</h4>
												</th>
												<th width="300">
													<h4>Discount</h4>
												</th>
												<th>
													<h4>Sub Total</h4>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<div class="form-group form-group-sm  no-margin-bottom">
														<a href="#" class="btn btn-danger btn-xs delete-row"><span
															class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
														<input type="text"
															class="form-control form-group-sm item-input invoice_product"
															name="invoice_product[]"
															placeholder="Enter item title and / or description">
														<p class="item-select">
															or <a href="#">select an item</a>
														</p>
													</div>
												</td>
												<td class="text-right">
													<div class="form-group form-group-sm no-margin-bottom">
														<input type="text"
															class="form-control invoice_product_qty calculate"
															name="invoice_product_qty[]" value="1">
													</div>
												</td>
												<td class="text-right">
													<div class="input-group input-group-sm  no-margin-bottom">
														<span class="input-group-addon">R</span> <input
															type="text"
															class="form-control calculate invoice_product_price required"
															name="invoice_product_price[]"
															aria-describedby="sizing-addon1" placeholder="0.00">
													</div>
												</td>
												<td class="text-right">
													<div class="form-group form-group-sm  no-margin-bottom">
														<input type="text" class="form-control calculate"
															name="invoice_product_discount[]"
															placeholder="Enter % or value (ex: 10% or 10.50)">
													</div>
												</td>
												<td class="text-right">
													<div class="input-group input-group-sm">
														<span class="input-group-addon">R</span> <input
															type="text" class="form-control calculate-sub"
															name="invoice_product_sub[]" id="invoice_product_sub"
															value="0.00" aria-describedby="sizing-addon1" disabled>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
									<div id="invoice_totals" class="padding-right row text-right">
										<br> <br>
										<div class="col-xs-6">
											<div
												class="input-group form-group-sm textarea no-margin-bottom">
												<textarea class-"form-control" name="invoice_notes"
													placeholder="Please enter any order notes here."></textarea>
											</div>
										</div>
										<div class="col-xs-6 no-padding-right">
											<div class="row">
												<div class="col-xs-4 col-xs-offset-5">
													<strong>Sub Total:</strong>
												</div>
												<div class="col-xs-3">
													R<span class="invoice-sub-total">0.00</span> <input
														type="hidden" name="invoice_subtotal"
														id="invoice_subtotal">
												</div>
											</div>
											<div class="row">
												<div class="col-xs-4 col-xs-offset-5">
													<strong>Discount:</strong>
												</div>
												<div class="col-xs-3">
													R<span class="invoice-discount">0.00</span> <input
														type="hidden" name="invoice_discount"
														id="invoice_discount">
												</div>
											</div>
											<!-- <!-- <div class="row">
												<div class="col-xs-4 col-xs-offset-5">
													<strong class="shipping">Shipping:</strong>
												</div>
												<div class="col-xs-3">
													<div class="input-group input-group-sm">
														<span class="input-group-addon">R</span> <input
															type="text" class="form-control calculate shipping"
															name="invoice_shipping" aria-describedby="sizing-addon1"
															placeholder="0.00">
													</div>
												</div>
											</div> -->
											<div class="row">
												<div class="col-xs-4 col-xs-offset-5">
													<strong>TAX/VAT:</strong><br>Remove TAX/VAT <input
														type="checkbox" class="remove_vat">
												</div>
												<div class="col-xs-3">
													R<span class="invoice-vat" data-enable-vat="1"
														data-vat-rate="20" data-vat-method="">0.00</span> <input
														type="hidden" name="invoice_vat" id="invoice_vat">
												</div>
											</div>
											<div class="row">
												<div class="col-xs-4 col-xs-offset-5">
													<strong>Total:</strong>
												</div>
												<div class="col-xs-3">
													R<span class="invoice-total">0.00</span> <input
														type="hidden" name="invoice_total" id="invoice_total">
												</div>
											</div>
										</div>

									</div>

									</form>

									<div id="insert" class="modal fade">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
													<h4 class="modal-title">Select an item</h4>
												</div>
												<div class="modal-body">
													<select class="form-control item-select"><option
															value="45454.21">Device with Reading</option></select>
												</div>
												<div class="modal-footer">
													<button type="button" data-dismiss="modal"
														class="btn btn-primary" id="selected">Add</button>
													<button type="button" data-dismiss="modal" class="btn">Cancel</button>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- /.modal -->

									<div id="insert_customer" class="modal fade">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
													<h4 class="modal-title">Select an existing customer</h4>
												</div>
												<div class="modal-body">
													<table class="table table-striped table-bordered"
														id="data-table">

														<thead>
															<tr>
																<th><h4>Name</h4></th>
																<th><h4>Email</h4></th>
																<th><h4>Phone</h4></th>
																<th><h4>Action</h4></th>
															</tr>
														</thead>
														<tbody>

															<tr>
																<td>sharan</td>
																<td>sahaeran@dd.com</td>
																<td>0552323234</td>
																<td><a href="#"
																	class="btn btn-primary btn-xs customer-select"
																	data-customer-name="Bean Group"
																	data-customer-email="sahaeran@beangroup.com"
																	data-customer-phone="0552323234"
																	data-customer-address-1="Pretoria"
																	data-customer-address_2="Centurion"
																	data-customer-town="Centurion"
																	data-customer-county="RSA"
																	data-customer-postcode="4343"
																	data-customer-name-ship="Pretoria"
																	data-customer-address-1-ship="Pretoria"
																	data-customer-address-2-ship="Pretoria"
																	data-customer-town-ship="Centurion"
																	data-customer-county-ship="RSA"
																	data-customer-postcode-ship="4343">Select</a></td>
															</tr>



														</tbody>
													</table>
												</div>
												<div class="modal-footer">
													<button type="button" data-dismiss="modal" class="btn">Cancel</button>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- /.modal -->
									<div class="form-wizard-buttons">
										<br> <br>
										<button type="button" class="btn btn-previous">Previous</button>

										<div class="center" align="center">
											<div class="form-group row">
												<div class="col-sm-8">
													<br> <br>


													<div class="col-xs-12 margin-top btn-group">
														<input type="submit" id="action_create_invoice"
															class="btn-primary btn-block btn-lg"
															value="Create Invoice" data-loading-text="Creating..."
															onclick="return confirm('Are you sure you want to create invoice?');">
													</div>

												</div>
											</div>
										</div>

									</div>


								</fieldset>
								<!-- Form Step 2 -->

								<%-- </form:form> --%>
								<!-- Form Wizard -->
							</div>
						</div>




						</section>


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
	<c:import url="templates/ticketmanagementscript.jsp"></c:import>
	<c:import url="templates/sidebar-collapse.jsp"></c:import>
	<c:import url="templates/orderscript.jsp"></c:import>
	<c:import url="templates/invoicescripts.jsp"></c:import>

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

	<!-- /Scripts -->
</body>
</html>
