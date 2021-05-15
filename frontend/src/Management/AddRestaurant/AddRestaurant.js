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
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Dialog from "@material-ui/core/Dialog";
import DialogContentText from "@material-ui/core/DialogContentText";

import useForm from "./useForm";
import validate from "./validateInfo.js";
import { OutlinedInput } from "@material-ui/core";

// Styling and icons
import "./AddRestaurant.css";
import DescriptionIcon from "@material-ui/icons/Description";
import { withStyles, makeStyles } from "@material-ui/core/styles";
import PhoneIcon from "@material-ui/icons/Phone";
import AddIcon from "@material-ui/icons/Add";

const profileStyles = makeStyles(() => ({
    addButton: {
        fontSize: "17px",
        backgroundColor: "#0e83f0",
        color: "rgb(0, 0, 0)",
        "&:hover": {
            backgroundColor: "#0867c0",
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

const AddRestaurant = ({ submitForm }) => {
    const [submitResult, setSubmitResult] = useState("");
    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const { handleSubmit, values, errors } = useForm(
        submitForm,
        validate,
        handleClickOpen,
        setSubmitResult
    );
    const classes = profileStyles();
    const [phoneValue, setValues] = React.useState({
        textmask: "(  )    -    ",
    });

    const [cities, setCities] = useState();
    const [districts, setDistricts] = useState();

    const [city, setCity] = useState();
    const [district, setDistrict] = useState();

    useEffect(() => {
        async function getCities() {
            const response = await request(axios.get, "/cities");
            var array = [];

            for (var i in response.data.data) {
                array.push(response.data.data[i]["cityName"]);
            }

            setCities(array);
        }
        getCities();
    });

    useEffect(() => {
        async function getDistricts() {
            // prettier-ignore
            var url = "/districts/" + String(city);
            const response = await request(axios.get, url);
            var array = [];

            for (var i in response.data.data) {
                array.push(response.data.data[i]["districtName"]);
            }
            array.push("");
            setDistricts(array);
            setDistrict("");
        }
        getDistricts();
    }, [city]);

    const handleChange = (event) => {
        setValues({
            ...values,
            [event.target.name]: event.target.value,
        });
    };

    return (
        <div>
            <br />
            <br />
            <div className="add-restaurant-container">
                <form onSubmit={handleSubmit} className="profile" noValidate>
                    <h1 className="add-restaurant-h1">Add Restaurant</h1>
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <div className="add-restaurant-inputs">
                        <ProfileBlackTextField
                            label="Name"
                            variant="outlined"
                            fullWidth
                            id="name-field"
                        />
                        {errors.name && <h2>{errors.name}</h2>}
                    </div>
                    <br />
                    <div className="add-restaurant-inputs">
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
                        />
                        {errors.description && <h2>{errors.description}</h2>}
                    </div>
                    <br />
                    <div className="add-restaurant-inputs">
                        <ProfileBlackOutlinedInput fullWidth>
                            <OutlinedInput
                                fullWidth
                                startAdornment={
                                    <InputAdornment position="start">
                                        <PhoneIcon />
                                    </InputAdornment>
                                }
                                value={phoneValue.textmask}
                                id="phone-number-input"
                                inputComponent={TextMaskCustom}
                                onChange={handleChange}
                            />
                        </ProfileBlackOutlinedInput>
                        {errors.phoneNumber && <h2>{errors.phoneNumber}</h2>}
                    </div>
                    <br />
                    <div className="add-restaurant-inputs">
                        <ProfileBlackOutlinedAutocomplete
                            value={city}
                            onChange={(event, newValue) => {
                                setCity(newValue);
                            }}
                            inputValue={city}
                            onInputChange={(event, newInputValue) => {
                                setCity(newInputValue);
                            }}
                            id="city-field"
                            options={cities}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="City"
                                    variant="outlined"
                                />
                            )}
                        />
                        {errors.city && <h2>{errors.city}</h2>}
                    </div>
                    <br />
                    <div className="add-restaurant-inputs">
                        <ProfileBlackOutlinedAutocomplete
                            id="district-field"
                            options={districts}
                            value={district}
                            inputValue={district}
                            getOptionLabel={(option) => option}
                            fullWidth
                            onChange={(event, newValue) => {
                                setDistrict(newValue);
                            }}
                            onInputChange={(event, newInputValue) => {
                                setDistrict(newInputValue);
                            }}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="District"
                                    variant="outlined"
                                />
                            )}
                        />
                        {errors.district && <h2>{errors.district}</h2>}
                    </div>
                    <br />
                    <br />
                    <br />
                    <br />
                    <Button
                        type="submit"
                        variant="contained"
                        color="secondary"
                        startIcon={<AddIcon />}
                        className={classes.addButton}
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
                        Add
                    </Button>
                </form>
                <Dialog
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">
                        {submitResult}
                    </DialogTitle>
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">
                            Ok
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        </div>
    );
};

AddRestaurant.propTypes = {
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
const ProfileBlackOutlinedAutocomplete = withStyles({
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
})(Autocomplete);

export default AddRestaurant;
