<%@include file="templates/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Add Client | Velaphanda Trading & Projects</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<c:import url="templates/stylesheetlib.jsp"></c:import>

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
		<c:if test="${not empty retMessage }">
			<div class="alert alert-info" role="alert">
				<c:out value="${ retMessage}">
				</c:out>
			</div>
		</c:if>
		<c:if test="${not empty retErrorMessage }">
			<div class="alert alert-danger" role="alert">
				<c:out value="${ retErrorMessage}">
				</c:out>
			</div>
		</c:if>

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading" align="center">Add Customer</div>
					<div class="panel-body">

						<form:form class="well form-horizontal" method="post"
							action="saveClient" id="addClient" modelAttribute="saveClient">
							<fieldset>
								<legend>
									<b style="font-size: 15px;">Customer Details </b>
								</legend>
								
								<!-- Customer Action -->
								<input type="hidden" id="customerAction" name="customerAction" class="form-control" value="Create">
														

								<!--First column Customer Fields-->
								<div class="col-sm-6">
								
									<!-- Text input Client Name-->
									<div class="form-group">
										<label class="col-md-3 control-label">Customer</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-user"></i></span> <input
													name="customerName" id="clientName"
													placeholder="Customer Name" class="form-control"
													type="text">
											</div>
										</div>
									</div>

									<!-- Text input Tellphone Number-->
									<div class="form-group">
										<label class="col-md-3 control-label">Telephone No</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-earphone"></i></span> <input
													name="telephoneNumber" id="telephoneNumber"
													placeholder="Telephone Number" maxlength="10"
													class="form-control" type="text"
													onkeypress="return isNumber(event)">
											</div>
										</div>
									</div>

									<!-- Text input Fax Number-->
									<div class="form-group">
										<label class="col-md-3 control-label">Fax Number</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-earphone"></i></span> <input
													name="faxNumber" id="faxNumber" placeholder="Fax Number"
													class="form-control" type="text" maxlength="10"
													onkeypress="return isNumber(event)">
											</div>
										</div>
									</div>


									<!-- Text input Email
								<div class="form-group">
									<label class="col-md-3 control-label">Company Email</label>
									<div class="col-md-8 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-envelope"></i></span> <input
												name="email" id="email"
												placeholder="Company Email Address" class="form-control"
												type="text">
										</div>
									</div>
								</div> -->

									<!-- Text input Street Number-->
									<div class="form-group">
										<label class="col-md-3 control-label">Street No</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-home"></i></span> <input
													name="streetNumber" id="streetNumber"
													placeholder="Street No" class="form-control" type="text">
											</div>
										</div>
									</div>
								</div>
								<!-- / F Customer Fields -->

								<!--Second column Customer Fields-->
								<div class="col-sm-6">

									<!-- Text input Street Name-->
									<div class="form-group">
										<label class="col-md-3 control-label">Street Name</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-home"></i></span> <input
													name="streetName" id="streetName" placeholder="Street Name"
													class="form-control" type="text">
											</div>
										</div>
									</div>
									<!-- Text input City or Town-->
									<div class="form-group">
										<label class="col-md-3 control-label">City/Town</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-home"></i></span> <input
													name="city_town" id="city_town" placeholder="City / Town"
													class="form-control" type="text">
											</div>
										</div>
									</div>
									<!-- Select type Province-->
									<div class="form-group">
										<label class="col-md-3 control-label">Province</label>
										<div class="col-md-8 selectContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-list"></i></span> <select
													name="province" id="province"
													class="form-control selectpicker">
													<option value="">Select Province</option>
													<option value="Gauteng">Gauteng</option>
													<option value="Limpopo">Limpopo</option>
													<option value="Nort West">North West</option>
													<option value="Free State">Free State</option>
													<option value="Mpumalanga">Mpumalanga</option>
													<option value="KwaZulu Natal">KwaZulu Natal</option>
													<option value="Northern Cape">Northern Cape</option>
													<option value="Eastern Cape">Eastern Cape</option>
													<option value="Mpumalanga">Western Cape</option>
												</select>
											</div>
										</div>
									</div>

									<!-- Text input Area Code-->
									<div class="form-group">
										<label class="col-md-3 control-label">Area Code</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-home"></i></span> <input maxlength="4"
													name="zipcode" id="zipcode" placeholder="Area Code"
													class="form-control" type="text"
													onkeypress="return isNumber(event)">
											</div>
										</div>
									</div>
								</div>

							</fieldset>
							<!--/Second column Customer Fields-->



							<fieldset>
								<legend>
									<b style="font-size: 15px;">Contact
										Person 1 </b>
								</legend>

								<!-- Contact Person 1 -->
								<div class="col-sm-6">
									<!-- Text input Contact Person First Name-->
									<div class="form-group">
										<label class="col-md-3 control-label">First Name</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-user"></i></span> <input id="firstName"
													name="firstName" placeholder="First Name"
													class="form-control" type="text">
											</div>
										</div>
									</div>
									<!-- Text input Contact Person  Last Name-->
									<div class="form-group">
										<label class="col-md-3 control-label">Last Name</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-user"></i></span> <input id="lastName"
													name="lastName" placeholder="Last Name"
													class="form-control" type="text">
											</div>
										</div>
									</div>
									<!-- Text input Contact Person Cellphone Number-->
									<div class="form-group">
										<label class="col-md-3 control-label">Cellphone No</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-earphone"></i></span> <input
													id="contactCellNumber" name="contactCellNumber"
													placeholder="Cellphone No" class="form-control"
													maxlength="10" type="text"
													onkeypress="return isNumber(event)">
											</div>
										</div>
									</div>
								</div>

								<div class="col-sm-6">

									<!-- Text input Contact Person Tellphone Number-->
									<div class="form-group">
										<label class="col-md-3 control-label">Tellphone No</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-earphone"></i></span> <input
													id="contactTelephoneNumber" name="contactTelephoneNumber"
													placeholder="Telephone No" class="form-control"
													maxlength="10" type="text"
													onkeypress="return isNumber(event)">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Email</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-envelope"></i></span> <input
													id="contactEmail" name="contactEmail"
													placeholder="Email Address" class="form-control"
													type="email">
											</div>
										</div>
									</div>


								</div>
								<!-- /Contact Person 1 -->

							</fieldset>


							<fieldset>
								<legend>
									<b class="optionalFields" style="color: red; font-size: 15px;">Contact
										Person 2 (Optional Fields)</b>
								</legend>

								<!-- Contact Person 2 -->
								<div class="col-sm-6">

									<!-- Text input Contact Person 2 First Name-->
									<div class="form-group">
										<label class="col-md-3 control-label">First Name</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-user"></i></span> <input
													id="firstName1" name="firstName1" placeholder="First Name"
													class="form-control" type="text">
											</div>
										</div>
									</div>
									<!-- Text input Contact Person 2 Last Name-->
									<div class="form-group">
										<label class="col-md-3 control-label">Last Name</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-user"></i></span> <input id="lastName1"
													name="lastName1" placeholder="Last Name"
													class="form-control" type="text">
											</div>
										</div>
									</div>
									<!-- Text input Contact Person 2 Cellphone Number-->
									<div class="form-group">
										<label class="col-md-3 control-label">Cellphone No</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-earphone"></i></span> <input
													id="contactCellNumber1" name="contactCellNumber1"
													placeholder="Cellphone No" class="form-control"
													maxlength="10" type="text"
													onkeypress="return isNumber(event)">
											</div>
										</div>
									</div>
								</div>

								<div class="col-sm-6">

									<!-- Text input Contact Person 2 Tellphone Number-->
									<div class="form-group">
										<label class="col-md-3 control-label">Tellphone No</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-earphone"></i></span> <input
													id="contactTelephoneNumber1" name="contactTelephoneNumber1"
													placeholder="Telephone No" class="form-control"
													maxlength="10" type="text"
													onkeypress="return isNumber(event)">
											</div>
										</div>
									</div>

									<!-- Text input Contact Person 2 Email-->
									<div class="form-group">
										<label class="col-md-3 control-label">Email</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-envelope"></i></span> <input
													id="contactEmail1" name="contactEmail1"
													placeholder="Email Address" class="form-control"
													type="email">
											</div>
										</div>
									</div>
								</div>
								<!--/Contact Person 2 -->

							</fieldset>

							<div class="form-group row">
								<div class="col-sm-offset-2 col-sm-8">
									<br> <br> <input type="submit" value="Add Customer"
										class="btn btn-primary btn-block btn-lg" tabindex="9"
										id="addClnt">
								</div>
							</div>
						</form:form>

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