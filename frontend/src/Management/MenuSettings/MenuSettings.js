import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import "./MenuSettings.css";
import MenuTable from "./MenuTable";
import Button from "@material-ui/core/Button";
import FastfoodIcon from "@material-ui/icons/Fastfood";
import TextField from "@material-ui/core/TextField";
import { getLocalStorage } from "../../Service/localStorage";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Dialog from "@material-ui/core/Dialog";
import axios from "axios";
import request from "../../Service/request";

const useStyles = makeStyles(() => ({
    addButton: {
        fontSize: "17px",
        backgroundColor: "rgb(80, 190, 37)",
        color: "rgb(0, 0, 0)",
        "&:hover": {
            backgroundColor: "rgb(80, 190, 37)",
            color: "#rgb(0, 0, 0)",
        },
        marginBottom: "0px",
    },
}));

export const MenuSettings = () => {
    const classes = useStyles();

    const [open, setOpen] = useState(false);
    const [addMeal, setAddMeal] = useState(false);
    const [values, setValues] = useState({});

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    useEffect(() => {
        async function addMealRequest() {
            const response = await request(
                axios.put,
                "/manager/restaurant/" + getLocalStorage("rid") + "/meals",
                {
                    description: values.description,
                    ingredients: values.ingredients,
                    name: values.name,
                    price: values.price,
                    type: values.type,
                }
            );

            setAddMeal(false);
        }

        if (addMeal == true) {
            addMealRequest();
        }
    }, [addMeal]);

    const handleAddMealSubmit = () => {
        setAddMeal(true);
    };

    return (
        <div>
            <div className="button-container">
                <Button
                    type="submit"
                    variant="contained"
                    color="secondary"
                    startIcon={<FastfoodIcon />}
                    className={classes.addButton}
                    onClick={handleClickOpen}
                >
                    Add Meal
                </Button>
                <Dialog
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">
                        {"New Meal"}
                    </DialogTitle>
                    <form
                        onSubmit={(e) => {
                            e.preventDefault();
                            handleAddMealSubmit();
                            handleClose();
                        }}
                    >
                        <DialogContent>
                            <TextField
                                required
                                margin="dense"
                                id="name"
                                label="Name"
                                fullWidth
                            />
                            <TextField
                                required
                                margin="dense"
                                id="description"
                                label="Description"
                                helperText="Description of the meal"
                                fullWidth
                            />
                            <TextField
                                required
                                margin="dense"
                                id="ingredients"
                                label="Ingredients"
                                helperText="Seperate each ingredient with comma"
                                fullWidth
                            />
                            <TextField
                                required
                                margin="dense"
                                id="type"
                                label="Type"
                                fullWidth
                            />
                            <TextField
                                required
                                margin="dense"
                                id="price"
                                label="Price"
                                fullWidth
                                type="number"
                                InputLabelProps={{
                                    shrink: true,
                                }}
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
                                Add
                            </Button>
                        </DialogActions>
                    </form>
                </Dialog>
            </div>
            <div className="container">
                <h1 className="h1">Menu</h1>
                <MenuTable mealAdded={addMeal}></MenuTable>
            </div>
        </div>
    );
};
