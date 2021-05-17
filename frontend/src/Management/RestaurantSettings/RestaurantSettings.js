import React, { useState, useEffect } from "react";
import { TextField, InputAdornment, FormControl } from "@material-ui/core";
import PropTypes from "prop-types";
import Button from "@material-ui/core/Button";
import InputLabel from "@material-ui/core/InputLabel";
import Input from "@material-ui/core/Input";
import MaskedInput from "react-text-mask";
import MenuItem from "@material-ui/core/MenuItem";
import FormHelperText from "@material-ui/core/FormHelperText";
import Select from "@material-ui/core/Select";
import Autocomplete from "@material-ui/lab/Autocomplete";
import axios from "axios";
import request from "../../Service/request";
import useForceUpdate from "use-force-update";
import { getLocalStorage } from "../../Service/localStorage";
import { Controller } from "react-hook-form";
import useForm from "./useForm";
import validate from "./validateInfo.js";
import { OutlinedInput } from "@material-ui/core";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Dialog from "@material-ui/core/Dialog";
import DialogContentText from "@material-ui/core/DialogContentText";

// Styling and icons
import "./RestaurantSettings.css";
import DescriptionIcon from "@material-ui/icons/Description";
import { withStyles, makeStyles } from "@material-ui/core/styles";
import PhoneIcon from "@material-ui/icons/Phone";
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

function TextMaskCustom(props) {
    const { inputRef, ...other } = props;

    return (
        <MaskedInput
            {...other}
            ref={(ref) => {
                inputRef(ref ? ref.inputElement : null);
            }}
            mask={[
                "(",
                /[1-9]/,
                /\d/,
                /\d/,
                ")",
                " ",
                /\d/,
                /\d/,
                /\d/,
                "-",
                /\d/,
                /\d/,
                /\d/,
                /\d/,
            ]}
            placeholderChar={"\u2000"}
            showMask
        />
    );
}
TextMaskCustom.propTypes = {
    inputRef: PropTypes.object.isRequired,
};

