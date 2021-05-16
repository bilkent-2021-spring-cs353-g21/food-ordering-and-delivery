export default function validateInfo(values) {
    let errors = {};

    if (values.password && values.password.length < 6) {
        errors.password = "Password needs to be 6 characters or more";
    }

    if (values.password2 !== values.password) {
        errors.password2 = "Passwords do not match";
    }

    return errors;
}
