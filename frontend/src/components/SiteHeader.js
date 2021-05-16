import React from "react";
import {
    makeStyles,
    Typography,
    IconButton,
    InputBase,
    AppBar,
    Toolbar,
    fade,
} from "@material-ui/core";

import SearchIcon from "@material-ui/icons/Search";

const useStyles = makeStyles((theme) => ({
    logo: {
        marginLeft: 30,
        fontSize: 30,
    },
    search: {
        position: "relative",
        float: "right",
        borderRadius: theme.shape.borderRadius,
        backgroundColor: fade(theme.palette.common.white, 0.15),
        "&:hover": {
            backgroundColor: fade(theme.palette.common.white, 0.25),
        },
        marginLeft: 0,
        width: "100%",
        [theme.breakpoints.up("sm")]: {
            marginLeft: theme.spacing(1),
            width: "auto",
        },
    },
    searchIcon: {
        padding: theme.spacing(0, 2),
        height: "100%",
        position: "absolute",
        pointerEvents: "none",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
    },
    inputRoot: {
        color: "inherit",
    },
    inputInput: {
        padding: theme.spacing(1, 1, 1, 0),
        // vertical padding + font size from searchIcon
        paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
        transition: theme.transitions.create("width"),
        width: "100%",
        [theme.breakpoints.up("sm")]: {
            width: "12ch",
            "&:focus": {
                width: "20ch",
            },
        },
    },
}));

export default function MySearch(props) {
    const classes = useStyles();

    return (
        <AppBar position="static" style={{ backgroundColor: "#F2C94C" }}>
            <Toolbar
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                }}
            >
                <Typography variant="h6" className={classes.logo}>
                    ORDER.COM
                </Typography>
                <div className={classes.search}>
                    <InputBase
                        placeholder="Search…"
                        classes={{
                            root: classes.inputRoot,
                            input: classes.inputInput,
                        }}
                        inputProps={{ "aria-label": "search" }}
                    />
                    <IconButton
                        type="submit"
                        className={classes.iconButton}
                        aria-label="search"
                    >
                        <SearchIcon />
                    </IconButton>
                </div>
            </Toolbar>
        </AppBar>
    );
}
