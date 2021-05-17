import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Landing from "./pages/Landing";
import Search from "./pages/search/Search";
import Restaurant from "./pages/Restaurant";
import Order from "./pages/Order";
import Signin from "./Signin/Signin";
import Signup from "./Signup/Signup";
import Home from "./Home";
import ManagerLanding from "./ManagerLanding/ManagerLanding";
import DelivererLanding from "./DelivererLanding/DelivererLanding";
import DelivererSignup from "./DelivererSignup/DelivererSignup";

function App() {
    return (
        <Router>
            <Switch>
                <Route exact path="/restaurant/:rid" component={Restaurant} />
                <Route exact path="/search" component={Search}></Route>
                <Route path="/:userType/signin" component={Signin}></Route>
                <Route path="/:userType/signup" component={Signup}></Route>
                <Route exact path="/landing" component={Landing}></Route>
                <Route exact path="/order" component={Order}></Route>

                <Route exact path="/" component={Home}></Route>
                <Route exact path="/manager" component={ManagerLanding}></Route>
                <Route
                    exact
                    path="/deliverer"
                    component={DelivererSignup}
                ></Route>
                <Route
                    exact
                    path="/delivererLanding"
                    component={DelivererLanding}
                ></Route>
            </Switch>
        </Router>
    );
}

export default App;
