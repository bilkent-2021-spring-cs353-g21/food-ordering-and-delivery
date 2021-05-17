import { useState, useEffect } from "react";
import request from "../Service/request";
import axios from "axios";

const useForm = (callback, validate) => {
    const [values, setValues] = useState({
        fullName: "",
        username: "",
        email: "",
        password: "",
        password2: "",
    });

    const [errors, setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        setErrors(validate(values));
        setIsSubmitting(true);
    };

    useEffect(() => {
        async function attemptSignup() {
            const response = await request(axios.post, "/manager/register", {
                birthdate: values.birthdate,
                email: values.email,
                fullName: values.fullName,
                password: values.password,
                username: values.username,
            });

            if (response.status == 200) {
                callback();
            } else if (response.status == -1) {
                // Something really bad happened...
                // Server crash, url wrong, request timeout...
            } else {
                if (response.status == 409) {
                    setErrors({
                        username: "Username or email is already in use",
                    });
                } else {
                    setErrors({ username: "Error" });
                }
            }

            console.log(response);
        }

        if (Object.keys(errors).length === 0 && isSubmitting) {
            attemptSignup();
        }
    }, [errors]);

    return { handleSubmit, values, errors, setValues };
};

export default useForm;
