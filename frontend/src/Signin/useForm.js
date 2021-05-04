import { useState, useEffect } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

import { setLocalStorage } from "../Service/localStorage";
import request from "../Service/request";

const useForm = (callback, validate) => {
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
      let url =
        "/login?username=" + values.username + "&password=" + values.password;
      const response = await request(axios.post, url);

      if (response.status == 200) {
        setLocalStorage("username", values.username);
        setLocalStorage("isSignedIn", true);
        window.location.href = "/manager";
      } else if (response.status == -1) {
        // Something really bad happened...
        // Server crash, url wrong, request timeout...
      } else {
        setErrors({ username: "Incorrect username or password" });
      }

      console.log(response);
    }

    if (Object.keys(errors).length === 0 && isSubmitting) {
      checkIfUserExists();
    }
  }, [errors]);

  return { handleSubmit, values, errors };
};

export default useForm;
