import { useState, useEffect } from "react";
import axios from "axios";
import request from "../../Service/request";

const useForm = (callback, validate, handleClickOpen_, setSubmitResult) => {
    const [values, setValues] = useState({
        fullName: "",
        username: "",
        email: "",
        birthdate: "",
        password: "",
        password2: "",
        pNumber: "",
    });

    const [errors, setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        setErrors(validate(values));
        setIsSubmitting(true);
    };

    useEffect(() => {
        async function attemptChangeProfile() {
            const response = await request(axios.patch, "/deliverer/account", {
                birthdate: values.birthdate,
                fullName: values.fullName,
                phoneNumber: values.pNumber,
            });

            setSubmitResult(response.data.message);
            handleClickOpen_();
        }

        if (Object.keys(errors).length === 0 && isSubmitting) {
            attemptChangeProfile();
        }
    }, [errors]);

    return { handleSubmit, values, setValues, errors };
};

export default useForm;
