import {
    makeStyles,
    Paper,
    AppBar,
    Link,
    Toolbar,
    Typography,
    InputBase,
    fade,
    IconButton,
    Menu,
    MenuItem,
    Container,
    FormControl,
    InputLabel,
    Select,
} from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";
import AccountCircle from "@material-ui/icons/AccountCircle";
import React from "react";
{
    /*import backgroundImg from "~/assets/landing_background.jpg";*/
}
import adImg from "~/assets/ad.jpg";
import MyButton from "~/components/MyButton";

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
            {district !== "" ? (
                <>
                    <AppBar position="static" className={classes.header}>
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
                                <div className={classes.searchIcon}>
                                    <SearchIcon />
                                </div>
                                <InputBase
                                    placeholder="Search…"
                                    classes={{
                                        root: classes.inputRoot,
                                        input: classes.inputInput,
                                    }}
                                    inputProps={{ "aria-label": "search" }}
                                />
                            </div>
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
                                    <MenuItem
                                        onClick={handleClose}
                                        href="/profile"
                                    >
                                        Profile
                                    </MenuItem>
                                    <MenuItem
                                        onClick={handleClose}
                                        href="/myaccount"
                                    >
                                        My account
                                    </MenuItem>
                                    <MenuItem onClick={handleClose} href="/">
                                        Log out
                                    </MenuItem>
                                </Menu>
                            </div>
                        </Toolbar>
                    </AppBar>

                    <Link
                        href="/aboutus"
                        className={classes.tabs}
                        style={{ float: "right" }}
                    >
                        About Us
                    </Link>
                    <Link
                        href="/offers"
                        className={classes.tabs}
                        style={{ float: "right" }}
                    >
                        Offers
                    </Link>
                    <Link
                        href="/popularrestaurants"
                        className={classes.tabs}
                        style={{ float: "right" }}
                    >
                        Popular Restaurants
                    </Link>
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

                    <AppBar position="static" className={classes.header}>
                        <Toolbar
                            style={{
                                display: "flex",
                                justifyContent: "space-between",
                            }}
                        >
                            <MyButton className={classes.myButton}>
                                Restaurant Manager Sign Up / In
                            </MyButton>
                            <MyButton className={classes.myButton}>
                                Deliverer Sign Up / In
                            </MyButton>
                        </Toolbar>
                    </AppBar>
                </>
            ) : (
                <form onSubmit={confirm}>
                    <Container
                        className={classes.addressPrompt}
                        maxWidth="xs"
                        style={{
                            position: "absolute",
                            left: "50%",
                            top: "50%",
                            transform: "translate(-50%, -50%)",
                        }}
                    >
                        Please select a city and a district to proceed
                        <FormControl className={classes.formControl}>
                            <InputLabel id="city-selector">City</InputLabel>
                            <Select
                                native
                                labelId="city-selector"
                                id="city-selector"
                                value={state.city}
                                onChange={handleChange}
                                inputProps={{
                                    name: "city",
                                    id: "city-selector",
                                }}
                            >
                                <option aria-label="" value="" />
                                <option>Cankaya</option>
                                <option>Kecioren</option>
                            </Select>
                        </FormControl>
                        <FormControl className={classes.formControl}>
                            <InputLabel id="district-selector">
                                District
                            </InputLabel>
                            <Select
                                labelId="district-selector"
                                id="district-selector"
                                value={state.district}
                                onChange={handleChange}
                                inputProps={{
                                    name: "district",
                                    id: "district-selector",
                                }}
                            >
                                <MenuItem value="Cankaya">Cankaya</MenuItem>
                                <MenuItem value={"Kecioren"}>Kecioren</MenuItem>
                                <MenuItem value={"Mamak"}>Mamak</MenuItem>
                            </Select>
                        </FormControl>
                        <MyButton
                            type="submit"
                            className={classes.myButton}
                            style={{
                                position: "absolute",
                                left: "48%",
                                top: "75%",
                                transform: "translate(-50%, -50%)",
                            }}
                        >
                            Confirm
                        </MyButton>
                    </Container>
                </form>
            )}
        </>
    );
}
