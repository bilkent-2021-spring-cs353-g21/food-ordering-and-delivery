import React from "react";
import PropTypes from "prop-types";
import { withStyles, Typography, Box, Divider } from "@material-ui/core";
import MyButton from "~components/MyButton";

const CustomBox = withStyles({
    root: {
        backgroundColor: "#FFFFFF",
    },
})(Box);

const popAdd = (e) => {
    e.preventDefault();
};

export default function MealBox(props) {
    return (
        <CustomBox>
            <MyButton onClick={popAdd} style={{ float: "right" }}>
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
                <b>Chicken Burrito</b>
            </Typography>

            <Typography>
                Marineted chicken pieces inside tortilla and tomato and potato
                and citato and bla bla
            </Typography>
            <Divider />
        </CustomBox>
    );
}

MealBox.propTypes = {
    meal: PropTypes.object.isRequired,
};
