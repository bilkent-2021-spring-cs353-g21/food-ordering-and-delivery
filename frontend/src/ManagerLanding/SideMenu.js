import React from "react";
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
import { getLocalStorage } from "../Service/localStorage";
import AddCircleIcon from "@material-ui/icons/AddCircle";

const sideMenuStyles = makeStyles((theme) => ({
  root: {
    width: "100%",
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
  },
  nested: {
    paddingLeft: theme.spacing(4),
  },
}));

function SideMenuItem() {
  const classes = sideMenuStyles();
  const [open, setOpen] = React.useState(false);

  const handleClick = () => {
    setOpen(!open);
  };

  return (
    <div>
      <br />
      <ListItem button onClick={handleClick}>
        <ListItemIcon>
          <RestaurantIcon />
        </ListItemIcon>
        <ListItemText primary="Restaurant" />
        {open ? <ExpandLess /> : <ExpandMore />}
      </ListItem>
      <Collapse in={open} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItem button className={classes.nested}>
            <ListItemIcon>
              <SettingsIcon />
            </ListItemIcon>
            <ListItemText primary="Settings" />
          </ListItem>
        </List>
      </Collapse>
      <Collapse in={open} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItem button className={classes.nested}>
            <ListItemIcon>
              <RestaurantMenuIcon />
            </ListItemIcon>
            <ListItemText primary="Menu" />
          </ListItem>
        </List>
      </Collapse>
      <Collapse in={open} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItem button className={classes.nested}>
            <ListItemIcon>
              <ReceiptIcon />
            </ListItemIcon>
            <ListItemText primary="Orders" />
          </ListItem>
        </List>
      </Collapse>
      <Collapse in={open} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItem button className={classes.nested}>
            <ListItemIcon>
              <CommentIcon />
            </ListItemIcon>
            <ListItemText primary="Comments" />
          </ListItem>
        </List>
      </Collapse>
    </div>
  );
}

export default function SideMenu() {
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
      <ListItem button onClick={handleClick}>
        <ListItemIcon>
          <AccountCircle />
        </ListItemIcon>
        <ListItemText primary={getLocalStorage("username")} />
      </ListItem>
      <Divider />
      <ListItem button onClick={handleClick}>
        <ListItemIcon>
          <AddCircleIcon />
        </ListItemIcon>
        <ListItemText primary="Add Restaurant" />
      </ListItem>
      <Divider />
      <SideMenuItem></SideMenuItem>
      <SideMenuItem></SideMenuItem>
      <SideMenuItem></SideMenuItem>
    </List>
  );
}
