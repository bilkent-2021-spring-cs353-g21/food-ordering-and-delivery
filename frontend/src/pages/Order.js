import {
    makeStyles,
    Paper,
    AppBar,
    Link,
    Toolbar,
    fade,
    Container,
} from "@material-ui/core";
import React from "react";
{
    /*import backgroundImg from "~/assets/landing_background.jpg";*/
}
import adImg from "~/assets/ad.jpg";
import MyButton from "~/components/MyButton";
import SiteHeader from "~/components/SiteHeader";

const useStyles = makeStyles((theme) => ({
    background: {
        opacity: 1,
    },
    ad: {
        opacity: 1,
        aspectRatio: 0.2,
        margin: 14,
    },
    basket: {
        maxHeight: 400,
        maxWidth: 300,
        background: "#F2C94C",
    },
    header: {
        background: "#F2C94C",
    },
    myButton: {
        marginTop: 8,
    },
    mySearch: {
        minWidth: 300,
        marginTop: 8,
        marginBottom: 8,
    },
    logo: {
        marginLeft: 30,
        fontSize: 30,
    },
    panel: {
        background: "#F2C94C",
        maxHeight: 600,
        minWidth: 200,
        minHeight: 400,
        maxWidth: 300,
        marginLeft: 60,
    },
    tabs: {
        margin: 24,
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
    addressPrompt: {
        padding: "68px 68px 100px",
        background: "#F2C94C",
        backgroundColor: "rgba(242, 201, 76, 0.91)",
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 100,
        borderRadius: 4,
        marginLeft: 20,
        marginTop: 30,
    },
}));

export default function Landing() {
    const isSignedIn = localStorage.getItem("isSignedIn");
    let city = localStorage.getItem("city");
    let district = localStorage.getItem("district");

    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);

    const handleMenu = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const confirm = (e) => {
        e.preventDefault();

        city = e.target.city.value;
        district = e.target.district.value;

        localStorage.setItem("city", city);
        localStorage.setItem("district", district);

        window.location.href = "/";
    };

    const [state, setState] = React.useState({
        city: "",
        district: "",
    });

    const handleChange = (event) => {
        const name = event.target.name;
        setState({
            ...state,
            [name]: event.target.value,
        });
    };

    const classes = useStyles();

    return (
        <>
            <SiteHeader />

            <Paper
                className={classes.panel}
                style={{
                    display: "flex",
                }}
            >
                <img
                    className={classes.ad}
                    src={adImg}
                    style={{
                        flex: 1,
                    }}
                />
            </Paper>
        </>
    );
}
