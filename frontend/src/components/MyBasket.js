import React from "react";
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

export default function MyBasket() {
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
                <Typography variant="h6">
                    Your basket is currently empty
                </Typography>
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
