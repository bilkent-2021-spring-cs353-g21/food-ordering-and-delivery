import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Landing from "./pages/Landing";
import Search from "./pages/Search";
import Restaurant from "./pages/Restaurant";
import Order from "./pages/Order";

function App() {
    return (
        <Router>
            <Switch>
                <Route exact path="" component={Restaurant}></Route>
            </Switch>
        </Router>
    );
}

export default App;
