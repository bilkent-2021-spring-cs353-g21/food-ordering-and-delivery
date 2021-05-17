import React from "react";
import { Typography, Box } from "@material-ui/core";
import PopupState, { bindTrigger, bindPopover } from "material-ui-popup-state";
import Popover from "@material-ui/core/Popover";
import MyButton from "~components/MyButton";

export default function AddMealPopup() {
    return (
        <PopupState variant="popover" popupId="demo-popup-popover">
            {(popupState) => (
                <div>
                    <MyButton {...bindTrigger(popupState)}>
                        Open Popover
                    </MyButton>
                    <Popover
                        {...bindPopover(popupState)}
                        anchorOrigin={{
                            vertical: "bottom",
                            horizontal: "center",
                        }}
                        transformOrigin={{
                            vertical: "top",
                            horizontal: "center",
                        }}
                    >
                        <Box p={2}>
                            <Typography>The content of the Popover.</Typography>
                        </Box>
                    </Popover>
                </div>
            )}
        </PopupState>
    );
}
