import React from "react";
import "./SignupForm.css";
import { Link, makeStyles } from "@material-ui/core";

const linkStyle = makeStyles({
  link: {
    textDecoration: "none",
    color: "#1c88a8",
    fontWeight: "600",
    fontFamily: "Times New Roman",
  },
});

const FormSuccess = () => {
  const classes = linkStyle();
  return (
    <div className="signup-success-container">
      <h1 className="signup-success-h1">We have received your request!</h1>
      <img
        className="signup-success-img"
        src="img/delivery-guy.gif"
        alt="success-image"
      />
      <h2 className="signup-success-h2">
        Click{" "}
        <Link className={classes.link} href="/signin">
          here
        </Link>{" "}
        to sign in
      </h2>
    </div>
  );
};

export default FormSuccess;
