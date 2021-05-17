import React from "react";
import { Link, TextField, InputAdornment } from "@material-ui/core";
import { useHistory } from "react-router-dom";
import PropTypes from "prop-types";

import useForm from "./useForm";
import validate from "./validateInfo.js";
import { getLocalStorage } from "../Service/localStorage";

// Styling and icons
import "./SigninForm.css";
import AccountCircle from "@material-ui/icons/AccountCircle";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";
import { withStyles, makeStyles } from "@material-ui/core/styles";

const linkStyle = makeStyles({
    link: {
        textDecoration: "none",
        color: "#1c88a8",
        fontWeight: "600",
        fontFamily: "Times New Roman",
    },
});

const FormSignin = () => {
    const isSignedIn = getLocalStorage("isSignedIn");
    const history = useHistory();
    if (isSignedIn) {
        history.replace("/manager");
        window.location.href = "/manager";
    }

    const { handleSubmit, values, errors } = useForm(validate);
    const classes = linkStyle();

    return (
        <div className="signin-form-container">
            <form onSubmit={handleSubmit} className="signin-form" noValidate>
                <h1 className="signin-form-h1">ORDER.COM</h1>
                <br />
                <div className="signin-form-inputs">
                    <SigninFormBlackTextField
                        InputProps={{
                            startAdornment: (
                                <InputAdornment position="start">
                                    <AccountCircle />
                                </InputAdornment>
                            ),
                        }}
                        label="Username"
                        variant="outlined"
                        fullWidth
                        id="username-field"
                    />
                    {errors.username && <h2>{errors.username}</h2>}
                </div>
                <br />
                <div className="signin-form-inputs">
                    <SigninFormBlackTextField
                        InputProps={{
                            endAdornment: (
                                <InputAdornment position="end">
                                    <VisibilityOffIcon />
                                </InputAdornment>
                            ),
                        }}
                        label="Password"
                        variant="outlined"
                        fullWidth
                        type="password"
                        id="password-field"
                    />
                    {errors.password && <h2>{errors.password}</h2>}
                </div>
                <br />
                <div className="signin-form-inputs"></div>
                <button
                    className="signin-form-input-btn"
                    type="submit"
                    onClick={() => {
                        values.username = document.getElementById(
                            "username-field"
                        ).value;
                        values.password = document.getElementById(
                            "password-field"
                        ).value;
                    }}
                >
                    SIGN IN
                </button>
                <div className="signin-form-input-login">
                    Dont have an account? Click{" "}
                    <Link className={classes.link} href="/signup">
                        here
                    </Link>{" "}
                    to sign up
                </div>
            </form>
        </div>
    );
};

FormSignin.propTypes = {
    submitForm: PropTypes.object.isRequired,
};

// To customize material ui textfield
const SigninFormBlackTextField = withStyles({
    root: {
        "& label.Mui-focused": {
            color: "black",
        },
        "& .MuiInput-underline:after": {
            borderBottomColor: "black",
        },
        "& .MuiOutlinedInput-root": {
            "& fieldset": {
                borderColor: "black",
                border: "2px solid",
            },
            "&:hover fieldset": {
                borderColor: "black",
                border: "3px solid",
            },
            "&.Mui-focused fieldset": {
                borderColor: "black",
            },
        },
    },
})(TextField);

export default FormSignin;
