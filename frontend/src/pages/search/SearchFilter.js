import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import {
    Box,
    Container,
    Divider,
    FormControl,
    FormControlLabel,
    makeStyles,
    Slider,
    Switch,
    Typography,
    withStyles,
} from "@material-ui/core";
import { yellow } from "@material-ui/core/colors";

const useStyles = makeStyles({
    boxHeader: {
        background: "#F2C94C",
        marginTop: 20,
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
});

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

export default function SearchFilters(props) {
    const { onFiltersChange, keyword } = { ...props };

    const [onlyServingDistrict, setOnlyServingDistrict] = useState(false);
    const [minDeliveryCost, setMinDeliveryCost] = useState(5);
    const [scoreAtLeast, setScoreAtLeast] = useState(5);

    useEffect(() => {
        // const district = getLocalStorage("district");
        const district = {
            cityName: "Ankara",
            districtName: "Çankaya",
        };

        let data = {
            district: district,
            keyword: keyword,
            maxPrice: 1000,
            minPrice: 0,
            onlyServingDistrict: onlyServingDistrict,
            scoreAtLeast: scoreAtLeast,
        };
        if (onlyServingDistrict) data[minDeliveryCost] = minDeliveryCost;

        onFiltersChange(data);
    }, [
        onlyServingDistrict,
        minDeliveryCost,
        scoreAtLeast,
        keyword,
        onFiltersChange,
    ]);

    const classes = useStyles();
    return (
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
                                checked={onlyServingDistrict}
                                onChange={(_e, checked) =>
                                    setOnlyServingDistrict(checked)
                                }
                                name="checkedA"
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
                        aria-labelledby="min-delivery-cost"
                        disabled={!onlyServingDistrict}
                        step={5}
                        defaultValue={5}
                        marks
                        min={5}
                        max={40}
                        valueLabelDisplay="auto"
                        onChangeCommitted={(_e, val) => setMinDeliveryCost(val)}
                    />

                    <Divider style={{ width: 250 }} />
                    <Typography>Minimum rating</Typography>
                    <Slider
                        className={classes.slider}
                        aria-labelledby="socre-at-least"
                        step={1}
                        defaultValue={5}
                        marks
                        min={0}
                        max={10}
                        valueLabelDisplay="auto"
                        onChangeCommitted={(_e, val) => setScoreAtLeast(val)}
                    />
                    <Divider style={{ width: 250 }} />
                </FormControl>
            </Box>
        </Container>
    );
}

SearchFilters.propTypes = {
    onFiltersChange: PropTypes.func.isRequired,
    keyword: PropTypes.string.isRequired,
};
