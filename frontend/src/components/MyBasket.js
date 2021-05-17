import React from "react";
import PropTypes from "prop-types";
import { withStyles, Container, Typography, Box } from "@material-ui/core";
import MyButton from "./MyButton";

const Basket = withStyles({
    root: {
        maxWidth: 300,
        float: "left",
        position: "absolute",
        top: "60%",
    },
})(Container);

export default function MyBasket(props) {
    return (
        <Basket>
            <Box style={{ backgroundColor: "#F2C94C" }}>
                <Typography
                    variant="h6"
                    style={{ display: "flex", justifyContent: "center" }}
                >
                    My Basket
                </Typography>
            </Box>
            <Box style={{ backgroundColor: "#FFFFFF", paddingBottom: 30 }}>
                {!props.basket ? (
                    <Typography>Your basket is currently empty</Typography>
                ) : (
                    <>
                        <Typography>
                            <b>{props.basket.name} </b>
                        </Typography>
                        <Typography>{props.basket.quantity}</Typography>
                        <Typography>{props.basket.price}</Typography>
                    </>
                )}
            </Box>

            <Box
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                    backgroundColor: "#F2C94C",
                    paddingRight: 10,
                    paddingLeft: 10,
                }}
            >
                <Typography variant="h6">Total</Typography>
                <Typography variant="h6">26.50</Typography>
            </Box>
            <MyButton style={{ float: "right" }}>Order now</MyButton>
        </Basket>
    );
}

MyBasket.propTypes = {
    basket: PropTypes.array.isRequired,
};
