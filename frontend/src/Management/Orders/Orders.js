import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
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
