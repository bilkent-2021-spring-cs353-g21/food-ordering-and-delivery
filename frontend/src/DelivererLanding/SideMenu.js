import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import ListSubheader from "@material-ui/core/ListSubheader";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";
import Collapse from "@material-ui/core/Collapse";
import ExpandLess from "@material-ui/icons/ExpandLess";
import ExpandMore from "@material-ui/icons/ExpandMore";
import RestaurantIcon from "@material-ui/icons/Restaurant";
import SettingsIcon from "@material-ui/icons/Settings";
import RestaurantMenuIcon from "@material-ui/icons/RestaurantMenu";
import ReceiptIcon from "@material-ui/icons/Receipt";
import AccountCircle from "@material-ui/icons/AccountCircle";
import CommentIcon from "@material-ui/icons/Comment";
import Divider from "@material-ui/core/Divider";
import { getLocalStorage, setLocalStorage } from "../Service/localStorage";
import MotorcycleIcon from '@material-ui/icons/Motorcycle';
import PropTypes from "prop-types";
import axios from "axios";
import request from "../Service/request";

const sideMenuStyles = makeStyles((theme) => ({
    root: {
        width: "100%",
        maxWidth: 360,
        backgroundColor: theme.palette.background.paper,
    },
    nested: {
        paddingLeft: theme.spacing(4),
    },
    listItem: { width: "220px" },
}));

function RestaurantItem({ restaurant, callback }) {
    const classes = sideMenuStyles();
    const [open, setOpen] = React.useState(false);
    const handleClick = () => {
        setOpen(!open);
    };
    return (
        <div>
            <ListItem button onClick={handleClick} className={classes.listItem}>
                <ListItemIcon>
                    <RestaurantIcon />
                </ListItemIcon>
                <ListItemText primary={restaurant.name} />
                {open ? <ExpandLess /> : <ExpandMore />}
            </ListItem>
            <Collapse in={open} timeout="auto" unmountOnExit>
                <List component="div" disablePadding>
                    <ListItem
                        button
                        onClick={() => {
                            setLocalStorage("rid", restaurant.rid);
                            setLocalStorage("rname", restaurant.name);
                            callback(3);
                        }}
                        className={classes.nested}
                    >
                        <ListItemIcon>
                            <SettingsIcon />
                        </ListItemIcon>
                        <ListItemText primary="Settings" />
                    </ListItem>
                </List>
            </Collapse>
            <Collapse in={open} timeout="auto" unmountOnExit>
                <List component="div" disablePadding>
                    <ListItem
                        button
                        onClick={() => {
                            setLocalStorage("rid", restaurant.rid);
                            setLocalStorage("rname", restaurant.name);
                            callback(4);
                        }}
                        className={classes.nested}
                    >
                        <ListItemIcon>
                            <ReceiptIcon />
                        </ListItemIcon>
                        <ListItemText primary="Orders" />
                    </ListItem>
                </List>
            </Collapse>
            <Collapse in={open} timeout="auto" unmountOnExit>
                <List component="div" disablePadding>
                    <ListItem
                        button
                        onClick={() => {
                            setLocalStorage("rid", restaurant.rid);
                            setLocalStorage("rname", restaurant.name);
                            callback(5);
                        }}
                        className={classes.nested}
                    >
                        <ListItemIcon>
                            <CommentIcon />
                        </ListItemIcon>
                        <ListItemText primary="Comments" />
                    </ListItem>
                </List>
            </Collapse>
            <Collapse in={open} timeout="auto" unmountOnExit>
                <List component="div" disablePadding>
                    <ListItem
                        button
                        onClick={() => {
                            setLocalStorage("rid", restaurant.rid);
                            setLocalStorage("rname", restaurant.name);
                            callback(6);
                        }}
                        className={classes.nested}
                    >
                        <ListItemIcon>
                            <RestaurantMenuIcon />
                        </ListItemIcon>
                        <ListItemText primary="Menu" />
                    </ListItem>
                </List>
            </Collapse>
        </div>
    );
}
RestaurantItem.propTypes = {
    restaurant: PropTypes.object.isRequired,
    callback: PropTypes.object.isRequired,
};

function Restaurants({ parentsParentCallback }) {
    const [restaurants, setRestaurants] = React.useState();
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        async function getRestaurants() {
            const response = await request(axios.get, "/manager/restaurants");
            var restaurants = [];

            for (var i in response.data.data) {
                restaurants.push({
                    name: response.data.data[i]["name"],
                    rid: response.data.data[i]["rid"],
                });
            }

            setRestaurants(restaurants);
            setLoading(false);
        }
        getRestaurants();
    });

    return (
        <div>
            {loading ? (
                <h1>...</h1>
            ) : (
                restaurants.map((restaurant, index) => {
                    return (
                        <tr key={index}>
                            <RestaurantItem
                                restaurant={restaurant}
                                callback={parentsParentCallback}
                            ></RestaurantItem>
                        </tr>
                    );
                })
            )}
        </div>
    );
}
Restaurants.propTypes = {
    parentsParentCallback: PropTypes.object.isRequired,
};

export default function SideMenu({ parentCallback }) {
    const classes = sideMenuStyles();
    const [open, setOpen] = React.useState(true);

    const handleClick = () => {
        setOpen(!open);
    };

    return (
        <List
            component="nav"
            aria-labelledby="nested-list-subheader"
            subheader={
                <ListSubheader component="div" id="nested-list-subheader">
                    Nested List Items
                </ListSubheader>
            }
            className={classes.root}
        >
            <br />
            <ListItem
                button
                onClick={() => {
                    parentCallback(1);
                }}
            >
                <ListItemIcon>
                    <AccountCircle />
                </ListItemIcon>
                <ListItemText primary={getLocalStorage("username")} />
            </ListItem>
            <Divider />
            <ListItem
                button
                onClick={() => {
                    parentCallback(2);
                }}
            >
                <ListItemIcon>
                    <MotorcycleIcon />
                </ListItemIcon>
                <ListItemText primary="Show Deliveries" />
            </ListItem>
            <Divider />
            <Restaurants parentsParentCallback={parentCallback}></Restaurants>
        </List>
    );
}

SideMenu.propTypes = {
    parentCallback: PropTypes.object.isRequired,
};
