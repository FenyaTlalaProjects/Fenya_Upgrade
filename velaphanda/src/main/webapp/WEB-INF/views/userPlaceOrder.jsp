<%@ include file="templates/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Order | Velaphanda Trading & Projects</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="templates/tableresizefont.jsp"></c:import>
<c:import url="templates/stylesheetlib.jsp"></c:import>
<c:import url="templates/orderstyle.jsp"></c:import>
</head>
<body>

	<c:import url="templates/usernavbar.jsp"></c:import>

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href='<c:url value="/userdashboard"/>'><svg class="glyph stroked home">
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
						<b>New Order</b>
					</div>
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

								<form:form role="form" name="placeOrder" class="well form-horizontal"
									modelAttribute="makeOrder" method="post" action="makeOrder"
									id="putInOrder">


									<h3>New Order</h3>
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
											<p>Stock Type and Approver</p>
										</div>
										<!-- Step 1 -->

										<!-- Step 2 -->
										<div class="form-wizard-step HO-stock">
											<div class="form-wizard-step-icon">
												<i class="fa fa-home" aria-hidden="true"></i>
											</div>
											<p>Available HO Stock</p>
										</div>
										<!-- Step 2 -->

										<!-- Step 3 -->
										<div class="form-wizard-step toOrder-stock">
											<div class="form-wizard-step-icon">
												<i class="fa fa-list" aria-hidden="true"></i>
											</div>
											<p>Selected Line Items</p>
										</div>
										<!-- Step 3 -->

										<!-- Step 4 -->
										<div class="form-wizard-step">
											<div class="form-wizard-step-icon">
												<i class="fa fa-money" aria-hidden="true"></i>
											</div>
											<p>Place Order</p>
										</div>
										<!-- Step 4 -->
									</div>
									<!-- Form progress -->


									<!-- Form Step 1 -->
									<fieldset>

										<h4>
											Provide Details: <span>Step 1 - 4</span>
										</h4>
										
										<p>Fields marked with <span style="color:red;">*</span> are required</p>

										<!-- Select type Stock Type-->
										<div class="form-group">
											<label>Stock Type <span>*</span></label> <select
												class="form-control required" id="stockType"
												name="stockType" onchange='CheckStockType(this.value);'>
												<option value="">Select Stock Type</option>
												<option value="Boot">Boot</option>
												<option value="Site">Site</option>
											</select>
										</div>
										
										<!-- Text input Technician-->
										<div id="Boot" style='display: none;'>
											<div class="form-group">
												<label>Technician <span>*</span></label> <select
													class="form-control" name="technicianUserName"
													id="technicianUserName">
													<option value="">Select Technician</option>
													<c:forEach items="${technicianList}" var="technician">
														<option value="${technician.email}">${technician.firstName}
															${technician.lastName}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- Text input Customer Name-->
										<div id="Site" style='display: none;'>
											<div class="form-group">
												<label>Customer Name <span>*</span></label> <select
													class="form-control" id="customer" name="customer">
													<option value="">Customer Name</option>
													<c:forEach items="${customerList}" var="customer">
														<option value="${customer.customerName}">${customer.customerName}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<!-- Text input Approver-->
										<div class="form-group">
											<label>Approver <span>*</span></label> <select
												class="form-control required" name="approver" id="approver">
												<option value="">Select Manager</option>
												<c:forEach items="${managersList}" var="approver">
													<option value="${approver.email}">${approver.firstName}
														${approver.lastName}</option>
												</c:forEach>
											</select>
										</div>


										<div class="form-wizard-buttons">
											<button type="button" class="btn btn-next">Next</button>
										</div>
									</fieldset>
									<!-- Form Step 1 -->

									<!-- Form Step 2 -->
									<fieldset>

										<h4>
											Select Head Office Stock to Order : <span>Step 2 - 4</span>
										</h4>


										<table id="stockForOrder" data-toggle="table"
											data-show-refresh="true" data-show-toggle="true"
											data-search="true" data-select-item-name="toolbar1"
											data-pagination="true" data-sort-name="partno"
											data-sort-order="desc">
											<thead>
												<tr>
													<th data-field="partno" data-sortable="true">Part No</th>
													<th data-field="description" data-sortable="true">Description</th>
													<th data-field="modelno" data-sortable="true">Model No</th>
													<th data-field="customer" data-sortable="true">Available
														QTY</th>
													<th data-field="provideqty" data-sortable="true">Provide
														QTY</th>
													<th data-field="action" data-sortable="true">Action</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach var="list" items="${compatibility}">
													<tr>
														<td>${list.partNumber}</td>
														<td>${list.itemDescription}</td>
														<td>${list.compitableDevice}</td>
														<td><input type="text"
															id="${list.partNumber}_avaliableQuantity"
															name="avaliableQuantity" class="form-control"
															readonly="readonly" value="${list.quantity}"></td>
														<td><input type="text"
															id="${list.partNumber}_quantity" name="quantity"
															class="form-control" onkeypress="return isNumber(event)"
															onblur="compareQuantity(this, ${list.quantity})" value="" /></td>
														<td><input class="addLineItem" type="button"
															value="Add"></td>
													</tr>
												</c:forEach>

											</tbody>
										</table>

										<div class="form-wizard-buttons">
											<button type="button" class="btn btn-previous">Previous</button>
											<button type="button" id="goToNextPage" class="goToNextPage btn btn-next">Next</button>
										</div>
									</fieldset>
									<!-- Form Step 2 -->

									<!-- Form Step 3 -->
									<fieldset>

										<h4>
											Selected Order Line Items: <span>Step 3 - 4</span>
										</h4>
										<table id="toOrder"
											class="table table-striped table-bordered table-hover table-condensed"
											data-show-refresh="true" data-show-toggle="true"
											data-search="true" data-select-item-name="toolbar1"
											data-pagination="true" data-sort-name="partno"
											data-sort-order="desc">
											<thead>
												<tr>
													<th data-field="partno" data-sortable="true">Part No</th>
													<th data-field="description" data-sortable="true">Description</th>
													<th data-field="modelno" data-sortable="true">Model No</th>
													<th data-field="customer" data-sortable="true">Available
														QTY</th>
													<th data-field="qtyprovide" data-sortable="true">QTY
														Provide</th>
													<th data-field="action" data-sortable="true">Action</th>
												</tr>
											</thead>

											<tbody>

											</tbody>

										</table>
										<br />
										<div class="form-wizard-buttons">
											<button type="button" class="btn btn-previous">Previous</button>
											<button type="button" class="btn btn-next">Next</button>
										</div>

									</fieldset>
									<!-- Form Step 3 -->

									<!-- Form Step 4 -->
									<fieldset>

										<h4>
											Place Order: <span>Step 4 - 4</span>
										</h4>
										<div style="clear: both;"></div>
										<p>
											You are about to place an order, are you sure you want to
											place the order? You can go back to see if all infomation are
											correct. <br />

										<!-- grap part Number and Quantity Entered upon user click place order button and send them -->
										 <input type="hidden" id="quantityList" name="quantityList" class="form-control" value="" /> 
										 <input type="hidden" id="partNumberList" name="partNumberList" class="form-control" value="" />
										  
										  <div class="form-wizard-buttons">
											<button type="button" class="btn btn-previous">Previous</button>

											<div class="center" align="center">
												<div class="form-group row">
													<div class="col-sm-8">
														<br> <br> <input type="submit"
															value="Place Order"
															class="orderSubmit btn-primary btn-block btn-lg"
															tabindex="9" id="putorder" name="putorder"
															onclick="return confirm('Are you sure you want to place order?');"
															disabled="disabled">
													</div>
												</div>
											</div>

										</div>


									</fieldset>
									<!-- Form Step 4 -->

								</form:form>
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
	<!-- /Scripts -->
</body>
</html>
