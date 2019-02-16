/**
 * Purpose: Interact with the html pages and create communication between server
 * and client side 
 * Author : Fenya Tlala 
 * Client: Velaphanda
 * Created Date:21-01-2018
 * Last Date Modified:26-10-2018
 */

//Order available head office stock-->
            

"use strict";
function scroll_to_class(element_class, removed_height) {
	var scroll_to = $(element_class).offset().top - removed_height;
	if($(window).scrollTop() != scroll_to) {
		$('.form-wizard').stop().animate({scrollTop: scroll_to}, 0);
	}
}

function bar_progress(progress_line_object, direction) {
	var number_of_steps = progress_line_object.data('number-of-steps');
	var now_value = progress_line_object.data('now-value');
	var new_value = 0;
	if(direction == 'right') {
		new_value = now_value + ( 100 / number_of_steps );
	}
	else if(direction == 'left') {
		new_value = now_value - ( 100 / number_of_steps );
	}
	progress_line_object.attr('style', 'width: ' + new_value + '%;').data('now-value', new_value);
}

//validate customer and technician

jQuery(document).ready(function() {
    
    /*
        Form
    */
    $('.form-wizard fieldset:first').fadeIn('slow');
    
    $('.form-wizard .required').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    // next step
    $('.form-wizard .btn-next').on('click', function() {
    	var parent_fieldset = $(this).parents('fieldset');
    	var next_step = true;
    	// navigation steps / progress steps
    	var current_active_step = $(this).parents('.form-wizard').find('.form-wizard-step.active');
    	var progress_line = $(this).parents('.form-wizard').find('.form-wizard-progress-line');
    	
    	// fields validation
    	parent_fieldset.find('.required').each(function() {
    		if( $(this).val() == "" ) {
    			$(this).addClass('input-error');
    			next_step = false;
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	// fields validation
    	if(current_active_step.hasClass("HO-stock")){
        if($("#stockForOrder tbody tr").length > 0){
        	//alert("Please select at least one item to order");
          	//return;
           }     
        }   
        
    	if( next_step ) {
    		parent_fieldset.fadeOut(400, function() {
    			// change icons
    			current_active_step.removeClass('active').addClass('activated').next().addClass('active');
    			// progress bar
    			bar_progress(progress_line, 'right');
          
          // show next step
	    		$(this).next().fadeIn();
	    		// scroll window to beginning of the form
    			scroll_to_class( $('.form-wizard'), 20 );
	    	});
    	}
    	
    });
    
    // previous step
    $('.form-wizard .btn-previous').on('click', function() {
    	// navigation steps / progress steps
    	var current_active_step = $(this).parents('.form-wizard').find('.form-wizard-step.active');
    	var progress_line = $(this).parents('.form-wizard').find('.form-wizard-progress-line');
    	
    	$(this).parents('fieldset').fadeOut(400, function() {
    		// change icons
    		current_active_step.removeClass('active').prev().removeClass('activated').addClass('active');
    		// progress bar
    		bar_progress(progress_line, 'left');
    		// show previous step
    		$(this).prev().fadeIn();
    		// scroll window to beginning of the form
			scroll_to_class( $('.form-wizard'), 20 );
    	});
    });
    
    // submit
    $('.form-wizard').on('submit', function(e) {
    	
    	// fields validation
    	$(this).find('.required').each(function() {
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	// fields validation
    	
    });
    
    
});


//Compare available quantity with entered quantity
function compareQuantity(element, availableQuantity) {

	if (availableQuantity > element.value) {
		console.log("True,", element.value + " is less than "
				+ availableQuantity);
		console.log("Place an Order");
	}
	if (element.value == '' || element.value == 0) {
		alert("Part is out of stock");
		element.value = null;
	} else if (element.value == '' || element.value == '') {
		alert("Quantity can not be empty or cant not be zero. Please enter quantity which is less than available quantity");
		element.value = null;
		console.log(element.value);
	} else if (availableQuantity < element.value) {
		alert("Your order quantity can not be greater than available quantity. Please enter less quantity");
		element.value = null;
		console.log("False,", availableQuantity + " is small than "
				+ element.value);
		console.log("You can not place an order, enter less quantity");
		console.log("Enter value between 1 till " + element.value
				+ " not more than " + availableQuantity);
	}
}
//End Compare available quantity with entered quantity	

/* --Stock type Selection-- */
function CheckStockType(val) {
	var element = document.getElementById('Site');
	if (val == 'select stock type' || val == 'Site')
		element.style.display = 'block';
	else
		element.style.display = 'none';
	var element = document.getElementById('Boot');
	if (val == 'select stock type' || val == 'Boot')
		element.style.display = 'block';
	else
		element.style.display = 'none';

}/* --Stock type Selection-- */

//Order available head office stock-->
    var row;
    var partNumberList = [];
    var quantityList = [];
    //move selected line items to table 2
    $('#stockForOrder').on('click', '.addLineItem', function() {
	   
       var quantity;
       row = $(this).closest("tr").clone(); 
       quantity = $(this).closest('tr').find('td:eq(4)').find('input').val();
       console.log("Check the grapped quantity on table of Selected Line Items to Order : ",quantity);
      
       if(quantity == '' || quantity == 0){
           alert("Quantity can not be zero.\n Please enter quantity which is less than available quantity"); 
       }      
       if (quantity > 0){
            var items = [];
            row = $(this).closest("tr").clone();
            var partNumber = $(this).closest('tr').find('td:eq(0)').text();
            var quantity = $(this).closest('tr').find('td:eq(4)').find('input').val();
            document.getElementById("quantityList").value = quantityList;
            document.getElementById("partNumberList").value = partNumberList;
                                    
            items.push(row);
            row.appendTo($("#toOrder"));
            //debugger;
            $(this).closest('tr').remove();
            $('input[type="button"]', row).removeClass('AddNew').addClass('RemoveRow').val('Remove');
        }
     });
    //remove selected line items from table 1 to table 2
    $('#toOrder').on('click', '.RemoveRow', function(){
    	 //debugger;
	    row = $(this).closest("tr").clone();
	    row.find("input[name=quantity]").val("");
      	row.appendTo($("#stockForOrder"));
        $(this).closest('tr').remove();        
        $('input[type="button"]', row).removeClass('RemoveRow').addClass('addLineItem').val('Add');
    });
     
    //send selected items when user clicks submit button
     $('#putorder').on('click', function(){
           var row;
           $('#toOrder tr').each(function(row, tr){
             partNumberList[row]=[$(tr).find('td:eq(0)').text()];
             quantityList[row]=[$(this).closest('tr').find('td:eq(4)').find('input').val()];
      }); 
      partNumberList.shift();
      quantityList.shift();
      document.getElementById("quantityList").value = quantityList;
      document.getElementById("partNumberList").value = partNumberList;
    });
     
	 $(document).ready(function(){
    	    $('.orderSubmit').attr('disabled',true);
    	    
    	    $('input[name$=quantity]').change(function(){
    	        if($(this).val().length !=0){
    	            $('.orderSubmit').attr('disabled', false);
    	            console.log("Something Entered");
    	        }
    	        else
    	        {
    	            $('.orderSubmit').attr('disabled', true);
    	            console.log("Nothing Entered");
    	        }
    	    })
    });
	
	//disable next button if quantity is empty
	 $(document).ready(function(){
    	    $('.goToNextPage').attr('disabled',true);
    	    
    	    $('input[name$=quantity]').change(function(){
    	        if($(this).val().length !=0){
    	            $('.goToNextPage').attr('disabled', false);
    	            console.log("Something Entered");
    	        }
    	        else
    	        {
    	            $('.goToNextPage').attr('disabled', true);
    	            console.log("Nothing Entered");
    	        }
    	    })
    });
    