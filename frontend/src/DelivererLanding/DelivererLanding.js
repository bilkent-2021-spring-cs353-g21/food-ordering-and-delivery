import {
    CssBaseline,
    Typography,
    IconButton,
    AppBar,
    Toolbar,
    Drawer,
} from "@material-ui/core";
import React from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import classNames from "classnames";
import MenuIcon from "@material-ui/icons/Menu";
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import Button from "@material-ui/core/Button";
import SideMenu from "./SideMenu";
import { Link } from "react-router-dom";

import { setLocalStorage, getLocalStorage } from "../Service/localStorage";
import Profile from "../Deliverer/ProfileSettings/Profile";
import Deliveries from "../Deliverer/Deliveries/DeliveryTable";

const drawerWidth = 220;

class DelivererLanding extends React.Component {
    constructor() {
        super();
        this.state = {
            open: false,
            anchorEl: null,
            pageSelection: 0,
        };

        this.callbackFunction = (childData) => {
            this.setState({ pageSelection: childData });
        };

        this.handleDrawerOpen = () => {
            this.setState({ open: !this.state.open });
        };

        this.handleDrawerClose = () => {
            this.setState({ open: false });
        };

        this.handleMenu = (event) => {
            this.setState({ anchorEl: event.currentTarget });
        };
        this.handleClose = () => {
            this.setState({ anchorEl: null });
        };

        this.renderSwitch = (pageNo) => {
            switch (pageNo) {
                case 1:
                    return <Profile></Profile>;
                case 2:
                    return <Deliveries></Deliveries>
            }
        };
    }

    render() {
        const { classes, theme } = this.props;
        const { anchorEl } = this.state;
        const open = Boolean(anchorEl);
        const { history } = this.props;
        const isSignedIn = getLocalStorage("isSignedIn");
        if (!isSignedIn) {
            history.replace("/signin");
            window.location.href = "/signin";
        }

        return (
            <div className={classes.root}>
                <CssBaseline />
                <AppBar
                    position="fixed"
                    className={classes.appBar}
                    fooJon={classNames(classes.appBar, {
                        [classes.appBarShift]: this.state.open,
                    })}
                >
                    <Toolbar disableGutters={true}>
                        <IconButton
                            color="inherit"
                            aria-label="Open drawer"
                            onClick={this.handleDrawerOpen}
                            className={classes.menuButton}
                        >
                            <MenuIcon
                                classes={{
                                    root: this.state.open
                                        ? classes.menuButtonIconOpen
                                        : classes.menuButtonIconClosed,
                                }}
                            />
                        </IconButton>
                        <Typography
                            variant="h6"
                            color="inherit"
                            className={classes.title}
                            noWrap
                        >
                            {this.state.pageSelection}
                        </Typography>
                        <div>
                            <Button
                                component={Link}
                                to="/signin"
                                variant="contained"
                                color="secondary"
                                className={classes.button}
                                startIcon={<ExitToAppIcon />}
                                onClick={() => {
                                    setLocalStorage("username", "");
                                    setLocalStorage("isSignedIn", false);
                                    history.replace("/signin");
                                    window.location.href = "/signin";
                                }}
                            >
                                Log out
                            </Button>
                        </div>
                    </Toolbar>
                </AppBar>
                <Drawer
                    variant="permanent"
                    className={classNames(classes.drawer, {
                        [classes.drawerOpen]: this.state.open,
                        [classes.drawerClose]: !this.state.open,
                    })}
                    classes={{
                        paper: classNames({
                            [classes.drawerOpen]: this.state.open,
                            [classes.drawerClose]: !this.state.open,
                        }),
                    }}
                    open={this.state.open}
                >
                    <SideMenu parentCallback={this.callbackFunction}></SideMenu>
                </Drawer>
                <main className={classes.content}>
                    {this.renderSwitch(this.state.pageSelection)}
                </main>
            </div>
        );
    }
}

DelivererLanding.propTypes = {
    classes: PropTypes.object.isRequired,
    theme: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired,
};

const styles = (theme) => ({
    root: {
        display: "flex",
        //flexGrow: 1,
    },
    appBar: {
        zIndex: theme.zIndex.drawer + 1,
        backgroundColor: "rgb(238, 197, 16)",
    },
    appBarShift: {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(["width", "margin"], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    menuButton: {
        marginLeft: 12,
        marginRight: 36,
    },
    menuButtonIconClosed: {
        transition: theme.transitions.create(["transform"], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        transform: "rotate(0deg)",
    },
    menuButtonIconOpen: {
        transition: theme.transitions.create(["transform"], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        transform: "rotate(180deg)",
    },
    hide: {
        display: "none",
    },
    drawer: {
        width: drawerWidth,
        flexShrink: 0,
        whiteSpace: "nowrap",
    },
    drawerOpen: {
        width: drawerWidth,
        transition: theme.transitions.create("width", {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    drawerClose: {
        transition: theme.transitions.create("width", {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        overflowX: "hidden",
        width: theme.spacing.unit * 7 + 1,
        [theme.breakpoints.up("sm")]: {
            width: theme.spacing.unit * 9 + 1,
        },
    },
    toolbar: {
        display: "flex",
        alignItems: "center",
        marginTop: theme.spacing.unit,
        justifyContent: "flex-end",
        padding: "0 8px",
        ...theme.mixins.toolbar,
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing.unit * 3,
    },
    grow: {
        flexGrow: 1,
    },

    //  menuButton: {
    //    marginRight: theme.spacing(2),
    //},
    title: {
        flexGrow: 1,
        fontFamily: "Times New Roman",
        fontSize: "2rem",
        color: "rgb(206, 14, 14)",
    },
    button: {
        margin: theme.spacing(1),
        fontFamily: "Times New Roman",
        backgroundColor: "rgb(233, 166, 11)",
        color: "rgb(0, 0, 0)",
        "&:hover": {
            backgroundColor: "#fff",
            color: "#rgb(0, 0, 0)",
        },
    },
});

export default withStyles(styles, { withTheme: true })(DelivererLanding);
