import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import DialogContent from "@material-ui/core/DialogContent";
import TextField from "@material-ui/core/TextField";

import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import IconButton from "@material-ui/core/IconButton";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogTitle from "@material-ui/core/DialogTitle";
import PropTypes from "prop-types";
import NumberFormat from "react-number-format";
import axios from "axios";
import request from "../../Service/request";
import { getLocalStorage } from "../../Service/localStorage";
import ChatIcon from "@material-ui/icons/Chat";

function NumberFormatCustom(props) {
    const { inputRef, onChange, ...other } = props;

    return (
        <NumberFormat
            {...other}
            getInputRef={inputRef}
            onValueChange={(values) => {
                onChange({
                    target: {
                        name: props.name,
                        value: values.value,
                    },
                });
            }}
            thousandSeparator
            isNumericString
            prefix="$"
        />
    );
}

NumberFormatCustom.propTypes = {
    inputRef: PropTypes.func.isRequired,
    name: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
};

const columns = [
    { id: "placedTime", label: "Placed Time", minWidth: 160 },
    {
        id: "requestedDeliveryTime",
        label: "Requested Delivery Time",
        minWidth: 160,
    },
    {
        id: "status",
        label: "Status",
        minWidth: 50,
    },
    {
        id: "cost",
        label: "Cost",
        minWidth: 50,
        format: (value) => value.toLocaleString("en-US"),
    },
    { id: "restaurantScore", label: "Restaurant Score", minWidth: 50 },
    { id: "deliveryScore", label: "Delivery Score", minWidth: 50 },
    { id: "text", label: "Text", minWidth: 100 },
    { id: "response", label: "Response", minWidth: 100 },
    { id: "reply", label: "Reply" },
];

function createData(
    placedTime,
    requestedDeliveryTime,
    status,
    cost,
    restaurantScore,
    deliveryScore,
    text,
    response,
    reply,
    oid
) {
    return {
        placedTime,
        requestedDeliveryTime,
        status,
        cost,
        restaurantScore,
        deliveryScore,
        text,
        response,
        reply,
        oid,
    };
}

const useStyles = makeStyles({
    root: {
        fontFamily: "Times new roman",
        width: "100%",
    },
    container: {
        maxHeight: 1000,
    },
});

export default function StickyHeadTable() {
    const classes = useStyles();

    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [totalComments, setTotalComments] = useState(0);
    const [commentIndex, setCommentIndex] = useState(0);
    const [comments, setComments] = useState([]);
    const [reply, setReply] = useState(false);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);

        setPage(0);
    };

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleClose_ = () => {
        setReply(true);
        setOpen(false);
    };

    useEffect(() => {}, [reply]);

    useEffect(() => {
        async function getTotal() {
            const response = await request(
                axios.get,
                "/manager/restaurant/" + getLocalStorage("rid") + "/comments"
            );

            setTotalComments(response.data.data.totalComments);
        }

        getTotal();
    }, [page]);

    useEffect(() => {
        async function getComments() {
            var page_ = page;
            console.log(page_);
            const response = await request(
                axios.get,
                "/manager/restaurant/" + getLocalStorage("rid") + "/comments",
                {
                    limit: 10,
                    page: page_,
                }
            );

            var comments = [];
            for (var i in response.data.data.comments) {
                const k = i;
                comments.push(
                    createData(
                        response.data.data.comments[i].order["placedTime"],
                        response.data.data.comments[i].order[
                            "requestedDeliveryTime"
                        ],
                        response.data.data.comments[i].order["status"],
                        response.data.data.comments[i].order["cost"],
                        response.data.data.comments[i]["restaurantScore"],
                        response.data.data.comments[i]["deliveryScore"],
                        response.data.data.comments[i]["text"],
                        response.data.data.comments[i]["response"],
                        <>
                            <IconButton
                                onClick={() => {
                                    handleClickOpen();
                                    setCommentIndex(k);
                                }}
                            >
                                <ChatIcon style={{ fill: "black" }} />
                            </IconButton>
                        </>,
                        response.data.data.comments[i].order["oid"]
                    )
                );
            }
            setComments(comments);
        }

        getComments();
    }, [page]);

    return (
        <Paper className={classes.root}>
            <TableContainer className={classes.container}>
                <Table stickyHeader aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            {columns.map((column) => (
                                <TableCell
                                    key={column.id}
                                    align={column.align}
                                    style={{ minWidth: column.minWidth }}
                                >
                                    {column.label}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {comments.map((comments) => {
                            return (
                                <TableRow
                                    hover
                                    role="checkbox"
                                    tabIndex={-1}
                                    key={comments.code}
                                >
                                    {columns.map((column) => {
                                        const value = comments[column.id];
                                        return (
                                            <TableCell
                                                key={column.id}
                                                align={column.align}
                                            >
                                                {column.format &&
                                                typeof value === "number"
                                                    ? column.format(value)
                                                    : value}
                                            </TableCell>
                                        );
                                    })}
                                </TableRow>
                            );
                        })}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[10]}
                component="div"
                count={totalComments}
                rowsPerPage={rowsPerPage}
                page={page}
                onChangePage={handleChangePage}
                onChangeRowsPerPage={handleChangeRowsPerPage}
            />
            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="form-dialog-title"
            >
                <DialogTitle id="form-dialog-title">Reply</DialogTitle>
                <DialogContent>
                    <TextField
                        required
                        margin="dense"
                        id="name"
                        label="Reply"
                        fullWidth
                        multiline
                        rowsMax={4}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={handleClose_} color="primary">
                        Yes
                    </Button>
                </DialogActions>
            </Dialog>
        </Paper>
    );
}
