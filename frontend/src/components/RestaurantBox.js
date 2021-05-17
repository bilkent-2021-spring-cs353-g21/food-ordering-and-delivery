import React from "react";
import PropTypes from "prop-types";
import { Typography, Box, Container } from "@material-ui/core";
import MealBox from "./MealBox";

export default function RestaurantBox(props) {
    return (
        <Container
            maxWidth="sm"
            style={{
                marginTop: 40,
                position: "relative",
                left: "10%",
            }}
        >
            <Box
                style={{
                    background: "#F2C94C",
                    marginTop: 20,
                }}
            >
                <Typography
                    variant="h5"
                    style={{ display: "flex", justifyContent: "center" }}
                >
                    {props.type}
                </Typography>
            </Box>
            {(() => {
                if (props.data) {
                    return props.data.map((meal, i) => {
                        return (
                            <MealBox
                                meal={meal}
                                key={i}
                                updateBasket={props.updateBasket}
                            />
                        );
                    });
                }
            })()}
        </Container>
    );
}

RestaurantBox.propTypes = {
    data: PropTypes.array.isRequired,
    type: PropTypes.string.isRequired,
    updateBasket: PropTypes.func,
};
