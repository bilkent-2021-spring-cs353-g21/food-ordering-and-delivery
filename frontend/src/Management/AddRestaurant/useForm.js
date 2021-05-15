import { useState, useEffect } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

import request from "../../Service/request";

const useForm = (callback, validate, handleClickOpen, setSubmitResult) => {
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
        async function attemptAddRestaurant() {
            const response = await request(axios.put, "/manager/restaurant", {
                address: {
                    cityName: values.city,
                    districtName: values.district,
                },
                description: values.description,
                name: values.name,
                phoneNumber: values.phoneNumber,
            });

            var message;
            if (response.status == 200) {
                message = "Resturant has been added successfully.";
            } else {
                message = "Request failed.";
            }

            setSubmitResult(message);
            handleClickOpen();
        }

        if (Object.keys(errors).length === 0 && isSubmitting) {
            attemptAddRestaurant();
        }
    }, [errors]);

    return { handleSubmit, values, errors };
};

export default useForm;
