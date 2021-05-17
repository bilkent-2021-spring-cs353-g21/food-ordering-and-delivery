import { useState, useEffect } from "react";
import axios from "axios";
import { setLocalStorage } from "../Service/localStorage";

const useForm = (validate) => {
    const [values, setValues] = useState({
        username: "",
        password: "",
    });

    const [errors, setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        setErrors(validate(values));
        setIsSubmitting(true);
    };

    useEffect(() => {
        async function checkIfUserExists() {
            let params = {
                username: values.username,
                password: values.password,
            };
            axios.defaults.withCredentials = true;
            const qs = require("qs");

            const response = await axios.post(
                "https://52.59.101.158/api/login",
                qs.stringify(params),
                { withCredentials: true }
            );

            if (response.status == 200) {
                setLocalStorage("username", values.username);
                setLocalStorage("isSignedIn", true);
                window.location.href = "/landing";
            }
            console.log(response);
        }

        if (Object.keys(errors).length === 0 && isSubmitting) {
            checkIfUserExists();
            //setErrors({ username: "Incorrect username or password" });
        }
    }, [errors]);

    return { handleSubmit, values, errors, setValues };
};

export default useForm;
