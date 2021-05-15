import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import FormSignup from "./FormSignup";
import FormSuccess from "./FormSuccess";

const signupBackground = makeStyles((theme) => ({
    root: {
        minHeight: "100vh",
        display: "flex",
        backgroundImage: "url(/img/food.png)",
        backgroundRepeat: "no-repeat",
        backgroundPosition: "center",
        backgroundSize: "cover",
    },
}));

const Signup = () => {
    const [isSubmitted, setIsSubmitted] = useState(false);

    function submitForm() {
        setIsSubmitted(true);
    }

    const classes = signupBackground();

    return (
        <div className={classes.root}>
            {!isSubmitted ? (
                <FormSignup submitForm={submitForm} />
            ) : (
                <FormSuccess />
            )}
        </div>
    );
};

export default Signup;
