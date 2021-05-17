import React from "react";
import PropTypes from "prop-types";
import {
    Box,
    Container,
    fade,
    makeStyles,
    Typography,
} from "@material-ui/core";
import MealBox from "~/components/MealBox";
import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Pagination } from "swiper/core";
import "swiper/swiper.min.css";
import "swiper/components/pagination/pagination.min.css";
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
}));

export default function SearchResults(props) {
    const classes = useStyles();

    return (
        <>
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

            {(() => {
                if (props.results && props.results.mealResults) {
                    const meals = props.results.mealResults.meals;
                    return meals.map((meal) => {
                        return <MealBox meal={meal} key={meal.rid} />;
                    });
                }
            })()}

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
                        Ev yemekleri
                    </Typography>
                </Box>
                <MealBox />

                <Box className={classes.boxHeader}>
                    <Typography
                        variant="h5"
                        style={{ display: "flex", justifyContent: "center" }}
                    >
                        Abimin doneri
                    </Typography>
                </Box>
                <MealBox />
            </Container>
        </>
    );
}

SearchResults.propTypes = {
    results: PropTypes.object,
};
