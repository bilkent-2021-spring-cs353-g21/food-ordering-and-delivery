import { useState, useEffect } from "react";
import axios from "axios";
import request from "../../Service/request";
import { getLocalStorage } from "../../Service/localStorage";

const useForm = (callback, validate, handleClickOpen_, setSubmitResult) => {
    const [values, setValues] = useState({
        name: "",
        description: "",
        phoneNumber: "",
        city: "",
        district: "",
    });
    const [errors, setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        setErrors(validate(values));
        setIsSubmitting(true);
    };

    useEffect(() => {
        async function saveRestaurantSettings() {
            const response = await request(
                axios.patch,
                "/manager/restaurant/" + getLocalStorage("rid"),
                {
                    description: values.description,
                    name: values.name,
                    phoneNumber: values.phoneNumber,
                }
            );

            setSubmitResult(response.data.message);
            handleClickOpen_();
        }
        if (Object.keys(errors).length === 0 && isSubmitting) {
            saveRestaurantSettings();
        }
    }, [errors]);

    return { handleSubmit, values, setValues, errors };
};

export default useForm;
