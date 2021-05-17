import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import CommentTable from "./CommentTable";

const useStyles = makeStyles((theme) => ({
    button: {
        margin: theme.spacing(1),
    },
}));

export const Comments = () => {
    const classes = useStyles();
    return (
        <div>
            <br />
            <div className="container">
                <h1 className="h1">Comments</h1>
                <CommentTable></CommentTable>
            </div>
        </div>
    );
};
