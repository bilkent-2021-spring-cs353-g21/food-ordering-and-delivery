import { makeStyles, Paper, AppBar, Link, Toolbar } from "@material-ui/core";
import React from "react";
{
    /*import backgroundImg from "~/assets/landing_background.jpg";*/
}
import adImg from "~/assets/ad.jpg";
import AddressPrompt from "~/components/AddressPrompt";
import MyButton from "~/components/MyButton";
import SiteHeader from "~/components/SiteHeader";

const useStyles = makeStyles(() => ({
    ad: {
        aspectRatio: 0.2,
        margin: 14,
    },
    header: {
        background: "#F2C94C",
    },
    logo: {
        marginLeft: 30,
        fontSize: 30,
    },
    panel: {
        background: "#F2C94C",
        maxHeight: 600,
        minWidth: 200,
        minHeight: 400,
        maxWidth: 300,
        marginLeft: 60,
        borderRadius: 0,
    },
    tabs: {
        margin: 40,
    },
}));

export default function Landing() {
    const classes = useStyles();
    const district = localStorage.getItem("district");
    return (
        <>
            {district !== "" ? (
                <>
                    <SiteHeader />

                    <Link
                        href="/aboutus"
                        className={classes.tabs}
                        style={{ float: "right" }}
                    >
                        About Us
                    </Link>
                    <Link
                        href="/offers"
                        className={classes.tabs}
                        style={{ float: "right" }}
                    >
                        Offers
                    </Link>

                    <Paper
                        className={classes.panel}
                        style={{
                            display: "flex",
                        }}
                    >
                        <img
                            className={classes.ad}
                            src={adImg}
                            style={{
                                flex: 1,
                            }}
                        />
                    </Paper>

                    <AppBar position="static" className={classes.header}>
                        <Toolbar
                            style={{
                                display: "flex",
                                justifyContent: "space-between",
                            }}
                        >
                            <MyButton className={classes.myButton}>
                                Restaurant Manager Sign Up / In
                            </MyButton>
                            <MyButton className={classes.myButton}>
                                Deliverer Sign Up / In
                            </MyButton>
                        </Toolbar>
                    </AppBar>
                </>
            ) : (
                <AddressPrompt />
            )}
        </>
    );
}
