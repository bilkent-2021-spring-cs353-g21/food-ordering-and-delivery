import React, { useState, useEffect } from "react";
import { TextField, InputAdornment } from "@material-ui/core";
import PropTypes from "prop-types";
import DateFnsUtils from "@date-io/date-fns";
import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker,
} from "@material-ui/pickers";
import Button from "@material-ui/core/Button";
import axios from "axios";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Dialog from "@material-ui/core/Dialog";
import DialogContentText from "@material-ui/core/DialogContentText";

import request from "../../Service/request";
import useForm from "./useForm";
import validate from "./validateInfo.js";
import { setLocalStorage, getLocalStorage } from "../../Service/localStorage";

// Styling and icons
import "./Profile.css";
import AccountCircle from "@material-ui/icons/AccountCircle";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";
import EmailIcon from "@material-ui/icons/Email";
import { withStyles, makeStyles } from "@material-ui/core/styles";
import SaveIcon from "@material-ui/icons/Save";
import DeleteIcon from "@material-ui/icons/Delete";

const profileStyles = makeStyles(() => ({
    saveButton: {
        fontSize: "17px",
        backgroundColor: "rgb(13, 165, 13)",
        color: "rgb(0, 0, 0)",
        "&:hover": {
            backgroundColor: "rgb(0, 151, 0)",
            color: "#rgb(0, 0, 0)",
        },
    },
    deleteAccountButton: {
        fontSize: "17px",
        backgroundColor: "#f00e0e",
        color: "rgb(0, 0, 0)",
        "&:hover": {
            backgroundColor: "#f00e0e",
            color: "#rgb(0, 0, 0)",
        },
    },
}));

const Profile = ({ submitForm }) => {
    const classes = profileStyles();
    const [loading, setLoading] = useState(true);

    // For form submit
    const [open_, setOpen_] = React.useState(false);
    const [submitResult, setSubmitResult] = useState("");

    const handleClickOpen_ = () => {
        setOpen_(true);
    };

    const handleClose_ = () => {
        setOpen_(false);
    };

    const { handleSubmit, values, setValues, errors } = useForm(
        submitForm,
        validate,
        handleClickOpen_,
        setSubmitResult
    );

    const [selectedDate, handleDateChange] = useState();

    const handleChange = (event) => {
        var tmp = {};
        for (var i in values) tmp[i] = values[i];
        tmp.fullName = event.target.value;

        setValues(tmp);
    };

    useEffect(() => {
        async function getInfo() {
            const response = await request(axios.get, "/manager/account");

            handleDateChange(response.data.data.birthdate);
            setValues({
                fullName: response.data.data.fullName,
                username: response.data.data.username,
                email: response.data.data.email,
                password: response.data.data.password,
            });

            setLoading(false);
        }
        getInfo();
    }, []);

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const [deleteAccount, setDeleteAccount] = useState(false);

    useEffect(() => {
        async function deleteAccountRequest() {
            const response = await request(axios.delete, "/manager/account");
        }

        if (deleteAccount == true) {
            deleteAccountRequest();
            setLocalStorage("isSignedIn", false);
            window.location.href = "/signin";
        }
    }, [deleteAccount]);

    return (
        <div>
            <br />
            <br />
            <div className="profile-container ">
                <form onSubmit={handleSubmit} className="profile" noValidate>
                    <h1 className="signup-form-h1">Profile</h1>
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <div className="profile-inputs">
                        <ProfileBlackTextField
                            autoFocus
                            label="Full Name"
                            variant="outlined"
                            fullWidth
                            id="full-name-field"
                            value={values.fullName}
                            onChange={handleChange}
                        />
                        {errors.fullName && <h2>{errors.fullName}</h2>}
                    </div>
                    <br />
                    <div className="profile-inputs">
                        <ProfileBlackTextField
                            disabled
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <AccountCircle />
                                    </InputAdornment>
                                ),
                            }}
                            value={values.username}
                            label="Username"
                            variant="outlined"
                            fullWidth
                            id="username-field"
                        />
                    </div>
                    <br />
                    <div className="profile-inputs">
                        <ProfileBlackTextField
                            disabled
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <EmailIcon />
                                    </InputAdornment>
                                ),
                            }}
                            label="Email"
                            variant="outlined"
                            value={values.email}
                            fullWidth
                            id="email-field"
                        />
                    </div>
                    <br />
                    <div className="profile-inputs">
                        <ProfileBlackTextField
                            InputProps={{
                                endAdornment: (
                                    <InputAdornment position="end">
                                        <VisibilityOffIcon />
                                    </InputAdornment>
                                ),
                            }}
                            label="New Password"
                            variant="outlined"
                            fullWidth
                            type="password"
                            id="password-field"
                        />
                        {errors.password && <h2>{errors.password}</h2>}
                    </div>
                    <br />
                    <div className="profile-inputs">
                        <ProfileBlackTextField
                            InputProps={{
                                endAdornment: (
                                    <InputAdornment position="end">
                                        <VisibilityOffIcon />
                                    </InputAdornment>
                                ),
                            }}
                            label="Confirm New Password"
                            variant="outlined"
                            fullWidth
                            type="password"
                            id="password2-field"
                        />
                        {errors.password2 && <h2>{errors.password2}</h2>}
                    </div>
                    <br />
                    {loading ? (
                        <br></br>
                    ) : (
                        <MuiPickersUtilsProvider utils={DateFnsUtils}>
                            <ProfileBlackKeyboardDatePicker
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
                    )}

                    <br />
                    <div className="profile-inputs"></div>
                    <Button
                        variant="contained"
                        color="secondary"
                        startIcon={<DeleteIcon />}
                        className={classes.deleteAccountButton}
                        onClick={handleClickOpen}
                    >
                        Delete Account
                    </Button>
                    <Dialog
                        open={open}
                        onClose={handleClose}
                        aria-labelledby="alert-dialog-title"
                        aria-describedby="alert-dialog-description"
                    >
                        <DialogTitle id="alert-dialog-title">
                            {"Are you sure you want to delete your account?"}
                        </DialogTitle>
                        <DialogContent>
                            <DialogContentText id="alert-dialog-description">
                                Your account and all of the associated
                                restaurants will be deleted. This action cannot
                                be undone.
                            </DialogContentText>
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={handleClose} color="primary">
                                No
                            </Button>
                            <Button
                                onClick={() => {
                                    setDeleteAccount(true);
                                }}
                                color="primary"
                                autoFocus
                            >
                                Yes
                            </Button>
                        </DialogActions>
                    </Dialog>
                    <br />
                    <br />
                    <br />
                    <Button
                        type="submit"
                        variant="contained"
                        color="secondary"
                        startIcon={<SaveIcon />}
                        className={classes.saveButton}
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
                        Save
                    </Button>
                </form>
                <Dialog
                    open={open_}
                    onClose={handleClose_}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">
                        {submitResult}
                    </DialogTitle>
                    <DialogActions>
                        <Button onClick={handleClose_} color="primary">
                            Ok
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        </div>
    );
};

Profile.propTypes = {
    submitForm: PropTypes.object.isRequired,
};

// To customize material ui textfield
const ProfileBlackTextField = withStyles({
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

const ProfileBlackKeyboardDatePicker = withStyles({
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

export default Profile;
