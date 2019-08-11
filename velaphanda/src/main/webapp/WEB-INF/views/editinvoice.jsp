<%@ include file="templates/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Edit Invoice | Velaphanda Trading & Projects</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="templates/tableresizefont.jsp"></c:import>
<c:import url="templates/stylesheetlib.jsp"></c:import>
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

						<div class="row">

							<div class="col-sm-12">

								<h3>Edit Invoice</h3>


								<form:form action="updateInvoice" modelAttribute="updateInvoice"
									method="post" id="updateInvoice">

									<div style="clear: both;"></div>
									<p>
										<br />

										<c:if test="${not empty custName}">

											<div class="well form-horizontal">
											
												<fieldset>

													<!-- Customer Device -->
													<legend>
														<b style="font-size: 15px;">Customer Device </b>
													</legend>

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
													</div>

													<%-- <!-- Text input Reading Number-->
													<div class="form-group ">
														<label class="col-xs-3 control-label">Reading
															Number</label>
														<div class="col-md-6 inputGroupContainer">
															<div class="input-group">
																<span class="input-group-addon"><i
																	class="glyphicon glyphicon-list"></i></span> <input
																	type="text" class="form-control" name="readingNumber"
																	id="readingNumber" value="${readingNumber}"
																	readonly="readonly">
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
																				value="${readingBean.monoReading}"
																				readonly="readonly">
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
																				value="${readingBean.colorReading}"
																				readonly="readonly">
																		</div>
																	</div>
																</div>

															</div>
														</div>
													</div>
												</fieldset>
											</div>
										</c:if>
										<c:if test="${empty custName }"></c:if>
									<div class="row">
										<div class="col-sm-3"></div>
										<div class="col-xs-6 text-right">
											<div class="row">


												<div class="col-xs-4"></div>
											</div>
											<div class="col-xs-4"></div>
											<div class="col-xs-4"></div>

										</div>
										<div class="col-sm-6"></div>

										<div class="col-xs-3">

											<div class="form-group">
												<label>Status</label> <select name="status" id="status"
													class="form-control">
													<option value="open" selected>Open</option>
													<option value="paid">Partial</option>
													<option value="draft">Draft</option>
													<option value="paid">Paid</option>
												</select>
											</div>

											<div class="form-group">
												<label>Invoice Number</label>
												<div class="input-group" id="invoiceNumber">
													<span class="input-group-addon">INV</span> <input
														type="text" name="invoiceNumber" id="invoiceNumber"
														class="form-control" placeholder="Invoice Number"
														aria-describedby="sizing-addon1" value="">
												</div>
											</div>

										</div>

										<div class="col-xs-3">
											<div class="form-group">
												<label>Invoice Date</label>
												<div class="input-group date" id="invoice_date">
													<input type="text" class="form-control" name="invoiceDate"
														placeholder="Select invoice date"
														data-date-format="DD/MM/YYYY" /> <span
														class="input-group-addon"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
											</div>
											<div class="form-group">
												<label>Invoice Due Date</label>
												<div class="input-group date" id="invoice_due_date">
													<input type="text" class="form-control"
														name="invoiceDueDate"
														placeholder="Select invoice due date"
														data-date-format="DD/MM/YYYY" /> <span
														class="input-group-addon"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
											</div>
										</div>
										
									</div>


									<div class="row">

										<div class="col-xs-6">
											<div class="panel panel-default">
												<div class="panel-heading">
													INOVICE FROM
													<div class="row">
														<div class=col-sm-6>
															<h4 class="float-left">Vendor Information</h4>
														</div>


														<div class="clear"></div>
													</div>
												</div>

												<div class="panel-body form-group form-group-sm">
													<div class="row">

														<div class="col-xs-6">
															<div class="form-group">
																<img src="resources/images/mainlogo_small.jpg"
																	class="img-responsive"
																	style="width: 53px; height: 63px; background-position: 50% 50%;">
															</div>

															<div class="form-group no-margin-bottom">
																<label>Switchboard </label> <input type="text"
																	class="form-control copy-input"
																	name="customer_postcode" id="customer_postcode"
																	placeholder="Postcode" tabindex="7"
																	value="012 765 0200 / 087 701 1689" readonly="readonly">
															</div>
															<div class="form-group no-margin-bottom">
																<label>Fax No </label> <input type="text"
																	class="form-control copy-input"
																	name="customer_postcode" id="customer_postcode"
																	placeholder="Postcode" tabindex="7"
																	value="086 403 7955" readonly="readonly">
															</div>
															<div class="form-group no-margin-bottom">
																<label>Email </label> <input type="text"
																	class="form-control copy-input"
																	name="customer_postcode" id="customer_postcode"
																	placeholder="Postcode" tabindex="7"
																	value="admin@velaphanda.co.za" readonly="readonly">
															</div>

														</div>

														<div class="col-xs-6">


															<div class="form-group">
																<label>Company Name</label> <input type="text"
																	class="form-control margin-bottom copy-input"
																	name="customer_name" id="customer_name"
																	placeholder="Enter name" tabindex="1"
																	value="Velaphanda Trading & Projects"
																	readonly="readonly">
															</div>
															<div class="form-group">
																<label>Address</label> <input type="text"
																	class="form-control margin-bottom copy-input"
																	name="customer_address_1" id="customer_address_1"
																	placeholder="Address 1" tabindex="3"
																	value="296 Galway Avenue, Bronberrick"
																	readonly="readonly">
															</div>
															<div class="form-group">
																<label>Town</label> <input type="text"
																	class="form-control margin-bottom copy-input"
																	name="customer_town" id="customer_town"
																	placeholder="Town" tabindex="5" value="Centurion"
																	readonly="readonly">
															</div>
															<div class="form-group no-margin-bottom">
																<label>Postal code</label> <input type="text"
																	class="form-control copy-input"
																	name="customer_postcode" id="customer_postcode"
																	placeholder="Postcode" tabindex="7" value="0158"
																	readonly="readonly">
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
														<div class=col-sm-6>
															<h4 class="">Customer Information</h4>
														</div>


														<div class="clear"></div>
													</div>
												</div>
												<div class="panel-body form-group form-group-sm">
													<div class="row">

														<div class="col-xs-6">
															<label>First name</label>
															<div class="form-group">
																<input type="text"
																	class="form-control margin-bottom copy-input"
																	name="contactPersonFirstname"
																	id="contactPersonFirstname"
																	placeholder="Contact Person Enter firstname"
																	tabindex="1">
															</div>
															<div class="form-group">
																<label>Last name</label> <input type="text"
																	class="form-control margin-bottom copy-input"
																	name="contactPersonLastname" id="contactPersonLastname"
																	placeholder="Contact Person Enter lastname"
																	tabindex="3">
															</div>
															<div class="form-group">
																<label>CellPhone No</label> <input type="text"
																	class="form-control margin-bottom copy-input"
																	name="contactPersonCellphoneNo"
																	id="contactPersonCellphoneNo"
																	placeholder="Contact Person Cellphone no" tabindex="5">
															</div>
															<div class="form-group no-margin-bottom">
																<label>Email</label> <input type="text"
																	class="form-control copy-input"
																	name="contactPersonEmail" id="contactPersonEmail"
																	placeholder="Contact Person Email" tabindex="7">
															</div>
														</div>
														<div class="col-xs-6">


															<div class="form-group">
																<label>Customer Address</label> <input type="text"
																	class="form-control margin-bottom copy-input"
																	name="customer_address_2" id="customer_address_2"
																	placeholder="Customer Address" tabindex="4">
															</div>
															<div class="form-group">
																<div class="form-group no-margin-bottom">
																	<label>Customer Telephone No</label> <input type="text"
																		class="form-control" name="customer_phone"
																		id="customer_phone"
																		placeholder="Customer Phone number" tabindex="8">
																</div>
																<div class="form-group">
																	<label>Customer Province</label> <input type="text"
																		class="form-control margin-bottom copy-input"
																		name="customer_province" id="customer_province"
																		placeholder="Customer Province" tabindex="6">
																</div>
																<div class="form-group no-margin-bottom">
																	<label>Customer Email</label> <input type="text"
																		class="form-control copy-input" name="customerEmail"
																		id="customerEmail" placeholder="Customer Email"
																		tabindex="7">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<!-- / end client details section -->
										<div class="row" style="margin-right: 0%; margin-left: 0%;">
											<div class="col-sm-12">
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
																	<a href="#" class="btn btn-danger btn-xs delete-row">
																		<span class="glyphicon glyphicon-remove"
																		aria-hidden="true"></span>
																	</a>
																	<div class="form-group">

																		<div class="col-md-8">

																			<input type="text"
																				class="form-control item-input invoice_product"
																				name="invoice_product[]"
																				placeholder="Enter item and / or description">

																		</div>
																	</div>
																	<p class="item-select">
																		or <a href="#">Select an item</a>
																	</p>
																</div>
															</td>
															<td class="text-right">
																<div class="form-group form-group-sm no-margin-bottom">
																	<input type="text"
																		class="form-control invoice_product_qty calculate"
																		name="quantity[]" value="1">
																</div>
															</td>
															<td class="text-right">
																<div
																	class="input-group input-group-sm  no-margin-bottom">
																	<span class="input-group-addon">R</span> <input
																		type="text"
																		class="form-control calculate invoice_product_price"
																		name="price[]" aria-describedby="sizing-addon1"
																		placeholder="0.00">
																</div>
															</td>
															<td class="text-right">
																<div class="form-group form-group-sm  no-margin-bottom">
																	<input type="text" class="form-control calculate"
																		name="discount[]"
																		placeholder="Enter % or value (ex: 10% or 10.50)">
																</div>
															</td>
															<td class="text-right">
																<div class="input-group input-group-sm">
																	<span class="input-group-addon">R</span> <input
																		type="text" class="form-control calculate-sub"
																		name="subTotal[]" id="subTotal" value="0.00"
																		aria-describedby="sizing-addon1" disabled>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>

										<div id="invoice_totals" class="padding-right row text-right">
											<br> <br>
											<div class="col-xs-6">

												<!-- Text input Invoice Note-->
												<div class="form-group">

													<div class="col-md-8 inputGroupContainer">
														<div class="input-group">
															<textarea class="form-control" name="invoice_notes"
																placeholder="Please enter any invoice notes here."
																style="margin: 0px; width: 649px; height: 89px;"></textarea>
														</div>
													</div>
												</div>

											</div>
											<div class="col-xs-6 no-padding-right">
												<div class="row">
													<div class="col-xs-4 col-xs-offset-5">
														<b>Sub Total:</b>
													</div>
													<div class="col-xs-3">
														R<span class="invoice-sub-total">0.00</span> <input
															type="hidden" name="subTotal" id="subTotal">
													</div>
												</div>
												<div class="row">
													<div class="col-xs-4 col-xs-offset-5">
														<b>Discount:</b>
													</div>
													<div class="col-xs-3">
														R<span class="invoice-discount">0.00</span> <input
															type="hidden" name="discount" id="discount">
													</div>
												</div>

												<div class="row">
													<div class="col-xs-4 col-xs-offset-5">
														<b>TAX/VAT:</b><br>Remove TAX/VAT <input
															type="checkbox" class="remove_vat">
													</div>
													<div class="col-xs-3">
														R<span class="invoice-vat" data-enable-vat="1"
															data-vat-rate="15" data-vat-method="">0.00</span> <input
															type="hidden" name="vat" id="vat">
													</div>
												</div>
												<div class="row">
													<div class="col-xs-4 col-xs-offset-5">
														<b>Total:</b>
													</div>
													<div class="col-xs-3">
														R<span class="invoice-total">0.00</span> <input
															type="hidden" name="total" id="total">
													</div>
												</div>
											</div>

										</div>

										<div id="insert" class="modal fade">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
														<h4 class="modal-title">Select an Line item</h4>
													</div>
													<div class="modal-body">
														<div class="well form-horizontal">
															<div class="form-group">
																<select class="form-control item-select" name="" id="">
																	<option value="45454.21"></option>
																	<option value="200.11"></option>
																	<option value="300.21"></option>
																</select>
															</div>
														</div>
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

									</div>

									<div class="form-wizard-buttons">
										

										<div class="row">
											<br> <br>
											<div class="col-sm-3"></div>
											<div class="col-sm-3">
												<div class="col text-center">
													<input type="submit" name="SaveInvoice"
														value="Save Invoice"
														class="btn btn-primary btn-block btn-md" tabindex="9"
														id="SaveInvoice">
												</div>
											</div>

											<div class="col-sm-3">
												<div class="col text-center">
													<input type="submit" name="SubmitInvoice"
														value="Submit Invoice"
														class="btn btn-primary btn-block btn-md" tabindex="9"
														id="submitInvoice">
												</div>
											</div>
											<div class="col-sm-3"></div>

										</div>

									</div>

								</form:form>

								</fieldset>
							</div>
						</div>

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
		request
				.open(
						'GET',
						'https://s3-us-west-2.amazonaws.com/s.cdpn.io/4621/html-elements.json',
						true);
		request.send();
	</script>

	<!-- /Scripts -->
</body>
</html>
