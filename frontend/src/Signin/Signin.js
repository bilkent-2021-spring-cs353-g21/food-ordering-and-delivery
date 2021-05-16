import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import FormSignin from "./FormSignin";
import ManagerLanding from "../ManagerLanding/ManagerLanding";

const signinBackground = makeStyles((theme) => ({
  root: {
    minHeight: "100vh",
    display: "flex",
    backgroundImage: "url(/img/signin.jpg)",
    backgroundRepeat: "no-repeat",
    backgroundPosition: "center",
    backgroundSize: "cover",
  },
}));

const Signin = () => {
  const [isSubmitted, setIsSubmitted] = useState(false);

  function submitForm() {
    setIsSubmitted(true);
  }

  const classes = signinBackground();

  return (
    <div className={classes.root}>
      <FormSignin></FormSignin>
    </div>
  );
};
export default Signin;
