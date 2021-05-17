import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import IconButton from "@material-ui/core/IconButton";
import DeleteIcon from "@material-ui/icons/Delete";
import EditIcon from "@material-ui/icons/Edit";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import PropTypes from "prop-types";
import MaskedInput from "react-text-mask";
import NumberFormat from "react-number-format";
import axios from "axios";
import request from "../../Service/request";
import { getLocalStorage } from "../../Service/localStorage";
import CheckCircleIcon from "@material-ui/icons/CheckCircle";

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
    { id: "content", label: "Content", minWidth: 300 },

    { id: "placedTime", label: "Order Placed Time", minWidth: 100 },
    { id: "requestTime", label: "Delivery Request Time", minWidth: 100 },
    { id: "status", label: "Status", minWidth: 50 },
    {
        id: "price",
        label: "Price",
        minWidth: 50,
        format: (value) => value.toLocaleString("en-US"),
    },
    { id: "actions", label: "Finalize" },
];

function createData(
    content,
    placedTime,
    requestTime,
    status,
    price,
    actions,
    oid
) {
    return { content, placedTime, requestTime, status, price, actions, oid };
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
    const [totalOrders, setTotalOrders] = useState(0);
    const [orderIndex, setOrderIndex] = useState(0);
    const [orders, setOrders] = useState([]);
    const [finalize, setFinalize] = useState(false);

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
        setFinalize(true);
        setOpen(false);
    };

    useEffect(() => {
        async function finalizeOrder() {
            const response = await request(
                axios.post,
                "/manager/restaurant/" +
                    getLocalStorage("rid") +
                    " /orders/" +
                    orders[orderIndex]["oid"] +
                    "/request_delivery"
            );
            console.log(orders[orderIndex]["oid"]);
        }

        if (finalize == true) {
            finalizeOrder();
            setFinalize(false);
        }
    }, [finalize]);

    useEffect(() => {
        async function getOrders() {
            const response = await request(
                axios.get,
                "/manager/restaurant/" +
                    getLocalStorage("rid") +
                    "/orders?page=" +
                    page +
                    "&limit=10"
            );
            var orders = [];
            for (var i in response.data.data.orders) {
                const k = i;
                var content = "";

                for (var j in response.data.data.orders[i]["content"]) {
                    content =
                        Object.keys(response.data.data.orders[i]["content"][j])
                            .map(function (k) {
                                if (k == "quantity") {
                                    return (
                                        "x" +
                                        response.data.data.orders[i]["content"][
                                            j
                                        ][k] +
                                        ""
                                    );
                                }
                                if (k == "mealPrice") {
                                    return (
                                        "price: " +
                                        response.data.data.orders[i]["content"][
                                            j
                                        ][k]
                                    );
                                }
                                if (k == "ingredients") {
                                    return (
                                        "ingredients: " +
                                        response.data.data.orders[i]["content"][
                                            j
                                        ][k].join()
                                    );
                                }
                                return response
                                    .data.data.orders[i]["content"][j][k];
                            })
                            .join(" ") + "\n";
                }

                orders.push(
                    createData(
                        content,
                        response.data.data.orders[i]["placedTime"],
                        response.data.data.orders[i]["requestedDeliveryTime"],
                        response.data.data.orders[i]["status"],
                        response.data.data.orders[i]["cost"],
                        <>
                            <IconButton
                                onClick={() => {
                                    handleClickOpen();
                                    setOrderIndex(k);
                                }}
                            >
                                <CheckCircleIcon style={{ fill: "blue" }} />
                            </IconButton>
                        </>,
                        response.data.data.orders[i]["oid"]
                    )
                );
            }
            setOrders(orders);
        }

        getOrders();
    }, [page]);

    useEffect(() => {
        async function getTotal() {
            const response = await request(
                axios.get,
                "/manager/restaurant/" +
                    getLocalStorage("rid") +
                    "/orders?0=1&limit=1"
            );

            setTotalOrders(response.data.data.totalOrders);
            console.log(response.data.data.totalOrders);
        }

        getTotal();
    }, []);

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
                        {orders.map((orders) => {
                            return (
                                <TableRow
                                    hover
                                    role="checkbox"
                                    tabIndex={-1}
                                    key={orders.code}
                                >
                                    {columns.map((column) => {
                                        const value = orders[column.id];
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
                count={totalOrders}
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
                <DialogTitle id="form-dialog-title">
                    Do you want to finalize this order?
                </DialogTitle>
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
