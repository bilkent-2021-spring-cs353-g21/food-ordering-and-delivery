export default function validateInfo(values) {
    let errors = {};

    if (!values.pNumber){
        errors.pNumber = "Phone No. cannot be empty"; 
      } else if (!phonenumber(values.pNumber))
        errors.pNumber = "Phone No. invalid";

    if (values.password && values.password.length < 6) {
        errors.password = "Password needs to be 6 characters or more";
    }

    if (values.password2 !== values.password) {
        errors.password2 = "Passwords do not match";
    }

    return errors;
}

function phonenumber(inputtxt)
{
  var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
  if(inputtxt.match(phoneno))
     {
	   return true;
	 }
   else
     {
	   alert("Not a valid Phone Number");
	   return false;
     }
}
