import React, { useState } from "react";
import { Link, TextField, makeStyles } from "@material-ui/core";
import DateFnsUtils from "@date-io/date-fns";
import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker,
} from "@material-ui/pickers";
import PropTypes from "prop-types";

import validate from "./validateInfo.js";
import useForm from "./useForm";
import { getLocalStorage } from "../Service/localStorage";

// Styling and icons
import "./SignupForm.css";
import { withStyles } from "@material-ui/core/styles";
import AccountCircle from "@material-ui/icons/AccountCircle";
import InputAdornment from "@material-ui/core/InputAdornment";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";
import EmailIcon from "@material-ui/icons/Email";
import { useParams } from "react-router";

const linkStyle = makeStyles({
    link: {
        textDecoration: "none",
        color: "#1c88a8",
        fontWeight: "600",
        fontFamily: "Times New Roman",
    },
});

const FormSignup = ({ submitForm }) => {
    const { userType } = useParams();

    const { handleSubmit, values, errors } = useForm(submitForm, validate);
    const [selectedDate, handleDateChange] = useState("2000-01-01");
    const classes = linkStyle();

    return (
        <div className="signup-form-container">
            <form onSubmit={handleSubmit} className="signup-form" noValidate>
                <h1 className="signup-form-h1">
                    Sign up start starting using the website today!
                </h1>
                <div className="signup-form-inputs">
                    <SignupFormBlackTextField
                        label="Full Name"
                        variant="outlined"
                        fullWidth
                        id="full-name-field"
                    />
                    {errors.fullName && <h2>{errors.fullName}</h2>}
                </div>
                <br />
                <div className="signup-form-inputs">
                    <SignupFormBlackTextField
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
                <div className="signup-form-inputs">
                    <SignupFormBlackTextField
                        InputProps={{
                            startAdornment: (
                                <InputAdornment position="start">
                                    <EmailIcon />
                                </InputAdornment>
                            ),
                        }}
                        label="Email"
                        variant="outlined"
                        fullWidth
                        id="email-field"
                    />
                    {errors.email && <h2>{errors.email}</h2>}
                </div>
                <br />
                <div className="signup-form-inputs">
                    <SignupFormBlackTextField
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
                <div className="signup-form-inputs">
                    <SignupFormBlackTextField
                        InputProps={{
                            endAdornment: (
                                <InputAdornment position="end">
                                    <VisibilityOffIcon />
                                </InputAdornment>
                            ),
                        }}
                        label="Confirm your password"
                        variant="outlined"
                        fullWidth
                        type="password"
                        id="password2-field"
                    />
                    {errors.password2 && <h2>{errors.password2}</h2>}
                </div>
                <br />
                <div className="signup-form-inputs-date-picker">
                    <MuiPickersUtilsProvider utils={DateFnsUtils}>
                        <SignupFormBlackKeyboardDatePicker
                            autoOk
                            variant="inline"
                            inputVariant="outlined"
                            label="Birth Date"
                            format="yyyy-MM-dd"
                            id="birthdate-field"
                            maxDate={new Date()}
                            value={selectedDate}
                            InputAdornmentProps={{ position: "start" }}
                            onChange={(date) => handleDateChange(date)}
                        />
                    </MuiPickersUtilsProvider>
                </div>

                <br />
                <div className="signup-form-inputs"></div>
                <button
                    className="signup-form-input-btn"
                    type="submit"
                    onClick={() => {
                        values.fullName = document.getElementById(
                            "full-name-field"
                        ).value;
                        values.username = document.getElementById(
                            "username-field"
                        ).value;
                        values.email = document.getElementById(
                            "email-field"
                        ).value;
                        values.password = document.getElementById(
                            "password-field"
                        ).value;
                        values.password2 = document.getElementById(
                            "password2-field"
                        ).value;
                        values.birthdate = document.getElementById(
                            "birthdate-field"
                        ).value;
                    }}
                >
                    SIGN UP
                </button>
                <div className={"signup-form-input-login"}>
                    Already have an account? Click{" "}
                    <Link
                        className={classes.link}
                        href={"/" + userType + "/signin"}
                    >
                        here
                    </Link>{" "}
                    to sign in
                </div>
            </form>
        </div>
    );
};

FormSignup.propTypes = {
    submitForm: PropTypes.func.isRequired,
};

// To customize material ui textfield
const SignupFormBlackTextField = withStyles({
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

const SignupFormBlackKeyboardDatePicker = withStyles({
    root: {
        width: "200px",
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
})(KeyboardDatePicker);

export default FormSignup;
