import React from "react";
import { withStyles, Button } from "@material-ui/core";

const StyledButton = withStyles({
    root: {
        textTransform: "none",
        background: "#F6B028",
        borderRadius: 3,
        paddingLeft: "1em",
        paddingRight: "1em",
        margin: 6,
        "&:hover": {
            background: "#F2C94C",
        },
        "&:active": {
            background: "#bb0a12",
        },
        "&:focus": {
            outline: "none",
            border: "0.085em solid black",
            boxShadow: "0 0 0 0.085em white",
        },
    },
})(Button);

export default function MyButton(props) {
    return <StyledButton {...props} disableRipple />;
}
