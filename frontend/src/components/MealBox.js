import React from "react";
import PropTypes from "prop-types";
import { withStyles, Typography, Box, Divider } from "@material-ui/core";
import MyButton from "~components/MyButton";
import { addToBasket } from "~/Service/service";

const CustomBox = withStyles({
    root: {
        backgroundColor: "#FFFFFF",
        maxWidth: 600,
        justifyContent: "center",
    },
})(Box);

export default function MealBox(props) {
    const add = (e) => {
        e.preventDefault();

        const isSignedIn = localStorage.getItem("isSignedIn");

        const data = {
            name: props.meal.name,
            rid: props.meal.rid,
            quantity: 1,
        };

        addToBasket(data)
            .then(() => {
                props.updateBasket();
            })
            .catch((error) => {
                alert(error);
            });
    };

    return (
        <CustomBox>
            <MyButton onClick={add} style={{ float: "right" }}>
                Add
            </MyButton>
            <Typography
                variant="h6"
                style={{
                    float: "right",
                    paddingRight: 100,
                    paddingTop: 10,
                }}
            >
                {props.meal && props.meal.price}
            </Typography>
            <Typography variant="h6">
                <b>{props.meal && props.meal.name}</b>
            </Typography>

            <Typography>
                {props.meal &&
                    (props.meal.description
                        ? props.meal.description
                        : "No Description")}{" "}
            </Typography>
            <Divider />
        </CustomBox>
    );
}

MealBox.propTypes = {
    meal: PropTypes.object.isRequired,
    updateBasket: PropTypes.func,
};
