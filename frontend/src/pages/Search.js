import {
    makeStyles,
    Box,
    AppBar,
    Toolbar,
    Typography,
    InputBase,
    fade,
    Container,
    FormControl,
    FormControlLabel,
    withStyles,
    Switch,
    Divider,
    Slider,
    IconButton,
} from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";
import { yellow } from "@material-ui/core/colors";
import React from "react";
{
    /*import backgroundImg from "~/assets/landing_background.jpg";*/
}
import SiteHeader from "~/components/SiteHeader";
import MyBasket from "~/components/MyBasket";
import MealBox from "~/components/MealBox";

import { Swiper, SwiperSlide } from "swiper/react";

import "swiper/swiper.min.css";
import "swiper/components/pagination/pagination.min.css";

import "./styles.css";

// import Swiper core and required modules
import SwiperCore, { Pagination } from "swiper/core";

// install Swiper modules
SwiperCore.use([Pagination]);

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
    boxHeader: {
        background: "#F2C94C",
        marginTop: 20,
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
    swiper: {
        marginTop: 30,
    },
    searchFilters: {
        maxWidth: 300,
        float: "left",
    },
    slider: {
        color: "#F2C94C",
        marginLeft: 20,
        maxWidth: 200,
    },
}));

const YellowSwitch = withStyles({
    switchBase: {
        color: yellow[300],
        "&$checked": {
            color: yellow[500],
        },
        "&$checked + $track": {
            backgroundColor: yellow[500],
        },
    },
    checked: {},
    track: {},
})(Switch);

function valuetext(value) {
    return `${value}°C`;
}

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

    const handleSwitch = (event) => {
        setState({ ...state, [event.target.name]: event.target.checked });
    };

    const classes = useStyles();

    return (
        <>
            <SiteHeader />

            <Container className={classes.searchFilters}>
                <Box className={classes.boxHeader}>
                    <Typography
                        variant="h6"
                        style={{ display: "flex", justifyContent: "center" }}
                    >
                        Search Filters
                    </Typography>
                </Box>
                <Box style={{ backgroundColor: "#FFFFFF" }}>
                    <FormControl>
                        <FormControlLabel
                            control={
                                <YellowSwitch
                                    checked={state.checkedA}
                                    onChange={handleSwitch}
                                    name="checkedA"
                                />
                            }
                            style={{
                                marginLeft: 5,
                            }}
                            label="Open"
                        />
                        <Divider style={{ width: 250 }} />
                        <FormControlLabel
                            control={
                                <YellowSwitch
                                    checked={state.checkedB}
                                    onChange={handleSwitch}
                                    name="checkedB"
                                />
                            }
                            style={{
                                marginLeft: 5,
                            }}
                            label="Serves your district"
                        />

                        <Divider style={{ width: 250 }} />
                        <Typography>Minimum delivery cost</Typography>
                        <Slider
                            className={classes.slider}
                            defaultValue={5}
                            getAriaValueText={valuetext}
                            aria-labelledby="min-delivery-cost"
                            step={5}
                            marks
                            min={5}
                            max={40}
                            valueLabelDisplay="auto"
                        />

                        <Divider style={{ width: 250 }} />
                        <Typography>Minimum rating</Typography>
                        <Slider
                            className={classes.slider}
                            defaultValue={0}
                            getAriaValueText={valuetext}
                            aria-labelledby="min-delivery-cost"
                            step={1}
                            marks
                            min={0}
                            max={10}
                            valueLabelDisplay="auto"
                        />
                        <Divider style={{ width: 250 }} />
                    </FormControl>
                </Box>
            </Container>

            <Swiper
                slidesPerView={3}
                spaceBetween={25}
                centeredSlides={true}
                pagination={{
                    clickable: true,
                }}
                className={classes.swiper}
            >
                <SwiperSlide>Slide 1</SwiperSlide>
                <SwiperSlide>Slide 2</SwiperSlide>
                <SwiperSlide>Slide 3</SwiperSlide>
                <SwiperSlide>Slide 4</SwiperSlide>
                <SwiperSlide>Slide 5</SwiperSlide>
                <SwiperSlide>Slide 6</SwiperSlide>
            </Swiper>

            <Container
                maxWidth="md"
                style={{
                    marginTop: 40,
                    position: "relative",
                    left: "10%",
                }}
            >
                <Box className={classes.boxHeader}>
                    <Typography
                        variant="h5"
                        style={{ display: "flex", justifyContent: "center" }}
                    >
                        Ustamin el isi
                    </Typography>
                </Box>
                <MealBox />

                <Box className={classes.boxHeader}>
                    <Typography
                        variant="h5"
                        style={{ display: "flex", justifyContent: "center" }}
                    >
                        Caqqolunun yeri
                    </Typography>
                </Box>
                <MealBox />

                <Box className={classes.boxHeader}>
                    <Typography
                        variant="h5"
                        style={{ display: "flex", justifyContent: "center" }}
                    >
                        Caqqolunun yeri
                    </Typography>
                </Box>
                <MealBox />
            </Container>

            <MyBasket />
        </>
    );
}
