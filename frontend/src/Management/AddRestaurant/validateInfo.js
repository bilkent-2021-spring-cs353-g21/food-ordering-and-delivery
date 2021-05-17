export default function validateInfo(values) {
    let errors = {};

    if (!values.name) {
        errors.name = "Name required";
    }

    if (!values.description) {
        errors.description = "Description required";
    }

    if (!values.phoneNumber) {
        errors.phoneNumber = "Phone number required";
    } else if ((values.phoneNumber + "").length != 10) {
        console.log((values.phoneNumber + "").length);
        errors.phoneNumber = "Invalid phone number";
    }

    if (!values.city) {
        errors.city = "City required";
    }

    if (!values.district) {
        errors.district = "District required";
    }

    return errors;
}