const RestaurantSettings = ({ submitForm }) => {
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
    const classes = profileStyles();

    const [rid, setRid] = useState();

    useEffect(() => {
        var tmp = getLocalStorage("rid");
        if (tmp != rid) {
            setRid(tmp);
        }
    });

    useEffect(() => {
        async function getInfo() {
            const response = await request(
                axios.get,
                "/manager/restaurant/" + getLocalStorage("rid")
            );

            setValues({
                name: response.data.data.name,
                description: response.data.data.description,
                phoneNumber: response.data.data.phoneNumber,
                city: response.data.data.address.cityName,
                district: response.data.data.address.districtName,
            });
        }
        getInfo();
    }, [rid]);

    const handlePhoneChange = (event) => {
        var tmp = {};
        for (var i in values) tmp[i] = values[i];
        tmp.phoneNumber = event.target.value;

        setValues(tmp);
    };

    const handleNameChange = (event) => {
        var tmp = {};
        for (var i in values) tmp[i] = values[i];
        tmp.name = event.target.value;

        setValues(tmp);
    };

    const handleDescriptionChange = (event) => {
        var tmp = {};
        for (var i in values) tmp[i] = values[i];
        tmp.description = event.target.value;

        setValues(tmp);
    };

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const [deleteRestaurant, setDeleteRestaurant] = useState(false);

    useEffect(() => {
        async function deleteRestaurantRequest() {
            const response = await request(
                axios.delete,
                "/manager/restaurant/" + getLocalStorage("rid")
            );
            console.log(response);
            console.log(response);
            console.log(response);
            console.log(response);
            console.log(response);
            console.log(response);
            console.log(response);
        }

        if (deleteRestaurant == true) {
            deleteRestaurantRequest();
            window.location.href = "/manager";
        }
    }, [deleteRestaurant]);

    return (
        <div>
            <br />
            <br />
            <div className="restaurant-settings-container">
                <form onSubmit={handleSubmit} className="profile" noValidate>
                    <h1 className="restaurant-settings-h1">
                        {getLocalStorage("rname")}
                    </h1>
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <div className="restaurant-settings-inputs">
                        <ProfileBlackTextField
                            label="Name"
                            variant="outlined"
                            fullWidth
                            id="name-field"
                            value={values.name}
                            onChange={handleNameChange}
                        />
                        {errors.name && <h2>{errors.name}</h2>}
                    </div>
                    <br />
                    <div className="restaurant-settings-inputs">
                        <ProfileBlackTextField
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <DescriptionIcon />
                                    </InputAdornment>
                                ),
                            }}
                            label="Description"
                            multiline
                            rowsMax={4}
                            variant="outlined"
                            fullWidth
                            id="description-field"
                            value={values.description}
                            onChange={handleDescriptionChange}
                        />
                        {errors.description && <h2>{errors.description}</h2>}
                    </div>
                    <br />
                    <div className="restaurant-settings-inputs">
                        <ProfileBlackOutlinedInput fullWidth>
                            <OutlinedInput
                                fullWidth
                                startAdornment={
                                    <InputAdornment position="start">
                                        <PhoneIcon />
                                    </InputAdornment>
                                }
                                value={values.phoneNumber}
                                id="phone-number-input"
                                inputComponent={TextMaskCustom}
                                onChange={handlePhoneChange}
                            />
                        </ProfileBlackOutlinedInput>
                        {errors.phoneNumber && <h2>{errors.phoneNumber}</h2>}
                    </div>
                    <br />
                    <div className="restaurant-settings-inputs">
                        <ProfileBlackOutlinedInput fullWidth>
                            <OutlinedInput
                                fullWidth
                                value={values.city}
                                id="city-field"
                                disabled
                            />
                        </ProfileBlackOutlinedInput>

                        {errors.city && <h2>{errors.city}</h2>}
                    </div>
                    <br />
                    <div className="restaurant-settings-inputs">
                        <ProfileBlackOutlinedInput fullWidth>
                            <OutlinedInput
                                fullWidth
                                value={values.district}
                                id="district-field"
                                disabled
                            />
                        </ProfileBlackOutlinedInput>
                        {errors.district && <h2>{errors.district}</h2>}
                    </div>
                    <br />
                    <Button
                        variant="contained"
                        color="secondary"
                        startIcon={<DeleteIcon />}
                        className={classes.deleteAccountButton}
                        onClick={handleClickOpen}
                    >
                        Delete Restaurant
                    </Button>
                    <Dialog
                        open={open}
                        onClose={handleClose}
                        aria-labelledby="alert-dialog-title"
                        aria-describedby="alert-dialog-description"
                    >
                        <DialogTitle id="alert-dialog-title">
                            {"Are you sure you want to delete this restaurant?"}
                        </DialogTitle>
                        <DialogContent>
                            <DialogContentText id="alert-dialog-description">
                                Your restaurant and all of your menu selections
                                will be deleted. This action cannot be undone.
                            </DialogContentText>
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={handleClose} color="primary">
                                No
                            </Button>
                            <Button
                                onClick={() => {
                                    setDeleteRestaurant(true);
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
                    <br />
                    <Button
                        type="submit"
                        variant="contained"
                        color="secondary"
                        startIcon={<SaveIcon />}
                        className={classes.saveButton}
                        onClick={() => {
                            values.name = document.getElementById(
                                "name-field"
                            ).value;
                            values.description = document.getElementById(
                                "description-field"
                            ).value;

                            var phoneNumber = document.getElementById(
                                "phone-number-input"
                            ).value;

                            phoneNumber = phoneNumber.replace("(", "");
                            phoneNumber = phoneNumber.replace(") ", "");
                            phoneNumber = phoneNumber.replace("-", "");

                            values.phoneNumber = parseInt(phoneNumber);
                            values.city = document.getElementById(
                                "city-field"
                            ).value;
                            values.district = document.getElementById(
                                "district-field"
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

RestaurantSettings.propTypes = {
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

// To customize material ui input
const ProfileBlackOutlinedInput = withStyles({
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
                border: "2px solid",
            },
        },
    },
})(FormControl);

export default RestaurantSettings;
