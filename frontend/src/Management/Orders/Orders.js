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
import { getLocalStorage, setLocalStorage } from "../../Service/localStorage";
import AddCircleIcon from "@material-ui/icons/AddCircle";
import PropTypes from "prop-types";
import axios from "axios";
import request from "../../Service/request";
import "./Orders.css";
import OrderTable from "./OrderTable";

const useStyles = makeStyles((theme) => ({
    button: {
        margin: theme.spacing(1),
    },
}));

export const Orders = () => {
    const classes = useStyles();
    return (
        <div>
            <br />
            <div className="container">
                <h1 className="h1">Orders</h1>
                <OrderTable></OrderTable>
            </div>
        </div>
    );
};
