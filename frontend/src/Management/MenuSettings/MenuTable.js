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
import DialogContentText from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import PropTypes from "prop-types";
import MaskedInput from "react-text-mask";
import NumberFormat from "react-number-format";
import axios from "axios";
import request from "../../Service/request";
import { getLocalStorage } from "../../Service/localStorage";

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
    { id: "name", label: "Name", minWidth: 100 },
    {
        id: "price",
        label: "Price",
        minWidth: 30,
        format: (value) => value.toLocaleString("en-US"),
    },
    { id: "type", label: "Type", minWidth: 50 },
    { id: "ingredients", label: "Ingredients", minWidth: 100 },
    { id: "description", label: "Description", minWidth: 100 },
    { id: "actions", label: "Actions", width: 170 },
];

function createData(name, price, type, ingredients, description, actions) {
    return { name, price, ingredients, description, type, actions };
}

const useStyles = makeStyles({
    root: {
        fontFamily: "Times new roman",
        width: "100%",
    },
    container: {
        maxHeight: "auto",
    },
});

export default function StickyHeadTable({ mealAdded }) {
    const classes = useStyles();
    const [open, setOpen] = useState(false);
    const [deleteMeal, setDeleteMeal] = useState(false);
    const [confirmationOpen, setConfirmationOpen] = useState(false);
    const [messageOpen, setMessageOpen] = useState(false);
    const [mealIndex, setMealIndex] = useState(0);

    const handleCloseMessage = () => {
        setMessageOpen(false);
    };

    const handleClickOpenConfirmation = () => {
        setConfirmationOpen(true);
    };

    const handleCloseConfirmation = () => {
        setConfirmationOpen(false);
    };

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const [meals, setMeals] = React.useState([]);

    const [values, setValues] = useState({});
    const [editMeal, setEditMeal] = useState(false);
    const handleEditMealSubmit = () => {
        setEditMeal(true);
    };

    useEffect(() => {
        async function deleteMealRequest() {
            const response = await request(
                axios.delete,
                "/manager/restaurant/" +
                    getLocalStorage("rid") +
                    "/meals/" +
                    meals[mealIndex].name
            );

            console.log(response);
            setDeleteMeal(false);
            handleCloseConfirmation();
        }

        if (deleteMeal == true) {
            deleteMealRequest();
        }
    }, [deleteMeal]);

    useEffect(() => {
        async function getMeals() {
            const response = await request(
                axios.get,
                "/restaurant/" + getLocalStorage("rid") + "/meals"
            );
            var meals = [];

            for (var i in response.data.data) {
                const k = i;
                meals.push(
                    createData(
                        response.data.data[i]["name"],
                        response.data.data[i]["price"],
                        response.data.data[i]["type"],
                        response.data.data[i]["ingredients"].join(),
                        response.data.data[i]["description"],
                        <>
                            <IconButton
                                onClick={() => {
                                    handleClickOpen();
                                    setMealIndex(k);
                                }}
                            >
                                <EditIcon style={{ fill: "green" }} />
                            </IconButton>
                            <IconButton
                                onClick={() => {
                                    handleClickOpenConfirmation();
                                    setMealIndex(k);
                                }}
                            >
                                <DeleteIcon style={{ fill: "red" }} />
                            </IconButton>
                        </>
                    )
                );
            }

            setMeals(meals);
        }
        getMeals();
    }, [editMeal, deleteMeal, mealAdded]);

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
                                    style={{
                                        minWidth: column.minWidth,
                                        width: column.width,
                                    }}
                                >
                                    {column.label}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {meals.map((row) => {
                            return (
                                <TableRow
                                    hover
                                    role="checkbox"
                                    tabIndex={-1}
                                    key={row.code}
                                >
                                    {columns.map((column) => {
                                        const value = row[column.id];
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
            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="form-dialog-title"
            >
                <DialogTitle id="form-dialog-title">Edit Meal</DialogTitle>
                <form
                    onSubmit={(e) => {
                        e.preventDefault();
                        handleEditMealSubmit();
                        handleClose();
                    }}
                >
                    <DialogContent>
                        <TextField
                            defaultValue={
                                meals[mealIndex] == null
                                    ? ""
                                    : meals[mealIndex].name
                            }
                            autoFocus
                            margin="dense"
                            id="name"
                            label="Name"
                            fullWidth
                            required
                        />
                        <TextField
                            defaultValue={
                                meals[mealIndex] == null
                                    ? ""
                                    : meals[mealIndex].price
                            }
                            label="Price"
                            name="numberformat"
                            id="price"
                            type="number"
                            fullWidth
                            required
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        <TextField
                            defaultValue={
                                meals[mealIndex] == null
                                    ? ""
                                    : meals[mealIndex].type
                            }
                            autoFocus
                            required
                            margin="dense"
                            id="type"
                            label="Type"
                            fullWidth
                        />
                        <TextField
                            defaultValue={
                                meals[mealIndex] == null
                                    ? ""
                                    : meals[mealIndex].ingredients
                            }
                            autoFocus
                            required
                            helperText="Seperate each ingredient with comma"
                            margin="dense"
                            id="ingredients"
                            label="Ingredients"
                            fullWidth
                        />
                        <TextField
                            defaultValue={
                                meals[mealIndex] == null
                                    ? ""
                                    : meals[mealIndex].description
                            }
                            id="description"
                            label="Description"
                            multiline
                            required
                            rowsMax={4}
                            fullWidth
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button
                            type="submit"
                            onClick={() => {
                                values.description = document.getElementById(
                                    "description"
                                ).value;
                                values.ingredients = document
                                    .getElementById("ingredients")
                                    .value.split(",");
                                values.name = document.getElementById(
                                    "name"
                                ).value;
                                values.price = document.getElementById(
                                    "price"
                                ).value;
                                values.type = document.getElementById(
                                    "type"
                                ).value;
                            }}
                            color="primary"
                        >
                            Save
                        </Button>
                    </DialogActions>
                </form>
            </Dialog>
            <Dialog
                open={confirmationOpen}
                onClose={handleCloseConfirmation}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">
                    {"Are you sure you want to delete this meal?"}
                </DialogTitle>
                <DialogContent></DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseConfirmation} color="primary">
                        No
                    </Button>
                    <Button
                        onClick={() => {
                            setDeleteMeal(true);
                        }}
                        color="primary"
                        autoFocus
                    >
                        Yes
                    </Button>
                </DialogActions>
            </Dialog>
        </Paper>
    );
}

StickyHeadTable.propTypes = {
    mealAdded: PropTypes.bool.isRequired,
};
