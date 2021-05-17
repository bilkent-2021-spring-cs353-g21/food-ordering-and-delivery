import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import FormSignin from "./FormSignin";

const signinBackground = makeStyles(() => ({
    root: {
        minHeight: "100vh",
        display: "flex",
        backgroundImage: "url(/img/signin.jpg)",
        backgroundRepeat: "no-repeat",
        backgroundPosition: "center",
        backgroundSize: "cover",
    },
}));

const Signin = () => {
    const classes = signinBackground();

    return (
        <div className={classes.root}>
            <FormSignin></FormSignin>
        </div>
    );
};
export default Signin;
