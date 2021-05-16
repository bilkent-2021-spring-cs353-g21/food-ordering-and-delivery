import {
    makeStyles,
    Box,
    Typography,
    fade,
    Container,
} from "@material-ui/core";
import React from "react";
{
    /*import backgroundImg from "~/assets/landing_background.jpg";*/
}
import SiteHeader from "~/components/SiteHeader";
import MyBasket from "~/components/MyBasket";
import MealBox from "~/components/MealBox";

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
        marginTop: 40,
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
    info: {
        paddingTop: 10,
        display: "flex",
    },
}));

export default function Landing() {
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
                        Ustamin El Isi
                    </Typography>
                </Box>
                <Box style={{ backgroundColor: "#FFFFFF" }}>
                    <Typography variant="h7" className={classes.info}>
                        Status: Open
                    </Typography>
                    <Typography variant="h7" className={classes.info}>
                        Contact: +903122667254
                    </Typography>
                    <Typography variant="h7" className={classes.info}>
                        Address: Ankara, Bahcelievler
                    </Typography>
                </Box>
            </Container>

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
                        Ana Yemekler
                    </Typography>
                </Box>
                <MealBox />
                <MealBox />
                <MealBox />

                <Box className={classes.boxHeader}>
                    <Typography
                        variant="h5"
                        style={{ display: "flex", justifyContent: "center" }}
                    >
                        Chef&apos;s Special
                    </Typography>
                </Box>
                <MealBox />
                <MealBox />
            </Container>

            <MyBasket />
        </>
    );
}
