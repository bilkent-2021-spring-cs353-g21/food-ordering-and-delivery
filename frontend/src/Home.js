import React from "react";
import { Link } from "@material-ui/core";

const Home = () => {
    return (
        <div
            style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                height: "100vh",
            }}
        >
            <Link href="/signin">Sign in</Link>
            <br></br>
            <Link href="/signup">Sign up</Link>
            <br></br>
            <Link href="/deliverer">Deliverer Sign up</Link>
            <br></br>

        </div>
    );
};

export default Home;
