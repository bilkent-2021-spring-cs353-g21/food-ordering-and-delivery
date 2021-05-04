import React from "react";
import Signup from "./Signup/Signup";
import Signin from "./Signin/Signin";
import Home from "./Home";
import ManagerLanding from "./ManagerLanding/ManagerLanding";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

function App() {
    return (
        <Router>
            <Switch>
                <Route exact path="/" component={Home}></Route>
                <Route exact path="/signup" component={Signup}></Route>
                <Route exact path="/signin" component={Signin}></Route>
                <Route exact path="/manager" component={ManagerLanding}></Route>
            </Switch>
        </Router>
    );
}

export default App;
