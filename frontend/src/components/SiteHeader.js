import React from "react";
import {
    makeStyles,
    Typography,
    IconButton,
    InputBase,
    AppBar,
    Toolbar,
    Menu,
    MenuItem,
} from "@material-ui/core";

import AccountCircle from "@material-ui/icons/AccountCircle";
import SearchIcon from "@material-ui/icons/Search";
import MyButton from "./MyButton";
import { LaptopWindows } from "@material-ui/icons";

const useStyles = makeStyles((theme) => ({
    logo: {
        marginLeft: 30,
        fontSize: 30,
    },
    search: {
        position: "relative",
        float: "right",
        borderRadius: 5,
        backgroundColor: "rgba(255, 255, 255, 0.5)",
        "&:hover": {
            backgroundColor: "rgba(255, 255, 255, 0.35)",
        },
        width: "auto",
    },
    inputRoot: {
        color: "black",
    },
    inputInput: {
        padding: 1,
        // vertical padding + font size from searchIcon
        paddingLeft: 20,
        transition: theme.transitions.create("width"),
        width: "16ch",
        "&:focus": {
            width: "28ch",
        },
    },
}));

export default function MySearch() {
    const isSignedIn = localStorage.getItem("isSignedIn");

    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);

    const handleMenu = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const goToSignin = () => {
        window.location.href = "/signin";
    };

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

                {isSignedIn ? (
                    <div>
                        <IconButton
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            onClick={handleMenu}
                            color="inherit"
                        >
                            <AccountCircle />
                        </IconButton>
                        <Menu
                            id="menu-appbar"
                            anchorEl={anchorEl}
                            anchorOrigin={{
                                vertical: "top",
                                horizontal: "right",
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: "top",
                                horizontal: "right",
                            }}
                            open={open}
                            onClose={handleClose}
                        >
                            <MenuItem onClick={handleClose} href="/profile">
                                Profile
                            </MenuItem>
                            <MenuItem onClick={handleClose} href="/myaccount">
                                Edit Address
                            </MenuItem>
                            <MenuItem onClick={handleClose} href="/">
                                Log out
                            </MenuItem>
                        </Menu>
                    </div>
                ) : (
                    <MyButton onClick={goToSignin}>Sign in</MyButton>
                )}
            </Toolbar>
        </AppBar>
    );
}
