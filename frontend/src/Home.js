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
            <Link href="/signup">Sign up</Link>
        </div>
    );
};

export default Home;
