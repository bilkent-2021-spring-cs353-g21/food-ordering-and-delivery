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
            background: "#cc8c16",
        },
        "&:active": {
            background: "#F6B028",
        },
    },
})(Button);

export default function MyButton(props) {
    return <StyledButton {...props} disableRipple />;
}
