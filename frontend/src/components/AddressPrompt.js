import React from "react";
import {
    Container,
    FormControl,
    InputLabel,
    Select,
    MenuItem,
    makeStyles,
} from "@material-ui/core";

import MyButton from "~/components/MyButton";

const useStyles = makeStyles(() => ({}));

export default function AddressPrompt() {
    let city = localStorage.getItem("city");
    let district = localStorage.getItem("district");

    const confirm = (e) => {
        e.preventDefault();

        city = e.target.city.value;
        district = e.target.district.value;

        localStorage.setItem("city", city);
        localStorage.setItem("district", district);

        localStorage.setItem("isSignedIn", true);
        window.location.href = "/";
    };

    const [state, setState] = React.useState({
        city: "",
        district: "",
    });

    const handleChange = (event) => {
        const name = event.target.name;
        setState({
            ...state,
            [name]: event.target.value,
        });
    };

    const classes = useStyles();

    return (
        <form onSubmit={confirm}>
            <Container
                className={classes.addressPrompt}
                maxWidth="xs"
                style={{
                    position: "absolute",
                    left: "50%",
                    top: "50%",
                    transform: "translate(-50%, -50%)",
                }}
            >
                Please select a city and a district to proceed
                <FormControl className={classes.formControl}>
                    <InputLabel id="city-selector">City</InputLabel>
                    <Select
                        native
                        labelId="city-selector"
                        id="city-selector"
                        value={state.city}
                        onChange={handleChange}
                        inputProps={{
                            name: "city",
                            id: "city-selector",
                        }}
                    >
                        <option aria-label="" value="" />
                        <option>Cankaya</option>
                        <option>Kecioren</option>
                    </Select>
                </FormControl>
                <FormControl className={classes.formControl}>
                    <InputLabel id="district-selector">District</InputLabel>
                    <Select
                        labelId="district-selector"
                        id="district-selector"
                        value={state.district}
                        onChange={handleChange}
                        inputProps={{
                            name: "district",
                            id: "district-selector",
                        }}
                    >
                        <MenuItem value="Cankaya">Cankaya</MenuItem>
                        <MenuItem value={"Kecioren"}>Kecioren</MenuItem>
                        <MenuItem value={"Mamak"}>Mamak</MenuItem>
                    </Select>
                </FormControl>
                <MyButton
                    type="submit"
                    className={classes.myButton}
                    style={{
                        position: "absolute",
                        left: "48%",
                        top: "75%",
                        transform: "translate(-50%, -50%)",
                    }}
                >
                    Confirm
                </MyButton>
            </Container>
        </form>
    );
}
